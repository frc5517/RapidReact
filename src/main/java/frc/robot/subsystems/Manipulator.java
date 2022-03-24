// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SparkConstants;
import frc.robot.Constants.VictorConstants;

public class Manipulator extends SubsystemBase {
  /** Creates a new Intake. */

public static CANSparkMax liftSpark = new CANSparkMax(SparkConstants.liftPort, MotorType.kBrushless);
public static WPI_VictorSPX rightIntake = new WPI_VictorSPX(VictorConstants.rightIntakePort);
public static WPI_VictorSPX leftIntake = new WPI_VictorSPX(VictorConstants.leftIntakePort);

// Create a RelativeEncoder object for the arm motor
RelativeEncoder liftEncoder;

// Create a SparkMaxPIDController object for closed-loop control of the arm
public static SparkMaxPIDController liftPID;

// Create variables for PID control values
  // CHANGE VALUES AS FOLLOWS:
  // P: raise until the motor stops
  // I: raise until the motor stops smoothly
  double kP = 0.0,
         kI = 0.0,
         kD = 0.0,
         kIz = 0.0,
         kFF = 0.0,
         kMaxOutput = 1,
         kMinOutput = -1;

  public Manipulator() {
    rightIntake.setNeutralMode(NeutralMode.Coast);
    leftIntake.setNeutralMode(NeutralMode.Coast);
    liftSpark.setIdleMode(IdleMode.kBrake);

    liftSpark.restoreFactoryDefaults();

    // Allow closed-loop control of the arm
    liftPID = liftSpark.getPIDController();

    // Set the encoder to the correct motor
    liftEncoder = liftSpark.getEncoder();

    // Set the feedback device of the PID controller to the correct encoder
    liftPID.setFeedbackDevice(liftEncoder);

    // Set PID coefficients
    liftPID.setP(kP);
    liftPID.setI(kI);
    liftPID.setD(kD);
    liftPID.setIZone(kIz);
    liftPID.setFF(kFF);
    liftPID.setOutputRange(kMinOutput, kMaxOutput);

  }

  

  public void collect(double power) {
    // Apply voltage to the motor
    rightIntake.set(ControlMode.PercentOutput, power);
    leftIntake.set(ControlMode.PercentOutput, -power);
  }

  public void stopCollector() {
    // Apply no voltage to the motor
    rightIntake.set(ControlMode.PercentOutput, 0);
    leftIntake.set(ControlMode.PercentOutput, 0);
  }

  public void eject(double power) {
    // Apply voltage to the motor
    rightIntake.set(ControlMode.PercentOutput, -power);
    leftIntake.set(ControlMode.PercentOutput, power);
  }

  public void lower(double power) {
    liftSpark.set(-power);
  }


  public void raise(double power) {
    liftSpark.set(power);
  }

  public void stopArm() {
    liftSpark.stopMotor();
  }

  @Override
  public void periodic() { 
    liftSpark.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    liftSpark.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    liftSpark.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 200);
    liftSpark.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 10); 
    SmartDashboard.putNumber("Lift Position", liftEncoder.getPosition()); 

  }
  

  /**
   * moveToSetpoint - move the arm to the desired encoder value.
   * 
   * @param setpoint Desired encoder position
   * @param power Voltage being applied to the motor
   */
  public void moveToSetpoint(double setpoint, double power) {
    // Set power to the motor
    liftSpark.set(power);

    // Check encoder value
    if(liftEncoder.getPosition() >= setpoint) {
      // If the encoder is going to pass the setpoint, stop the motor
      liftSpark.stopMotor();}
    }
    /**
   * stopArm - stop the arm motors.
   */
  public void stopLift() {
    liftSpark.stopMotor();
  }

  public void resetliftEncoder() {
    // Set the position of the encoder to zero, effectively resetting it
    // liftEncoder.setPosition(0);
  }
  
}
