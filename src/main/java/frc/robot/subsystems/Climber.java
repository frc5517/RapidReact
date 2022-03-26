// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
 public final static VictorSPX climb = new VictorSPX(Constants.SparkConstants.climbPort);

  public Climber() {
      climb.setNeutralMode(NeutralMode.Brake);
    }

  public void periodic() {
    

    

  }
}

  

