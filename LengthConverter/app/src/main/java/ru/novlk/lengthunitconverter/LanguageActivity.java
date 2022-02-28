package ru.novlk.lengthunitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.novlk.lengthunitconverter.models.Language;
import ru.novlk.lengthunitconverter.models.LanguageAdapter;

public class LanguageActivity extends AppCompatActivity {
    List<Language> languages=new ArrayList<>();
    ListView languageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        setInitialData();

        languageList=findViewById(R.id.list_langPage);

        LanguageAdapter languageAdapter=new LanguageAdapter(this,R.layout.lang_item,languages);
        languageList.setAdapter(languageAdapter);

        AdapterView.OnItemClickListener itemClickListener= new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Language selectedLang = (Language) adapterView.getItemAtPosition(i);
                setLanguage(selectedLang);

            }
        };
        languageList.setOnItemClickListener(itemClickListener);

    }
    private void setInitialData(){
        String[] name=getResources().getStringArray(R.array.languages);
        String[] abbName=getResources().getStringArray(R.array.abbreviations);
        languages.add(new Language(name[0],abbName[0]));
        languages.add(new Language(name[1],abbName[1]));
    }
    private void setLanguage(Language lang){
        Locale locale = new Locale(lang.getAbbLang());
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);

        Toast toast=Toast.makeText(this, R.string.message, Toast.LENGTH_LONG);
        toast.show();

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}