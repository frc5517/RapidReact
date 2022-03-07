// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SparkConstants;
import frc.robot.Constants.VictorConstants;

public class Manipulator extends SubsystemBase {
  /** Creates a new Intake. */
public static RelativeEncoder liftEncoder;
public static CANSparkMax liftSpark = new CANSparkMax(SparkConstants.liftPort, MotorType.kBrushless);
public static WPI_VictorSPX rightIntake = new WPI_VictorSPX(VictorConstants.rightIntakePort);
public static WPI_VictorSPX leftIntake = new WPI_VictorSPX(VictorConstants.leftIntakePort);

  public Manipulator() {
    rightIntake.setNeutralMode(NeutralMode.Coast);
    leftIntake.setNeutralMode(NeutralMode.Coast);
    liftSpark.setIdleMode(IdleMode.kBrake);

    liftSpark.restoreFactoryDefaults();

    liftSpark.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    liftSpark.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    liftSpark.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 15);
    liftSpark.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 0);

    liftEncoder = liftSpark.getEncoder();

    SmartDashboard.putNumber("Lift Position", liftEncoder.getPosition());
  }

  public void collect(double power) {
    // Apply voltage to the motor
    rightIntake.set(ControlMode.PercentOutput, power);
    leftIntake.set(ControlMode.PercentOutput, -power);
  }

  public void stopCollector() {
    // Apply no voltage to the motor
    rightIntake.set(ControlMode.PercentOutput, 0);
    rightIntake.set(ControlMode.PercentOutput, 0);
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
    
  }
}
