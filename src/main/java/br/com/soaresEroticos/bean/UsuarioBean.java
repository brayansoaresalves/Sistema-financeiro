package br.com.soaresEroticos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.soaresEroticos.dao.ClienteDAO;
import br.com.soaresEroticos.dao.PessoaDAO;
import br.com.soaresEroticos.dao.UsuarioDAO;
import br.com.soaresEroticos.domain.Cliente;
import br.com.soaresEroticos.domain.Pessoa;
import br.com.soaresEroticos.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	
	private List<Pessoa> pessoas;
	private List<Usuario> usuarios;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("tipo");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			usuario = new Usuario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			
			SimpleHash hash = new SimpleHash("md5",usuario.getSenha() );
			usuario.setSenha(hash.toHex());
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			
			usuario = new Usuario();
			usuarios = usuarioDAO.listar("tipo");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try{
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			PessoaDAO pessoaDao = new PessoaDAO();
			pessoas = pessoaDao.listar("nome");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Usuario");
		}
	}
	
	public void excluir(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
		
		usuarios = usuarioDAO.listar();
		
		Messages.addGlobalInfo("Usuario excluido com sucesso");
	}
}
