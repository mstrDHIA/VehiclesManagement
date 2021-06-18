package entities;

import entities.Fraisactifes;
import entities.PeriodeKilometrageTab;
import entities.PeriodeTempsTab;
import entities.TypeFrais;
import entities.Voitures;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Frais.class)
public class Frais_ { 

    public static volatile SingularAttribute<Frais, TypeFrais> idType;
    public static volatile SingularAttribute<Frais, Voitures> matricule;
    public static volatile SingularAttribute<Frais, Integer> idFrais;
    public static volatile CollectionAttribute<Frais, Fraisactifes> fraisactifesCollection;
    public static volatile SingularAttribute<Frais, PeriodeKilometrageTab> idPeriodekilo;
    public static volatile SingularAttribute<Frais, PeriodeTempsTab> idPeriodet;
    public static volatile SingularAttribute<Frais, Double> montant;
    public static volatile SingularAttribute<Frais, String> nom;

}