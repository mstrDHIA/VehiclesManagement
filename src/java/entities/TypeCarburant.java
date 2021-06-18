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
@Table(name = "Type Carburant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeCarburant.findAll", query = "SELECT t FROM TypeCarburant t")
    , @NamedQuery(name = "TypeCarburant.findByIdCarburant", query = "SELECT t FROM TypeCarburant t WHERE t.idCarburant = :idCarburant")
    , @NamedQuery(name = "TypeCarburant.findByTypecarb", query = "SELECT t FROM TypeCarburant t WHERE t.typecarb = :typecarb")})
public class TypeCarburant implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarburant")
    private Collection<Voitures> voituresCollection;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_carburant")
    private Integer idCarburant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Type_carb")
    private String typecarb;

    public TypeCarburant() {
    }

    public TypeCarburant(Integer idCarburant) {
        this.idCarburant = idCarburant;
    }

    public TypeCarburant(Integer idCarburant, String typecarb) {
        this.idCarburant = idCarburant;
        this.typecarb = typecarb;
    }

    public Integer getIdCarburant() {
        return idCarburant;
    }

    public void setIdCarburant(Integer idCarburant) {
        this.idCarburant = idCarburant;
    }

    public String getTypecarb() {
        return typecarb;
    }

    public void setTypecarb(String typecarb) {
        this.typecarb = typecarb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarburant != null ? idCarburant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeCarburant)) {
            return false;
        }
        TypeCarburant other = (TypeCarburant) object;
        if ((this.idCarburant == null && other.idCarburant != null) || (this.idCarburant != null && !this.idCarburant.equals(other.idCarburant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return typecarb ;
    }

    @XmlTransient
    public Collection<Voitures> getVoituresCollection() {
        return voituresCollection;
    }

    public void setVoituresCollection(Collection<Voitures> voituresCollection) {
        this.voituresCollection = voituresCollection;
    }
    
}
