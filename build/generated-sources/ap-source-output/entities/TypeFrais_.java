package entities;

import entities.Frais;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(TypeFrais.class)
public class TypeFrais_ { 

    public static volatile SingularAttribute<TypeFrais, Integer> idType;
    public static volatile CollectionAttribute<TypeFrais, Frais> fraisCollection;
    public static volatile SingularAttribute<TypeFrais, String> type;

}