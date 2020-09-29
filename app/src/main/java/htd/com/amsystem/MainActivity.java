package htd.com.amsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.FacultyDetails;

public class MainActivity extends AppCompatActivity {

    public static String loggedIn = "no";
    int id;
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private String uname, pwd;
    private DatabaseHelper db;
    private ArrayList<FacultyDetails> facultyDetailsArrayList;
    private String funame, fpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.INTERNET",
                            "android.permission.READ_EXTERNAL_STORAGE"},
                    123);
        }

        SharedPreferences prefs1 = getSharedPreferences("AMSystem", MODE_PRIVATE);
        loggedIn = prefs1.getString("loggedIN", "no");

        db = new DatabaseHelper(this);
        facultyDetailsArrayList = new ArrayList<FacultyDetails>();
        facultyDetailsArrayList = db.getAllFacultyData();

        funame = facultyDetailsArrayList.get(id).getEmail();
        fpwd = facultyDetailsArrayList.get(id).getPassword();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uname = username.getText().toString();
                pwd = password.getText().toString();

                if (uname.equals(funame) && pwd.equals(fpwd)) {
                    Intent facultyActivity = new Intent(MainActivity.this, FacultyActivity.class);
                    loggedIn = "yes";
                    startActivity(facultyActivity);
                    finish();
                } else if (uname.equals("") && pwd.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Credentials!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Correct Credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor1 = getSharedPreferences("AMSystem", MODE_PRIVATE).edit();
        editor1.putString("loggedIN", loggedIn);
        editor1.apply();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs1 = getSharedPreferences("AMSystem", MODE_PRIVATE);
        loggedIn = prefs1.getString("loggedIN", loggedIn);

    }

}
