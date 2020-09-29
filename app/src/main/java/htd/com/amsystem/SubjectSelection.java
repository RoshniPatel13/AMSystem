package htd.com.amsystem;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.StudentDetails;

public class SubjectSelection extends AppCompatActivity {
    public RadioGroup subjects;
    int subjectID;
    ArrayList<StudentDetails> studentDetails;
    int studentID;
    private Button next;
    private DatabaseHelper db;
    private EditText date;
    private Calendar myCalendar;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectselection);

        studentDetails = new ArrayList<>();
        subjects = findViewById(R.id.subjects);
        next = findViewById(R.id.next);
        date = findViewById(R.id.date);
        myCalendar = Calendar.getInstance();

        db = new DatabaseHelper(this);

        studentDetails = db.getAllStudentData();

        subjects.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.cbd) {
                    subjectID = 1;
                } else if (i == R.id.java) {
                    subjectID = 2;
                } else if (i == R.id.swift) {
                    subjectID = 3;
                } else {
                    Toast.makeText(getApplicationContext(), "Can not decide", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SubjectSelection.this, dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = date.getText().toString();
                Log.e("subject id", subjectID + "");
                for (int i = 0; i < studentDetails.size(); i++) {
                    studentID = studentDetails.get(i).getStudId();
                    db.InsertAttendanceDetails(studentID, subjectID, "", "Absent", selectedDate);
                }

                Intent i = new Intent(SubjectSelection.this, AttendanceActivity.class);
                i.putExtra("subject", subjectID);
                i.putExtra("date", selectedDate);
                startActivity(i);
            }
        });

        View.OnFocusChangeListener listener;
        listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.getId() == R.id.date && !hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

            }
        };
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),FacultyActivity.class));
        finish();
        super.onBackPressed();
    }
}
