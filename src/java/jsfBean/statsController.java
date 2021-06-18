/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfBean;
import com.sun.javafx.charts.Legend;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartData;
import org.jfree.chart.title.Title;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author DHIA
 */
@Named(value = "statsController")
@Dependent
public class statsController {

    
   
    
    
     private BarChartModel barModel;
     @PostConstruct 
     public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
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
     
     
     
     
     public BarChartModel initBarModel(){
         BarChartModel model = new BarChartModel();
         ChartSeries java=new ChartSeries();
         java.setLabel("Java");
         java.set("2004",120);
         java.set("2005",60);
         java.set("2006",180);
         java.set("2007",300);
         java.set("2008",40);
         
          ChartSeries python=new ChartSeries();
         python.setLabel("Python");
         python.set("2004",40);
         python.set("2005",60);
         python.set("2006",180);
         python.set("2007",300);
         python.set("2008",400);
         model.addSeries(java);
          model.addSeries(python);
          return model;
     }
     
    /**
     * Creates a new instance of statsController
     */
    public statsController() {
    }
    
}
