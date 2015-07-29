/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Familia;
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
public class FamiliaDao {
    
    private Dao<Familia> dao;
    private EntityManager em;

    public FamiliaDao() {
        dao = new Dao<Familia>(Familia.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Familia t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Familia t) {
        dao.excluir(t);
    }

    public Familia carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Familia> listar() {
        return dao.listar();
    }

    public Familia carregarPorCodigo(Integer codigo) {
        TypedQuery<Familia> query = em.createQuery("select f from Familia f where f.codigo = :pCodigo", Familia.class);
        query.setParameter("pCodigo", codigo);
        return query.getSingleResult();
    }

    public List<Familia> filtrarFamilias(Integer codigo) {
        StringBuilder str = new StringBuilder();
        List<Familia> familias = new ArrayList<Familia>();
        
        str.append("SELECT * FROM familias AS f ");
        str.append("WHERE TRUE ");
        if (codigo != null && codigo != 0) {
            str.append("AND f.fami_codigo = " + codigo + " ");
        }
        str.append("ORDER BY f.fami_id");
        
        Query query = em.createNativeQuery(str.toString());
        List<Object[]> itens = query.getResultList();
        for (Object[] obj : itens) {
            Familia familia = new Familia();
            familia.setId(Integer.parseInt(obj[0].toString()));
            familia.setCodigo(Integer.valueOf(obj[1].toString()));
            familias.add(familia);
        }
        return familias;
    }
}
