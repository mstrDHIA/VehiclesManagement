/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DHIA
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_user) From utilisateur");

        return (Integer) q.getSingleResult();
    }
    
    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    
    public String bringgroup(int id){
        Query q = em.createNativeQuery("SELECT g.type FROM utilisateur u , [group] g  WHERE g.id_group=u.id_group and u.id_user=? ");
        q.setParameter(1, id);
        return q.getSingleResult().toString();
    } 
    
    public int group(String user,String pass){
        Query q = em.createNativeQuery("SELECT u.id_group FROM utilisateur u  WHERE u.nom=? and u.mdp=? ");
            q.setParameter(1, user);
            q.setParameter(2, pass);
            return Integer.parseInt(q.getSingleResult().toString());
    }
    
    public String verif(String user,String pass){
            Query q = em.createNativeQuery("SELECT nom,mdp FROM LED_Consommations.dbo.Utilisateur WHERE nom=? and mdp=? ");
            q.setParameter(1, user);
            q.setParameter(2, pass);
            System.out.println(q.getResultList().toString().length());
             System.out.println(q.getResultList().toString());
            return q.getResultList().toString();
            
    }
}
