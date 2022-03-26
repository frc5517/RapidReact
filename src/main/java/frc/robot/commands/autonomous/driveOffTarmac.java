// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import frc.robot.commands.driveTrain.ArcadeDrive;
import frc.robot.subsystems.driveTrain;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class driveOffTarmac extends ParallelDeadlineGroup {
  /** Creates a new DriveOffTarmac. */
  public driveOffTarmac(driveTrain drivetrain, double throttle) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new WaitCommand(2));
    addCommands(
      new ArcadeDrive(drivetrain, () -> throttle, () -> 0)
    );
  }
}
