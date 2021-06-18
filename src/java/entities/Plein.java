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
@Table(name = "Plein")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plein.findAll", query = "SELECT p FROM Plein p")
    , @NamedQuery(name = "Plein.findByIdPlein", query = "SELECT p FROM Plein p WHERE p.idPlein = :idPlein")
    , @NamedQuery(name = "Plein.findByDateplein", query = "SELECT p FROM Plein p WHERE p.dateplein = :dateplein")
    , @NamedQuery(name = "Plein.findByKiloActuel", query = "SELECT p FROM Plein p WHERE p.kiloActuel = :kiloActuel")
    , @NamedQuery(name = "Plein.findByLitres", query = "SELECT p FROM Plein p WHERE p.litres = :litres")
    , @NamedQuery(name = "Plein.findByPrixlitre", query = "SELECT p FROM Plein p WHERE p.prixlitre = :prixlitre")
    , @NamedQuery(name = "Plein.findByPrixplein", query = "SELECT p FROM Plein p WHERE p.prixplein = :prixplein")
    , @NamedQuery(name = "Plein.findByConsommation", query = "SELECT p FROM Plein p WHERE p.consommation = :consommation")
    , @NamedQuery(name = "Plein.findByDistance", query = "SELECT p FROM Plein p WHERE p.distance = :distance")
    , @NamedQuery(name = "Plein.findByDistancederniere", query = "SELECT p FROM Plein p WHERE p.distancederniere = :distancederniere")
    , @NamedQuery(name = "Plein.findByCoutkilo", query = "SELECT p FROM Plein p WHERE p.coutkilo = :coutkilo")
    , @NamedQuery(name = "Plein.findByResdernier", query = "SELECT p FROM Plein p WHERE p.resdernier = :resdernier")
    , @NamedQuery(name = "Plein.findByResactuel", query = "SELECT p FROM Plein p WHERE p.resactuel = :resactuel")})
public class Plein implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_plein")
    private Integer idPlein;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_plein")
    @Temporal(TemporalType.DATE)
    private Date dateplein;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kilo_Actuel")
    private double kiloActuel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Litres")
    private double litres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Prix_litre")
    private double prixlitre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Prix_plein")
    private double prixplein;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Consommation")
    private double consommation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Distance")
    private double distance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Distance_derniere")
    private double distancederniere;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cout_kilo")
    private double coutkilo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Res_dernier")
    private Double resdernier;
    @Column(name = "Res_actuel")
    private Double resactuel;
    @JoinColumn(name = "Matricule", referencedColumnName = "Matricule")
    @ManyToOne(optional = false)
    private Voitures matricule;

    public Plein() {
    }

    public Plein(Integer idPlein) {
        this.idPlein = idPlein;
    }

    public Plein(Integer idPlein, Date dateplein, double kiloActuel, double litres, double prixlitre, double prixplein, double consommation, double distance, double distancederniere, double coutkilo) {
        this.idPlein = idPlein;
        this.dateplein = dateplein;
        this.kiloActuel = kiloActuel;
        this.litres = litres;
        this.prixlitre = prixlitre;
        this.prixplein = prixplein;
        this.consommation = consommation;
        this.distance = distance;
        this.distancederniere = distancederniere;
        this.coutkilo = coutkilo;
    }

    public Integer getIdPlein() {
        return idPlein;
    }

    public void setIdPlein(Integer idPlein) {
        this.idPlein = idPlein;
    }

    public Date getDateplein() {
        return dateplein;
    }

    public void setDateplein(Date dateplein) {
        this.dateplein = dateplein;
    }

    public double getKiloActuel() {
        return kiloActuel;
    }

    public void setKiloActuel(double kiloActuel) {
        this.kiloActuel = kiloActuel;
    }

    public double getLitres() {
        return litres;
    }

    public void setLitres(double litres) {
        this.litres = litres;
    }

    public double getPrixlitre() {
        return prixlitre;
    }

    public void setPrixlitre(double prixlitre) {
        this.prixlitre = prixlitre;
    }

    public double getPrixplein() {
        return prixplein;
    }

    public void setPrixplein(double prixplein) {
        this.prixplein = prixplein;
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistancederniere() {
        return distancederniere;
    }

    public void setDistancederniere(double distancederniere) {
        this.distancederniere = distancederniere;
    }

    public double getCoutkilo() {
        return coutkilo;
    }

    public void setCoutkilo(double coutkilo) {
        this.coutkilo = coutkilo;
    }

    public Double getResdernier() {
        return resdernier;
    }

    public void setResdernier(Double resdernier) {
        this.resdernier = resdernier;
    }

    public Double getResactuel() {
        return resactuel;
    }

    public void setResactuel(Double resactuel) {
        this.resactuel = resactuel;
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
        hash += (idPlein != null ? idPlein.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plein)) {
            return false;
        }
        Plein other = (Plein) object;
        if ((this.idPlein == null && other.idPlein != null) || (this.idPlein != null && !this.idPlein.equals(other.idPlein))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Plein[ idPlein=" + idPlein + " ]";
    }
    
}
