/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Empresa;
import br.com.sim.model.bean.Local;
import br.com.sim.rn.EmpresaRn;
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
public class LocalDao {
    
    private Dao<Local> dao;
    private EntityManager em;

    public LocalDao() {
        dao = new Dao<Local>(Local.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Local t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Local t) {
        dao.excluir(t);
    }

    public Local carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Local> listar() {
        return dao.listar();
    }
    
    public Local carregarPorCodigoEEMpresa(Integer codigo, Empresa empresa) {
        TypedQuery<Local> q = em.createQuery("select l from Local l where l.codigo = :pCodigo and l.empresa = :pEmpresa", Local.class);
        q.setParameter("pCodigo", codigo);
        q.setParameter("pEmpresa", empresa);
        return q.getSingleResult();
    }
    
    public List<Local> filtrarLocais(Integer codigo, String descricao, Empresa empresa) {
        StringBuilder str = new StringBuilder();
        List<Local> locais = new ArrayList<Local>();
        
        str.append("select * from locais ");
        str.append("where true ");
        if (codigo != null && codigo > 0) {
            str.append("and loca_codigo = " + codigo + " ");
        }
        if (descricao != null && descricao.trim().length() > 0) {
            str.append("and lower(loca_descricao) like '" + descricao.trim().toLowerCase() + "%' ");
        }
        if (empresa != null) {
            str.append("and loca_empr_id = " + empresa.getId() + " ");
        }
        str.append("order by loca_id");
        
        Query q = em.createNativeQuery(str.toString());
        List<Object[]> itens = q.getResultList();
        
        for (Object[] obj : itens) {
            Local l = new Local();
            l.setId(Integer.valueOf(obj[0].toString()));
            l.setCodigo(Integer.valueOf(obj[1].toString()));
            l.setDescricao(obj[2].toString());
            l.setEmpresa(new EmpresaRn().carregarPorId(Integer.valueOf(obj[3].toString())));
            locais.add(l);
        }
        return locais;
    }
}
