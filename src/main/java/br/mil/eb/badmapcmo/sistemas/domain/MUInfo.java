/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.domain;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.InfoTypeEnum;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe para registro de informações sobre as unidades militares 
 * Estas informações podem ser de diferentes tipos conforme classificado em InfoTypeEnum
 * @see InfoTypeEnum
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_MU_INFO_ITEMS")
public class MUInfo implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;
    
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate creationDate;
     
    @ManyToOne()
    private Military military_author;
    
    @ManyToOne()
    private MilitaryUnit related_mu;
    
    @Column()
    @Enumerated(EnumType.STRING)
    private InfoTypeEnum info_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Military getMilitary_author() {
        return military_author;
    }

    public void setMilitary_author(Military military_author) {
        this.military_author = military_author;
    }

    public MilitaryUnit getRelated_mu() {
        return related_mu;
    }

    public void setRelated_mu(MilitaryUnit related_mu) {
        this.related_mu = related_mu;
    }

    public InfoTypeEnum getInfo_type() {
        return info_type;
    }

    public void setInfo_type(InfoTypeEnum info_type) {
        this.info_type = info_type;
    }

    @Override
    public String toString() {
        return "MUInfo{" + "id=" + id + ", title=" + title + ", body=" + body + ", creationDate=" + creationDate + ", military_author=" + military_author + ", related_mu=" + related_mu + ", info_type=" + info_type + '}';
    }    
    
}
