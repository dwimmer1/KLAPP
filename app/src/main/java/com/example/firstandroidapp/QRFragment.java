package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRFragment extends Fragment {

    public QRFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_r, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button scanButton = view.findViewById(R.id.scan_btn);

        scanButton.setOnClickListener(v -> startQRScan());
    }

    private void startQRScan() {
        //new IntentIntegrator(getActivity()).initiateScan();
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setPrompt("");
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String scanContent = result.getContents();
            if(scanContent != null) { //if scanContent is in Database
                Log.d("Content: ", scanContent);
                //Open Chatroom to the "Klettersteig"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }*/
}