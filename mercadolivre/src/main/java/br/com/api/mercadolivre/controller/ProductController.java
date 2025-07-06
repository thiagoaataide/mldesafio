package br.com.api.mercadolivre.controller;

import br.com.api.mercadolivre.dto.ProductDTO;
import br.com.api.mercadolivre.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public List<ProductDTO> searchByTitle(@RequestParam(name = "title") String title) {
        return productService.findByTitle(title);
    }
}
