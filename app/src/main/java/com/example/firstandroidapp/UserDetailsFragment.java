package com.example.firstandroidapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstandroidapp.databinding.FragmentThirdBinding;
import com.example.firstandroidapp.databinding.FragmentUserdataBinding;

public class UserDetailsFragment extends Fragment {

    private FragmentUserdataBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentUserdataBinding.inflate(inflater, container, false);
        // Hier sollten Daten aus der Datenbank abgerufen und in die TextViews eingefügt werden
        String username = "BeispielUsername";
        String email = "beispiel@email.com";
        String password = "Passwort123";


        // Setze die abgerufenen Daten in die TextViews
        binding.textViewUsername.setText("Username: " + username);
        binding.textViewEmail.setText("Email: " + email);
        binding.textViewPassword.setText("Password: " + password);
        binding.textViewUserId.setText("UserID: " + userID);


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewUsername = view.findViewById(R.id.);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        //tex = view.findViewById(R.id.textViewPassword);
        textViewUserId = view.findViewById(R.id.textViewUserId);
        textViewPhoneNumber = view.findViewById(R.id.textViewPhoneNumber);
        textViewDateAccountCreated = view.findViewById(R.id.textViewDateAccountCreated);
        textViewUserRole = view.findViewById(R.id.textViewUserRole);
        loadUserDataFromDatabase();


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(UserDetailsFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_SecondFragment);
            }
        });
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(UserDetailsFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });
    }


    // Hier könntest du die Methoden onCreate und onCreateView implementieren

    // Methode zur Anzeige der Benutzerdaten basierend auf Datenbankabfrage
    private void loadUserDataFromDatabase() {
        // Code zum Abrufen der Benutzerdaten aus der Datenbank

        // Angenommen, die Daten werden in userObjekt gespeichert
        // (ersetze dies mit deiner tatsächlichen Logik zum Abrufen von Benutzerdaten)
        String username = userObjekt.getUsername();
        String email = userObjekt.getEmail();
        // Weitere Benutzerdaten aus userObjekt abrufen

        // Setze die abgerufenen Daten in die TextViews oder UI-Elemente
        textViewUsername.setText("Username: " + username);
        textViewEmail.setText("Email: " + email);
        // Weitere TextViews mit anderen Benutzerdaten füllen
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}