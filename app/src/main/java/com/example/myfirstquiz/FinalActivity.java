package com.example.myfirstquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myfirstquiz.db.MyDBManager;

public class FinalActivity extends AppCompatActivity {

    private MyDBManager myDBManager;
    private Use use;
    private TextView result2;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        use = new Use();
        TextView result = findViewById(R.id.result);
        result.setText(use.getName() + " Ваш результат " + use.getScores() + " баллов");
        result2 = findViewById(R.id.result2);
        myDBManager = new MyDBManager(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        myDBManager.openDB();
    }

    public void statistics(View view){
        if (!flag) {
            flag = true;
            myDBManager.insertToDB(use.getName(), use.getScores());
            result2.setText("");
            for (String stat : myDBManager.getFromDB()) {
                result2.append(stat);
                result2.append("\n");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDBManager.closeDB();
    }
}