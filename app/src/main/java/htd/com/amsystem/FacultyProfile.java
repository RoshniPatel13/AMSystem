package htd.com.amsystem;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import htd.com.amsystem.database.DatabaseHelper;
import htd.com.amsystem.database.FacultyDetails;

public class FacultyProfile extends AppCompatActivity implements View.OnClickListener {

    String[] prefixArray = {"Miss", "Mrs", "Mr"};
    DatabaseHelper databaseHelper;
    ArrayList<FacultyDetails> facultyDetailsArrayList;
    private RadioGroup prefix;
    private int f_id;
    private int facultyId;
    private String fname;
    private String lname;
    private EditText fid;
    private EditText f_name;
    private EditText l_name;
    private CountryCodePicker c_code;
    private EditText f_contact;
    private EditText f_state;
    private EditText f_city;
    private EditText f_zip;
    private EditText f_email;
    private EditText f_password;
    private Button btnUpdate;
    private String namePrefix;
    private String countryCode;
    private String contact;
    private String state;
    private String city;
    private String zip;
    private String email;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            f_id = extras.getInt("Fac_ID");
        }

        databaseHelper = new DatabaseHelper(this);
        facultyDetailsArrayList = new ArrayList<FacultyDetails>();
        facultyDetailsArrayList = databaseHelper.getSingleFacultyData(f_id);

        prefix = findViewById(R.id.prefix);
        fid = findViewById(R.id.facultyId);
        f_name = findViewById(R.id.fname);
        l_name = findViewById(R.id.lname);
        c_code = findViewById(R.id.ccp);
        f_contact = findViewById(R.id.contact);
        f_state = findViewById(R.id.state);
        f_city = findViewById(R.id.city);
        f_zip = findViewById(R.id.zipcode);
        f_email = findViewById(R.id.email);
        f_password = findViewById(R.id.password);
        btnUpdate = findViewById(R.id.update_facultyProfile);

        prefix.setOnCheckedChangeListener(new btnPrexixListener());
        btnUpdate.setOnClickListener(this);

        GetFacultyDetails();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_facultyProfile:
                facultyId = Integer.parseInt(fid.getText().toString());
                fname = f_name.getText().toString();
                lname = l_name.getText().toString();
                contact = f_contact.getText().toString();
                state = f_state.getText().toString();
                city = f_city.getText().toString();
                zip = f_zip.getText().toString();
                email = f_email.getText().toString();
                password = f_password.getText().toString();
                countryCode = c_code.getSelectedCountryNameCode();

                Log.e("code", countryCode);

                databaseHelper.UpdateFacultyProfile(f_id, facultyId, fname, lname, contact,
                        countryCode, namePrefix, state, city, zip, email, password);
                finish();
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                break;

            default:
                return;
        }
    }

    public void GetFacultyDetails() {
        facultyId = facultyDetailsArrayList.get(0).getFacultyId();
        fname = facultyDetailsArrayList.get(0).getFname();
        lname = facultyDetailsArrayList.get(0).getLname();
        namePrefix = facultyDetailsArrayList.get(0).getNameprefix();
        countryCode = facultyDetailsArrayList.get(0).getCountryCode();
        contact = facultyDetailsArrayList.get(0).getContact();
        state = facultyDetailsArrayList.get(0).getState();
        city = facultyDetailsArrayList.get(0).getCity();
        zip = facultyDetailsArrayList.get(0).getZipcode();
        email = facultyDetailsArrayList.get(0).getEmail();
        password = facultyDetailsArrayList.get(0).getPassword();

        f_name.setText(fname + "");
        l_name.setText(lname + "");
        fid.setText(facultyId + "");
        f_contact.setText(contact + "");
        f_state.setText(state + "");
        f_city.setText(city + "");
        f_zip.setText(zip + "");
        f_email.setText(email + "");
        f_password.setText(password);

        if (namePrefix.equals("Miss")) {
            prefix.check(R.id.miss);
        } else if (namePrefix.equals("Mrs")) {
            prefix.check(R.id.mrs);
        } else {
            prefix.check(R.id.mr);
        }

    }

    private class btnPrexixListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.mr) {
                FacultyProfile.this.namePrefix = "Mr";
                return;
            } else if (i == R.id.miss) {
                FacultyProfile.this.namePrefix = "Miss";
                return;
            } else {
                FacultyProfile.this.namePrefix = "Mrs";
            }
        }
    }


}
