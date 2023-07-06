package fr.dansebag.partiel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button connexion;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexion = findViewById(R.id.connexion);
        username = findViewById(R.id.username);

        connexion();
    }

    public void messageAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quitter l'application");
        builder.setMessage("Voulez-vous vraiment quitter l'application ?");
        builder.setPositiveButton("Oui", (dialog, which) -> finish());
        builder.setNegativeButton("Non", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void connexion(){
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty()){
                    username.setError("Veuillez saisir un nom d'utilisateur");
                }else{
                    SharedPreferences preferences = getSharedPreferences(MainActivity.class.getSimpleName(), MODE_PRIVATE);
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", username.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        messageAlertDialog();
    }


}