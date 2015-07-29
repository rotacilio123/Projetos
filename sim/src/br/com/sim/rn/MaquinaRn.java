package br.com.sim.rn;

import br.com.sim.model.bean.Familia;
import java.util.List;

import br.com.sim.model.bean.Maquina;
import br.com.sim.model.bean.Posto;
import br.com.sim.model.dao.MaquinaDao;
import java.util.Date;

public class MaquinaRn {

    private MaquinaDao maquinaDao;

    public MaquinaRn() {
        // TODO Auto-generated constructor stub
        this.maquinaDao = new MaquinaDao();
    }

    public void salvar(Maquina t) {
        if (t.getId() == null || t.getId() == 0) {
            
        }
        t.setDescricao(t.getDescricao().toUpperCase());
        t.setModelo(t.getModelo().toUpperCase());
        t.setNumeroDeSerie(t.getNumeroDeSerie().toUpperCase());
        maquinaDao.salvar(t);
    }

    public void excluir(Maquina t) {
        maquinaDao.excluir(t);
    }

    public Maquina carregarPorId(Integer id) {
        return maquinaDao.carregarPorId(id);
    }

    public List<Maquina> listar() {
        return maquinaDao.listar();
    }

    public Maquina carregarPorNumeroDeSerie(String numeroDeSerie) {
        return maquinaDao.carregarPorNumeroDeSerie(numeroDeSerie);
    }

    public List<Maquina> filtrarMaquinas(Integer ordem, Familia familia, String numeroDeSerie) {
        return maquinaDao.filtrarMaquinas(ordem, familia, numeroDeSerie);
    }

    public List<Maquina> listarMaquinasPorPosto(Posto posto) {
        return maquinaDao.listarMaquinasPorPosto(posto);
    }
    
    public void criarManutencaoPreventiva(Maquina maquina) {
        
    }
}
