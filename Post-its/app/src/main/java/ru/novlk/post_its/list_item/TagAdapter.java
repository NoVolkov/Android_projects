package ru.novlk.post_its.list_item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ru.novlk.post_its.R;

public class TagAdapter extends ArrayAdapter<Tag> {
    private LayoutInflater inflater;
    private int layout;
    private List<Tag> tagList;

    public TagAdapter(@NonNull Context context, int resource, @NonNull List<Tag> objects) {
        super(context, resource, objects);
        this.tagList=objects;
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
        Tag tag=tagList.get(position);
        viewHolder.nameView.setText(tag.getName());
        viewHolder.deleteView.setOnClickListener(x->{});
        viewHolder.editView.setOnClickListener(x->{});
        return convertView;
    }

    private class ViewHolder{
        final TextView nameView;
        final Button deleteView, editView;
        final View view;

        public ViewHolder(View view) {
            this.view = view;
            nameView=view.findViewById(R.id.txt_item_name);
            deleteView=view.findViewById(R.id.btn_edit_tag);
            editView=view.findViewById(R.id.btn_delete_tag);
        }
    }
}
