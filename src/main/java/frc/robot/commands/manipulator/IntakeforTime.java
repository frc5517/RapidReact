// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.indexer.Index;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;

public class IntakeforTime extends ParallelCommandGroup {
  /** Creates a new Intake. */
  public IntakeforTime(
    Manipulator manipulator, double manipulatorPower, int intakeTime,
    Indexer indexer, double indexerPower
  ) {

    super(new WaitCommand(intakeTime));
    addCommands(
    new Collect(manipulator, () -> manipulatorPower),
    new Index(indexer, () -> indexerPower)
    );
  }
}
