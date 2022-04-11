// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.vision.BallVision;

public class VisualTrackForTime extends ParallelDeadlineGroup {
  /** Creates a new VisualTrackForTime. */
  public VisualTrackForTime(int time, DriveTrain driveTrain, BallVision ballVision) {
    // Use addRequirements() here to declare subsystem dependencies.

    super(new WaitCommand(time));
    addCommands(
      new VisualTrack(
        driveTrain,
        ballVision
        ));

  }
}
