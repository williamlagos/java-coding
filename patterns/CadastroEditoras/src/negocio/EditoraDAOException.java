package negocio;

public class EditoraDAOException extends Exception {
	private static final long serialVersionUID = 1L;
	public EditoraDAOException(){
		super();
	}
	public EditoraDAOException(String mensagem) {
		super(mensagem);
	}
	public EditoraDAOException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
