package org.frogforce503.robot2025.subsystem;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.ControlType;

//import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

//import org.littletonrobotics.junction.AutoLog;

import com.revrobotics.RelativeEncoder;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkMaxConfig;
//import com.revrobotics.SparkMaxPIDController;





public class ArmIOSpark implements ArmIO{

    SparkMax motor1;
    SparkMaxConfig config1;
    // RelativeEncoder encoder1;
    SparkAbsoluteEncoder encoder1;
    SparkClosedLoopController pid;

    public ArmIOSpark(int CANId, double kP, double kI, double kD){
        //Usually stores hardware values in a seperate file

        motor1 = new SparkMax(CANId, MotorType.kBrushless);

        pid = motor1.getClosedLoopController();

        config1 = new SparkMaxConfig();

        encoder1 = motor1.getAbsoluteEncoder();
        config1.absoluteEncoder.zeroOffset(0.0);//Added this after the meeting discussion with ethan

        config1.closedLoop
            .p(kP)
            .i(kI)
            .d(kD);
        
        config1.idleMode(IdleMode.kBrake);

        
        motor1.configure(config1, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }
    

    public void updateInputs(ArmIOInputs inputs){

        // inputs.temp() = motor1.getMotorTemperature();//1st method.
        // inputs.voltage = motor1.getBusVoltage();
        
        // inputs.positionRadians = encoder1.getPosition();

        inputs.data = new ArmIOData(
            encoder1.getPosition(), 
            encoder1.getVelocity(), 
            MotorConnected(), 
            getMotorTemp(), 
            getVoltage(), 
            getCurrent());
        
        
        //what is current supposed to be?

    }//Needs to be done
    @Override
    public double getPosition(){

        return encoder1.getPosition();
    }

    @Override
    public double getVelocity(){
        return encoder1.getVelocity();
    }

    @Override
    public double getMotorTemp(){

        return motor1.getMotorTemperature();
    }

    @Override
    public double getVoltage(){

        return motor1.getBusVoltage();
    }

    @Override
    public double getCurrent(){

        return motor1.getOutputCurrent();
    }

    @Override
    public boolean MotorConnected(){

        return motor1.isConnected();//I tried searcing this up in the docs, but didn't show up.
    }

    @Override
    public void setPosition(double setpoint){
        motor1.getClosedLoopController().setReference(setpoint, ControlType.kPosition);
    }

    @Override
    public void setPID(double kP, double kI, double kD){

        config1.closedLoop
            .p(kP)
            .i(kI)
            .d(kD);

        
        motor1.configure(config1, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    @Override
    public void setOperatingMode(boolean isBreak){
        if (isBreak){

            config1.idleMode(IdleMode.kBrake);
        }else{
            config1.idleMode(IdleMode.kCoast);
        }
        
    }



    @Override
    public void stop(){
        motor1.close();//Is this right??
        //config1.set(0.0);
    }

    @Override
    public void reset(){

        motor1.configure(config1, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }



}

