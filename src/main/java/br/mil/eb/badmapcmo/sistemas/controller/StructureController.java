/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.StructureDao;
import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.*;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.*;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controla operações sobre itens de estrutura (instalações), independente de 
 * classe, operações de CRUD são feitas individualmente por tipo de item de 
 * estrutura, pois, dependendo das regras de negócio, podem ser necessários 
 * tratamentos específicos de dados dentro dos métodos de cotrole.
 * @author tenbenites
 */
@Controller
@RequestMapping("structureControl")
public class StructureController {
    @Autowired
    private StructureDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;
    
    @ModelAttribute("PNRTypes")
    public PNRTypeEnum[] PNRTypeEnum(){
            return PNRTypeEnum.values();
    }
    @ModelAttribute("PNRCategories")
    public PNRCategoryEnum[] PNRCategoryEnum(){
            return PNRCategoryEnum.values();
    }
    @ModelAttribute("PNRKinds")
    public PNRKindEnum[] PNRKindEnum(){
            return PNRKindEnum.values();
    }
    @ModelAttribute("PNRAvailabilities")
    public GeneralAvailabilityEnum[] GeneralAvailabilityEnum(){
            GeneralAvailabilityEnum[] GA = {GeneralAvailabilityEnum.FREE,GeneralAvailabilityEnum.OCCUPIED};
            return GA;
    }
    
    //MÉTODOS GERAIS PARA TODAS AS ESTRUTURAS
       
    //TODO melhorar a passagem de parametro enviando o enum direto para o controller e retirando esse método
    private StructureTypeEnum testType(String testClass){
        System.out.println("\n\n\n\n\n\nTESTANDO STRUCTURE: "+testClass);       
        if(testClass.contains("PNR")){
            return StructureTypeEnum.TYPE1;}
        if(testClass.contains("Estocagem")){
            return StructureTypeEnum.TYPE2;}
        if(testClass.contains("Sala de Armas")){
            return StructureTypeEnum.TYPE3;}
        if(testClass.contains("Outras Instalações")){
            return StructureTypeEnum.TYPE4;}
        
        System.out.println("Saiu sem tipo definido");
        return null;
    } 
    
    
    /**Lista itens de suprimento de acordo com a classe de suprimento fornecida na chamada.*/
    @RequestMapping(value = "/listStructuralItems/{id}/{structureType}", method=RequestMethod.GET)
    public ModelAndView listStructuralItems(@PathVariable("id") Long id, @PathVariable("structureType") String structureType, ModelMap model) {                     
        model.addAttribute("militaryUnit", muDao.findById(id));
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n LISTAGEM PARA MU:"+muDao.findById(id).getMU_name());
        switch (testType(structureType)){
            case TYPE1:
                model.addAttribute("structureItems", dao.getST1ByMUId(id));
                model.addAttribute("structureType", StructureTypeEnum.TYPE1);
                break;
            case TYPE2:
                model.addAttribute("structureItems", dao.getST2ByMUId(id));
                model.addAttribute("structureType", StructureTypeEnum.TYPE2);
                break;
            case TYPE3:
                model.addAttribute("structureItems", dao.getST3ByMUId(id));
                model.addAttribute("structureType", StructureTypeEnum.TYPE3);
                break;
            case TYPE4:
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n INDO PARA st4");
                model.addAttribute("structureItems", dao.getST4ByMUId(id));
                model.addAttribute("structureType", StructureTypeEnum.TYPE4);
                break;           
            
            default:
                model.addAttribute("structureItems", dao.getAllByMUId(id));
                model.addAttribute("structureType", StructureTypeEnum.TYPE4);
        }
        
	//System.out.println("Usuários recuperados::"+model.toString());
	model.addAttribute("subContent", "mu_management/listStructure");        
        return new ModelAndView("layout", FragmentController.checkMUMenu(muDao.findById(id), model));
    }
    
    
      
    /**Exclui um item de material a volta para a tela de listagem de materiais*/
    @GetMapping("/delete/{id}/{structureType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView delete(@ModelAttribute("id") Long id, @ModelAttribute("structureType") String structureType, ModelMap model) {            
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        Long muId;
        switch (testType(structureType)){
            case TYPE1:
                //recupera a MU
                muId = dao.getST1ById(id).getMu_owner().getId();
                dao.deleteST1(id);
                modelAndView.addObject("structureType", StructureTypeEnum.TYPE1.getDescription());
                break;
            case TYPE2:
                //recupera a MU
                muId = dao.getST2ById(id).getMu_owner().getId();
                dao.deleteST2(id);
                modelAndView.addObject("structureType", StructureTypeEnum.TYPE2.getDescription());
                break;
            case TYPE3:
                //recupera a MU
                muId = dao.getST3ById(id).getMu_owner().getId();
                dao.deleteST3(id);
                modelAndView.addObject("structureType", StructureTypeEnum.TYPE3.getDescription());
                break;
            case TYPE4:
                //recupera a MU
                muId = dao.getST4ById(id).getMu_owner().getId();
                dao.deleteST4(id);
                modelAndView.addObject("structureType", StructureTypeEnum.TYPE4.getDescription());
                break;
                     
            
            default:
                muId = new Long(0);
        
        }
        modelAndView.addObject("id", muId);
        return modelAndView;        
    }
    
    
    
    /**Chama a tela de cadastro para uma nova estrutura de acordo com o seu tipo */
    @GetMapping("/registerStructure/{id}/{structureType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView registerStructure(@ModelAttribute("id") Long id, @ModelAttribute("structureType") String structureType, ModelMap model) {            
                
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit", mu);
      
        //seta a MU da estrutura a ser cadastrada e manda para o form de cadastro
        switch (testType(structureType)){
            case TYPE1:
                StructureT1 sT1 = new StructureT1();
                sT1.setStructureType(StructureTypeEnum.TYPE1);
                sT1.setMu_owner(mu);
                model.addAttribute("structureT1", sT1);
                model.addAttribute("subContent", "mu_management/addStructureT1");
                model.addAttribute("structureType", StructureTypeEnum.TYPE1);
                break;
            case TYPE2:
                StructureT2 sT2 = new StructureT2();
                sT2.setStructureType(StructureTypeEnum.TYPE2);
                sT2.setMu_owner(mu);
                model.addAttribute("structureT2", sT2);
                model.addAttribute("subContent", "mu_management/addStructureT2");
                model.addAttribute("structureType", StructureTypeEnum.TYPE2);
                break;
            case TYPE3:
                StructureT3 sT3 = new StructureT3();
                sT3.setStructureType(StructureTypeEnum.TYPE3);
                sT3.setMu_owner(mu);
                model.addAttribute("structureT3", sT3);
                model.addAttribute("subContent", "mu_management/addStructureT3");
                model.addAttribute("structureType", StructureTypeEnum.TYPE3);
                break;
            case TYPE4:
                StructuralItem sT4 = new StructuralItem();
                sT4.setStructureType(StructureTypeEnum.TYPE4);
                sT4.setMu_owner(mu);
                model.addAttribute("structureT4", sT4);
                model.addAttribute("subContent", "mu_management/addStructureT4");
                model.addAttribute("structureType", StructureTypeEnum.TYPE4);
                break;
           
            
          
 
         
        }
        return new ModelAndView("layout", FragmentController.checkMUMenu(muDao.findById(id), model));
    }
   
    
       
    /**Chama a tela de atualizaão de estrutura*/
    @GetMapping("/preUpdate/{strId}/{structureType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView preUpdate(@ModelAttribute("strId") Long strId, @ModelAttribute("structureType") String structureType, ModelMap model) {            
          
        MilitaryUnit mu;
        switch (testType(structureType)){
            case TYPE1:
                StructureT1 sT1 = dao.getST1ById(strId);
                mu = sT1.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("structureT1", sT1);
                model.addAttribute("subContent", "mu_management/addStructureT1");
                model.addAttribute("structureType", StructureTypeEnum.TYPE1);
                break;
            case TYPE2:
                StructureT2 sT2 = dao.getST2ById(strId);
                mu = sT2.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("structureT2", sT2);
                model.addAttribute("subContent", "mu_management/addStructureT2");
                model.addAttribute("structureType", StructureTypeEnum.TYPE2);
                break;
            case TYPE3:
                StructureT3 sT3 = dao.getST3ById(strId);
                mu = sT3.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("structureT3", sT3);
                model.addAttribute("subContent", "mu_management/addStructureT3");
                model.addAttribute("structureType", StructureTypeEnum.TYPE3);
                break;
            case TYPE4:
                StructuralItem sT4 = dao.getST4ById(strId);
                mu = sT4.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("structureT4", sT4);
                model.addAttribute("subContent", "mu_management/addStructureT4");
                model.addAttribute("structureType", StructureTypeEnum.TYPE4);
                break;
           
             
            default:
                mu = new MilitaryUnit();
        
        }
        
        return new ModelAndView("layout", FragmentController.checkMUMenu(mu, model));
        
    }
  
    
     /**Chama a tela de cadastro para um novo material de acordo com sua classe */
    @GetMapping("/moreInfo/{id}/{structureType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView moreInfo(@ModelAttribute("id") Long id, @ModelAttribute("structureType") String structureType, ModelMap model) {            
        MilitaryUnit mu = new MilitaryUnit();
        switch (testType(structureType)){
            case TYPE1:                
                StructureT1 sT1 = dao.getST1ById(id);
                mu = sT1.getMu_owner();
                model.addAttribute("structureItem", sT1);
                model.addAttribute("structureType", StructureTypeEnum.TYPE1);
            break;
            case TYPE2:                
                StructureT2 sT2 = dao.getST2ById(id);
                mu = sT2.getMu_owner();
                model.addAttribute("structureItem", sT2);
                model.addAttribute("structureType", StructureTypeEnum.TYPE2);
            break;
            case TYPE3:                
                StructureT3 sT3 = dao.getST3ById(id);
                mu = sT3.getMu_owner();
                model.addAttribute("structureItem", sT3);
                model.addAttribute("structureType", StructureTypeEnum.TYPE3);
            break;
            case TYPE4:                
                StructuralItem sT4 = dao.getST4ById(id);
                mu = sT4.getMu_owner();
                model.addAttribute("structureItem", sT4);
                model.addAttribute("structureType", StructureTypeEnum.TYPE4);
            break;
            
        
        }
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("subContent", "mu_management/structureInfo");
        //Atualiza o model com a parte de menu e retorna 
        return new ModelAndView("layout", FragmentController.checkMUMenu(mu, model));
    }    
    
    
    //MÉTODOS ESPECÍFICOS POR TIPO DE ESTRUTURA 
    /**Salva um cadastro de PNR*/    
    @RequestMapping(value = "/savestructureT1", method = RequestMethod.POST)
    public ModelAndView savestructureT1(@Valid @ModelAttribute("structureT1") StructureT1 structureT1, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(structureT1.getMu_owner().getId());
        structureT1.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE1.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        structureT1.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT1.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveST1(structureT1);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    /**Salva um cadastro de Oficina*/    
    @RequestMapping(value = "/savestructureT2", method = RequestMethod.POST)
    public ModelAndView savestructureT2(@Valid @ModelAttribute("structureT2") StructureT2 structureT2, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(structureT2.getMu_owner().getId());
        structureT2.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE2.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        structureT2.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT2.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveST2(structureT2);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    /**Salva um cadastro de Oficina*/    
    @RequestMapping(value = "/savestructureT3", method = RequestMethod.POST)
    public ModelAndView savestructureT3(@Valid @ModelAttribute("structureT3") StructureT3 structureT3, BindingResult result, RedirectAttributes attr) {
        
       // System.out.println("\n\n\n\n\n\n\n\n\n\n ENTRANDO NO SALVAMENTO SEM CONSULTA AO BANCO:"+structureT3.getMu_owner().getId());
        MilitaryUnit mu = muDao.findById(structureT3.getMu_owner().getId());
        structureT3.setMu_owner(mu);
        //System.out.println("\n\n\n\n\n\n\n\n\n\n SALVANDO estrutura ST3");
        //System.out.println("\n\n\n\n\n\n\n\n\n\n SALVANDO COM MU:"+mu.getMU_name());
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE3.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        structureT3.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT3.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveST3(structureT3);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    /**Salva um cadastro de Reserva de Armamento*/    
    @RequestMapping(value = "/savestructureT4", method = RequestMethod.POST)
    public ModelAndView savestructureT4(@Valid @ModelAttribute("structureT4") StructuralItem structureT4, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(structureT4.getMu_owner().getId());
        structureT4.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE4.getDescription());
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        structureT4.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT4.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveST4(structureT4);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
   
    
    
     /**Efetiva a atualização de cadastro de PNR*/
    @PostMapping("/updateStructureT1")
    public ModelAndView updateStructureT1(@Valid @ModelAttribute("structureT1") StructureT1 structureT1, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",structureT1.getMu_owner().getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE1.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT1.setMil_modifier(currentUser.getUser().getMilitary());
        
        structureT1.setLast_update(LocalDate.now());
        dao.updateST1(structureT1);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
     /**Efetiva a atualização de cadastro de oficina*/
    @PostMapping("/updateStructureT2")
    public ModelAndView updateStructureT2(@Valid @ModelAttribute("structureT2") StructureT2 structureT2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",structureT2.getMu_owner().getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE2.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT2.setMil_modifier(currentUser.getUser().getMilitary());
        
        structureT2.setLast_update(LocalDate.now());
        dao.updateST2(structureT2);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
     /**Efetiva a atualização de cadastro de reserva de armamento*/
    @PostMapping("/updateStructureT3")
    public ModelAndView updateStructureT3(@Valid @ModelAttribute("structureT3") StructureT3 structureT3, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",structureT3.getMu_owner().getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE3.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT3.setMil_modifier(currentUser.getUser().getMilitary());
        
        structureT3.setLast_update(LocalDate.now());
        dao.updateST3(structureT3);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
     /**Efetiva a atualização de item geral de estocagem*/
    @PostMapping("/updateStructureT4")
    public ModelAndView updateStructureT4(@Valid @ModelAttribute("structureT4") StructuralItem structureT4, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/structureControl/listStructuralItems/{id}/{structureType}");
        modelAndView.addObject("id",structureT4.getMu_owner().getId());
        modelAndView.addObject("structureType", StructureTypeEnum.TYPE4.getDescription());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        structureT4.setMil_modifier(currentUser.getUser().getMilitary());
        
        structureT4.setLast_update(LocalDate.now());
        dao.updateST4(structureT4);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    
    
    
}
