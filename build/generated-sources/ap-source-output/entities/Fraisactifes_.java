package entities;

import entities.Frais;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Fraisactifes.class)
public class Fraisactifes_ { 

    public static volatile SingularAttribute<Fraisactifes, Integer> idActif;
    public static volatile SingularAttribute<Fraisactifes, Frais> idFrais;
    public static volatile SingularAttribute<Fraisactifes, Date> dateValidation;
    public static volatile SingularAttribute<Fraisactifes, Date> dateActif;
    public static volatile SingularAttribute<Fraisactifes, Integer> periodeActif;
    public static volatile SingularAttribute<Fraisactifes, Boolean> validation;

}