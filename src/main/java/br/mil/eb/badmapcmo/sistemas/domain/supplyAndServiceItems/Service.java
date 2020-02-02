/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems;

import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ServiceCatalogEnum;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
 * Cadastro de serviços.
 * @author tenbenites
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_SERVICE")
public class Service implements Serializable {
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate last_update;
    
    //qual unidade militar dona do item
    @ManyToOne
    private MilitaryUnit mu_owner;
    
    //qual militar alterou o item pela última vez
    @ManyToOne
    private Military mil_modifier;
    
    @Column
    @Enumerated(EnumType.STRING)
    private ServiceCatalogEnum serviceType;

    public MilitaryUnit getMu_owner() {
        return mu_owner;
    }

    public void setMu_owner(MilitaryUnit mu_owner) {
        this.mu_owner = mu_owner;
    }

    
    
    public ServiceCatalogEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceCatalogEnum serviceType) {
        this.serviceType = serviceType;
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

    public Military getMil_modifier() {
        return mil_modifier;
    }

    public void setMil_modifier(Military mil_modifier) {
        this.mil_modifier = mil_modifier;
    }

   

    public Service() {
        this.serviceType = ServiceCatalogEnum.SVC1;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", name=" + name + ", description=" + description + ", extra_observation=" + extra_observation + ", last_update=" + last_update + ", mu_owner=" + mu_owner + ", mil_modifier=" + mil_modifier + ", serviceType=" + serviceType + '}';
    }

    

   
}
