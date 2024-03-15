package com.example.firstandroidapp;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//DataFetcher isntances of database
public class DataFetcher extends AsyncTask<String, Void, List<UserData>> {

    private OnDataFetchedListener listener;

    public DataFetcher(OnDataFetchedListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<UserData> doInBackground(String... strings) {
        List<UserData> userDataList = new ArrayList<>();
        try {
            String urlStr = strings[0]; // Übergib die URL als Parameter ??????
            URL url = new URL(urlStr);
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();
            inputStream.close();

            // Verarbeite die erhaltenen Daten und erstelle UserData-Objekte
            if (result.length() > 0) {
                JSONArray jsonArray = new JSONArray(result.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObjekt = jsonArray.getJSONObject(i);
                    String userName = jsonObjekt.getString("username");
                    String password = jsonObjekt.getString("password");
                    String email = jsonObjekt.getString("email");
                    String userId = jsonObjekt.getString("user_id");
                    String phoneNumber = jsonObjekt.getString("phone_number");
                    String dateAccCreated = jsonObjekt.getString("date_account_created");
                    String userRole = jsonObjekt.getString("user_role");
                  //  String pfPath = jsonObjekt.getString("profilepicture");


                    // Erstelle ein UserData-Objekt und füge es der Liste hinzu
                    UserData userData = new UserData(userName,password,email,userId,phoneNumber,dateAccCreated,userRole);
                    userDataList.add(userData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDataList;
    }



    @Override
    protected void onPostExecute(List<UserData> result) {
        if (listener != null) {
            listener.onDataFetched(result);
        }
    }

    public interface OnDataFetchedListener {
        void onDataFetched(List<UserData> userDataList);
    }
}