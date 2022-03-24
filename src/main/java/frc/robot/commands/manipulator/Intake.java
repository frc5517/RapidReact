// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.indexer.Index;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;

public class Intake extends ParallelCommandGroup {
  /** Creates a new Intake. */
  public Intake(
    Manipulator manipulator, double manipulatorPower,
    Indexer indexer, double indexerPower
  ) {

    addCommands(
    new Collect(manipulator, () -> manipulatorPower),
    new Index(indexer, () -> indexerPower)
    );
  }
}
