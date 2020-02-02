/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.LocationPoint;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.StructureTypeEnum;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author tenbenites
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_STRUCTURE")
public class StructuralItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    @Column //Descrição do local, TODO verificar se é necessário registrar lat e long
    private String place;
    @Column //Descrição da função do item estrutural
    private String str_function;
    @Column //Descrição da função do item estrutural
    private String description;
    @Column
    @Enumerated(EnumType.STRING)
    private StructureTypeEnum structureType;
    //qual unidade militar dona do item
    @ManyToOne
    private MilitaryUnit mu_owner;

    @Column
    private String extra_observation;
    
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate last_update;
    
    @OneToOne(cascade={CascadeType.ALL})
    private LocationPoint locationPoint;

    //qual militar alterou o item pela última vez
    @ManyToOne
    private Military mil_modifier;
    
    public String getExtra_observation() {
        return extra_observation;
    }

    public void setExtra_observation(String extra_observation) {
        this.extra_observation = extra_observation;
    }

    public LocalDate getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDate last_update) {
        this.last_update = last_update;
    }

    public Military getMil_modifier() {
        return mil_modifier;
    }

    public void setMil_modifier(Military mil_modifier) {
        this.mil_modifier = mil_modifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StructureTypeEnum getStructureType() {
        return structureType;
    }

    public void setStructureType(StructureTypeEnum structureType) {
        this.structureType = structureType;
    }
    
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStr_function() {
        return str_function;
    }

    public void setStr_function(String str_function) {
        this.str_function = str_function;
    }

    public MilitaryUnit getMu_owner() {
        return mu_owner;
    }

    public void setMu_owner(MilitaryUnit mu_owner) {
        this.mu_owner = mu_owner;
    }

    public LocationPoint getLocationPoint() {
        return locationPoint;
    }

    public void setLocationPoint(LocationPoint locationPoint) {
        this.locationPoint = locationPoint;
    }

    @Override
    public String toString() {
        return "StructuralItem{" + "id=" + id + ", name=" + name + ", place=" + place + ", function=" + str_function + ", description=" + description + ", mu_owner=" + mu_owner + ", extra_observation=" + extra_observation + ", last_update=" + last_update + ", locationPoint=" + locationPoint + ", mil_modifier=" + mil_modifier + '}';
    }

    
   
}
