package com.mtuity.contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mtuity.contacts.model.ContactBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ContactBean> list = new ArrayList<>();
    private ContactAdapter adapter;
    private ListView listview;
    private Button makeCallBtn;
    private Button dialPadCallBtn;
    private Button getContactsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getContactsBtn = (Button) findViewById(R.id.heading);
        listview = (ListView) findViewById(R.id.contacts_list_view);
        makeCallBtn = (Button) findViewById(R.id.make_call);
        dialPadCallBtn = (Button) findViewById(R.id.dial_pad_call);

        makeCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPermissionsToMakePhoneCall()) {
                    new PhoneCall(MainActivity.this).makePhoneCall("8848032919");
                } else {
                    requestPhoneCallPermission();
                }
            }
        });

        dialPadCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPermissionsToMakePhoneCall()) {
                    new PhoneCall(MainActivity.this).showPhoneNumberOnDialPad("8848032919");
                } else {
                    requestPhoneCallPermission();
                }
            }
        });

        getContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hasPermissionsToReadContacts()) {
                     list = new MobileContacts(MainActivity.this).fetchContacts();
                    //list = new SIMContacts(MainActivity.this).getSIMContacts();
                } else {
                    requestReadContactsPermission();
                }
                Log.i("MobileContacts", "Contact List===========" + list.toString());

                setContactsAdapter();

            }
        });

    }

    private void setContactsAdapter() {
        if (list != null) {
            adapter = new ContactAdapter(MainActivity.this, R.layout.contact_list_item, list);
            listview.setAdapter(adapter);
        }
    }

    private void requestPhoneCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {
            Toast.makeText(MainActivity.this, "We need permission so you can call your friends.", Toast.LENGTH_LONG).show();
            requestResultPhoneCallPermission();
        } else {
            requestResultPhoneCallPermission();
        }
    }

    private void requestResultPhoneCallPermission() {
        try {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, Constants.CALL_PHONE_REQUEST);
        } catch (IllegalArgumentException e) {
            Log.i("RequestCallPermission", "IllegalArgumentException: " + e);
        }
    }

    private boolean hasPermissionsToMakePhoneCall() {
        return ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }


    private void requestReadContactsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(MainActivity.this, "We need permission so you can text your friends.", Toast.LENGTH_LONG).show();
            requestResultContactPermission();
        } else {
            requestResultContactPermission();
        }
    }

    private void requestResultContactPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, Constants.READ_CONTACTS_PERMISSION_REQUEST);
    }

    private boolean hasPermissionsToReadContacts() {
        return ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.READ_CONTACTS_PERMISSION_REQUEST
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, " ReadContacts Permission Granted", Toast.LENGTH_SHORT).show();

            // perform the action fetching contact here..like loading the contact list here

        } else if (requestCode == Constants.CALL_PHONE_REQUEST
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, " MakeCall Permission Granted", Toast.LENGTH_SHORT).show();

            if (PhoneCall.getRequestType() == Constants.PhoneCall.MAKE_CALL) {
                // perform actions here for directly placing the call...

            } else if (PhoneCall.getRequestType() == Constants.PhoneCall.DIAL_PAD_CALL) {
                // perform actions here for placing the call on to the dial pad first..
            }
        }

    }

}
