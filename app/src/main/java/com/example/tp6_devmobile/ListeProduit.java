package com.example.tp6_devmobile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ListeProduit extends AppCompatActivity {

    ListView listView;
    Helper helper;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_produit);

        listView = findViewById(R.id.listView);
        helper = new Helper(this);

        Cursor cursor = helper.getAllProducts();

        String[] from = {Helper.COL_ID, Helper.COL_NOM, Helper.COL_PRIX};
        int[] to = {R.id.tvId, R.id.tvNom, R.id.tvPrix};
        adapter = new SimpleCursorAdapter(this, R.layout.item_produit,
                cursor, from, to, 0);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ListeProduit.this, DetailProduit.class);
                intent.putExtra("id", (int) id); // id = valeur de la colonne _id
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.changeCursor(helper.getAllProducts());
    }
}