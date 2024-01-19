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

/*
        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


 */

    }
    public void sendMessage() {
        String message = binding.editTextMessage.getText().toString();
        if (!message.isEmpty()) {
            Log.d("MyApp", "sendMessage clicked");
            addChatMessage("Du: " + message);

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
            // Hier sollte die Logik zum Speichern der Nachricht in der Datenbank stehen
            // Beispiel: Aufruf einer Methode, die die Nachricht in der Datenbank speichert
            saveMessageToDatabase(params[0]);
            return null;
        }

        private void saveMessageToDatabase(String message) {
            // Hier sollte die tatsächliche Logik zum Speichern in der Datenbank stehen
            // In diesem Beispiel wird nur die aktuelle Zeit als Zeitstempel hinzugefügt
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
            String timestamp = dateFormat.format(new Date());

            // Hier können Sie Ihren eigenen Datenbank-Speichercode hinzufügen
            // Beispiel: dbHelper.saveMessage(message, timestamp);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}