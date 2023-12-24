package org.example.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Getter;

import java.util.Date;

@Getter
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AbstractItem {
    @XmlAttribute(name = "ID")
    private Long id;
    @XmlAttribute(name = "OBJECTID")
    private Long objectId;
    @XmlAttribute(name = "PREVID")
    private Long prevId;
    @XmlAttribute(name = "NEXTID")
    private Long nexId;
    @XmlAttribute(name = "UPDATEDATE")
    private Date updateDate;
    @XmlAttribute(name = "STARTDATE")
    private Date startDate;
    @XmlAttribute(name = "ENDDATE")
    private Date endDate;
    @XmlAttribute(name = "CHANGEID")
    private Long changeId;
    @XmlAttribute(name = "ISACTIVE")
    private Boolean isActive;
}
