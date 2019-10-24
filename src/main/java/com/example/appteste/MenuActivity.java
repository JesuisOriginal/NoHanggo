package com.example.appteste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_team);
        mAuth = FirebaseAuth.getInstance();
        final ImageButton lapaBtn = findViewById(R.id.btnLapa);
        final ImageButton gordaBtn = findViewById(R.id.gordaBtn);


        lapaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(MenuActivity.this, "Laposo.", Toast.LENGTH_SHORT).show();
            }
        });

        gordaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this, "Otaku Fudido que gosta de SAO", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
