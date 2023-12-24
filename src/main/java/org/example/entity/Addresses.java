package org.example.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@XmlRootElement(name = "ADDRESSOBJECTS")
@XmlAccessorType(XmlAccessType.FIELD)
public class Addresses {
    @XmlElement(name = "OBJECT")
    private List<AddressItem> objects = null;
}
