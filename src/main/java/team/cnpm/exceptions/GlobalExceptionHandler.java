package team.cnpm.exceptions;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import team.cnpm.DTOs.response.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)  // Có thể bắt nhiều loại exception
    public ResponseEntity<ResponseDTO> handleExceptionA(NoSuchElementException e) {
        return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy công dân nào!"));
    }
    
    // Có thêm các @ExceptionHandler khác...
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException e){
    	return ResponseEntity.ok(new ResponseDTO(false,"Chưa đăng nhập hoặc phiên đăng nhập đã quá hạn,"
    			+ " vui lòng thử lại!"));
    }
    
    @ExceptionHandler(OwnerNotAvailableException.class)
    public ResponseEntity<ResponseDTO> handleOwnerNotAvailableException(OwnerNotAvailableException e){
    	return ResponseEntity.ok(new ResponseDTO(false,"Người này đã là chủ hộ của một hộ khác!"));
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException e){
    	return ResponseEntity.ok(new ResponseDTO(false,"Không tìm thấy thông tin đóng góp tương ứng!"));
    }
    
    @ExceptionHandler(CongDanQuaDoiException.class)
    public ResponseEntity<ResponseDTO> handleCongDanNotFoundException(CongDanQuaDoiException e){
    	return ResponseEntity.ok(new ResponseDTO(false,"Lỗi vì công dân đã qua đời"));
    }
    // Nên bắt cả Exception.class
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleUnwantedException(Exception e) {
        // Log lỗi ra và ẩn đi message thực sự (xem phần 3.2)
        e.printStackTrace();  // Thực tế người ta dùng logger
        return ResponseEntity.ok(new ResponseDTO(false, "An errorr occrurred"));
    }
}