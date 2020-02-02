/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.PELDST;

import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

/**
 * Classe para registro de efetivos adicionais de outras organizações
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_MU_ADDITIONAL_STAFF")
public class MUAdditionalStaff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String organization_name;
    
    @Column
    private String description;    
    
    @ManyToOne()
    private MilitaryUnit related_mu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MilitaryUnit getRelated_mu() {
        return related_mu;
    }

    public void setRelated_mu(MilitaryUnit related_mu) {
        
        System.out.println("\n\n\n\n\n\n Setando OM");
        this.related_mu = related_mu;
    }

    @Override
    public String toString() {
        return "MUAdditionalStaff{" + "id=" + id + ", organization_name=" + organization_name + ", description=" + description + ", related_mu=" + related_mu + '}';
    }
        
}
