package entities;

import entities.Frais;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(PeriodeKilometrageTab.class)
public class PeriodeKilometrageTab_ { 

    public static volatile SingularAttribute<PeriodeKilometrageTab, Double> alarme;
    public static volatile SingularAttribute<PeriodeKilometrageTab, Integer> idPeriodekilo;
    public static volatile CollectionAttribute<PeriodeKilometrageTab, Frais> fraisCollection;
    public static volatile SingularAttribute<PeriodeKilometrageTab, Double> kilotrigger;
    public static volatile SingularAttribute<PeriodeKilometrageTab, Double> kilodebut;
    public static volatile SingularAttribute<PeriodeKilometrageTab, Integer> tolerance;

}