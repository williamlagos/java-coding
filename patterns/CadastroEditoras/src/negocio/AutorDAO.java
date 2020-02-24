package negocio;

import java.util.*;

public interface AutorDAO {
	List<Autor> buscarTodos() throws AutorDAOException;
    Autor buscarPorCodigo(int codigo) throws AutorDAOException;
    List<Autor> buscarPorUltimoNome(String nome) throws AutorDAOException;
	void inserir(Autor autor) throws AutorDAOException;
	void alterar(Autor autor) throws AutorDAOException;
}
