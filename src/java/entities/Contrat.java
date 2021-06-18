/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DHIA
 */
@Entity
@Table(name = "Contrat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrat.findAll", query = "SELECT c FROM Contrat c")
    , @NamedQuery(name = "Contrat.findByIdContrat", query = "SELECT c FROM Contrat c WHERE c.idContrat = :idContrat")
    , @NamedQuery(name = "Contrat.findByDate", query = "SELECT c FROM Contrat c WHERE c.date = :date")
    , @NamedQuery(name = "Contrat.findByPeriode", query = "SELECT c FROM Contrat c WHERE c.periode = :periode")
    , @NamedQuery(name = "Contrat.findByEntreprise", query = "SELECT c FROM Contrat c WHERE c.entreprise = :entreprise")})
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_contrat")
    private Integer idContrat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Periode")
    private double periode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Entreprise")
    private String entreprise;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrat")
    private Collection<Fournisseur> fournisseurCollection;

    public Contrat() {
    }

    public Contrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Contrat(Integer idContrat, Date date, double periode, String entreprise) {
        this.idContrat = idContrat;
        this.date = date;
        this.periode = periode;
        this.entreprise = entreprise;
    }

    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPeriode() {
        return periode;
    }

    public void setPeriode(double periode) {
        this.periode = periode;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    @XmlTransient
    public Collection<Fournisseur> getFournisseurCollection() {
        return fournisseurCollection;
    }

    public void setFournisseurCollection(Collection<Fournisseur> fournisseurCollection) {
        this.fournisseurCollection = fournisseurCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrat != null ? idContrat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrat)) {
            return false;
        }
        Contrat other = (Contrat) object;
        if ((this.idContrat == null && other.idContrat != null) || (this.idContrat != null && !this.idContrat.equals(other.idContrat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return entreprise;
    }
    
}
