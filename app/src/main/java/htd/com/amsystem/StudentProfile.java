package htd.com.amsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import htd.com.amsystem.database.DatabaseHelper;

public class StudentProfile extends AppCompatActivity {

    private int s_id;
    private int studentId;
    private String fname;
    private String lname;
    private EditText sid;
    private EditText f_name;
    private EditText l_name;
    private EditText s_contact;
    private EditText s_dob;
    private EditText s_age;
    private EditText s_email;
    private Button btnAdd;
    private String scontact;
    private String sdob;
    private String sage;
    private String semail;

    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        db = new DatabaseHelper(this);

        sid = findViewById(R.id.studentId);
        f_name = findViewById(R.id.fname);
        l_name = findViewById(R.id.lname);
        s_contact = findViewById(R.id.contact);
        s_dob = findViewById(R.id.dob);
        s_age = findViewById(R.id.age);
        s_email = findViewById(R.id.email);
        btnAdd = findViewById(R.id.addNewStudent);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudent();
                finish();
            }
        });
    }

    public void saveStudent(){
        studentId = Integer.parseInt(sid.getText().toString());
        fname = f_name.getText().toString();
        lname = l_name.getText().toString();
        scontact = s_contact.getText().toString();
        sdob = s_dob.getText().toString();
        sage = s_age.getText().toString();
        semail = s_email.getText().toString();

        db.InsertStudentDetails(studentId,fname,lname,scontact,sdob,sage,"Absent","20/12/2020",semail);
    }

}
