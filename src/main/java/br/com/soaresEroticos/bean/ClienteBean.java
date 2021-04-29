package br.com.soaresEroticos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.soaresEroticos.dao.CidadeDAO;
import br.com.soaresEroticos.dao.ClienteDAO;
import br.com.soaresEroticos.dao.EstadoDAO;
import br.com.soaresEroticos.dao.PessoaDAO;
import br.com.soaresEroticos.domain.Cliente;
import br.com.soaresEroticos.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private Cliente cliente;

	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar("dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			cliente = new Cliente();
			
			clientes = clienteDAO.listar("dataCadastro");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.excluir(cliente);
		
		clientes = clienteDAO.listar();
		
		Messages.addGlobalInfo("Cliente excluido com sucesso");
		
	}
	
	public void editar(ActionEvent evento) {
		try{
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
			PessoaDAO pessoaDao = new PessoaDAO();
			pessoas = pessoaDao.listar("nome");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma pessoa");
		}
	}
}
