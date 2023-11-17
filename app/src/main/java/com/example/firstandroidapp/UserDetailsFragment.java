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

import java.util.List;

public class UserDetailsFragment extends Fragment implements DataFetcher.OnDataFetchedListener {

    private FragmentUserdataBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentUserdataBinding.inflate(inflater, container, false);

        // Setze die abgerufenen Daten in die TextViews
        /*
        binding.textViewUsername.setText("Username: " + username);
        binding.textViewEmail.setText("Email: " + email);
        binding.textViewPassword.setText("Password: " + password);
        binding.textViewUserId.setText("UserID: " + userID);
         */
        DataFetcher dataFetcher = new DataFetcher(this);
        dataFetcher.execute("http://192.168.56.1/Klapp/getData.php");

        return binding.getRoot();

    }

    @Override
    public void onDataFetched(List<UserData> userDataList) {
        // TODO gehört noch gemacht das der benutzer welcher eingeloggt ist angezeigt wird

        if (!userDataList.isEmpty()) {
            UserData firstUser = userDataList.get(0); // Beispiel: Nehme den ersten Benutzer
            binding.textViewUsername.setText("Username: " + firstUser.getUsername());
            binding.textViewEmail.setText("Email: " + firstUser.getEmail());
            binding.textViewPassword.setText("Password: " + firstUser.getPassword());
            // Setze hier die weiteren TextViews für andere Benutzerdaten
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        textViewUsername = view.findViewById(R.id.);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        //tex = view.findViewById(R.id.textViewPassword);
        textViewUserId = view.findViewById(R.id.textViewUserId);
        textViewPhoneNumber = view.findViewById(R.id.textViewPhoneNumber);
        textViewDateAccountCreated = view.findViewById(R.id.textViewDateAccountCreated);
        textViewUserRole = view.findViewById(R.id.textViewUserRole);
        loadUserDataFromDatabase();

        */

        binding.BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(UserDetailsFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}