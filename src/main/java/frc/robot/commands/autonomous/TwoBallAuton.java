// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.driveTrain.DriveForward;
import frc.robot.commands.manipulator.IntakeforTime;
import frc.robot.commands.manipulator.MoveToPositionforTime;
import frc.robot.commands.manipulator.ScoreforTime;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAuton extends SequentialCommandGroup {
  /** Creates a new ScoreThenDriveOffTarmac. */
  public TwoBallAuton(Manipulator manipulator, double manipulatorPower, double manipulatorPowerSlow,
                                 Indexer indexer, double indexerPower, int intakeTime,
                                 Manipulator armLift, DoubleSupplier setpoint, DoubleSupplier setpoint2, DoubleSupplier power, DoubleSupplier power2, int scoreTime, int posTime,
                                 DriveTrain drivetrain, double throttle, double rotation, double rotation2, int forwardTime) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new MoveToPositionforTime(armLift, setpoint, power, posTime),
      new Rotate(drivetrain, throttle, rotation),
          new ParallelCommandGroup(
          new DriveForward(drivetrain, throttle, forwardTime),
          new IntakeforTime(manipulator, manipulatorPowerSlow, intakeTime,indexer, indexerPower)
          ),
      new Rotate(drivetrain, throttle, rotation2),
      new MoveToPositionforTime(manipulator, setpoint2, power2, posTime),
      new DriveForward(drivetrain, throttle, forwardTime),
      new ScoreforTime(manipulator, manipulatorPower, scoreTime, forwardTime, indexer, indexerPower),
      new driveOffTarmac(drivetrain, throttle)
    );
  }
}
  

