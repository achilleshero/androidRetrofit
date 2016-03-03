package com.wazoow.myapp;

/**
 * Created by swordmaster on 3/3/2016.
 */
public class ReceiveData {
    public String phone;
    public String internationalCode;
    public String pin;
    public String token;

    public ReceiveData(String phone, String internationalCode, String pin, String token)
    {
        this.phone = phone;
        this.internationalCode = internationalCode;
        this.pin = pin;
        this.token = token;
    }
}
