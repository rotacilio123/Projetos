/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Familia;
import br.com.sim.model.dao.FamiliaDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class FamiliaRn {

    private FamiliaDao familiaDao;

    public FamiliaRn() {
        familiaDao = new FamiliaDao();
    }

    public void salvar(Familia t) {
        familiaDao.salvar(t);
    }

    public void excluir(Familia t) {
        familiaDao.excluir(t);
    }

    public Familia carregarPorId(Integer id) {
        return familiaDao.carregarPorId(id);
    }

    public List<Familia> listar() {
        return familiaDao.listar();
    }

    public Familia carregarPorCodigo(Integer codigo) {
        return familiaDao.carregarPorCodigo(codigo);
    }

    public List<Familia> filtrarFamilias(Integer codigo) {
        return familiaDao.filtrarFamilias(codigo);
    }
    
    
}
