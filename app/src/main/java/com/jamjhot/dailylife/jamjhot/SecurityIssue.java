package com.jamjhot.dailylife.jamjhot;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;

public class SecurityIssue extends AppCompatActivity implements OnMapReadyCallback  {

    LocationManager locationManager;
    static final int REQUEST_LOCATION = 1;
    private GoogleMap mMap;
    MarkerOptions mo;
    Marker marker;
    double latti, longi;
    Button rst_cam;
    EditText typeyourownText;


    DbHelperVab dbHelperVab;

    RadioGroup grp;
    RadioButton rda, rdb, rdc, rdd;
    Button sendMassage;

    LinkedList<String> phoneLi= new LinkedList<>();

    String totalText;
    int checker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_issue);

        dbHelperVab= new DbHelperVab(SecurityIssue.this);
        Log.e("yyy", "checked");


        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        rdd=(RadioButton)findViewById(R.id.radio3);
        typeyourownText=(EditText) findViewById(R.id.typeyourown);
//        butNext=(Button)findViewById(R.id.button1);
        sendMassage=(Button) findViewById(R.id.buttonSend);
        grp=(RadioGroup)findViewById(R.id.radioGroup1);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mo = new MarkerOptions().position(new LatLng(0, 0)).title("My Current Location");
        getLocation();
        Log.e("KEY", "data : "+latti);
        Log.e("KEY", "data2 : "+longi);
        String myLoc= "Here is my \n Langitude:"+longi+" \n Latitude:"+latti+"\n";
        String locationStr="Sender Location: \n"+"https://www.google.com/maps/search/?api=1&query="+latti+","+longi;

        totalText= myLoc +locationStr;
        rda.setText("I\'m in Trouble. \n");
        rdb.setText("Meet Me Here. \n");
        rdc.setText("Help Me! \n");
        rdd.setText("Instant Reply! \n");
        Toast.makeText(getApplicationContext(),"Help ME",Toast.LENGTH_LONG);

        checker=0;

        Cursor cursor = dbHelperVab.getAllHOMEDATAs();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    checker++;

//                    Log.e("yyy", "checked"+);
                    phoneLi.add(cursor.getString(2));
                    Toast.makeText(getApplicationContext(),"Name"+cursor.getString(1)+"\n Numbers"+cursor.getString(2),Toast.LENGTH_LONG);

                } while (cursor.moveToNext());
            }
        }

        typeyourownText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grp.check(rdd.getId());
//                rdd.setSelected(true);
            }
        });

        sendMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
//                Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());

                if (checker!=0) {

                    Log.e("mmmmmmmm","ttt"+rdd.isChecked() );
                    if (rdd.isChecked()){
                        Log.e("mmmmmmmm","ttt"+typeyourownText.getText().toString() );
                        if (typeyourownText.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "Wrong Input. You Typed Nothing", Toast.LENGTH_LONG).show();

                        }
                        else {
                            String typedData = typeyourownText.getText().toString();
//                            Toast.makeText(getApplicationContext(), "Answer" + typedData, Toast.LENGTH_LONG).show();
                            String strnum = "";
                            for (int i=0;i<phoneLi.size();i++){
                                strnum =strnum+";"+phoneLi.get(i);
                            }

//                    Uri smsToUri = Uri.parse("smsto:" + strnum);
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + strnum));
                            intent.putExtra("sms_body", "Help \n"+typedData+"\n"+totalText);
                            startActivity(intent);
                        }

                    }else {
                        answer.getText();
//                        Toast.makeText(getApplicationContext(), "Answer" + answer.getText(), Toast.LENGTH_LONG).show();
                        String strnum = "";
                        for (int i=0;i<phoneLi.size();i++){
                            strnum =strnum+";"+phoneLi.get(i);
                        }

//                    Uri smsToUri = Uri.parse("smsto:" + strnum);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + strnum));
                        intent.putExtra("sms_body", "Help \n"+answer.getText()+totalText);
                        startActivity(intent);
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Nothing Found",Toast.LENGTH_LONG).show();
                }

            }
        });

//        if (phoneLi != null) {
//
//            for (int i = 0; i < phoneLi.size(); i++) {
//                String message = "Help";
//                String tempMobileNumber = phoneLi.get(i).toString();
//                sendSMS(tempMobileNumber, message);
//            }
//        }

//        for (int i=0; i< phoneLi.size();i++) {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneLi.get(i)));
//            intent.putExtra("sms_body", "I am in Danger");
//            startActivity(intent);
//        }


    }

    void getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&   ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
                latti = location.getLatitude();
                longi = location.getLongitude();


            } else {
                //GPS not found
                Toast.makeText(getApplicationContext(), "GPS is Not enable", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }


//    private void sendSMS(String phoneNumber, String message) {
//        String SENT = "SMS_SENT";
//        String DELIVERED = "SMS_DELIVERED";
//
//        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
//                SENT), 0);
//
//        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
//                new Intent(DELIVERED), 0);
//
//        // ---when the SMS has been sent---
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode()) {
//                    case Activity.RESULT_OK:
//                        ContentValues values = new ContentValues();
//                        for (int i = 0; i < phoneLi.size() - 1; i++) {
//                            values.put("address", phoneLi.get(i).toString());// txtPhoneNo.getText().toString());
//                            values.put("body", "Help");
//                        }
//                        getContentResolver().insert(
//                                Uri.parse("content://sms/sent"), values);
//                        Toast.makeText(getBaseContext(), "SMS sent",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    /*case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                        Toast.makeText(getBaseContext(), "Generic failure",
//                                Toast.LENGTH_SHORT).show();
//                        break;*/
//                    case SmsManager.RESULT_ERROR_NO_SERVICE:
//                        Toast.makeText(getBaseContext(), "No service",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case SmsManager.RESULT_ERROR_NULL_PDU:
//                        Toast.makeText(getBaseContext(), "Null PDU",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case SmsManager.RESULT_ERROR_RADIO_OFF:
//                        Toast.makeText(getBaseContext(), "Radio off",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        }, new IntentFilter(SENT));
//
//        // ---when the SMS has been delivered---
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context arg0, Intent arg1) {
//                switch (getResultCode()) {
//                    case Activity.RESULT_OK:
//                        Toast.makeText(getBaseContext(), "SMS delivered",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                    case Activity.RESULT_CANCELED:
//                        Toast.makeText(getBaseContext(), "SMS not delivered",
//                                Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        }, new IntentFilter(DELIVERED));
//
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
//    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
