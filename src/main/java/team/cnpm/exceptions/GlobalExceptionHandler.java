package team.cnpm.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import team.cnpm.DTOs.response.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)  // Có thể bắt nhiều loại exception
    public ResponseEntity<ResponseDTO> handleExceptionA(NoSuchElementException e) {
        return ResponseEntity.status(500).body(new ResponseDTO(false,"No such people exists!"));
    }
    
    // Có thêm các @ExceptionHandler khác...
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException e){
    	return ResponseEntity.status(500).body(new ResponseDTO(false,"access is denied!"));
    }
    
    @ExceptionHandler(OwnerNotAvailableException.class)
    public ResponseEntity<ResponseDTO> handleOwnerNotAvailableException(OwnerNotAvailableException e){
    	return ResponseEntity.status(400).body(new ResponseDTO(false,"Người này đã là chủ hộ của một hộ khác!"));
    }
    
    // Nên bắt cả Exception.class
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleUnwantedException(Exception e) {
        // Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
        e.printStackTrace();  // Thực tế người ta dùng logger
        return ResponseEntity.status(500).body(new ResponseDTO(false, "An errorr occrurred"));
    }
}
