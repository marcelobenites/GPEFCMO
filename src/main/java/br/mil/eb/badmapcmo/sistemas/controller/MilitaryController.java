/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MUDependenciesDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.UserDao;
import br.mil.eb.badmapcmo.sistemas.dao.ZoneDao;
import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import br.mil.eb.badmapcmo.sistemas.domain.MUInfo;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.User;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.PostGradEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SexoEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.StatesEnum;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 * Controller de entidades do tipo militar
 * @see Military.java
 * @author tenbenites
 */

@ControllerAdvice
@RequestMapping("militaryControl")
public class MilitaryController {
    @Autowired
    private MilitaryDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;
   
    @Autowired
    private MUDependenciesDao muDepDao;
    
    @Autowired
    private UserDao uDao;
    
    
    @Autowired
    private ZoneDao zoneDao;
    
    @ModelAttribute("sexos")
    public SexoEnum[] sexoEnum(){
            return SexoEnum.values();
    }

    @ModelAttribute("postgrads")
    public PostGradEnum[] postoGradEnum(){
            return PostGradEnum.values();
    }
    
    @ModelAttribute("zStates")
    public StatesEnum[] statesEnum(){
            return StatesEnum.values();
    }
     
        
    /**Chama tela com form para atualização*/
    @GetMapping("/listMilitaryForMU/{id}")//TODO atualizar o nome do método no html
    public ModelAndView listMilitaryForMU(@PathVariable("id") Long id, @ModelAttribute("militaryList") Military militaryList, ModelMap model) {
        //DAO já busca por militares das unidades subordinadas
        MilitaryUnit mu = muDao.findById(id);
        List<MilitaryUnit>lMU = muDepDao.getSubordinatedById(id);
        List<Military>lMIL = dao.getAllForMU(id);
        for(int i = 0 ; i < lMU.size(); i++){
                    lMIL.addAll(dao.getAllForMU(lMU.get(i).getId()));
        }
        model.addAttribute("militaryList", dao.getAllForMU(id));
        model.addAttribute("militaryUnit", mu);
        System.out.println("Recuperando lista para unidade militar:"+muDao.findById(id).getMU_name());
        model.addAttribute("subContent", "mu_management/listMilitaryPel");
        //Separa os menus por tipo de unidade militar
        if(MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type())||MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())){
            model.addAttribute("subMenu", "mu_management/subMenuPEL");       
        }else{        
            model.addAttribute("subMenu", "mu_management/subMenuMU");
        }
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model); 
    }
    
    
     /**Chama a tela de cadastro para uma nova informação*/
    @GetMapping("/registerMilitaryForMU/{id}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView registerMilitaryForMU(@ModelAttribute("id") Long id, ModelMap model) {            
                   
                
        MilitaryUnit militaryUnit = muDao.findById(id);
        Military military = new Military();
        military.setMilitaryUnit(militaryUnit);
        model.addAttribute("military", military);
        model.addAttribute("militaryUnit", militaryUnit);
        model.addAttribute("zones", zoneDao.getAll());
        
        model.addAttribute("subContent", "mu_management/addMilitary");
        model.addAttribute("content", "mu_management/subLayout");
        System.out.println("\n\n\n\n\nEntrou no registro de novo militar, unidade:"+militaryUnit.getMU_name()); 
        return new ModelAndView("layout", FragmentController.checkMUMenu(militaryUnit, model));           
    }
    
    /**Lista todos os usuários por classe*/
    @GetMapping("/listMilitaryByName/{id}")//TODO organizar todas as filtragens para respeitarem as dependencias entre unidades
    public ModelAndView listMilitaryByName(@RequestParam(value = "name") String name, @PathVariable("id") Long id, RedirectAttributes attr, ModelMap model) {
        System.out.println("entrou na busca");
    
        
        if (name == null) {
            attr.addFlashAttribute("message", "Militar não encontrado, tente novamente.");
        }
           
        
        
        model.addAttribute("militaryList", dao.searchByName(name, id));
        model.addAttribute("militaryUnit", muDao.findById(id));
        model.addAttribute("subContent", "mu_management/listMilitaryPel");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model); 
    }
        
    /**Salva um item genérico de infomação*/    
    @PostMapping("/saveMilitary")
    public ModelAndView saveMilitary(@Valid @ModelAttribute("military") Military military, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/militaryControl/listMilitaryForMU/{id}");
        modelAndView.addObject("id",military.getMilitaryUnit().getId());
        //System.out.println("\n\n\n\n\nId da unidade rel:"+muInfo.getRelated_mu().getId());
        //System.out.println("\n\n\nEntrou no salvamenteo para: "+muInfo.toString());
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        MilitaryUnit mu = muDao.findById(military.getMilitaryUnit().getId());
        model.addAttribute("militaryUnit", mu);
        military.setMilitaryUnit(mu);
        dao.save(military);
        return modelAndView;
    }
        
    /**Prepara para atualização de cadastro de militar*/
    @GetMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
	Military m = dao.getById(id);
	model.addAttribute("military", m);
        model.addAttribute("militaryUnit", m.getMilitaryUnit());
        model.addAttribute("subContent", "mu_management/addMilitary");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);
    }
    /**Efetiva uma atualização de unidade militar*/
    @PostMapping("/updateMilitary")
    public ModelAndView update(@Valid @ModelAttribute("military") Military military, BindingResult result, RedirectAttributes attr) {
        ModelAndView modelAndView = new ModelAndView("redirect:/militaryControl/listMilitaryForMU/{id}");
        modelAndView.addObject("id",military.getMilitaryUnit().getId());
        if (result.hasErrors()) {
                    attr.addFlashAttribute("Algo ocorreu!", "Tente novamente.");
                    return modelAndView;
        }
        dao.update(military);
        attr.addFlashAttribute("message", "Cadastro de militar alterado com sucesso.");
        return modelAndView;
    }
    
    /**Exclui uma unidade militar pelo seu id*/
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        //System.out.println("Solicitou exclusão de unidade militar");
        ModelAndView modelAndView = new ModelAndView("redirect:/militaryControl/listMilitaryForMU/{id}");
        Military m = dao.getById(id);
        modelAndView.addObject("id",m.getMilitaryUnit().getId());

        System.out.println("Entrando no if para::"+uDao.getByMilitaryId(id).toString());
        if(!uDao.getByMilitaryId(id).isEmpty()){
            attr.addFlashAttribute("message", "Cadastro vinculado a um usuário não pode ser removido. Remova a conta de usuário antes.");
            return modelAndView;
        }
        dao.delete(id);
        attr.addFlashAttribute("message", "Cadastro removido com sucesso.");
        return modelAndView;
    }
        
    /**Chamada para pagina de detalher os dados cadastrados de um militar*/
    @GetMapping(value="/showSingleProfile/{id}")    
    public ModelAndView showSingleProfile(@PathVariable("id") Long id, ModelMap model) {
        Military military = dao.getById(id);
        model.addAttribute("militaryUnit", military.getMilitaryUnit());
        model.addAttribute("military", military);
        model.addAttribute("subMenu", "mu_management/subMenuPEL"); 
        model.addAttribute("subContent", "mu_management/militaryInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);      
    }
    
    /**Lista unidades militares como resultado de uma busca por nome*/
    @RequestMapping(value = "/findByMUName")
    public ModelAndView findMilitaryByName(@RequestParam(value = "name") String name, @ModelAttribute("id") Long id, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/militaryControl/listMilitaryForMU/{id}");
        modelAndView.addObject("id",id);
        model.addAttribute("military", dao.searchByName(name,id));
        return modelAndView;
    }
    
    
}
