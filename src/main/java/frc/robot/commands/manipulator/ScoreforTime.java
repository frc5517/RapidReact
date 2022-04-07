// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.indexer.Outdex;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreforTime extends ParallelCommandGroup {

  public ScoreforTime(
  Manipulator manipulator, double manipulatorPower, int time, int scoreTime,
  Indexer indexer, double indexerPower
) {
  // Add your commands in the addCommands() call, e.g.
  // addCommands(new FooCommand(), new BarCommand());
  super(new WaitCommand(scoreTime));
  addCommands(
    new Eject(manipulator, () -> manipulatorPower),
    sequence(new WaitCommand(time),
    new Outdex(indexer, () -> indexerPower)
    )
  );
  }
}
