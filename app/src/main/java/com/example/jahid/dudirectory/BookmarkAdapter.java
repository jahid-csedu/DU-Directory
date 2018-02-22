package com.example.jahid.dudirectory;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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

public class BookmarkAdapter extends ArrayAdapter<Teacher> {
    private static final String TAG = "BookmarkArrayAdapter";
    static ArrayList<Teacher> bookmarkList = new ArrayList<>();
    Context context;
    Teacher teacher;
    BookmarkAdapter.BookmarkViewHolder viewHolder;

    static class BookmarkViewHolder {
        TextView name,designation,phone,email,department;
        ImageView call,emailSend,delete;
    }

    public BookmarkAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    @Override
    public void add(Teacher teacher) {
        bookmarkList.add(teacher);
        super.add(teacher);
    }

    @Override
    public int getCount() {
        return this.bookmarkList.size();
    }

    @Override
    public Teacher getItem(int index) {
        return this.bookmarkList.get(index);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.bookmark_list, parent, false);
            viewHolder = new BookmarkViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.teacher_name_bookmark);
            viewHolder.department = (TextView) row.findViewById(R.id.department);
            viewHolder.designation = (TextView) row.findViewById(R.id.designation_bookmark);
            viewHolder.phone = (TextView) row.findViewById(R.id.phone_no_bookmark);
            viewHolder.email = (TextView) row.findViewById(R.id.email_bookmark);
            viewHolder.call = (ImageView) row.findViewById(R.id.call_button_bookmark);
            viewHolder.emailSend = (ImageView) row.findViewById(R.id.email_button_bookmark);
            viewHolder.delete = (ImageView) row.findViewById(R.id.delete_bookmark);
            row.setTag(viewHolder);
        } else {
            viewHolder = (BookmarkAdapter.BookmarkViewHolder)row.getTag();
        }
        teacher = getItem(position);
        Log.d("name",teacher.getName());
        viewHolder.name.setText(teacher.getName());
        viewHolder.department.setText(teacher.getDepartment());
        viewHolder.designation.setText(teacher.getDesignation());
        viewHolder.phone.setText(teacher.getPhone());
        viewHolder.email.setText(teacher.getEmail());

        if(viewHolder.phone.getText().equals("")){
            viewHolder.phone.setText("দুঃখিত, কোন ফোন নম্বর দেওয়া হয়নি");
            viewHolder.call.setVisibility(View.GONE);
        }
        if(viewHolder.email.getText().equals("")){
            viewHolder.emailSend.setVisibility(View.GONE);
            viewHolder.email.setVisibility(View.GONE);
        }

        viewHolder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + viewHolder.phone.getText()));
                context.startActivity(intent);
            }
        });

        viewHolder.emailSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, viewHolder.email.getText());
                context.startActivity(emailIntent);
            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOperation databaseOperation = new DatabaseOperation(context);
                databaseOperation.deleteBookmark(databaseOperation,viewHolder.name.getText().toString(),viewHolder.designation.getText().toString(),viewHolder.phone.getText().toString(),viewHolder.email.getText().toString());
                Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

}
