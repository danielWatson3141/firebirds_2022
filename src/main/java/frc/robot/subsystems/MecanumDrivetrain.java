package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class MecanumDrivetrain extends SubsystemBase {

    public final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

    //public double gyroAngle = m_gyro.getAngle();

    private SlewRateLimiter rotationLimiter;
    private SlewRateLimiter throttleLimiterX;
    private SlewRateLimiter throttleLimiterY;

    // private double locationX = 0.2794;
    // private double locationY = 0.3048;

    Joystick m_stick;

    Spark m_frontLeft;
    Spark m_rearLeft;
    Spark m_frontRight;
    Spark m_rearRight;

    private double rotationRate = 0.3;
    private double throttleRate = 0.2;


    MecanumDrive m_robotDrive;


  /*  ChassisSpeeds chassisSpeed = new ChassisSpeeds(m_stick.getY(), m_stick.getX(), 0);

    Translation2d m_frontLeftLocation = new Translation2d(locationX, locationY);
    Translation2d m_frontRightLocation = new Translation2d(locationX, -locationY);
    Translation2d m_rearLeftLocation = new Translation2d(-locationX, locationY);
    Translation2d m_rearRightLocation = new Translation2d(-locationX, -locationY);

    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_rearLeftLocation, m_rearRightLocation);


    ChassisSpeeds speeds = new ChassisSpeeds(1.0, 3.0, 1.5);
    MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(speeds);

    double frontLeft = wheelSpeeds.frontLeftMetersPerSecond;
    double frontRight = wheelSpeeds.frontRightMetersPerSecond;
    double backLeft = wheelSpeeds.rearLeftMetersPerSecond;
    double backRight = wheelSpeeds.rearRightMetersPerSecond;  */

    
    public MecanumDrivetrain(Joystick input_stick){
      m_frontLeft = new Spark(4);
      m_rearLeft = new Spark(6);
      m_frontRight = new Spark(3);
      m_rearRight = new Spark(5);

      m_robotDrive = new MecanumDrive(m_frontLeft::set, m_rearLeft::set, m_frontRight::set, m_rearRight::set);

      m_stick = input_stick; 

      m_frontRight.setInverted(true);
      m_rearRight.setInverted(true);

      rotationLimiter = new SlewRateLimiter(rotationRate);
      throttleLimiterX = new SlewRateLimiter(throttleRate);
      throttleLimiterY = new SlewRateLimiter(throttleRate);
    }

  //multipliers for values
  private double speedCap = .6;
  private double rotateCap = .6; 

  public void drive() {
    m_robotDrive.driveCartesian(
      throttleLimiterX.calculate(m_stick.getX() * speedCap),
      throttleLimiterY.calculate(m_stick.getY() * speedCap),
      rotationLimiter.calculate(m_stick.getZ() * rotateCap)
      );
   
    SmartDashboard.putNumber("stickX", m_stick.getX());
    SmartDashboard.putNumber("stickY", m_stick.getY());
    SmartDashboard.putNumber("stickZ", m_stick.getZ());
  }
}