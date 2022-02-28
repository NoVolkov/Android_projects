package ru.novlk.lengthunitconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    int[] inUnits;
    boolean l=true;
    Map<String,Double> unitsToMeters;

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
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getStringsLanguages());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputList.setAdapter(adapter);
        outputList.setAdapter(adapter);
    }
    private void setListenerForInput(){
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calc(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    private void calc(CharSequence s){
        String inUnit=inputList.getSelectedItem().toString();
        String outUnit=outputList.getSelectedItem().toString();
        String s1=getResources().getString(
                getResources().getIdentifier(getNameUnit(inUnit),"string",getPackageName())
            );
        String s2=getResources().getString(
                getResources().getIdentifier(getNameUnit(outUnit),"string",getPackageName())
        );
        Double d1=Double.parseDouble(s1);
        Double d2=Double.parseDouble(s2);
        Double res=Double.parseDouble(s.toString())*d1/d2;
        output.setText(res.toString());

    }
    private String getNameUnit(String inUnit){
        switch(inUnit){
            case "meters":
            case "метры": return "meters";
            case "kilometers":
            case "километры": return "kilometers";
            case "centimeters":
            case "сантиметры": return "centimeters";
            case "feet":
            case "футы": return "feet";
            case "miles":
            case "мили": return "miles";
            case "inches":
            case "дюймы": return "inches";
            default:return "";
        }
    }
}