package br.com.api.mercadolivre.controller;

import br.com.api.mercadolivre.dto.ProductDTO;
import br.com.api.mercadolivre.model.PaymentInfo;
import br.com.api.mercadolivre.model.SellerInfo;
import br.com.api.mercadolivre.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    @Test
    void shouldReturnProductList() throws Exception {
        ProductDTO dto = new ProductDTO("P50", "TV 50 Polegadas LG", "Desc", BigDecimal.ONE, List.of(),
                new PaymentInfo(true, 10), new SellerInfo("LG", 4.5), List.of());

        when(service.getAllProducts()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnSingleProduct() throws Exception {
        ProductDTO dto = new ProductDTO("P60", "Notebook Gamer Lenovo 3i", "Desc", BigDecimal.ONE, List.of(),
                new PaymentInfo(true, 10), new SellerInfo("Lenovo", 4.5), List.of());

        when(service.getProductById("1")).thenReturn(dto);

        mockMvc.perform(get("/api/products/P60"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSearchByTitle() throws Exception {
        ProductDTO dto = new ProductDTO("P70", "Bola Adidas", "Desc", BigDecimal.ONE, List.of(),
                new PaymentInfo(true, 10), new SellerInfo("Adidas", 4.5), List.of());

        when(service.findByTitle("Adida")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/products/search?title=note"))
                .andExpect(status().isOk());
    }
}
