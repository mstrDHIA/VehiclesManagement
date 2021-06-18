/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.TypeFrais;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DHIA
 */
@Stateless
public class TypeFraisFacade extends AbstractFacade<TypeFrais> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_type) From [type frais]");

        return (Integer) q.getSingleResult();
    }

    public TypeFraisFacade() {
        super(TypeFrais.class);
    }
    
}
