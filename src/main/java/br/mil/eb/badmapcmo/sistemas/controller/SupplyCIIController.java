/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.SupplyDao;
import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SupplyClassEnum;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.SupplyItemCII;
import java.time.LocalDate;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tenbenites
 */
@Controller//TODO migrar controlador
@RequestMapping("supplyC2Control")
public class SupplyCIIController {
    @Autowired
    private SupplyDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;
    
     /**Salva um item genérico de classe II*/    
    @RequestMapping(value = "/saveSupplyC2", method = RequestMethod.POST)
    public ModelAndView saveSupplyC1(@Valid @ModelAttribute("supplyItemC2") SupplyItemCII supplyItemC2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        MilitaryUnit mu = muDao.findById(supplyItemC2.getMu_owner().getId());
        supplyItemC2.setMu_owner(mu);
        System.out.println("\n\n\n\n\n\n\n\n Military Unit:"+supplyItemC2.getMu_owner());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSII);
//        modelAndView.addObject("id",supplyItemC2.getMu_owner().getId());
//        modelAndView.addObject("supClass",supplyItemC2.getSupply_class().getDescription());
//        //System.out.println("\n\n\n\n\nId da unidade rel:"+muInfo.getRelated_mu().getId());
//        System.out.println("\n\n\nEntrou no salvamenteo para: "+supplyItemC2.toString());
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemC2.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemC2.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCII(supplyItemC2);
        System.out.println("ENTROU, SUPPLYITEM: "+supplyItemC2.toString());
        return modelAndView;
    }
    
    
    
    /**Efetiva a atualização*/
    @PostMapping("/updateSupplyC2")
    public ModelAndView updateSupplyC2(@Valid @ModelAttribute("supplyItemC2") SupplyItemCII supplyItemC2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemC2.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSII);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemC2.setMil_modifier(currentUser.getUser().getMilitary());
        
        supplyItemC2.setLast_update(LocalDate.now());
        dao.updateSCII(supplyItemC2);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
  
    
}
