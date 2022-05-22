package ru.novlk.post_its.list_item;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import ru.novlk.post_its.R;

public class PostItAdapter extends ArrayAdapter<PostIt> {

    private LayoutInflater inflater;
    private int layout;
    private List<PostIt> postItList;


    public PostItAdapter(@NonNull Context context, int resource, List<PostIt> postItList) {
        super(context, resource, postItList);
        this.postItList=postItList;
        this.layout=resource;
        this.inflater=LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(this.layout,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        PostIt postIt=postItList.get(position);

        viewHolder.titleView.setText(postIt.getTitle());
        viewHolder.textView.setText(postIt.getText());
        viewHolder.dateDeadline.setText(postIt.getDateDeadline());
        viewHolder.createdDate.setText(postIt.getCreatedDate());
        for(Tag t:postIt.getTags()){
            Button b=new Button(viewHolder.view.getContext());
            b.setText(t.getName());
            //Drawable d=ContextCompat.getDrawable(getContext(),R.drawable.tag_button);
            //d.setColorFilter(Color.parseColor("#"+t.getColor()), PorterDuff.Mode.MULTIPLY);
            //b.setBackground(d);
            b.setOnClickListener(x->{
                //System.out.println(t.getId()+"\t"+t.getName()+"\t"+t.getColor());
            });
            viewHolder.listTags.addView(b);
        }

        /*viewHolder.button.setOnClickListener((x)->
            System.out.println("---->"+viewHolder.textView.getText()+this.hashCode())
        );*/
        return convertView;
    }

    private class ViewHolder{
        final TextView titleView, textView, dateDeadline, createdDate;
        final LinearLayout listTags;
        final Button button;
        final View view;
        ViewHolder(View view){
            this.view=view;
            titleView=view.findViewById(R.id.txt_item_title);
            textView=view.findViewById(R.id.txt_item_text);
            dateDeadline=view.findViewById(R.id.txt_item_deadline);
            createdDate=view.findViewById(R.id.txt_item_created_date);
            button=view.findViewById(R.id.btn_new_tag);
            listTags=view.findViewById(R.id.linear_item_tags);
        }
    }
}
