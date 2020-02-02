/*
 * Desenvolvido pela Seção de Tecnologia da Informação - B ADM AP/CMO
 * Autor: 2º TEN Benites
 */
package br.mil.eb.badmapcmo.sistemas.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Endereço para cadastro de militares e unidades.
 * @author benites 
 */

@Entity
@Table(name = "TBL_ADDRESS")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
   
    @Column
    @Length(max = 50)
    private String street;
    @Column
    private Integer number;
    @Column
    @Length(max = 50)
    private String neighborhood;
    @Column
    @Length(max = 50)
    private String complement;
    @Column
    private Integer postal_code;
    @Column
    private Integer latitude;
    @Column
    private Integer longitude;
    /*Foreign keys*/
    
   
    @ManyToOne
    private Zone zone;    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Integer getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(Integer postal_code) {
        this.postal_code = postal_code;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

        
    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", number=" + number + ", neighborhood=" + neighborhood + ", complement=" + complement + ", postal_code=" + postal_code + ", latitude=" + latitude + ", longitude=" + longitude + ", zone=" + zone + '}';
    }

    
    
       
}
