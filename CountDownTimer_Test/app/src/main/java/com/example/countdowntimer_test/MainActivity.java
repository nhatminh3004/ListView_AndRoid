package com.example.countdowntimer_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener  {
    private static TextView countdownTimerText;
    private static EditText minutes;
    private static Button startTimer, resetTimer;
    private static CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdownTimerText = (TextView) findViewById(R.id.countdownText);
        minutes = (EditText) findViewById(R.id.enterMinutes);
        startTimer=(Button) findViewById(R.id.startTimer);
        resetTimer= (Button) findViewById(R.id.resetTimer);

        //hàm setListeners()
        setListeners();
        //
    }
    //hàm setListeners
    public void setListeners(){
        startTimer.setOnClickListener(this);
        resetTimer.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.startTimer:
                //nếu countDownTimer is null thì xử lý như sau :
                if(countDownTimer == null){
                    String getMinutes = minutes.getText().toString();
                    if(!getMinutes.equals("")&&getMinutes.length()>0){
                        int miligiay = Integer.parseInt(getMinutes) *60 *1000;//chuyen phut thanh miliiay
                        startTimer(miligiay);//bắt đầu đếm ngược
                        startTimer.setText(getString(R.string.stop_timer));//change Text

                    } else
                        Toast.makeText(MainActivity.this, "Vui lòng nhập số phút", Toast.LENGTH_SHORT).show();
                }
                else {
                    stopCountdown();
                    startTimer.setText(getString(R.string.start_timer));
                }
                break;
            case R.id.resetTimer:
                stopCountdown();
                startTimer.setText(getString(R.string.start_timer));//Change text to Start Timer
                countdownTimerText.setText(getString(R.string.timer));//Change Timer text
                break;

        }
    }
    //hàm starCountDownTimer
    private void startTimer(int miligiay){
        countDownTimer = new CountDownTimer(miligiay,5000) {
            @Override
            public void onTick(long l) {
                long miligiay = l;
                //chuyen miligiay thành 1 tiếng , phút , giây
                String dinhdang= String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(miligiay),TimeUnit.MILLISECONDS.toMinutes(miligiay) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(miligiay)), TimeUnit.MILLISECONDS.toSeconds(miligiay) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(miligiay)));
                    countdownTimerText.setText(dinhdang);
                Toast.makeText(MainActivity.this, "Love Die", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFinish() {
                countdownTimerText.setText("TIME'S UP!!"); //On finish change timer text
                countDownTimer = null;//set CountDownTimer to null
                startTimer.setText(getString(R.string.start_timer));//Change button text
            }
        }.start();
    }
    //hàm stoup CountDown
    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}