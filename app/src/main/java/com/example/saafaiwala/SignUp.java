package com.example.saafaiwala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private TextInputLayout txtFullName, txtEmail, txtPassword, txtConfirmPassword;
    TextView login;
    Button btn_Register;
    String gender="";
    RadioButton radioGenderMale, radioGenderFemale;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        txtFullName = (TextInputLayout) findViewById(R.id.txtfullname);
        txtEmail = (TextInputLayout)findViewById(R.id.txtemail);
        txtPassword = (TextInputLayout)findViewById(R.id.txtpassword);
        txtConfirmPassword = (TextInputLayout)findViewById(R.id.txtconfirmpassword);
        radioGenderMale = (RadioButton)findViewById(R.id.radio_gendermale);
        radioGenderFemale = (RadioButton)findViewById(R.id.radio_genderfemale);
        btn_Register = (Button)findViewById(R.id.btn_register);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        login = findViewById(R.id.alreadyacc);



        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        firebaseAuth = firebaseAuth.getInstance();

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = txtEmail.getEditText().getText().toString().trim();
                String password = txtPassword.getEditText().getText().toString().trim();
                final String fullname = txtFullName.getEditText().getText().toString().trim();
                String confirmPassword = txtConfirmPassword.getEditText().getText().toString().trim();

                if (radioGenderMale.isChecked()){
                    gender = "Male";
                }
                if (radioGenderFemale.isChecked()){
                    gender = "Female";
                }



                if (TextUtils.isEmpty(fullname)){
                    Toast.makeText(SignUp.this,"Please Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this,"Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this,"Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(SignUp.this,"Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<8){
                    Toast.makeText(SignUp.this,"Password too short", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirmPassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password )
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        Users information= new Users(
                                                fullname,
                                                email,
                                                gender
                                        );

                                        finish();
                                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                Toast.makeText(SignUp.this, "Registration Complete.", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {

                                        Toast.makeText(SignUp.this, "Authorization Error", Toast.LENGTH_SHORT).show();


                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(SignUp.this, "Password didn't match.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,login.class);
                startActivity(i);
            }
        });


    }
}
