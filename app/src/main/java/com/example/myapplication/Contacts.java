package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnAgregar;
    private ListView mListView;
    private EditText mEditText;
    private List<String> mLista= new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
        mListView = findViewById(R.id.listView);
        mListView.setOnClickListener(this);
        mEditText = findViewById(R.id.etLista);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgregar: String texto = mEditText.getText().toString().trim();
            mLista.add(texto);
            mEditText.getText().clear();
            mAdapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, mLista);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), "Item Clicked: "+i, Toast.LENGTH_SHORT).show();
    }
}