package com.callectiv.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "connections")
public class ConnectionResources {
    List<ConnectionResource> connections = new ArrayList<ConnectionResource>();

    @XmlElements(value = { @XmlElement(name = "connection", type = ConnectionResource.class) })
    public List<ConnectionResource> getConnections() {
	return connections;
    }

    public void setConnections(List<ConnectionResource> connections) {
	this.connections = connections;
    }

}
