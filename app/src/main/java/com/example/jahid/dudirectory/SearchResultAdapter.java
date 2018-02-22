package com.example.jahid.dudirectory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jahid on 5/2/2017.
 */

public class SearchResultAdapter extends ArrayAdapter<Teacher> {
    private static final String TAG = "BookmarkArrayAdapter";
    static ArrayList<Teacher> searchResultList = new ArrayList<>();
    Context context;
    Teacher teacher;
    String teacherName,teacherDept,teacherDesignation,teacherPhone,teacherEmail;

    static class SearchResultViewHolder {
        TextView name,designation,phone,email,department;
        ImageView call,emailSend;
    }
    public SearchResultAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    @Override
    public void add(Teacher teacher) {
        searchResultList.add(teacher);
        super.add(teacher);
    }

    @Override
    public int getCount() {
        return this.searchResultList.size();
    }

    @Override
    public Teacher getItem(int index) {
        return this.searchResultList.get(index);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;
        final SearchResultAdapter.SearchResultViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.search_result_list_item, parent, false);
            viewHolder = new SearchResultAdapter.SearchResultViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.teacher_name_search_result);
            viewHolder.department = (TextView) row.findViewById(R.id.department_search_result);
            viewHolder.designation = (TextView) row.findViewById(R.id.designation_search_result);
            viewHolder.phone = (TextView) row.findViewById(R.id.phone_no_search_result);
            viewHolder.email = (TextView) row.findViewById(R.id.email_search_result);
            viewHolder.call = (ImageView) row.findViewById(R.id.call_button_search_result);
            viewHolder.emailSend = (ImageView) row.findViewById(R.id.email_button_search_result);
            row.setTag(viewHolder);
        } else {
            viewHolder = (SearchResultAdapter.SearchResultViewHolder)row.getTag();
        }
        teacher = getItem(position);
        teacherName = teacher.getName();
        teacherDept = teacher.getDepartment();
        teacherDesignation = teacher.getDesignation();
        teacherPhone = teacher.getPhone();
        teacherEmail = teacher.getEmail();
        viewHolder.name.setText(teacherName);
        viewHolder.department.setText(teacherDept);
        viewHolder.designation.setText(teacherDesignation);
        viewHolder.phone.setText(teacherPhone);
        viewHolder.email.setText(teacherEmail);

        if(!teacherEmail.equals("")){
            viewHolder.emailSend.setVisibility(View.VISIBLE);
            viewHolder.email.setVisibility(View.VISIBLE);
        }
        if(!teacherPhone.equals("")){
            viewHolder.call.setVisibility(View.VISIBLE);
        }else{
            viewHolder.phone.setText("দুঃখিত, কোন ফোন নম্বর দেওয়া হয়নি");
            viewHolder.call.setVisibility(View.GONE);
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

        return row;
    }
}
