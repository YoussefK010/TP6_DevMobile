package com.example.tp6_devmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNom, etPrix;
    Button btnAjouter;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = findViewById(R.id.etNom);
        etPrix = findViewById(R.id.etPrix);
        btnAjouter = findViewById(R.id.btnAjouter);
        helper = new Helper(this);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = etNom.getText().toString().trim();
                String prixStr = etPrix.getText().toString().trim();

                if (nom.isEmpty() || prixStr.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Veuillez remplir tous les champs",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                helper.insertProduct(new Produit(nom, Double.parseDouble(prixStr)));
                Toast.makeText(MainActivity.this, "Produit ajouté",
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, ListeProduit.class));
            }
        });
    }
}