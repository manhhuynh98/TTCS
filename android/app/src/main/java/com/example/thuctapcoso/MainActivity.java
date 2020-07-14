package com.example.thuctapcoso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btnPlay,btnHS;
    EditText edtName;

    ListView listView;
    ArrayList<HighScore> arr;
    HighScoreAdapter adapter;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        dataBase = new DataBase(this);

        Bundle bundle = getIntent().getExtras();
        edtName.setText(bundle.getString("yourname"));

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                if (name.isEmpty()==true){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("Name", name);
                    startActivity(intent);
                    btnHS.setEnabled(true);
                }
            }
        });
        if (edtName.getText().toString().isEmpty()==true){
            btnHS.setEnabled(false);
        }
        else {
            edtName.selectAll();
        }
        highScore();

    }

    private void highScore() {
        btnHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                HighScore highScore = new HighScore();
                highScore.Ten = bundle.getString("yourname");
                highScore.Diem =bundle.getInt("yourscore");
                dataBase.insertData(highScore);
                loadData();
            }
        });

    }

    private void loadData() {
        arr = dataBase.showData();
        adapter = new HighScoreAdapter(this,R.layout.item,arr);
        listView.setAdapter(adapter);
    }

    private void anhXa() {
        btnPlay = findViewById(R.id.btnPlay);
        btnHS = findViewById(R.id.btnHighScore);
        edtName = findViewById(R.id.edtName);

        listView = findViewById(R.id.lvHighScore);
    }

}
