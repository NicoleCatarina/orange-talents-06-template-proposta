package br.com.zupacademy.nicolecatarina.proposta.exception;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(RegraVioladaException.class)
    public ErrorBody handleRegraVioladaException(RegraVioladaException e) {
        String mensagemDeErro = e.getMessage();
        return buildErrorResponse(mensagemDeErro);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntidadeEmUsoException.class)
    public ErrorBody handleEntidadeEmUsoException(EntidadeEmUsoException e) {
        String mensagemDeErro = e.getMessage();
        return buildErrorResponse(mensagemDeErro);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ErrorBody handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {

        String mensagemDeErro = e.getMessage();
        return buildErrorResponse(mensagemDeErro);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorBody handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<org.springframework.validation.FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return buildErrorResponse(fieldErrors);
    }

    private ErrorBody buildErrorResponse(List<org.springframework.validation.FieldError> erros) {
        List<FieldError> errorsBody = erros.stream()
                .map(erro -> new FieldError(toSnakeCase(erro.getField()), messageSource.getMessage(erro, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());

        return new ErrorBody(errorsBody);
    }

    private ErrorBody buildErrorResponse(String mensagemDeErro) {
        return new ErrorBody(mensagemDeErro);
    }

    private String toSnakeCase(String campo) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, campo);
    }

}
