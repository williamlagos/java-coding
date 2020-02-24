package negocio;

import java.util.List;

public interface EditoraDAO {
	List<Editora> buscarTodos() throws EditoraDAOException;
    Livro buscarPorCodigo(int codigo) throws EditoraDAOException;
    List<Editora> buscarPorLivro(String nomeLivro) throws EditoraDAOException;
    List<Editora> buscarPorNome(String nomeEditora) throws EditoraDAOException;
	void inserir(Editora editora) throws EditoraDAOException;
	void alterar(Editora editora) throws EditoraDAOException;
}
