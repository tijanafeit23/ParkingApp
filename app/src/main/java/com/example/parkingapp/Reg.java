package com.example.parkingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Reg extends AppCompatActivity {
    EditText username,password,repassword;
    Button singup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        username=(EditText) findViewById(R.id.reg_name);
        password=(EditText) findViewById(R.id.reg_pass);
        repassword=(EditText) findViewById(R.id.re_reg_pass);
        singup=(Button) findViewById(R.id.buttonRegister);
        DB= new DBHelper(this);


        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                if(user.equals("")||pass.equals("")|| repass.equals(""))
                    Toast.makeText(Reg.this,"Сите полиња се задолжителни",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(Reg.this,"Успешна регистрација",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Reg.this,"Неуспешна регистрација",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Reg.this,"Корисникот веќе постои,ве молиме логирајте се",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Reg.this,"Погрешно внесени лозинки",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}