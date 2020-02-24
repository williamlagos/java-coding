package negocio;

import java.util.List;

public interface ItemDAO {
	List<Item> buscarTodos() throws ItemDAOException;
	void inserir(Item item) throws ItemDAOException;
	void alterar(Item item) throws ItemDAOException;
}
