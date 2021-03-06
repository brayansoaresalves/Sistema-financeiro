package br.com.soaresEroticos.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.soaresEroticos.dao.HistoricoDAO;
import br.com.soaresEroticos.dao.ProdutoDAO;
import br.com.soaresEroticos.domain.Historico;
import br.com.soaresEroticos.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutoPesquisaBean {

	private Produto produto;
	private Boolean exibir;
	private Historico historico;
	
	public Historico getHistorico() {
		return historico;
	}
	
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public Boolean getExibir() {
		return exibir;
	}

	public void setExibir(Boolean exibir) {
		this.exibir = exibir;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Produto();
		exibir = false;
	}

	public void buscar() {

		try {
			ProdutoDAO produtodao = new ProdutoDAO();
			Produto resultado = produtodao.buscar(produto.getCodigo());

			if (resultado == null) {
				Messages.addGlobalWarn("Não existe esse produto cadastrado");
				exibir = false;
			} else {
				exibir = true;
				produto = resultado;
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);

			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);

			Messages.addGlobalInfo("Histórico salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
			erro.printStackTrace();
		}
	}
}
