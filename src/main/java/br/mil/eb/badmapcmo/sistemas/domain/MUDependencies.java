/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.domain;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.DependencyTypeEnum;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Representa tabela intermediária que mapeia as dependencias entre unidades.
 * @author benites
 */
@Entity
@Table(name = "TBL_MU_DEPENDENCIES")
public class MUDependencies implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Boolean Dep_type;//"0" para ligação indireta e "1" para direta
    
    /*Foreign keys - ambas referenciam a tabela de unidademilitar*/
    @ManyToOne
    private MilitaryUnit superiorUnit;
    @ManyToOne
    private MilitaryUnit subordinatedUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**Dada uma dependencia, troca a posição entre as unidades subordinada/superior */
    public void switchMUs(){
        MilitaryUnit aux;
        aux = this.superiorUnit;
        this.superiorUnit = this.subordinatedUnit;
        this.subordinatedUnit = aux;    
    }
    
    public MilitaryUnit getSuperiorUnit() {
        return superiorUnit;
    }

    public void setSuperiorUnit(MilitaryUnit superiorUnit) {
        this.superiorUnit = superiorUnit;
    }

    public MilitaryUnit getSubordinatedUnit() {
        return subordinatedUnit;
    }

    public void setSubordinatedUnit(MilitaryUnit subordinatedUnit) {
        this.subordinatedUnit = subordinatedUnit;
    }

    public Boolean getDep_type() {
        return Dep_type;
    }

    public void setDep_type(Boolean Dep_type) {
        this.Dep_type = Dep_type;
    }

    public MUDependencies() {
    }

    @Override
    public String toString() {
        return "MUDependencies{" + "id=" + id + ", Dep_type=" + Dep_type + ", superiorUnit=" + superiorUnit + ", subordinatedUnit=" + subordinatedUnit + '}';
    }

}
