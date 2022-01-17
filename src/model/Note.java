/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author loussin
 */
public class Note {

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", sessionId=" + sessionId + ", valeur=" + valeur + ", isDevoir=" + isDevoir + '}';
    }
    private Integer id;
    private Session session;
    private Integer sessionId;
    private Double valeur;
    private Boolean isDevoir=false;

    public Note() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Boolean isDevoir() {
        return isDevoir;
    }
    
    public Boolean getIsDevoir() {
        return isDevoir;
    }

    public void setIsDevoir(Boolean isDevoir) {
        this.isDevoir = isDevoir;
    }
    
    
    
}
