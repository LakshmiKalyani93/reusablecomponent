package com.mtuity.contacts.model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by kalyani on 10/2/16.
 */

public class ContactBean {

    private List<PhoneNumberBean> phoneList;
    private String contactName;
    private List<EmailBean> emailList;
    private List<PostalAddressBean> postalAddressList;
    private Bitmap contactPicture;

    /**
     * @return contactPicture
     */
    public Bitmap getContactPicture() {
        return contactPicture;
    }

    /**
     * @param contactPicture
     */
    public void setContactPicture(Bitmap contactPicture) {
        this.contactPicture = contactPicture;
    }

    /**
     * @return postalAddressList
     */
    public List<PostalAddressBean> getPostalAddressList() {
        return postalAddressList;
    }

    /**
     * @param postalAddressList
     */
    public void setPostalAddressList(List<PostalAddressBean> postalAddressList) {
        this.postalAddressList = postalAddressList;
    }

    /**
     * @return emailList
     */
    public List<EmailBean> getEmailList() {
        return emailList;
    }

    /**
     * @param emailList
     */
    public void setEmailList(List emailList) {
        this.emailList = emailList;
    }

    /**
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return phoneList
     */
    public List<PhoneNumberBean> getPhoneList() {
        return phoneList;
    }

    /**
     * @param phoneList
     */
    public void setPhoneList(List<PhoneNumberBean> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public String toString() {
        return "Phone: " + phoneList + " Name: " + contactName + " Email: " + emailList + " Address: " + postalAddressList + " Picture: " + contactPicture + "\n";
    }
}
