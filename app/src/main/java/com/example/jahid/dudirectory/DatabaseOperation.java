package com.example.jahid.dudirectory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jahid on 4/11/2017.
 */

public class DatabaseOperation extends SQLiteOpenHelper {

    private static final String DB_NAME = "du_directory";
    private static final int DB_VERSION = 3;
    private static final String TABLE_NAME = "info";
    private static final String FACULTY_TABLE = "faculty";
    private static final String INSTITUTE_TABLE = "institute";
    private static final String HALL_TABLE = "hall";
    private static final String DEPT_TABLE = "department";
    private static final String OFFICE_TABLE = "office";
    private static final String LOCATION_TABLE = "location";
    private static final String RESEARCH_TABLE = "research";
    private static final String OTHERS_TABLE = "others";
    private static final String HALL_NAME = "hall_name";
    private static final String FACULTY_NAME = "faculty_name";
    private static final String INSTITUTE_NAME = "institute_name";
    private static final String DEPT_NAME = "dept_name";
    private static final String BUILDING_NAME = "building";
    private static final String OFFICE_NAME = "office_name";
    private static final String LATTITUDE = "lattitude";
    private static final String LONGITUDE = "longitude";
    private static final String EMAIL = "email";
    private static final String ID = "ID";
    private static final String NAME = "name";
    private static final String NAME_ENG = "name_eng";
    private static final String DIVISION = "division";
    private static final String SUB_DIVISION = "sub_division";
    private static final String DESIGNATION = "designation";
    private static final String PHONE1 = "phone1";
    private static final String PHONE2 = "phone2";
    private static final String EMAIL1 = "email1";
    private static final String EMAIL2 = "email2";
    private static final String SHORT_CODE = "short_code";
    private static final String INFO = "info";
    private static final String BOOKMARK = "bookmark";
    private static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("+ID+" integer primary key, "+NAME+" text, "+NAME_ENG+
            " text, "+DIVISION+" text, "+SUB_DIVISION+" text, "+ DESIGNATION+" text, "+PHONE1+" text, "+PHONE2+" text, "+EMAIL1+" text, "+"" +
            EMAIL2+" text, "+SHORT_CODE+" text, "+INFO+" text,"+BOOKMARK+" integer);";

    private static final String CREATE_INSTITUTE_TABLE = "create table "+INSTITUTE_TABLE+"("+ID+" integer primary key, "+INSTITUTE_NAME+" text, "+EMAIL+" text);";

    private static final String CREATE_DEPT_TABLE = "create table "+DEPT_TABLE+"("+ID+" integer primary key, "+DEPT_NAME+
            " text, "+FACULTY_NAME+" text, "+EMAIL+" text);";

    private static final String CREATE_LOCATION_TABLE = "create table "+LOCATION_TABLE+"("+DEPT_NAME+" text, "+BUILDING_NAME+
            " text, "+LATTITUDE+" float, "+LONGITUDE+" float);";


    private static final String CREATE_FACULTY_TABLE = "create table "+FACULTY_TABLE+"("+ID+" integer primary key, "+FACULTY_NAME+" text);";

    private static final String CREATE_OFFICE_TABLE = "create table "+OFFICE_TABLE+"("+ID+" integer primary key, "+OFFICE_NAME+" text);";

    private static final String CREATE_HALL_TABLE = "create table "+HALL_TABLE+"("+ID+" integer primary key, "+HALL_NAME+" text);";

    private static final String CREATE_RESEARCH_TABLE = "create table "+RESEARCH_TABLE+"("+ID+" integer primary key, "+DEPT_NAME+" text);";

    private static final String CREATE_OTHERS_TABLE = "create table "+OTHERS_TABLE+"("+ID+" integer primary key, "+DEPT_NAME+" text);";

    public DatabaseOperation(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_FACULTY_TABLE);
        db.execSQL(CREATE_DEPT_TABLE);
        db.execSQL(CREATE_INSTITUTE_TABLE);
        db.execSQL(CREATE_HALL_TABLE);
        db.execSQL(CREATE_OFFICE_TABLE);
        db.execSQL(CREATE_LOCATION_TABLE);
        db.execSQL(CREATE_RESEARCH_TABLE);
        db.execSQL(CREATE_OTHERS_TABLE);
        Log.d("Database Operation","Table Created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertData(DatabaseOperation databaseOperation, String name, String engName, String division, String subdivision, String designation, String phone1, String phone2, String email1, String email2, String shortCode, String info){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(NAME,name);
        row.put(NAME_ENG,engName);
        row.put(DIVISION,division);
        row.put(SUB_DIVISION,subdivision);
        row.put(DESIGNATION,designation);
        row.put(PHONE1,phone1);
        row.put(PHONE2,phone2);
        row.put(EMAIL1,email1);
        row.put(EMAIL2,email2);
        row.put(SHORT_CODE,shortCode);
        row.put(INFO,info);
        row.put(BOOKMARK,0);
        long k = database.insert(TABLE_NAME,null,row);
        Log.d("Database Operation","One Row inserted");
    }
    public void insertFaculty(DatabaseOperation databaseOperation, String name){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(FACULTY_NAME,name);
        long k = database.insert(FACULTY_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public void insertHall(DatabaseOperation databaseOperation, String name){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(HALL_NAME,name);
        long k = database.insert(HALL_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public void insertResearch(DatabaseOperation databaseOperation, String name){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(DEPT_NAME,name);
        long k = database.insert(RESEARCH_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public void insertOthers(DatabaseOperation databaseOperation, String name){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(DEPT_NAME,name);
        long k = database.insert(OTHERS_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public void insertOffice(DatabaseOperation databaseOperation, String name){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(OFFICE_NAME,name);
        long k = database.insert(OFFICE_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public void insertDepartment(DatabaseOperation databaseOperation, String name,String email, String faculty){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(DEPT_NAME,name);
        row.put(EMAIL,email);
        row.put(FACULTY_NAME,faculty);
        long k = database.insert(DEPT_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public void insertLocation(DatabaseOperation databaseOperation, String dept_name,String building, float lat, float lon){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(DEPT_NAME,dept_name);
        row.put(BUILDING_NAME,building);
        row.put(LATTITUDE,lat);
        row.put(LONGITUDE,lon);
        long k = database.insert(LOCATION_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public void insertInstitute(DatabaseOperation databaseOperation, String name,String email){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(INSTITUTE_NAME,name);
        row.put(EMAIL,email);
        long k = database.insert(INSTITUTE_TABLE,null,row);
        Log.d("Database Operation","One Row inserted");
    }

    public Cursor getData(DatabaseOperation databaseOperation,String department){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {NAME,DESIGNATION,PHONE1,PHONE2,EMAIL1,EMAIL2};
        String selection = SUB_DIVISION+" like ?";
        String[] selectionArgs = {department};
        data = database.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        return data;
    }

    public void bookmark(DatabaseOperation databaseOperation,String name,String designation,String phone,String email){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String selection = NAME+" like ? and "+DESIGNATION+" like ? and "+PHONE1+" like ? and "+EMAIL1+" like ?";
        String[] args = {name,designation,phone,email};
        ContentValues row = new ContentValues();
        row.put(BOOKMARK,1);
        database.update(TABLE_NAME,row,selection,args);
    }

    public boolean isBookmarked(DatabaseOperation databaseOperation,String name,String designation,String phone,String email){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String selection = NAME+" like ? and "+DESIGNATION+" like ? and "+PHONE1+" like ? and "+EMAIL1+" like ?";
        String[] args = {name,designation,phone,email};
        String[] column = {BOOKMARK};
        Cursor result = database.query(TABLE_NAME,column,selection,args,null,null,null);
        result.moveToFirst();
        if(result.getInt(0)==0)
            return false;
        else
            return true;
    }

    public Cursor getBookmarks(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {NAME,DESIGNATION,PHONE1,EMAIL1,SUB_DIVISION};
        String selection = BOOKMARK+" like ?";
        String[] args = {String.valueOf(1)};
        data = database.query(TABLE_NAME,columns,selection,args,null,null,null);
        return data;
    }

    public void deleteBookmark(DatabaseOperation databaseOperation,String name,String designation,String phone,String email){
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String selection = NAME+" like ? and "+DESIGNATION+" like ? and "+PHONE1+" like ? and "+EMAIL1+" like ?";
        String[] args = {name,designation,phone,email};
        ContentValues row = new ContentValues();
        row.put(BOOKMARK,0);
        database.update(TABLE_NAME,row,selection,args);
    }

    public Cursor getHall(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {HALL_NAME};
        data = database.query(HALL_TABLE,columns,null,null,null,null,null);
        return data;
    }

    public Cursor getResearch(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {DEPT_NAME};
        data = database.query(RESEARCH_TABLE,columns,null,null,null,null,null);
        return data;
    }

    public Cursor getOthers(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {DEPT_NAME};
        data = database.query(OTHERS_TABLE,columns,null,null,null,null,null);
        return data;
    }

    public Cursor getOffice(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {OFFICE_NAME};
        data = database.query(OFFICE_TABLE,columns,null,null,null,null,null);
        return data;
    }


    public Cursor getDepartment(DatabaseOperation databaseOperation, String faculty){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {DEPT_NAME,EMAIL};
        String selection = FACULTY_NAME+" like ?";
        String selectionParameter[]={faculty};
        data = database.query(DEPT_TABLE,columns,selection,selectionParameter,null,null,null);
        return data;
    }

    public Cursor getLocation(DatabaseOperation databaseOperation, String department){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {BUILDING_NAME,LATTITUDE,LONGITUDE};
        String selection = DEPT_NAME+" like ?";
        String selectionParameter[]={department};
        data = database.query(LOCATION_TABLE,columns,selection,selectionParameter,null,null,null);
        return data;
    }

    public Cursor getAllDepartmentWithLocation(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {DEPT_NAME, BUILDING_NAME};
        data = database.query(LOCATION_TABLE,columns,null,null,null,null,null);
        return data;
    }

    public Cursor getFaculty(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {FACULTY_NAME};
        data = database.query(FACULTY_TABLE,columns,null,null,null,null,null);
        return data;
    }

    public Cursor getInstitute(DatabaseOperation databaseOperation){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {INSTITUTE_NAME,EMAIL};
        data = database.query(INSTITUTE_TABLE,columns,null,null,null,null,null);
        return data;
    }


    public void deleteAllData(DatabaseOperation databaseOperation){
        SQLiteDatabase database =databaseOperation.getWritableDatabase();
        database.delete(TABLE_NAME,null,null);
        database.delete(FACULTY_TABLE,null,null);
        database.delete(DEPT_TABLE,null,null);
        database.delete(INSTITUTE_TABLE,null,null);
        database.delete(OFFICE_TABLE,null,null);
        database.delete(HALL_TABLE,null,null);
        database.delete(LOCATION_TABLE,null,null);
        database.delete(RESEARCH_TABLE,null,null);
        database.delete(OTHERS_TABLE,null,null);
    }

    public Cursor getAllSubDivision(DatabaseOperation databaseOperation){
        SQLiteDatabase database =databaseOperation.getWritableDatabase();
        Cursor data = database.rawQuery("select distinct "+SUB_DIVISION+" from "+TABLE_NAME,null);
        return data;
    }

    public Cursor findTeacher(DatabaseOperation databaseOperation,String name,String department){
        Cursor data;
        SQLiteDatabase database = databaseOperation.getWritableDatabase();
        String[] columns = {NAME,DESIGNATION,PHONE1,EMAIL1,SUB_DIVISION};
        String selection = NAME+" like ? and "+SUB_DIVISION+" like ?";
        String[] args = {"%"+name+"%","%"+department+"%"};
        data = database.query(TABLE_NAME,columns,selection,args,null,null,null);
        return data;
    }
}
