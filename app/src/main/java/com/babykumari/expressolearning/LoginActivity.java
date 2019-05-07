package com.babykumari.expressolearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserName;
    private EditText edtUserPass;
    private Button btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = findViewById(R.id.edt_username);
        edtUserPass = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        String edtUserNameValue = edtUserName.getText().toString();
        String edtUserPassValue = edtUserPass.getText().toString();
        if(edtUserNameValue.isEmpty()){
           edtUserName.setError("Invalid User Name");
        }
        else if(!edtUserNameValue.equals("admin")){
            edtUserName.setError("Invalid User Name");
        }
        else if(edtUserPassValue.isEmpty()){
            edtUserPass.setError("Invalid Password");
        }
        else if(!edtUserPassValue.equals("admin@123")){
            edtUserPass.setError("Invalid Password");
        }
        else{
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

    }
}
