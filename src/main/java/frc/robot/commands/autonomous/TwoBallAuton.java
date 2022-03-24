// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.driveTrain.DriveForward;
import frc.robot.commands.manipulator.MoveToPosition;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.driveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAuton extends SequentialCommandGroup {
  /** Creates a new ScoreThenDriveOffTarmac. */
  public TwoBallAuton(Manipulator manipulator, double manipulatorPower,
                                 Indexer indexer, double indexerPower,
                                 Manipulator armLift, DoubleSupplier setpoint, DoubleSupplier power,
                                 driveTrain drivetrain, double throttle, double rotation) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new MoveToPosition(armLift, setpoint, power),
      new DriveForward(drivetrain, throttle),
      new Rotate(drivetrain, throttle, rotation),
      new Score(manipulator, manipulatorPower, indexer, indexerPower),
      new driveOffTarmac(drivetrain, throttle)
    );
  }

  public TwoBallAuton(Manipulator m_manipulator, double manipulatorPower, Indexer m_indexer, double indexerPower,
      Manipulator m_armLift, int i, int j, driveTrain m_drivetrain, int throttle, double rotation) {
  }
}
