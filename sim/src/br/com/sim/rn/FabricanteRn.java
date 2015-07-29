/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Fabricante;
import br.com.sim.model.dao.FabricanteDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class FabricanteRn {
    
    private FabricanteDao fabricanteDao;

    public FabricanteRn() {
        fabricanteDao = new FabricanteDao();
    }

    public void salvar(Fabricante t) {
        t.setNome(t.getNome().toUpperCase());
        fabricanteDao.salvar(t);
    }

    public void excluir(Fabricante t) {
        fabricanteDao.excluir(t);
    }

    public Fabricante carregarPorId(Integer id) {
        return fabricanteDao.carregarPorId(id);
    }

    public List<Fabricante> listar() {
        return fabricanteDao.listar();
    }

    public Fabricante carregarPorNome(String nome) {
        return fabricanteDao.carregarPorNome(nome);
    }

    public List<Fabricante> filtrarFabricantes(String nome) {
        return fabricanteDao.filtrarFabricantes(nome);
    }
}
