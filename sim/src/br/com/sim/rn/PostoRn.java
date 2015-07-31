/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.rn;

import br.com.sim.model.bean.Linha;
import br.com.sim.model.bean.Posto;
import br.com.sim.model.dao.PostoDao;
import java.util.List;

/**
 *
 * @author Rodrigo Otacilio
 */
public class PostoRn {
 
    private PostoDao postoDao;

    public PostoRn() {
        postoDao = new PostoDao();
    }

    public void salvar(Posto t) {
        t.setDescricao(t.getDescricao().toUpperCase());
        postoDao.salvar(t);
    }

    public void excluir(Posto t) {
        postoDao.excluir(t);
    }

    public Posto carregarPorId(Integer id) {
        return postoDao.carregarPorId(id);
    }

    public List<Posto> listar() {
        return postoDao.listar();
    }

    public Posto pesquisarPosto(String descricao, Linha linha) {
        return postoDao.pesquisarPosto(descricao, linha);
    }

    public List<Posto> filtrarPostos(String descricao, Linha linha) {
        return postoDao.filtrarPostos(descricao, linha);
    }

	public List<Posto> listarPorLinha(Linha linha) {
		// TODO Auto-generated method stub
		return postoDao.listarPorLinha(linha);
	}
}
