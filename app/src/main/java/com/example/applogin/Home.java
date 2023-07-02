package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applogin.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    ListView listadousuarios;

    ArrayList<String> listItem;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseHelper= new DatabaseHelper(this);

        listItem= new ArrayList<>();

        listadousuarios = findViewById(R.id.listadousuarios);

        recorrerDatos();
    }

    private void recorrerDatos(){
        Cursor cursor=databaseHelper.ConsultarUsuarios();
        if (cursor.getCount()==0){
            Toast.makeText(Home.this,"No se encontraron datos", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
            listadousuarios.setAdapter(adapter);
        }
    }
}