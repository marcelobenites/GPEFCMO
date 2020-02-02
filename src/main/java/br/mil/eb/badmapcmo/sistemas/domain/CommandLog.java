/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ten-benites
 */
@Entity
@Table(name = "TBL_COMMAND_LOG")
public class CommandLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    @ManyToOne
    private MilitaryUnit militaryUnit;
    
    
    @OneToOne
    private Commander MU_cmd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public Commander getMU_cmd() {
        return MU_cmd;
    }

    public void setMU_cmd(Commander MU_cmd) {
        this.MU_cmd = MU_cmd;
    }

    @Override
    public String toString() {
        return "CommandLog{" + "id=" + id + ", militaryUnit=" + militaryUnit + ", MU_cmd=" + MU_cmd + '}';
    }
    
    
    
    
    
    
}
