package org.example.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@XmlRootElement(name = "ITEMS")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hierarchy {
    @XmlElement(name = "ITEM")
    private List<HierarchyItem> items = null;
}
