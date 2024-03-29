package fr.dansebag.partiel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button connexion;
    private EditText username;
    private List<Users> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexion = findViewById(R.id.connexion);
        username = findViewById(R.id.username);

        connexion();
    }

    private void messageAlertDialog(){
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

                    if(checkUserIfExist(users, username.getText().toString())){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Utilisateur déjà existant");
                        builder.setMessage("Voulez-vous vraiment vous connecter ?");
                        builder.setPositiveButton("Oui", (dialog, which) -> {

                            ManageSharedPref manageSharedPref = new ManageSharedPref();
                            Users user = new Users(username.getText().toString(), 0);
                            users.add(user);
                            manageSharedPref.save(users, MainActivity.this);

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        });
                        builder.setNegativeButton("Non", (dialog, which) -> {

                            username.setError("Veuillez saisir un autre nom d'utilisateur");
                            dialog.cancel();

                        });
                        builder.show();
                        return;
                    }

                    ManageSharedPref manageSharedPref = new ManageSharedPref();
                    Users user = new Users(username.getText().toString(), 0);
                    users.add(user);
                    manageSharedPref.save(users, MainActivity.this);

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean checkUserIfExist(List<Users> users, String username) {
        for (Users user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        messageAlertDialog();
    }


}