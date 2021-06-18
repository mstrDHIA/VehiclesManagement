package jsfBean;

import entities.Fraisactifes;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.FraisactifesFacade;

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

@Named("fraisactifesController")
@SessionScoped
public class FraisactifesController implements Serializable {

    @EJB
    private sessionBean.FraisactifesFacade ejbFacade;
    private List<Fraisactifes> items = null;
    private Fraisactifes selected;

    public FraisactifesController() {
    }
    
    
    
    
    
     
    public String formatdate(java.util.Date d){
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
           String strDate= formatter.format(d); 
          
           return strDate;
    }

    public int refresh() {

        //System.out.println(actifes.get(1).getValidation());
        return getFacade().lkol().get(1);

    }
    
    
    public String writemat(int id){
        return getFacade().bringmat(id);
        
    }
 
    public float writemontant(int id){
        float montant=getFacade().bringmontant(id);
        return montant;
    }

    public Fraisactifes getSelected() {
        return selected;
    }

    public void setSelected(Fraisactifes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FraisactifesFacade getFacade() {
        return ejbFacade;
    }

    public Fraisactifes prepareCreate() {
        selected = new Fraisactifes();
        initializeEmbeddableKey();
        return selected;
    }

    
    public void create() {
        getSelected().setIdActif(getFacade().getLastId() + 1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FraisactifesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FraisactifesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FraisactifesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Fraisactifes> getItems() {
       if(items!=null){items.clear();}
        
        //if (items.isEmpty()||items==null) {
            items = getFacade().findAll();

      //  }
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

    public Fraisactifes getFraisactifes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Fraisactifes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Fraisactifes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Fraisactifes.class)
    public static class FraisactifesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FraisactifesController controller = (FraisactifesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fraisactifesController");
            return controller.getFraisactifes(getKey(value));
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
            if (object instanceof Fraisactifes) {
                Fraisactifes o = (Fraisactifes) object;
                return getStringKey(o.getIdActif());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Fraisactifes.class.getName()});
                return null;
            }
        }

    }

}
