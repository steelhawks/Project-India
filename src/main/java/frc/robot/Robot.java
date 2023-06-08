// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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

  private final WPI_TalonSRX m_leftMotor1 = new WPI_TalonSRX(0);
  private final WPI_TalonSRX m_leftMotor2 = new WPI_TalonSRX(1);
  private final WPI_TalonSRX m_leftMotor3 = new WPI_TalonSRX(2);

  private final WPI_TalonSRX m_rightMotor1 = new WPI_TalonSRX(3);
  private final WPI_TalonSRX m_rightMotor2 = new WPI_TalonSRX(4);
  private final WPI_TalonSRX m_rightMotor3 = new WPI_TalonSRX(5);

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
    m_myRobot.arcadeDrive(-m_joystick.getY(), m_joystick.getTwist() / 1.5, false);
  }
}
