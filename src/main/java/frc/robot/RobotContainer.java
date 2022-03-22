// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.Score;
import frc.robot.commands.autonomous.ScoreHighThenDriveOffTarmac;
import frc.robot.commands.autonomous.ScoreLowThenDriveOffTarmac;
import frc.robot.commands.autonomous.driveOffTarmac;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.driveTrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  private final driveTrain m_drivetrain = new driveTrain();
  private final Indexer m_indexer = new Indexer();
  private final Manipulator m_manipulator = new Manipulator();
  public SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  private final driveOffTarmac m_autoDriveOffTarmac = new driveOffTarmac(m_drivetrain, 0.5);
  private final ScoreHighThenDriveOffTarmac m_autoScoreHighThenDriveOffTarmac = new ScoreHighThenDriveOffTarmac(
    m_manipulator, .9, 
    m_indexer, 0.6, 
    m_drivetrain, 0.5
  );
  private final ScoreLowThenDriveOffTarmac m_autoScoreLowThenDriveOffTarmac = new ScoreLowThenDriveOffTarmac(
    m_manipulator, 0.5, 
    m_indexer, 0.6, 
    m_drivetrain, 0.5
  );
  private final Score m_autoScore = new Score(
    m_manipulator, 0.5, 
    m_indexer, 0.6
  );
  private final Score m_autoScoreHigh = new Score(
    m_manipulator, .9,
    m_indexer, .6
  );
  
  

  public void Periodic() {
    
  }

  public RobotContainer() {
    // Configure the button bindings
    
    // Add commands to the autonomous command chooser
    m_chooser.setDefaultOption("ScoreHighThenDriveOffTarmac", m_autoScoreHighThenDriveOffTarmac);
    m_chooser.addOption("ScoreLowThenDriveOffTarmac", m_autoScoreLowThenDriveOffTarmac);
    m_chooser.addOption("ScoreHigh", m_autoScoreHigh);
    m_chooser.addOption("Score", m_autoScore);
    m_chooser.addOption("DriveOffTarmac", m_autoDriveOffTarmac);

    // Put the chooser on the dashboard
    SmartDashboard.putData(m_chooser);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
    public Command getAutonomousCommand() {
      return m_chooser.getSelected();
    }
  }

