// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Vision;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.vision.BallVision;

public class AimAtBall extends CommandBase {
  /** Creates a new AimAtBall. */

  private DriveTrain _driveTrain;
  private Joystick _controller;
  private Joystick _leftController;
  private BallVision _ballVision;
  private double _targetYaw;
  public static final double DEADZONE = 0.12;

  final double ANGULAR_P = 0.1;
  final double ANGULAR_D = 0.0;
  PIDController turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);

  public AimAtBall(DriveTrain driveTrain, BallVision ballVision, Joystick controller, Joystick leftController) {
    // Use addRequirements() here to declare subsystem dependencies.

    this._driveTrain = driveTrain;
    this._ballVision = ballVision;
    this._controller = controller;
    this._leftController = leftController;

    addRequirements(driveTrain);

    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (this._controller.getRawButton(2)) {
      if (this._ballVision.hasTargets()) {
      // get yaw from camera
      this._targetYaw = this._ballVision.getYawVal();
      // trigger drive to turn to vision

        double rotationSpeed = turnController.calculate(this._targetYaw, -1);

      this._driveTrain.drive(this._controller.getRawAxis(1), rotationSpeed);
     // else below is when no target is in place
    } 
  }
    else {
      this._driveTrain.drive(this._controller.getRawAxis(1), -this._leftController.getRawAxis(0));
    
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
