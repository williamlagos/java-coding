package negocio;

public class VendaDAOException extends Exception {
	private static final long serialVersionUID = 1L;
	public VendaDAOException(){
		super();
	}
	public VendaDAOException(String mensagem) {
		super(mensagem);
	}
	public VendaDAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
