package org.example.entity;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

@Getter
@XmlRootElement(name = "ITEM")
public class HierarchyItem extends AbstractItem {
    @XmlAttribute(name = "PARENTOBJID")
    private Long parentObjId;
}
