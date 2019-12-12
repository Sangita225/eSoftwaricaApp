package com.example.esoftwaricaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.esoftwaricaapp.ui.home.HomeFragment;

public class Login extends AppCompatActivity {

   private EditText etusername,etpassword;
    private Button btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etpassword);
        btnlogin=findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etusername.getText().toString().equals("softwarica") && etpassword.getText().toString().equals("coventry")) {
                    Toast.makeText(getApplicationContext(),"Loginsuccessful",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(),"invalid username or password",Toast.LENGTH_SHORT).show();
                    etusername.setText("");
                    etpassword.setText("");

                }

            }
        });

    }
}
