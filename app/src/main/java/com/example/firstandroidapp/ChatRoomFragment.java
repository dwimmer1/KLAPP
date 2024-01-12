package com.example.firstandroidapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstandroidapp.databinding.FragmentChatroomBinding;
import com.example.firstandroidapp.databinding.FragmentFirstBinding;

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
    private TextView datenAnzeige;
    ResultSet results = null;




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

        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Support Emai Action Button
                NavHostFragment.findNavController(ChatRoomFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SupportFragment);

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}