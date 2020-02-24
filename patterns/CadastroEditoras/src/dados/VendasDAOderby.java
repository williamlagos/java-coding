package dados;

import java.util.Date;
import java.util.List;

import negocio.Venda;
import negocio.VendaDAO;
import negocio.VendaDAOException;

public class VendasDAOderby implements VendaDAO {

	@Override
	public List<Venda> buscarTodos() throws VendaDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public Venda buscarPorCodigo(int codigo) throws VendaDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Venda> buscarPorNome(String nome) throws VendaDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Venda> buscarPorCPFCNPJ(String cod) throws VendaDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public List<Venda> buscarPorData(Date data) throws VendaDAOException {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	@Override
	public void inserir(Venda venda) throws VendaDAOException {
		// TODO Stub de método gerado automaticamente
		
	}

	@Override
	public void alterar(Venda venda) throws VendaDAOException {
		// TODO Stub de método gerado automaticamente
		
	}

}
