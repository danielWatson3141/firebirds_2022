// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  DoubleSolenoid piston;

  boolean spinner_enabled = false;

  // Motor
  private VictorSPX intakeMotor;

  /** Creates a new Intake. */
  public Intake() {
    // motor that extends arm
    intakeMotor = new VictorSPX(9);
    intakeMotor.setInverted(true);

    piston=new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  }

  private static final double SPINNER_SPEED = 1;

  // activates/deactivates the spinner based on enabled
  public void setSpinnerEnabled(boolean enabled) {
    if (enabled) {
      intakeMotor.set(ControlMode.PercentOutput, SPINNER_SPEED);
      spinner_enabled = true;
    } else {
      intakeMotor.set(ControlMode.PercentOutput, 0);
      spinner_enabled = false;
    }
  }

  // drops the spinner
  // assumes spinner is up
  // does nothing otherwise
  public void dropSpinner() {
    //TODO: Implement this function.
    //Should activate a solenoid
    //Like in Cannon::setPegToggle

    piston.set(DoubleSolenoid.Value.kForward);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("setSpinnerEnabled", spinner_enabled);
    // This function will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
