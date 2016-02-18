package com.mtuity.contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.mtuity.contacts.model.SimContactBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalyani on 16/2/16.
 */
public class SIMContacts {


    private final Context mContxt;
    private String defaultString = "";

    public SIMContacts(Context context) {
        if (context != null) {
            mContxt = context;
        } else {
            throw new NullPointerException("Context should not be null.");
        }
    }

    /**
     * @return returns the list of SIM contacts..
     */
    public List<SimContactBean> getSIMContacts() {


        List<SimContactBean> simContactList = new ArrayList<SimContactBean>();
        defaultString = mContxt.getResources().getString(R.string.not_specified);
        try {

            Uri simUri = Uri.parse("content://icc/adn");
            Cursor cursor = mContxt.getContentResolver().query(simUri, null, null, null, null);

            Log.i("PhoneContact", "total: " + cursor.getCount());

            while (cursor.moveToNext()) {
                String name = "name";
                String number = "number";
                SimContactBean bean = new SimContactBean();

                String simContactName = cursor.getString(cursor.getColumnIndex(name));
                String simPhoneNumber = cursor.getString(cursor.getColumnIndex(number));

                simPhoneNumber.replaceAll("\\D", "");
                simPhoneNumber.replaceAll("&", "");
                simContactName = simContactName.replace("|", "");

                bean.setName(simContactName == null ? defaultString : simContactName);
                bean.setNumber(simPhoneNumber == null ? defaultString : simPhoneNumber);

                Log.i("PhoneContact", "name: " + simContactName + " phone: " + simPhoneNumber);
                simContactList.add(bean);
            }
            cursor.close();
        } catch (SecurityException | IllegalStateException e) {
            if (e instanceof SecurityException) {
                throw new SecurityException("Need to check the app permissions" + e);
            } else {
                throw new IllegalStateException("IllegalStateException" + e);
            }
        }

        return simContactList;
    }
}
