package ru.Simon.telegrambot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false, precision = 15, scale = 2)
    private Double total;

    public Client getClient()
    {
        return client;
    }

    public Long getId()
    {
        return id;
    }

    public Integer getStatus()
    {
        return status;
    }

    public Double getTotal()
    {
        return total;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }
}
