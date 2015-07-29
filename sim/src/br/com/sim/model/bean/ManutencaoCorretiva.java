/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author RodrigoOtacilio
 */
@Entity
@Table(name = "manutencoes_corretivas")
@PrimaryKeyJoinColumn(name = "maco_manu_id")
public class ManutencaoCorretiva extends Manutencao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8857344236410305808L;

	@ManyToOne
    @JoinColumn(name = "maco_seto_id", nullable = false)
    private Setor setor;
    
    @ManyToOne
    @JoinColumn(name = "maco_usua_id_solicitante", nullable = false)
    private Usuario solicitante;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "maco_dt_abertura", nullable = false)
    private Date dataAbertura;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "maco_dt_realizada")
    private Date dataRealizada;
    
    @Column(name = "maco_problema", length = 100, nullable = false)
    private String problema;

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(Date dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    @Override
    public int hashCode() {
        int hash = 3;
//        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.setor);
        hash = 11 * hash + Objects.hashCode(this.solicitante);
        hash = 11 * hash + Objects.hashCode(this.dataAbertura);
        hash = 11 * hash + Objects.hashCode(this.dataRealizada);
        hash = 11 * hash + Objects.hashCode(this.problema);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ManutencaoCorretiva other = (ManutencaoCorretiva) obj;
        if (!Objects.equals(this.setor, other.setor)) {
            return false;
        }
        if (!Objects.equals(this.solicitante, other.solicitante)) {
            return false;
        }
        if (!Objects.equals(this.dataAbertura, other.dataAbertura)) {
            return false;
        }
        if (!Objects.equals(this.dataRealizada, other.dataRealizada)) {
            return false;
        }
        if (!Objects.equals(this.problema, other.problema)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManutencaoCorretiva{" + "setor=" + setor + ", solicitante=" + solicitante + ", dataAbertura=" + dataAbertura + ", dataRealizada=" + dataRealizada + ", problema=" + problema + '}';
    }

    public ManutencaoCorretiva() {
    }
    
    
}
