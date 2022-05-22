package ru.novlk.post_its.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.novlk.post_its.list_item.PostIt;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="app.db";
    public static final String TABLE_NAME_POSTITS="post_its";
    public static final String TABLE_NAME_TAGS="tags";
    public static final String TABLE_NAME_TAGS_FOR_POSTITS="tags_f_post_its";

    public static final String COL_POSTITS_ID="id";
    public static final String COL_POSTITS_TITLE="title";
    public static final String COL_POSTITS_TEXT="text";
    public static final String COL_POSTITS_CREATED_DATE="create_date";
    public static final String COL_POSTITS_DEADLINE_DATE="deadline_date";

    public static final String COL_TAGS_ID="id";
    public static final String COL_TAGS_NAME="name";
    public static final String COL_TAGS_COLOR="color";

    public static final String COL_TFPI_ID_POSTIT="id_post_it";
    public static final String COL_TFPI_ID_TAG="id_tag";



    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_POSTITS + " ( "
                    + COL_POSTITS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_POSTITS_TITLE + " TEXT, "
                    + COL_POSTITS_TEXT + " TEXT, "
                    + COL_POSTITS_CREATED_DATE + " TEXT, "
                    + COL_POSTITS_DEADLINE_DATE + " TEXT "
                + ");"
                );
        db.execSQL("CREATE TABLE " + TABLE_NAME_TAGS + " ( "
                + COL_TAGS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TAGS_NAME + " TEXT, "
                + COL_TAGS_COLOR + " TEXT DEFAULT 'FFFFFFFF' "
                + ");"
        );
        db.execSQL("CREATE TABLE " + TABLE_NAME_TAGS_FOR_POSTITS + " ( "
                + COL_TFPI_ID_POSTIT + " INTEGER, "
                + COL_TFPI_ID_TAG + " INTEGER, "
                + "FOREIGN KEY("+COL_TFPI_ID_POSTIT+") REFERENCES "+ TABLE_NAME_POSTITS+" ("+COL_POSTITS_ID+") ON DELETE CASCADE, "
                + "FOREIGN KEY("+COL_TFPI_ID_TAG+") REFERENCES "+ TABLE_NAME_TAGS+" ("+COL_TAGS_ID+") "
                + ");"
        );
        db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME_POSTITS + " ( "
                + DatabaseHelper.COL_POSTITS_TITLE+ ", " +DatabaseHelper.COL_POSTITS_TEXT + ", " +DatabaseHelper.COL_POSTITS_CREATED_DATE + ", " +DatabaseHelper.COL_POSTITS_DEADLINE_DATE
                + ") VALUES ( "
                + "'Базовая заметка 1', 'Базовый текст 1', '12.12.2020', '20.04.2022'"
                + ");"
        );
        db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME_TAGS + " ( "
                + DatabaseHelper.COL_TAGS_NAME + ", " + DatabaseHelper.COL_TAGS_COLOR
                + ") VALUES ( "
                + "'Важное', 'FFFF0033'"
                + ");"
        );
        db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME_TAGS_FOR_POSTITS + " ( "
                + DatabaseHelper.COL_TFPI_ID_POSTIT + ", " + DatabaseHelper.COL_TFPI_ID_TAG
                + ") VALUES ( "
                + "1, 1"
                + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_POSTITS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TAGS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TAGS_FOR_POSTITS);
        onCreate(db);
    }


}
