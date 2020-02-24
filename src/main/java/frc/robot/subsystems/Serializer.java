/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
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
  public AnalogInput serializerSensor1;
  public AnalogInput serializerSensor2;
  public AnalogInput launcherSensor;
  public double ballCount = 0.0;
  public boolean acceptingBalls = true;
  public boolean previousLSValue = false;
  public boolean previousSSValue = false;
  public boolean serializerSensor1Value;
  public boolean serializerSensor2Value;
  public double previousBallCount;

  public Serializer() {
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

  public void periodic() {
    ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
    System.out.println("running periodic: " + serializerSensor1.getVoltage());
    SmartDashboard.putNumber("Sensor 1: ", serializerSensor1.getVoltage()); // true
    SmartDashboard.putNumber("Sensor 2: ", serializerSensor2.getVoltage()); // true

    if (!previousSSValue && serializerSensor2.getVoltage() < .85 && acceptingBalls) {
      ballCount++;
      // update ballCount
      ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
      SmartDashboard.putNumber("Ball Count: ", ballCount);
    }
    previousSSValue = serializerSensor2.getVoltage() < 0.85;

    acceptingBalls = ballCount < 5 && ballCount >= 0;

    if (acceptingBalls) {
      if (serializerSensor2Value) {
        if (previousBallCount == ballCount) {
          ballCount++;
          ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
          SmartDashboard.putNumber("Ball Count", ballCount);
        }
      } else {
        previousBallCount = ballCount;
      }
    }

    if (launcherSensor.getVoltage() < 0.85) {
      if (ballCount > 0 && !previousLSValue) {
        ballCount--;
        ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
        SmartDashboard.putNumber("Ball Count: ", ballCount);
      }
    }
    previousLSValue = launcherSensor.getVoltage() < .85;

    if ((serializerSensor1.getVoltage() < .85 || serializerSensor2.getVoltage() < .85) && acceptingBalls) {
      // serializerMotor1.set(ControlMode.PercentOutput, 0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    } else {
      // serializerMotor1.set(ControlMode.PercentOutput, 0);
      SmartDashboard.putBoolean("Belts On: ", false);
    }
    ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
    SmartDashboard.putNumber("Ball Count", ballCount);

  }

  public void moveBeltsForward() {
    acceptingBalls = false;
    while (launcherSensor.getVoltage() < .85) {
      // serializerMotor1.set(ControlMode.PercentOutput, 0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    }
    // serializerMotor1.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putBoolean("Belts On: ", false);
  }

  public void moveBack() {
    while (serializerSensor2.getVoltage() < .85) {
      // serializerMotor1.set(ControlMode.PercentOutput, -0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    }
    // serializerMotor1.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putBoolean("Belts On: ", false);
    acceptingBalls = true;
  }

}
