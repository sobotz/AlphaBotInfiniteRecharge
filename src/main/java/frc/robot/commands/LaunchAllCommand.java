/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Serializer;

public class LaunchAllCommand extends CommandBase {

  private Serializer serializer;
  private Launcher launcher;
  /**
   * Creates a new LaunchAllCommand.
   */
  public LaunchAllCommand(Serializer serializer1, Launcher launcher1) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.serializer = serializer1;
    this.launcher = launcher1;
    addRequirements(serializer);
    addRequirements(launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.serializer.moveBeltsForward();
    this.launcher.startLauncher();
    this.launcher.startRollers();
    while (this.serializer.ballCount != 0) {
      try {
        wait(10); 
      } catch (InterruptedException e) {
        e.printStackTrace(); // tell us where the problem might occur
      }
    }
    this.launcher.stopRollers();
    this.launcher.stopLauncher();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}