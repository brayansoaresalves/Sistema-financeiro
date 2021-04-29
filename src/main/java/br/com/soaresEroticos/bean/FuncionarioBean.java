package br.com.soaresEroticos.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.soaresEroticos.dao.FuncionarioDAO;
import br.com.soaresEroticos.dao.PessoaDAO;
import br.com.soaresEroticos.domain.Funcionario;
import br.com.soaresEroticos.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean {
	
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private Pessoa pessoa;
	
	private List<Pessoa> pessoas;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionarios");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			funcionario = new Funcionario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo Funcionario");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try{
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um funcionario");
		}
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);
			
			funcionario = new Funcionario();
			
			funcionarios = funcionarioDAO.listar("dataAdmissao");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Funcionario salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o funcionario");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.excluir(funcionario);
		
		funcionarios = funcionarioDAO.listar();
		
		Messages.addGlobalInfo("Funcionario excluido com sucesso");

	}

}
