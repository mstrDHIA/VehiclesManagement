/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DHIA
 */
@Entity
@Table(name = "Frais_actifes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fraisactifes.findAll", query = "SELECT f FROM Fraisactifes f")
    , @NamedQuery(name = "Fraisactifes.findByIdActif", query = "SELECT f FROM Fraisactifes f WHERE f.idActif = :idActif")
    , @NamedQuery(name = "Fraisactifes.findByValidation", query = "SELECT f FROM Fraisactifes f WHERE f.validation = :validation")
    , @NamedQuery(name = "Fraisactifes.findByDateValidation", query = "SELECT f FROM Fraisactifes f WHERE f.dateValidation = :dateValidation")
    , @NamedQuery(name = "Fraisactifes.findByDateActif", query = "SELECT f FROM Fraisactifes f WHERE f.dateActif = :dateActif")
    , @NamedQuery(name = "Fraisactifes.findByPeriodeActif", query = "SELECT f FROM Fraisactifes f WHERE f.periodeActif = :periodeActif")})
public class Fraisactifes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_actif")
    private Integer idActif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validation")
    private boolean validation;
    @Column(name = "date_validation")
    @Temporal(TemporalType.DATE)
    private Date dateValidation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_actif")
    @Temporal(TemporalType.DATE)
    private Date dateActif;
    @Column(name = "periode_actif")
    private Integer periodeActif;
    @JoinColumn(name = "id_frais", referencedColumnName = "id_frais")
    @ManyToOne(optional = false)
    private Frais idFrais;

    public Fraisactifes() {
    }

    public Fraisactifes(Integer idActif) {
        this.idActif = idActif;
    }

    public Fraisactifes(Integer idActif, boolean validation, Date dateActif) {
        this.idActif = idActif;
        this.validation = validation;
        this.dateActif = dateActif;
    }

    public Integer getIdActif() {
        return idActif;
    }

    public void setIdActif(Integer idActif) {
        this.idActif = idActif;
    }

    public boolean getValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Date getDateActif() {
        return dateActif;
    }

    public void setDateActif(Date dateActif) {
        this.dateActif = dateActif;
    }

    public Integer getPeriodeActif() {
        return periodeActif;
    }

    public void setPeriodeActif(Integer periodeActif) {
        this.periodeActif = periodeActif;
    }

    public Frais getIdFrais() {
        return idFrais;
    }

    public void setIdFrais(Frais idFrais) {
        this.idFrais = idFrais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActif != null ? idActif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fraisactifes)) {
            return false;
        }
        Fraisactifes other = (Fraisactifes) object;
        if ((this.idActif == null && other.idActif != null) || (this.idActif != null && !this.idActif.equals(other.idActif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fraisactifes[ idActif=" + idActif + " ]";
    }
    
}
