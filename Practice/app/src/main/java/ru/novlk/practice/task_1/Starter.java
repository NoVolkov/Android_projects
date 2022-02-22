package ru.novlk.practice.task_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

import ru.novlk.practice.R;

public class Starter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1_1);

    }
    public void editBackground(View view){
        Random r=new Random();
        ConstraintLayout layout=findViewById(R.id.main);
        layout.setBackgroundColor(
                Color.argb(
                        r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt()
                )
        );
    }
    public void editText(View view){
        TextView textView=findViewById(R.id.txt_1);
        EditText editText=findViewById(R.id.edtxt_1);
        textView.setText(editText.getText());
    }
    public void goToSecondPage(View view){
        Intent intent=new Intent(this, SecondPage.class);
        startActivity(intent);
    }
}
