// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VictorConstants;

public class Indexer extends SubsystemBase {
public final static WPI_VictorSPX rightIndexer = new WPI_VictorSPX(VictorConstants.rightIndexerPort);
public final static WPI_VictorSPX leftIndexer = new WPI_VictorSPX(VictorConstants.leftIndexerPort);

  public Indexer() {
    // Set the motors to Brake mode
    rightIndexer.setNeutralMode(NeutralMode.Brake);
    leftIndexer.setNeutralMode(NeutralMode.Brake);
  }

  public void index(double power) {
    // Apply voltage to the motors
    rightIndexer.set(ControlMode.PercentOutput, power);
    leftIndexer.set(ControlMode.PercentOutput, power);
  }

  public void outdex(double power) {
    // Apply voltage to the motors
    rightIndexer.set(ControlMode.PercentOutput, -power);
    leftIndexer.set(ControlMode.PercentOutput, -power);
  }

  public void stop() {
    // Apply no voltage to the motor
    rightIndexer.set(ControlMode.PercentOutput, 0);
    leftIndexer.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {}
  
}
