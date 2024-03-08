package com.example.firstandroidapp;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.example.firstandroidapp.databinding.FragmentFirstBinding;
import com.example.firstandroidapp.databinding.FragmentSecondBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    String userRoles;

    public SecondFragment() {
        // Erforderlicher leerer öffentlicher Konstruktor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //   return inflater.inflate(R.layout.fragment_second, container, false);

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        SecondFragment.DatenAbrufenTask datenAbrufenTask = new SecondFragment.DatenAbrufenTask();
        // CHANGE TO RIGHT IP ADRESS
        datenAbrufenTask.execute("http://klettersteig-app.at/daten/getData.php");

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("admin?", userRoles);
        if ("Admin".equals(userRoles)) {
            binding.test.setVisibility(View.VISIBLE);
        } else {
            binding.test.setVisibility(View.GONE);
        }


        BottomNavigationView bottomNavigation = view.findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_qr:
                        loadFragment(new QRFragment());
                        return true;
                    case R.id.action_account:
                        loadFragment(new UserDetailsFragment());
                        return true;

                    case R.id.action_settings:
                        if ("Admin".equals(userRoles)) {
                            loadFragment(new SettingsFragment());
                            return true;
                        } else {
                            Toast.makeText(getContext(), "Nur Administratoren haben Zugriff auf die Einstellungen", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                }
                return false;
            }
        });

        // Lade standardmäßig das QRFragment beim Start
        loadFragment(new QRFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
                    String userRole = jsonObjekt.getString("user_role");


                    // Speichere roles in den Listen
                    userRoles = userRole;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}