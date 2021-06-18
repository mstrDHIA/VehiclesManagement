package jsfBean;

import entities.TypeVoiture;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.TypeVoitureFacade;

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

@Named("typeVoitureController")
@SessionScoped
public class TypeVoitureController implements Serializable {

    @EJB
    private sessionBean.TypeVoitureFacade ejbFacade;
    private List<TypeVoiture> items = null;
    private TypeVoiture selected;

    public TypeVoitureController() {
    }

    public TypeVoiture getSelected() {
        return selected;
    }

    public void setSelected(TypeVoiture selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TypeVoitureFacade getFacade() {
        return ejbFacade;
    }

    public TypeVoiture prepareCreate() {
        selected = new TypeVoiture();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        getSelected().setIdTypev(getFacade().getLastId() + 1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TypeVoitureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TypeVoitureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TypeVoitureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TypeVoiture> getItems() {
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

    public TypeVoiture getTypeVoiture(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TypeVoiture> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TypeVoiture> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TypeVoiture.class)
    public static class TypeVoitureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TypeVoitureController controller = (TypeVoitureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "typeVoitureController");
            return controller.getTypeVoiture(getKey(value));
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
            if (object instanceof TypeVoiture) {
                TypeVoiture o = (TypeVoiture) object;
                return getStringKey(o.getIdTypev());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TypeVoiture.class.getName()});
                return null;
            }
        }

    }

}
