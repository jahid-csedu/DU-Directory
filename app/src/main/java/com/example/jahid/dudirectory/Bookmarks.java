package com.example.jahid.dudirectory;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class Bookmarks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        ListView listView = (ListView) findViewById(R.id.list_view_bookmarks);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        DatabaseOperation databaseOperation = new DatabaseOperation(this);
        Cursor data = databaseOperation.getBookmarks(databaseOperation);
        data.moveToFirst();
        Log.d("bookmark",Integer.toString(data.getCount()));

        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(this, R.layout.bookmark_list);
        BookmarkAdapter.bookmarkList.clear();

        for (int i = 0; i < data.getCount(); i++) {
            Teacher teacher = new Teacher(data.getString(0), data.getString(1), data.getString(2), data.getString(3),data.getString(4));
            bookmarkAdapter.add(teacher);
            data.moveToNext();
        }

        listView.setAdapter(bookmarkAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
