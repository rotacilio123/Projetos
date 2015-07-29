/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Fabricante;
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
public class FabricanteDao {

    private Dao<Fabricante> dao;
    private EntityManager em;

    public FabricanteDao() {
        dao = new Dao<Fabricante>(Fabricante.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Fabricante t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Fabricante t) {
        dao.excluir(t);
    }

    public Fabricante carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Fabricante> listar() {
        return dao.listar();
    }

    public Fabricante carregarPorNome(String nome) {
        TypedQuery<Fabricante> q = em.createQuery("select f from Fabricante f where lower(f.nome) = :pNome", Fabricante.class);
        q.setParameter("pNome", nome.trim().toLowerCase());
        return q.getSingleResult();
    }

    public List<Fabricante> filtrarFabricantes(String nome) {
        StringBuilder str = new StringBuilder();
        List<Fabricante> fabricantes = new ArrayList<Fabricante>();

        str.append("select * from fabricantes ");
        str.append("where true ");
        if (nome != null && nome.trim().length() > 0) {
            str.append("and lower(fabr_nome) like '" + nome.trim().toLowerCase() + "%' ");
        }
        str.append("order by fabr_id");
        
        Query q = em.createNativeQuery(str.toString());
        List<Object[]> itens = q.getResultList();

        for (Object[] obj : itens) {
            Fabricante f = new Fabricante();
            f.setId(Integer.valueOf(obj[0].toString()));
            f.setNome(obj[1].toString());
            fabricantes.add(f);
        }
        return fabricantes;
    }
}
