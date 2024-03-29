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

//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

public class QRFragment extends Fragment {

    public QRFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_r, container, false);
    }
/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button scanButton = view.findViewById(R.id.scan_btn);

        scanButton.setOnClickListener(v -> startQRScan());
    }

    private void startQRScan() {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setPrompt("");
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("QRCodeScanner", "Abbruch des Scans");
            } else {
                String qrCodeValue = result.getContents();
                Log.d("QRCodeScanner", "QR-Code-Wert: " + qrCodeValue);

                String uuid = extractUUIDFromLink(qrCodeValue);

                if (uuid != null) {
                    Log.d("Link2FindUUIDEasier", "https://www.qr-code-generator.com/x");
                    Log.d("QRCodeScanner", "Extrahierte UUID: " + uuid);

                    // DB abfragen wegen UUID und Klettersteig basierend darauf laden


                } else {
                    Log.d("QRCodeScanner", "Der gescannte Wert enthält keine UUID.");
                }
            }
        } else {
            Log.d("QRCodeScanner", "Fehler beim Verarbeiten des Ergebnisses.");
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    private String extractUUIDFromLink(String link) {
        String[] parts = link.split("/");
        if (parts.length > 0) {
            return parts[parts.length - 1];
        } else {
            return null;
        }
    }

 */
}
