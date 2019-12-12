package com.example.esoftwaricaapp.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.esoftwaricaapp.Contacts;
import com.example.esoftwaricaapp.MainActivity;
import com.example.esoftwaricaapp.R;

public class DashboardFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener {

    private DashboardViewModel dashboardViewModel;
    RadioButton rdomale,rdofemale,rdoothers;
    RadioGroup rdogroup;
    EditText etName,etAddress,etAge;
    Button btnsave;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.addstudent_layout, container, false);


        rdogroup=root.findViewById(R.id.rdoGroup);
        etName=root.findViewById(R.id.etName);
        etAddress=root.findViewById(R.id.etAddress);
        etAge=root.findViewById(R.id.etAge);
        btnsave=root.findViewById(R.id.btnSave);

        rdogroup.setOnCheckedChangeListener( this);
        btnsave.setOnClickListener(this);



        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    String fullname,address,age,gender;

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSave){
            Toast.makeText(getActivity(),"save",Toast.LENGTH_SHORT).show();
            fullname=etName.getText().toString();
            age=etAge.getText().toString();
            address=etAddress.getText().toString();

            if(validate()){
               MainActivity.contact.add(new Contacts(fullname,address,gender,Integer.parseInt(age)));
               Toast.makeText(getContext(),"Student added",Toast.LENGTH_SHORT).show();
            }
        }



    }
    private  boolean validate(){
        if(TextUtils.isEmpty(etName.getText())){
            etName.setError("please enter full name");
            etName.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(etAddress.getText())){
            etAddress.setError("please enter address");
            etAddress.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(etAge.getText())){
            etAge.setError("please enter age");
            etAge.requestFocus();
            return false;
        }

        else if(TextUtils.isEmpty(gender)){
            Toast.makeText(getContext(),"please select gender",Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        if(checkedId==R.id.rdobtnMale){
            gender="male";
        }
        if(checkedId==R.id.rdobtnFemale){
            gender="female";
        }
        if(checkedId==R.id.rdobtnOthers){
            gender="other";
        }

    }
}