package dados;

import java.util.List;

import negocio.Editora;
import negocio.EditoraDAO;
import negocio.EditoraDAOException;
import negocio.Livro;

public class EditorasDAOderby implements EditoraDAO{

	@Override
	public List<Editora> buscarTodos() throws EditoraDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Livro buscarPorCodigo(int codigo) throws EditoraDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Editora> buscarPorLivro(String nomeLivro)
			throws EditoraDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Editora> buscarPorNome(String nomeEditora)
			throws EditoraDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public void inserir(Editora editora) throws EditoraDAOException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public void alterar(Editora editora) throws EditoraDAOException {
		// TODO Stub de método gerado automaticamente
		
	}

}
