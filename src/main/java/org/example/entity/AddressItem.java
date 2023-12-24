package org.example.entity;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.UUID;

@Getter
@XmlRootElement(name = "OBJECT")
public class AddressItem extends AbstractItem {
    @XmlAttribute(name = "OBJECTGUID")
    private UUID objectGuid;
    @XmlAttribute(name = "NAME")
    private String name;
    @XmlAttribute(name = "TYPENAME")
    private String typeName;
    @XmlAttribute(name = "LEVEL")
    private Long level;
    @XmlAttribute(name = "OPERTYPEID")
    private Long operTypeId;
    @XmlAttribute(name = "ISACTUAL")
    private Boolean isActual;
}
