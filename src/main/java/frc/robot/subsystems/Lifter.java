package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Lifter extends SubsystemBase{

    private DoubleSolenoid lifterArm;

    public Lifter() {
        lifterArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    public void toggleLifter(){
        lifterArm.toggle();

        SmartDashboard.putBoolean("armUp", lifterArm.get()==Value.kForward);

    }

    
}
