package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.applogin.databinding.ActivityMainBinding;
import com.example.applogin.databinding.ActivitySignUpBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        databaseHelper= new DatabaseHelper(this);
        binding.buttonIngresar.setOnClickListener(v -> {
            String username= binding.editTextUsername.getText().toString();
            String password= binding.editTextPassword.getText().toString();

            if (username.equals("")||password.equals("")){
                Toast.makeText(MainActivity.this,"Todos los campos son Obligatorios", Toast.LENGTH_SHORT).show();
            }else{
                Boolean verificarcontrasena= databaseHelper.verificarContrasena(username, password);

                if (verificarcontrasena){
                    Toast.makeText(MainActivity.this,"Logueado Correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonsignup.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),SignUp.class);
            startActivity(intent);
        });
    }
}