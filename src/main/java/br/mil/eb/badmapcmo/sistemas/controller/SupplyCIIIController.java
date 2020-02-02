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
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.SupplyItemCIII1_LPG;
import java.time.LocalDate;
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
 * Controla o CRUD de itens de suprimento Classe III, outros procedimentos de
 * listar, preUpdate estão no controlador geral de itens de suprimento.
 * @see SupplyItemController
 * @author tenbenites
 */
@Controller
@RequestMapping("supplyCIIIControl")
public class SupplyCIIIController {
    @Autowired
    private SupplyDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;
    
    /**Salva um item de gás de cozinha*/    
    @RequestMapping(value = "/saveSupplyItemCIII1", method = RequestMethod.POST)
    public ModelAndView saveSupplyItemCIII1(@Valid @ModelAttribute("lpg") SupplyItemCIII1_LPG lpg, BindingResult result, RedirectAttributes attr, ModelMap model) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENTROU SALVAMENTO"+lpg+" \n\n\n\n\n\n\n");
        MilitaryUnit mu = muDao.findById(lpg.getMu_owner().getId());
        lpg.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII1.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        lpg.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        lpg.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCIII1(lpg);
        return modelAndView;
    }
    
    /**Efetiva a atualização*/
    @PostMapping("/updateSupplyItemCIII1")
    public ModelAndView updateSupplyItemCIII1(@Valid @ModelAttribute("lpg") SupplyItemCIII1_LPG lpg, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update viatura: "+lpg.toString());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",lpg.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII1.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        lpg.setMil_modifier(currentUser.getUser().getMilitary());
        
        lpg.setLast_update(LocalDate.now());
        dao.updateSCIII1(lpg);
        attr.addFlashAttribute("message", "Cadastro de gás de cozinha alterado com sucesso.");
        return modelAndView;
    }
    
    
}
