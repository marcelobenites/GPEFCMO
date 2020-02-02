/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SupplyClassEnum;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Item de suprimento (classe mãe), dentro das categorias previstas.
 * @see SupplyClassEnum.java
 * @author tenbenites
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_SUPPLY")
public class SupplyItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
       
    @Column
    @Length(max = 50)
    private String name;
    @Column
    private String description;
    @Column
    private String extra_observation;
    @Column
    private String metric_unit;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate last_update;
    @Column
    @Enumerated(EnumType.STRING)
    private SupplyClassEnum supply_class;
    
    //qual unidade militar dona do item
    @ManyToOne
    private MilitaryUnit mu_owner;
    
    //qual militar alterou o item pela última vez
    @ManyToOne
    private Military mil_modifier;

    public Military getMil_modifier() {
        return mil_modifier;
    }

    public void setMil_modifier(Military mil_modifier) {
        this.mil_modifier = mil_modifier;
    }

        
    public String getMetric_unit() {
        return metric_unit;
    }

    public void setMetric_unit(String metric_unit) {
        this.metric_unit = metric_unit;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public SupplyClassEnum getSupply_class() {
        return supply_class;
    }

    public void setSupply_class(SupplyClassEnum supply_class) {
        this.supply_class = supply_class;
    }

    public MilitaryUnit getMu_owner() {
        return mu_owner;
    }

    public void setMu_owner(MilitaryUnit mu_owner) {
        this.mu_owner = mu_owner;
    }

    @Override
    public String toString() {
        return "SupplyItem{" + "id=" + id + ", name=" + name + ", description=" + description + ", extra_observation=" + extra_observation + ", metric_unit=" + metric_unit + ", last_update=" + last_update + ", supply_class=" + supply_class + ", mu_owner=" + mu_owner + ", mil_modifier=" + mil_modifier + '}';
    }    
}
