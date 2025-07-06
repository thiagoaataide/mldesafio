package br.com.api.mercadolivre.dto;

import br.com.api.mercadolivre.model.PaymentInfo;
import br.com.api.mercadolivre.model.Review;
import br.com.api.mercadolivre.model.SellerInfo;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(String id,
                         String title,
                         String description,
                         BigDecimal price,
                         List<String> images,
                         PaymentInfo payment,
                         SellerInfo seller,
                         List<Review> reviews) { }
