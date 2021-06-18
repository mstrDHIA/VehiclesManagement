/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;


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
public class statsFacade {
    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
public List<String> bringvoitures(){
    Query q=em.createNativeQuery("Select matricule from voitures");
    return q.getResultList();
}
public float bringtotal(String mat){
    Query q=em.createNativeQuery("Select total from depences where matricule=?");
    q.setParameter(1, mat);
    return Float.parseFloat(mat);
}
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
