package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    EditText user, password;
    Button loginbtn;
    ImageButton googleBtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleBtn = findViewById(R.id.google_btn);

        user = findViewById(R.id.txtuser);
        password = findViewById(R.id.txtpassword);
        loginbtn = findViewById(R.id.btn_login);
        Context context = getApplicationContext();
        CharSequence text = "Incorrect user ó password";
        CharSequence text2 = "Successful login";

        int duration = Toast.LENGTH_LONG;

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().equals("Sebas") && password.getText().toString().equals("sebas123")) {
                    Toast toast = Toast.makeText(context, text2, duration);
                    toast.show();
                    Intent intent = new Intent(MainActivity.this, Principal.class);
                    intent.putExtra("username", user.getText().toString());
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
     /*Google Login*/

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null){
            navigateToSecondActivity();
        }
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
         void signIn() {
            Intent signIntent = gsc.getSignInIntent();
            startActivityForResult(signIntent, 1000);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1000){
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try{
                    task.getResult(ApiException.class);
                }catch (ApiException e){
                    Toast.makeText(getApplicationContext(), "Algo ha ocurrido",Toast.LENGTH_SHORT).show();
                }
            }
        }

            void navigateToSecondActivity(){
            Intent intent = new Intent(MainActivity.this, Principal.class);
            startActivity(intent);
            }

     /*
     *FIn
     */




    }


