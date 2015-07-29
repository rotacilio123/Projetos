/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Setor;
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
public class SetorDao {

    private Dao<Setor> dao;
    private EntityManager em;

    public SetorDao() {
        dao = new Dao<Setor>(Setor.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Setor t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Setor t) {
        dao.excluir(t);
    }

    public Setor carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Setor> listar() {
        return dao.listar();
    }

    public Setor carregarPorNome(String nome) {
        TypedQuery<Setor> q = em.createQuery("select s from Setor s where lower(s.nome) = :pNome", Setor.class);
        q.setParameter("pNome", nome.trim().toLowerCase());
        return q.getSingleResult();
    }

    public List<Setor> filtrarSetores(String nome) {
        StringBuilder str = new StringBuilder();
        List<Setor> setores = new ArrayList<Setor>();

        str.append("select * from setores ");
        str.append("where true ");
        if (nome != null && nome.trim().length() > 0) {
            str.append("and lower(seto_nome) like '" + nome.trim().toLowerCase() + "%' ");
        }
        str.append("order by seto_id");
        
        Query q = em.createNativeQuery(str.toString());
        List<Object[]> itens = q.getResultList();
        
        for (Object[] obj : itens) {
            Setor s = new Setor();
            s.setId(Integer.valueOf(obj[0].toString()));
            s.setNome(obj[1].toString());
            setores.add(s);
        }
        return setores;
    }
}
