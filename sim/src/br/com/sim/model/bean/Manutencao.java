/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.model.bean;

import br.com.sim.util.SequencesName;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author RodrigoOtacilio
 */
@Entity
@Table(name = "manutencoes")
@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = SequencesName.SEQUENCE_NAME_MANUTENCOES, name = SequencesName.SEQUENCE_NAME_MANUTENCOES)
@Inheritance(strategy = InheritanceType.JOINED)
public class Manutencao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4654797934565284134L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SequencesName.SEQUENCE_NAME_MANUTENCOES)
    @Column(name = "manu_id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "manu_maqu_id", nullable = false)
    private Maquina maquina;
    
    @OneToOne
    @JoinColumn(name = "manu_usua_id_tecnico")
    private Usuario tecnico;
    
    @OneToOne
    @JoinColumn(name = "manu_stat_id", nullable = false)
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.maquina);
        hash = 23 * hash + Objects.hashCode(this.tecnico);
        hash = 23 * hash + Objects.hashCode(this.status);
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
        final Manutencao other = (Manutencao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.maquina, other.maquina)) {
            return false;
        }
        if (!Objects.equals(this.tecnico, other.tecnico)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Manutencao{" + "id=" + id + ", maquina=" + maquina + ", tecnico=" + tecnico + ", status=" + status + '}';
    }

    public Manutencao() {
    }
}
