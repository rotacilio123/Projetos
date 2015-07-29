package br.com.sim.model.dao;

import br.com.sim.model.bean.Familia;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.sim.model.bean.Maquina;
import br.com.sim.model.bean.Posto;
import br.com.sim.rn.FabricanteRn;
import br.com.sim.rn.FamiliaRn;
import br.com.sim.rn.LocalRn;
import br.com.sim.rn.PostoRn;
import br.com.sim.util.JpaUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class MaquinaDao {

    private final Dao<Maquina> dao;
    private final EntityManager em;

    public MaquinaDao() {
        // TODO Auto-generated constructor stub
        this.dao = new Dao<Maquina>(Maquina.class);
        this.em = JpaUtil.getEntityManager();
    }

    public void salvar(Maquina t) {
        Integer id = t.getId();
        if (id == null || id == 0) {
            dao.salvar(t);
        } else {
            dao.atualizar(t);
        }
    }

    public void excluir(Maquina t) {
        dao.excluir(t);
    }

    public Maquina carregarPorId(Integer id) {
        return dao.carregarPorId(id);
    }

    public List<Maquina> listar() {
        return dao.listar();
    }

    public Maquina carregarPorNumeroDeSerie(String numeroDeSerie) {
        TypedQuery<Maquina> query = em.createQuery("select m from Maquina m where lower(m.numeroDeSerie) = :pNumeroDeSerie", Maquina.class);
        query.setParameter("pNumeroDeSerie", numeroDeSerie.toLowerCase());
        return query.getSingleResult();
    }

    public List<Maquina> filtrarMaquinas(Integer ordem, Familia familia, String numeroDeSerie) {
        StringBuilder str = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        List<Maquina> maquinas = new ArrayList<Maquina>();
        
        str.append("SELECT * FROM maquinas AS m ");
        str.append("WHERE TRUE ");
        if (familia != null) {
            str.append("AND m.maqu_fami_id = " + familia.getId() + " ");
        }
        if (ordem != null && ordem > 0) {
            str.append("AND m.maqu_ordem = " + ordem + " ");
        }
        if (numeroDeSerie != null && numeroDeSerie.trim().length() > 0) {
            str.append("AND LOWER(m.maqu_num_serie) LIKE '" + numeroDeSerie.toLowerCase() + "%' ");
        }
        str.append("ORDER BY m.maqu_id");
        
        Query query = em.createNativeQuery(str.toString());
        List<Object[]> itens = query.getResultList();
        try {
            for (Object[] obj : itens) {
                Maquina maquina = new Maquina();
                maquina.setId(Integer.parseInt(obj[0].toString()));
                maquina.setLocal(new LocalRn().carregarPorId(Integer.valueOf(obj[1].toString())));
                maquina.setPosto(new PostoRn().carregarPorId(Integer.valueOf(obj[2].toString())));
                maquina.setFabricante(new FabricanteRn().carregarPorId(Integer.valueOf(obj[3].toString())));
                maquina.setFamilia(new FamiliaRn().carregarPorId(Integer.valueOf(obj[4].toString())));
                maquina.setOrdem(Integer.valueOf(obj[5].toString()));
                maquina.setNumeroDeSerie(obj[6].toString());
                maquina.setModelo(obj[7].toString());
                if (obj[8] != null && obj[8].toString().trim().length() > 0) {
                    maquina.setDescricao(obj[8].toString());
                }
                maquina.setDataFabricacao(formatter.parse(obj[9].toString()));
                if (obj[10] != null && obj[10].toString().trim().length() > 0) {
                    maquina.setDataInstalacao(formatter.parse(obj[10].toString()));
                }
                maquina.setAtiva(Boolean.valueOf(obj[11].toString()));
                maquina.setPeriodoManutencao(Integer.valueOf(obj[12].toString()));
                maquinas.add(maquina);
            }
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return maquinas;
    }
    
    public List<Maquina> listarMaquinasPorPosto(Posto posto) {
        TypedQuery<Maquina> query = em.createQuery("select m from Maquina m where m.posto = :pPosto", Maquina.class);
        query.setParameter("pPosto", posto);
        return query.getResultList();
    }
}
