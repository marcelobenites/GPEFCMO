/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.CommandCategoryEnum;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author tenbenites
 */
@Entity
@Table(name = "TBL_COMMANDER")
public class Commander implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String training_group;
    
    @Column//data de praça
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate enlist;
    
    @Column//data de entrada no comando
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate entrance;
    
    @Column//data de saída no comando
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate exit_date;

    @Column//data prevista de saída no comando
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate estimated_exit;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private Military military;
    
    @ManyToOne//outorgado ("padrinho") na sede
    private Military holder;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private CommandCategoryEnum role;

    public CommandCategoryEnum getRole() {
        return role;
    }

    public void setRole(CommandCategoryEnum role) {
        this.role = role;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraining_group() {
        return training_group;
    }

    public void setTraining_group(String training_group) {
        this.training_group = training_group;
    }

    public LocalDate getEnlist() {
        return enlist;
    }

    public void setEnlist(LocalDate enlist) {
        this.enlist = enlist;
    }

    public LocalDate getEntrance() {
        return entrance;
    }

    public void setEntrance(LocalDate entrance) {
        this.entrance = entrance;
    }

    public LocalDate getExit_date() {
        return exit_date;
    }

    public void setExit_date(LocalDate exit_date) {
        this.exit_date = exit_date;
    }

    public LocalDate getEstimated_exit() {
        return estimated_exit;
    }

    public void setEstimated_exit(LocalDate estimated_exit) {
        this.estimated_exit = estimated_exit;
    }

    public Military getMilitary() {
        return military;
    }

    public void setMilitary(Military military) {
        this.military = military;
    }

    public Military getHolder() {
        return holder;
    }

    public void setHolder(Military holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Commander{" + "id=" + id + ", training_group=" + training_group + ", enlist=" + enlist + ", entrance=" + entrance + ", exit_date=" + exit_date + ", estimated_exit=" + estimated_exit + ", military=" + military + ", holder=" + holder + ", role=" + role + '}';
    }
       
}
