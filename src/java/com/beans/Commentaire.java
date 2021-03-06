/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wissems
 */
@Entity
@Table(name = "commentaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c order by c.idCommentaire desc")})
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCommentaire")
    private Integer idCommentaire;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "Texte")
    private String texte;
    @JoinColumn(name = "Compte", referencedColumnName = "IdCompte")
    @ManyToOne(optional = false)
    private Compte compte;
    @JoinColumn(name = "Publication", referencedColumnName = "IdPublication")
    @ManyToOne(optional = false)
    private Publication publication;

    public Commentaire() {
    }

    public Commentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Commentaire(Integer idCommentaire, String texte) {
        this.idCommentaire = idCommentaire;
        this.texte = texte;
    }

    public Integer getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommentaire != null ? idCommentaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.idCommentaire == null && other.idCommentaire != null) || (this.idCommentaire != null && !this.idCommentaire.equals(other.idCommentaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.beans.Commentaire[ idCommentaire=" + idCommentaire + " ]";
    }
    
}
