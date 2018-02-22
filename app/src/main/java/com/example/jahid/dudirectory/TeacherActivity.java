package com.example.jahid.dudirectory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
        }

        listView = (ListView) findViewById(R.id.teacher_list);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        String department = getIntent().getExtras().getString("department");

        DatabaseOperation databaseOperation = new DatabaseOperation(this);
        Cursor data = databaseOperation.getData(databaseOperation, department);
        data.moveToFirst();

        TeacherAdapter teachers = new TeacherAdapter(this, R.layout.teacher_list_item);
        TeacherAdapter.teacherList.clear();

        for (int i = 0; i < data.getCount(); i++) {
            Teacher teacher = new Teacher(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5),department);
            teachers.add(teacher);
            data.moveToNext();
        }

        listView.setAdapter(teachers);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 1){

            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }
        }
    }
}
