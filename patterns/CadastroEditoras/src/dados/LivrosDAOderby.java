package dados;

import java.util.List;

import negocio.Livro;
import negocio.LivroDAO;
import negocio.LivroDAOException;

public class LivrosDAOderby implements LivroDAO {

	@Override
	public List<Livro> buscarTodos() throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Livro buscarPorCodigo(int codigo) throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Livro> buscarPorTitulo(String nome) throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Livro> buscarPorAno(int ano) throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Livro> buscarPorEditora(String nomeEditora)
			throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Livro> buscarPorAutor(String nomeAutor)
			throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public void inserir(Livro livro) throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public void alterar(Livro livro) throws LivroDAOException {
		// TODO Stub de método gerado automaticamente
		
	}

}
