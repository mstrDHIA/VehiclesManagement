package entities;

import entities.Fournisseur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Contrat.class)
public class Contrat_ { 

    public static volatile SingularAttribute<Contrat, Date> date;
    public static volatile SingularAttribute<Contrat, String> entreprise;
    public static volatile CollectionAttribute<Contrat, Fournisseur> fournisseurCollection;
    public static volatile SingularAttribute<Contrat, Integer> idContrat;
    public static volatile SingularAttribute<Contrat, Double> periode;

}