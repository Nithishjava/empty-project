package org.frogforce503.robot2025.subsystem;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public interface ArmIO {
    
    @AutoLog
    class ArmIOInputs{
 

        ArmIOData data = new ArmIOData(0,0,false,0,0,0);

    }
    record ArmIOData(double positionRadians, double velocityRadians, boolean isMOtorConnected, double temp, double voltage, double currnt){}




    public void updateInputs(ArmIOData inputs);

    public double getPositoin();

    public double getVelocity();

    public double setPID();

    public double setOperatingMode();

    public double stop();

    public double reset();


}
