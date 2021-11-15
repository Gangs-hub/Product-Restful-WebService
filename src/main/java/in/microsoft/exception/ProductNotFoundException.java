package in.microsoft.exception;

public class ProductNotFoundException extends RuntimeException {
	
	
	
	private static final long serialVersionUID = 1L;

		//Constructor
		public ProductNotFoundException() {
			super();
		}
		
		public ProductNotFoundException(String message) {
			super(message);
		}

}
