package negocio;

public class LivroDAOException extends Exception {
	private static final long serialVersionUID = 1L;
	public LivroDAOException(){
		super();
	}
	public LivroDAOException(String mensagem) {
		super(mensagem);
	}
	public LivroDAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
