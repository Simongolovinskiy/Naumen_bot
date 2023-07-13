package ru.Simon.telegrambot.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Simon.telegrambot.repositories.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntitiesServiceImpl {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Autowired
    public EntitiesServiceImpl(ClientRepository clientRepository, OrderRepository orderRepository,
                           ProductRepository productRepository) {

        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

    }

    public List<Product> getCategoryProducts(Category category) {
        Product product = new Product();
        product.setCategory(category);
        return productRepository.findAll(Example.of(product));
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    public List<Product> getClientProducts(Long clientId) {
        return clientRepository.getAllProduct(clientId);
    }

    public List<Product> getTopPopularProducts(Integer limit) {
        return productRepository.getTopPopular().stream().limit(limit).collect(Collectors.toList());
    }


    public List<ClientOrder> getClientOrders(Client client) {
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClient(client);
        return orderRepository.findAll(Example.of(clientOrder));
    }
}