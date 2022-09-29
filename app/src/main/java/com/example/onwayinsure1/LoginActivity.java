package com.example.onwayinsure1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //Declare field variables
    EditText inputUsername, inputPassword;
    Button btnSignIn;
    // ProgressBar progressBar;
    ProgressDialog progressDialog;
    //Declare Email Validation pattern
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //Declare Firebase authentication variables
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    /////////////////for remoavable
    TextView toDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Another way to remove Action Bar

        //Get the Action bar,its help for customize it
        ActionBar actionBar = getSupportActionBar();
        //remove AB
        actionBar.hide();

        //Rename ->
        //actionBar.setTitle("amy name");

        //Initialize fieled variables
        TextView singUp=findViewById(R.id.signUp);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        //progressBar = new ProgressBar(this);
        progressDialog = new ProgressDialog(this);

        //Initialize firebase variables
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();




        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforSignIn();
            }
        });
    }

    private void perforSignIn() {

        //String username= inputUsername.getText().toString();

        String email = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        //String email = "test@gmail.com";
        //String password = "123456";


//        if(username.isEmpty()){
//            inputUsername.setError("Username cannot be empty");
//        }else
        if(!email.matches((emailPattern))){
            inputUsername.setError("Incorrect Email format");
        }else if(password.isEmpty() || password.length() <6){
            inputPassword.setError("Password length should greater than 6");
        }else{
            //progressBar.getProgress();
            //progressBar.showContextMenu();
            progressDialog.setMessage("Please wait....");
            progressDialog.setTitle("SignIn");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

           mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       progressDialog.dismiss();
                       sendUserToNextActivity();
                       Toast.makeText(LoginActivity.this, "Login successful", Toast. LENGTH_SHORT).show();
                   }else{
                       progressDialog.dismiss();
                       Toast.makeText(LoginActivity.this, ""+task.getException(),Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this, DashboardHome.class);
       // Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}