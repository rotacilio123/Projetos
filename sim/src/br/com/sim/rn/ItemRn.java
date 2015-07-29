/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Item;
import br.com.sim.model.dao.ItemDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class ItemRn {

    private ItemDao itemDao;

    public ItemRn() {
        this.itemDao = new ItemDao();
    }

    public void salvar(Item t) {
        t.setDescricao(t.getDescricao().toUpperCase());
        itemDao.salvar(t);
    }

    public void excluir(Item t) {
        itemDao.excluir(t);
    }

    public Item carregarPorId(Integer id) {
        return itemDao.carregarPorId(id);
    }

    public List<Item> listar() {
        return itemDao.listar();
    }

    public List<Item> filtrarItens(String descricao) {
        return itemDao.filtrarItens(descricao);
    }

    public Item carregarPorDescricao(String descricao) {
        return itemDao.carregarPorDescricao(descricao);
    }
}
