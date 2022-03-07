// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexer;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class Index extends CommandBase {
  // Create an object for the indexer
  private final Indexer m_indexer;

  // Create a DoubleSupplier for the power to apply to the motors
  private final DoubleSupplier m_power;

  /** Creates a new Index. */
  public Index(Indexer indexer, DoubleSupplier power) {
    // Use the Indexer subsystem to gain access to its commands
    m_indexer = indexer;
    
    // Apply power to the motors
    m_power = power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Use the index method from the Indexer subsystem
    m_indexer.index(m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Use the stop method from the Indexer subsystem
    m_indexer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
