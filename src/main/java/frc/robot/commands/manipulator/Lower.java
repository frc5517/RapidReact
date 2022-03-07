// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Manipulator;

public class Lower extends CommandBase {
  // Create an object for the manipulator
  private final Manipulator m_manipulator;

  // Create a DoubleSupplier for the power to apply to the motors
  private final DoubleSupplier m_power;

  /** Creates a new Lower. */
  public Lower(Manipulator manipulator, DoubleSupplier power) {
    // Use the Manipulator subsystem to gain access to its commands
    m_manipulator = manipulator;

    // Apply power to the motors
    m_power = power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(manipulator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Use the lower method from the Manipulator subsystem
    m_manipulator.lower(m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Use the stopArm method from the Manipulator subsystem
    m_manipulator.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
