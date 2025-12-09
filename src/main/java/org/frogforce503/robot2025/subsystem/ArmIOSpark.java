package org.frogforce503.robot2025.subsystem;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

import org.littletonrobotics.junction.AutoLog;

import com.revrobotics.RelativeEncoder;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkMaxConfig;
//import com.revrobotics.SparkMaxPIDController;





public class ArmIOSpark implements ArmIO{

    SparkMax motor1;
    SparkMaxConfig config1;
    RelativeEncoder encoder1;

    public ArmIOSpark(int CANId, double kP, double kI, double kD){

        SparkMax motor1 = new SparkMax(CANId, MotorType.kBrushless);

        SparkClosedLoopController pid = motor1.getClosedLoopController();

        SparkMaxConfig config1 = new SparkMaxConfig();

        RelativeEncoder encoder1 = motor1.getEncoder();

        config1.closedLoop
            .p(kP)
            .i(kI)
            .d(kD);

        
        motor1.configure(config1, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

    }
    

    public void updateInputs(ArmIOData inputs){}

    public double getPosition(){

        return encoder1.getPosition();
    }

    public double getVelocity(){
        return encoder1.getVelocity();
    }

    public void setPID(double kP, double kI, double kD){

        config1.closedLoop
            .p(kP)
            .i(kI)
            .d(kD);

        
        motor1.configure(config1, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setOperatingMode(){}

    public void stop(){
        encoder1.setPosition(0.0);
        //config1.set(0.0);
    }

    public void reset(){

        motor1.configure(config1, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }
}

