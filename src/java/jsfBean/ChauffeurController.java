package jsfBean;

import entities.Chauffeur;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.ChauffeurFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("chauffeurController")
@SessionScoped
public class ChauffeurController implements Serializable {

    @EJB
    private sessionBean.ChauffeurFacade ejbFacade;
    private List<Chauffeur> items = null;
    private Chauffeur selected;

    public ChauffeurController() {
    }
    
    public String veriflog(){
        UtilisateurController uc=new UtilisateurController();
        String ch="chauffeur";
        if (!uc.isConnected()){
            ch="auth";
        }
        return ch;
    }

    public Chauffeur getSelected() {
        return selected;
    }

    public void setSelected(Chauffeur selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ChauffeurFacade getFacade() {
        return ejbFacade;
    }

    
    public String writecin(float cin){
        String ch=String.format("%f",cin);
        ch=ch.substring(0, 8);
        return ch;
    }
    
    public Chauffeur prepareCreate() {
        selected = new Chauffeur();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
       
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ChauffeurCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ChauffeurUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ChauffeurDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Chauffeur> getItems() {
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

    public Chauffeur getChauffeur(java.lang.Double id) {
        return getFacade().find(id);
    }

    public List<Chauffeur> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Chauffeur> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Chauffeur.class)
    public static class ChauffeurControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChauffeurController controller = (ChauffeurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "chauffeurController");
            return controller.getChauffeur(getKey(value));
        }

        java.lang.Double getKey(String value) {
            java.lang.Double key;
            key = Double.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Double value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Chauffeur) {
                Chauffeur o = (Chauffeur) object;
                return getStringKey(o.getCin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Chauffeur.class.getName()});
                return null;
            }
        }

    }

}
