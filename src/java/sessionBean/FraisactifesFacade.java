/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.Fraisactifes;
import static java.util.Collections.list;
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
public class FraisactifesFacade extends AbstractFacade<Fraisactifes> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FraisactifesFacade() {
        super(Fraisactifes.class);
    }

    public float getdist(String mat) {
        Query q = em.createNativeQuery("Select top(1) distance from plein where matricule=? order by id_plein desc");
        q.setParameter(1, mat);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString());
    }
    
    
    
    public String bringmat(int id){
        Query q = em.createNativeQuery("Select matricule from frais f,frais_actifes fa where id_actif=? and f.id_frais=fa.id_frais");
        q.setParameter(1, id);
        
        return q.getSingleResult().toString();
    }

    public float getdist_dern(String mat) {
        Query q = em.createNativeQuery("Select top(1) distance_derniere from plein where matricule=? order by id_plein desc");
        q.setParameter(1, mat);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString());
    }

    public String getmat(int idActif) {
        Query q = em.createNativeQuery("Select matricule from frais f , frais_actifes a where  (a.id_frais=f.id_frais) and a.id_actif=?");
        q.setParameter(1, idActif);
        System.out.println((q.getSingleResult().toString()));
        return (q.getSingleResult().toString());
    }

    public float getalarmekilo(int idActif) {
        Query q = em.createNativeQuery("Select alarme from [Periode Kilometrage Tab] p ,frais f ,frais_actifes a where  (p.id_periodekilo=f.id_periodekilo) and(a.id_frais=f.id_frais) and a.id_actif=?");
        q.setParameter(1, idActif);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString());
    }

    public List<Integer> lkol() {
        Query q = em.createNativeQuery("Select id_actif from frais_actifes ");

        //System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return q.getResultList();
    }
    
    
    public float bringmontant(int id){
        Query q = em.createNativeQuery("Select montant From frais_actifes fa,frais f where f.id_frais=fa.id_frais and fa.id_actif=?");
        q.setParameter(1, id);
        return Float.parseFloat(q.getSingleResult().toString());
    }
    

    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_actif) From frais_actifes");

        return (Integer) q.getSingleResult();
    }
}
