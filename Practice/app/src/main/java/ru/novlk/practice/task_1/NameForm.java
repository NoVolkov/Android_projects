package ru.novlk.practice.task_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import ru.novlk.practice.R;

public class NameForm extends AppCompatActivity {
    String name;
    String age;
    String gender;
    TextView textViewName;
    TextView textViewAge;
    RadioGroup radios;
    RadioButton rbtnW;
    RadioButton rbtnM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_form);
        textViewName=findViewById(R.id.edtxt_nameUser);
        textViewAge=findViewById(R.id.edtxt_ageUser);
        radios=findViewById(R.id.radios);
        rbtnM=findViewById(R.id.M);
        rbtnW=findViewById(R.id.W);
    }
    public void goToNextPage(View view){
        name=textViewName.getText().toString();
        age=textViewAge.getText().toString();
        if(!name.matches("[a-zA-Z[а-яА-Я]]+")){
            textViewName.requestFocus();
            textViewName.setError("Поле имени некоректно или пустое.");
            return;
        }
        if(age!=null && !age.matches("[0-9]+")){
            textViewAge.requestFocus();
            textViewAge.setError("Поле возраста должно быть целочисленным.");//
            return;
        }

//        if(radios.getCheckedRadioButtonId()==-1){
//            rbtnW.requestFocus();
//            rbtnW.setError("Укажите пол.");
//            return;
//        }
        switch (radios.getCheckedRadioButtonId()){
            case R.id.M: gender="M";
            case R.id.W: gender="W";
        }
        Intent intent=new Intent(this, Starter.class);
        intent.putExtra("name",name);
        intent.putExtra("age",age);
        intent.putExtra("gender",gender);
        startActivity(intent);

    }
}