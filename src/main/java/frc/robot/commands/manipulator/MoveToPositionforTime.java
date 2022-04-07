// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Manipulator;

public class MoveToPositionforTime extends ParallelCommandGroup {
  /** Creates a new MoveToPositionforTime. */
  public MoveToPositionforTime(Manipulator manipulator, DoubleSupplier setpoint, DoubleSupplier power, int posTime) {
    
    super(new WaitCommand(posTime));
    addCommands(
      new MoveToPosition(manipulator, setpoint, power)
    );

  }
}
