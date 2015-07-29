package br.com.sim.model.dao;

import java.util.List;

import br.com.sim.model.bean.Usuario;
import br.com.sim.util.JpaUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UsuarioDao {

    private Dao<Usuario> dao;
    private EntityManager em;

    public UsuarioDao() {
        dao = new Dao<Usuario>(Usuario.class);
        em = JpaUtil.getEntityManager();
    }

    public void salvar(Usuario t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Usuario t) {
        dao.excluir(t);
    }

    public Usuario carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public Usuario carregarPorEmailELogin(String email, String login) {
        TypedQuery<Usuario> q = em.createQuery("select u from Usuario u where lower(u.email) = :pEmail and lower(u.login) = :pLogin", Usuario.class);
        q.setParameter("pEmail", email.trim().toLowerCase());
        q.setParameter("pLogin", login.trim().toLowerCase());
        return q.getSingleResult();
    }

    public Usuario carregarPorLogin(String login) {
        TypedQuery<Usuario> q = em.createQuery("select u from Usuario u where lower(u.login) = :pLogin", Usuario.class);
        q.setParameter("pLogin", login.trim().toLowerCase());
        return q.getSingleResult();
    }

    public List<Usuario> filtrarUsuarios(String nome, String email, String login, Boolean status) {
        StringBuilder sql = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        
        sql.append("select * from usuarios ");
        sql.append("where true ");
        if (nome != null && nome.trim().length() > 0) {
            sql.append("and lower(usua_nome) like '" + nome.trim().toLowerCase() + "%' ");
        }
        if (email != null && email.trim().length() > 0) {
            sql.append("and lower(usua_email) like '" + email.trim().toLowerCase() + "%' ");
        }
        if (login != null && login.trim().length() > 0) {
            sql.append("and lower(usua_login) like '" + login.trim().toLowerCase() + "%' ");
        }
        if (status != null) {
            sql.append("and usua_ativo is " + status.booleanValue() + " ");
        }
        sql.append("order by usua_id");
        
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        Query q = em.createNativeQuery(sql.toString());
        List<Object[]> itens = q.getResultList();

        try {
            for (Object[] obj : itens) {
                Usuario u = new Usuario();
                u.setId(Integer.valueOf(obj[0].toString()));
                u.setNome(obj[1].toString());
                u.setEmail(obj[2].toString());
                u.setLogin(obj[3].toString());
                u.setSenha(obj[4].toString());
                u.setAtivo(Boolean.valueOf(obj[5].toString()));
                u.setDataCadastro(formatter.parse(obj[6].toString()));
                usuarios.add(u);
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return usuarios;
    }
}
