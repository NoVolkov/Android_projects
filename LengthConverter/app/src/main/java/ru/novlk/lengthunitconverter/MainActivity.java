package ru.novlk.lengthunitconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    int[] inUnits;
//    Map<String,Double> unitsToMeters;

    Spinner inputList;
    Spinner outputList;
    EditText input;
    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        getInitResources();
        setListenerForInput();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_page,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.btn_changeLang){
            Intent intent=new Intent(this,LanguageActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private String getValueStringById(int id){
        return getResources().getString(id);
    }
    private List<String> getStringsLanguages(){
        List<String> res=new ArrayList<>();
        for(int i:inUnits){
            res.add(getValueStringById(i));
        }
        return res;
    }
//    private void setInitMapUnits(){
//        unitsToMeters=new HashMap<>();
//        unitsToMeters.put("meters",.1);
//        unitsToMeters.put("kilometers",.1000);
//        unitsToMeters.put("centimeters",.01);
//        unitsToMeters.put("feet",.3048);
//        unitsToMeters.put("miles",1609.344);
//        unitsToMeters.put("inches",.0254);
//    }
    private void getInitResources(){
        input=findViewById(R.id.edtxt_input);
        output=findViewById(R.id.txt_output);
        inputList=findViewById(R.id.sp_inputUnits);
        outputList=findViewById(R.id.sp_outputUnits);
        inUnits= new int[]{
                R.string.u_centimeters,
                R.string.u_meters,
                R.string.u_kilometers,
                R.string.u_inches,
                R.string.u_feet,
                R.string.u_miles};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, R.layout.spinner_item,getStringsLanguages());
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        inputList.setAdapter(adapter);
        outputList.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener=new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s=input.getText().toString();
                if(TextUtils.isEmpty(s))s="0";
                calc(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        inputList.setOnItemSelectedListener(itemSelectedListener);
        outputList.setOnItemSelectedListener(itemSelectedListener);
    }
    private void setListenerForInput(){
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s=charSequence.toString();
                if(TextUtils.isEmpty(charSequence))s="0";
                calc(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    private void calc(String s){
        String inUnit=inputList.getSelectedItem().toString();
        String outUnit=outputList.getSelectedItem().toString();
        String s1=getResources().getString(
                getResources().getIdentifier(getNameUnit(inUnit),"string",getPackageName())
            );
        String s2=getResources().getString(
                getResources().getIdentifier(getNameUnit(outUnit),"string",getPackageName())
        );
//        BigDecimal d1=new BigDecimal(s1);
//        BigDecimal d2=new BigDecimal(s2);
//        BigDecimal res=new BigDecimal(s).multiply(d1).divide(d2);

        Double d1=Double.parseDouble(s1);
        Double d2=Double.parseDouble(s2);
        Double res=Double.parseDouble(s.toString())*d1/d2;
        output.setText(res.toString());

    }
    private String getNameUnit(String inUnit){
        switch(inUnit){
            case "meters":
            case "??????????": return "meters";
            case "kilometers":
            case "??????????????????": return "kilometers";
            case "centimeters":
            case "????????????????????": return "centimeters";
            case "feet":
            case "????????": return "feet";
            case "miles":
            case "????????": return "miles";
            case "inches":
            case "??????????": return "inches";
            default:return "";
        }
    }
}