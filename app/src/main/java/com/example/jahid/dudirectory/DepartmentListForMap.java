package com.example.jahid.dudirectory;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DepartmentListForMap extends AppCompatActivity {

    Cursor data;
    private CardArrayAdapter cardArrayAdapter;
    String deptName,building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list_for_map);

        ListView listView = (ListView) findViewById(R.id.departmentLocationList);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.division_list_item);

        DatabaseOperation databaseOperation = new DatabaseOperation(this);
        data = databaseOperation.getAllDepartmentWithLocation(databaseOperation);
        data.moveToFirst();
        for(int i=0;i<data.getCount();i++){
            deptName = data.getString(0);
            building = data.getString(1);
            Card card = new Card(deptName,building);
            cardArrayAdapter.add(card);
            data.moveToNext();
        }
        listView.setAdapter(cardArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String department = ((TextView)view.findViewById(R.id.line1)).getText().toString();
                Intent intent = new Intent(getApplicationContext(),Location.class);
                intent.putExtra("department",department);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
}
