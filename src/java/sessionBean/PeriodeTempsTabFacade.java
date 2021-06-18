/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.PeriodeTempsTab;
import java.sql.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DHIA
 */
@Stateless
public class PeriodeTempsTabFacade extends AbstractFacade<PeriodeTempsTab> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodeTempsTabFacade() {
        super(PeriodeTempsTab.class);
    }

    public Date bringdate(int pt) {
        Query q = em.createNativeQuery("Select date from [Periode Temps Tab] where id_periodet=?");
        q.setParameter(1, pt);
        return Date.valueOf(q.getSingleResult().toString());

    }

    public Date bringalerme(int pt) {
        Query q = em.createNativeQuery("Select alarme from [Periode Temps Tab] where id_periodet=?");
        q.setParameter(1, pt);
        return Date.valueOf(q.getSingleResult().toString());

    }
    
    
     public void updatealarme(int pt,Date d) {
         Query q = em.createNativeQuery("Update [Periode Temps Tab] set alarme=? where id_periodet=?");
         q.setParameter(1, d);
         q.setParameter(2, pt);
         q.executeUpdate();

    }
     public int bringtolerance(int pt) {
        Query q = em.createNativeQuery("Select tolerance from [Periode Temps Tab] where id_periodet=?");
        q.setParameter(1, pt);
        return (Integer)q.getSingleResult();

    }
    
    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_periodet) From [periode temps tab]");

        return (Integer) q.getSingleResult();
    }

}
