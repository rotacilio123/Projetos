/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Linha;
import br.com.sim.model.dao.LinhaDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class LinhaRn {

    private LinhaDao linhaDao;

    public LinhaRn() {
        linhaDao = new LinhaDao();
    }

    public void salvar(Linha t) {
        linhaDao.salvar(t);
    }

    public void excluir(Linha t) {
        linhaDao.excluir(t);
    }

    public Linha carregarPorId(Integer id) {
        return linhaDao.carregarPorId(id);
    }

    public List<Linha> listar() {
        return linhaDao.listar();
    }

    public List<Linha> filtrarLinhas(String nome) {
        return linhaDao.filtrarLinhas(nome);
    }

    public Linha carregarPorNome(String nome) {
        return linhaDao.carregarPorNome(nome);
    }
}
