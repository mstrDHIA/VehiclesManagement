/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.TypeVoiture;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DHIA
 */
@Stateless
public class TypeVoitureFacade extends AbstractFacade<TypeVoiture> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_typev) From [type voiture]");

        return (Integer) q.getSingleResult();
    }
    
    public TypeVoitureFacade() {
        super(TypeVoiture.class);
    }
    
}
