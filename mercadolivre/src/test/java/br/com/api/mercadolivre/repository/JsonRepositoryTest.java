package br.com.api.mercadolivre.repository;

import br.com.api.mercadolivre.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JsonRepositoryTest {

    @Autowired
    private JsonProductRepository repository;

    @Test
    void printAllProducts(){
        List<Product> products = repository.findAll();
        Assertions.assertTrue(!products.isEmpty());

        Product product = products.get(0);

        Assertions.assertFalse(product.getId().isBlank());
        Assertions.assertFalse(product.getTitle().isBlank());
        Assertions.assertNotNull(product.getPrice());
    }

    @Test
    void shouldFindProductById() {
        String knownId = repository.findAll().get(0).getId();
        Optional<Product> product = repository.findById(knownId);

        Assertions.assertTrue(product.isPresent());
        Assertions.assertEquals(knownId, product.get().getId());
    }

    @Test
    void shouldFindProductByTitleContaing() {
        List<Product> products = repository.findByTitleContaining("Produto 4");

//        Assertions.assertThat(products).isNotEmpty();
//        Assertions.assertThat(products.get(0).getId()).isNotBlank();
    }
}
