/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.ManutencaoCorretiva;
import br.com.sim.model.bean.Maquina;
import br.com.sim.model.dao.ManutencaoCorretivaDao;

import java.util.Date;
import java.util.List;

/**
 *
 * @author RodrigoOtacilio
 */
public class ManutencaoCorretivaRn {

    private ManutencaoCorretivaDao manutencaoCorretivaDao;

    public ManutencaoCorretivaRn() {
        manutencaoCorretivaDao = new ManutencaoCorretivaDao();
    }

    public void salvar(ManutencaoCorretiva t) {
        t.setProblema(t.getProblema().toUpperCase());
        if (t.getId() == null || t.getId() == 0) {
        	t.setStatus(new StatusRn().carregarPorId(1));
        	t.setDataAbertura(new Date());
        }
        manutencaoCorretivaDao.salvar(t);
    }

    public void excluir(ManutencaoCorretiva t) {
        manutencaoCorretivaDao.excluir(t);
    }

    public ManutencaoCorretiva carregarPorId(Integer id) {
        return manutencaoCorretivaDao.carregarPorId(id);
    }

    public List<ManutencaoCorretiva> listar() {
        return manutencaoCorretivaDao.listar();
    }

	public List<ManutencaoCorretiva> listarPorMaquina(Maquina maquina) {
		return manutencaoCorretivaDao.listarPorMaquina(maquina);
	}
    
    
}
