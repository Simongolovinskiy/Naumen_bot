package ru.Simon.telegrambot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer externalId;

    @Column(nullable = false, length = 255)
    private String fullName;

    @Column(nullable = false, length = 400)
    private String address;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    public String getAddress()
    {
        return address;
    }

    public Integer getExternalId()
    {
        return externalId;
    }

    public Long getId()
    {
        return id;
    }


    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setExternalId(Integer externalId)
    {
        this.externalId = externalId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
