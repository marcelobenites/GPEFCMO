/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.PELDST;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe para registro do efetivo atual em cada PEF
 * @author ten-benites
 */
@Entity
@Table(name = "TBL_MU_STAFF")
public class MUStaff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Column//efetivo previsto de oficiais
    private Integer expected_officers;
    @Column//efetivo existente de oficiais    
    private Integer current_officers;
    
    @Column//efetivo previsto de st e sgt
    private Integer expected_st_sgt;
    @Column//efetivo existente de st e sgt   
    private Integer current_st_sgt;
    
    @Column//efetivo previsto de cabos
    private Integer expected_cb;
    @Column//efetivo existente de cabos    
    private Integer current_cb;
    
    @Column//efetivo previsto de soldados
    private Integer expected_soldiers;
    @Column//efetivo existente de soldados    
    private Integer current_soldiers;
    
    @Column//quantidade familiares adultos (>18anos)
    private Integer adult_relatives_amount;
    @Column//quantidade familiares menores (<18anos)    
    private Integer children_amount;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExpected_officers() {
        return expected_officers;
    }

    public void setExpected_officers(Integer expected_officers) {
        this.expected_officers = expected_officers;
    }

    public Integer getCurrent_officers() {
        return current_officers;
    }

    public void setCurrent_officers(Integer current_officers) {
        this.current_officers = current_officers;
    }

    public Integer getExpected_st_sgt() {
        return expected_st_sgt;
    }

    public void setExpected_st_sgt(Integer expected_st_sgt) {
        this.expected_st_sgt = expected_st_sgt;
    }

    public Integer getCurrent_st_sgt() {
        return current_st_sgt;
    }

    public void setCurrent_st_sgt(Integer current_st_sgt) {
        this.current_st_sgt = current_st_sgt;
    }

    public Integer getExpected_cb() {
        return expected_cb;
    }

    public void setExpected_cb(Integer expected_cb) {
        this.expected_cb = expected_cb;
    }

    public Integer getCurrent_cb() {
        return current_cb;
    }

    public void setCurrent_cb(Integer current_cb) {
        this.current_cb = current_cb;
    }

    public Integer getExpected_soldiers() {
        return expected_soldiers;
    }

    public void setExpected_soldiers(Integer expected_soldiers) {
        this.expected_soldiers = expected_soldiers;
    }

    public Integer getCurrent_soldiers() {
        return current_soldiers;
    }

    public void setCurrent_soldiers(Integer current_soldiers) {
        this.current_soldiers = current_soldiers;
    }

    public Integer getAdult_relatives_amount() {
        return adult_relatives_amount;
    }

    public void setAdult_relatives_amount(Integer adult_relatives_amount) {
        this.adult_relatives_amount = adult_relatives_amount;
    }

    public Integer getChildren_amount() {
        return children_amount;
    }

    public void setChildren_amount(Integer children_amount) {
        this.children_amount = children_amount;
    }

    @Override
    public String toString() {
        return "MUStaff{" + "id=" + id + ", expected_officers=" + expected_officers + ", current_officers=" + current_officers + ", expected_st_sgt=" + expected_st_sgt + ", current_st_sgt=" + current_st_sgt + ", expected_cb=" + expected_cb + ", current_cb=" + current_cb + ", expected_soldiers=" + expected_soldiers + ", current_soldiers=" + current_soldiers + ", adult_relatives_amount=" + adult_relatives_amount + ", children_amount=" + children_amount + '}';
    }

    
    
    
}
