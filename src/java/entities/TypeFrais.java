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
@Table(name = "Type Frais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeFrais.findAll", query = "SELECT t FROM TypeFrais t")
    , @NamedQuery(name = "TypeFrais.findByIdType", query = "SELECT t FROM TypeFrais t WHERE t.idType = :idType")
    , @NamedQuery(name = "TypeFrais.findByType", query = "SELECT t FROM TypeFrais t WHERE t.type = :type")})
public class TypeFrais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_type")
    private Integer idType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idType")
    private Collection<Frais> fraisCollection;

    public TypeFrais() {
    }

    public TypeFrais(Integer idType) {
        this.idType = idType;
    }

    public TypeFrais(Integer idType, String type) {
        this.idType = idType;
        this.type = type;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Frais> getFraisCollection() {
        return fraisCollection;
    }

    public void setFraisCollection(Collection<Frais> fraisCollection) {
        this.fraisCollection = fraisCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeFrais)) {
            return false;
        }
        TypeFrais other = (TypeFrais) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return type ;
    }
    
}
