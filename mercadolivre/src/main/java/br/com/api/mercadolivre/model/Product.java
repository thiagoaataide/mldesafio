package br.com.api.mercadolivre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private List<String> images;
    private PaymentInfo payment;
    private SellerInfo seller;
    private Integer availableStock;
    private List<Review> reviews;
}
