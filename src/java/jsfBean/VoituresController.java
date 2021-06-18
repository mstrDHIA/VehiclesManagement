package jsfBean;

import entities.Voitures;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.VoituresFacade;

import java.io.Serializable;
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

@Named("voituresController")
@SessionScoped
public class VoituresController implements Serializable {

    @EJB
    private sessionBean.VoituresFacade ejbFacade;
    private List<Voitures> items = null;
    private Voitures selected;

    public VoituresController() {
    }

    public Voitures getSelected() {
        return selected;
    }

    
    
    
    
    
     
    public String formatdate(java.util.Date d){
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
           String strDate= formatter.format(d); 
          
           return strDate;
    }
    public void setSelected(Voitures selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VoituresFacade getFacade() {
        return ejbFacade;
    }

    public Voitures prepareCreate() {
        selected = new Voitures();
        initializeEmbeddableKey();
        return selected;
    }
    
    
    public void createdepences(){
        //int id_depences=getFacade().getLastId()+1;
        float total=getFacade().calcul_total(getSelected().getMatricule());
        //Integer fournisseur=getFacade().getFournisseur(getSelected().getMatricule());
        getFacade().createdepences(total,getSelected().getMatricule());
        makedepences();
    }
    
     public void makedepences() {

        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Felicitation un nouveau champ Depences a ete cree pour votre nouvelle voiture voir page depences pour plus de details"));

    }

    public void create() {
        
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VoituresCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VoituresUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VoituresDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Voitures> getItems() {
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

    public String refreshKilo(String mat) {
        //getFacade().getkilonew(mat);
        return getFacade().getkilonew(mat);
    }

    public Voitures getVoitures(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Voitures> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Voitures> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Voitures.class)
    public static class VoituresControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VoituresController controller = (VoituresController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "voituresController");
            return controller.getVoitures(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Voitures) {
                Voitures o = (Voitures) object;
                return getStringKey(o.getMatricule());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Voitures.class.getName()});
                return null;
            }
        }

    }

}
