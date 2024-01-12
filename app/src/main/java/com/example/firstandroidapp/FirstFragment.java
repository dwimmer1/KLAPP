package com.example.firstandroidapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstandroidapp.databinding.FragmentFirstBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding; // binding muss immer so heißen wie xml in CamelCase und Binding am schluss
    private TextView datenAnzeige;
    ResultSet results = null;

    List<String> userNames = new ArrayList<>();
    List<String> passwords = new ArrayList<>();



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        DatenAbrufenTask datenAbrufenTask = new DatenAbrufenTask();
        // CHANGE TO RIGHT IP ADRESS
        datenAbrufenTask.execute("http://192.168.56.1/Klapp/getData.php");

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.supportBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Support Emai Action Button
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SupportFragment);

            }
        });


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredUserName = binding.usernameEditText.getText().toString();
                String enteredPassword = binding.passwordEditText.getText().toString();

                // Prüfe, ob eingegebener Benutzername und Passwort in der Liste der JSON-Daten vorhanden sind
                if (userNames.contains(enteredUserName) && passwords.contains(enteredPassword)) {
                    Log.d("LoginIdentifier", "Success");

                    Toast.makeText(getContext(), "Erfolgreich Eingeloggt", Toast.LENGTH_LONG).show();
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                } else {
                    Toast.makeText(getContext(), "Ungültige Eingabe", Toast.LENGTH_LONG).show();
                    Log.d("Wrong Password/Username", "Wrong Credentials");
                }


            }
        });

        binding.tempSwitchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                //.navigate(R.id.action_FirstFragment_to_UserDetailFragment);
            }
        });
        binding.tempSwitchBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_KletterSteig);
                //.navigate(R.id.action_FirstFragment_to_UserDetailFragment);
            }
        });
        binding.chatRoomBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ChatRoomFragment);
                //.navigate(R.id.action_FirstFragment_to_UserDetailFragment);
            }
        });
    }
    private class DatenAbrufenTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                // Die URL aus den Parametern extrahieren
                String urlStr = "http://192.168.56.1/Klapp/getData.php";
                URL url = new URL(urlStr);

                // Eine Verbindung zur URL herstellen und den Inhalt lesen
                InputStream inputStream = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }


                // Schließe den BufferedReader und die InputStream
                reader.close();
                inputStream.close();

                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                // Hier kannst du den PHP-Code in der result-Variable verwenden
                // Achte darauf, dass dies aus Sicherheitsgründen nicht empfohlen ist
                // PHP-Code sollte normalerweise auf einem Server ausgeführt werden
                Log.d("DatenAbrufenTask", "Serverantwort: " + result);
                loggeDaten(result);
            }
        }
        private void loggeDaten(String jsonDaten) {
            try {
                JSONArray jsonArray = new JSONArray(jsonDaten);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObjekt = jsonArray.getJSONObject(i);
                    String userName = jsonObjekt.getString("username");
                    String password = jsonObjekt.getString("password");

                    // Speichere Benutzernamen und Passwörter in den Listen
                    userNames.add(userName);
                    passwords.add(password);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}