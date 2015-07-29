/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Manutencao;
import java.util.List;

/**
 *
 * @author RodrigoOtacilio
 */
public class ManutencaoDao {

    private Dao<Manutencao> dao;

    public ManutencaoDao() {
        dao = new Dao<Manutencao>(Manutencao.class);
    }

    public void salvar(Manutencao t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Manutencao t) {
        dao.excluir(t);
    }

    public Manutencao carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Manutencao> listar() {
        return dao.listar();
    }
    
    
}
