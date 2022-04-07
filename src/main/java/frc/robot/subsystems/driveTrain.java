// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  static WPI_VictorSPX leftRearMotor = new WPI_VictorSPX(DriveConstants.leftRearMotorPort);
  static WPI_VictorSPX leftFrontMotor = new WPI_VictorSPX(DriveConstants.leftFrontMotorPort);
  static MotorControllerGroup leftMotors = new MotorControllerGroup(leftFrontMotor, leftRearMotor);
  static WPI_VictorSPX rightRearMotor = new WPI_VictorSPX(DriveConstants.rightRearMotorPort);
  static WPI_VictorSPX rightFrontMotor = new WPI_VictorSPX(DriveConstants.rightFrontMotorPort);
  static MotorControllerGroup rightMotors = new MotorControllerGroup(rightFrontMotor, rightRearMotor);
  public static DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);
  public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  public static DriveTrain instance;

  // PID constants should be tuned per robot
  final double LINEAR_P = 0.1;
  final double LINEAR_D = 0.0;
  PIDController forwardController = new PIDController(LINEAR_P, 0, LINEAR_D);

  final double ANGULAR_P = 0.61;
  final double ANGULAR_D = 0.0;
  PIDController turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);

  final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(24);
    final double TARGET_HEIGHT_METERS = Units.feetToMeters(5);
    // Angle between horizontal and the camera.
    final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);

    // How far from the target we want to be
    final double GOAL_RANGE_METERS = Units.feetToMeters(3);

    // Change this to match the name of your camera

    private double _targetYaw;
    private boolean _hasTarget;

  public DriveTrain() {
    leftRearMotor.setNeutralMode(NeutralMode.Coast);
    leftFrontMotor.setNeutralMode(NeutralMode.Coast);
    rightRearMotor.setNeutralMode(NeutralMode.Coast);
    rightFrontMotor.setNeutralMode(NeutralMode.Coast);

    CommandScheduler.getInstance().registerSubsystem(this);

    SmartDashboard.putData(gyro);

    gyro.calibrate();
  
  }

  public void setMaxOutput(double maxOutput) {
  }

  public void stop() {
    // Call DifferentialDrive's stopMotor method
    drive.stopMotor();
  }

  
  @Override
  public void periodic() {

    leftMotors.setInverted(false);
    rightMotors.setInverted(true);
    
    // Drive slower
    if (joystickControls.leftStick.getRawButton(1)) {
      drive.setMaxOutput(.4);
    }
    else if (joystickControls.rightStick.getRawButton(1)) {
      drive.setMaxOutput(1);
    }
    else {
      DriveTrain.drive.setMaxOutput(.7);
    }

        // Use our forward/turn speeds to control the drivetrain
        
    }

  public void drive(double forwardSpeed, double rotationSpeed) {
    drive.arcadeDrive(forwardSpeed, rotationSpeed);
  }
  

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void visionTurn(DoubleSupplier speedSupplier, boolean hasTarget, double DEADZONE, double targetYaw) {
    double joyStickSpeed = speedSupplier.getAsDouble();
    double speed;
    this._hasTarget = hasTarget;
    this._targetYaw = targetYaw;
    //System.out.println("Turret 'speed': " + speed);
    
    if (this._hasTarget){
      speed = turnController.calculate(this._targetYaw, 0);
    }
    else{
      //deadzone clause, deadzone is 0.12 (or not, check TurretCommand.java)
      if(Math.abs(joyStickSpeed) > DEADZONE) {
        speed = joyStickSpeed*.75;
      }
      else {
        speed = 0;
      }
    }

    drive.setMaxOutput(speed);
  }

  public static DriveTrain getInstance() {
    if (instance == null) {
      instance = new DriveTrain();
    }
    return instance;
  }

  public void stopDriveTrain() {
    drive.stopMotor();
  }
  
}
