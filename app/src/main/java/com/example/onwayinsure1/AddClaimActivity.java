package com.example.onwayinsure1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddClaimActivity extends AppCompatActivity {

    //Declare field variables
    //AutoCompleteTextView vehicles;
    EditText editTextCustomerNumber,editTextCustomer,editTextTextVehicleType,editTextInsurance,date,contact,note,vehicles;
    ImageButton engine,chassis, vehiclesbtn,object,driver,front,back;
    ImageView enginePhoto, chassisPhoto,VehiclePhoto,ObjectPhoto,driverPhoto,frontPhoto,backPhoto;
    Button SubmitClaim;

    //Declare calendar variables
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String Currentdate;

    Claim claim;
    DatabaseReference dbRef;
    long maxid;

//    String items[] = {"Item1", "Item2","Item2"};//
//    ValueEventListener listener;
//    ArrayList<String> vehicleList;
//    ArrayAdapter<String> vehicleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_claim);

//        //Get the Action bar,its help for customize it
//        ActionBar actionBar = getSupportActionBar();
//        //remove AB
//        // actionBar.hide();
//        actionBar.setTitle("CREATE A CLAIM");
//        //enable Back Button
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //mDatabase = FirebaseDatabase.getInstance().getReference();

        vehicles = findViewById(R.id.vehicles);
        //vehicleAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.,items );
        //vehicles.setAdapter(vehicleAdapter);

        editTextCustomerNumber = findViewById(R.id.editTextCustomerNumber);
        editTextCustomer = findViewById(R.id.editTextCustomer);
        editTextTextVehicleType = findViewById(R.id.editTextTextVehicleType);
        editTextInsurance = findViewById(R.id.editTextInsurance);
        date = findViewById(R.id.date);
        contact = findViewById(R.id.contact);
        note = findViewById(R.id.note);

        engine = findViewById(R.id.engine);
        chassis = findViewById(R.id.chassis);
        vehiclesbtn = findViewById(R.id.vehiclesbtn);
        object = findViewById(R.id.object);
        driver = findViewById(R.id.driver);
        front = findViewById(R.id.front);
        back = findViewById(R.id.back);

        enginePhoto = findViewById(R.id.enginePhoto);
        chassisPhoto = findViewById(R.id.chassisPhoto);
        VehiclePhoto = findViewById(R.id.VehiclePhoto);
        ObjectPhoto = findViewById(R.id.ObjectPhoto);
        driverPhoto = findViewById(R.id.driverPhoto);
        frontPhoto = findViewById(R.id.frontPhoto);
        backPhoto = findViewById(R.id.backPhoto);

        SubmitClaim = findViewById(R.id.SubmitClaim);

        //Get current date/ time and set it on the field
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Currentdate = dateFormat.format(calendar.getTime());
        date.setText(Currentdate);

        claim = new Claim();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Claim_Requests");
        dbRef.keepSynced(true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Request for camera permission
        if(ContextCompat.checkSelfPermission(AddClaimActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddClaimActivity.this, new String[]{
                    Manifest.permission.CAMERA
            },
                    100);
        }

        //Engine button click image capture
        engine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });

        //Chasis button click image capture
        chassis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 102);
            }
        });

        //Vehcle button click image capture
        vehiclesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 103);
            }
        });

        //Object button click image capture
        object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 104);
            }
        });

        //Driver button click image capture
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 105);
            }
        });

        //Front button click image capture
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 106);
            }
        });

        //Back button click image capture
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 107);
            }
        });


        SubmitClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String vehicle =vehicles.getText().toString().trim();
                int customerNo = Integer.parseInt(editTextCustomerNumber.getText().toString().trim());
                String customer = editTextCustomer.getText().toString().trim();
                String  vehicleType = editTextTextVehicleType.getText().toString().trim();
                int insuranceNo = Integer.parseInt(editTextInsurance.getText().toString().trim());
//                //Bitmap enginePhoto = vehicles.getText().toString().trim();
//                //Bitmap chasisPhoto = vehicles.getText().toString().trim();
//                //Bitmap vehiclePhoto = vehicles.getText().toString().trim();
//                //Bitmap objectPhoto = vehicles.getText().toString().trim();
//                //Bitmap driverPhoto = vehicles.getText().toString().trim();
//                //Bitmap licenceFrontPhoto = vehicles.getText().toString().trim();
//                //Bitmap lincenceBackPhoto = vehicles.getText().toString().trim();
                String applyDate = date.getText().toString().trim();
                String contactNo= contact.getText().toString().trim();
                String addressNote= note.getText().toString().trim();


                //String vehicle = "DAA 5555";
                //int customerNo = 956;
               // String customer = "P.K.M. Bandara";
               // String  vehicleType = editTextTextVehicleType.getText().toString().trim();
              //  int insuranceNo = 655420;
                //Bitmap enginePhoto = vehicles.getText().toString().trim();
                //Bitmap chasisPhoto = vehicles.getText().toString().trim();
                //Bitmap vehiclePhoto = vehicles.getText().toString().trim();
                //Bitmap objectPhoto = vehicles.getText().toString().trim();
                //Bitmap driverPhoto = vehicles.getText().toString().trim();
                //Bitmap licenceFrontPhoto = vehicles.getText().toString().trim();
                //Bitmap lincenceBackPhoto = vehicles.getText().toString().trim();
                //String applyDate = date.getText().toString().trim();
                //String contactNo= "0712345678";
               // String addressNote= "5/1, Colombo 01";

                claim.setVehicle(vehicle);
                claim.setCustomerNo(customerNo);
                claim.setCustomer(customer);
                claim.setVehicleType(vehicleType);
                claim.setInsuranceNo(insuranceNo);
                //claim.setEnginePhoto(enginePhoto);
                //claim.setChasisPhoto(chassisPhoto);
                //claim.setVehiclePhoto(vehiclePhoto);
                //claim.setObjectPhoto(objectPhoto);
                //claim.setDriverPhoto(driverPhoto);
                //claim.setLicenceFrontPhoto(licenceFrontPhoto);
                //claim.setLincenceBackPhoto(lincenceBackPhoto);
                claim.setApplyDate(applyDate);
                claim.setContactNo(contactNo);
                claim.setAddressNote(addressNote);


                dbRef.child(String.valueOf(maxid+1)).setValue(claim);

                Toast.makeText(AddClaimActivity.this, "Claim request sent successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            enginePhoto.setImageBitmap(captureImage);

        }else  if (requestCode == 102) {
            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            chassisPhoto.setImageBitmap(captureImage);

        }else  if (requestCode == 103) {
            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            VehiclePhoto.setImageBitmap(captureImage);

        }else  if (requestCode == 104) {
            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            ObjectPhoto.setImageBitmap(captureImage);

        }else  if (requestCode == 105) {
            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            driverPhoto.setImageBitmap(captureImage);

        }else  if (requestCode == 106) {
            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            frontPhoto.setImageBitmap(captureImage);

        }else  if (requestCode == 107) {
            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            backPhoto.setImageBitmap(captureImage);
        }
    }

}