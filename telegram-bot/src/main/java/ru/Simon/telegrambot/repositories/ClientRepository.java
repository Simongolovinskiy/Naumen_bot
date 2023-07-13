package ru.Simon.telegrambot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.Simon.telegrambot.entities.Client;
import ru.Simon.telegrambot.entities.Product;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRepository extends JpaRepository<Client, Long>
{
    String GET_ALL_PRODUCT_QUERY = "from Product where id in (from OrderProduct where clientOrder.client.id = "
            + ":clientId) ";

    @Query(GET_ALL_PRODUCT_QUERY)
    List<Product> getAllProduct(@Param("clientId") Long clientId);
}
