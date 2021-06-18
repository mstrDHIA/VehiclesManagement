package entities;

import entities.Fournisseur;
import entities.Voitures;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Depences.class)
public class Depences_ { 

    public static volatile SingularAttribute<Depences, Double> total;
    public static volatile SingularAttribute<Depences, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Depences, Voitures> matricule;
    public static volatile SingularAttribute<Depences, Integer> idDepences;

}