package in.microsoft.custom;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.microsoft.exception.ProductNotFoundException;

@RestControllerAdvice
//internally linked with @ResponseBody to send data in JSON format
public class MyCustomeExceptionHandler {
	/*
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e){
		
		//Message
		return  new ResponseEntity<String>(
				e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR
				);
		
	}*/

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Message>handleProductNotFoundException(ProductNotFoundException e){
		
		return new ResponseEntity<Message>(
				new Message(
				e.getMessage(),
				"Product",
				new Date().toString()
				),HttpStatus.INTERNAL_SERVER_ERROR
				);
		
		
	}
}
