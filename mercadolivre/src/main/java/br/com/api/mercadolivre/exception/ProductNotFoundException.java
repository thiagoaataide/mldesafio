package br.com.api.mercadolivre.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id) {
        super("Produto com ID " + id + " não encontrado.");
    }
}
