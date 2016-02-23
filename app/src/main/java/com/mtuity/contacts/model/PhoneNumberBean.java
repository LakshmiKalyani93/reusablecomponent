package com.mtuity.contacts.model;

/**
 * Created by kalyani on 12/2/16.
 */
public class PhoneNumberBean {
    private String number;
    private String numberType;
    private String numberLabel;

    /**
     * @return numberLabel
     */
    public String getNumberLabel() {
        return numberLabel;
    }

    /**
     * @param label
     */
    public void setNumberLabel(String label) {
        this.numberLabel = label;
    }

    /**
     * @return numberType
     */
    public String getNumberType() {
        return numberType;
    }

    /**
     * @param numberType
     */
    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    /**
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return " PhoneNUmber: " + number + " NumberType:  " + numberType + " NumberLabel: " + numberLabel;
    }
}
