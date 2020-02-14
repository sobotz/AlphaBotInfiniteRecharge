@@-24,18+24,15 @@
public class Serializer extends SubsystemBase {
  public DigitalInput serializerSensor1;
  public DigitalInput serializerSensor2;
  public DigitalInput launcherSensor;
  public double ballCount;
  public double ballCount = 0;
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

    SmartDashboard.putBoolean("Sensor 1: ", serializerSensor1.get()); // true
    SmartDashboard.putBoolean("Sensor 2: ", serializerSensor2.get()); // true

   // if (!previousSSValue && serializerSensor2.get() && acceptingBalls) {
   //   ballCount++;
      //update ballCount
   //   SmartDashboard.putNumber("Ball Count: ", ballCount);
    //}
   // previousSSValue = serializerSensor2.get();

    if(ballCount <= 5 && ballCount >= 0 && acceptingBalls){
      if(serializerSensor2.get()){
        if(previousBallCount == ballCount){
          SmartDashboard.getNumber("Ball Count", ballCount);
          ballCount++;
          SmartDashboard.putNumber("Ball Count", ballCount); 
        }
      }
      else{
        previousBallCount = ballCount;
      }
    }

    if(ballCount <= 5 && ballCount >= 0){
      if(launcherSensor.get()){
        if(previousBallCount == ballCount){
          SmartDashboard.getNumber("Ball Count", ballCount);
          ballCount--;
          SmartDashboard.putNumber("Ball Count", ballCount);
        }
      }
      else{
        previousBallCount = ballCount;
      }
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
      }
    }
    previousLSValue = launcherSensor.get();
    */

    if ((serializerSensor1.get() || serializerSensor2.get()) && acceptingBalls) {
      // serializerMotor1.set(ControlMode.PercentOutput, 0.5);
      SmartDashboard.putBoolean("Belts On: ", true);
    } else {
      // serializerMotor1.set(ControlMode.PercentOutput, 0);
      SmartDashboard.putBoolean("Belts On: ", false);
    }
    SmartDashboard.putNumber("Ball Count", ballCount){
  
    }
    previousLSValue = launcherSensor.get();