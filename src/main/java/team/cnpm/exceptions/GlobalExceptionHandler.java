package team.cnpm.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import team.cnpm.DTOs.response.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ NoSuchElementException.class})  // Có thể bắt nhiều loại exception
    public ResponseEntity<ResponseDTO> handleExceptionA(Exception e) {
        return ResponseEntity.status(500).body(new ResponseDTO(false,"No such people exists!"));
    }
    
    // Có thêm các @ExceptionHandler khác...
    
    // Nên bắt cả Exception.class
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleUnwantedException(Exception e) {
        // Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
        e.printStackTrace();  // Thực tế người ta dùng logger
        return ResponseEntity.status(500).body(new ResponseDTO(false, "An errorr occrurred"));
    }
}
