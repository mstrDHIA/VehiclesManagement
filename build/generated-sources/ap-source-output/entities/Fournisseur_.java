package entities;

import entities.Contrat;
import entities.Depences;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Fournisseur.class)
public class Fournisseur_ { 

    public static volatile SingularAttribute<Fournisseur, Integer> idFournisseur;
    public static volatile CollectionAttribute<Fournisseur, Depences> depencesCollection;
    public static volatile SingularAttribute<Fournisseur, String> nom;
    public static volatile SingularAttribute<Fournisseur, Contrat> idContrat;

}