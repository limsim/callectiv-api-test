package com.callectiv.api.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "connection")
public class ConnectionResource {
    private Long id;
    private ContactResource from;
    private ContactResource to;
    private String subjectReference;
    private String startDateTime;
    private String timeZone;
    private String actualStartDateTime;
    private String systemStartDateTime;
    private String receivedDateTime;
    private String status;
    private Double duration;
    private Long customerId;

    @XmlElement(name = "from")
    public ContactResource getFrom() {
	return from;
    }

    public void setFrom(ContactResource from) {
	this.from = from;
    }

    @XmlElement(name = "to")
    public ContactResource getTo() {
	return to;
    }

    public void setTo(ContactResource to) {
	this.to = to;
    }

    @XmlElement(name = "startDateTime")
    public String getStartDateTime() {
	return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
	this.startDateTime = startDateTime;
    }

    @XmlElement(name = "actualStartDateTime")
    public String getActualStartDateTime() {
	return actualStartDateTime;
    }

    public void setActualStartDateTime(String actualStartDateTime) {
	this.actualStartDateTime = actualStartDateTime;
    }

    @XmlElement(name = "systemStartDateTime")
    public String getSystemStartDateTime() {
	return systemStartDateTime;
    }

    public void setSystemStartDateTime(String systemStartDateTime) {
	this.systemStartDateTime = systemStartDateTime;
    }

    @XmlElement(name = "status")
    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @XmlElement(name = "duration")
    public Double getDuration() {
	return duration;
    }

    public void setDuration(Double duration) {
	this.duration = duration;
    }

    @XmlElement(name = "subjectReference")
    public String getSubjectReference() {
	return subjectReference;
    }

    public void setSubjectReference(String subjectReference) {
	this.subjectReference = subjectReference;
    }

    @XmlElement(name = "id")
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @XmlElement(name = "timeZone")
    public String getTimeZone() {
	return timeZone;
    }

    public void setTimeZone(String timeZone) {
	this.timeZone = timeZone;
    }

    @XmlElement(name = "receivedDateTime")
    public String getReceivedDateTime() {
	return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
	this.receivedDateTime = receivedDateTime;
    }

    @Override
    public String toString() {
	return "ConnectionResource [id=" + id + ", from=" + from + ", to=" + to + ", startDateTime=" + startDateTime
		+ ", timeZone=" + timeZone + ", actualStartDateTime=" + actualStartDateTime + ", systemStartDateTime="
		+ systemStartDateTime + ", receivedDateTime=" + receivedDateTime + ", status=" + status + ", duration="
		+ duration + ", subjectReference=" + subjectReference + ", customerId=" + customerId + " ]";
    }

}
