package br.com.sim.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sim.util.JpaUtil;

public class Dao<T> {

	private EntityManager em;
	private Class<T> classe;
	
	public Dao(Class<T> classe) {
		// TODO Auto-generated constructor stub
		this.classe = classe;
		em = JpaUtil.getEntityManager();
	}
	
	public void salvar(T t) {
		em.persist(t);
	}
	
	public void atualizar(T t) {
		em.persist(em.merge(t));
	}
	
	public void excluir(T t) {
		em.remove(t);
	}
	
	public T carregarPorId(Integer id) {
		TypedQuery<T> query = em.createQuery("select t from " + classe.getName() + " t where t.id = :pId", classe);
		query.setParameter("pId", id);
		return query.getSingleResult();
	}
	
	public List<T> listar() {
		TypedQuery<T> query = em.createQuery("select t from " + classe.getName() + " t order by t.id", classe);
		return query.getResultList();
	}
}
