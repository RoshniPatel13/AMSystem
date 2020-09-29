package htd.com.amsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String FID = "fid";
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String FCONTACT = "fcontact";
    public static final String FCOUNTRYCODE = "countrycode";
    public static final String NAMEPREFIX = "nameprefix";
    public static final String STATE = "state";
    public static final String CITY = "city";
    public static final String ZIPCODE = "zipcode";
    public static final String FACULTYID = "facultyid";
    public static final String FEMAIL = "email";
    public static final String FPASSWORD = "password";
    public static final String STUDID = "studid";
    public static final String SID = "sid";
    public static final String SFNAME = "sfname";
    public static final String SLNAME = "slname";
    public static final String SEMAIL = "semail";
    public static final String SAGE = "age";
    public static final String SDOB = "dob";
    public static final String SDATE = "date";
    public static final String SCONTACT = "scontact";
    public static final String SPA = "spresentabsent";
    public static final String SUBID = "subid";
    public static final String SUBNAME = "subname";
    public static final String SUBCODE = "subcode";
    public static final String AID = "aid";
    public static final String PERCENTAGE = "precentage";
    public static final String STATUS = "status";
    public static final String ADATE = "date";
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "facultydepartment", null, 1);
        Log.e("dbpath", context.getDatabasePath("facultydepartment") + "");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table faculty_info(fid INTEGER PRIMARY KEY , facultyid INTEGER,fname TEXT,lname TEXT,fcontact TEXT,countrycode TEXT,nameprefix TEXT,state TEXT,city TEXT,zipcode TEXT,email TEXT,password TEXT)");
        sqLiteDatabase.execSQL("create table student_info(sid INTEGER PRIMARY KEY , studid INTEGER,sfname TEXT,slname TEXT,scontact TEXT,spresentabsent TEXT,semail TEXT,dob TEXT, age TEXT, date TEXT)");
        sqLiteDatabase.execSQL("create table attendance_info(aid INTEGER PRIMARY KEY , sid INTEGER, subid INTEGER, precentage TEXT,status TEXT,date TEXT)");
        sqLiteDatabase.execSQL("create table subject_info(subid INTEGER PRIMARY KEY ,subname TEXT, fid INTEGER,subcode TEXT)");

        Log.d("Database", "Table Created table....");

    }

    /* public void generateDefaultValues(SQLiteDatabase db){
         InsertFacultyDetails(12345,"Roshani","Patel","255-233-2424","CA",
                 "Miss","Ontario","Toronto","355633","lambton","lambton");

         InsertStudentDetails(1234,"Vipula","Bhalla","604 555 5555",
                 "02/10/1997","23","Absent","27.09/2020","vipula@lambton.ca");

         InsertStudentDetails(2345,"Roshani","Patel","437 886 2581",
                 "01/08/1998","22","Absent","27.09/2020","roshni@lambton.ca");

         InsertStudentDetails(3456,"Elmy","Sebastian","437 886 1089",
                 "02/02/1995","25","Absent","27.09/2020","elmy@lambton.ca");

         InsertStudentDetails(4567,"Navpreet","Kaur","437 886 2401",
                 "12/03/1997","23","Absent","27.09/2020","navpreet@lambton.ca");

         InsertStudentDetails(5678,"Sangita","Bhagte","437 886 3154",
                 "11/08/1996","24","Absent","27.09/2020","sangita@lambton.ca");

         InsertStudentDetails(6789,"Jaspreet","Kaur","437 886 1347",
                 "03/03/1998","22","Absent","27.09/2020","jaspreet@lambton.ca");

         InsertStudentDetails(7891,"Awad","Basher","437 886 1057",
                 "05/05/1997","23","Absent","27.09/2020","awad@lambton.ca");

         InsertStudentDetails(8912,"Karan","Grover","437 886 2753",
                 "16/04/1996","24","Absent","27.09/2020","karan@lambton.ca");

         InsertStudentDetails(9101,"Sai","Kesav","437 886 1261",
                 "09/06/1997","23","Absent","27.09/2020","sai@lambton.ca");

         InsertStudentDetails(1012,"Syed","Nasrallah","437 886 1434",
                 "03/11/1994","26","Absent","27.09/2020","syed@lambton.ca");

         InsertSubjectDetails(1,"Database Design", "CDB2303");
         InsertSubjectDetails(1,"Java Programming","MADT3463");
         InsertSubjectDetails(1,"Introduction Swift Programming","MADT3004");
     }
 */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS faculty_info");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS student_info");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS attendance_info");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS subject_info");
    }

    public void InsertFacultyDetails(int facId, String fname, String lname, String fcontact, String countrycode, String nameprefix, String state, String city, String zipcode, String email, String password) {

        this.sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FACULTYID, facId);
        cv.put(FNAME, fname);
        cv.put(LNAME, lname);
        cv.put(FCONTACT, fcontact);
        cv.put(FCOUNTRYCODE, countrycode);
        cv.put(NAMEPREFIX, nameprefix);
        cv.put(STATE, state);
        cv.put(CITY, city);
        cv.put(ZIPCODE, zipcode);
        cv.put(FEMAIL, email);
        cv.put(FPASSWORD, password);

        this.sqLiteDatabase.insert("faculty_info", null, cv);
        Log.d("Database", "Record.....]]]]] Created table....");
        this.sqLiteDatabase.close();
    }

    public void InsertAttendanceDetails(int sid, int subid, String percentage, String status, String date) {
        this.sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SID, sid);
        cv.put(SUBID, subid);
        cv.put(PERCENTAGE, percentage);
        cv.put(STATUS, status);
        cv.put(ADATE, date);

        this.sqLiteDatabase.insert("attendance_info", null, cv);
        Log.d("Database", "Record Created...");
        this.sqLiteDatabase.close();
    }

    public void UpdateAttendanceDetails(int studid, int subid, String percentage, String status, String date) {
        this.sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        // cv.put(SID, sid);
        cv.put(SUBID, subid);
        cv.put(PERCENTAGE, percentage);
        cv.put(STATUS, status);
        cv.put(ADATE, date);

        this.sqLiteDatabase.update("attendance_info", cv, "sid=" + studid + " AND " + "subid=" + subid, null);
        Log.d("Database", "Record Updated...");
        this.sqLiteDatabase.close();
    }

    public ArrayList<AttendanceDetails> getAllAttendanceData() {
        ArrayList<AttendanceDetails> a_data = new ArrayList<AttendanceDetails>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from attendance_info", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {

            AttendanceDetails attendanceDetails = new AttendanceDetails();
            attendanceDetails.setaId(res.getInt(res.getColumnIndex(AID)));
            attendanceDetails.setsId((res.getInt(res.getColumnIndex(SID))));
            attendanceDetails.setSubId((res.getInt(res.getColumnIndex(SUBID))));
            attendanceDetails.setPercentage((res.getString(res.getColumnIndex(PERCENTAGE))));
            attendanceDetails.setDate((res.getString(res.getColumnIndex(ADATE))));
            attendanceDetails.setStatus((res.getString(res.getColumnIndex(STATUS))));

            res.moveToNext();
            a_data.add(attendanceDetails);
        }
        return a_data;
    }

    public ArrayList<AttendanceDetails> getStudentAttendance(int studid, int subid) {
        ArrayList<AttendanceDetails> a_data = new ArrayList<AttendanceDetails>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from attendance_info where sid = " + studid + " AND subid = " + subid + "", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {

            AttendanceDetails attendanceDetails = new AttendanceDetails();
            attendanceDetails.setaId(res.getInt(res.getColumnIndex(AID)));
            attendanceDetails.setsId((res.getInt(res.getColumnIndex(SID))));
            attendanceDetails.setSubId((res.getInt(res.getColumnIndex(SUBID))));
            attendanceDetails.setPercentage((res.getString(res.getColumnIndex(PERCENTAGE))));
            attendanceDetails.setDate((res.getString(res.getColumnIndex(ADATE))));
            attendanceDetails.setStatus((res.getString(res.getColumnIndex(STATUS))));

            res.moveToNext();
            a_data.add(attendanceDetails);
        }
        return a_data;
    }


    public void InsertSubjectDetails(int fid, String subname, String subcode) {
        this.sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUBNAME, subname);
        cv.put(FID, fid);
        cv.put(SUBCODE, subcode);

        this.sqLiteDatabase.insert("subject_info", null, cv);
        Log.d("Database", "Record.....]]]]] Created table....");
        this.sqLiteDatabase.close();
    }

    public void deleteStudentDetail(int s_id) {
        this.sqLiteDatabase = getWritableDatabase();
        this.sqLiteDatabase.execSQL("DELETE FROM student_info WHERE sid = '" + s_id + "'");
    }

    public ArrayList<FacultyDetails> getAllFacultyData() {
        ArrayList<FacultyDetails> c_data = new ArrayList<FacultyDetails>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from faculty_info", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {

            FacultyDetails facultyDetails = new FacultyDetails();
            facultyDetails.setfId(res.getInt(res.getColumnIndex(FID)));
            facultyDetails.setFname((res.getString(res.getColumnIndex(FNAME))));
            facultyDetails.setLname((res.getString(res.getColumnIndex(LNAME))));
            facultyDetails.setCity((res.getString(res.getColumnIndex(CITY))));
            facultyDetails.setState((res.getString(res.getColumnIndex(STATE))));
            facultyDetails.setCountryCode((res.getString(res.getColumnIndex(FCOUNTRYCODE))));
            facultyDetails.setContact((res.getString(res.getColumnIndex(FCONTACT))));
            facultyDetails.setNameprefix((res.getString(res.getColumnIndex(NAMEPREFIX))));
            facultyDetails.setZipcode((res.getString(res.getColumnIndex(ZIPCODE))));
            facultyDetails.setFacultyId((res.getInt(res.getColumnIndex(FACULTYID))));
            facultyDetails.setEmail((res.getString(res.getColumnIndex(FEMAIL))));
            facultyDetails.setPassword((res.getString(res.getColumnIndex(FPASSWORD))));

            res.moveToNext();
            c_data.add(facultyDetails);
        }
        return c_data;
    }

    public ArrayList<FacultyDetails> getSingleFacultyData(int f_id) {
        ArrayList<FacultyDetails> c_data = new ArrayList<FacultyDetails>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from faculty_info where fid = '" + f_id + "'", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {

            FacultyDetails facultyDetails = new FacultyDetails();
            facultyDetails.setfId(res.getInt(res.getColumnIndex(FID)));
            facultyDetails.setFname((res.getString(res.getColumnIndex(FNAME))));
            facultyDetails.setLname((res.getString(res.getColumnIndex(LNAME))));
            facultyDetails.setCity((res.getString(res.getColumnIndex(CITY))));
            facultyDetails.setState((res.getString(res.getColumnIndex(STATE))));
            facultyDetails.setCountryCode((res.getString(res.getColumnIndex(FCOUNTRYCODE))));
            facultyDetails.setContact((res.getString(res.getColumnIndex(FCONTACT))));
            facultyDetails.setNameprefix((res.getString(res.getColumnIndex(NAMEPREFIX))));
            facultyDetails.setZipcode((res.getString(res.getColumnIndex(ZIPCODE))));
            facultyDetails.setFacultyId((res.getInt(res.getColumnIndex(FACULTYID))));
            facultyDetails.setEmail((res.getString(res.getColumnIndex(FEMAIL))));
            facultyDetails.setPassword((res.getString(res.getColumnIndex(FPASSWORD))));

            res.moveToNext();
            c_data.add(facultyDetails);
        }
        return c_data;
    }

    public void UpdateFacultyProfile(int fid, int facId, String fname, String lname, String fcontact, String countrycode, String nameprefix, String state, String city, String zipcode, String email, String password) {

        this.sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FACULTYID, facId);
        values.put(FNAME, fname);
        values.put(LNAME, lname);
        values.put(FCONTACT, fcontact);
        values.put(FCOUNTRYCODE, countrycode);
        values.put(NAMEPREFIX, nameprefix);
        values.put(STATE, state);
        values.put(CITY, city);
        values.put(ZIPCODE, zipcode);
        values.put(FEMAIL, email);
        values.put(FPASSWORD, password);

        this.sqLiteDatabase.update("faculty_info", values, "fid=" + fid, null);
    }

    public void InsertStudentDetails(int studId, String sfname, String slname, String scontact, String dob, String age, String presentAbsent, String date, String email) {
        this.sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STUDID, studId);
        cv.put(SFNAME, sfname);
        cv.put(SLNAME, slname);
        cv.put(SCONTACT, scontact);
        cv.put(SEMAIL, email);
        cv.put(SDOB, dob);
        cv.put(SAGE, age);
        cv.put(SDATE, date);
        cv.put(SPA, presentAbsent);

        this.sqLiteDatabase.insert("student_info", null, cv);
        Log.d("Database", "Record.....]]]]] Created table....");
        this.sqLiteDatabase.close();
    }

    public ArrayList<StudentDetails> getAllStudentData() {
        ArrayList<StudentDetails> s_data = new ArrayList<StudentDetails>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from student_info", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {

            StudentDetails studentDetails = new StudentDetails();
            studentDetails.setsID(res.getInt(res.getColumnIndex(SID)));
            studentDetails.setStudId((res.getInt(res.getColumnIndex(STUDID))));
            studentDetails.setFname((res.getString(res.getColumnIndex(SFNAME))));
            studentDetails.setLname((res.getString(res.getColumnIndex(SLNAME))));
            studentDetails.setDate((res.getString(res.getColumnIndex(SDATE))));
            studentDetails.setDob((res.getString(res.getColumnIndex(SDOB))));
            studentDetails.setContact((res.getString(res.getColumnIndex(SCONTACT))));
            studentDetails.setAge((res.getInt(res.getColumnIndex(SAGE))));
            studentDetails.setEmail((res.getString(res.getColumnIndex(SEMAIL))));
            studentDetails.setPresentAbsent(res.getString(res.getColumnIndex(SPA)));

            res.moveToNext();
            s_data.add(studentDetails);
        }
        return s_data;
    }

}
