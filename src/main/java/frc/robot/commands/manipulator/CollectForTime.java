// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Manipulator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CollectForTime extends ParallelDeadlineGroup {
  /** Creates a new ManipulateForTime. */
  public CollectForTime(Manipulator manipulator, DoubleSupplier power, int time) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new WaitCommand(time));
    addCommands(
      new Collect(manipulator, power)
    );
  }
}
