package br.com.api.mercadolivre.repository;

import br.com.api.mercadolivre.model.Product;
import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(products).isNotEmpty();

        Product product = products.get(0);
        Assertions.assertThat(product.getId()).isNotBlank();
        Assertions.assertThat(product.getTitle()).isNotBlank();
        Assertions.assertThat(product.getPrice()).isNotNull();
    }

    @Test
    void shouldFindProductById() {
        String knownId = repository.findAll().get(0).getId();
        Optional<Product> product = repository.findById(knownId);

        Assertions.assertThat(product).isPresent();
        Assertions.assertThat(product.get().getId()).isEqualTo(knownId);
    }

    @Test
    void shouldFindProductByTitleContaing() {
        List<Product> products = repository.findByTitleContaining("Produto 4");

        Assertions.assertThat(products).isNotEmpty();
        Assertions.assertThat(products.get(0).getId()).isNotBlank();
    }
}
