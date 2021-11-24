package com.example.tubespw_mehtravelling.hardware;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tubespw_mehtravelling.databinding.ActivityMainBinding;
import com.example.tubespw_mehtravelling.databinding.ActivityQrscannerMainBinding;


public class QRScannerMain extends AppCompatActivity {
    private ActivityQrscannerMainBinding binding;
    private final ActivityResultLauncher<Intent> cameraResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                try {
                                    Intent intent = result.getData();
                                    String strQRRes =
                                            intent.getStringExtra("QR_RESULT");
                                    String[] res = strQRRes.split(";");

                                    binding.txtWelcome.setText(res[0]);
                                    binding.txtKeterangan.setText(res[1]);
                                } catch (Exception e) {
                                    binding.txtWelcome.setText("");
                                    binding.txtKeterangan.setText("");
                                    Toast.makeText(QRScannerMain.this, "QR CODE TIDAK VALID!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrscannerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnScan.setOnClickListener(v -> {
            cameraResult.launch(new Intent(this, QRScannerActivity.class));
        });
    }
}
