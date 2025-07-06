package br.com.api.mercadolivre.service;

import br.com.api.mercadolivre.dto.ProductDTO;
import br.com.api.mercadolivre.exception.ProductNotFoundException;
import br.com.api.mercadolivre.model.Product;
import br.com.api.mercadolivre.repository.JsonProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class ProductService {
    private final JsonProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return toDTO(product);
    }

    public List<ProductDTO> findByTitle(String title) {
        return productRepository.findByTitleContaining(title).stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ProductDTO toDTO(Product p) {
        return new ProductDTO(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getPrice(),
                p.getImages(),
                p.getPayment(),
                p.getSeller(),
                p.getReviews()
        );
    }
}
