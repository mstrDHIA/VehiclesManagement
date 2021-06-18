package entities;

import entities.Voitures;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Site.class)
public class Site_ { 

    public static volatile SingularAttribute<Site, byte[]> localisation;
    public static volatile SingularAttribute<Site, Integer> idSite;
    public static volatile CollectionAttribute<Site, Voitures> voituresCollection;
    public static volatile SingularAttribute<Site, String> nom;
    public static volatile SingularAttribute<Site, String> governerat;

}