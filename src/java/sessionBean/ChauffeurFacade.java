/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.Chauffeur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DHIA
 */
@Stateless
public class ChauffeurFacade extends AbstractFacade<Chauffeur> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChauffeurFacade() {
        super(Chauffeur.class);
    }
    
}
