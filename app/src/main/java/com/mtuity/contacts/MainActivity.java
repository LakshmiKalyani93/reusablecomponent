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

    private static final String TAG = MainActivity.class.getSimpleName();
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
                if (HasPermissionsToMakePhoneCall()) {
                    new PhoneCall(MainActivity.this).makePhoneCall("8848032919");
                } else {
                    requestPhoneCallPermission();
                }
            }
        });

        dialPadCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HasPermissionsToMakePhoneCall()) {
                    new PhoneCall(MainActivity.this).getDialPadPhoneNumber("8848032919");
                } else {
                    requestPhoneCallPermission();
                }
            }
        });

        getContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (HasPermissionsToReadContacts()) {
                    list = new MobileContacts(MainActivity.this).fetchContacts();
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
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 100);
    }

    private boolean HasPermissionsToMakePhoneCall() {
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
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 123);
    }

    private boolean HasPermissionsToReadContacts() {
        return ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, " ReadContacts Permission Granted", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 100
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, " MakeCall Permission Granted", Toast.LENGTH_SHORT).show();
        }

    }

}
