package com.example.onwayinsure1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    //Declare field variables
    TextView alreadyHaveAccount;
    //EditText inputUsername;
    EditText inputEmail, inputPassword, inputConfirmPassword;
    Button btnRegister;
   // ProgressBar progressBar;
    ProgressDialog progressDialog;
    //Declare Email Validation pattern
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //Declare Firebase authentication variables
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get the Action bar,its help for customize it
        ActionBar actionBar = getSupportActionBar();
        //remove AB
        actionBar.hide();

        //Initialize all field variables
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        //inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword =findViewById(R.id.inputConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        //progressBar = new ProgressBar(this);
        progressDialog = new ProgressDialog(this);

        mAuth =FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //dbRef.keepSynced(true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });

    }

    private void PerforAuth() {
        //String username= inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();


//        if(username.isEmpty()){
//            inputUsername.setError("Username cannot be empty");
//        }else
        if(!email.matches((emailPattern))){
            inputEmail.setError("Incorrect Email format");
        }else if(password.isEmpty() || password.length() <6){
            inputPassword.setError("Password length should greater than 6");
        }else if(!password.equals((confirmPassword))){
            inputConfirmPassword.setError("Password not matched");
        }else{
            //progressBar.getProgress();
            //progressBar.showContextMenu();
            progressDialog.setMessage("Please wait....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}