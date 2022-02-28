package ru.novlk.practice.task_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

import ru.novlk.practice.R;

public class Starter extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1_1);
        textView=findViewById(R.id.txt_1);
        Bundle args=getIntent().getExtras();
        textView.setText("Привет, "
                +args.get("name").toString()
                +" ("+Integer.parseInt(args.get("age").toString())+", "
                +args.get("gender").toString()+")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_page_2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.btn_returnBack){
            Intent intent=new Intent(this,NameForm.class);
            startActivity(intent);
        }
        return true;
    }

    public void editBackground(View view){
        Random r=new Random();
        ConstraintLayout layout=findViewById(R.id.main);
        layout.setBackgroundColor(
                Color.argb(
                        r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt()
                )
        );
    }
    public void editText(View view){

        EditText editText=findViewById(R.id.edtxt_1);
        textView.setText(editText.getText());
    }
    public void goToSecondPage(View view){
        Intent intent=new Intent(this, SecondPage.class);
        startActivity(intent);
    }
}
