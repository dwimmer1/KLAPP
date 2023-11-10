package com.example.firstandroidapp;

import android.os.Bundle;
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

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ResultSet results = null;
    public ResultSet dbConnection() throws SQLException {

        final String JdbcUrl = "jdbc:mysql://xserv:3306/klapp";
        final String user = "klapp";
        final String password = "superklapp";

        Connection connection = DriverManager.getConnection(JdbcUrl,user,password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Account");

        while (resultSet.next()){
            System.out.println(resultSet.toString());
            System.out.println(resultSet.getString("username"));
            String DbUsernames = resultSet.getString("username");
            System.out.println(resultSet.getString("email"));
        }


        statement.close();
        resultSet.close();
        connection.close();

        return resultSet;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}