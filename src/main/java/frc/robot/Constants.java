// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
    public static final int leftRearMotorPort = 1;
    public static final int leftFrontMotorPort = 2;
    public static final int rightRearMotorPort = 3;    
    public static final int rightFrontMotorPort = 4;
    
    }

public static final class OIConstants {
    public static final int xboxControllerPort = 1;
    public static final int rightJoystickPort = 2;
    public static final int leftJoystickPort = 1;
    public static final int psControllerPort = 0;
    }

public static final class VictorConstants {
    public static final int rightIndexerPort = 21;
    public static final int leftIndexerPort = 24;
    public static final int rightIntakePort = 23;
    public static final int leftIntakePort = 22;
}
public static final class SparkConstants {
    public static final int climbPort = 0;
    public static final int liftPort = 5;
}
}