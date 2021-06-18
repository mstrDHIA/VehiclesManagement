/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import entities.Frais;
import java.sql.Date;
import java.time.LocalDate;
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
public class FraisFacade extends AbstractFacade<Frais> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FraisFacade() {
        super(Frais.class);
    }
    
    
    
    
    
    
    
        public float getdist(String mat){
        Query q=em.createNativeQuery("Select top(1) distance from plein where matricule=? order by id_plein desc");
        q.setParameter(1, mat);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString()) ;
    }
    public float getdist_dern(String mat){
        Query q=em.createNativeQuery("Select top(1) distance_derniere from plein where matricule=? order by id_plein desc");
        q.setParameter(1, mat);
         System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString()) ;
    }
    
    
   /*public String getmat(int idActif){
         Query q=em.createNativeQuery("Select matricule from frais f , frais_actifes a where  (a.id_frais=f.id_frais) and a.id_frais=?");
        q.setParameter(1, idActif);
         System.out.println((q.getSingleResult().toString()));
        return(q.getSingleResult().toString()) ;
    }*/
    
    public float getalarmekilo(int idfrais){
         Query q=em.createNativeQuery("Select alarme from  [Periode Kilometrage Tab] p ,frais f  where  (p.id_periodekilo=f.id_periodekilo) and f.id_frais=?");
        q.setParameter(1, idfrais);
         System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString()) ;
    }
    
    
    public Date bringalarmet(int id){
        Query q=em.createNativeQuery("Select date from [periode temps tab] p  where id_periodet=?");
        q.setParameter(1, id);
        Date d=(Date) q.getSingleResult();
               return d;
    }
    
    
    
    public float bringKilo(String mat){
        Query q=em.createNativeQuery("Select Top(1) kilo_Actuel from frais f,plein p where p.matricule=? order by f.id_frais DESC");
        return Float.parseFloat(q.setParameter(1, mat).getSingleResult().toString());
           
    }
    
    public int bringMaxId(){
        Query q=em.createNativeQuery("Select max(id_actif) from frais_actifes");
        return Integer.parseInt(q.getSingleResult().toString());
    }
    
    
    public void moin(){
         Query q=em.createNativeQuery("Insert into [Type Carburant] ( type_carb) Values(?)");
        //Query p=em.createNativeQuery("Select date_actif where id_frais = ?");
        
        q.setParameter(1, "huile");
        q.executeUpdate();
    }
    
    public void plus (int id_frais){
        Query q=em.createNativeQuery("Insert into frais_actifes ( validation,date_actif,id_frais) Values(?,?,?)");
        //Query p=em.createNativeQuery("Select date_actif where id_frais = ?");
        //q.setParameter(1, bringMaxId()+1);
        
        
        q.setParameter(1,  0);
        /*LocalDate d;
        d= LocalDate.of( 2026 , 1 , 23 );
        //d="20/03/2020";
        
        q.setParameter(2, d);
        
        //p.setParameter(1, id_frais);*/
        q.setParameter(2, "02/02/2020");
        q.setParameter(3, id_frais);
        q.executeUpdate();
        System.out.println("success");
    }
    
    
     public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_frais) From frais");

        return (Integer) q.getSingleResult();
    }
    
    
    public List all(){
        Query q = em.createNativeQuery("select * from frais_actifes");
        return q.getResultList();
    }
    
    
  
    
}
