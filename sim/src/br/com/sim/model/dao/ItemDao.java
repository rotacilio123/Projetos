/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Familia;
import br.com.sim.model.bean.Item;
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
public class ItemDao {

    private Dao<Item> dao;
    private EntityManager em;

    public ItemDao() {
        this.dao = new Dao<Item>(Item.class);
        this.em = JpaUtil.getEntityManager();
    }

    public void salvar(Item t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Item t) {
        dao.excluir(t);
    }

    public Item carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Item> listar() {
        return dao.listar();
    }
    
    public List<Item> filtrarItens(String descricao) {
        StringBuilder str = new StringBuilder();
        List<Item> itens = new ArrayList<Item>();
        
        str.append("SELECT * FROM itens AS i ");
        str.append("WHERE TRUE ");
        if (descricao != null) {
            str.append("AND LOWER(i.iten_descricao) LIKE '" + descricao.toLowerCase() + "%' ");
        }
        str.append("ORDER BY i.iten_id");
        
        Query query = em.createNativeQuery(str.toString());
        List<Object[]> result = query.getResultList();
        for (Object[] obj : result) {
            Item item = new Item();
            item.setId(Integer.parseInt(obj[0].toString()));
            item.setDescricao(obj[1].toString());
            itens.add(item);
        }
        return itens;
    }
    
    public Item carregarPorDescricao(String descricao) {
        TypedQuery<Item> query = em.createQuery("select i from Item i where lower(i.descricao) = :pDescricao", Item.class);
        query.setParameter("pDescricao", descricao.toLowerCase());
        return query.getSingleResult();
    }
}
