// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_joystick;

  // Each line defines the spark connected to each motor
  private final CANSparkMax m_leftMotor1 = new CANSparkMax(6, MotorType.kBrushless);
  private final CANSparkMax m_leftMotor2 = new CANSparkMax(8, MotorType.kBrushless);
  private final CANSparkMax m_leftMotor3 = new CANSparkMax(10, MotorType.kBrushless);

  private final CANSparkMax m_rightMotor1 = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax m_rightMotor2 = new CANSparkMax(7, MotorType.kBrushless);
  private final CANSparkMax m_rightMotor3 = new CANSparkMax(20, MotorType.kBrushless);

  // This groups the sparks together in left and right groups because all left and right wheels move at the same speed
  private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftMotor1, m_leftMotor2, m_leftMotor3);
  private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightMotor1, m_rightMotor2, m_rightMotor3);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightGroup.setInverted(true);

    m_myRobot = new DifferentialDrive(m_leftGroup, m_rightGroup);
    m_joystick = new Joystick(0);
  }

  @Override
  public void teleopPeriodic() {
    // Speed depends on the y axis of the joystick and turning depends on the rotation of the joystick
    m_myRobot.arcadeDrive(-m_joystick.getY(), m_joystick.getTwist() / 1.5, false);
  }
}
