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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DHIA
 */
@Entity
@Table(name = "Periode Temps Tab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodeTempsTab.findAll", query = "SELECT p FROM PeriodeTempsTab p")
    , @NamedQuery(name = "PeriodeTempsTab.findByIdPeriodet", query = "SELECT p FROM PeriodeTempsTab p WHERE p.idPeriodet = :idPeriodet")
    , @NamedQuery(name = "PeriodeTempsTab.findByDate", query = "SELECT p FROM PeriodeTempsTab p WHERE p.date = :date")
    , @NamedQuery(name = "PeriodeTempsTab.findByTolerance", query = "SELECT p FROM PeriodeTempsTab p WHERE p.tolerance = :tolerance")
    , @NamedQuery(name = "PeriodeTempsTab.findByAlarme", query = "SELECT p FROM PeriodeTempsTab p WHERE p.alarme = :alarme")})
public class PeriodeTempsTab implements Serializable {

    @Column(name = "periodicitee")
    private Integer periodicitee;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_periodet")
    private Integer idPeriodet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tolerance")
    private int tolerance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Alarme")
    @Temporal(TemporalType.DATE)
    private Date alarme;
    @OneToMany(mappedBy = "idPeriodet")
    private Collection<Frais> fraisCollection;

    public PeriodeTempsTab() {
    }

    
    
    public PeriodeTempsTab(Integer idPeriodet) {
        this.idPeriodet = idPeriodet;
    }

    public PeriodeTempsTab(Integer idPeriodet, Date date, int tolerance, Date alarme,int peridicitee) {
        this.idPeriodet = idPeriodet;
        this.date = date;
        this.tolerance = tolerance;
        this.alarme = alarme;
        this.periodicitee = peridicitee;
    }

    
    
    public Integer getIdPeriodet() {
        return idPeriodet;
    }

    public void setIdPeriodet(Integer idPeriodet) {
        this.idPeriodet = idPeriodet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public Date getAlarme() {
        return alarme;
    }

    public void setAlarme(Date alarme) {
        this.alarme = alarme;
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
        hash += (idPeriodet != null ? idPeriodet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodeTempsTab)) {
            return false;
        }
        PeriodeTempsTab other = (PeriodeTempsTab) object;
        if ((this.idPeriodet == null && other.idPeriodet != null) || (this.idPeriodet != null && !this.idPeriodet.equals(other.idPeriodet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  String.valueOf(alarme) ;
    }

    public Integer getPeriodicitee() {
        return periodicitee;
    }

    public void setPeriodicitee(Integer periodicitee) {
        this.periodicitee = periodicitee;
    }
    
}
