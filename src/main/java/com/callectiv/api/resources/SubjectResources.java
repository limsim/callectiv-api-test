package com.callectiv.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subjects")
public class SubjectResources {
    List<SubjectResource> subjects = new ArrayList<SubjectResource>();

    @XmlElements(value = { @XmlElement(name = "subject", type = SubjectResource.class) })
    public List<SubjectResource> getSubjects() {
	return subjects;
    }

    public void setSubjects(List<SubjectResource> subjects) {
	this.subjects = subjects;
    }

}
