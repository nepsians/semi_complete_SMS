package com.gameconnect3.ujjwal.firebasedemo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TopClass extends Fragment {

    Context c;
    TextView txt_token, from, message;
    EditText text_message;
    Button btn_send;

   /* public void TopClass(Context context){
        c=context;
    }
*/
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.top_frame,null);
        txt_token = view.findViewById(R.id.textview_token);
       /* from = view.findViewById(R.id.from);
        message = view.findViewById(R.id.message);
*/
        text_message = view.findViewById(R.id.text_message);
        btn_send = view.findViewById(R.id.btn_send);

        txt_token.setText(SharedPrefManager.getInstance(getActivity()).getToken());

        /*
        from.setText("AA");
        message.setText("aa");*/

        Log.d("token", SharedPrefManager.getInstance(getActivity()).getToken());

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });


        return view;
    }



    public void sendMessage(){

        String phoneNo = "9860999594";
        String msg = text_message.getText().toString();


        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, msg, null, null);
        Toast.makeText(getActivity(), "Message Sent",
                Toast.LENGTH_LONG).show();


    }
}
