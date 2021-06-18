/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.Depences;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DHIA
 */
@Stateless
public class DepencesFacade extends AbstractFacade<Depences> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    
    
    public List<String> getmats(){
       Query q = em.createNativeQuery("SELECT matricule From depences  ");
       return q.getResultList();
    }
    
    @Override

    
    
    
    
    
    
    protected EntityManager getEntityManager() {
        return em;
    }

    
     public float calcul_total(String mat) {
        float total = 0;
        Query total_plein = em.createNativeQuery("SELECT prix_plein From plein where matricule=? ");
        total_plein.setParameter(1, mat);
        
        int l = total_plein.getResultList().toArray().length;
        if(l>0){
        for (int i = 0; i < l; i++) {
            total += Float.parseFloat(total_plein.getResultList().toArray()[i].toString());
        }
        }
        Query total_frais = em.createNativeQuery("Select montant from Frais f , Frais_actifes fa where f.id_frais=fa.id_frais and f.Matricule=? and fa.validation=1;");
        total_frais.setParameter(1, mat);
        l = total_frais.getResultList().toArray().length;
        if(l>0)
        for (int j = 0; j < l; j++) {
            total += Float.parseFloat(total_frais.getResultList().toArray()[j].toString());
        }
        
        Query q = em.createNativeQuery("Update depences set total= ? where matricule=? ");
        q.setParameter(1, total);
        q.setParameter(2, mat);
        q.executeUpdate();
        return total;
}

    
    
    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_depences) From depences");

        return (Integer) q.getSingleResult();
    }
    
    
    public DepencesFacade() {
        super(Depences.class);
    }

}
