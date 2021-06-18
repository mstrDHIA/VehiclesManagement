/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBean;

import java.sql.Date;
import entities.Plein;
import java.time.LocalDate;
import java.time.Period;
//import java.util.Date;
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
public class PleinFacade extends AbstractFacade<Plein> {

    @PersistenceContext(unitName = "LEDC_NewVersionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public float getdist(String mat) {
        float dist=0;
        Query q = em.createNativeQuery("Select top(1) distance from plein where matricule=? order by id_plein desc");
        q.setParameter(1, mat);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        if(!q.getResultList().isEmpty()){
            dist=Float.parseFloat(q.getSingleResult().toString());
        }
        return dist;
    }

    public float getdist_dern(String mat) {
        float dist=0;
        Query q = em.createNativeQuery("Select top(1) distance_derniere from plein where matricule=? order by id_plein desc");
        q.setParameter(1, mat);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
         if(!q.getResultList().isEmpty()){
            dist=Float.parseFloat(q.getSingleResult().toString());
        }
        return dist;
    }

    public float getDist_der(String mat) {
        float dist=0;
        Query q = em.createNativeQuery("Select TOP(1) Distance  from Plein Where Matricule=? Order By id_plein DESC ;");
        q.setParameter(1, mat);
         if(!q.getResultList().isEmpty()){
            dist=Float.parseFloat(q.getSingleResult().toString());
        }
        return dist;

    }

    public Double getRes_der(String mat) {
        Double res=0.0;
        Query q = em.createNativeQuery("Select TOP(1) Res_actuel from Plein Where Matricule=? Order By id_plein DESC ;");
        q.setParameter(1, mat);
         if(!q.getResultList().isEmpty()){
            res=Double.parseDouble(q.getSingleResult().toString());
        }
        return res;
       

    }

    public void updateKilo(String mat, float kilo) {
        Query q = em.createNativeQuery("Update Voitures set Kilometrage=? Where Matricule=?  ;");
        q.setParameter(1, kilo);
        q.setParameter(2, mat);

        q.executeUpdate();

    }

    public double getDebutKilo(String mat) {
        Query q = em.createNativeQuery("Select kilo_debut from voitures where matricule=?");
        q.setParameter(1, mat);
        return (double) q.getSingleResult();
    }

    public int getLastId() {
        Query q = em.createNativeQuery("Select MAX(id_plein) From plein");

        return (Integer) q.getSingleResult();
    }

    public float bringKilo(String mat) {
        Query q = em.createNativeQuery("Select Top(1) kilo_Actuel from frais f,plein p where p.matricule=? order by f.id_frais DESC");
        return Float.parseFloat(q.setParameter(1, mat).getSingleResult().toString());

    }

    public int bringId(String mat) {
        Query q = em.createNativeQuery("Select Top(1) id_frais from frais where matricule=? order by id_frais DESC");
        return Integer.parseInt(q.setParameter(1, mat).getSingleResult().toString());

    }
    
    
     public float getkilo(int idplein) {
        Query q = em.createNativeQuery("Select kilo_actuel from  plein  where id_plein=?");
        q.setParameter(1, idplein);
        //System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString());
    }
     public float getperiodiciteek(int idfrais) {
        Query q = em.createNativeQuery("Select kilo_debut from  [Periode Kilometrage Tab] p ,frais f  where  (p.id_periodekilo=f.id_periodekilo) and f.id_frais=? and id_periodet=4");
        q.setParameter(1, idfrais);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString());
    }
    
     
    
     
     
    public void updatekilo(int id,int idplein){
         float per=getperiodiciteek(id);
         float kilo=getkilo(idplein);
                  Query q = em.createNativeQuery("UPDATE  p SET p.kilo_trigger=? FROM [Periode kilometrage Tab] p INNER JOIN frais f ON (p.id_periodekilo = f.id_periodekilo) and id_frais = ? and id_periodet=4");

         kilo=kilo+per;
         q.setParameter(1, kilo);
         q.setParameter(2, id);
         q.executeUpdate();
    }

    public float getalarmekilo(int idfrais) {
        Query q = em.createNativeQuery("Select alarme from  [Periode Kilometrage Tab] p ,frais f  where  (p.id_periodekilo=f.id_periodekilo) and f.id_frais=? and id_periodet=4");
        q.setParameter(1, idfrais);
        System.out.println(Float.parseFloat(q.getSingleResult().toString()));
        return Float.parseFloat(q.getSingleResult().toString());
    }

    public LocalDate getdateplein(String mat) {
        Query q = em.createNativeQuery("Select top(1) date_plein from plein where matricule=? order by id_plein desc");
        q.setParameter(1, mat);
        return LocalDate.parse(q.getSingleResult().toString());
    }

    public int getperiodicitee(int id) {
        Query q = em.createNativeQuery("Select top(1) date_plein from plein where matricule=? order by id_plein desc");
        q.setParameter(1, id);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    public LocalDate getalarmet(int id) {
        Query q = em.createNativeQuery("Select Alarme from [Periode Temps Tab] p,frais f where f.id_frais=? and (p.id_periodet=f.id_periodet) and f.id_periodekilo=2 ");
        q.setParameter(1, id);
        return LocalDate.parse(q.getSingleResult().toString());
    }

    public void plus(int id_frais) {
        Query q = em.createNativeQuery("Insert into frais_actifes ( validation,date_actif,id_frais) Values(?,?,?)");

        q.setParameter(1, 0);

        q.setParameter(2, "02/02/2020");
        q.setParameter(3, id_frais);
        q.executeUpdate();
        System.out.println("success");
    }

    public int bringPeriodicitee(int id) {
        Query q = em.createNativeQuery("Select periodicitee from [Periode Temps Tab] p,frais f where f.id_frais=? and (p.id_periodet=f.id_periodet)");
        q.setParameter(1, id);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    public LocalDate bringdate(int id) {
        Query q = em.createNativeQuery("select date from [Periode Temps Tab]p ,frais f where  f.id_frais=? and (p.id_periodet=f.id_periodet) ");
        q.setParameter(1, id);
        return LocalDate.parse(q.getSingleResult().toString());

    }

    public int bringtolerance(int id) {
        Query q = em.createNativeQuery("select tolerance from [Periode Temps Tab]p ,frais f where  f.id_frais=? and (p.id_periodet=f.id_periodet) ");
        q.setParameter(1, id);
        return Integer.parseInt(q.getSingleResult().toString());

    }
    
    public Date bringpleindate(int id){
        Query q = em.createNativeQuery("select date_plein from plein where  id_plein=?  ");
        q.setParameter(1, id);
        return Date.valueOf(q.getSingleResult().toString());
    }

    public void updatedatet(Period p, int id,int idplein) {
        Query q = em.createNativeQuery("UPDATE  p SET p.date=? FROM [Periode Temps Tab] p INNER JOIN frais f ON (p.id_periodet = f.id_periodet) and id_frais = ?");
        LocalDate d = bringpleindate(idplein).toLocalDate();
        LocalDate ld=d;
        d = d.plus(p);
        Date da = Date.valueOf(d);
        
       
        q.setParameter(1, da);
        q.setParameter(2, id);
        q.executeUpdate();
     
    }

    public List<Integer> bringFraist(String mat) {
        Query q = em.createNativeQuery("Select id_frais from frais where matricule = ? and id_periodekilo=2");
        q.setParameter(1, mat);
        return q.getResultList();
    }

    public List<Integer> bringFraisk(String mat) {
        Query q = em.createNativeQuery("Select id_frais from frais where matricule = ? and id_periodet=4 ");
        q.setParameter(1, mat);
        return q.getResultList();
    }
    
    
    public float whichres(String mat){
        float res=0;
        Query q = em.createNativeQuery("Select top(1) res_actuel from plein where matricule = ? order by id_plein desc  ");
        q.setParameter(1, mat);
        if(!q.getResultList().isEmpty()){
            res=Float.parseFloat(q.getSingleResult().toString());
        }
        else{
            Query p = em.createNativeQuery("Select puissance from voitures where matricule = ?   ");
        p.setParameter(1, mat);
        res=Integer.parseInt(p.getSingleResult().toString());
        }
        return res;
    }

    public PleinFacade() {
        super(Plein.class);
    }

}
