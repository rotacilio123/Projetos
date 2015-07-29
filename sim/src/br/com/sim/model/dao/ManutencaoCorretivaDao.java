/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.ManutencaoCorretiva;
import br.com.sim.model.bean.Maquina;
import br.com.sim.util.JpaUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author RodrigoOtacilio
 */
public class ManutencaoCorretivaDao {

    private Dao<ManutencaoCorretiva> dao;
    private EntityManager em;

    public ManutencaoCorretivaDao() {
        dao = new Dao<ManutencaoCorretiva>(ManutencaoCorretiva.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(ManutencaoCorretiva t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(ManutencaoCorretiva t) {
        dao.excluir(t);
    }

    public ManutencaoCorretiva carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<ManutencaoCorretiva> listar() {
        return dao.listar();
    }
    
    public List<ManutencaoCorretiva> listarPorMaquina(Maquina maquina) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<ManutencaoCorretiva> cq = cb.createQuery(ManutencaoCorretiva.class);
    	Root<ManutencaoCorretiva> manutencaoCorretiva = cq.from(ManutencaoCorretiva.class); 
    	cq.distinct(true).where(cb.equal(manutencaoCorretiva.get("maquina"), cb.parameter(Maquina.class, "pMaquina")));
    	TypedQuery<ManutencaoCorretiva> query = em.createQuery(cq);
    	query.setParameter("pMaquina", maquina);
    	return query.getResultList();
    }
}
