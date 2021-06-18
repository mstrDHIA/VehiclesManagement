package jsfBean;

import entities.Depences;
import jsfBean.util.JsfUtil;
import jsfBean.util.JsfUtil.PersistAction;
import sessionBean.DepencesFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named("depencesController")
@SessionScoped
public class DepencesController implements Serializable {

    @EJB
    private sessionBean.DepencesFacade ejbFacade;
    private List<Depences> items = null;
    private Depences selected;

    public DepencesController() {
    }

    public Depences getSelected() {
        return selected;
    }

    public void setSelected(Depences selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DepencesFacade getFacade() {
        return ejbFacade;
    }

    public Depences prepareCreate() {
        selected = new Depences();
        initializeEmbeddableKey();
        return selected;
    }
    
     
    public String formatdate(java.util.Date d){
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
           String strDate= formatter.format(d); 
          
           return strDate;
    }

    public void getTotal(){
        
    }
    
    
    public String update_total(String mat){
        float total = getFacade().calcul_total(mat);
        String ch="";
        if(total == (long) total)
        ch=String.format("%d",(long)total);
        else
        ch=String.format("%s",total);
       
        return ch;
            // return String.valueOf(getFacade().calcul_total(mat));
}
    
    
    
    
     private BarChartModel barModel;
     @PostConstruct 
     public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        if (barModel!=null){
            barModel.clear();
        }
        init();
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
     
     public void  createBarModels(){
          createBarModel();
     }
     
     public void  createBarModel(){
         barModel=initBarModel();
         barModel.setTitle("Bar Chart");
         barModel.setLegendPosition("ne");
        
     }
     
     
     
     
     private BarChartModel initBarModel(){
         BarChartModel model = new BarChartModel();
         List<String> mats=getFacade().getmats();
         ChartSeries matricules=new ChartSeries();
         for(int i=0;i<mats.size();i++){
             String mat=mats.get(i);
              float f =getFacade().calcul_total(mat);
              matricules.set(mat,f);
         }
         
       
          model.addSeries(matricules);
          return model;
     }

    
    public void create() {
        getSelected().setIdDepences(getFacade().getLastId() + 1);
        getSelected().setTotal(getFacade().calcul_total(getSelected().getMatricule().toString()));             
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DepencesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DepencesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DepencesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Depences> getItems() {
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

    public Depences getDepences(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Depences> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Depences> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Depences.class)
    public static class DepencesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DepencesController controller = (DepencesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "depencesController");
            return controller.getDepences(getKey(value));
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
            if (object instanceof Depences) {
                Depences o = (Depences) object;
                return getStringKey(o.getIdDepences());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Depences.class.getName()});
                return null;
            }
        }

    }

}
