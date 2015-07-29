/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Setor;
import br.com.sim.model.dao.SetorDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class SetorRn {
    
    private SetorDao setorDao;

    public SetorRn() {
        setorDao = new SetorDao();
    }

    public void salvar(Setor t) {
        t.setNome(t.getNome().toUpperCase());
        setorDao.salvar(t);
    }

    public void excluir(Setor t) {
        setorDao.excluir(t);
    }

    public Setor carregarPorId(Integer id) {
        return setorDao.carregarPorId(id);
    }

    public List<Setor> listar() {
        return setorDao.listar();
    }

    public Setor carregarPorNome(String nome) {
        return setorDao.carregarPorNome(nome);
    }

    public List<Setor> filtrarSetores(String nome) {
        return setorDao.filtrarSetores(nome);
    }
    
    
}
