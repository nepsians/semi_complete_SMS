package com.gameconnect3.ujjwal.firebasedemo;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ButtomClass extends Fragment {

    EditText Username,Password;
    Button LoginBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.button_frame,null);

        Username=view.findViewById(R.id.UserName);
        Password=view.findViewById(R.id.Password);
        LoginBtn=view.findViewById(R.id.LoginButton);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginProcess();
            }
        });
        return view;
    }

    public void LoginProcess(){

       // intialize();
        if (!valid()){
            Toast.makeText(getActivity(), "Error in Login", Toast.LENGTH_SHORT).show();
        }else{
            BackendProcess();

        }



    }


    public boolean valid(){
        String User_Name=Username.getText().toString();
        String Pass_word=Password.getText().toString();

        boolean valid=true;
            if (User_Name.isEmpty()){
                Username.setError("Please enter the UserName");
                valid=false;
            }
            if (User_Name.length()>20){
                 Username.setError("Username too long");
                 valid=false;
            }
            if (Pass_word.isEmpty()){
                Password.setError("Please enter the Password");
                valid=false;
            }
            return valid;
    }

    public void BackendProcess(){
     //   Toast.makeText(getContext(),"Login Button has been clicked",Toast.LENGTH_SHORT).show();
        String url="http://nepsian.000webhostapp.com/technorio_sms.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Success")){
                    NextActivity();
                    Toast.makeText(getActivity(), "Login Successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
            },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Login Error: "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params= new HashMap<>();
                params.put("Username",Username.getText().toString().trim());
                params.put("Password",Password.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(request);
    }


    public void NextActivity(){
        Intent intent=new Intent(getActivity(),SecondMainActivity.class);
        startActivity(intent);
    }

}
