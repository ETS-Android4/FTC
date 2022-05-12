package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOpCodeMASTER (Blocks to Java)")
@Disabled
public class TeleOpCodeMASTER extends LinearOpMode {

  private DcMotor FL;
  private DcMotor FR;
  private DcMotor BL;
  private DcMotor BR;
  private DcMotor imotorAsDcMotor;
  private DcMotor pmotorAsDcMotor;
  private DcMotor lmotorAsDcMotor;
  private Servo launcherproperty;
  private Servo elbow;
  private Servo grip;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double lpower;
    double ServoPosition;
    double ServoSpeed;
    float MoveForward;
    float LateralMovement;
    float RightJoystickX;

    FL = hardwareMap.get(DcMotor.class, "FL");
    FR = hardwareMap.get(DcMotor.class, "FR");
    BL = hardwareMap.get(DcMotor.class, "BL");
    BR = hardwareMap.get(DcMotor.class, "BR");
    imotorAsDcMotor = hardwareMap.get(DcMotor.class, "imotorAsDcMotor");
    pmotorAsDcMotor = hardwareMap.get(DcMotor.class, "pmotorAsDcMotor");
    lmotorAsDcMotor = hardwareMap.get(DcMotor.class, "lmotorAsDcMotor");
    launcherproperty = hardwareMap.get(Servo.class, "launcherproperty");
    elbow = hardwareMap.get(Servo.class, "elbow");
    grip = hardwareMap.get(Servo.class, "grip");

    // Put initialization blocks here.
    FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    imotorAsDcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    imotorAsDcMotor.setPower(0.8);
    pmotorAsDcMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    pmotorAsDcMotor.setPower(0.95);
    lmotorAsDcMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    lpower = 0.8;
    lmotorAsDcMotor.setPower(lpower);
    launcherproperty.setPosition(0.5);
    ServoPosition = 0.5;
    ServoSpeed = 0.01;
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        MoveForward = gamepad1.left_stick_y;
        FR.setPower(-MoveForward);
        BR.setPower(-MoveForward);
        BL.setPower(MoveForward);
        FL.setPower(MoveForward);
        telemetry.addData("LeftStickY", MoveForward);
        telemetry.update();
        LateralMovement = gamepad1.left_stick_x;
        BL.setPower(gamepad1.left_stick_x);
        FR.setPower(-gamepad1.left_stick_x);
        FL.setPower(-gamepad1.left_stick_x);
        BR.setPower(gamepad1.left_stick_x);
        telemetry.addData("LeftStickX", LateralMovement);
        telemetry.update();
        RightJoystickX = gamepad1.right_stick_x;
        if (RightJoystickX < Math.abs(0)) {
          FR.setPower(-RightJoystickX);
          FL.setPower(-RightJoystickX);
          BR.setPower(-RightJoystickX);
          BL.setPower(-RightJoystickX);
        } else if (RightJoystickX > Math.abs(0)) {
          FR.setPower(-RightJoystickX);
          FL.setPower(-RightJoystickX);
          BR.setPower(-RightJoystickX);
          BL.setPower(-RightJoystickX);
        } else {
        }
        if (gamepad1.a) {
          ServoPosition += ServoSpeed;
          ServoPosition = Math.min(Math.max(ServoPosition, 0), 1);
          elbow.setPosition(ServoPosition);
        }
        if (gamepad1.y) {
          ServoPosition += -ServoSpeed;
          ServoPosition = Math.min(Math.max(ServoPosition, 0), 1);
          elbow.setPosition(ServoPosition);
        }
        if (gamepad1.dpad_up) {
          lpower = lmotorAsDcMotor.getPower();
          lpower += 0.05;
          telemetry.addData("Launcher increase", lpower);
          lmotorAsDcMotor.setPower(lpower);
        }
        if (gamepad1.dpad_down) {
          lpower = lmotorAsDcMotor.getPower();
          lpower += -0.05;
          telemetry.addData("Launcher decrease", lpower);
          lmotorAsDcMotor.setPower(lpower);
        }
        telemetry.addData("Servo", ServoPosition);
        telemetry.addData("Launcher", lpower);
        telemetry.update();
        if (gamepad1.x) {
          grip.setPosition(0);
        }
        if (gamepad1.b) {
          grip.setPosition(0.8);
        }
        if (gamepad1.right_bumper) {
          launcherproperty.setPosition(0.3);
          sleep(200);
          launcherproperty.setPosition(0.5);
          sleep(200);
        }
        if (gamepad1.left_bumper == true) {
          pmotorAsDcMotor.setPower(-0.8);
          imotorAsDcMotor.setPower(-0.8);
        } else if (gamepad1.left_bumper == false) {
          pmotorAsDcMotor.setPower(0.8);
          imotorAsDcMotor.setPower(0.8);
        } else {
        }
      }
    }
  }
}
