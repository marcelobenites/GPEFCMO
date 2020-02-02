/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain.auxiliary;

/**
 * Classe auxiliar não persistida no banco que auxilia na aplicação de filtros
 * em listagens de diferentes tipos de elementos do domínio.
 * @author tenbenites
 */
public class FilterList {
    private Integer option;

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }
    
    
}
