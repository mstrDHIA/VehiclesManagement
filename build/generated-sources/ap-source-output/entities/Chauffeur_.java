package entities;

import entities.Voitures;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Chauffeur.class)
public class Chauffeur_ { 

    public static volatile CollectionAttribute<Chauffeur, Voitures> voituresCollection;
    public static volatile SingularAttribute<Chauffeur, Double> cin;
    public static volatile SingularAttribute<Chauffeur, String> nom;
    public static volatile SingularAttribute<Chauffeur, String> prenom;
    public static volatile SingularAttribute<Chauffeur, Integer> age;

}