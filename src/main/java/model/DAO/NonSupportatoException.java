package model.DAO;

public class NonSupportatoException extends RuntimeException{
		public NonSupportatoException(){}
		public NonSupportatoException(String str) {
			super(str);
		}
}
