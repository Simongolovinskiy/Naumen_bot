package ru.Simon.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.Simon.telegrambot.entities.ClientOrder;

@RepositoryRestResource(collectionResourceRel = "clientOrders", path = "clientOrders")
public interface OrderRepository extends JpaRepository<ClientOrder, Long>
{
}
