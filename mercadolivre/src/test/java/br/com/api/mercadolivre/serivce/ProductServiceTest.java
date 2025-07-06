package br.com.api.mercadolivre.serivce;

import br.com.api.mercadolivre.exception.ProductNotFoundException;
import br.com.api.mercadolivre.model.Product;
import br.com.api.mercadolivre.repository.JsonProductRepository;
import br.com.api.mercadolivre.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private JsonProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setup() {
        repository = mock(JsonProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void shouldReturnAllProductsAsDTOs() {
        Product mockProduct = new Product("P100", "Produto A", "Desc", BigDecimal.TEN, List.of(), null, null, 10, List.of());
        when(repository.findAll()).thenReturn(List.of(mockProduct));

        var result = service.getAllProducts();

        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.get(0).title()).isEqualTo("Produto A");
        Assertions.assertThat(result.get(0).id()).isEqualTo("P100");
    }

    @Test
    void shouldReturnProductByIdAsDTO() {
        Product mockProduct = new Product("P66", "Samsung Galaxy S24 Ultra", "Desc", BigDecimal.TEN, List.of(), null, null, 10, List.of());
        when(repository.findById("P66")).thenReturn(Optional.of(mockProduct));

        var productDTO = service.getProductById("P66");

        Assertions.assertThat(productDTO.id()).isEqualTo("P66");
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        when(repository.findById("999")).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> service.getProductById("999"))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void shouldSearchProductsByTitle() {
        Product product = new Product("P50", "Notebook Gamer", "Desc", BigDecimal.ONE, List.of(), null, null, 10, List.of());
        when(repository.findByTitleContaining("note")).thenReturn(List.of(product));

        var result = service.findByTitle("note");

        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.get(0).title()).containsIgnoringCase("note");
    }
}
