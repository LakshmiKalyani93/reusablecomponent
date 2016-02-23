package com.mtuity.contacts.model;

/**
 * Created by kalyani on 16/2/16.
 */
public class SimContactBean {
    private String number;
    private String name;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
        return " SIM Contact Name : " + name + " SIM Contact Number: " + number;
    }
}
