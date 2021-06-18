package entities;

import entities.Chauffeur;
import entities.Frais;
import entities.Plein;
import entities.Site;
import entities.TypeCarburant;
import entities.TypeVoiture;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-20T14:41:26")
@StaticMetamodel(Voitures.class)
public class Voitures_ { 

    public static volatile SingularAttribute<Voitures, Double> consomax;
    public static volatile SingularAttribute<Voitures, String> matricule;
    public static volatile SingularAttribute<Voitures, Double> prix;
    public static volatile CollectionAttribute<Voitures, Plein> pleinCollection;
    public static volatile SingularAttribute<Voitures, Double> volres;
    public static volatile SingularAttribute<Voitures, Chauffeur> cin;
    public static volatile SingularAttribute<Voitures, TypeVoiture> idTypev;
    public static volatile CollectionAttribute<Voitures, Frais> fraisCollection;
    public static volatile SingularAttribute<Voitures, String> nom;
    public static volatile SingularAttribute<Voitures, Double> kilodebut;
    public static volatile SingularAttribute<Voitures, String> marque;
    public static volatile SingularAttribute<Voitures, Double> consomin;
    public static volatile SingularAttribute<Voitures, Double> prixpleindepart;
    public static volatile SingularAttribute<Voitures, Integer> nbPlace;
    public static volatile SingularAttribute<Voitures, Date> dateachat;
    public static volatile SingularAttribute<Voitures, Integer> puissance;
    public static volatile SingularAttribute<Voitures, Site> idSite;
    public static volatile SingularAttribute<Voitures, Double> kilometrage;
    public static volatile SingularAttribute<Voitures, Date> datedebut;
    public static volatile SingularAttribute<Voitures, TypeCarburant> idCarburant;

}