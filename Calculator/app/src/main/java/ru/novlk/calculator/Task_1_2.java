package ru.novlk.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Task_1_2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1_2);
    }
    public void goToSecondPage(View view){
        Intent intent=new Intent(this, Task_1_1.class);
        startActivity(intent);
    }
}
