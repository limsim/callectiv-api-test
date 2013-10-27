package com.callectiv.api.resources;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public class ConnectionStatusResource {
    private Date date = Calendar.getInstance().getTime();

    private String externalCallStatus = "";

    private String externalCallId = "";

    private String callDuration = "0";

    private String recordingUrl = "";

    public ConnectionStatusResource() {

    }

    @XmlElement
    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public String getExternalCallStatus() {
	return externalCallStatus;
    }

    public void setExternalCallStatus(String externalCallStatus) {
	this.externalCallStatus = externalCallStatus;
    }

    public String getExternalCallId() {
	return externalCallId;
    }

    public void setExternalCallId(String externalCallId) {
	this.externalCallId = externalCallId;
    }

    public String getCallDuration() {
	return callDuration;
    }

    public void setCallDuration(String callDuration) {
	this.callDuration = callDuration;
    }

    public String getRecordingUrl() {
	return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
	this.recordingUrl = recordingUrl;
    }

    @Override
    public String toString() {
	return "CallStatusResource [date=" + date + ", externalCallStatus=" + externalCallStatus + ", externalCallId="
		+ externalCallId + ", callDuration=" + callDuration + ", recordingUrl=" + recordingUrl + "]";
    }

}
