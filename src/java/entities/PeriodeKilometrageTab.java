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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DHIA
 */
@Entity
@Table(name = "Periode Kilometrage Tab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodeKilometrageTab.findAll", query = "SELECT p FROM PeriodeKilometrageTab p")
    , @NamedQuery(name = "PeriodeKilometrageTab.findByIdPeriodekilo", query = "SELECT p FROM PeriodeKilometrageTab p WHERE p.idPeriodekilo = :idPeriodekilo")
    , @NamedQuery(name = "PeriodeKilometrageTab.findByKilotrigger", query = "SELECT p FROM PeriodeKilometrageTab p WHERE p.kilotrigger = :kilotrigger")
    , @NamedQuery(name = "PeriodeKilometrageTab.findByKilodebut", query = "SELECT p FROM PeriodeKilometrageTab p WHERE p.kilodebut = :kilodebut")
    , @NamedQuery(name = "PeriodeKilometrageTab.findByTolerance", query = "SELECT p FROM PeriodeKilometrageTab p WHERE p.tolerance = :tolerance")
    , @NamedQuery(name = "PeriodeKilometrageTab.findByAlarme", query = "SELECT p FROM PeriodeKilometrageTab p WHERE p.alarme = :alarme")})
public class PeriodeKilometrageTab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_periodekilo")
    private Integer idPeriodekilo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kilo_trigger")
    private double kilotrigger;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kilo_debut")
    private double kilodebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tolerance")
    private int tolerance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Alarme")
    private double alarme;
    @OneToMany(mappedBy = "idPeriodekilo")
    private Collection<Frais> fraisCollection;

    public PeriodeKilometrageTab() {
    }

    public PeriodeKilometrageTab(Integer idPeriodekilo) {
        this.idPeriodekilo = idPeriodekilo;
    }

    public PeriodeKilometrageTab(Integer idPeriodekilo, double kilotrigger, double kilodebut, int tolerance, double alarme) {
        this.idPeriodekilo = idPeriodekilo;
        this.kilotrigger = kilotrigger;
        this.kilodebut = kilodebut;
        this.tolerance = tolerance;
        this.alarme = alarme;
    }

    public Integer getIdPeriodekilo() {
        return idPeriodekilo;
    }

    public void setIdPeriodekilo(Integer idPeriodekilo) {
        this.idPeriodekilo = idPeriodekilo;
    }

    public double getKilotrigger() {
        return kilotrigger;
    }

    public void setKilotrigger(double kilotrigger) {
        this.kilotrigger = kilotrigger;
    }

    public double getKilodebut() {
        return kilodebut;
    }

    public void setKilodebut(double kilodebut) {
        this.kilodebut = kilodebut;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public double getAlarme() {
        return alarme;
    }

    public void setAlarme(double alarme) {
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
        hash += (idPeriodekilo != null ? idPeriodekilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodeKilometrageTab)) {
            return false;
        }
        PeriodeKilometrageTab other = (PeriodeKilometrageTab) object;
        if ((this.idPeriodekilo == null && other.idPeriodekilo != null) || (this.idPeriodekilo != null && !this.idPeriodekilo.equals(other.idPeriodekilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(alarme);
    }
    
}
