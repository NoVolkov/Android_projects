package ru.novlk.post_its;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import ru.novlk.post_its.list_item.Tag;
import ru.novlk.post_its.list_item.TagAdapter;
import ru.novlk.post_its.sql.SQLiteInstance;
import ru.novlk.post_its.sql.SQLiteInstanceable;

public class ListTagsFragment extends Fragment  implements SQLiteInstanceable {
    private List<Tag> tagsList=new ArrayList<>();
    private SQLiteInstance sqLiteInstance;
    ListView list;

    private ViewGroup thisViewGroup;
    private Bundle thisSavedInstanceState;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisViewGroup=container;
        thisSavedInstanceState=savedInstanceState;
        return inflater.inflate(R.layout.frgt_list_tags, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list=view.findViewById(R.id.list_tags);



        initDate();

        TagAdapter adapter=new TagAdapter(getContext(), R.layout.list_item_tag,tagsList);
        list.setAdapter(adapter);



    }

    @Override
    public void setSQLInstance(SQLiteInstance sqLiteInstance) {
        this.sqLiteInstance=sqLiteInstance;
    }
    private void initDate(){
        tagsList.clear();
        tagsList.addAll(sqLiteInstance.getAllTags());
    }
    public void updateList(){
        onViewCreated(this.getView(),this.getArguments());
    }
}
