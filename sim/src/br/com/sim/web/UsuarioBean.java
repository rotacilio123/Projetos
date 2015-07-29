package br.com.sim.web;

import br.com.sim.model.bean.Perfil;
import javax.faces.bean.ManagedBean;
import br.com.sim.model.bean.Usuario;
import br.com.sim.rn.PerfilRn;
import br.com.sim.rn.UsuarioRn;
import br.com.sim.util.Messages;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private List<Perfil> perfisNaoAssociados;
    private List<Perfil> perfisAssociados = new ArrayList<Perfil>();
    private List<Perfil> perfisAux = new ArrayList<Perfil>();
    private Boolean usuarioStatus;
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioRn usuarioRn = new UsuarioRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("usua_id"));
            usuario = usuarioRn.carregarPorId(id);
            perfisAssociados.addAll(usuario.getPerfis());
            getPerfisNaoAssociados().removeAll(usuario.getPerfis());
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Perfil> getPerfisAux() {
        return perfisAux;
    }

    public void setPerfisAux(List<Perfil> perfisAux) {
        this.perfisAux = perfisAux;
    }

    public List<Perfil> getPerfisAssociados() {
        return perfisAssociados;
    }

    public Boolean getUsuarioStatus() {
        return usuarioStatus;
    }

    public void setUsuarioStatus(Boolean usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }

    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = new UsuarioRn().listar();
        }
        return usuarios;
    }

    public List<Perfil> getPerfisNaoAssociados() {
        if (perfisNaoAssociados == null) {
            perfisNaoAssociados = new PerfilRn().listar();
        }
        return perfisNaoAssociados;
    }

    public String salvarUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioRn usuarioRn = new UsuarioRn();
        try {
            Usuario usuarioAux = null;
            try {
                usuarioAux = usuarioRn.carregarPorEmailELogin(usuario.getEmail(), usuario.getLogin());
                if (usuarioAux != null && (usuario.getId() == null || usuario.getId() == 0)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.REGISTRO_EXISTE, null));
                    return null;
                }
            } catch (NoResultException e) {}
            usuario.setPerfis(perfisAssociados);
            usuarioRn.salvar(usuario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_SALVO_SUCESSO, null));
            return "/restrito/usuarios/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioRn usuarioRn = new UsuarioRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("usua_id"));
            usuario = usuarioRn.carregarPorId(id);
            usuarioRn.excluir(usuario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
            return "/restrito/usuarios/index";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String excluirAjax() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioRn usuarioRn = new UsuarioRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("usua_id"));
            usuario = usuarioRn.carregarPorId(id);
            usuarioRn.excluir(usuario);
            usuario = new Usuario();
            usuarios = null;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.REGISTRO_DELETADO_SUCESSO, null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
    
    public String limparCampos() {
        usuario = new Usuario();
        usuarios = null;
        usuarioStatus = null;
        return null;
    }
    
    public String filtrarUsuarios() {
        usuarios = new UsuarioRn().filtrarUsuarios(usuario.getNome(), usuario.getEmail(), usuario.getLogin(), usuarioStatus);
        return null;
    }
    
    public String associarPerfis() {
        perfisAssociados.addAll(perfisAux);
        perfisNaoAssociados.removeAll(perfisAux);
        perfisAux.clear();
        return null;
    }
    
    public String desassociarPerfis() {
        perfisNaoAssociados.addAll(perfisAux);
        perfisAssociados.removeAll(perfisAux);
        perfisAux.clear();
        return null;
    }
    
    public String ativarUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioRn usuarioRn = new UsuarioRn();
        try {
            Integer id = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("usua_id"));
            usuario = usuarioRn.carregarPorId(id);
            if (usuario.isAtivo()) {
                usuario.setAtivo(false);
            } else {
                usuario.setAtivo(true);
            }
            usuarioRn.salvar(usuario);
            usuario = new Usuario();
            usuarios = null;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        }
        return null;
    }
}
