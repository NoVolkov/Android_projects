package ru.novlk.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

public class MainActivity extends AppCompatActivity {
    TextView resField;

    private boolean isCounted=false;
    private String expression;
    private Deque<BigDecimal> stackOperands=new ArrayDeque<>();
    private Deque<String> stackOperations=new ArrayDeque<>();
    private int c=0;
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
        resField=findViewById(R.id.txt_EntryField);


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
    public void test(View view){
        resField.setText(String.valueOf(c++));
    }
    public void calc(View view){
        expression=resField.getText().toString().replace(" ","");
        String accNumber="";
        boolean replacementFirstOperand=true;
        for(String s: expression.split("")){
            if(s.matches("[0-9[.]]")){
                accNumber+=s;
            }else{

                if(s.matches("[+-[/*]\u221A]")){
                    if(replacementFirstOperand || getOperandPriority(s)>=getOperandPriority(stackOperations.pop())){//или идти от того, что пустая очередь возвращает null?
                        replacementFirstOperand=false;
                    }else{
                        BigDecimal b2=stackOperands.pop();
                        BigDecimal b1=stackOperands.pop();
                        String operation=stackOperations.pop();
                        stackOperands.push(executeOperation(operation,b1,b2));
                    }
                    stackOperations.push(s);
                }
            }
            if(!accNumber.isEmpty()){
                stackOperands.push(new BigDecimal(accNumber));
                accNumber="";
            }
        }
        while (!stackOperations.isEmpty()){
            BigDecimal b2=stackOperands.pop();
            BigDecimal b1=stackOperands.pop();
            String operation=stackOperations.pop();
            stackOperands.push(executeOperation(operation,b1,b2));
        }

        resField.setText(stackOperands.pop().toString());
    }
    public String getOperand(View view){
        return "";
    }
    public String getOperation(View view){
        return "";
    }
    private int getOperandPriority(String s){
        switch (s){
            case "+":
            case "-": return 1;
            case "*":
            case "/": return 2;
            case "\u221A": return 3;
            default: return 0;
        }
    }
    private BigDecimal executeOperation(String operation, BigDecimal d1, BigDecimal d2){
        switch (operation){
            case "*": return d1.multiply(d2);
            case "/": return d1.divide(d2);
            case "+": return d1.add(d2);
            case "-": return d1.subtract(d2);
            case "\u221A": return new BigDecimal(Math.sqrt(d1.doubleValue()));
            default: return new BigDecimal(0);
        }
    }
}