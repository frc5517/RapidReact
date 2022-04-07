// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import frc.robot.commands.driveTrain.ArcadeDrive;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Rotate extends ParallelDeadlineGroup {
  /** Creates a new Rotate. */
  public Rotate(DriveTrain drivetrain, double throttle, double rotation) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new WaitCommand(3));
    addCommands(
      new ArcadeDrive(drivetrain, () -> 0, () -> rotation)
    );
  }
}

