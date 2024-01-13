package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.ejml.simple.SimpleMatrix;


public class MecanumDrivetrain extends SubsystemBase {

    public MecanumDrive driver;

    //private SlewRateLimiter steeringLimiter;
    //private SlewRateLimiter throttleLimiter;

    private double locationX = 0.2794;
    private double locationY = 0.3048;


    WPI_TalonSRX m_frontLeft = new WPI_TalonSRX(69);
    WPI_TalonSRX m_rearLeft = new WPI_TalonSRX(69);
    WPI_TalonSRX m_frontRight = new WPI_TalonSRX(69);
    WPI_TalonSRX m_rearRight = new WPI_TalonSRX(69);
    

    MecanumDrive m_robotDrive = new MecanumDrive(m_frontRight, m_frontLeft, m_rearLeft, m_rearRight);

    public Joystick m_stick = new Joystick(69); 

    private Joystick myJoystick;

    ChassisSpeeds chassisSpeed = new ChassisSpeeds(m_stick.getY(), m_stick.getX(), 0);

    Translation2d m_frontLeftLocation = new Translation2d(locationX, locationY);
    Translation2d m_frontRightLocation = new Translation2d(locationX, -locationY);
    Translation2d m_rearLeftLocation = new Translation2d(-locationX, locationY);
    Translation2d m_rearRightLocation = new Translation2d(-locationX, -locationY);

    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_rearLeftLocation, m_rearRightLocation);

    public MecanumDrivetrain(Joystick joystick){

    driver = new MecanumDrive(m_frontRight, m_rearLeft, m_frontLeft, m_rearLeft);

        //steeringLimiter = new SlewRateLimiter(2.5);
        //throttleLimiter = new SlewRateLimiter(2.0);

        myJoystick = joystick;
    }

    private boolean BRAKE_ON = true;
    private boolean BRAKE_OFF = !BRAKE_ON;
    boolean brakeState;
    
    public void brakeToggle() {
        brakeState = !brakeState;
    
        if (brakeState == BRAKE_OFF) {
          m_frontLeft.setNeutralMode(NeutralMode.Coast);
          m_rearLeft.setNeutralMode(NeutralMode.Coast);
          m_frontRight.setNeutralMode(NeutralMode.Coast);
          m_rearRight.setNeutralMode(NeutralMode.Coast);
        } else {
          m_frontLeft.setNeutralMode(NeutralMode.Brake);
          m_rearLeft.setNeutralMode(NeutralMode.Brake);
          m_frontRight.setNeutralMode(NeutralMode.Brake);
          m_rearRight.setNeutralMode(NeutralMode.Brake);
        }
      }

public void teleopPeriodic() {
    m_robotDrive.driveCartesian(-m_stick.getY(), m_stick.getX(), m_stick.getZ());
     
    }
    
}

