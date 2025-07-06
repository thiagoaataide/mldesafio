package br.com.api.mercadolivre.repository;

import br.com.api.mercadolivre.exception.JsonDataLoadingException;
import br.com.api.mercadolivre.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JsonProductRepository {
    private final Map<String, Product> products = new HashMap<>();

    @PostConstruct
    public void init(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            InputStream is = getClass().getResourceAsStream("/data/products.json");
            List<Product> listProducts = mapper.readValue(is, new TypeReference<>() {});
            listProducts.forEach(product -> products.put(product.getId(), product));
        } catch (Exception e) {
            throw new JsonDataLoadingException("Erro ao carregar arquivo JSON de produtos. ", e);
        }
    }
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> findByTitleContaining(String title) {
        return products.values().stream()
                .filter(p -> p.getTitle() != null && p.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
}
