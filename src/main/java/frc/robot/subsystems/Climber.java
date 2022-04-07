// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SparkConstants;

public class Climber extends SubsystemBase {
 public final static VictorSPX climb = new VictorSPX(SparkConstants.climbPort);

  public Climber() {
      
    }

  public void periodic() {
    

    

  }
}

  

