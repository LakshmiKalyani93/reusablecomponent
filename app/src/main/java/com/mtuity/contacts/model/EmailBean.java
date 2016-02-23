package com.mtuity.contacts.model;

/**
 * Created by kalyani on 12/2/16.
 */
public class EmailBean {
    private String emailId;
    private String emailType;
    private String emailLabel;

    /**
     * @return emailLabel
     */
    public String getEmailLabel() {
        return emailLabel;
    }

    /**
     * @param emailLabel
     */
    public void setEmailLabel(String emailLabel) {
        this.emailLabel = emailLabel;
    }

    /**
     * @return emailType
     */
    public String getEmailType() {
        return emailType;
    }

    /**
     * @param emailType
     */
    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    /**
     * @return emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return " EmailId: " + emailId + " EmailType:  " + emailType + " EmailLabel: " + emailLabel;
    }
}
