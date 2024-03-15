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

    String jsonString = "[{\"username\":\"ManuelRester\",\"email\":\"manuel@rester.test\",\"password\":\"test123\",\"user_id\":\"1\",\"profile_picture\":\"0\",\"phone_number\":\"0664 123456789\",\"date_account_created\":\"10.11.2023\",\"user_role\":\"Admin\"},{\"username\":\"DanielWimmer\",\"email\":\"daniel@wimmer.test\",\"password\":\"test123\",\"user_id\":\"2\",\"profile_picture\":\"0\",\"phone_number\":\"0664 987654321\",\"date_account_created\":\"10.11.2023\",\"user_role\":\"User\"},{\"username\":\"EliasMiklautsch\",\"email\":\"elias@miklautsch.test\",\"password\":\"elistinkt\",\"user_id\":\"3\",\"profile_picture\":\"0\",\"phone_number\":\"0664 741852963\",\"date_account_created\":\"10.11.2023\",\"user_role\":\"User\"},{\"username\":\"SemirMedzikovic\",\"email\":\"semir@medzikovic.test\",\"password\":\"test123\",\"user_id\":\"4\",\"profile_picture\":\"0\",\"phone_number\":\"0664 963852741\",\"date_account_created\":\"10.11.2023\",\"user_role\":\"User\"},{\"username\":\"admin\",\"email\":\"admin@test\",\"password\":\"admin\",\"user_id\":\"5\",\"profile_picture\":\"0\",\"phone_number\":\"525253\",\"date_account_created\":\"10.11.2023\",\"user_role\":\"Admin\"}]";

    List<String> userNames = new ArrayList<>();
    List<String> passwords = new ArrayList<>();

    List<String> userRoles = new ArrayList<>();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        DatenAbrufenTask datenAbrufenTask = new DatenAbrufenTask();
        // CHANGE TO RIGHT IP ADRESS
        datenAbrufenTask.execute("http://klettersteig-app.at/daten/getData.php");

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.supportBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    LoggendUserSingleton.getInstance().setUserNames(enteredUserName);

                    try {
                      String userRole = getUserRoleFromLoggedinUser(jsonString, enteredUserName);
                      LoggendUserSingleton.getInstance().setUserRole(userRole);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    Log.d("LoginIdentifier", "Success");

                    //Saves currentLoggedInUser in Singelton
                    LoggendUserSingleton.getInstance().setUserNames(enteredUserName);

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
                //String urlStr = "http://192.168.56.1/Klapp/getData.php";
                String urlStr = "https://klettersteig-app.at/daten/getData.php";
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
                Log.d("DatenAbrufenTask", "Serverantwort: " + result);
                loggeDaten(result);
                //   getUserRoleFromLoggedinUser(result);
            }
        }

        private void loggeDaten(String jsonDaten) {
            try {
                JSONArray jsonArray = new JSONArray(jsonDaten);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObjekt = jsonArray.getJSONObject(i);
                    String userName = jsonObjekt.getString("username");
                    String password = jsonObjekt.getString("password");
                    String userRole = jsonObjekt.getString("user_role");

                    // Speichere Benutzernamen und Passwörter in den Listen
                    userNames.add(userName);
                    passwords.add(password);
                    userRoles.add(userRole);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    public String getUserRoleFromLoggedinUser(String jsonDaten, String userName) throws JSONException {
        String userRole = null;
        JSONArray jsonArray = new JSONArray(jsonDaten);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String jsonUsername = jsonObject.getString("username");

            if (userName.equals(jsonUsername)) {
                userRole = jsonObject.getString("user_role");
                break; // Beende die Schleife, wenn der Benutzer gefunden wurde
            }
        }

        return userRole;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}