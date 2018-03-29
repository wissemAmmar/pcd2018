/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
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
    @NamedQuery(name = "Publication.findbyCompte", query = "SELECT p FROM Publication p where p.idCompte=:compte order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findAllAfterId", query = "SELECT p FROM Publication p where p.idPublication<:idDerniere order by p.idPublication desc"),
    @NamedQuery(name = "Publication.findbyCompteAfterId", query = "SELECT p FROM Publication p where p.idCompte=:compte and p.idPublication<:idDerniere order by p.idPublication desc")})
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
    @Size(max = 45)
    @Column(name = "Lieu")
    private String lieu = "L";
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Temps")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date_Temps = Timestamp.from(Instant.now());
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Categorie")
    private String categorie = "C";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Etat")
    private String etat = "non résolu";
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nb_Signal")
    private int nbSignal;
    @Size(max = 1000)
    @Column(name = "Exprimer")
    private String exprimer = " ";
    @Basic(optional = false)
    @NotNull
    @Column(name = "Anonyme")
    private boolean anonyme = false;
    @JoinColumn(name = "IdCompte", referencedColumnName = "IdCompte")
    @ManyToOne(optional = false)
    private Compte idCompte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPublication", fetch = FetchType.EAGER)
    @OrderBy("idCommentaire desc")
    private List<Commentaire> commentaireList;

    public Publication() {
    }

    public Publication(Integer idPublication) {
        this.idPublication = idPublication;
    }

    public Publication(Integer idPublication, String titre, String type, Timestamp date_Temps, String categorie, String etat, int nbSignal) {
        this.idPublication = idPublication;
        this.titre = titre;
        this.type = type;
        this.date_Temps = date_Temps;
        this.categorie = categorie;
        this.etat = etat;
        this.nbSignal = nbSignal;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate_Temps() {
        String dt = date_Temps.toString().substring(0, date_Temps.toString().lastIndexOf('.'));
        return dt;
    }

    public void setDate_Temps(Timestamp date_Temps) {
        this.date_Temps = date_Temps;
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

    public int getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(int nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getExprimer() {
        return exprimer;
    }

    public void setExprimer(String exprimer) {
        this.exprimer = exprimer;
    }

    public Compte getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Compte idCompte) {
        this.idCompte = idCompte;
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

    public boolean getAnonyme() {
        return anonyme;
    }

    public void setAnonyme(boolean anonyme) {
        this.anonyme = anonyme;
    }
    
}