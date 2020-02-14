<<<<<<< HEAD
@@-24,18+24,15 @@
public class Serializer extends SubsystemBase {
  public DigitalInput serializerSensor1;
  public DigitalInput serializerSensor2;
  public DigitalInput launcherSensor;
  public double ballCount;
  public double ballCount = 0;
=======
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
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
  public DigitalInput serializerSensor1;
  public DigitalInput serializerSensor2;
  public DigitalInput launcherSensor;
  public double ballCount = 0.0;
>>>>>>> 93b780bff3896f4a868f0fb9f563accf846487af
  public boolean acceptingBalls = true;
  public boolean previousLSValue = false;
  public boolean previousSSValue = false;
  public double previousBallCount;

  public Serializer() {
    serializerSensor1 = new DigitalInput(Constants.PHOTOELECTRIC_SENSOR_1);
    serializerSensor2 = new DigitalInput(Constants.PHOTOELECTRIC_SENSOR_2);
    launcherSensor = new DigitalInput(Constants.PHOTOELECTRIC_SENSOR_3);
    ballCount = SmartDashboard.getNumber("Ball Count: ", 0);
    previousBallCount = 0;

    /*
     * serializerMotor1 = new WPI_TalonSRX(Constants.TOP_SERIALIZER_MOTOR);
@ -52,41 +49,6 @@ public class Serializer extends SubsystemBase {
  }

  public void periodic() {
    ballCount = SmartDashboard.getNumber("Ball Count", ballCount);
    System.out.println("running periodic: " + serializerSensor1.get());
    SmartDashboard.putBoolean("Sensor 1: ", !serializerSensor1.get()); // true
    SmartDashboard.putBoolean("Sensor 2: ", !serializerSensor2.get()); // true

    // if (!previousSSValue && serializerSensor2.get() && acceptingBalls) {
    // ballCount++;
    // update ballCount
    // SmartDashboard.putNumber("Ball Count: ", ballCount);
    // }
    // previousSSValue = serializerSensor2.get();

    if (ballCount < 5 && ballCount >= 0) {
      acceptingBalls = true;
    } else {
      acceptingBalls = false;
    }

    if (acceptingBalls) {
      if (serializerSensor2.get()) {
        if (previousBallCount == ballCount) {
          ballCount++;
          SmartDashboard.putNumber("Ball Count", ballCount);
        }
      } else {
        previousBallCount = ballCount;
      }
<<<<<<< HEAD
    System.out.println("Hello");
    SmartDashboard.putBoolean("Sensor 1: ", serializerSensor1.get()); // true
    SmartDashboard.putBoolean("Sensor 2: ", serializerSensor2.get()); // true
@ -111,30 +73,15 @@ public class Serializer extends SubsystemBase {
      SmartDashboard.putNumber("Ball Count: ", ballCount);
    }
    previousSSValue = serializerSensor2.get();

    if (serializerSensor1.get() || serializerSensor2.get() && acceptingBalls) {
    /*if (ballCount >= 5) {
        acceptingBalls = false;
    }
    else {
      acceptingBalls = true;
     }
       if (launcherSensor.get()){
            if (ballCount >0 && !previousLSValue){
            ballCount--;
        SmartDashboard.putNumber("Ball Count: ", ballCount);
=======
    }

    if (!launcherSensor.get()) {
      if (previousBallCount == ballCount && ballCount >= 0) {
        ballCount--;
        SmartDashboard.putNumber("Ball Count", ballCount);
>>>>>>> 93b780bff3896f4a868f0fb9f563accf846487af
      }
    }

    /*
     * 
     * if(ballCount <= 5 && ballCount >= 0){ if(launcherSensor.get()){
     * if(previousBallCount == ballCount){ SmartDashboard.getNumber("Ball Count",
     * ballCount); ballCount--; SmartDashboard.putNumber("Ball Count", ballCount); }
     * } else{ previousBallCount = ballCount; } System.out.println("Hello");
     * SmartDashboard.putBoolean("Sensor 1: ", serializerSensor1.get()); // true
     * SmartDashboard.putBoolean("Sensor 2: ", serializerSensor2.get()); // true
     * 
     * if (ballCount >= 5) { acceptingBalls = false; }else { acceptingBalls = true;
     * }
     * 
     * if (launcherSensor.get()){ if (ballCount >0 && !previousLSValue){
     * ballCount--; SmartDashboard.putNumber("Ball Count: ", ballCount); }
     * 
     * ballCount = SmartDashboard.getNumber("Ball Count: ", ballCount);
     * System.out.println(serializerSensor1.get());
     * 
     * if (!previousSSValue && serializerSensor2.get() && acceptingBalls) {
     * ballCount++; SmartDashboard.putNumber("Ball Count: ", ballCount); }
     * previousSSValue = serializerSensor2.get(); if (serializerSensor1.get() ||
     * serializerSensor2.get() && acceptingBalls) { /*if (ballCount >= 5) {
     * acceptingBalls = false; } else { acceptingBalls = true; } if
     * (launcherSensor.get()){ if (ballCount >0 && !previousLSValue){ ballCount--;
     * SmartDashboard.putNumber("Ball Count: ", ballCount); } } previousLSValue =
     * launcherSensor.get();
     */

    if ((serializerSensor1.get() || serializerSensor2.get()) && acceptingBalls) {
      // serializerMotor1.set(ControlMode.PercentOutput, 0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    } else {
      // serializerMotor1.set(ControlMode.PercentOutput, 0);
      SmartDashboard.putBoolean("Belts On: ", false);
    }
<<<<<<< HEAD
    SmartDashboard.putNumber("Ball Count", ballCount){
  
    }
    previousLSValue = launcherSensor.get();
=======
    SmartDashboard.putNumber("Ball Count", ballCount);
  }

  public void moveBeltsForward() {
    acceptingBalls = false;
    while (launcherSensor.get()) {
      // serializerMotor1.set(ControlMode.PercentOutput, 0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    }
    // serializerMotor1.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putBoolean("Belts On: ", false);
  }

  public void moveBack() {
    while (serializerSensor2.get()) {
      // serializerMotor1.set(ControlMode.PercentOutput, -0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    }
    // serializerMotor1.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putBoolean("Belts On: ", false);
    acceptingBalls = true;
  }

}
>>>>>>> 93b780bff3896f4a868f0fb9f563accf846487af
