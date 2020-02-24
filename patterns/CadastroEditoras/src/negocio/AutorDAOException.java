package negocio;

public class AutorDAOException extends Exception {
	private static final long serialVersionUID = 1L;
	public AutorDAOException(){
		super();
	}
	public AutorDAOException(String mensagem) {
		super(mensagem);
	}
	public AutorDAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
