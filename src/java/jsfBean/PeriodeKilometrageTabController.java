package jsfBean;

import entities.PeriodeKilometrageTab;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.PeriodeKilometrageTabFacade;

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

@Named("periodeKilometrageTabController")
@SessionScoped
public class PeriodeKilometrageTabController implements Serializable {

    @EJB
    private sessionBean.PeriodeKilometrageTabFacade ejbFacade;
    private List<PeriodeKilometrageTab> items = null;
    private PeriodeKilometrageTab selected;

    public PeriodeKilometrageTabController() {
    }

    public PeriodeKilometrageTab getSelected() {
        return selected;
    }

    public void setSelected(PeriodeKilometrageTab selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void change() {
        getSelected().setAlarme(getSelected().getKilotrigger() - getSelected().getTolerance());
    }

    private PeriodeKilometrageTabFacade getFacade() {
        return ejbFacade;
    }

    public PeriodeKilometrageTab prepareCreate() {
        selected = new PeriodeKilometrageTab();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        getSelected().setIdPeriodekilo(getFacade().getLastId() + 1);
        change();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeriodeKilometrageTabCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        change();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PeriodeKilometrageTabUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PeriodeKilometrageTabDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public float changealarmek(int id){
       return getFacade().changealarme(id);
    }
    
    public List<PeriodeKilometrageTab> getItems() {
        if (items != null) {
            items.clear();
        }
         items = getFacade().findAll();
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

    public PeriodeKilometrageTab getPeriodeKilometrageTab(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PeriodeKilometrageTab> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PeriodeKilometrageTab> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PeriodeKilometrageTab.class)
    public static class PeriodeKilometrageTabControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeriodeKilometrageTabController controller = (PeriodeKilometrageTabController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "periodeKilometrageTabController");
            return controller.getPeriodeKilometrageTab(getKey(value));
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
            if (object instanceof PeriodeKilometrageTab) {
                PeriodeKilometrageTab o = (PeriodeKilometrageTab) object;
                return getStringKey(o.getIdPeriodekilo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeriodeKilometrageTab.class.getName()});
                return null;
            }
        }

    }

}
