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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Voitures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voitures.findAll", query = "SELECT v FROM Voitures v")
    , @NamedQuery(name = "Voitures.findByMatricule", query = "SELECT v FROM Voitures v WHERE v.matricule = :matricule")
    , @NamedQuery(name = "Voitures.findByNom", query = "SELECT v FROM Voitures v WHERE v.nom = :nom")
    , @NamedQuery(name = "Voitures.findByKilometrage", query = "SELECT v FROM Voitures v WHERE v.kilometrage = :kilometrage")
    , @NamedQuery(name = "Voitures.findByKilodebut", query = "SELECT v FROM Voitures v WHERE v.kilodebut = :kilodebut")
    , @NamedQuery(name = "Voitures.findByVolres", query = "SELECT v FROM Voitures v WHERE v.volres = :volres")
    , @NamedQuery(name = "Voitures.findByConsomin", query = "SELECT v FROM Voitures v WHERE v.consomin = :consomin")
    , @NamedQuery(name = "Voitures.findByConsomax", query = "SELECT v FROM Voitures v WHERE v.consomax = :consomax")
    , @NamedQuery(name = "Voitures.findByPrixpleindepart", query = "SELECT v FROM Voitures v WHERE v.prixpleindepart = :prixpleindepart")
    , @NamedQuery(name = "Voitures.findByDatedebut", query = "SELECT v FROM Voitures v WHERE v.datedebut = :datedebut")
    , @NamedQuery(name = "Voitures.findByDateachat", query = "SELECT v FROM Voitures v WHERE v.dateachat = :dateachat")
    , @NamedQuery(name = "Voitures.findByPrix", query = "SELECT v FROM Voitures v WHERE v.prix = :prix")
    , @NamedQuery(name = "Voitures.findByIdCarburant", query = "SELECT v FROM Voitures v WHERE v.idCarburant = :idCarburant")
    , @NamedQuery(name = "Voitures.findByPuissance", query = "SELECT v FROM Voitures v WHERE v.puissance = :puissance")
    , @NamedQuery(name = "Voitures.findByNbPlace", query = "SELECT v FROM Voitures v WHERE v.nbPlace = :nbPlace")
    , @NamedQuery(name = "Voitures.findByMarque", query = "SELECT v FROM Voitures v WHERE v.marque = :marque")})
public class Voitures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id 
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Matricule")
    private String matricule;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kilometrage")
    private double kilometrage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kilo_debut")
    private double kilodebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Vol_res")
    private double volres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Conso_min")
    private double consomin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Conso_max")
    private double consomax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Prix_plein_depart")
    private double prixpleindepart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_debut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;
    @Column(name = "Date_achat")
    @Temporal(TemporalType.DATE)
    private Date dateachat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Prix")
    private double prix;
    /*@Basic(optional = false)
    @NotNull
    @Column(name = "id_carburant")
    private int idCarburant;*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "Puissance")
    private int puissance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_place")
    private int nbPlace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Marque")
    private String marque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricule")
    private Collection<Frais> fraisCollection;
    @JoinColumn(name = "CIN", referencedColumnName = "CIN")
    @ManyToOne
    private Chauffeur cin;
    /*@JoinColumn(name = "id_depences", referencedColumnName = "id_depences")
    @ManyToOne(optional = false)
    private Depences idDepences;*/
    @JoinColumn(name = "id_site", referencedColumnName = "id_site")
    @ManyToOne
    private Site idSite;
    @JoinColumn(name = "id_typev", referencedColumnName = "id_typev")
    @ManyToOne(optional = false)
    private TypeVoiture idTypev;
     @JoinColumn(name = "id_carburant", referencedColumnName = "id_carburant")
    @ManyToOne(optional = false)
    private TypeCarburant idCarburant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricule")
    private Collection<Plein> pleinCollection;

    public Voitures() {
    }

    public Voitures(String matricule) {
        this.matricule = matricule;
    }

    public Voitures(String matricule, String nom, double kilometrage, double kilodebut, double volres, double consomin, double consomax, double prixpleindepart, Date datedebut, double prix , int puissance, int nbPlace, String marque) {
        this.matricule = matricule;
        this.nom = nom;
        this.kilometrage = kilometrage;
        this.kilodebut = kilodebut;
        this.volres = volres;
        this.consomin = consomin;
        this.consomax = consomax;
        this.prixpleindepart = prixpleindepart;
        this.datedebut = datedebut;
        this.prix = prix;
        //this.idCarburant = idCarburant;
        this.puissance = puissance;
        this.nbPlace = nbPlace;
        this.marque = marque;
    }

    public TypeCarburant getIdCarburant() {
        return idCarburant;
    }

    public void setIdCarburant(TypeCarburant idCarburant) {
        this.idCarburant = idCarburant;
    }

    
    
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public double getKilodebut() {
        return kilodebut;
    }

    public void setKilodebut(double kilodebut) {
        this.kilodebut = kilodebut;
    }

    public double getVolres() {
        return volres;
    }

    public void setVolres(double volres) {
        this.volres = volres;
    }

    public double getConsomin() {
        return consomin;
    }

    public void setConsomin(double consomin) {
        this.consomin = consomin;
    }

    public double getConsomax() {
        return consomax;
    }

    public void setConsomax(double consomax) {
        this.consomax = consomax;
    }

    public double getPrixpleindepart() {
        return prixpleindepart;
    }

    public void setPrixpleindepart(double prixpleindepart) {
        this.prixpleindepart = prixpleindepart;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDateachat() {
        return dateachat;
    }

    public void setDateachat(Date dateachat) {
        this.dateachat = dateachat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    /*public TypeCarburant getIdCarburant() {
        return idCarburant;
    }

    public void setIdCarburant(int idCarburant) {
        this.idCarburant = idCarburant;
    }*/

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @XmlTransient
    public Collection<Frais> getFraisCollection() {
        return fraisCollection;
    }

    public void setFraisCollection(Collection<Frais> fraisCollection) {
        this.fraisCollection = fraisCollection;
    }

    public Chauffeur getCin() {
        return cin;
    }

    public void setCin(Chauffeur cin) {
        this.cin = cin;
    }

    /*public Depences getIdDepences() {
        return idDepences;
    }

    public void setIdDepences(Depences idDepences) {
        this.idDepences = idDepences;
    }*/

    public Site getIdSite() {
        return idSite;
    }

    public void setIdSite(Site idSite) {
        this.idSite = idSite;
    }

    public TypeVoiture getIdTypev() {
        return idTypev;
    }

    public void setIdTypev(TypeVoiture idTypev) {
        this.idTypev = idTypev;
    }

    @XmlTransient
    public Collection<Plein> getPleinCollection() {
        return pleinCollection;
    }

    public void setPleinCollection(Collection<Plein> pleinCollection) {
        this.pleinCollection = pleinCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voitures)) {
            return false;
        }
        Voitures other = (Voitures) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  matricule ;
    }
    
}
