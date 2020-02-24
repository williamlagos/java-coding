package negocio;

import java.util.*;

public interface VendaDAO {
	List<Venda> buscarTodos() throws VendaDAOException;
    Venda buscarPorCodigo(int codigo) throws VendaDAOException;
    List<Venda> buscarPorNome(String nome) throws VendaDAOException;
    List<Venda> buscarPorCPFCNPJ(String cod) throws VendaDAOException;
    List<Venda> buscarPorData(Date data) throws VendaDAOException;
	void inserir(Venda venda) throws VendaDAOException;
	void alterar(Venda venda) throws VendaDAOException;
}
