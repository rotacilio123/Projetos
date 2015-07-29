/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.dao;

import br.com.sim.model.bean.Status;

/**
 *
 * @author RodrigoOtacilio
 */
public class StatusDao {

    private Dao<Status> dao;

    public StatusDao() {
        dao = new Dao<Status>(Status.class);
    }

    public Status carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }
    
    
}
