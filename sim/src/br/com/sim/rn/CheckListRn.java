/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.CheckList;
import br.com.sim.model.bean.Familia;
import br.com.sim.model.bean.Item;
import br.com.sim.model.dao.CheckListDao;
import java.util.List;

/**
 *
 * @author RodrigoOtacilio
 */
public class CheckListRn {

    private CheckListDao checkListDao;

    public CheckListRn() {
        checkListDao = new CheckListDao();
    }

    public void salvar(CheckList t) {
        t.setDescricao(t.getDescricao().toUpperCase());
        checkListDao.salvar(t);
    }

    public void excluir(CheckList t) {
        checkListDao.excluir(t);
    }

    public CheckList carregarPorId(Integer id) {
        return checkListDao.carregarPorId(id);
    }

    public List<String> listar() {
        return checkListDao.listar();
    }

    public CheckList carregarPorFamiliaEItem(Familia f, Item i) {
        return checkListDao.carregarPorFamiliaEItem(f, i);
    }

    public List<CheckList> listarPorDescricao(String descricao) {
        return checkListDao.listarPorDescricao(descricao);
    }

    public List<Familia> listarFamiliasPorDescricao(String descricao) {
        return checkListDao.listarFamiliasPorDescricao(descricao);
    }

    public List<Item> listarItensPorDescricao(String descricao) {
        return checkListDao.listarItensPorDescricao(descricao);
    }

    public List<CheckList> listarCheckListPorFamilia(Familia familia) {
        return checkListDao.listarCheckListPorFamilia(familia);
    }

    
}
