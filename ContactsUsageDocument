Contacts Reusable Component :

 It is used to get contacts information such as contact name, contact number, email id, postal address of single
 contact.


 Suppose, if a single contact contains list of phone numbers, email ids, and postal addresses they also be retrieved
 with this contact reusable component.
 Those are :
 1. Fetching contacts : You just need to call fetchContacts() method, which has been explained further

            - returns the list of contacts of type ContactBean

    - Contact Name
    - Contact Number(s)
    - Contact Picture
    - Contact Email Id(s)
    - Contact Postal Address(s)

 2. Phone call :

    - Place Call Directly - You just need to call makePhoneCall(String PhoneNumber);
    - Get Number on the Dial Pad - You just need to call getDialPadPhoneNumber(String PhoneNumber);


 1. In order to get contacts info, we need to call the method

  if (hasPermissionsToReadContacts()) {
        // it retrieves the list of contacts of type ContactBean
       list = new MobileContacts(MainActivity.this).fetchContacts();
  } else {
         requestReadContactsPermission();
  }

 2(I) In order to make a call directly to the given phone number, we need to call the method

  if (hasPermissionsToMakePhoneCall()) {
    // it directly places the call for the given number
     new PhoneCall(MainActivity.this).makePhoneCall("8848032919");
  } else {
        requestPhoneCallPermission();
  }

  2(II) In order to get the number on the dial pad, we need to call the method

   if (hasPermissionsToMakePhoneCall()) {
          // it places the given number on to the dial pad
          new PhoneCall(MainActivity.this).getDialPadPhoneNumber("8848032919");
   } else {
           requestPhoneCallPermission();
   }

Note :

Beginning in Android 6.0 (API level 23), users grant permissions to apps while the app is running, not when they install the app.
This approach streamlines the app install process, since the user does not need to grant permissions when they install or update the app.
It also gives the user more control over the app's functionality;

for example, a user could choose to give a camera app access to the camera but not to the device location.
The user can revoke the permissions at any time, by going to the app's Settings screen.

user can revoke the permissions at any time. For this reason, we need to check permissions in the application
while we built itself.

Manifest permissions : (pre-Marshmallow devices) Must declare in the Manifest file.

    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.CALL_PHONE" />


Method to be imported into the activity, in which we are going to use this reusable component methods:

Target Api : 23 then we need to add permissions dynamically

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

String Resources : <string name="not_specified">Not Specified</string>

 Improvements :

 1. Adding a contact
 2. Deleting a contact
 3. Editing a contact



