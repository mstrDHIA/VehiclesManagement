/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.Voitures;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.Null;

/**
 *
 * @author DHIA
 */
@Stateless
public class VoituresFacade extends AbstractFacade<Voitures> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public String getkilonew(String mat) {
        Query q = em.createNativeQuery("Select kilometrage from voitures where matricule = ?");
        q.setParameter(1, mat);
        return q.getSingleResult().toString();
    }

    public VoituresFacade() {
        super(Voitures.class);
    }

    public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_depences) From depences");

        return (Integer) q.getSingleResult();
    }

    public Integer getFournisseur(String mat) {

        Query q = em.createNativeQuery("Select id_fournisseur From fournisseur f , voitures v where v.matricule=? and (f.id_fournisseur=v.id_fournisseur) ");
       Integer id =null ;

        q.setParameter(1, mat);
        boolean ok;
        ok = q.getSingleResult().toString().isEmpty();
        if (ok) {
             id =(Integer) q.getSingleResult();
        }

        return  id;

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
        return total;
}
    public void createdepences(float total,String mat){
        Query q = em.createNativeQuery("Insert into depences (matricule,total) Values(?,?)");
        //q.setParameter(1, id);
        q.setParameter(1, mat);
    
        q.setParameter(2, total);
        
        q.executeUpdate();
        

    }
}
