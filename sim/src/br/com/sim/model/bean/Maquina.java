package br.com.sim.model.bean;

import br.com.sim.util.SequencesName;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "maquinas")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = SequencesName.SEQUENCE_NAME_MAQUINAS, sequenceName = SequencesName.SEQUENCE_NAME_MAQUINAS)
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SequencesName.SEQUENCE_NAME_MAQUINAS)
    @Column(name = "maqu_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "maqu_loca_id", nullable = false)
    private Local local;

    @ManyToOne
    @JoinColumn(name = "maqu_post_id", nullable = false)
    private Posto posto;

    @ManyToOne
    @JoinColumn(name = "maqu_fabr_id", nullable = false)
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "maqu_fami_id", nullable = false)
    private Familia familia;

    @Column(name = "maqu_ordem", nullable = false)
    private Integer ordem;

    @Column(name = "maqu_num_serie", length = 100, nullable = false)
    private String numeroDeSerie;

    @Column(name = "maqu_modelo", length = 100, nullable = false)
    private String modelo;

    @Column(name = "maqu_descricao", length = 100)
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "maqu_dt_fabricacao", nullable = false)
    private Date dataFabricacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "maqu_dt_instalacao")
    private Date dataInstalacao;

    @Column(name = "maqu_ativa", nullable = false)
    private boolean ativa;
    
    @Column(name = "maqu_periodo_manutencao", nullable = false)
    private Integer periodoManutencao;

    @ManyToMany
    @JoinTable(name = "maquinas_check_list", joinColumns = {@JoinColumn(name = "macl_maqu_id")}, inverseJoinColumns = {@JoinColumn(name = "macl_chli_id")})
    private List<CheckList> checkList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Date getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(Date dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public List<CheckList> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<CheckList> checkList) {
        this.checkList = checkList;
    }

    public Integer getPeriodoManutencao() {
        return periodoManutencao;
    }

    public void setPeriodoManutencao(Integer periodoManutencao) {
        this.periodoManutencao = periodoManutencao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.local);
        hash = 71 * hash + Objects.hashCode(this.posto);
        hash = 71 * hash + Objects.hashCode(this.fabricante);
        hash = 71 * hash + Objects.hashCode(this.familia);
        hash = 71 * hash + Objects.hashCode(this.ordem);
        hash = 71 * hash + Objects.hashCode(this.numeroDeSerie);
        hash = 71 * hash + Objects.hashCode(this.modelo);
        hash = 71 * hash + Objects.hashCode(this.descricao);
        hash = 71 * hash + Objects.hashCode(this.dataFabricacao);
        hash = 71 * hash + Objects.hashCode(this.dataInstalacao);
        hash = 71 * hash + (this.ativa ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.checkList);
        hash = 71 * hash + Objects.hashCode(this.periodoManutencao);
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
        final Maquina other = (Maquina) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.posto, other.posto)) {
            return false;
        }
        if (!Objects.equals(this.fabricante, other.fabricante)) {
            return false;
        }
        if (!Objects.equals(this.familia, other.familia)) {
            return false;
        }
        if (!Objects.equals(this.ordem, other.ordem)) {
            return false;
        }
        if (!Objects.equals(this.numeroDeSerie, other.numeroDeSerie)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.dataFabricacao, other.dataFabricacao)) {
            return false;
        }
        if (!Objects.equals(this.dataInstalacao, other.dataInstalacao)) {
            return false;
        }
        if (this.ativa != other.ativa) {
            return false;
        }
        if (!Objects.equals(this.checkList, other.checkList)) {
            return false;
        }
        if (!Objects.equals(this.periodoManutencao, other.periodoManutencao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Maquina{" + "id=" + id + ", local=" + local + ", posto=" + posto + ", fabricante=" + fabricante + ", familia=" + familia + ", ordem=" + ordem + ", numeroDeSerie=" + numeroDeSerie + ", modelo=" + modelo + ", descricao=" + descricao + ", dataFabricacao=" + dataFabricacao + ", dataInstalacao=" + dataInstalacao + ", ativa=" + ativa + ", checkList=" + checkList + '}';
    }

    public Maquina() {
    }
}
