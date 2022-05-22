package ru.novlk.post_its.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.novlk.post_its.list_item.PostIt;
import ru.novlk.post_its.list_item.Tag;

public class SQLiteInstance {
    private SQLiteDatabase db;
    public SQLiteInstance(Context context){
        db=new DatabaseHelper(context.getApplicationContext()).getReadableDatabase();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<PostIt> getAllPostIts(){
        List<PostIt> list=new ArrayList<>();
        Cursor curPostIts=db.rawQuery("select * from "+DatabaseHelper.TABLE_NAME_POSTITS,null);

        List<Tag> listTags=getAllTags();
        while(curPostIts.moveToNext()){
            PostIt p=new PostIt(
                curPostIts.getInt(0),
                curPostIts.getString(1),
                curPostIts.getString(2),
            null,
                curPostIts.getString(3),
                curPostIts.getString(4)
            );


            Cursor curTag=db.rawQuery("select * from " + DatabaseHelper.TABLE_NAME_TAGS_FOR_POSTITS + " where " +
                    DatabaseHelper.COL_TFPI_ID_POSTIT + " like ?", new String[]{"%" + p.getId().toString() + "%"});
            List<Tag> tagsForPostIt=new ArrayList<>();
            while(curTag.moveToNext()){
                Integer idTag=curTag.getInt(1);
                tagsForPostIt.addAll(listTags.stream().filter(x->x.getId().equals(idTag)).collect(Collectors.toList()));
            }
            p.setTags(tagsForPostIt);

            curTag.close();
            list.add(p);
        }
        curPostIts.close();
        return list;
    }
    public List<Tag> getAllTags(){
        List<Tag> list=new ArrayList<>();
        Cursor curTags=db.rawQuery("select * from "+DatabaseHelper.TABLE_NAME_TAGS,null);
        while(curTags.moveToNext()){
            Tag t=new Tag(
                    curTags.getInt(0),
                    curTags.getString(1),
                    curTags.getString(2)
            );
            list.add(t);
        }
        curTags.close();
        return list;
    }
    public void addPostIt(PostIt p){
        ContentValues newValues=new ContentValues();
        newValues.put(DatabaseHelper.COL_POSTITS_TITLE,p.getTitle());
        newValues.put(DatabaseHelper.COL_POSTITS_TEXT,p.getText());
        newValues.put(DatabaseHelper.COL_POSTITS_DEADLINE_DATE,p.getDateDeadline());
        newValues.put(DatabaseHelper.COL_POSTITS_CREATED_DATE,p.getCreatedDate());
        db.insert(
                DatabaseHelper.TABLE_NAME_POSTITS,
                null,
                newValues
        );
    }
    public void addTag(Tag t){
        ContentValues newValues=new ContentValues();
        newValues.put(DatabaseHelper.COL_TAGS_NAME,t.getName());
        newValues.put(DatabaseHelper.COL_TAGS_COLOR,t.getColor());
        db.insert(
                DatabaseHelper.TABLE_NAME_TAGS,
                null,
                newValues
        );
    }
    public boolean checkExistsTag(String name){
        Cursor cur=db.rawQuery("select count(*) from "+DatabaseHelper.TABLE_NAME_TAGS+" where "+DatabaseHelper.COL_TAGS_NAME+" = ? GROUP BY ("+DatabaseHelper.COL_TAGS_NAME+")", new String[]{name});
        while(cur.moveToNext()){
            if(cur.getInt(0)>=0)return true;
        }
        return false;
    }
    @Override
    protected void finalize() throws Throwable {
        db.close();
        super.finalize();
    }
}
