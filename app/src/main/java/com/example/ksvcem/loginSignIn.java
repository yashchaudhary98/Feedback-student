package com.example.ksvcem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginSignIn extends AppCompatActivity {


    private EditText email_type, Pass_type;
    private Button loggingIn;

    private ProgressDialog progressDialog;

    private TextView register;

    private FirebaseAuth mAuth;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(loginSignIn.this, Introduction.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_in);


        loggingIn = findViewById(R.id.loggedin);

        register = findViewById(R.id.newuser);

        loggingIn = findViewById(R.id.loggedin);
        email_type = findViewById(R.id.Email);
        Pass_type = findViewById(R.id.Password);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(loginSignIn.this);
        progressDialog.setMessage("Loading...");


        loggingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userinfo();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginSignIn.this, login_authenticate.class));
            }
        });
    }


    private void userinfo() {
        String Email = email_type.getText().toString().trim();
        String Password = Pass_type.getText().toString().trim();

        if (!Email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            if (!Password.isEmpty()) {

                progressDialog.show();
                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    if (user.isEmailVerified()) {

                                        Intent intent = new Intent(loginSignIn.this, profile.class);
                                        intent.putExtra("Exit", false);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);

                                        Toast.makeText(loginSignIn.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    } else {
                                        user.sendEmailVerification();
                                        Toast.makeText(loginSignIn.this, "Check your Email to verify the account", Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    progressDialog.hide();
                                    Toast.makeText(loginSignIn.this, "Incorrect Username and Password", Toast.LENGTH_LONG).show();

                                }
                                progressDialog.hide();
                            }

                        });

            } else {
                Pass_type.setError("Enter the valid Password");
                Pass_type.requestFocus();
                return;
            }

        } else if (Email.isEmpty()) {
            email_type.setError("Enter the valid Email");
            email_type.requestFocus();
            return;
        } else {
            email_type.setError("Enter the valid Email");
            email_type.requestFocus();
            return;
        }

        if (Password.length() < 6) {
            Pass_type.setError("Enter the valid Password");
            Pass_type.requestFocus();
            return;
        }
    }


}