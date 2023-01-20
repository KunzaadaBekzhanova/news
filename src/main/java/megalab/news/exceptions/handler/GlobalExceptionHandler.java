package megalab.news.exceptions.handler;


import megalab.news.dtos.responses.ExceptionResponse;
import megalab.news.exceptions.BadRequestException;
import megalab.news.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ExceptionResponse handleException(Exception e) {
        e.printStackTrace();
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ExceptionResponse handleBadRequestException(BadRequestException e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ExceptionResponse handleNotFoundException(NotFoundException e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .build();
    }
}

