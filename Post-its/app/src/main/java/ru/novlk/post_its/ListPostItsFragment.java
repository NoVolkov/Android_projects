package ru.novlk.post_its;

import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.novlk.post_its.list_item.PostIt;
import ru.novlk.post_its.list_item.PostItAdapter;
import ru.novlk.post_its.sql.DatabaseHelper;
import ru.novlk.post_its.sql.SQLiteInstance;
import ru.novlk.post_its.sql.SQLiteInstanceable;

public class ListPostItsFragment extends Fragment implements SQLiteInstanceable {
    List<PostIt> postItList=new ArrayList<>();
    ListView list;
    SQLiteInstance sqlInstance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frgt_list_post_its, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list=view.findViewById(R.id.list_post_its);

        initDate();
        PostItAdapter adapter=new PostItAdapter(this.getContext(),R.layout.list_item_post_its,postItList);
        list.setAdapter(adapter);

    }


    @Override
    public void setSQLInstance(SQLiteInstance sqLiteInstance) {
        this.sqlInstance=sqLiteInstance;
    }

    private void initDate(){
        postItList.clear();
        postItList.addAll(sqlInstance.getAllPostIts());
        Collections.reverse(postItList);
    }
    public void updateList(){
        onViewCreated(this.getView(),this.getArguments());
    }
}
