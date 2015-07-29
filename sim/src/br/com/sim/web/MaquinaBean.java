package br.com.sim.web;

import br.com.sim.model.bean.CheckList;
import br.com.sim.model.bean.Fabricante;
import br.com.sim.model.bean.Familia;
import br.com.sim.model.bean.Local;
import java.util.List;

import javax.faces.bean.ManagedBean;
import br.com.sim.model.bean.Maquina;
import br.com.sim.model.bean.Posto;
import br.com.sim.rn.CheckListRn;
import br.com.sim.rn.FabricanteRn;
import br.com.sim.rn.FamiliaRn;
import br.com.sim.rn.LocalRn;
import br.com.sim.rn.MaquinaRn;
import br.com.sim.rn.PostoRn;
import br.com.sim.util.Messages;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.NoResultException;

@ManagedBean(name = "maquinaBean")
@ViewScoped
public class MaquinaBean {

    private Maquina maquina = new Maquina();
    private List<Maquina> maquinas;
    private List<Local> locais;
    private List<Fabricante> fabricantes;
    private List<Posto> postos;
    private List<Familia> familias;
    private List<CheckList> checkListsNaoAssociados;
    private List<CheckList> checkListsAssociados = new ArrayList<CheckList>();
    private List<CheckList> checkListsAux = new ArrayList<CheckList>();

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        MaquinaRn maquinaRn = new MaquinaRn();
        CheckListRn checkListRn = new CheckListRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("maqu_id"));
            maquina = maquinaRn.carregarPorId(id);
            checkListsNaoAssociados = checkListRn.listarCheckListPorFamilia(maquina.getFamilia());
            checkListsNaoAssociados.removeAll(maquina.getCheckList());
            checkListsAssociados = maquina.getCheckList();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getClass().getName());
        }
    }

    public List<CheckList> getCheckListsNaoAssociados() {
        return checkListsNaoAssociados;
    }

    public void setCheckListsNaoAssociados(List<CheckList> checkListsNaoAssociados) {
        this.checkListsNaoAssociados = checkListsNaoAssociados;
    }

    public List<CheckList> getCheckListsAux() {
        return checkListsAux;
    }

    public void setCheckListsAux(List<CheckList> checkListsAux) {
        this.checkListsAux = checkListsAux;
    }

    public List<CheckList> getCheckListsAssociados() {
        return checkListsAssociados;
    }

    public void setCheckListsAssociados(List<CheckList> checkListsAssociados) {
        this.checkListsAssociados = checkListsAssociados;
    }

    public List<Maquina> getMaquinas() {
        if (maquinas == null) {
            maquinas = new MaquinaRn().listar();
        }
        return maquinas;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public List<Local> getLocais() {
        if (locais == null) {
            locais = new LocalRn().listar();
        }
        return locais;
    }

    public List<Fabricante> getFabricantes() {
        if (fabricantes == null) {
            fabricantes = new FabricanteRn().listar();
        }
        return fabricantes;
    }

    public List<Posto> getPostos() {
        if (postos == null) {
            postos = new PostoRn().listar();
        }
        return postos;
    }

    public List<Familia> getFamilias() {
        if (familias == null) {
            familias = new FamiliaRn().listar();
        }
        return familias;
    }

    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        MaquinaRn maquinaRn = new MaquinaRn();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("maqu_id"));
        try {
            maquina = maquinaRn.carregarPorId(id);
            maquinaRn.excluir(maquina);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/maquinas/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }

    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        MaquinaRn maquinaRn = new MaquinaRn();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("maqu_id"));
        try {
            maquina = maquinaRn.carregarPorId(id);
            maquinaRn.excluir(maquina);
            maquina = new Maquina();
            maquinas = null;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }

    public String filtrarMaquinas() {
        MaquinaRn maquinaRn = new MaquinaRn();
        maquinas = maquinaRn.filtrarMaquinas(maquina.getOrdem(), maquina.getFamilia(), maquina.getNumeroDeSerie());
        return null;
    }

    public String limparCampos() {
        maquina = new Maquina();
        maquinas = null;
        return null;
    }

    public String salvarMaquina() {
        MaquinaRn maquinaRn = new MaquinaRn();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                Maquina maquinaAux = maquinaRn.carregarPorNumeroDeSerie(maquina.getNumeroDeSerie());
                if (maquinaAux != null && (maquina.getId() == null || maquina.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {
            }
            maquina.setCheckList(checkListsAssociados);
            maquinaRn.salvar(maquina);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            maquina = new Maquina();
            maquinas = null;
            return "/restrito/maquinas/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public void carregarItens(ValueChangeEvent evt) {
        Familia familia = (Familia) evt.getNewValue();
        checkListsNaoAssociados = new CheckListRn().listarCheckListPorFamilia(familia);
        checkListsAux.clear();
        checkListsAssociados.clear();
    }
    
    public String associarItens() {
        checkListsAssociados.addAll(checkListsAux);
        checkListsNaoAssociados.removeAll(checkListsAux);
        checkListsAux.clear();
        return null;
    }
    
    public String desassociarItens() {
        checkListsNaoAssociados.addAll(checkListsAux);
        checkListsAssociados.removeAll(checkListsAux);
        checkListsAux.clear();
        return null;
    }
    
    public String ativarMaquina() {
        FacesContext context = FacesContext.getCurrentInstance();
        MaquinaRn maquinaRn = new MaquinaRn();
        Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("maqu_id"));
        maquina = maquinaRn.carregarPorId(id);
        if (maquina.isAtiva())
            maquina.setAtiva(false);
        else 
            maquina.setAtiva(true);
        maquinaRn.salvar(maquina);
        maquina = new Maquina();
        maquinas = null;
        return null;
    }
}
