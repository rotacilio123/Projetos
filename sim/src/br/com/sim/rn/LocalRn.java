/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Empresa;
import br.com.sim.model.bean.Local;
import br.com.sim.model.dao.LocalDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class LocalRn {
    
    private LocalDao localDao;

    public LocalRn() {
        localDao = new LocalDao();
    }

    public void salvar(Local t) {
        t.setDescricao(t.getDescricao().toUpperCase());
        localDao.salvar(t);
    }

    public void excluir(Local t) {
        localDao.excluir(t);
    }

    public Local carregarPorId(Integer id) {
        return localDao.carregarPorId(id);
    }

    public List<Local> listar() {
        return localDao.listar();
    }

    public Local carregarPorCodigoEEMpresa(Integer codigo, Empresa empresa) {
        return localDao.carregarPorCodigoEEMpresa(codigo, empresa);
    }

    public List<Local> filtrarLocais(Integer codigo, String descricao, Empresa empresa) {
        return localDao.filtrarLocais(codigo, descricao, empresa);
    }
}
