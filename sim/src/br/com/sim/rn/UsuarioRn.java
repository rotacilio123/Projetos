package br.com.sim.rn;

import br.com.sim.model.bean.Usuario;
import br.com.sim.model.dao.UsuarioDao;
import br.com.sim.util.Utils;
import java.util.Date;
import java.util.List;

public class UsuarioRn {

    private UsuarioDao usuarioDao;

    public UsuarioRn() {
        usuarioDao = new UsuarioDao();
    }

    public void salvar(Usuario t) {
        Integer id = t.getId();
        t.setNome(t.getNome().toUpperCase());
        t.setEmail(t.getEmail().toLowerCase());
        
        if (id == null || id == 0) {
            t.setDataCadastro(new Date());
            t.setSenha(Utils.textToMd5("123456"));
            t.setAtivo(true);
        }
        usuarioDao.salvar(t);
    }

    public void excluir(Usuario t) {
        usuarioDao.excluir(t);
    }

    public Usuario carregarPorId(Integer id) {
        return usuarioDao.carregarPorId(id);
    }

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public Usuario carregarPorEmailELogin(String email, String login) {
        return usuarioDao.carregarPorEmailELogin(email, login);
    }

    public List<Usuario> filtrarUsuarios(String nome, String email, String login, Boolean status) {
        return usuarioDao.filtrarUsuarios(nome, email, login, status);
    }

    public Usuario carregarPorLogin(String login) {
        return usuarioDao.carregarPorLogin(login);
    }

    
}
