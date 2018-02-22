package com.example.jahid.dudirectory;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DivisionActivity extends AppCompatActivity {
    Cursor data;
    private CardArrayAdapter cardArrayAdapter;
    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        ListView listView = (ListView) findViewById(R.id.divisionList);
        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.division_list_item);

        type = getIntent().getExtras().getString("type");

        if(type.equals("faculty")) {
            DatabaseOperation databaseOperation = new DatabaseOperation(this);
            data = databaseOperation.getFaculty(databaseOperation);
            data.moveToFirst();
            for(int i=0;i<data.getCount();i++){
                String name;
                int numberOfDept;
                name = data.getString(0);
                Cursor dept = databaseOperation.getDepartment(databaseOperation,name);
                numberOfDept = dept.getCount();
                Card card = new Card(name,Integer.toString(numberOfDept)+" টি বিভাগ");
                cardArrayAdapter.add(card);
                data.moveToNext();
            }
            listView.setAdapter(cardArrayAdapter);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String faculty = ((TextView)view.findViewById(R.id.line1)).getText().toString();
                Intent intent = new Intent(getApplicationContext(),SubDivisionActivity.class);
                intent.putExtra("division",faculty);
                intent.putExtra("type",type);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            callPopup();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void callPopup() {

        final Dialog view = new Dialog(this);
        view.requestWindowFeature(Window.FEATURE_NO_TITLE);
        view.setContentView(R.layout.search_view);
        view.show();

        ArrayList<String> list = new ArrayList<>();
        list.clear();
        DatabaseOperation databaseOperation = new DatabaseOperation(this);
        Cursor data = databaseOperation.getAllSubDivision(databaseOperation);
        data.moveToFirst();
        while (data.moveToNext()){
            list.add(data.getString(0));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,list);
        ((AutoCompleteTextView)view.findViewById(R.id.search_dept)).setAdapter(adapter);


        ((Button)view.findViewById(R.id.search_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)view.findViewById(R.id.search_teacher)).getText().toString();
                String department = ((AutoCompleteTextView)view.findViewById(R.id.search_dept)).getText().toString();

                Intent intent = new Intent(getApplicationContext(),SearchResult.class);
                intent.putExtra("name",name);
                intent.putExtra("department",department);
                view.dismiss();
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        ((Button)view.findViewById(R.id.search_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.dismiss();
            }
        });
    }
}
