package chess.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResolver {

    private static final String DEFAULT_SERVER_EXCEPTION_MESSAGE = "예상치 못한 문제가 발생하였습니다.";
    private static final String NO_DATA_EXCEPTION_MESSAGE = "해당 데이터를 찾을 수 없습니다.";
    private static final String EMPTY_STRING = "";
    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        logger.error(EMPTY_STRING, e);
        return DEFAULT_SERVER_EXCEPTION_MESSAGE;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(EmptyResultDataAccessException e) {
        logger.error(EMPTY_STRING, e);
        return NO_DATA_EXCEPTION_MESSAGE;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(IllegalArgumentException e) {
        logger.error(EMPTY_STRING, e);
        return e.getMessage();
    }
}
