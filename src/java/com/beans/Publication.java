/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wissem
 */
@Entity
@Table(name = "publication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publication.findAll", query = "SELECT p FROM Publication p order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findAllAfterId", query = "SELECT p FROM Publication p where p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCategorie", query = "SELECT p FROM Publication p where p.categorie in :categories order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyVille", query = "SELECT p FROM Publication p where p.ville = :ville order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCategorieVille", query = "SELECT p FROM Publication p where p.categorie in :categories and p.ville = :ville order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyGouvernorat", query = "SELECT p FROM Publication p where p.gouvernorat = :gouvernorat order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCategorieGouvernorat", query = "SELECT p FROM Publication p where p.categorie in :categories and p.gouvernorat = :gouvernorat order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCategorieAfterId", query = "SELECT p FROM Publication p where p.categorie in :categories and p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyVilleAfterId", query = "SELECT p FROM Publication p where p.ville = :ville and p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCategorieVilleAfterId", query = "SELECT p FROM Publication p where p.categorie in :categories and p.ville = :ville and p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyGouvernoratAfterId", query = "SELECT p FROM Publication p where p.gouvernorat = :gouvernorat and p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCategorieGouvernoratAfterId", query = "SELECT p FROM Publication p where p.categorie in :categories and p.gouvernorat = :gouvernorat and p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCompte", query = "SELECT p FROM Publication p where p.compte=:compte order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCompteAfterId", query = "SELECT p FROM Publication p where p.compte=:compte and p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.nbreComptesSignales", query = "SELECT count(c) FROM Compte c where :publication member of c.publicationsSignales"),
    @NamedQuery(name = "Publication.nbreParGouvernorat", query = "SELECT count(p) FROM Publication p where p.gouvernorat = :gouvernorat"),
    @NamedQuery(name = "Publication.nbrePubParEtat", query = "SELECT count(p) FROM Publication p where p.etat=:etat")})


public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPublication")
    private Integer idPublication;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Titre")
    private String titre = "T";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Type")
    private String type = "T";
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date de création")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedecreation = new Date();
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Ville")
    private String ville = "V";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Catégorie")
    private String categorie = "C";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Etat")
    private String etat = "non résolu";
    @Size(max = 1000)
    @Column(name = "Exprimer")
    private String description = "";
    @Basic(optional = false)
    @NotNull
    @Column(name = "Anonyme")
    private boolean anonyme = false;
    @JoinColumn(name = "Compte", referencedColumnName = "IdCompte")
    @ManyToOne(optional = false)
    private Compte compte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Lat")
    private float lat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Lng")
    private float lng;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Gouvernorat")
    private String gouvernorat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "Addresse")
    private String addresse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publication", fetch = FetchType.EAGER)
    @OrderBy("idCommentaire desc")
    private List<Commentaire> commentaireList;

    @ManyToMany(mappedBy = "publicationsSuivis", fetch = FetchType.EAGER)
    private List<Compte> compteSuiviList;
    
    @ManyToMany(mappedBy = "publicationsSignales", fetch = FetchType.LAZY)
    private List<Compte> CompteSignaleList;
    
    public Publication() {
    }

    public Publication(Integer idPublication) {
        this.idPublication = idPublication;
    }

    public Publication(Integer idPublication, String titre, String type, Date datedecreation, String ville, String categorie, String etat, boolean anonyme) {
        this.idPublication = idPublication;
        this.titre = titre;
        this.type = type;
        this.datedecreation = datedecreation;
        this.ville = ville;
        this.categorie = categorie;
        this.etat = etat;
        this.anonyme = anonyme;
    }

    public Integer getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(Integer idPublication) {
        this.idPublication = idPublication;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatedecreation() {
        String dc = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(datedecreation);
        return dc;
    }

    public void setDatedecreation(Date datedecreation) {
        this.datedecreation = datedecreation;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getAnonyme() {
        return anonyme;
    }

    public void setAnonyme(boolean anonyme) {
        this.anonyme = anonyme;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    
    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    @XmlTransient
    public List<Commentaire> getCommentaireList() {
        return commentaireList;
    }

    public void setCommentaireList(List<Commentaire> commentaireList) {
        this.commentaireList = commentaireList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublication != null ? idPublication.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        if ((this.idPublication == null && other.idPublication != null) || (this.idPublication != null && !this.idPublication.equals(other.idPublication))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beans.Publication[ idPublication=" + idPublication + " ]";
    }

    @XmlTransient
    public List<Compte> getCompteSuiviList() {
        return compteSuiviList;
    }

    public void setCompteSuiviList(List<Compte> compteSuiviList) {
        this.compteSuiviList = compteSuiviList;
    }

}
