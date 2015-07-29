/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.web;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.sim.model.bean.ManutencaoCorretiva;
import br.com.sim.model.bean.Maquina;
import br.com.sim.model.bean.Setor;
import br.com.sim.rn.ManutencaoCorretivaRn;
import br.com.sim.rn.MaquinaRn;
import br.com.sim.rn.SetorRn;
import br.com.sim.rn.StatusRn;
import br.com.sim.util.ContextoUtil;
import br.com.sim.util.Messages;

/**
 *
 * @author RodrigoOtacilio
 */
@ManagedBean(name = "manutencaoCorretivaBean")
@ViewScoped
public class ManutencaoCorretivaBean {
    
	private List<Maquina> maquinas;
    private List<Setor> setores;
    private List<ManutencaoCorretiva> manutencoesCorretivas;
    private Maquina maquina;
    private ManutencaoCorretiva manutencaoCorretiva = new ManutencaoCorretiva();
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        MaquinaRn maquinaRn = new MaquinaRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("maqu_id"));
            manutencaoCorretiva.setMaquina(maquinaRn.carregarPorId(id));
            manutencaoCorretiva.setSolicitante(ContextoUtil.getUsuarioLogado());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public List<Maquina> getMaquinas() {
        if (maquinas == null) {
            maquinas = new MaquinaRn().listar();
        }
        return maquinas;
    }

    public List<Setor> getSetores() {
        if (setores == null) {
            setores = new SetorRn().listar();
        }
        return setores;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public ManutencaoCorretiva getManutencaoCorretiva() {
        return manutencaoCorretiva;
    }

    public void setManutencaoCorretiva(ManutencaoCorretiva manutencaoCorretiva) {
        this.manutencaoCorretiva = manutencaoCorretiva;
    }
    
    public List<ManutencaoCorretiva> getManutencoesCorretivas() {
		return manutencoesCorretivas;
	}
    
    public String salvarManutencaoCorretiva() {
        FacesContext context = FacesContext.getCurrentInstance();
        ManutencaoCorretivaRn corretivaRn = new ManutencaoCorretivaRn();
        try {            
            manutencaoCorretiva.setStatus(new StatusRn().carregarPorId(1));
            manutencaoCorretiva.setDataAbertura(new Date());
            corretivaRn.salvar(manutencaoCorretiva);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/manutencoes_corretivas/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String carregarManutencoesCorretivas() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	ManutencaoCorretivaRn corretivaRn = new ManutencaoCorretivaRn();
    	MaquinaRn maquinaRn = new MaquinaRn();
    	try {
    		Integer maquId = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("maqu_id"));
    		maquina = maquinaRn.carregarPorId(maquId);
    		manutencoesCorretivas = corretivaRn.listarPorMaquina(maquina);
    		RequestContext.getCurrentInstance().execute("$('#modalManutencoesCorretivas').modal('show');");
    	} catch (Exception e) {
    		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
    	}
    	return null;
    }
}
