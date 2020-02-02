/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import org.springframework.ui.ModelMap;

/**
 * Reúne funções comuns em diferentes controllers para composição de páginas. 
 * @author tenbenites
 */
public class FragmentController {
    public static ModelMap checkMUMenu (MilitaryUnit mu, ModelMap model){
        //abre a página de gerenciamento restrita a PEFs e Pelotões
        if(MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type())||MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())){
            model.addAttribute("subMenu", "mu_management/subMenuPEL");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nAdicionou menu para pelotão");
        }else{        
            model.addAttribute("subMenu", "mu_management/subMenuMU");
        }
        model.addAttribute("content", "mu_management/subLayout");
        return model;
    }
}
