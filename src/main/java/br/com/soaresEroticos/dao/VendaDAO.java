package br.com.soaresEroticos.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.soaresEroticos.domain.ItemVenda;
import br.com.soaresEroticos.domain.Produto;
import br.com.soaresEroticos.domain.Venda;
import br.com.soaresEroticos.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {
	public void salvar(Venda venda, List<ItemVenda> itensVenda) {
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();

			sessao.save(venda);

			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);

				sessao.save(itemVenda);
				Produto produto = itemVenda.getProduto();

				int qtde = produto.getQuantidade() - itemVenda.getQuantidade();

				if (qtde >= 0) {

					produto.setQuantidade(new Short((qtde) + ""));

					sessao.update(produto);
				}else {
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}
			}

			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
