/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.PeriodeKilometrageTab;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DHIA
 */
@Stateless
public class PeriodeKilometrageTabFacade extends AbstractFacade<PeriodeKilometrageTab> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodeKilometrageTabFacade() {
        super(PeriodeKilometrageTab.class);
    }
    
    public float bringkilo(int id){
                Query q = em.createNativeQuery("Select kilo_trigger From [periode kilometrage tab] where id_periodekilo=?");
                q.setParameter(1, id);
                return Float.parseFloat(q.getSingleResult().toString());

    }
    public float bringtolerance(int id){
                Query q = em.createNativeQuery("Select tolerance From [periode kilometrage tab] where id_periodekilo=?");
                q.setParameter(1, id);
                return Float.parseFloat(q.getSingleResult().toString());

    }
    
    public float changealarme(int id){
                float kilo=bringkilo(id);
                float tolerance=bringtolerance(id);
                kilo=kilo-tolerance;
                Query q = em.createNativeQuery("update [periode kilometrage tab] set alarme=? where id_periodekilo=?");
                q.setParameter(1, kilo);
                q.setParameter(2, id);
                q.executeUpdate();
                Query p = em.createNativeQuery("Select alarme from [periode kilometrage tab] where id_periodekilo=?");
                p.setParameter(1, id);
                return Float.parseFloat(p.getSingleResult().toString());
                

    }
    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_periodekilo) From [periode kilometrage tab]");

        return (Integer) q.getSingleResult();
    }
}
