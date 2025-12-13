package org.frogforce503.robot2025.subsystem;

import org.littletonrobotics.junction.AutoLog;

//import edu.wpi.first.wpilibj2.command.SubsystemBase;

public interface ArmIO {
    
    @AutoLog
    class ArmIOInputs{
 

        ArmIOData data = new ArmIOData(0,0,false,0,0,0);

    }
    record ArmIOData(
        double positionRadians, 
        double velocityRadians, 
        boolean isMOtorConnected, 
        double temp, 
        double voltage, 
        double currnt){}




    public void updateInputs(ArmIOInputs inputs);

    public double getPosition();

    public void setPosition(double setpoint);

    public void setVoltage(double voltage);

    public double getVelocity();

    public void setPID(double kP,double kI,double kD, int slot, double minVolts, double maxVolts);

    public void setOperatingMode(boolean isBreak);//It is basically idle mode

    public double getMotorTemp();

    public double getVoltage();

    public double getCurrent();

    public boolean MotorConnected();

    public void stop();

    public void reset();


}
