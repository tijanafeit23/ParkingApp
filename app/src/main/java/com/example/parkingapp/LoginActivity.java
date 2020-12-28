package com.example.parkingapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button singR,btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        username=(EditText)findViewById(R.id.log_name);
        password=(EditText)findViewById(R.id.log_pass);
        btnlogin=(Button)findViewById(R.id.buttonLogin);
        singR=(Button) findViewById(R.id.button);
        DB=new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this,"Сите полиња се задолжителни",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkupuserpass=DB.checkusernamepassword(user,pass);
                    if (checkupuserpass==true){
                        Toast.makeText(LoginActivity.this,"Успешно се логиравте",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Cities.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Мора да се регистрирате",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        singR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Reg.class);
                startActivity(intent);

            }
        });
    }
}