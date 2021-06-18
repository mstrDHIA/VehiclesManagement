package entities;

import entities.Frais;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(PeriodeTempsTab.class)
public class PeriodeTempsTab_ { 

    public static volatile SingularAttribute<PeriodeTempsTab, Date> date;
    public static volatile SingularAttribute<PeriodeTempsTab, Date> alarme;
    public static volatile SingularAttribute<PeriodeTempsTab, Integer> idPeriodet;
    public static volatile CollectionAttribute<PeriodeTempsTab, Frais> fraisCollection;
    public static volatile SingularAttribute<PeriodeTempsTab, Integer> periodicitee;
    public static volatile SingularAttribute<PeriodeTempsTab, Integer> tolerance;

}