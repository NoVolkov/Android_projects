package ru.novlk.post_its;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.novlk.post_its.list_item.Tag;
import ru.novlk.post_its.sql.SQLiteInstance;
import ru.novlk.post_its.sql.SQLiteInstanceable;

public class EditTagFragment extends Fragment implements SQLiteInstanceable {
    MainActivity contextParent;
    Button btn_Cancel;
    Button btn_Update;

    EditText txt_nameTag;
    SQLiteInstance sqLiteInstance;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frgt_popup_edit_tag,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_nameTag=view.findViewById(R.id.edittxt_name);
        btn_Cancel=view.findViewById(R.id.btn_cancel);
        btn_Update=view.findViewById(R.id.btn_update);
        btn_Cancel.setOnClickListener(x->onClick_cancel());
        btn_Update.setOnClickListener(x->onClick_add());
    }
    public void setContextParent(MainActivity contextParent){
        this.contextParent=contextParent;
    }
    public void onClick_cancel(){contextParent.closePopup(this);}
    public void onClick_add(){
        String name=txt_nameTag.getText().toString().toLowerCase().trim();

        if(sqLiteInstance.checkExistsTag(name)){
            txt_nameTag.requestFocus();
            txt_nameTag.setError("Данный тег уже существует");
            return;
        }
        Tag t=new Tag(null,name,"FFFFFFFF");
        sqLiteInstance.addTag(t);

        contextParent.frgt_tags.updateList();
        onClick_cancel();
    }
    @Override
    public void setSQLInstance(SQLiteInstance sqLiteInstance) {
        this.sqLiteInstance=sqLiteInstance;
    }
}
