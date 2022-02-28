package ru.novlk.practice.task_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ru.novlk.practice.R;

public class SecondPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_1_2);
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
            Intent intent=new Intent(this,Starter.class);
            startActivity(intent);
        }
        return true;
    }

    public void goToSecondPage(View view){
        Intent intent=new Intent(this, Starter.class);
        startActivity(intent);
    }
}
