package htd.com.amsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import htd.com.amsystem.database.DatabaseHelper;

public class SplashActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SharedPreferences preference = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        final boolean firstLaunch = preference.getBoolean("firstlaunch", true);


        RelativeLayout linearLayout = findViewById(R.id.splashBody);
        linearLayout.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (firstLaunch) {
                            preference.edit().putBoolean("firstlaunch", false).apply();

                            defaultData();

                            SharedPreferences prefs1 = getSharedPreferences("AMSystem", MODE_PRIVATE);
                            MainActivity.loggedIn = prefs1.getString("loggedIN", "no");
                            if (MainActivity.loggedIn.equals("no")) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                startActivity(new Intent(getApplicationContext(), FacultyActivity.class));
                                finish();
                            }
                        } else {
                            SharedPreferences prefs1 = getSharedPreferences("AMSystem", MODE_PRIVATE);
                            MainActivity.loggedIn = prefs1.getString("loggedIN", "no");
                            if (MainActivity.loggedIn.equals("no")) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                startActivity(new Intent(getApplicationContext(), FacultyActivity.class));
                                finish();
                            }
                        }
                    }
                }, 3000);
    }


    public void defaultData() {
        db = new DatabaseHelper(this);
        db.InsertFacultyDetails(12345, "Roshani", "Patel", "255-233-2424", "CA",
                "Miss", "Ontario", "Toronto", "355633", "madt@lambton.ca", "lambton");

        db.InsertStudentDetails(1234, "Vipula", "Bhalla", "604 555 5555",
                "02/10/1997", "23", "Absent", "27.09/2020", "vipula@lambton.ca");

        db.InsertStudentDetails(2345, "Roshani", "Patel", "437 886 2581",
                "01/08/1998", "22", "Absent", "27.09/2020", "roshni@lambton.ca");

        db.InsertStudentDetails(3456, "Elmy", "Sebastian", "437 886 1089",
                "02/02/1995", "25", "Absent", "27.09/2020", "elmy@lambton.ca");

        db.InsertStudentDetails(4567, "Navpreet", "Kaur", "437 886 2401",
                "12/03/1997", "23", "Absent", "27.09/2020", "navpreet@lambton.ca");

        db.InsertStudentDetails(5678, "Sangita", "Bhagte", "437 886 3154",
                "11/08/1996", "24", "Absent", "27.09/2020", "sangita@lambton.ca");

        db.InsertStudentDetails(6789, "Jaspreet", "Kaur", "437 886 1347",
                "03/03/1998", "22", "Absent", "27.09/2020", "jaspreet@lambton.ca");

        db.InsertStudentDetails(7891, "Awad", "Basher", "437 886 1057",
                "05/05/1997", "23", "Absent", "27.09/2020", "awad@lambton.ca");

        db.InsertStudentDetails(8912, "Karan", "Grover", "437 886 2753",
                "16/04/1996", "24", "Absent", "27.09/2020", "karan@lambton.ca");

        db.InsertStudentDetails(9101, "Sai", "Kesav", "437 886 1261",
                "09/06/1997", "23", "Absent", "27.09/2020", "sai@lambton.ca");

        db.InsertStudentDetails(1012, "Syed", "Sabiyama", "437 886 1434",
                "03/11/1994", "26", "Absent", "27.09/2020", "syed@lambton.ca");

        db.InsertStudentDetails(1013, "Sai", "Kishan", "433 887 1422",
                "13/11/1996", "24", "Absent", "27.09/2020", "sk@lambton.ca");

        db.InsertStudentDetails(1014, "Amarvir", "Singh", "333 777 2434",
                "09/10/1998", "22", "Absent", "27.09/2020", "amarvir@lambton.ca");

        db.InsertStudentDetails(1015, "Meet", "Patel", "343 454 7896",
                "25/08/1994", "26", "Absent", "27.09/2020", "meet@lambton.ca");

        db.InsertStudentDetails(1016, "Rahulgiri", "Ajaygiri", "345 856 9876",
                "25/05/1995", "25", "Absent", "27.09/2020", "rahul@lambton.ca");

        db.InsertStudentDetails(1017, "Adity", "Kansara", "4563 654 4536",
                "02/11/1994", "26", "Absent", "27.09/2020", "adity@lambton.ca");

        db.InsertSubjectDetails(1, "Database Design", "CDB2303");
        db.InsertSubjectDetails(1, "Java Programming", "MADT3463");
        db.InsertSubjectDetails(1, "Introduction Swift Programming", "MADT3004");

    }
}

