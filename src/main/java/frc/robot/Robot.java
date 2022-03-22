// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.indexer.EjectThenOutdex;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.playstationControls;
import frc.robot.subsystems.xboxControls;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final Manipulator m_manipulator = new Manipulator();
  private final Indexer m_Indexer = new Indexer();
  
  private final EjectThenOutdex m_lowSpeed = new EjectThenOutdex(
    m_manipulator, 0.5, 1,
    m_Indexer, 0.5
  );
  private final EjectThenOutdex m_highSpeed = new EjectThenOutdex(
    m_manipulator, 0.9, 1,
    m_Indexer, 0.5
  );

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture();
  }

  

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }
  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
    Manipulator.liftSpark.restoreFactoryDefaults();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // Indexer input
    if (playstationControls.psController.getRawButton(5)) {
      Manipulator.rightIntake.set(ControlMode.PercentOutput, .5);
      Manipulator.leftIntake.set(ControlMode.PercentOutput, -.5);
      Indexer.rightIndexer.set(ControlMode.PercentOutput, .6);
      Indexer.leftIndexer.set(ControlMode.PercentOutput, .6); 
    }
    // Shoot
    else if (playstationControls.psController.getRawButton(3)) {
      m_lowSpeed.execute();
    }
    // Highspeed Shoot
    else if (playstationControls.psController.getRawButton(4)) {
      m_highSpeed.execute();
    }
      
    else {
      Manipulator.rightIntake.set(ControlMode.PercentOutput, 0);
      Manipulator.leftIntake.set(ControlMode.PercentOutput, 0);
      Indexer.rightIndexer.set(ControlMode.PercentOutput, 0);
      Indexer.leftIndexer.set(ControlMode.PercentOutput, 0);
      m_lowSpeed.cancel();
      m_highSpeed.cancel();
    }


    // Climb up function
    if (playstationControls.psController.getRawButton(8)) {
      Climber.climb.set(-0.8);
    }
    // Climb down function
    else if (playstationControls.psController.getRawButton(7)) {
      Climber.climb.set(0.8);
    }
    // If nothing is being pressed climb stops
    else {
      Climber.climb.set(0);
    }

    // Intake lift mechanism
    if (playstationControls.psController.getRawButton(2)) {
      m_manipulator.moveToSetpoint(130, 1);
    }
    // Intake lift down
    else if (playstationControls.psController.getRawButton(1)) {
    Manipulator.liftSpark.set(-1);
    }
    // Intake Lift set position
    else if (playstationControls.psController.getRawButton(6)) {
      m_manipulator.moveToSetpoint(145, 1);
    }
    // Intake Lift Auton Position
    else if (xboxControls.xboxController.getRawButton(8)) {
      m_manipulator.moveToSetpoint(130, 0.5);
    }
    else {
      Manipulator.liftSpark.set(0);
    }

  }


  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
