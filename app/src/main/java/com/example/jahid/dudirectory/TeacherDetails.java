package com.example.jahid.dudirectory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherDetails extends AppCompatActivity {

    TextView name,designation,phone,email,department,phone2,email2;
    ImageView call,call2,emailSend,bookmark,sendSms,sendSms2,emailSend2;
    RelativeLayout phoneBar,phoneBar2,emailBar,emailBar2;
    String teacherName,teacherDept,teacherDesignation,teacherPhone,teacherEmail,teacherPhone2,teacherEmail2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},2);
        }

        teacherName = getIntent().getExtras().getString("name");
        teacherDept= getIntent().getExtras().getString("department");
        teacherDesignation = getIntent().getExtras().getString("designation");
        teacherPhone = getIntent().getExtras().getString("phone");
        teacherEmail = getIntent().getExtras().getString("email");
        teacherPhone2 = getIntent().getExtras().getString("phone2");
        teacherEmail2 = getIntent().getExtras().getString("email2");

        name = (TextView)findViewById(R.id.teacher_name_details);
        department = (TextView)findViewById(R.id.dept_name_details);
        designation = (TextView)findViewById(R.id.designation_details);
        phone = (TextView)findViewById(R.id.phone_no_details);
        email = (TextView)findViewById(R.id.email_details);
        phone2 = (TextView)findViewById(R.id.phone_no_details2);
        email2 = (TextView)findViewById(R.id.email_details2);
        call = (ImageView) findViewById(R.id.details_call_button);
        emailSend = (ImageView) findViewById(R.id.details_email_button);
        call2 = (ImageView) findViewById(R.id.details_call_button2);
        emailSend2 = (ImageView) findViewById(R.id.details_email_button2);
        bookmark = (ImageView) findViewById(R.id.bookmark_icon);
        sendSms = (ImageView) findViewById(R.id.details_sms_button);
        sendSms2 = (ImageView) findViewById(R.id.details_sms_button2);
        phoneBar = (RelativeLayout) findViewById(R.id.phone_bar);
        phoneBar2 = (RelativeLayout) findViewById(R.id.phone_bar2);
        emailBar = (RelativeLayout) findViewById(R.id.email_bar);
        emailBar2 = (RelativeLayout) findViewById(R.id.email_bar2);

        name.setText(teacherName);
        department.setText(teacherDept);
        designation.setText(teacherDesignation);
        phone.setText(teacherPhone);
        email.setText(teacherEmail);
        phone2.setText(teacherPhone2);
        email2.setText(teacherEmail2);

        DatabaseOperation databaseOperation = new DatabaseOperation(this);
        if(databaseOperation.isBookmarked(databaseOperation,teacherName,teacherDesignation,teacherPhone,teacherEmail)){
            bookmark.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_black_24dp));
        }

        emailSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email(v);
            }
        });

        emailSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email2(v);
            }
        });

        if(phone.getText().equals("")){
            phone.setText("দুঃখিত, কোন ফোন নম্বর দেওয়া হয়নি");
            call.setVisibility(View.GONE);
            sendSms.setVisibility(View.GONE);
        }
        if(phone2.getText().equals("")){
            phoneBar2.setVisibility(View.GONE);
        }
        if(email.getText().equals("")){
            emailBar.setVisibility(View.GONE);
        }
        if(email2.getText().equals("")){
            emailBar2.setVisibility(View.GONE);
        }
    }

    public void call(View view){

        Intent intent;
        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + teacherPhone));
        startActivity(intent);
    }

    public void sms(View view){

        Uri smsUri;
        smsUri = Uri.parse("tel:" + teacherPhone);
        Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
        intent.putExtra("address", teacherPhone);
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);
    }

    public void call2(View view){

        Intent intent;
        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + teacherPhone2));
        startActivity(intent);
    }

    public void sms2(View view){

        Uri smsUri;
        smsUri = Uri.parse("tel:" + teacherPhone2);
        Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
        intent.putExtra("address", teacherPhone2);
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);
    }

    public void email(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", teacherEmail, null));
        this.startActivity(Intent.createChooser(emailIntent, null));
    }

    public void email2(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", teacherEmail2, null));
        this.startActivity(Intent.createChooser(emailIntent, null));
    }

    public void setBookmark(View view){

        DatabaseOperation databaseOperation = new DatabaseOperation(getApplicationContext());
        if(!databaseOperation.isBookmarked(databaseOperation,teacherName,teacherDesignation,teacherPhone,teacherEmail)) {
            databaseOperation.bookmark(databaseOperation, teacherName, teacherDesignation, teacherPhone, teacherEmail);
            bookmark.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_black_24dp));
        }else{
            databaseOperation.deleteBookmark(databaseOperation, teacherName, teacherDesignation, teacherPhone, teacherEmail);
            bookmark.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
        }

    }

    public void setReminder(View view){
        Intent intent = new Intent(getApplicationContext(),Reminder.class);
        intent.putExtra("name",name.getText());
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    public void address(View view){
        Intent intent = new Intent(getApplicationContext(),Location.class);
        intent.putExtra("department",department.getText());
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 2){

            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
