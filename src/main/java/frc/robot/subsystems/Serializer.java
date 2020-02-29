/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Serializer extends SubsystemBase {
  /**
   * Creates a new Serializer.
   */
  // WPI_TalonSRX serializerMotor1;
  // WPI_TalonSRX serializerMotor2;
  // WPI_TalonFX serializerMotor; For use with Falcon 500

  // Initializes the sensors in the serializer and launcher
  public AnalogInput serializerSensor1;
  public AnalogInput serializerSensor2;
  public AnalogInput launcherSensor;

  // Initializes variables that wiil be used in the program
  public static double ballCount = 0.0;
  public boolean acceptingBalls = true;
  public boolean previousLSValue = false; // previous launcher sensor value
  public boolean previousSSValue = false; // previous serializer sensor value
  public boolean serializerSensor1Value; // first sensor true means ball
  public boolean serializerSensor2Value; // sec sensor
  public double previousBallCount;

  public Serializer() {
    // instantiates sensor values with respect to the contants method
    serializerSensor1 = new AnalogInput(Constants.PHOTOELECTRIC_SENSOR_1);
    serializerSensor2 = new AnalogInput(Constants.PHOTOELECTRIC_SENSOR_2);
    launcherSensor = new AnalogInput(Constants.PHOTOELECTRIC_SENSOR_3);
    ballCount = SmartDashboard.getNumber("Ball Count: ", 0);
    previousBallCount = 0;

    /*
     * serializerMotor1 = new WPI_TalonSRX(Constants.TOP_SERIALIZER_MOTOR);
     * 
     * serializerMotor1.configFactoryDefault();
     */
    // serializerMotor = new WPI_TalonFX(Constants.SERIALIZER_MOTOR);
  }

  
  // Called every time the Command Scheduler runs (every 20 miliseconds)
  public void periodic() {
    // Recieves possible user input from the smart dashboard
    ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
    // outputs current value to the smart dashboard
    System.out.println("running periodic: " + serializerSensor1.getVoltage());
    // Puts sensor voltage values on the Smart dashboard
    SmartDashboard.putNumber("Sensor 1: ", serializerSensor1.getVoltage()); // true
    SmartDashboard.putNumber("Sensor 2: ", serializerSensor2.getVoltage()); // true

    if (!previousSSValue && serializerSensor2.getVoltage() < .85 && acceptingBalls) {
      ballCount++;
      // update ballCount
      ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
      SmartDashboard.putNumber("Ball Count: ", ballCount);
      previousSSValue = true;
    }

    acceptingBalls = ballCount < 5; //Took out >= 0 because we should still be able to accept balls even when negative
/*
    if (acceptingBalls) {
      if (serializerSensor2Value) {
        if (previousBallCount == ballCount) {
          // adds one to the ball count
          ballCount++;
          // updates ballcount on smart dashboard
          ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
          SmartDashboard.putNumber("Ball Count", ballCount);
        }
      } else { 
        previousBallCount = ballCount;
      }
    }
*/
    if (launcherSensor.getVoltage() < 0.85) {
      if (ballCount > 0 && !previousLSValue) {
        // decrement ballCount by 1
        ballCount--;
        // update ballCount
        SmartDashboard.putNumber("Ball Count: ", ballCount);
      previousLSValue = true;
      }
    }

    if ((serializerSensor1.getVoltage() < .85 && acceptingBalls)) {
      //serializerMotor1.set(ControlMode.PercentOutput, 0.5);
      Timer.delay(0.12); //check 
      SmartDashboard.putBoolean("Belts On: ", true);
    } else {
      // serializerMotor1.set(ControlMode.PercentOutput, 0);
      SmartDashboard.putBoolean("Belts On: ", false);
    }
    ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
    SmartDashboard.putNumber("Ball Count", ballCount);

  }

  public void moveBeltsForward() {
    // accepting balls is set to false to stop incorrect ball placement in the
    // serializer
    acceptingBalls = false;
    // turns serializer motor on
    // serializerMotor1.set(ControlMode.PercentOutput, 0.5);
    // lets us know if the belts are running
    SmartDashboard.putBoolean("Belts On: ", true);
    // changes the amount of time moved forward based on the ball count
    Timer.delay(0.12 * ballCount); //check
    // turns serializer motor on
    // serializerMotor1.set(ControlMode.PercentOutput, 0);
    // outputs belt state to the smart dashboard
    SmartDashboard.putBoolean("Belts On: ", false);
  }

  public void moveBack() {
    // runs belts until sensor at the start of the serializer is triggered
    while (serializerSensor2.getVoltage() < .85) {
      // starts belts in inverse
      // serializerMotor1.set(ControlMode.PercentOutput, -0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    }
    // stops belts
    // serializerMotor1.set(ControlMode.PercentOutput, 0);
    // outputs belt states to the smart dashboard
    SmartDashboard.putBoolean("Belts On: ", false);
    // allows balls to be intaken again
    acceptingBalls = true;
  }

}
