package com.mtuity.contacts.model;

/**
 * Created by kalyani on 10/2/16.
 */
public class PostalAddressBean {

    private String addressType;
    private String city;
    private String street;
    private String country;
    private String postCode;
    private String label;
    private String poBox;
    private String neighborHood;
    private String region;
    private String formattedAddress;

    /**
     * @return formattedAddress
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * @param formattedAddress
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    /**
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return neighborHood
     */
    public String getNeighborHood() {
        return neighborHood;
    }

    /**
     * @param neighborHood
     */
    public void setNeighborHood(String neighborHood) {
        this.neighborHood = neighborHood;
    }

    /**
     * @return poBox
     */
    public String getPoBox() {
        return poBox;
    }

    /**
     * @param poBox
     */
    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return addressType
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * @param addressType
     */
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return " FormattedAddress: " + formattedAddress + " AddressType: " + addressType + " Street: " + street + " City: " + city + " Country: " + country + " PostCode: " + postCode + " PoBox: " + poBox + " NeighborHood: " + neighborHood + " Region: " + region + " AddressLabel: " + label;
    }
}
