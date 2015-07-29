/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Manutencao;
import br.com.sim.model.dao.ManutencaoDao;
import java.util.List;

/**
 *
 * @author RodrigoOtacilio
 */
public class ManutencaoRn {

    private ManutencaoDao manutencaoDao;

    public ManutencaoRn() {
        manutencaoDao = new ManutencaoDao();
    }

    public void salvar(Manutencao t) {
        manutencaoDao.salvar(t);
    }

    public void excluir(Manutencao t) {
        manutencaoDao.excluir(t);
    }

    public Manutencao carregarPorId(Integer id) {
        return manutencaoDao.carregarPorId(id);
    }

    public List<Manutencao> listar() {
        return manutencaoDao.listar();
    }
    
    
}
