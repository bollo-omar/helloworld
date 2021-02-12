/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "bhuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bhuser.findAll", query = "SELECT b FROM Bhuser b"),
    @NamedQuery(name = "Bhuser.findByBhuserid", query = "SELECT b FROM Bhuser b WHERE b.bhuserid = :bhuserid"),
    @NamedQuery(name = "Bhuser.findByUsername", query = "SELECT b FROM Bhuser b WHERE b.username = :username"),
    @NamedQuery(name = "Bhuser.findByUserpassword", query = "SELECT b FROM Bhuser b WHERE b.userpassword = :userpassword"),
    @NamedQuery(name = "Bhuser.findByMotto", query = "SELECT b FROM Bhuser b WHERE b.motto = :motto"),
    @NamedQuery(name = "Bhuser.findByUseremail", query = "SELECT b FROM Bhuser b WHERE b.useremail = :useremail"),
    @NamedQuery(name = "Bhuser.findByJoindate", query = "SELECT b FROM Bhuser b WHERE b.joindate = :joindate")})
public class Bhuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BHUSERID")
    private Integer bhuserid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 50)
    @Column(name = "USERPASSWORD")
    private String userpassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MOTTO")
    private String motto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USEREMAIL")
    private String useremail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JOINDATE")
    @Temporal(TemporalType.DATE)
    private Date joindate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bhuserid")
    private Collection<Bhpost> bhpostCollection;

    public Bhuser() {
    }

    public Bhuser(Integer bhuserid) {
        this.bhuserid = bhuserid;
    }

    public Bhuser(Integer bhuserid, String username, String motto, String useremail, Date joindate) {
        this.bhuserid = bhuserid;
        this.username = username;
        this.motto = motto;
        this.useremail = useremail;
        this.joindate = joindate;
    }

    public Integer getBhuserid() {
        return bhuserid;
    }

    public void setBhuserid(Integer bhuserid) {
        this.bhuserid = bhuserid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    @XmlTransient
    public Collection<Bhpost> getBhpostCollection() {
        return bhpostCollection;
    }

    public void setBhpostCollection(Collection<Bhpost> bhpostCollection) {
        this.bhpostCollection = bhpostCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bhuserid != null ? bhuserid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bhuser)) {
            return false;
        }
        Bhuser other = (Bhuser) object;
        if ((this.bhuserid == null && other.bhuserid != null) || (this.bhuserid != null && !this.bhuserid.equals(other.bhuserid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Bhuser[ bhuserid=" + bhuserid + " ]";
    }
    
}
