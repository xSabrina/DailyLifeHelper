package com.example.rober.dailylifehelper.RecipeList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeAsyncTask extends AsyncTask <String, Integer, Void> {

    private ProgressDialog progressDialog;
    private HttpURLConnection httpURLConnection = null;
    private String result = "";
    private String line = "";
    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private String recipeUrl = "https://github.com/LeaVerou/forkgasm/blob/master/recipes.json";

    //debug
    private final String LOG_TAG = "RecipeAsync-TAG";

    /*
        ever method will be executed :)
        httpConn gives wrong format
     */
    @Override
    protected void onPreExecute(){
        // toDo setup progressDialog
        Log.d(LOG_TAG, "onPreExecute done");
        super.onPreExecute();
    }

    /*
        toDo replace params w/ stringUrl
        create json w/ getJsonFromUrl-method
        add jsons to db w/ addJsonsToDb-method
        @param params desired url-string
     */

    @Override
    protected Void doInBackground(String... params) {
        String recipeUrl = "https://github.com/LeaVerou/forkgasm/blob/master/recipes.json";
        JSONObject jsonObject = getJSONFromUrl(recipeUrl);
        addJsonToDb(jsonObject);

        Log.d(LOG_TAG, "doInBackground done");

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        // toDo add methods
        Log.d(LOG_TAG, "onProgressUpdate done");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void v){
        //toDo add methods?
        Log.d(LOG_TAG, "onPostExecute done");
        super.onPostExecute(v);
    }

    /*
         create new Url with url-string
         create new httpURLConnection w/ url
         set time values for httpURLConnection
         get response-code
         create new bufferedReader w/ inputStream and utf-8 encoding
         create new stringBuilder
         append bufferedReader to stringBuilder
         create json w/ stringBuilder
         @param urlString string of url
         @return JSONObject jsonObject created from url
    */

    private JSONObject getJSONFromUrl(String urlString){
        JSONObject jsonObject = new JSONObject();
        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // toDo change time-values
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(4000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            // toDo use response-code
            int response = httpURLConnection.getResponseCode();
            httpURLConnection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
            stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            jsonObject = new JSONObject(stringBuilder.toString());
            Log.d(LOG_TAG, "json: " + jsonObject);
        }catch (Exception e){
            Log.d(LOG_TAG, e.toString());
        }
        Log.d(LOG_TAG, "getJSONFromUrl finished");
        return jsonObject;
    }

    /*
        create new jsonArray  w/ json from doInBackground
        add recipes from array in db
        toDo fix exception: Value <!DOCTYPE of type java.lang.String cannot be converted to JSONArray
        toDo fix exception: org.json.JSONException: No value for recipe
        @param jsonObject object to save into db
     */

    private void addJsonToDb(JSONObject jsonObject){
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("recipe");
            Log.d(LOG_TAG, "starting loop...");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject newJsonObject = jsonArray.getJSONObject(i);
                // toDo add as recipe in db
                String recipeName = newJsonObject.getString("name");
                //for-loop doesn't work
                Log.d(LOG_TAG, "Contains: " + recipeName);
            }
            Log.d(LOG_TAG, "loop finished!");
        }catch (Exception e){
            Log.d(LOG_TAG, "addJsonsToDb ex: " + e);
        }
        Log.d(LOG_TAG, "addJsonsToDb done");
    }
}
