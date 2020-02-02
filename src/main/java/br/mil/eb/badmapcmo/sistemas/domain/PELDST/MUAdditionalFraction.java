/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.PELDST;

import br.mil.eb.badmapcmo.sistemas.domain.Address;
import br.mil.eb.badmapcmo.sistemas.domain.PELDST.*;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

/**
 * Classe para registro de efetivos adicionais de outras organizações
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_MU_ADDITIONAL_FRACTION")
public class MUAdditionalFraction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer total_staff;
        
    @Column
    private String fraction_name;    
 
    @Column
    @Length(max = 50)
    private String fraction_tel;
    
    @OneToOne
    private Address address;
    
    
    @ManyToOne()
    private MilitaryUnit related_mu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal_staff() {
        return total_staff;
    }

    public void setTotal_staff(Integer total_staff) {
        this.total_staff = total_staff;
    }

    public String getFraction_name() {
        return fraction_name;
    }

    public void setFraction_name(String fraction_name) {
        this.fraction_name = fraction_name;
    }

    public String getFraction_tel() {
        return fraction_tel;
    }

    public void setFraction_tel(String fraction_tel) {
        this.fraction_tel = fraction_tel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public MilitaryUnit getRelated_mu() {
        return related_mu;
    }

    public void setRelated_mu(MilitaryUnit related_mu) {
        this.related_mu = related_mu;
    }

    @Override
    public String toString() {
        return "MUAdditionalFraction{" + "id=" + id + ", total_staff=" + total_staff + ", fraction_name=" + fraction_name + ", fraction_tel=" + fraction_tel + ", address=" + address + ", related_mu=" + related_mu + '}';
    }
       
}
