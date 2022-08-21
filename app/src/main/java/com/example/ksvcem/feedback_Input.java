package com.example.ksvcem;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class feedback_Input extends AppCompatActivity implements View.OnClickListener {

    private EditText num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
    private TextView result;
//    private  ProgressDialog progressDialog;

    private TextView nameTextView, rollTextView, yearTextView, branchTextView;
    private Button Submit;
    private EditText suggest;
    private CheckBox checkBox;


    TextInputLayout drop1, drop2;
    AutoCompleteTextView faculty, subName;

    private boolean isBackPressedOnce = false;

    @Override
    public void onBackPressed() {
        if(isBackPressedOnce){
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        isBackPressedOnce = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressedOnce = false;
            }
        },2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Initialization of the Instance");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_input);
        Objects.requireNonNull(getSupportActionBar()).hide();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        assert user != null;
        String userID = user.getUid();


        drop1 = findViewById(R.id.menu_drop1);
        drop2 = findViewById(R.id.menu_drop2);
        faculty = findViewById(R.id.faculty_name);
        subName = findViewById(R.id.sub_name);


        Submit = findViewById(R.id.sendBtn);
//
//        progressDialog = new ProgressDialog(feedback_Input.this);
//        progressDialog.setMessage("Loading...");

        nameTextView = findViewById(R.id.nameUser);
        rollTextView = findViewById(R.id.rollUser);
        branchTextView = findViewById(R.id.branchUser);
        yearTextView = findViewById(R.id.yearUser);

        checkBox = findViewById(R.id.checkBox);

        System.out.println("nameTextView     --->"+ nameTextView);

        suggest = findViewById(R.id.suggestions);

        num1 = findViewById(R.id.rating1);
        num2 = findViewById(R.id.rating2);
        num3 = findViewById(R.id.rating3);
        num4 = findViewById(R.id.rating4);
        num5 = findViewById(R.id.rating5);
        num6 = findViewById(R.id.rating6);
        num7 = findViewById(R.id.rating7);
        num8 = findViewById(R.id.rating8);
        num9 = findViewById(R.id.rating9);
        num10 = findViewById(R.id.rating10);


        System.out.println("nameTextView     --->"+ nameTextView);
        result = findViewById(R.id.total1);

        System.out.println("result     --->"+ result);

       Submit.setOnClickListener(this);





        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!num1.getText().toString().equals("") && !num2.getText().toString().equals("") && !num3.getText().toString().equals("") && !num4.getText().toString().equals("") && !num5.getText().toString().equals("") && !num6.getText().toString().equals("") && !num8.getText().toString().equals("") && !num9.getText().toString().equals("") && !num10.getText().toString().equals("")) {

                    int number1 = Integer.parseInt(num1.getText().toString());
                    int number2 = Integer.parseInt(num2.getText().toString());
                    int number3 = Integer.parseInt(num3.getText().toString());
                    int number4 = Integer.parseInt(num4.getText().toString());
                    int number5 = Integer.parseInt(num5.getText().toString());
                    int number6 = Integer.parseInt(num6.getText().toString());
                    int number7 = Integer.parseInt(num7.getText().toString());
                    int number8 = Integer.parseInt(num8.getText().toString());
                    int number9 = Integer.parseInt(num9.getText().toString());
                    int number10 = Integer.parseInt(num10.getText().toString());

                    int sum = number1 + number2 + number3 + number4 + number5 + number6 + number7 + number8 + number9 + number10;

                    result.setText(String.valueOf(sum));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        num1.addTextChangedListener(textWatcher);
        num2.addTextChangedListener(textWatcher);
        num3.addTextChangedListener(textWatcher);
        num4.addTextChangedListener(textWatcher);
        num5.addTextChangedListener(textWatcher);
        num6.addTextChangedListener(textWatcher);
        num7.addTextChangedListener(textWatcher);
        num8.addTextChangedListener(textWatcher);
        num9.addTextChangedListener(textWatcher);
        num10.addTextChangedListener(textWatcher);



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);
                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    String rollNumber = userProfile.rollNumber;
                    String branch = userProfile.branch;
                    String year = userProfile.year;

                    nameTextView.setText(fullName);
                    rollTextView.setText(rollNumber);
                    branchTextView.setText(branch);
                    yearTextView.setText(year);


                    ArrayList<String> items1 = new ArrayList<String>();
                    String s = yearTextView.getText().toString();
                    if ("1st".equals(s)) {
                        items1.add("Ankit Rajan");
                        items1.add("Ajay Upadhyay");
                        items1.add("Abhishek chaudhary");
                    } else if ("2nd".equals(s)) {
                        items1.add("Ankit Rajan");
                        items1.add("Rajeev Sehrawat");
                        items1.add("Nidhi Bishnoi");
                        items1.add("Vivek Kumar");
                        items1.add("Anshika Pathak");
                    }

                    ArrayAdapter<String> itemAdapter1 = new ArrayAdapter<String>(feedback_Input.this, R.layout.dropdown_menu, items1);
                    faculty.setAdapter(itemAdapter1);


                    ArrayList<String> items2 = new ArrayList<String>();
                    String s1 = yearTextView.getText().toString();
                    if ("1st".equals(s1)) {
                        items2.add("Maths I");
                        items2.add("Electronics");
                        items2.add("Artificial Intelligence");
                    } else if ("2nd".equals(s1)) {
                        items2.add("OS (KCS401)");
                        items2.add("Python (KNC402)");
                        items2.add("UHV (KVE401)");
                        items2.add("Automata (KCS402)");
                        items2.add("Microprocessor (KCS403)");
                    }

                    ArrayAdapter<String> itemAdapter2 = new ArrayAdapter<String>(feedback_Input.this, R.layout.dropdown_menu, items2);
                    subName.setAdapter(itemAdapter2);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(feedback_Input.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });



    }

    private boolean error() {
        if(!checkBox.isChecked()){
            Toast.makeText(this, "Please check the checkbox", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }

    }


    private void addUserdata() {
        System.out.println("I am in AddUserData  Method");
        String facultyName = faculty.getText().toString();
        String name = String.valueOf(nameTextView.getText());
        String collegeNo = String.valueOf(rollTextView.getText());
        String branchName = String.valueOf(branchTextView.getText());
        String total = result.getText().toString();
        String year = String.valueOf(yearTextView.getText());
        String suggestions = suggest.getText().toString();
        String subject = subName.getText().toString();


        String q1 = num1.getText().toString();
        String q2 = num2.getText().toString();
        String q3 = num3.getText().toString();
        String q4 = num4.getText().toString();
        String q5 = num5.getText().toString();
        String q6 = num6.getText().toString();
        String q7 = num7.getText().toString();
        String q8 = num8.getText().toString();
        String q9 = num9.getText().toString();
        String q10 = num10.getText().toString();


        System.out.println("I am in AddUserData  Method");

        if (facultyName.isEmpty()) {
            faculty.setError("Choose the Faculty Name");
            faculty.requestFocus();
            return;
        }

        if(subject.isEmpty()){
            subName.setError("Choose the Subject Name");
            subName.requestFocus();
            return;
        }

        if(total.isEmpty()){
            result.setError("Fields are empty");
            result.requestFocus();
            return;
        }

        if (suggestions.isEmpty()) {
            suggest.setError("Enter the suggestion/comment/feedback");
            suggest.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyATbAi3PFSRHKYkuAdTpTc7ci6GoG3nTtsSJOnF-wlTd8OwANmk5q3G6XQCx9YegxWXg/exec", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(feedback_Input.this,response, Toast.LENGTH_LONG).show();
//                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(feedback_Input.this, "Unsuccessful", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                System.out.println("I'm in get parameter");
                Map<String, String> params = new HashMap<>();
                params.put("action", "addUserdata");
                params.put("faculty", facultyName);
                params.put("student", name);
                params.put("roll", collegeNo);
                params.put("branch", branchName);
                params.put("sum", total);
                params.put("time", year);
                params.put("comment", suggestions);
                params.put("sub", subject);

                params.put("Q1", q1);
                params.put("Q2", q2);
                params.put("Q3", q3);
                params.put("Q4", q4);
                params.put("Q5", q5);
                params.put("Q6", q6);
                params.put("Q7", q7);
                params.put("Q8", q7);
                params.put("Q9", q8);
                params.put("Q10", q10);

                return params;
            }
        };


        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println("abc");
        queue.add(stringRequest);
        System.out.println("hello====");
    }


    @Override
    public void onClick(View v) {
        if(v==Submit) {
            if(error()){
                System.out.println("I am calling this method");
                addUserdata();
                Toast.makeText(this, "Thanks for submitting the feedback", Toast.LENGTH_LONG).show();
            }
        }

    }
}

