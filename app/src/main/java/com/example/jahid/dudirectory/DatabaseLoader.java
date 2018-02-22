package com.example.jahid.dudirectory;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jahid on 4/11/2017.
 */

public class DatabaseLoader extends AsyncTask<Void,Void,Void> {
    ProgressDialog pDialog;
    String jsonString;
    Context context;

    public DatabaseLoader(Context context){
        this.context = context;
        this.execute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        URL url;
        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_json.php");
            Log.d("Remote Status","Remote Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadData(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_dept.php");
            Log.d("Remote Status","Remote Department Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String data = "";
            while((jsonString=bufferedReader.readLine())!=null){
                data+=jsonString;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            loadDepartment(data.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_institute.php");
            Log.d("Remote Status","Remote Institute Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadInstitute(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_faculty.php");
            Log.d("Remote Status","Remote Faculty Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadFaculty(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_hall.php");
            Log.d("Remote Status","Remote Hall Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadHall(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_office.php");
            Log.d("Remote Status","Remote Office Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadOffice(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_location.php");
            Log.d("Remote Status","Remote Location Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadLocation(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_research.php");
            Log.d("Remote Status","Remote Location Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadResearch(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            url=new URL("http://edirectory.cse.du.ac.bd/App/get_others.php");
            Log.d("Remote Status","Remote Location Connected");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            StringBuilder data = new StringBuilder();
            while((jsonString=bufferedReader.readLine())!=null){
                data.append(jsonString);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            String json =  data.toString().trim();
            loadOthers(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading Data...\nPlease Wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onPostExecute(Void data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Status",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("update",true);
        editor.commit();

        pDialog.dismiss();
    }

    private void loadData(String json){
        String name,engName,division,subdivision,designation,phone1,phone2,email1,email2,shortCode,info;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                engName = JO.getString("name_eng");
                division = JO.getString("division");
                subdivision = JO.getString("sub_division");
                designation = JO.getString("designation");
                phone1 = JO.getString("phone1");
                phone2 = JO.getString("phone2");
                email1 = JO.getString("email1");
                email2 = JO.getString("email2");
                shortCode = JO.getString("short_code");
                info = JO.getString("info");
                databaseOperation.insertData(databaseOperation,name,engName,division,subdivision,designation,phone1,phone2,email1,email2,shortCode,info);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadDepartment(String json){
        String name,email,faculty;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("dept_name");
                email = JO.getString("email");
                faculty = JO.getString("faculty_name");
                databaseOperation.insertDepartment(databaseOperation,name,email,faculty);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadLocation(String json){
        String name,building;
        float lat,lon;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("dept_name");
                building = JO.getString("building");
                lat = Float.valueOf(JO.getString("lattitude"));
                lon = Float.valueOf(JO.getString("longitude"));
                databaseOperation.insertLocation(databaseOperation,name,building,lat,lon);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadInstitute(String json){
        String name,email;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("institute_name");
                email = JO.getString("email");
                databaseOperation.insertInstitute(databaseOperation,name,email);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadFaculty(String json){
        String name;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("faculty_name");
                databaseOperation.insertFaculty(databaseOperation,name);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadHall(String json){
        String name;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("hall_name");
                databaseOperation.insertHall(databaseOperation,name);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadResearch(String json){
        String name;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("dept_name");
                databaseOperation.insertResearch(databaseOperation,name);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadOthers(String json){
        String name;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("office_name");
                databaseOperation.insertOthers(databaseOperation,name);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadOffice(String json){
        String name;
        int count=0;
        try{
            JSONObject jsonObject  =new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("response");
            DatabaseOperation databaseOperation = new DatabaseOperation(context);
            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("office_name");
                databaseOperation.insertOffice(databaseOperation,name);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
