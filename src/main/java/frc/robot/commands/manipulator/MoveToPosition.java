// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.manipulator;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Manipulator;

public class MoveToPosition extends CommandBase {
  // Create an object for the Manipulator
  private final Manipulator m_manipulator;

  // Create DoubleSuppliers for the setpoint and power
  private final DoubleSupplier m_setpoint, m_power;

  /** Creates a new MoveToPosition. */
  public MoveToPosition(Manipulator manipulator, DoubleSupplier setpoint, DoubleSupplier power) {
    // Use the Manipulator subsystem to gain access to its commands
    m_manipulator = manipulator;

    // Set the setpoint and power
    m_setpoint = setpoint;
    m_power = power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(manipulator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Reset the encoder
    m_manipulator.resetliftEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Call the moveToSetpoint method
    m_manipulator.moveToSetpoint(m_setpoint.getAsDouble(), m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_manipulator.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
