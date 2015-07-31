/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Linha;
import br.com.sim.model.bean.Maquina;
import br.com.sim.model.bean.Posto;
import br.com.sim.rn.LinhaRn;
import br.com.sim.util.JpaUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rodrigo Otacilio
 */
public class PostoDao {

    private Dao<Posto> dao;
    private EntityManager em;

    public PostoDao() {
        dao = new Dao<Posto>(Posto.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Posto t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Posto t) {
        dao.excluir(t);
    }

    public Posto carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Posto> listar() {
        return dao.listar();
    }

    public Posto pesquisarPosto(String descricao, Linha linha) {
        TypedQuery<Posto> query = em.createQuery("select p from Posto p where lower(p.descricao) = :pDescricao and p.linha = :pLinha", Posto.class);
        query.setParameter("pDescricao", descricao.toLowerCase());
        query.setParameter("pLinha", linha);
        return query.getSingleResult();
    }

    public List<Posto> filtrarPostos(String descricao, Linha linha) {
        StringBuilder str = new StringBuilder();
        str.append("select * from postos where true ");
        if (descricao != null && descricao.trim().length() > 0) {
            str.append("and lower(post_descricao) like '" + descricao.toLowerCase() + "%'");
        }
        if (linha != null) {
            str.append("and post_linh_id = " + linha.getId() + " ");
        }
        str.append("order by post_id ");
        
        Query q = em.createNativeQuery(str.toString());
        List<Object[]> itens = q.getResultList();
        List<Posto> postos = new ArrayList<Posto>();
        for (Object[] obj : itens) {
            Posto p = new Posto();
            p.setId(Integer.valueOf(obj[0].toString()));
            p.setLinha(new LinhaRn().carregarPorId(Integer.valueOf(obj[1].toString())));
            p.setDescricao(obj[2].toString());
            postos.add(p);
        }
        return postos;
    }

	public List<Posto> listarPorLinha(Linha linha) {
		// TODO Auto-generated method stub
        TypedQuery<Posto> query = em.createQuery("select p from Posto p where p.linha = :pLinha order by p.id", Posto.class);
        query.setParameter("pLinha", linha);
        return query.getResultList();
	}

}
