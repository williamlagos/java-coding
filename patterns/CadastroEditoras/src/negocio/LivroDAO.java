package negocio;

import java.util.*;

public interface LivroDAO {
	List<Livro> buscarTodos() throws LivroDAOException;
    Livro buscarPorCodigo(int codigo) throws LivroDAOException;
    List<Livro> buscarPorTitulo(String nome) throws LivroDAOException;
    List<Livro> buscarPorAno(int ano) throws LivroDAOException;
    List<Livro> buscarPorEditora(String nomeEditora) throws LivroDAOException;
    List<Livro> buscarPorAutor(String nomeAutor) throws LivroDAOException;
	void inserir(Livro livro) throws LivroDAOException;
	void alterar(Livro livro) throws LivroDAOException;
}
