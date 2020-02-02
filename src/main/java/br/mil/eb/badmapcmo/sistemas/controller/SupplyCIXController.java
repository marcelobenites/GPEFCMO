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
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.OutboardEngine;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.Vehicle;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.Watercraft;
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
 * Controla o CRUD de itens de suprimento Classe IX, outros procedimentos de
 * listar, preUpdate estão no controlador geral de itens de suprimento.
 * @see SupplyItemController
 * @author tenbenites
 */
@Controller
@RequestMapping("supplyCIXControl")
public class SupplyCIXController {
    @Autowired
    private SupplyDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;
    
    /**Salva um item de viatura*/    
    @RequestMapping(value = "/saveSupplyItemCIX1", method = RequestMethod.POST)
    public ModelAndView saveSupplyItemCIX1(@Valid @ModelAttribute("v") Vehicle v, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(v.getMu_owner().getId());
        v.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX1.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        v.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        v.setMil_modifier(currentUser.getUser().getMilitary());
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENTROU SALVAMENTO"+v.getMil_modifier().toString()+" \n\n\n\n\n\n\n");
        dao.saveSCIX1(v);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    
    /**Efetiva a atualização de viatura*/
    @PostMapping("/updateSupplyItemCIX1")
    public ModelAndView updateSupplyItemCIX1(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, RedirectAttributes attr, ModelMap model) {
        System.out.println("\n\n\n\n\nEntrou no update viatura: "+vehicle.toString());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",vehicle.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX1.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        vehicle.setMil_modifier(currentUser.getUser().getMilitary());
        
        vehicle.setLast_update(LocalDate.now());
        dao.updateSIX1(vehicle);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    
    /**Salva um item de embarcação*/    
    @RequestMapping(value = "/saveSupplyItemCIX2", method = RequestMethod.POST)
    public ModelAndView saveSupplyItemCIX2(@Valid @ModelAttribute("wc") Watercraft wc, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(wc.getMu_owner().getId());
        wc.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX2.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        wc.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        wc.setMil_modifier(currentUser.getUser().getMilitary());
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENTROU SALVAMENTO"+wc.getMil_modifier().toString()+" \n\n\n\n\n\n\n");
        dao.saveSCIX2(wc);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    
    /**Efetiva a atualização de embarcação*/
    @PostMapping("/updateSupplyItemCIX2")
    public ModelAndView updateSupplyItemCIX2(@Valid @ModelAttribute("watercraft") Watercraft watercraft, BindingResult result, RedirectAttributes attr, ModelMap model) {

        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",watercraft.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX2.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        watercraft.setMil_modifier(currentUser.getUser().getMilitary());

        watercraft.setLast_update(LocalDate.now());
        dao.updateSIX2(watercraft);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    /**Salva um item de embarcação*/    
    @RequestMapping(value = "/saveSupplyItemCIX3", method = RequestMethod.POST)
    public ModelAndView saveSupplyItemCIX3(@Valid @ModelAttribute("oe") OutboardEngine oe, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(oe.getMu_owner().getId());
        oe.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX3.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        oe.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        oe.setMil_modifier(currentUser.getUser().getMilitary());
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENTROU SALVAMENTO"+oe.getMil_modifier().toString()+" \n\n\n\n\n\n\n");
        dao.saveSCIX3(oe);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    
    /**Efetiva a atualização de embarcação*/
    @PostMapping("/updateSupplyItemCIX3")
    public ModelAndView updateSupplyItemCIX3(@Valid @ModelAttribute("oe") OutboardEngine oe, BindingResult result, RedirectAttributes attr, ModelMap model) {

        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",oe.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX3.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        oe.setMil_modifier(currentUser.getUser().getMilitary());

        oe.setLast_update(LocalDate.now());
        dao.updateSIX3(oe);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }

}