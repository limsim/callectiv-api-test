package com.callectiv.api.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class ContactResource {

    private String message;

    private String phone;

    private String sms;

    private String email;

    public ContactResource() {
    }

    @XmlElement(name = "message")
    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    @XmlElement(name = "sms")
    public String getSms() {
	return sms;
    }

    public void setSms(String sms) {
	this.sms = sms;
    }

    @XmlElement(name = "email")
    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return "ContactResource [phone=" + phone + ", sms=" + sms + ", email=" + email + ", message=" + message + "]";
    }

}
