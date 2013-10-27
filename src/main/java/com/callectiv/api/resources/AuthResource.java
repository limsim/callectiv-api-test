package com.callectiv.api.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authentication")
public class AuthResource {

    private String apikey;

    private String secret;

    @XmlElement(name = "apikey")
    public String getApikey() {
	return apikey;
    }

    public void setApikey(String apiKey) {
	this.apikey = apiKey;
    }

    @XmlElement(name = "secret")
    public String getSecret() {
	return secret;
    }

    public void setSecret(String secret) {
	this.secret = secret;
    }

    @Override
    public String toString() {
	return "AuthResource [apikey=" + apikey + ", secret=" + secret + "]";
    }

}
