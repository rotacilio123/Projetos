/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Perfil;
import br.com.sim.model.dao.PerfilDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class PerfilRn {

    private PerfilDao perfilDao;

    public PerfilRn() {
        perfilDao = new PerfilDao();
    }

    public void salvar(Perfil t) {
        t.setNome(t.getNome().toUpperCase());
        perfilDao.salvar(t);
    }

    public void excluir(Perfil t) {
        perfilDao.excluir(t);
    }

    public Perfil carregarPorId(Integer id) {
        return perfilDao.carregarPorId(id);
    }

    public List<Perfil> listar() {
        return perfilDao.listar();
    }

    public Perfil carregarPorNome(String nome) {
        return perfilDao.carregarPorNome(nome);
    }

    public List<Perfil> filtrarPerfis(String nome, String descricao) {
        return perfilDao.filtrarPerfis(nome, descricao);
    }
    
    
}
