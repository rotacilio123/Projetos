/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Linha;
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
public class LinhaDao {
    
    private Dao<Linha> dao;
    private EntityManager em;

    public LinhaDao() {
        dao = new Dao<Linha>(Linha.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Linha t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Linha t) {
        dao.excluir(t);
    }

    public Linha carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Linha> listar() {
        return dao.listar();
    }
    
    public List<Linha> filtrarLinhas(String nome) {
        StringBuilder str = new StringBuilder();
        List<Linha> linhas = new ArrayList<Linha>();
        
        str.append("SELECT * FROM linhas AS l ");
        str.append("WHERE TRUE ");
        if (nome != null) {
            str.append("AND LOWER(l.linh_nome) LIKE '" + nome.toLowerCase() + "%' ");
        }
        str.append("ORDER BY l.linh_id");
        
        Query query = em.createNativeQuery(str.toString());
        List<Object[]> itens = query.getResultList();
        for (Object[] obj : itens) {
            Linha linha = new Linha();
            linha.setId(Integer.parseInt(obj[0].toString()));
            linha.setNome(obj[1].toString());
            linhas.add(linha);
        }
        return linhas;
    }
    
    public Linha carregarPorNome(String nome) {
        TypedQuery<Linha> query = em.createQuery("select l from Linha l where lower(l.nome) = :pNome", Linha.class);
        query.setParameter("pNome", nome.toLowerCase());
        return query.getSingleResult();
    }
}
