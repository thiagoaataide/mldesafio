package br.com.api.mercadolivre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String username;
    private String comment;
    private double rating;
    private LocalDate date;
}
