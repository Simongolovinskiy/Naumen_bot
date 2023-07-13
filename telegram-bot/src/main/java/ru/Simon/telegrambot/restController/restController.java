package ru.Simon.telegrambot.restController;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.Simon.telegrambot.entities.*;
import ru.Simon.telegrambot.repositories.CategoryRepository;
import ru.Simon.telegrambot.repositories.ClientRepository;
import ru.Simon.telegrambot.repositories.ProductRepository;

import java.util.List;

@RestController
public class restController {

    private final EntitiesServiceImpl entitiesService;
    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public restController(EntitiesServiceImpl entitiesService, ClientRepository clientRepository,
                          ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.entitiesService = entitiesService;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/rest/clients")
    public List<Client> getAllClients()
    {
        return clientRepository.findAll();
    }

    @GetMapping("/rest/products")
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/rest/products/search", params = {"categoryId"})
    public List<Product> getCategoryProducts(@RequestParam("categoryId") Long categoryId) throws NotFoundException
    {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(NotFoundException::new);
        return entitiesService.getCategoryProducts(category);
    }


    @GetMapping("/rest/clients/{id}/orders")
    public List<ClientOrder> getClientOrders(@PathVariable("id") Long id) throws NotFoundException
    {
        Client client = clientRepository.findById(id).orElseThrow(NotFoundException::new);
        return entitiesService.getClientOrders(client);
    }

    @GetMapping("/rest/clients/{id}/products")
    public List<Product> getClientProducts(@PathVariable("id") Long id)
    {
        return entitiesService.getClientProducts(id);
    }

    @GetMapping("/rest/products/popular")
    public List<Product> getTopPopularProducts(@RequestParam("limit") Integer limit)
    {
        return entitiesService.getTopPopularProducts(limit);
    }

    @RequestMapping(value = "/rest/product", params = { "id" })
    public Product getProductById(@RequestParam Long id) throws NotFoundException
    {
        return entitiesService.getProductById(id);
    }
}
