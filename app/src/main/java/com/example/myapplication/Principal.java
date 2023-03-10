package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;

public class Principal extends AppCompatActivity {


    TextView userPass, name;
    ImageButton btnTask;
    ImageButton btnContact;
    ImageButton btnNotas;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signOutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        userPass = findViewById(R.id.username);
        String user = getIntent().getStringExtra("username");
        userPass.setText(user);

        btnTask = findViewById(R.id.btn_task);
        btnContact = findViewById(R.id.btn_contact);
        btnNotas= findViewById(R.id.btn_notas);

        btnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, Task.class);
                startActivity(intent);
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, Contacts.class);
                startActivity(intent);
            }
        });
        btnNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, NotasImportantes.class);
                startActivity(intent);
            }
        });

        /*recibo datos google*/
        name = findViewById(R.id.username);
        signOutbtn = findViewById(R.id.signout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            String personName = acct.getDisplayName();
            name.setText(personName);
        }

        signOutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(com.google.android.gms.tasks.Task<Void> task) {
               finish();
               startActivity(new Intent(Principal.this, MainActivity.class));
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == androidx.core.R.id.item1){
            Toast.makeText(this,"Se mostrara el evento", Toast.LENGTH_SHORT).show();
        }else if(id == androidx.core.R.id.item2){
            Toast.makeText(this,"Ayuda", Toast.LENGTH_SHORT).show();
        }else if (id == androidx.core.R.id.item3){
            Toast.makeText(this,"Info", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }

}