/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Status;
import br.com.sim.model.dao.StatusDao;

/**
 *
 * @author RodrigoOtacilio
 */
public class StatusRn {

    private StatusDao statusDao;

    public StatusRn() {
        statusDao = new StatusDao();
    }

    public Status carregarPorId(Integer id) {
        return statusDao.carregarPorId(id);
    }
    
    
}
