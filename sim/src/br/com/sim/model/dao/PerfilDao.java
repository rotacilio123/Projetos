/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Perfil;
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
public class PerfilDao {

    private Dao<Perfil> dao;
    private EntityManager em;

    public PerfilDao() {
        dao = new Dao<Perfil>(Perfil.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Perfil t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Perfil t) {
        dao.excluir(t);
    }

    public Perfil carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Perfil> listar() {
        return dao.listar();
    }

    public Perfil carregarPorNome(String nome) {
        TypedQuery<Perfil> q = em.createQuery("select p from Perfil p where lower(p.nome) = :pNome", Perfil.class);
        q.setParameter("pNome", nome.trim().toLowerCase());
        return q.getSingleResult();
    }

    public List<Perfil> filtrarPerfis(String nome, String descricao) {
        StringBuilder str = new StringBuilder();
        List<Perfil> perfis = new ArrayList<Perfil>();

        str.append("select * from perfis ");
        str.append("where true ");
        if (nome != null && nome.trim().length() > 0) {
            str.append("and lower(perf_nome) like '" + nome.trim().toLowerCase() + "%' ");
        }
        if (descricao != null && descricao.trim().length() > 0) {
            str.append("and lower(perf_descricao) like '" + descricao.trim().toLowerCase() + "%' ");
        }
        str.append("order by perf_id");

        Query q = em.createNativeQuery(str.toString());
        List<Object[]> itens = q.getResultList();
        
        for (Object[] obj : itens) {
            Perfil p = new Perfil();
            p.setId(Integer.valueOf(obj[0].toString()));
            p.setNome(obj[1].toString());
            p.setDescricao(obj[2].toString());
            perfis.add(p);
        }
        return perfis;
    }
}
