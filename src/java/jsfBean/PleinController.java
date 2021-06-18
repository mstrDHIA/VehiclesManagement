package jsfBean;

import entities.Plein;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.PleinFacade;
//import java.sql.Date;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@Named("pleinController")
@SessionScoped
public class PleinController implements Serializable {

    @EJB
    private sessionBean.PleinFacade ejbFacade;
    private List<Plein> items = null;
    private Plein selected;

    public PleinController() {
    }

    public Plein getSelected() {
        return selected;
    }

    public void setSelected(Plein selected) {
        this.selected = selected;
    }

    
    
    public String formatfloat(float f){
       /* DecimalFormat df=new DecimalFormat("#.##");
        String done=df.format(f);
        return done;*/
         String ch="";
        if(f == (long) f)
        ch=String.format("%d",(long)f);
        else
        ch=String.format("%.2f",f);
       
        return ch;
    }
    
    
     
    public String formatdate(java.util.Date d){
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
           String strDate= formatter.format(d); 
          
           return strDate;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PleinFacade getFacade() {
        return ejbFacade;
    }

    public void whichone(String mat) {

        controlt(mat);
        controlk(mat);
    }

    public void controlt(String mat) {
        boolean ok = false;

        LocalDate dp = null;
        Date dat=getSelected().getDateplein();
        dp=dat.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //dp=LocalDate.
        List<Integer> l = getFacade().bringFraist(mat);
        System.out.println(dp);
        for (int i = 0; i < l.size(); i++) {
            int id = l.get(i);
            LocalDate d = getFacade().getalarmet(id);
            int test = d.compareTo(dp);
            if (test < 0) {
                getFacade().plus(id);
                /*RequestContext rc=RequestContext.getCurrentInstance();
                rc.update("FraisactifesListForm:lst:datalist");*/
                Period p = Period.ofMonths(getFacade().bringPeriodicitee(id));
                System.out.println(dp + " " + d + " " + test);
                // java.util.Date sd=getSelected().getDateplein();
                //Date ll=(java.sql.Date)getSelected().getDateplein();
                // LocalDate aaa=LocalDate.parse(getSelected().getDateplein().toString());
                getFacade().updatedatet(p, id,getSelected().getIdPlein());
                
                makefrais();
            }

        }

    }

    public void controlk(String mat) {
        boolean ok = false;
        List<Integer> l = getFacade().bringFraisk(mat);
        System.out.println(l);
        for (int i = 0; i < l.size(); i++) {
            int id = l.get(i);

            float dist = getFacade().getdist(mat);
            float dist_dern = getFacade().getdist_dern(mat);
            float alarmek = getFacade().getalarmekilo(id);
            //int q = (int) (dist_dern / alarmek);
            //int p = (int) (dist / alarmek);
            // ok=false ;
           // System.out.println(p);
            //System.out.println(q);
            /*if (q != p) {
                ok = true;
            }*/
            if ((dist >= alarmek ) ) {
                
                getFacade().plus(id);
                makefrais();
                System.out.println(getSelected().getIdPlein());
                getFacade().updatekilo(id,getSelected().getIdPlein());
                // RequestContext.getCurrentInstance().update("foo:bar");
                //ok = false;
                //}
            }

            //makefrais();
            //getFacade().plus(id);
        }
        //k=Float.parseFloat(kilo) ;
        //if(getFacade().bringKilo(mat)>0){

        System.out.println(ok);

    }

    public void makefrais() {

        System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee heheheheheheheheya mamaweeeey");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une nouvelle frais doit etre validee consulter les frais actifes pour plus de details"));

    }

    public void testcontrolk(String mat) {
        // List<Integer> l = getFacade().bringFrais(mat);
        FraisController f = new FraisController();
        Boolean ok = f.controlk(4, mat);
        System.out.println(ok);
    }
    
    
   

    public Plein prepareCreate() {
        selected = new Plein();
        initializeEmbeddableKey();
        return selected;
    }

    public void i5dem() {
        System.out.println(getFacade().getLastId());
        //getSelected().setIdPlein(23);

    }

    /*
    public void notification(String mat) {
        try {
            
            FraisController f =  FraisController.class.newInstance();
             boolean ok=false ;
        //float k;
        //k=Float.parseFloat(kilo) ;
         if(getFacade().bringKilo(mat)>0){
        float dist=getFacade().getdist(mat);
        float dist_dern=getFacade().getdist_dern(mat);
        String alarmek=getFacade().getalarmekilo(mat);
        
        
        /*int q = (int) (dist_dern/alarmek);        
        int p=(int) (dist/alarmek);
        // ok=false ;
        System.out.println(p);
        System.out.println(q);
        if(q!=p){
            ok=true;
        }
        if((dist>=alarmek*p)&&(ok==true)){
            System.out.println("you need assurance "+mat);
      // RequestContext.getCurrentInstance().update("foo:bar");
            ok=false;
        }
         }
            
            
            
            
            
            if(ok){f.makefrais();}
        } catch (InstantiationException ex) {
            Logger.getLogger(PleinController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PleinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     */
    public void create() {
        // System.out.println(getFacade().getLastId());

        change();

        System.out.println(getFacade().getLastId() + 1);
        getSelected().setIdPlein(getFacade().getLastId() + 1);

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PleinCreated"));

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    
    
    public String writeprixplein(){
        getSelected().setPrixplein(getSelected().getPrixlitre() * getSelected().getLitres());
        return String.valueOf(getSelected().getPrixplein());
    }
    
    
    public void test(String mat){
        System.out.println(getFacade().whichres(mat));
    }

    public void change() {
        //getSelected().setDistance(Float.parseFloat(getFacade().getDist_der((getSelected().getMatricule().toString()))) + getSelected().getKiloActuel() - getFacade().getDebutKilo(getSelected().getMatricule().toString()));
         getSelected().setDistancederniere(getFacade().getDist_der((getSelected().getMatricule().toString())));
        getSelected().setDistance(getSelected().getDistancederniere()+getSelected().getKiloActuel());
        getSelected().setPrixplein(getSelected().getPrixlitre() * getSelected().getLitres());
       
        getSelected().setResdernier(getFacade().getRes_der(getSelected().getMatricule().toString()));
        System.out.println((getFacade().whichres(getSelected().getMatricule().toString()) - getSelected().getResactuel()) / ((getSelected().getDistance() + getSelected().getDistancederniere())));
        getSelected().setConsommation((getFacade().whichres(getSelected().getMatricule().toString()) -getSelected().getResactuel() )/ ((getSelected().getDistance() + getSelected().getDistancederniere())));
        
        getSelected().setCoutkilo(getSelected().getPrixplein() / (getSelected().getDistance() - getSelected().getDistancederniere()));
        getFacade().updateKilo(getSelected().getMatricule().toString(), (float) getSelected().getKiloActuel());

    }

    public void update() {
        change();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PleinUpdated"));

    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PleinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Plein> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /*public void writeDist_dern(String mat){
        
      
    }*/
    public Plein getPlein(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Plein> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Plein> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Plein.class)
    public static class PleinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PleinController controller = (PleinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pleinController");
            return controller.getPlein(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Plein) {
                Plein o = (Plein) object;
                return getStringKey(o.getIdPlein());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Plein.class.getName()});
                return null;
            }
        }

    }

}
