package com.example.tp6_devmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailProduit extends AppCompatActivity {

    EditText etNom, etPrix;
    Button btnMod, btnSupp;
    Helper helper;
    Produit produit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produit);

        etNom = findViewById(R.id.etNom);
        etPrix = findViewById(R.id.etPrix);
        btnMod = findViewById(R.id.btnMod);
        btnSupp = findViewById(R.id.btnSupp);
        helper = new Helper(this);

        final int id = getIntent().getIntExtra("id", -1);
        produit = helper.getOneProduct(id);

        if (produit == null) {
            Toast.makeText(this, "Produit introuvable", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        etNom.setText(produit.getNom());
        etPrix.setText(String.valueOf(produit.getPrix()));

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produit.setNom(etNom.getText().toString().trim());
                produit.setPrix(Double.parseDouble(etPrix.getText().toString().trim()));
                helper.updateProduct(produit);
                Toast.makeText(DetailProduit.this, "Produit modifié",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteProduct(id);
                Toast.makeText(DetailProduit.this, "Produit supprimé",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}