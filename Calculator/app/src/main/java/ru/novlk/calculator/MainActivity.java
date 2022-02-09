package ru.novlk.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int clicks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*ConstraintLayout layout=new ConstraintLayout(this);

        TextView textView=new TextView(this);
        textView.setText("Hello there");
        textView.setTextSize(26);

        ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(
          ConstraintLayout.LayoutParams.WRAP_CONTENT,
          200//ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.rightToRight=ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop=ConstraintLayout.LayoutParams.PARENT_ID;

        textView.setLayoutParams(layoutParams);
        layout.addView(textView);
        setContentView(layout);*/
        setContentView(R.layout.activity_main);

        /*View plusBtnView=findViewById(R.id.plus_button);
        View minusBtnView=findViewById(R.id.minus_button);
        TextView clicksText=findViewById(R.id.clicksText);

        Button plusBtn=plusBtnView.findViewById(R.id.clickBtn);
        Button minusBtn=minusBtnView.findViewById(R.id.clickBtn);

        plusBtn.setText("+");
        minusBtn.setText("-");

        plusBtn.setOnClickListener(v->{
            clicks++;
            clicksText.setText(clicks+"");
        });
        minusBtn.setOnClickListener(v->{
            clicks--;
            clicksText.setText(clicks+"");
        });*/
        /*EditText editText = findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(s);
            }
        });*/
    }

}