package ru.novlk.practice.task_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.novlk.practice.R;

public class SecondPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1_2);
    }
    public void goToSecondPage(View view){
        Intent intent=new Intent(this, Starter.class);
        startActivity(intent);
    }
}
