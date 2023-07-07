package fr.dansebag.partiel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<Users> users;
    private ManageSharedPref manageSharedPref;
    private Button play;
    private Button about;
    private Button quit;
    private TextView username;
    private TextView score;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        play = findViewById(R.id.btn_play);
        about = findViewById(R.id.btn_about);
        quit = findViewById(R.id.btn_logout);
        username = findViewById(R.id.tv_name);
        score = findViewById(R.id.tv_score);

        manageSharedPref = new ManageSharedPref();
        users = manageSharedPref.load(this);

        if(users != null) {
            for(Users user : users) {
                username.setText(user.getUsername());
                score.setText(String.valueOf(user.getScore()));
            }
        }

        play.setOnClickListener(v -> {
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        });

        quit.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        });

    }
}