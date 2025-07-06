package br.com.api.mercadolivre.exception;

public class JsonDataLoadingException extends RuntimeException  {
    public JsonDataLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
