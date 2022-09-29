package com.example.onwayinsure1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateSOSActivity extends AppCompatActivity {

    //Declare field variables
    //AutoCompleteTextView vehicles;
    EditText editTextCInsuranceNo, editTextCustomerNumber, editTextCustomer, editTextContact, editTextDate, editTextAgent, editTextBranch, editTextLatitude, editTextLogitude,vehicles;
    ImageButton photobtn, locationbtn;
    ImageView PhotoAccident;
    Button SubmitSOS;

    //Declare calendar variables
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String Currentdate;

    private  LocationManager locationManager;

    SOSMessage sosMessage;
    DatabaseReference dbRef;
    FirebaseStorage mStorage;

    //private StorageReference strRef;


    long maxid;
    private static final int Gallery_Code = 102;
    private static final int Camera_code = 101;
    private static final int Camera_perm_code = 100;
    Uri imageUri= null;
    String currentPhotoPath;

    ProgressDialog progressDialog;

    //ActivityCreateSosactivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sosactivity);

        //Get the Action bar,its help for customize it
        //ActionBar actionBar = getSupportActionBar();
        //remove AB
        // actionBar.hide();
        // actionBar.setTitle("Hello, ");
        //actionBar.setDisplayHomeAsUpEnabled(true);

        //Set the Page action bar titel
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_sos_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vehicles = findViewById(R.id.vehicles);
        editTextCInsuranceNo = findViewById(R.id.editTextCInsuranceNo);
        editTextCustomerNumber = findViewById(R.id.editTextCustomerNumber);
        editTextCustomer = findViewById(R.id.editTextCustomer);
        editTextContact = findViewById(R.id.editTextContact);
        editTextDate = findViewById(R.id.editTextDate);
        editTextAgent = findViewById(R.id.editTextAgent);
        editTextBranch = findViewById(R.id.editTextBranch);
        locationbtn = findViewById(R.id.locationbtn);
        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLogitude = findViewById(R.id.editTextLogitude);
        photobtn = findViewById(R.id.photobtn);
        PhotoAccident = findViewById(R.id.PhotoAccident);
        SubmitSOS = findViewById(R.id.SubmitSOS);

        //Get current date/ time and set it on the field
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        Currentdate = dateFormat.format(calendar.getTime());
        editTextDate.setText(Currentdate);

        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        sosMessage = new SOSMessage();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        dbRef = FirebaseDatabase.getInstance().getReference().child("SOS_Messages");
        dbRef.keepSynced(true);
        mStorage = FirebaseStorage.getInstance();

        //strRef = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //binding = ActivityCreateSosactivityBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        //String fileName = dateFormat.format(new Date());

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

        //Request for location permission
        if((ContextCompat.checkSelfPermission(CreateSOSActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(CreateSOSActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ){
            ActivityCompat.requestPermissions(CreateSOSActivity.this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
                    },1);
        }


        //Request for camera permission
        if (ContextCompat.checkSelfPermission(CreateSOSActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreateSOSActivity.this, new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        //Set Location details
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                editTextLogitude.setText(String.valueOf(location.getLongitude()));
                editTextLatitude.setText(String.valueOf(location.getLatitude()));
            }
        });


        //Photo button click to capture images from camera
        photobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //intent.setType("image/*");
                startActivityForResult(intent, Camera_code);
            }
        });

       SubmitSOS.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String vehicle = vehicles.getText().toString().trim();
               int insuranceNo= Integer.parseInt(editTextCInsuranceNo.getText().toString().trim());
               int customerNo= Integer.parseInt(editTextCustomerNumber.getText().toString().trim());
               String customer = editTextCustomer.getText().toString().trim();
               String contactNo= editTextContact.getText().toString().trim();
               String dateTime= editTextDate.getText().toString().trim();
               String agent= editTextAgent.getText().toString().trim();
               String branch= editTextBranch.getText().toString().trim();
               String longitude= editTextLogitude.getText().toString().trim();
               String latitude= editTextLatitude.getText().toString().trim();
//               //Bitmap photo= Bitmap(vehicles.getText().toString().trim());
                //Uri photo= Bitmap(vehicles.getText().toString().trim());


//               String vehicle = "KAL 7565";
//               int insuranceNo= 655800;
//               int customerNo = 210;
//               String customer = "P.K.M. Perara";
//               String contactNo= "0712345678";
               //String dateTime= "2022-09-25 03:07:03";
//               String agent= "A.B.C. Zoysa";
//               String branch= "Colombo";
               //String longitude="-122.084";
               //String latitude= "37.421998333333335";
               //Bitmap photo= Bitmap(vehicles.getText().toString().trim());

//               if(vehicle.isEmpty() ) {
//                   vehicles.setError("Vehicle Number is not added! ");
//               }
////               else if(insuranceNo == null){
////                   editTextCInsuranceNo.setError("Insurance Number is not added!");
////               }else if(customerNo.isEmpty() ){
////                   editTextCustomerNumber.setError("Customer Number is not added!");
////               }
//               else if(customer.isEmpty() ){
//                   editTextCustomer.setError("Customer is not added!");
//               }else if(contactNo.isEmpty() ){
//                   editTextContact.setError("Contact Number is not added!");
//               }else if(dateTime.isEmpty() ){
//                   editTextDate.setError("Date is not added!");
//               }else if(agent.isEmpty() ){
//                   editTextAgent.setError("Agent is not added!");
//               }else if(branch.isEmpty() ){
//                   editTextBranch.setError("Branch Number is not added!");
//               }else if(longitude.isEmpty() ){
//                   editTextLogitude.setError("Logitude is not added!");
//               }else if(latitude.isEmpty() ){
//                   editTextLatitude.setError("Latitude is not added!");
//               }
////               else if(photo.isEmpty() ){
////                   inputPassword.setError("Vehicle Number is not added!");
////               }
//               else {
//
//                   progressDialog.setMessage("Please wait....");
//                   progressDialog.setTitle("SignIn");
//                   progressDialog.setCanceledOnTouchOutside(false);
//                   progressDialog.show();
//
//
//                   sosMessage.setVehicle(vehicle);
//                   sosMessage.setInsuranceNo(insuranceNo);
//                   sosMessage.setCustomerNo(customerNo);
//                   sosMessage.setCustomer(customer);
//                   sosMessage.setContactNo(contactNo);
//                   sosMessage.setDateTime(dateTime);
//                   sosMessage.setAgent(agent);
//                   sosMessage.setBranch(branch);
//                   sosMessage.setLongitude(longitude);
//                   sosMessage.setLatitude(latitude);
//                   //sosMessage.setPhoto(imageUri);
//
//                   dbRef.child(String.valueOf(maxid+1)).setValue(sosMessage);
//
//                   Toast.makeText(CreateSOSActivity.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
//
//               }




               sosMessage.setVehicle(vehicle);
               sosMessage.setInsuranceNo(insuranceNo);
               sosMessage.setCustomerNo(customerNo);
               sosMessage.setCustomer(customer);
               sosMessage.setContactNo(contactNo);
               sosMessage.setDateTime(dateTime);
               sosMessage.setAgent(agent);
               sosMessage.setBranch(branch);
               sosMessage.setLongitude(longitude);
               sosMessage.setLatitude(latitude);
               //sosMessage.setPhoto(imageUri);

               dbRef.child(String.valueOf(maxid+1)).setValue(sosMessage);

               Toast.makeText(CreateSOSActivity.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Camera_code && requestCode ==RESULT_OK) {
//            //Get capture Image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            PhotoAccident.setImageBitmap(captureImage);
//
//            imageUri =  data.getData();
//            PhotoAccident.setImageURI(imageUri);

//            progressDialog.setMessage("Image is uploading");
//
//            Uri uri = data.getData();
//            StorageReference filePath= strRef.child("Photo").child(uri.getLastPathSegment());
//
//            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progressDialog.dismiss();
//                    Toast.makeText(CreateSOSActivity.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }

//        if(requestCode == Camera_code){
//            if(requestCode == Activity.RESULT_OK){
//
//            }
//        }


    }




}