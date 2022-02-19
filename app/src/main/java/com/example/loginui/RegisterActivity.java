package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DB = new DBHelper(this);

        EditText user = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        EditText rePassword = findViewById(R.id.validatePassword);
        Button signUp= findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getText().toString();
                String pass = password.getText().toString();
                String rePass = rePassword.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(rePass) ){
                    Toast.makeText(RegisterActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else {
                     if (pass.equals(rePass)){
                         Boolean userExists = DB.checkUsername(username);
                         if (userExists == false){
                             Boolean created = DB.insertData(username, pass);

                             if (created) {
                                 Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                 startActivity(intent);
                             } else {
                                 Toast.makeText(RegisterActivity.this, "Register error", Toast.LENGTH_SHORT).show();
                             }

                         }
                         else {
                             Toast.makeText(RegisterActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();
                         }
                     }
                     else {
                         Toast.makeText(RegisterActivity.this, "The passwords are not equals", Toast.LENGTH_SHORT).show();
                     }
                }
            }
        });
    }
}