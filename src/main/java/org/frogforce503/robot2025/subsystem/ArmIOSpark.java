package org.frogforce503.robot2025.subsystem;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

import org.littletonrobotics.junction.AutoLog;

import com.revrobotics.RelativeEncoder;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.SparkMaxPIDController;





public class ArmIOSpark implements ArmIO{

    public ArmIOSpark(int CANId, double kP, double kI, double kD){

        SparkMax motor1 = new SparkMax(CANId, MotorType.kBrushless);

        SparkClosedLoopController pid = motor1.getClosedLoopController();

        SparkMaxConfig config1 = new SparkMaxConfig();

        RelativeEncoder encoder1 = motor1.getEncoder();

        config1.closedLoop
            .p(kP)
            .i(kI)
            .d(kD);

    }
    

    public void updateInputs(ArmIOData inputs){}

    public double getPosition(){}

    public double getVelocity(){}

    public void setPID(){}

    public void setOperatingMode(){}

    public void stop(){
        motor1.set(0.0);
    }

    public void reset(){

        
    }
}

