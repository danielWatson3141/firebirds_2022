// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    //Motor
    private TalonSRX intakeMotor;

    //Servo Hinge
    private Servo hinge;

  /** Creates a new Intake. */
  public Intake() {}

  //activates/deactivates the spinner based on enabled
  public void setSpinnerEnabled(boolean enabled){

  }

  //drops the spinner
  //assumes spinner is up
  //does nothing otherwise
  public void dropSpinner(){

  }
  
  
  //raises the spinner
  //assumes spinner is down
  //does nothing otherwise
  public void raiseSpinner(){
      
  }
  
  @Override
  public void periodic() {
    // This function will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
