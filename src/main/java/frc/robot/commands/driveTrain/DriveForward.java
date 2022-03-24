// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveTrain;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.driveTrain;

public class DriveForward extends ParallelDeadlineGroup {
  /** Creates a new DriveForward. */
  public DriveForward(driveTrain drivetrain, double throttle, int forwardTime) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new WaitCommand(forwardTime));
    addCommands(
      new ArcadeDrive(drivetrain, () -> throttle, () -> 0)
    );
  }
}

