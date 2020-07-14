package com.example.thuctapcoso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.thuctapcoso.R.string.home;

public class WelcomeActivity extends AppCompatActivity {
    TextView txtCauHoi,txtName,txtScore;
    Button btnDung,btnSai;
    int score = 0;
    int SoA,SoB,KQ,SoC,checkKQ;
    Random random;
    ConstraintLayout constraints;
    String name;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txtCauHoi = findViewById(R.id.txtCauHoi);
        txtName = findViewById(R.id.txtName);
        txtScore = findViewById(R.id.txtScore);
        btnDung = findViewById(R.id.btnDung);
        btnSai = findViewById(R.id.btnSai);
        constraints = findViewById(R.id.constrainLayout);
        progressBar = findViewById(R.id.progressBar);

        randomCauHoi(constraints);
        Intent intent =this.getIntent();

        name = intent.getStringExtra("Name");


        txtName.setText("Your Name: " + name);

        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SoA+SoB==KQ){
                    score++;
                    timeOver();
                    randomCauHoi(constraints);
                }else{
                    showGameover();
                }
            }
        });

        btnSai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SoA+SoB==KQ){
                    showGameover();
                }else{
                    score++;
                    timeOver();
                    randomCauHoi(constraints);

                }

            }
        });
    }

    private void timeAnswer() {
        if (score >= 0 && score < 10){
            countDownTimer = new CountDownTimer(5000,50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int startTime = progressBar.getProgress();
                    if (startTime >= progressBar.getMax()){
                        startTime = 0;
                    }
                    progressBar.setProgress(startTime + 50);
                }

                @Override
                public void onFinish() {
                    showGameover();
                }
            };
            countDownTimer.start();
        }

        if (score >= 10){
            if (score == 10){
                Toast.makeText(this, "Level up: MEDIUM", Toast.LENGTH_SHORT).show();
            }
            countDownTimer = new CountDownTimer(4000,50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int startTime = progressBar.getProgress();
                    if (startTime >= progressBar.getMax()){
                        startTime = 0;
                    }
                    progressBar.setProgress(startTime + 50);
                }

                @Override
                public void onFinish() {
                    showGameover();
                }
            };
            countDownTimer.start();
        }
        if (score >= 20){
            if (score == 20){
                Toast.makeText(this, "Level up: HARD", Toast.LENGTH_SHORT).show();
            }
            countDownTimer = new CountDownTimer(3000,50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int startTime = progressBar.getProgress();
                    if (startTime >= progressBar.getMax()){
                        startTime = 0;
                    }
                    progressBar.setProgress(startTime + 50);
                }

                @Override
                public void onFinish() {
                    showGameover();
                }
            };
            countDownTimer.start();
        }
        if (score >= 40){
            if (score == 40){
                Toast.makeText(this, "Level up: VERY HARD", Toast.LENGTH_SHORT).show();
            }
            countDownTimer = new CountDownTimer(2500,50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int startTime = progressBar.getProgress();
                    if (startTime >= progressBar.getMax()){
                        startTime = 0;
                    }
                    progressBar.setProgress(startTime + 50);
                }

                @Override
                public void onFinish() {
                    showGameover();
                }
            };
            countDownTimer.start();
        }
        if (score >= 50){
            if (score == 50){
                Toast.makeText(this, "Level up: HARDEST(HELL)", Toast.LENGTH_SHORT).show();
            }
            countDownTimer = new CountDownTimer(1500,50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int startTime = progressBar.getProgress();
                    if (startTime >= progressBar.getMax()){
                        startTime = 0;
                    }
                    progressBar.setProgress(startTime + 50);
                }

                @Override
                public void onFinish() {
                    showGameover();
                }
            };
            countDownTimer.start();
        }
    }
    private void timeOver(){
        progressBar.setProgress(0);
        countDownTimer.cancel();
    }

    private void showGameover() {
        timeOver();
        new AlertDialog.Builder(this)
                .setTitle("GameOver")
                .setMessage(txtName.getText().toString() + "\n Your Score: " + score)

                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("yourname",name);
                        bundle.putInt("yourscore",score);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                })
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        score = 0;
                        timeOver();
                        randomCauHoi(constraints);
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        score = 0;
                    }
                })

                .show();

    }

    private void randomCauHoi(ConstraintLayout constraints) {
        random = new Random();
        SoA = random.nextInt(21);
        SoB = random.nextInt(21);
        checkKQ = random.nextInt(21);
        if (score >= 0){
            if (score == 0){
                Toast.makeText(this, "Level: EASY", Toast.LENGTH_SHORT).show();
            }
            if (checkKQ % 2 == 0){
                KQ = SoA + SoB;
            }else{
                KQ = random.nextInt(21);
            }
            txtCauHoi.setText(SoA+"+"+SoB+"="+KQ);
        }
        if (score >=50){
            if (score == 50){
                Toast.makeText(this, "Level up: HARD", Toast.LENGTH_SHORT).show();
            }
            if (checkKQ % 2 == 0){
                KQ = SoA + SoB + SoC;
            }else{
                KQ = random.nextInt(21);
            }
            txtCauHoi.setText(SoA+"+"+SoB+"+"+SoC+"="+KQ);
        }

        constraints.setBackgroundColor(Color.rgb(154,233,220));
        txtScore.setText("Score: "+ score);
        timeAnswer();

    }
}
