package fr.dansebag.partiel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView usernameView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usernameView = findViewById(R.id.usernameView);

        SharedPreferences preferences = getSharedPreferences(MainActivity.class.getSimpleName(), MODE_PRIVATE);

        usernameView.setText(preferences.getString("username", ""));
    }
}