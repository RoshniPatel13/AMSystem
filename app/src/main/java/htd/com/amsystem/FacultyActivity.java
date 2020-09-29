package htd.com.amsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.FacultyDetails;

public class FacultyActivity extends AppCompatActivity {

    String fname, lname;
    int id;
    int fid;
    private Button attendance;
    private Button updateProfile;
    private Button studentList;
    private Button logout;
    private DatabaseHelper db;
    private ArrayList<FacultyDetails> facultyDetails;
    private TextView facultyName;
    private TextView classDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        db = new DatabaseHelper(this);
        facultyDetails = new ArrayList<>();
        facultyDetails = db.getAllFacultyData();

        attendance = findViewById(R.id.btnAttendance);
        updateProfile = findViewById(R.id.btnUpdate);
        studentList = findViewById(R.id.btnStudentList);
        logout = findViewById(R.id.btnLogout);
        facultyName = findViewById(R.id.facltyname);
        classDetails = findViewById(R.id.classDetails);

        fname = facultyDetails.get(id).getFname();
        lname = facultyDetails.get(id).getLname();
        fid = facultyDetails.get(id).getfId();

        Log.e("fname", fname);
        Log.e("lname", lname);

        facultyName.setText(fname + " " + lname);
        classDetails.setText("MADT FALL 2020 SEM-1");

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(getApplicationContext(), FacultyProfile.class);
                update.putExtra("Fac_ID", fid);
                startActivity(update);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent attendanceAct = new Intent(getApplicationContext(), SubjectSelection.class);
                startActivity(attendanceAct);
                finish();
            }
        });

        studentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent studentList = new Intent(getApplicationContext(), StudentListActivity.class);
                startActivity(studentList);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.loggedIn = "no";
                SharedPreferences.Editor editor1 = getSharedPreferences("AMSystem", MODE_PRIVATE).edit();
                editor1.putString("loggedIN", MainActivity.loggedIn);
                editor1.apply();
                finish();
            }
        });
    }
}
