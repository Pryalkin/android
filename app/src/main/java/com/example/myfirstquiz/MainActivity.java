package com.example.myfirstquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name = findViewById(R.id.name);
        Button buttonCreate = findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Use use = new Use();
                String nm = name.getText().toString();
                Pattern pat = Pattern.compile("[a-z]+");
                Matcher mat = pat.matcher(nm);
                if (mat.find()) {
                    use.setName(name.getText().toString());
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Введите имя!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

    public void startTheGame(View view){
        Intent intent = new Intent(this, QuestionsActivity.class);
        startActivity(intent);
    }
}