package ru.novlk.post_its;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ru.novlk.post_its.sql.DatabaseHelper;
import ru.novlk.post_its.sql.SQLiteInstance;

public class MainActivity extends AppCompatActivity {
    FragmentContainerView fragmentView;
    FragmentContainerView fragmentView_popup;

    ListPostItsFragment frgt_postIts;
    ListTagsFragment frgt_tags;
    EditPostItFragment frgt_edit_postIt;
    EditTagFragment frgt_edit_tag;

    FragmentTransaction fTrans;
    FragmentTransaction fTrans_2;

    SQLiteInstance sqlInstance;
    ImageButton btn_newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.popup_page).setZ(-1);
        findViewById(R.id.main_page).setZ(1);

        findViewById(R.id.second_page).setZ(0);
        findViewById(R.id.second_page).setClickable(true);

        fragmentView=findViewById(R.id.fragment);
        fragmentView_popup=findViewById(R.id.fragment_popup);
        btn_newItem=findViewById(R.id.btn_newItem);

        sqlInstance=new SQLiteInstance(this);

        initFragmentsList();
        frgt_edit_postIt=new EditPostItFragment();
        frgt_edit_tag=new EditTagFragment();


        frgt_edit_postIt.setSQLInstance(sqlInstance);

        frgt_edit_tag.setSQLInstance(sqlInstance);

        onClick_goToListPostIts(null);


    }
    public void initFragmentsList(){
        frgt_postIts=new ListPostItsFragment();
        frgt_tags=new ListTagsFragment();
        frgt_postIts.setSQLInstance(sqlInstance);
        frgt_tags.setSQLInstance(sqlInstance);
    }

    public void onClick_newPostIts(View v){
        findViewById(R.id.popup_page).setZ(1);
        findViewById(R.id.main_page).setZ(-1);
        frgt_edit_postIt.setContextParent(this);
        fTrans=getSupportFragmentManager().beginTransaction();
        fTrans.add(fragmentView_popup.getId(),frgt_edit_postIt);
        fTrans.commit();
    }
    public void onClick_newTag(View v){
        findViewById(R.id.popup_page).setZ(1);
        findViewById(R.id.main_page).setZ(-1);
        frgt_edit_tag.setContextParent(this);
        fTrans=getSupportFragmentManager().beginTransaction();
        fTrans.add(fragmentView_popup.getId(),frgt_edit_tag);
        fTrans.commit();
    }

    public void onClick_goToListPostIts(View view){
        fTrans=getSupportFragmentManager().beginTransaction();
        fTrans.replace(fragmentView.getId(),frgt_postIts);
        //fTrans.addToBackStack(null);
        fTrans.commit();
        btn_newItem.setOnClickListener(x->onClick_newPostIts(null));
    }
    public void onClick_goToListTags(View view){
        fTrans=getSupportFragmentManager().beginTransaction();
        fTrans.replace(fragmentView.getId(),frgt_tags);
        //fTrans.addToBackStack(null);
        fTrans.commit();
        btn_newItem.setOnClickListener(x->onClick_newTag(null));
    }

    public void closePopup(Fragment curFrgt){
        fTrans=getSupportFragmentManager().beginTransaction();
        //Fragment curFrgt=frgt_edit_postIt.isAdded()?frgt_edit_postIt:frgt_edit_tag;
        fTrans.remove(curFrgt);
        fTrans.commit();
        findViewById(R.id.popup_page).setZ(-1);
        findViewById(R.id.main_page).setZ(1);

    }
}