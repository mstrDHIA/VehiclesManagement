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
import javax.persistence.Lob;
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
@Table(name = "Site")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Site.findAll", query = "SELECT s FROM Site s")
    , @NamedQuery(name = "Site.findByIdSite", query = "SELECT s FROM Site s WHERE s.idSite = :idSite")
    , @NamedQuery(name = "Site.findByNom", query = "SELECT s FROM Site s WHERE s.nom = :nom")
    , @NamedQuery(name = "Site.findByGovernerat", query = "SELECT s FROM Site s WHERE s.governerat = :governerat")})
public class Site implements Serializable {

    @Lob
    @Column(name = "Localisation")
    private byte[] localisation;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_site")
    private Integer idSite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Governerat")
    private String governerat;
    @OneToMany(mappedBy = "idSite")
    private Collection<Voitures> voituresCollection;

    public Site() {
    }

    public Site(Integer idSite) {
        this.idSite = idSite;
    }

    public Site(Integer idSite, String nom, String governerat) {
        this.idSite = idSite;
        this.nom = nom;
        this.governerat = governerat;
    }

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getGovernerat() {
        return governerat;
    }

    public void setGovernerat(String governerat) {
        this.governerat = governerat;
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
        hash += (idSite != null ? idSite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Site)) {
            return false;
        }
        Site other = (Site) object;
        if ((this.idSite == null && other.idSite != null) || (this.idSite != null && !this.idSite.equals(other.idSite))) {
            return false;
        }
        return true;
    }
   

    @Override
    
    public String toString() {
        return nom;
    }

    
    public byte[] getLocalisation() {
        return localisation;
    }

    public void setLocalisation(byte[] localisation) {
        this.localisation = localisation;
    }
    
}
