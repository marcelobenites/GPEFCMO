/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.domain.auxiliary;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Transient;

/**
 * Classe transiente para auxiliar no registro de dependências entre unidades na 
 * entidade MUDependencies
 * @see MUDependencies
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */

public class MUDeps implements Serializable{
    //tipo de dependencia que pode ocorrer entre duas unidades, recebido de um enum
    String mu_dep;
    //id da unidade que se relaciona com a unidade em adição/edição
    Long mu_id;

    public String getMu_dep() {
        return mu_dep;
    }

    public void setMu_dep(String mu_dep) {
        this.mu_dep = mu_dep;
    }

    public Long getMu_id() {
        return mu_id;
    }

    public void setMu_id(Long mu_id) {
        this.mu_id = mu_id;
    }
    

    @Override
    public String toString() {
        return "MUDeps{" + "mu_dep=" + mu_dep + ", mu_id=" + mu_id + '}';
    }

    
}
