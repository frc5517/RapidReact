// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.indexer.Outdex;
import frc.robot.commands.manipulator.Eject;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Score extends ParallelDeadlineGroup {
  /** Creates a new ShootThenDriveOffTarmac. */
  public Score(Manipulator manipulator, double manipulatorPower,
               Indexer indexer, double indexerPower) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new WaitCommand(2));
    addCommands(
      new Outdex(indexer, () -> indexerPower),
      new Eject(manipulator, () -> manipulatorPower)
    );
  }
}
