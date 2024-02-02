package com.example.firstandroidapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroidapp.databinding.FragmentChatroomBinding;
import com.example.firstandroidapp.databinding.FragmentFirstBinding;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomFragment extends Fragment {

    private FragmentChatroomBinding binding; // binding muss immer so heißen wie xml in CamelCase und Binding am schluss
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String timestamp = sdf.format(new Date());

    private class GetMessagesTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                // URL für getDataChat.php anpassen
                //String urlString = "http://192.168.56.1/Klapp/getDataChat.php";
                String urlString = "https://klettersteig-app.at/daten/getDataChat.php";
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // Antworte von der PHP-Seite lesen
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                StringBuilder result = new StringBuilder();
                String line;
                TextView textView = new TextView(requireContext());


                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    textView.append(result);

                }
                binding.chatContainer.addView(textView);

                reader.close();
                inputStream.close();

                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    private class SendMessageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String name = params[0];
                String message = params[1];

                // URL für sendDataChat.php anpassen
                String urlString = "http://192.168.56.1/Klapp/sendDataChat.php";
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String postData = "name=" + URLEncoder.encode(name, "UTF-8") +
                        "&message=" + URLEncoder.encode(message, "UTF-8") +
                        "&timestamp=" + URLEncoder.encode(String.valueOf(timestamp), "UTF-8");

                writer.write(postData);
                writer.flush();
                writer.close();
                outputStream.close();

                // Antworte von der PHP-Seite lesen
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                reader.close();
                inputStream.close();

                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }



        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d("Message", result + " slpit ");
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentChatroomBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSend.setOnClickListener(v -> sendMessage());

        GetMessagesTask getMessagesTask = new GetMessagesTask();
        getMessagesTask.execute();

    }

    public void sendMessage() {
        String message = binding.editTextMessage.getText().toString();

        if (!message.isEmpty()) {
            Log.d("MyApp", "sendMessage clicked");
            String userName = LoggendUserSingleton.getInstance().getUserNames();

            addChatMessage(timestamp + " " + userName + ":  " + message);

            SendMessageTask sendMessageTask = new SendMessageTask();


            // Mit Singelton
            sendMessageTask.execute(userName, message);

            // Hier die Logik zum Speichern in der Datenbank aufrufen
            new SaveMessageTask().execute(message);

            // Nachrichtenfeld leeren
            binding.editTextMessage.setText("");
        }
    }

    private void addChatMessage(String message) {
        TextView textView = new TextView(requireContext());
        textView.setText(message);
        binding.chatContainer.addView(textView);


        // Scrollen zum neuesten Nachrichtenende
        //binding.chatContainer.post(() -> binding.chatContainer.scrollF.fullScroll(View.FOCUS_DOWN));
    }

    private class SaveMessageTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            saveMessageToDatabase(params[0]);
            return null;
        }

        private void saveMessageToDatabase(String message) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
            String timestamp = dateFormat.format(new Date());


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}