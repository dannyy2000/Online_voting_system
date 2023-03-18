//package Africa.semicolon.my_VotingApp.exception;
//
//import Africa.semicolon.my_VotingApp.utils.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.ZonedDateTime;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(GeneralServiceException.class)
//    public ResponseEntity<ApiResponse> generalServiceException(GeneralServiceException generalServiceException){
       // ApiResponse apiResponse = ApiResponse.builder()
//                .time(ZonedDateTime.now())
//                .statusCode(HttpStatus.CONFLICT.value())
//                .data(generalServiceException.getMessage())
//                .isSuccessful(false)
//                .build();
//        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
//    }
//}
//
//
