/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.CheckList;
import br.com.sim.model.bean.Familia;
import br.com.sim.model.bean.Item;
import br.com.sim.rn.FamiliaRn;
import br.com.sim.rn.ItemRn;
import br.com.sim.util.JpaUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author RodrigoOtacilio
 */
public class CheckListDao {

    private Dao<CheckList> dao;
    private EntityManager em;

    public CheckListDao() {
        dao = new Dao<CheckList>(CheckList.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(CheckList t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(CheckList t) {
        dao.excluir(t);
    }

    public CheckList carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<CheckList> listarPorDescricao(String descricao) {
        TypedQuery<CheckList> q = em.createQuery("select c from CheckList c where lower(c.descricao) = :pDescricao", CheckList.class);
        q.setParameter("pDescricao", descricao.toLowerCase());
        return q.getResultList();
    }

    public List<String> listar() {
        TypedQuery<String> query = em.createQuery("select distinct cl.descricao from CheckList cl", String.class);
        return query.getResultList();
    }

    public CheckList carregarPorFamiliaEItem(Familia f, Item i) {
        TypedQuery<CheckList> query = em.createQuery("select c from CheckList c where c.familia = :pFamilia and c.item = :pItem", CheckList.class);
        query.setParameter("pFamilia", f);
        query.setParameter("pItem", i);
        return query.getSingleResult();
    }

    public List<Familia> listarFamiliasPorDescricao(String descricao) {
        TypedQuery<Familia> q = em.createQuery("select distinct c.familia from CheckList c where lower(c.descricao) = :pDescricao", Familia.class);
        q.setParameter("pDescricao", descricao.toLowerCase());
        return q.getResultList();
    }

    public List<Item> listarItensPorDescricao(String descricao) {
        TypedQuery<Item> q = em.createQuery("select distinct c.item from CheckList c where lower(c.descricao) = :pDescricao", Item.class);
        q.setParameter("pDescricao", descricao.toLowerCase());
        return q.getResultList();
    }

    public List<CheckList> listarCheckListPorFamilia(Familia familia) {
        TypedQuery<CheckList> q = em.createQuery("select c from CheckList c where c.familia = :pFamilia order by c.id", CheckList.class);
        q.setParameter("pFamilia", familia);
        return q.getResultList();
    }
}
