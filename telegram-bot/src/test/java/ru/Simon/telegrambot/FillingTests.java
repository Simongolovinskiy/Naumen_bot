package ru.Simon.telegrambot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import ru.Simon.telegrambot.entities.Category;
import ru.Simon.telegrambot.entities.Product;
import ru.Simon.telegrambot.repositories.CategoryRepository;
import ru.Simon.telegrambot.repositories.ProductRepository;

@SpringBootTest
class FillingTests {
    interface CategoryNames {
        String MENU = "Menu";
        String ABOUT_US = "Information";
        String PIZZA = "Pizza";
        String BURGERS = "Burgers";
        String BURGERS_CLASSIC = "Classic Burgers";
        String BURGERS_SPICY = "Spicy Burgers";
        String ROLLS = "Rolls";
        String ROLLS_CLASSIC = "Classic Rolls";
        String ROLLS_BAKED = "Baked Rolls";
        String ROLLS_SWEET = "Sweet Rolls";
        String ROLLS_SETS = "Sets";
    }

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void createCategories() {
        categoryRepository.deleteAll();

        Category menuCategory = createAndSaveCategory(CategoryNames.MENU, null);

        createAndSaveCategory(CategoryNames.ABOUT_US, null);

        Category pizzaCategory = createAndSaveCategory(CategoryNames.PIZZA, menuCategory);

        Category rollsCategory = createAndSaveCategory(CategoryNames.ROLLS, menuCategory);
        createAndSaveCategory(CategoryNames.ROLLS_BAKED, rollsCategory);
        createAndSaveCategory(CategoryNames.ROLLS_CLASSIC, rollsCategory);
        createAndSaveCategory(CategoryNames.ROLLS_SWEET, rollsCategory);
        createAndSaveCategory(CategoryNames.ROLLS_SETS, rollsCategory);

        Category burgersCategory = createAndSaveCategory(CategoryNames.BURGERS, menuCategory);
        createAndSaveCategory(CategoryNames.BURGERS_CLASSIC, burgersCategory);
        createAndSaveCategory(CategoryNames.BURGERS_SPICY, burgersCategory);
    }

    @Test
    void createProducts() {
        productRepository.deleteAll();

        Category pizzaCategory = getCategoryByName(CategoryNames.PIZZA);
        Category rollsBakedCategory = getCategoryByName(CategoryNames.ROLLS_BAKED);
        Category rollsClassicCategory = getCategoryByName(CategoryNames.ROLLS_CLASSIC);
        Category rollsSweetCategory = getCategoryByName(CategoryNames.ROLLS_SWEET);
        Category rollsSetsCategory = getCategoryByName(CategoryNames.ROLLS_SETS);
        Category burgersClassicCategory = getCategoryByName(CategoryNames.BURGERS_CLASSIC);
        Category burgersSpicyCategory = getCategoryByName(CategoryNames.BURGERS_SPICY);

        createAndSaveProduct("Pizza 1", 9.99, pizzaCategory);
        createAndSaveProduct("Pizza 2", 10.99, pizzaCategory);
        createAndSaveProduct("Pizza 3", 11.99, pizzaCategory);

        createAndSaveProduct("Rolls Baked 1", 7.99, rollsBakedCategory);
        createAndSaveProduct("Rolls Baked 2", 8.99, rollsBakedCategory);
        createAndSaveProduct("Rolls Baked 3", 9.99, rollsBakedCategory);

        createAndSaveProduct("Rolls Classic 1", 7.99, rollsClassicCategory);
        createAndSaveProduct("Rolls Classic 2", 8.99, rollsClassicCategory);
        createAndSaveProduct("Rolls Classic 3", 9.99, rollsClassicCategory);

        createAndSaveProduct("Rolls Sweet 1", 7.99, rollsSweetCategory);
        createAndSaveProduct("Rolls Sweet 2", 8.99, rollsSweetCategory);
        createAndSaveProduct("Rolls Sweet 3", 9.99, rollsSweetCategory);

        createAndSaveProduct("Rolls Sets 1", 7.99, rollsSetsCategory);
        createAndSaveProduct("Rolls Sets 2", 8.99, rollsSetsCategory);
        createAndSaveProduct("Rolls Sets 3", 9.99, rollsSetsCategory);

        createAndSaveProduct("Burgers Classic 1", 7.99, burgersClassicCategory);
        createAndSaveProduct("Burgers Classic 2", 8.99, burgersClassicCategory);
        createAndSaveProduct("Burgers Classic 3", 9.99, burgersClassicCategory);

        createAndSaveProduct("Burgers Spicy 1", 7.99, burgersSpicyCategory);
        createAndSaveProduct("Burgers Spicy 2", 8.99, burgersSpicyCategory);
        createAndSaveProduct("Burgers Spicy 3", 9.99, burgersSpicyCategory);
    }

    private Category createAndSaveCategory(String name, Category parent) {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        return categoryRepository.save(category);
    }

    private void createAndSaveProduct(String name, Double price, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription("Product description");
        product.setPrice(price);
        product.setCategory(category);
        productRepository.save(product);
    }

    private Category getCategoryByName(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.findOne(Example.of(category)).orElse(null);
    }
}
