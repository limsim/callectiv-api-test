package com.callectiv.api.resources;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authorization")
public class AuthTokenResource {

    private String token;

    private String expiryTime;

    @XmlElement(name = "token")
    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    @XmlAttribute(name = "expiryTime")
    public String getExpiryTime() {
	return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
	this.expiryTime = expiryTime;
    }

}
