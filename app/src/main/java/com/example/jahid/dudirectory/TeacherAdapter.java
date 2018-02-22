package com.example.jahid.dudirectory;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jahid on 4/23/2017.
 */

public class TeacherAdapter extends ArrayAdapter<Teacher> {
    private static final String TAG = "TeacherArrayAdapter";
    static ArrayList<Teacher> teacherList = new ArrayList<>();
    Context context;
    Teacher teacher;
    String teacherName,teacherDept,teacherDesignation,teacherPhone,teacherEmail,teacherPhone2,teacherEmail2;
    Intent intent;

    static class TeacherViewHolder {
        TextView name,dept,designation,phone,email,phone2,email2;
        ImageView call;
    }

    public TeacherAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    @Override
    public void add(Teacher teacher) {
        teacherList.add(teacher);
        super.add(teacher);
    }

    @Override
    public int getCount() {
        return this.teacherList.size();
    }

    @Override
    public Teacher getItem(int index) {
        return this.teacherList.get(index);
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        final TeacherAdapter.TeacherViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.teacher_list_item, parent, false);
            viewHolder = new TeacherViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.teacher_name);
            viewHolder.dept = (TextView) row.findViewById(R.id.dept_name);
            viewHolder.designation = (TextView) row.findViewById(R.id.designation);
            viewHolder.phone = (TextView) row.findViewById(R.id.phone_no);
            viewHolder.email = (TextView) row.findViewById(R.id.email_id);
            viewHolder.phone2 = (TextView) row.findViewById(R.id.phone_no2);
            viewHolder.email2 = (TextView) row.findViewById(R.id.email_id2);
            viewHolder.call = (ImageView) row.findViewById(R.id.button_call);
            row.setTag(viewHolder);
        } else {
            viewHolder = (TeacherAdapter.TeacherViewHolder)row.getTag();
        }
        teacher = getItem(position);
        teacherName = teacher.getName();
        teacherDept = teacher.getDepartment();
        teacherDesignation = teacher.getDesignation();
        teacherPhone = teacher.getPhone();
        teacherPhone2 = teacher.getPhone2();
        teacherEmail2 = teacher.getEmail2();
        teacherEmail = teacher.getEmail();

        if(!teacherPhone.equals("")){
            viewHolder.call.setVisibility(View.VISIBLE);
        }else {
            viewHolder.call.setVisibility(View.GONE);
        }

        viewHolder.name.setText(teacherName);
        viewHolder.dept.setText(teacherDept);
        viewHolder.designation.setText(teacherDesignation);
        viewHolder.phone.setText(teacherPhone);
        viewHolder.email.setText(teacherEmail);
        viewHolder.phone2.setText(teacherPhone2);
        viewHolder.email2.setText(teacherEmail2);

        viewHolder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewHolder.phone.getText().equals("")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + viewHolder.phone.getText()));
                    context.startActivity(intent);
                }
            }
        });

        /*viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"clicked",Toast.LENGTH_LONG).show();
            }
        });*/

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context,TeacherDetails.class);
                intent.putExtra("name",viewHolder.name.getText());
                intent.putExtra("designation",viewHolder.designation.getText());
                intent.putExtra("phone",viewHolder.phone.getText());
                intent.putExtra("email",viewHolder.email.getText());
                intent.putExtra("phone2",viewHolder.phone2.getText());
                intent.putExtra("email2",viewHolder.email2.getText());
                intent.putExtra("department",viewHolder.dept.getText());
                context.startActivity(intent);


            }
        });

        return row;
    }

}
