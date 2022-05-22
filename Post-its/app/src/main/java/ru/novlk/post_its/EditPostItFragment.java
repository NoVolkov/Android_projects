package ru.novlk.post_its;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ru.novlk.post_its.list_item.PostIt;
import ru.novlk.post_its.sql.SQLiteInstance;
import ru.novlk.post_its.sql.SQLiteInstanceable;

public class EditPostItFragment extends Fragment  implements SQLiteInstanceable {
    MainActivity contextParent;
    Button btn_Cancel;
    Button btn_Update;


    TextView textViewTitle;
    TextView textViewText;
    TextView textViewDeadline;
    Button btn_ChangeDeadline;

    LinearLayout linearCalendar;
    CalendarView calendarView;
    Button btn_CalendarCancel;
    Button btn_CalendarUpdate;
    private String changedCalendarDate;

    SQLiteInstance sqlInstance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frgt_popup_edit_post_it, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_Cancel=view.findViewById(R.id.btn_cancel);
        btn_Update=view.findViewById(R.id.btn_update);
        btn_ChangeDeadline=view.findViewById(R.id.btn_changeDeadline);
        btn_CalendarCancel=view.findViewById(R.id.btn_calendar_cancel);
        btn_CalendarUpdate=view.findViewById(R.id.btn_calendar_update);

        btn_Cancel.setOnClickListener((x)-> {
            onClick_cancel();
        });
        btn_Update.setOnClickListener((x)-> {
            onClick_add();
        });
        btn_CalendarUpdate.setOnClickListener(x->{
            onClick_updateCalendar();
        });
        btn_CalendarCancel.setOnClickListener((x)->onClick_changeDeadline());
        btn_ChangeDeadline.setOnClickListener((x)->{onClick_changeDeadline();});
        textViewTitle=view.findViewById(R.id.edittxt_title);
        textViewText=view.findViewById(R.id.edittxt_text);
        textViewDeadline=view.findViewById(R.id.txt_deadline);

        linearCalendar=view.findViewById(R.id.linear_calendar);
        calendarView=view.findViewById(R.id.calendarView);
        linearCalendar.setVisibility(View.INVISIBLE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                Calendar calendar=new GregorianCalendar();
                int mYear=year;
                int mMonth=month;
                int mDay=day;
                if(calendar.after(new GregorianCalendar(mYear,mMonth,mDay))){
                    textViewDeadline.requestFocus();
                    textViewDeadline.setError("Установлена текущая дата");
                    changedCalendarDate=new StringBuilder()
                            .append(calendar.get(Calendar.DAY_OF_MONTH)).append(".")
                            .append(calendar.get(Calendar.MONTH)+1).append(".")
                            .append(calendar.get(Calendar.YEAR))
                            .toString();
                    return;
                }
                changedCalendarDate=new StringBuilder()
                        .append(mDay).append(".")
                        .append(mMonth+1).append(".")
                        .append(mYear).toString();

            }
        });
    }

    public void setContextParent(MainActivity contextParent) {
        this.contextParent = contextParent;

    }

    public void onClick_cancel(){
        contextParent.closePopup(this);
    }
    public void onClick_add(){
        if(textViewTitle.getText().equals(null) || textViewTitle.getText().length()==0){
            textViewTitle.requestFocus();
            textViewTitle.setError("Название не задано");
            return;
        }
        if (textViewText.getText().equals(null) || textViewText.getText().length()==0){
            textViewText.setText("");
        }
        Calendar calendar=Calendar.getInstance();
        if(changedCalendarDate==null || changedCalendarDate.length()==0){
            changedCalendarDate=new StringBuilder()
                    .append(calendar.get(Calendar.DAY_OF_MONTH)).append(".")
                    .append(calendar.get(Calendar.MONTH)+1).append(".")
                    .append(calendar.get(Calendar.YEAR))
                    .toString();
        }
        PostIt p=new PostIt(
                null,
                textViewTitle.getText().toString(),
                textViewText.getText().toString(),
                null,
                changedCalendarDate,
                new StringBuilder()
                        .append(calendar.get(Calendar.DAY_OF_MONTH)).append(".")
                        .append(calendar.get(Calendar.MONTH)+1).append(".")
                        .append(calendar.get(Calendar.YEAR))
                        .toString()
        );
        //System.out.println(p.getTitle()+" "+p.getText()+" "+p.getDateDeadline()+" "+p.getCreatedDate());
        sqlInstance.addPostIt(p);
        contextParent.frgt_postIts.updateList();
        onClick_cancel();
    }
    public void onClick_changeDeadline(){
        if(linearCalendar.getVisibility()==View.VISIBLE){
            linearCalendar.setVisibility(View.INVISIBLE);
            btn_ChangeDeadline.setVisibility(View.VISIBLE);
        }else{
            linearCalendar.setVisibility(View.VISIBLE);
            btn_ChangeDeadline.setVisibility(View.INVISIBLE);
            Calendar c=Calendar.getInstance();
            calendarView.setDate(c.getTimeInMillis(),true,true);
        }
    }
    public void onClick_updateCalendar(){
        onClick_changeDeadline();
        textViewDeadline.setText(changedCalendarDate);
    }
    @Override
    public void setSQLInstance(SQLiteInstance sqLiteInstance) {
        this.sqlInstance=sqLiteInstance;
    }
}
