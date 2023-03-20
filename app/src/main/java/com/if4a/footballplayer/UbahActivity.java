package com.if4a.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etnama, etnomor, etklub;
    private Button btnUbah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etnama = findViewById(R.id.et_nama);
        etnomor = findViewById(R.id.et_nomor);
        etklub = findViewById(R.id.et_klub);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent varIntent = getIntent();
        String id = varIntent.getStringExtra("varID");
        String Nama = varIntent.getStringExtra("varNama");
        String Nomor = varIntent.getStringExtra("varNomor");
        String Klub = varIntent.getStringExtra("varKlub");

        etnama.setText(Nama);
        etnomor.setText(Nomor);
        etklub.setText(Klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama, getNomor, getKlub;
                getNama =etnama.getText().toString();
                getNomor = etnomor.getText().toString();
                getKlub = etklub.getText().toString();
                if (getNama.trim().equals("")) {
                    etnama.setError("Nama tidak boleh kosong");
                } else if (getNomor.trim().isEmpty()) {
                    etnomor.setError("Nomor Punggung tidak boleh kosong");
                } else if (getKlub.trim().isEmpty()) {
                    etklub.setError("Klub tidak boleh kosong");
                } else {
                    long eks = myDB.ubahPlayer(id, getNama, getNomor, getKlub);

                    if (eks == -1){
                        Toast.makeText(UbahActivity.this, "Gagal Mengubah Data", Toast.LENGTH_SHORT).show();
                        etnama.requestFocus();
                    }
                    else {
                        Toast.makeText(UbahActivity.this, "Berhasil Mengubah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }
}