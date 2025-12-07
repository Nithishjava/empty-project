package org.frogforce503.robot2025.subsystem;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.SparkMaxPIDController;





public class ArmIOSpark implements ArmIO{

    ArmIO armIO = new ArmIO();

    SparkMax motor = new SparkMax(0, MotorType.kBrushless);

    SparkMaxPIDController pid;

    public void updateInputs(ArmIOData inputs){}

    public double getPositoin(){}

    public double getVelocity(){}

    public double setPID(){

        pid = motor.getPIDController();
        pid.setP(0.2342);
        pid.setI(0.12126);
        pid.setD(2.285);
        pid.setFF(8.876);
    }

    public double setOperatingMode(){}

    public double stop(){
        motor.set(0.0);
    }

    public double reset(){
        motor.resetEncoder();
    }
}

