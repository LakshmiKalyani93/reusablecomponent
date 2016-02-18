package com.mtuity.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import com.mtuity.contacts.model.ContactBean;
import com.mtuity.contacts.model.EmailBean;
import com.mtuity.contacts.model.PhoneNumberBean;
import com.mtuity.contacts.model.PostalAddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalyani on 10/2/16.
 */
public class MobileContacts {

    private final Context mContext;
    private String defaultString = "";

    public MobileContacts(Context context) {
        if (context != null) {
            mContext = context;
        } else {
            throw new NullPointerException("Context should not be null.");
        }
    }

    private static class ContactNumberKeys {
        
        private static final Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        private static final String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        private static final String NUMBER_DATA = ContactsContract.CommonDataKinds.Phone.NUMBER;
        private static final String NUMBER_TYPE_DATA = ContactsContract.CommonDataKinds.Phone.TYPE;
        private static final String NUMBER_LABEL_DATA = ContactsContract.CommonDataKinds.Phone.LABEL;

        private ContactNumberKeys() {
            // default constructor
        }

    }

    private static class EmailKeys {

        private static final Uri EMAIL_CONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        private static final String EMAIL_CONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        private static final String EMAIL_DATA = ContactsContract.CommonDataKinds.Email.DATA;
        private static final String EMAIL_TYPE_DATA = ContactsContract.CommonDataKinds.Email.TYPE;
        private static final String EMAIL_LABEL_DATA = ContactsContract.CommonDataKinds.Email.LABEL;

        private EmailKeys() {
            // default constructor
        }
    }

    private static class PostalAddressKeys {

        private static final Uri ADDRESS_CONTENT_URI = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;
        private static final String ADDRESS_CONTACT_ID = ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID;
        private static final String FORMATTED_ADDRESS_DATA = ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS;
        private static final String LABEL_DATA = ContactsContract.CommonDataKinds.StructuredPostal.LABEL;
        private static final String PO_BOX_DATA = ContactsContract.CommonDataKinds.StructuredPostal.POBOX;
        private static final String REGION_DATA = ContactsContract.CommonDataKinds.StructuredPostal.REGION;
        private static final String STREET_DATA = ContactsContract.CommonDataKinds.StructuredPostal.STREET;
        private static final String CITY_DATA = ContactsContract.CommonDataKinds.StructuredPostal.CITY;
        private static final String COUNTRY_DATA = ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY;
        private static final String POSTCODE_DATA = ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE;
        private static final String NEIGHBORHOOD_DATA = ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD;
        private static final String ADDRESS_TYPE_DATA = ContactsContract.CommonDataKinds.StructuredPostal.TYPE;

        private PostalAddressKeys() {
            // default constructor
        }

    }

    /**
     * @return it returns the list of contacts of the type ContactBean
     */
    protected List<ContactBean> fetchContacts() {

        List<ContactBean> contactBeanList = new ArrayList<ContactBean>();

        defaultString = mContext.getResources().getString(R.string.not_specified);

        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String displayName = ContactsContract.Contacts.DISPLAY_NAME;
        String hasContactNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        ContentResolver contentResolver = mContext.getContentResolver();

        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);

        // Loop for every contact in the phone
        if (cursor != null && cursor.moveToFirst()) {

            do {

                ContactBean bean = new ContactBean();
                String contactId = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(displayName));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(hasContactNumber)));

                if (hasPhoneNumber > 0) {

                    bean = queryPhoneNumberList(contentResolver, bean, contactId, name);

                    bean = queryEmailList(contentResolver, bean, contactId);

                    bean = queryAddressList(contentResolver, bean, contactId);

                    contactBeanList.add(bean);
                }
            } while (cursor.moveToNext());

        }

        return contactBeanList;
    }


    /**
     * @param contentResolver provides applications access to the content model.
     * @param bean            object reference to the ContactBean
     * @param contactId       is the id of a single contact
     * @return returns the contactBean object
     */
    private ContactBean queryAddressList(ContentResolver contentResolver, ContactBean bean, String contactId) {


        ContactBean addressBean = bean;

        //Query and loop for every address of the contact
        Cursor addressCursor = contentResolver.query(PostalAddressKeys.ADDRESS_CONTENT_URI, null, PostalAddressKeys.ADDRESS_CONTACT_ID + " = ?", new String[]{contactId}, null);

        List<PostalAddressBean> addressList = new ArrayList<PostalAddressBean>();
        if (addressCursor != null && addressCursor.moveToFirst()) {

            do {
                PostalAddressBean addressBeanObj = new PostalAddressBean();
                String formattedAddress = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.FORMATTED_ADDRESS_DATA));
                String addressLabel = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.LABEL_DATA));
                String street = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.STREET_DATA));
                String city = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.CITY_DATA));
                String country = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.COUNTRY_DATA));
                String poBox = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.PO_BOX_DATA));
                String postCode = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.POSTCODE_DATA));
                String neighborHood = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.NEIGHBORHOOD_DATA));
                String region = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.REGION_DATA));
                String addressType = addressCursor.getString(addressCursor.getColumnIndex(PostalAddressKeys.ADDRESS_TYPE_DATA));

                addressBeanObj.setFormattedAddress(formattedAddress == null ? defaultString : formattedAddress);
                addressBeanObj.setLabel(addressLabel == null ? defaultString : addressLabel);
                addressBeanObj.setStreet(street == null ? defaultString : street);
                addressBeanObj.setCity(city == null ? defaultString : city);
                addressBeanObj.setCountry(country == null ? defaultString : country);
                addressBeanObj.setPoBox(poBox == null ? defaultString : poBox);
                addressBeanObj.setPostCode(postCode == null ? defaultString : postCode);
                addressBeanObj.setNeighborHood(neighborHood == null ? defaultString : neighborHood);
                addressBeanObj.setRegion(region == null ? defaultString : region);
                addressBeanObj.setAddressType(addressType == null ? defaultString : addressType);
                addressList.add(addressBeanObj);

                addressBean.setPostalAddressList(addressList);
            } while (addressCursor.moveToNext());
        }
        addressCursor.close();
        return addressBean;
    }

    /**
     * @param contentResolver provides applications access to the content model.
     * @param bean            object reference to the ContactBean
     * @param contactId       is the id of a single contact
     * @return returns the contactBean object
     */
    private ContactBean queryEmailList(ContentResolver contentResolver, ContactBean bean, String contactId) {


        ContactBean emailBean = bean;

        // Query and loop for every email of the contact
        Cursor emailCursor = contentResolver.query(EmailKeys.EMAIL_CONTENT_URI, null, EmailKeys.EMAIL_CONTACT_ID + " = ?", new String[]{contactId}, null);

        List<EmailBean> emailList = new ArrayList<EmailBean>();
        if (emailCursor != null && emailCursor.moveToFirst()) {
            do {
                EmailBean emailBeanObj = new EmailBean();

                String emailId = emailCursor.getString(emailCursor.getColumnIndex(EmailKeys.EMAIL_DATA));
                String emailType = emailCursor.getString(emailCursor.getColumnIndex(EmailKeys.EMAIL_TYPE_DATA));
                String emailLabel = emailCursor.getString(emailCursor.getColumnIndex(EmailKeys.EMAIL_LABEL_DATA));

                emailBeanObj.setEmailId(emailId == null ? defaultString : emailId);
                emailBeanObj.setEmailType(emailType == null ? defaultString : emailType);
                emailBeanObj.setEmailLabel(emailLabel == null ? defaultString : emailLabel);

                emailList.add(emailBeanObj);

                emailBean.setEmailList(emailList);
            }
            while (emailCursor.moveToNext());

        }
        emailCursor.close();

        return emailBean;
    }

    /**
     * @param contentResolver provides applications access to the content model.
     * @param bean            object reference to the ContactBean
     * @param id              is the id of a single contact
     * @return returns the contactBean object
     */
    private ContactBean queryPhoneNumberList(ContentResolver contentResolver, ContactBean bean, String id, String name) {


        ContactBean phoneBean = bean;

        // Query and loop for every phone number of the contact
        Cursor phoneCursor = contentResolver.query(ContactNumberKeys.PHONE_CONTENT_URI, null, ContactNumberKeys.PHONE_CONTACT_ID + " = ?", new String[]{id}, null);

        List<PhoneNumberBean> phoneList = new ArrayList<PhoneNumberBean>();
        if (phoneCursor != null && phoneCursor.moveToFirst()) {
            do {
                PhoneNumberBean numberBean = new PhoneNumberBean();

                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactNumberKeys.NUMBER_DATA));
                String phoneType = phoneCursor.getString(phoneCursor.getColumnIndex(ContactNumberKeys.NUMBER_TYPE_DATA));
                String phoneLabel = phoneCursor.getString(phoneCursor.getColumnIndex(ContactNumberKeys.NUMBER_LABEL_DATA));

                numberBean.setNumber(phoneNumber == null ? defaultString : phoneNumber);
                numberBean.setNumberLabel(phoneLabel == null ? defaultString : phoneLabel);
                numberBean.setNumberType(phoneType == null ? defaultString : phoneType);

                phoneList.add(numberBean);

                if (phoneNumber != null) {
                    Bitmap thumbNail = new ImageLoader(mContext, phoneNumber).addThumbnail();

                    phoneBean.setContactName(name);

                    Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
                            R.mipmap.ic_launcher);
                    phoneBean.setContactPicture(thumbNail == null ? icon : thumbNail);
                }
                phoneBean.setPhoneList(phoneList);
            } while (phoneCursor.moveToNext());
        }

        phoneCursor.close();
        return phoneBean;
    }


}
