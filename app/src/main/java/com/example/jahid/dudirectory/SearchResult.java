package com.example.jahid.dudirectory;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ListView listView = (ListView) findViewById(R.id.search_result_list);
        TextView result = (TextView) findViewById(R.id.search_result_text);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        String name = getIntent().getExtras().getString("name");
        String department = getIntent().getExtras().getString("department");

        DatabaseOperation databaseOperation = new DatabaseOperation(this);
        Cursor data = databaseOperation.findTeacher(databaseOperation,name,department);
        data.moveToFirst();

        SearchResultAdapter adapter = new SearchResultAdapter(this,R.layout.search_result_list_item);
        adapter.searchResultList.clear();

        if(data.getCount()==0){
            result.setText("কোন ফলাফল পাওয়া যায়নি");
            result.setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < data.getCount(); i++) {
            Teacher teacher = new Teacher(data.getString(0), data.getString(1), data.getString(2), data.getString(3),data.getString(4));
            adapter.add(teacher);
            data.moveToNext();
        }

        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
