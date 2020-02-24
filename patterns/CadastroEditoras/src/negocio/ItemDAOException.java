package negocio;

public class ItemDAOException extends Exception {
	private static final long serialVersionUID = 1L;
	public ItemDAOException(){
		super();
	}
	public ItemDAOException(String mensagem) {
		super(mensagem);
	}
	public ItemDAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
