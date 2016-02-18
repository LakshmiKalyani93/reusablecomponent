package com.mtuity.contacts;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by kalyani on 16/2/16.
 */
public class PhoneCall {

    private static int REQUEST_TYPE = 0;
    private final Context context;

    public PhoneCall(Context contxt) {
        if (contxt != null) {
            context = contxt;
        } else {
            throw new NullPointerException("Context should not be null.");
        }
    }

    public static int getRequestType() {
        return REQUEST_TYPE;
    }

    /**
     * @param phoneNumber for which the call is placed
     */
    public void makePhoneCall(String phoneNumber) {
        REQUEST_TYPE = Constants.PhoneCall.MAKE_CALL;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel: " + phoneNumber));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(callIntent);
    }

    /**
     * @param phoneNumber for which the call is placed
     */
    public void showPhoneNumberOnDialPad(String phoneNumber) {
        REQUEST_TYPE = Constants.PhoneCall.DIAL_PAD_CALL;
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel: " + phoneNumber));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(dialIntent);
    }
}
