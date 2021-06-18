package jsfBean;

import entities.Fraisactifes;
import entities.Frais;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.FraisFacade;
import jsfBean.FraisactifesController;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
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

@Named("fraisController")
@SessionScoped
public class FraisController implements Serializable {

    @EJB
    private sessionBean.FraisFacade ejbFacade;
    private List<Frais> items = null;
    private Frais selected;

    public FraisController() {
    }

    public Frais getSelected() {
        return selected;
    }

    public void setSelected(Frais selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }
    
    
    
    
    
    
     
    public String formatdate(java.util.Date d){
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
           String strDate= formatter.format(d); 
          
           return strDate;
    }

    protected void initializeEmbeddableKey() {
    }

    private FraisFacade getFacade() {
        return ejbFacade;
    }
    
    
    
    
    
    
     public Boolean controlk(int idfrais,String mat){
        boolean ok=false ;
        
        FraisController f = new FraisController();
        //k=Float.parseFloat(kilo) ;
         //if(getFacade().bringKilo(mat)>0){
        float dist=f.getFacade().getdist(mat);
        float dist_dern=f.getFacade().getdist_dern(mat);
        float alarmek=f.getFacade().getalarmekilo(idfrais);
        int q = (int) (dist_dern/alarmek);        
        int p=(int) (dist/alarmek);
        // ok=false ;
        System.out.println(p);
        System.out.println(q);
        if(q!=p){
            ok=true;
        }
        if((dist>=alarmek*p)&&(ok==true)){
            
            getFacade().plus(idfrais);
            makefrais();
      // RequestContext.getCurrentInstance().update("foo:bar");
            ok=false;
        //}
         }
         
         
         
         
         
                    makefrais();
                  getFacade().plus(idfrais);
         
    System.out.println(ok);     
    return ok;   
     }     
     /*public void test(int idfrais){
        // getFacade().plus(idfrais);
     }
*/
     /*public void lala(){
       List <Fraisactifes> f = getFacade().all();
       f.get(1);
       int x=f.get(1).getIdActif();
       System.out.println(x);
       
     }*/
      public void makefrais(){
        
        
        System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee heheheheheheheheya mamaweeeey");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("woooooooooooooooooooooooork"));
        
        
        
    }
      
      
      public Date writealarme(int id_periodet){
          return getFacade().bringalarmet(id_periodet);
      }
     
    public Frais prepareCreate() {
        selected = new Frais();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        getSelected().setIdFrais(getFacade().getLastId() + 1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FraisCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FraisUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FraisDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Frais> getItems() {
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

    public Frais getFrais(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Frais> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Frais> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Frais.class)
    public static class FraisControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FraisController controller = (FraisController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fraisController");
            return controller.getFrais(getKey(value));
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
            if (object instanceof Frais) {
                Frais o = (Frais) object;
                return getStringKey(o.getIdFrais());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Frais.class.getName()});
                return null;
            }
        }

    }

}
