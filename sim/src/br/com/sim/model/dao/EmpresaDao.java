/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Empresa;
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
public class EmpresaDao {

    private Dao<Empresa> dao;
    private EntityManager em;

    public EmpresaDao() {
        dao = new Dao<Empresa>(Empresa.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Empresa t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void atualizar(Empresa t) {
        dao.atualizar(t);
    }

    public void excluir(Empresa t) {
        dao.excluir(t);
    }

    public Empresa carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Empresa> listar() {
        return dao.listar();
    }

    public Empresa carregarPorNomeECodigo(Integer codigo, String nome) {
        TypedQuery<Empresa> q = em.createQuery("select e from Empresa e where e.codigo = :pCodigo and lower(e.nome) = :pNome", Empresa.class);
        q.setParameter("pCodigo", codigo);
        q.setParameter("pNome", nome.toLowerCase());
        return q.getSingleResult();
    }

    public List<Empresa> filtrarEmpresas(Integer codigo, String nome) {
        StringBuilder str = new StringBuilder();
        List<Empresa> empresas = new ArrayList<Empresa>();
        
        str.append("select * from empresas ");
        str.append("where true ");
        if (codigo != null && codigo > 0) {
            str.append("and empr_codigo = " + codigo + " ");
        }
        if (nome != null && nome.trim().length() > 0) {
            str.append(" and lower(empr_nome) like '" + nome.trim().toLowerCase() + "%'");
        }
        str.append("order by empr_id");
        
        Query q = em.createNativeQuery(str.toString());
        List<Object[]> itens = q.getResultList();
        for (Object[] obj : itens) {
            Empresa e = new Empresa();
            e.setId(Integer.valueOf(obj[0].toString()));
            e.setNome(obj[1].toString());
            e.setCodigo(Integer.valueOf(obj[2].toString()));
            empresas.add(e);
        }
        return empresas;
    }
}
