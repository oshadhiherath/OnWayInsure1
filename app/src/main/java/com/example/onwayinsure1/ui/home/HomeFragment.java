package com.example.onwayinsure1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.onwayinsure1.AddClaimActivity;
import com.example.onwayinsure1.ClaimHistroryActivity;
import com.example.onwayinsure1.CreateSOSActivity;
import com.example.onwayinsure1.VehiclesActivity;
import com.example.onwayinsure1.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageButton addClaimButton ,btnClaimHistory, btnVehicles, btnCreateSOS;
//    private TextView CurrentDateText;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        //set Action bar Title
        setActionBarTitile("Hello Oshadi");

        //Get current date/ time and set it on the field
//        CurrentDateText = binding.CurrentDateText;
//        calendar = Calendar.getInstance();
//        dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
//        date = dateFormat.format(calendar.getTime());
//        binding.CurrentDateText.setText(date);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        addClaimButton = binding.btnAddClaim;
        //btnClaimHistory = binding.btnClaimHistory;
        //btnVehicles = binding.btnVehicles;
        btnCreateSOS = binding.btnCreateSOS;

        addClaimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getActivity().getApplicationContext(), AddClaimActivity.class));
            }
        });

//        btnClaimHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getContext().startActivity(new Intent(getActivity().getApplicationContext(), ClaimHistroryActivity.class));
//            }
//        });

//        btnVehicles.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getContext().startActivity(new Intent(getActivity().getApplicationContext(), VehiclesActivity.class));
//            }
//        });

        btnCreateSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getActivity().getApplicationContext(), CreateSOSActivity.class));


            }
        });



        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void setActionBarTitile(String title) {
        //
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}