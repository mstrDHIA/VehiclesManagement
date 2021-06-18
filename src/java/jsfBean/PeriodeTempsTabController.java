package jsfBean;

import entities.PeriodeTempsTab;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.PeriodeTempsTabFacade;
import java.time.Period;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
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

@Named("periodeTempsTabController")
@SessionScoped
public class PeriodeTempsTabController implements Serializable {

    @EJB
    private sessionBean.PeriodeTempsTabFacade ejbFacade;
    private List<PeriodeTempsTab> items = null;
    private PeriodeTempsTab selected;

    public PeriodeTempsTabController() {
    }

    public PeriodeTempsTab getSelected() {
        return selected;
    }

    public void setSelected(PeriodeTempsTab selected) {
        this.selected = selected;
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

    private PeriodeTempsTabFacade getFacade() {
        return ejbFacade;
    }

    public PeriodeTempsTab prepareCreate() {
        selected = new PeriodeTempsTab();
        initializeEmbeddableKey();
        return selected;
    }

    
    
    public Date writedate(int id){
        return getFacade().bringdate(id);
    }
    
    public void testdate(){
        java.util.Date ud= getFacade().bringalerme(7);
         java.sql.Date sd= getFacade().bringalerme(7);
        //java.util.Date ud=new java.util.Date()  ;
        //java.sql.Date sd=new java.sql.Date(ud.getTime());
       // ud = new java.util.Date(sd.getTime());
        System.out.println(ud);
         System.out.println(sd);
        //System.out.println(sd);
    }
    
    
    
    public Date writealarme(int id){
        Date d=getFacade().bringdate(id);
        LocalDate ld=d.toLocalDate();
        int t=getFacade().bringtolerance(id);
        ld=ld.minusDays(t);
        d=Date.valueOf(ld);
         getFacade().updatealarme(id,d);
         
        return d;
    }
    
     
    
    public void change (){
        java.sql.Date d= new java.sql.Date(getSelected().getDate().getTime()); 
        
        //Date d =(Date) getSelected().getDate();
        int t=getSelected().getTolerance();
        //t=365-((int)3.65*t);
        LocalDate ld=d.toLocalDate();
        //LocalDate ld=d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //System.out.println(ld);
        //Period p=Period.ofDays(t);
        ld=ld.minusDays(t);
        d=Date.valueOf(ld);
        getSelected().setAlarme(d);
        System.out.println(getSelected().getAlarme());
    }
    
    public void create() {
        change();
         getSelected().setIdPeriodet(getFacade().getLastId() + 1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeriodeTempsTabCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        change();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PeriodeTempsTabUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PeriodeTempsTabDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void addperiode(){
        LocalDate d=LocalDate.of(2020,02,02);
        
        System.out.println(d);
        Period p=Period.ofMonths(06);
        d=d.plus(p);
        System.out.println(d);
    }
    
    
    
    public Date writeAlarme(int id){
        return getFacade().bringalerme(id);
    }
    
    public List<PeriodeTempsTab> getItems() {
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

    public PeriodeTempsTab getPeriodeTempsTab(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PeriodeTempsTab> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PeriodeTempsTab> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PeriodeTempsTab.class)
    public static class PeriodeTempsTabControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeriodeTempsTabController controller = (PeriodeTempsTabController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "periodeTempsTabController");
            return controller.getPeriodeTempsTab(getKey(value));
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
            if (object instanceof PeriodeTempsTab) {
                PeriodeTempsTab o = (PeriodeTempsTab) object;
                return getStringKey(o.getIdPeriodet());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PeriodeTempsTab.class.getName()});
                return null;
            }
        }

    }

}
