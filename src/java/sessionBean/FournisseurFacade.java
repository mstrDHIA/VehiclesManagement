/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.Fournisseur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DHIA
 */
@Stateless
public class FournisseurFacade extends AbstractFacade<Fournisseur> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FournisseurFacade() {
        super(Fournisseur.class);
    }
    
    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_fournisseur) From fournisseur");

        return (Integer) q.getSingleResult();
    }
    
    public String bringEntreprise(int id_four){
        Query q=em.createNativeQuery("SELECT Entreprise FROM Contrat WHERE id_contrat=?");
        q.setParameter(1, id_four);
        return q.getSingleResult().toString();
    }
    
}
