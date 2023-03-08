package com.if4a.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etnama, etnomor, etklub;
    private Button btnTambah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        etnama = findViewById(R.id.et_nama);
        etnomor = findViewById(R.id.et_nomor);
        etklub = findViewById(R.id.et_klub);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, nomor, klub;

                nama = etnama.getText().toString();
                nomor = etnomor.getText().toString();
                klub = etklub.getText().toString();

                if (nama.trim().equals("")){
                    etnama.setError("Nama tidak boleh kosong");
                }
                else if (nomor.trim().isEmpty()){
                    etnomor.setError("Nomor Punggung tidak boleh kosong");
                }
                else if (klub.trim().isEmpty()) {
                    etklub.setError("Klub tidak boleh kosong");
                }
                else {
                    long eks = myDB.tambahPlayer(nama, nomor, klub);

                    if (eks == -1){
                        Toast.makeText(TambahActivity.this, "Gagal menambah data", Toast.LENGTH_SHORT).show();
                        etnama.requestFocus();
                    }
                    else {
                        Toast.makeText(TambahActivity.this, "Berhasil menambah data", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }
            }
        });
    }
}