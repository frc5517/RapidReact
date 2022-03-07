// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OIConstants;

public class joystickControls extends SubsystemBase {
  /** Creates a new joystickControls. */

  public static Joystick leftStick = new Joystick(OIConstants.leftJoystickPort);
  public static Joystick rightStick = new Joystick(OIConstants.rightJoystickPort);

  public joystickControls() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
