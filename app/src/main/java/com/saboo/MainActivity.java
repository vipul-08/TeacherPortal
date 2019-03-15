package com.saboo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView mainTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mainTest = findViewById(R.id.mainTest);

        mainTest.setText(mAuth.getCurrentUser().getUid()+"\n"+mAuth.getCurrentUser().getEmail());
    }
}
