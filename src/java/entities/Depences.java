/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DHIA
 */
@Entity
@Table(name = "Depences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depences.findAll", query = "SELECT d FROM Depences d")
    , @NamedQuery(name = "Depences.findByIdDepences", query = "SELECT d FROM Depences d WHERE d.idDepences = :idDepences")
    , @NamedQuery(name = "Depences.findByMatricule", query = "SELECT d FROM Depences d WHERE d.matricule = :matricule")
    , @NamedQuery(name = "Depences.findByTotal", query = "SELECT d FROM Depences d WHERE d.total = :total")})
public class Depences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_depences")
    private Integer idDepences;
   /* @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Matricule")
    private String matricule;*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "Total")
    private double total;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepences")
    private Collection<Voitures> voituresCollection;*/
    @JoinColumn(name = "id_fournisseur", referencedColumnName = "id_fournisseur")
    @ManyToOne(optional = false)
    private Fournisseur idFournisseur;
    @JoinColumn(name = "matricule", referencedColumnName = "matricule")
    @ManyToOne
    private Voitures matricule;

    public Depences() {
    }

    public Depences(Integer idDepences) {
        this.idDepences = idDepences;
    }

    public Depences(Integer idDepences, String matricule, double total) {
        this.idDepences = idDepences;
       // this.matricule = matricule;
        this.total = total;
    }

    public Integer getIdDepences() {
        return idDepences;
    }

    public void setIdDepences(Integer idDepences) {
        this.idDepences = idDepences;
    }

    public Voitures getMatricule() {
        return matricule;
    }

    public void setMatricule(Voitures matricule) {
        this.matricule = matricule;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @XmlTransient
    /*public Collection<Voitures> getVoituresCollection() {
        return voituresCollection;
    }*/

  /*  public void setVoituresCollection(Collection<Voitures> voituresCollection) {
        this.voituresCollection = voituresCollection;
    }*/

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepences != null ? idDepences.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depences)) {
            return false;
        }
        Depences other = (Depences) object;
        if ((this.idDepences == null && other.idDepences != null) || (this.idDepences != null && !this.idDepences.equals(other.idDepences))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + total + "";
    }
    
}
