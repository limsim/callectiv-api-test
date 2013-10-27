package com.callectiv.api.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subject")
public class SubjectResource {

    private String reference;

    private ContactResource contact;

    private String message;

    private String status;

    private String creationDateTime;

    @XmlElement(name = "reference")
    public String getReference() {
	return reference;
    }

    public void setReference(String reference) {
	this.reference = reference;
    }

    @XmlElement(name = "contact", type = ContactResource.class)
    public ContactResource getContact() {
	return contact;
    }

    public void setContact(ContactResource contact) {
	this.contact = contact;
    }

    @XmlElement(name = "message")
    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @XmlElement(name = "status")
    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @XmlElement(name = "creationDateTime")
    public String getCreationDateTime() {
	return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
	this.creationDateTime = creationDateTime;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((contact == null) ? 0 : contact.hashCode());
	result = prime * result + ((creationDateTime == null) ? 0 : creationDateTime.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + ((reference == null) ? 0 : reference.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SubjectResource other = (SubjectResource) obj;
	if (status != other.status)
	    return false;
	if (contact == null) {
	    if (other.contact != null)
		return false;
	} else if (!contact.equals(other.contact))
	    return false;
	if (creationDateTime == null) {
	    if (other.creationDateTime != null)
		return false;
	} else if (!creationDateTime.equals(other.creationDateTime))
	    return false;
	if (message == null) {
	    if (other.message != null)
		return false;
	} else if (!message.equals(other.message))
	    return false;
	if (reference == null) {
	    if (other.reference != null)
		return false;
	} else if (!reference.equals(other.reference))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "SubjectResource [reference=" + reference + ", contact=" + contact + ", message=" + message
		+ ", status=" + status + ", creationDateTime=" + creationDateTime + "]";
    }

}
