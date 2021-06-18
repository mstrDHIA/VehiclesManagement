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
@Table(name = "Frais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frais.findAll", query = "SELECT f FROM Frais f")
    , @NamedQuery(name = "Frais.findByIdFrais", query = "SELECT f FROM Frais f WHERE f.idFrais = :idFrais")
    , @NamedQuery(name = "Frais.findByNom", query = "SELECT f FROM Frais f WHERE f.nom = :nom")
    , @NamedQuery(name = "Frais.findByMontant", query = "SELECT f FROM Frais f WHERE f.montant = :montant")})
public class Frais implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFrais")
    private Collection<Fraisactifes> fraisactifesCollection;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_frais")
    private Integer idFrais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Montant")
    private double montant;
    @JoinColumn(name = "id_periodekilo", referencedColumnName = "id_periodekilo")
    @ManyToOne
    private PeriodeKilometrageTab idPeriodekilo;
    @JoinColumn(name = "id_periodet", referencedColumnName = "id_periodet")
    @ManyToOne
    private PeriodeTempsTab idPeriodet;
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private TypeFrais idType;
    @JoinColumn(name = "Matricule", referencedColumnName = "Matricule")
    @ManyToOne(optional = false)
    private Voitures matricule;

    public Frais() {
    }

    public Frais(Integer idFrais) {
        this.idFrais = idFrais;
    }

    public Frais(Integer idFrais, String nom, double montant) {
        this.idFrais = idFrais;
        this.nom = nom;
        this.montant = montant;
    }

    public Integer getIdFrais() {
        return idFrais;
    }

    public void setIdFrais(Integer idFrais) {
        this.idFrais = idFrais;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public PeriodeKilometrageTab getIdPeriodekilo() {
        return idPeriodekilo;
    }

    public void setIdPeriodekilo(PeriodeKilometrageTab idPeriodekilo) {
        this.idPeriodekilo = idPeriodekilo;
    }

    public PeriodeTempsTab getIdPeriodet() {
        return idPeriodet;
    }

    public void setIdPeriodet(PeriodeTempsTab idPeriodet) {
        this.idPeriodet = idPeriodet;
    }

    public TypeFrais getIdType() {
        return idType;
    }

    public void setIdType(TypeFrais idType) {
        this.idType = idType;
    }

    public Voitures getMatricule() {
        return matricule;
    }

    public void setMatricule(Voitures matricule) {
        this.matricule = matricule;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFrais != null ? idFrais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frais)) {
            return false;
        }
        Frais other = (Frais) object;
        if ((this.idFrais == null && other.idFrais != null) || (this.idFrais != null && !this.idFrais.equals(other.idFrais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }

    @XmlTransient
    public Collection<Fraisactifes> getFraisactifesCollection() {
        return fraisactifesCollection;
    }

    public void setFraisactifesCollection(Collection<Fraisactifes> fraisactifesCollection) {
        this.fraisactifesCollection = fraisactifesCollection;
    }
    
}
