package entities;

import entities.Voitures;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(TypeCarburant.class)
public class TypeCarburant_ { 

    public static volatile CollectionAttribute<TypeCarburant, Voitures> voituresCollection;
    public static volatile SingularAttribute<TypeCarburant, Integer> idCarburant;
    public static volatile SingularAttribute<TypeCarburant, String> typecarb;

}