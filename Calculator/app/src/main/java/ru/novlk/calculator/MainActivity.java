package ru.novlk.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView resField;
    private Deque<String> fieldOut=new ArrayDeque<>();
    private List<Button> specFunction=new ArrayList<>();
    private boolean isCounted=false;
    private String expression;
    private Deque<BigDecimal> stackOperands=new ArrayDeque<>();
    private Deque<String> stackOperations=new ArrayDeque<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resField=findViewById(R.id.txt_EntryField);
        specFunction.add(findViewById(R.id.sqrt));
        specFunction.add(findViewById(R.id.partOfAWhole));
        specFunction.add(findViewById(R.id.plusMinus));
        activateSpecFunctions();
    }

    public void btnCalc(View view){
        if(TextUtils.isEmpty(resField.getText()))return;
        expression=resField.getText().toString().replace(" ","");
        String accNumber="";
        boolean replacementFirstOperand=true;
        if(expression.substring(0,1).equals("-")){
            expression="0"+expression;
        }
        for(String s: expression.split("")){
            if(s.matches("[0-9[.]]")){
                accNumber+=s;
            }else{
                if(!accNumber.isEmpty()){
                    stackOperands.push(new BigDecimal(accNumber));
                    accNumber="";
                }
                if(s.matches("[+-[/*]]")){
                    if(replacementFirstOperand || getOperationPriority(s)> getOperationPriority(stackOperations.getLast())){//или идти от того, что пустая очередь возвращает null?
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

        }
        if(!accNumber.isEmpty()){
            stackOperands.push(new BigDecimal(accNumber));
        }
        while (!stackOperations.isEmpty()){
            BigDecimal b2=stackOperands.pop();
            BigDecimal b1=stackOperands.pop();
            String operation=stackOperations.pop();
            stackOperands.push(executeOperation(operation,b1,b2));
        }

        resField.setText(stackOperands.pop().toString());
        isCounted=true;
        activateSpecFunctions();
    }

    public void btnOperandOperation(View view){
        Button button=(Button) view;
        if(!TextUtils.isEmpty(resField.getText())){
            String stringInput=resField.getText().toString();
            String lastSymbol=stringInput.substring(stringInput.length()-1);
            if(lastSymbol.matches("[+-[/*]]") && button.getText().toString().matches("[+-[/*]]"))return;
        }else{
            if(button.getText().toString().matches("[+-[/*]]")){
                resField.append("0");
            }
        }

        resField.append(button.getText());
        isCounted=false;
        activateSpecFunctions();
    }
    private int getOperationPriority(String s){
        switch (s){
            case "+":
            case "-": return 1;
            case "*":
            case "/": return 2;
            //case "\u221A": return 3;
            default: return 0;
        }
    }
    private BigDecimal executeOperation(String operation, BigDecimal d1, BigDecimal d2){
        switch (operation){
            case "*": return d1.multiply(d2);
            case "/": return d1.divide(d2);
            case "+": return d1.add(d2);
            case "-": return d1.subtract(d2);
           // case "\u221A": return new BigDecimal(Math.sqrt(d1.doubleValue()));
            default: return new BigDecimal(0);
        }
    }
    public void btnClearFieldOut(View view){
        resField.setText("");
        isCounted=false;
        activateSpecFunctions();
        stackOperands.clear();
    }
    public void btnSpecFunction(View view){
        Button button=(Button)view;
        String res=resField.getText().toString();
        if(isCounted){
            switch(button.getText().toString()){
                case "\u221A": res=String.valueOf(Math.sqrt(Double.parseDouble(res)));break;
                case "1/x": res=executeOperation("/",new BigDecimal(1),new BigDecimal(res)).toString();break;
                case "+/-": res=executeOperation("*",new BigDecimal(-1),new BigDecimal(res)).toString();break;
                default:break;
            }
            resField.setText(res);
        }
    }
    public void activateSpecFunctions(){
        if(isCounted){
            for(Button b:specFunction){
                b.setActivated(true);
                b.setBackgroundColor(getResources().getColor(R.color.btn_active));
            }
        }else{
            for(Button b:specFunction){
                b.setActivated(false);
                b.setBackgroundColor(getResources().getColor(R.color.btn_inactive));
            }
        }
    }
}