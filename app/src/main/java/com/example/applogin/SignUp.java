package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.applogin.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        databaseHelper= new DatabaseHelper(this);
        binding.buttonguardar.setOnClickListener(v->{
            String nombrecompleto = binding.editTextname.getText().toString();
            String username = binding.editTextusenamesp.getText().toString();
            String password = binding.editTextpasswordsp.getText().toString();
            String passwordc = binding.editTextpasswordc.getText().toString();

            if (nombrecompleto.equals("")||username.equals("")||password.equals("")||passwordc.equals("")){
                Toast.makeText(SignUp.this,"Todos los campos son Obligatorios", Toast.LENGTH_SHORT).show();
            }else{
                if (password.equals(passwordc)){
                    Boolean verificarusuario = databaseHelper.verificarUsername(username);

                    if (!verificarusuario){
                        Boolean insertar= databaseHelper.insertarDatos(username,nombrecompleto,password);
                        if (insertar) {
                            Toast.makeText(SignUp.this,"Usuario Guardado con Exito", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignUp.this,"Error no se pudo Guardar el Usuario", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this,"El Usuario "+username+" ya Existe", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUp.this,"La Contraseña no Coincide", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.buttonvolver.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}