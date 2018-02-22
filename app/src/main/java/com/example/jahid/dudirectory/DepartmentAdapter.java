package com.example.jahid.dudirectory;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jahid on 4/16/2017.
 */

public class DepartmentAdapter extends ArrayAdapter<Department> {

    Context context;
    int resourceId;
    ArrayList<Department> data;

    public DepartmentAdapter(Context context, int resourceId, ArrayList<Department> data) {
        super(context, resourceId,data);
        this.context = context;
        this.resourceId = resourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DeptHolder holder = null;

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resourceId,parent,false);

            holder = new DeptHolder();
            holder.deptName = (TextView) row.findViewById(R.id.dept);
            holder.email = (TextView) row.findViewById(R.id.email);

            row.setTag(holder);
        }else{
            holder = (DeptHolder) row.getTag();
        }

        Department department = data.get(position);
        holder.deptName.setText(department.getDepartmentName());
        holder.email.setText(department.getEmail());
        return row;
    }

    static class DeptHolder{
        TextView deptName,email;
    }
}
