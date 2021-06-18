/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Chauffeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chauffeur.findAll", query = "SELECT c FROM Chauffeur c")
    , @NamedQuery(name = "Chauffeur.findByCin", query = "SELECT c FROM Chauffeur c WHERE c.cin = :cin")
    , @NamedQuery(name = "Chauffeur.findByNom", query = "SELECT c FROM Chauffeur c WHERE c.nom = :nom")
    , @NamedQuery(name = "Chauffeur.findByPrenom", query = "SELECT c FROM Chauffeur c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Chauffeur.findByAge", query = "SELECT c FROM Chauffeur c WHERE c.age = :age")})
public class Chauffeur implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id  
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIN")
    private Double cin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Age")
    private int age;
    @OneToMany(mappedBy = "cin")
    private Collection<Voitures> voituresCollection;

    public Chauffeur() {
    }

    public Chauffeur(Double cin) {
        this.cin = cin;
    }

    public Chauffeur(Double cin, String nom, String prenom, int age) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    
    
    
    public Double getCin() {
        return cin;
    }

    public void setCin(Double cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlTransient
    public Collection<Voitures> getVoituresCollection() {
        return voituresCollection;
    }

    public void setVoituresCollection(Collection<Voitures> voituresCollection) {
        this.voituresCollection = voituresCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cin != null ? cin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chauffeur)) {
            return false;
        }
        Chauffeur other = (Chauffeur) object;
        if ((this.cin == null && other.cin != null) || (this.cin != null && !this.cin.equals(other.cin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom +" "+ prenom;
    }
    
}
