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
@Table(name = "Type Voiture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeVoiture.findAll", query = "SELECT t FROM TypeVoiture t")
    , @NamedQuery(name = "TypeVoiture.findByIdTypev", query = "SELECT t FROM TypeVoiture t WHERE t.idTypev = :idTypev")
    , @NamedQuery(name = "TypeVoiture.findByType", query = "SELECT t FROM TypeVoiture t WHERE t.type = :type")})
public class TypeVoiture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_typev")
    private Integer idTypev;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypev")
    private Collection<Voitures> voituresCollection;

    public TypeVoiture() {
    }

    public TypeVoiture(Integer idTypev) {
        this.idTypev = idTypev;
    }

    public TypeVoiture(Integer idTypev, String type) {
        this.idTypev = idTypev;
        this.type = type;
    }

    public Integer getIdTypev() {
        return idTypev;
    }

    public void setIdTypev(Integer idTypev) {
        this.idTypev = idTypev;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (idTypev != null ? idTypev.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeVoiture)) {
            return false;
        }
        TypeVoiture other = (TypeVoiture) object;
        if ((this.idTypev == null && other.idTypev != null) || (this.idTypev != null && !this.idTypev.equals(other.idTypev))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return type;
    }
    
}
