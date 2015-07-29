/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Empresa;
import br.com.sim.model.dao.EmpresaDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class EmpresaRn {
    
    private EmpresaDao empresaDao;

    public EmpresaRn() {
        empresaDao = new EmpresaDao();
    }

    public void salvar(Empresa t) {
        t.setNome(t.getNome().toUpperCase());
        empresaDao.salvar(t);
    }

    public void excluir(Empresa t) {
        empresaDao.excluir(t);
    }

    public Empresa carregarPorId(Integer id) {
        return empresaDao.carregarPorId(id);
    }

    public List<Empresa> listar() {
        return empresaDao.listar();
    }

    public Empresa carregarPorNomeECodigo(Integer codigo, String nome) {
        return empresaDao.carregarPorNomeECodigo(codigo, nome);
    }

    public List<Empresa> filtrarEmpresas(Integer codigo, String nome) {
        return empresaDao.filtrarEmpresas(codigo, nome);
    }
    
    
}
