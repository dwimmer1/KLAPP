package com.example.firstandroidapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.example.firstandroidapp.databinding.FragmentKlettersteigBinding;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

//Creating klettersetig
public class KletterSteigFragment extends Fragment implements DataFetcherKletterSteig.OnDataFetchedListener {

    private FragmentKlettersteigBinding binding;

    private class SaveDataAsyncTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {
            String name = params[0]; //a
            String description = params[1];
            String risetime = params[2];
            String descenttime = params[3];
            String status = params[4];
            String startingpoint = params[5];
            String federalstate = params[6];
            String difficulty = params[7];
            String datecreated = params[8];

            try {
                // HTTP-Anfrage an die serverseitige API senden
                //URL url = new URL("http://192.168.56.1/Klapp/sendDataKlapp.php");
                URL url = new URL("http://klettersteig-app.at/daten/sendDataKlapp.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String postData = "name=" + URLEncoder.encode(name, "UTF-8") +
                        "&description=" + URLEncoder.encode(description, "UTF-8") +
                        "&risetime=" + URLEncoder.encode(risetime, "UTF-8") +
                        "&descenttime=" + URLEncoder.encode(descenttime, "UTF-8") +
                        "&status=" + URLEncoder.encode(status, "UTF-8") +
                        "&startingpoint=" + URLEncoder.encode(startingpoint, "UTF-8") +
                        "&federalstate=" + URLEncoder.encode(federalstate, "UTF-8") +
                        "&difficulty=" + URLEncoder.encode(difficulty, "UTF-8") +
                        "&datecreated=" + URLEncoder.encode(datecreated, "UTF-8");

                writer.write(postData);
                writer.flush();
                writer.close();
                outputStream.close();

                // Verarbeite die Serverantwort hier (falls erforderlich)
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Erfolgreich gesendet
                } else {
                    // Probleme bei der Verbindung
                }

                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                // Fehlerbehandlung hier
            }

            return null;
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentKlettersteigBinding.inflate(inflater, container, false);

        DataFetcherKletterSteig dataFetcherKlapp = new DataFetcherKletterSteig(this);
        dataFetcherKlapp.execute("http://klettersteig-app.at/daten/getDataKlapp.php");

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(KletterSteigFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

        binding.buttonSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO sollten späda nur leute mit der gewissen berechtigung tun können

                String username = binding.editTextUsername.getText().toString();
                String description = binding.editTextDescription.getText().toString();
                String risetime = binding.editTextRisetime.getText().toString();
                String descenttime = binding.editTextDescenttime.getText().toString();
                String status = binding.editTextStatus.getText().toString();
                String startingpoint = binding.editTextStartingPoint.getText().toString();
                String federalstate = binding.editTextFederalState.getText().toString();
                String difficulty = binding.editTextDifficulty.getText().toString();
                String datecreated = binding.editTextDateCreated.getText().toString();
/*
                try {
                    // HTTP-Anfrage an die serverseitige API senden
                    URL url = new URL("http://192.168.56.1/Klapp/sendDataKlapp.php");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    OutputStream outputStream = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String postData = "username=" + URLEncoder.encode(username, "UTF-8") +
                            "&description=" + URLEncoder.encode(description, "UTF-8") +
                            "&risetime=" + URLEncoder.encode(risetime, "UTF-8") +
                            "&descenttime=" + URLEncoder.encode(descenttime, "UTF-8") +
                            "&status=" + URLEncoder.encode(status, "UTF-8") +
                            "&startingpoint=" + URLEncoder.encode(startingpoint, "UTF-8") +
                            "&federalstate=" + URLEncoder.encode(federalstate, "UTF-8") +
                            "&difficulty=" + URLEncoder.encode(difficulty, "UTF-8") +
                            "&datecreated=" + URLEncoder.encode(datecreated, "UTF-8");

                    writer.write(postData);
                    writer.flush();
                    writer.close();
                    outputStream.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                 */
                new SaveDataAsyncTask().execute(username, description, risetime, descenttime, status, startingpoint, federalstate, difficulty, datecreated);
            }

        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onDataFetched(List<KlappData> klappDataList) {

/*
        if (!userDataList.isEmpty()) {

        }

 */
    }
}