package ru.novlk.lengthunitconverter.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.novlk.lengthunitconverter.R;

public class LanguageAdapter extends ArrayAdapter<Language> {
    private LayoutInflater inflater;
    private int layout;
    private List<Language> languages;

    public LanguageAdapter(Context context, int resource, List<Language> languages){
        super(context,resource,languages);
        this.languages=languages;
        this.layout=resource;
        this.inflater=LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View view=inflater.inflate(this.layout,parent,false);

        TextView nameView=view.findViewById(R.id.nameLang);

        Language language=languages.get(position);

        nameView.setText(language.getLang());
        return view;
    }
}
