package com.example.firstandroidapp;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//DataFetcher isntances of database
public class DataFetcherKletterSteig extends AsyncTask<String, Void, List<KlappData>> {

    private OnDataFetchedListener listener;

    public DataFetcherKletterSteig(OnDataFetchedListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<KlappData> doInBackground(String... strings) {
        List<KlappData> KlappDataList = new ArrayList<>();
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
                    String name = jsonObjekt.getString("name");
                    String description = jsonObjekt.getString("description");
                    String risetime = jsonObjekt.getString("risetime");
                    String descenttime = jsonObjekt.getString("descenttime");
                    String dateaccessible = jsonObjekt.getString("dateaccessible");
                    String dateAccCreated = jsonObjekt.getString("datenotaccessible");
                    String status = jsonObjekt.getString("status");
                    String startingpoint = jsonObjekt.getString("startingpoint");
                    String endpoint = jsonObjekt.getString("endpoint");
                    String federalstate = jsonObjekt.getString("federalstate");
                    String difficulty = jsonObjekt.getString("difficulty");
                    String linktowebsite = jsonObjekt.getString("linktowebsite");
                    String userid = jsonObjekt.getString("userid");
                    String datecreated = jsonObjekt.getString("datecreated");
                  //  String qrCode = jsonObjekt.getString("qr_code");

                    // Erstelle ein Klapp-Objekt und füge es der Liste hinzu
                    KlappData klappData = new KlappData(name,description,risetime,descenttime,dateaccessible,dateAccCreated,status,startingpoint,endpoint,federalstate,difficulty,linktowebsite,userid,datecreated);
                    KlappDataList.add(klappData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return KlappDataList;
    }

    @Override
    protected void onPostExecute(List<KlappData> result) {
        if (listener != null) {
            listener.onDataFetched(result);
        }
    }

    public interface OnDataFetchedListener {
        void onDataFetched(List<KlappData> klappDataList);
    }
}