/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.ServiceDao;
import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ElectricTensionEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.GarbageTreatmentTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ServiceCatalogEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SewageSystemEnum;
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
 * Controlador de operações sobre cadastro de serviços.
 * @author tenbenites
 */
@Controller
@RequestMapping("serviceControl")
public class ServiceController {
    @Autowired
    private ServiceDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;     

    @ModelAttribute("sewageSys")
    public SewageSystemEnum[] sewageSystemEnum(){
            return SewageSystemEnum.values();
    }
    @ModelAttribute("garbageTreatment")
    public GarbageTreatmentTypeEnum[] garbageTreatmentTypeEnum(){
            return GarbageTreatmentTypeEnum.values();
    }
    @ModelAttribute("electricTension")
    public ElectricTensionEnum[] electricTensionEnum(){
            return ElectricTensionEnum.values();
    }
    
    //MÉTODOS GERAIS PARA TODOS OS SERVIÇOS
       
        
    /**Lista serviços de acordo com o tipo fornecido na chamada.*/
    @RequestMapping(value = "/listServices/{id}/{serviceType}", method=RequestMethod.GET)
    public ModelAndView listServices(@PathVariable("id") Long id, @PathVariable("serviceType") String serviceType) {                     
        ModelMap model = new ModelMap();
        model.addAttribute("militaryUnit", muDao.findById(id));
        switch (serviceType){
            case "SVC1"://Geral
                model.addAttribute("serviceItems", dao.getSvc1ByMUId(id));
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC1);
                break;
            case "SVC2"://Água/Esgoto
                model.addAttribute("serviceItems", dao.getSvc2ByMUId(id));
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC2);
                break;
            case "SVC3"://Coleta de Lixo
                model.addAttribute("serviceItems", dao.getSvc3ByMUId(id));
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC3);
                break;
            case "SVC4"://Rede elétrica
                model.addAttribute("serviceItems", dao.getSvc4ByMUId(id));
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC4);
                break;
            case "SVC5"://Convênios
                model.addAttribute("serviceItems", dao.getSvc5ByMUId(id));
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC5);
                break;
            default:
                model.addAttribute("serviceItems", dao.getAllByMUId(id));
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC1);
        }
        
	//System.out.println("Usuários recuperados::"+model.toString());
	model.addAttribute("subContent", "mu_management/listServices");        
        return new ModelAndView("layout", FragmentController.checkMUMenu(muDao.findById(id), model));
    }
    
    
     
    
    /**Chama a tela de cadastro para uma nova estrutura de acordo com o seu tipo */
    @GetMapping("/registerService/{id}/{serviceType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView registerService(@ModelAttribute("id") Long id, @ModelAttribute("serviceType") ServiceCatalogEnum serviceType, ModelMap model) {            
                
                System.out.println("\n\n\n\n\n\n\n\n\n CHAMOU NOVO REGISTRO DE SERVIÇO"+serviceType);
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit", mu);
      
        //seta a MU da estrutura a ser cadastrada e manda para o form de cadastro
        switch (serviceType){
            case SVC1:
                Service svc1 = new Service();//Construtor já seta o tipo 
                svc1.setMu_owner(mu);
                model.addAttribute("svc1", svc1);
                model.addAttribute("subContent", "mu_management/addService1");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC1);
                break;
            case SVC2:
                SVC2_WaterSupplySewageCollection svc2 = new SVC2_WaterSupplySewageCollection();//Construtor já seta o tipo 
                svc2.setMu_owner(mu);
                model.addAttribute("svc2", svc2);
                model.addAttribute("subContent", "mu_management/addService2");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC2);
                break;
            case SVC3:
                SVC3_GarbageCollection svc3 = new SVC3_GarbageCollection();//Construtor já seta o tipo 
                svc3.setMu_owner(mu);
                model.addAttribute("svc3", svc3);
                model.addAttribute("subContent", "mu_management/addService3");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC3);
                break;
            case SVC4:
                SVC4_EnergyNet svc4 = new SVC4_EnergyNet();//Construtor já seta o tipo 
                svc4.setMu_owner(mu);
                model.addAttribute("svc4", svc4);
                model.addAttribute("subContent", "mu_management/addService4");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC4);
                break;
            case SVC5:
                SVC5_Insurance svc5 = new SVC5_Insurance();//Construtor já seta o tipo "Outros"
                svc5.setMu_owner(mu);
                model.addAttribute("svc5", svc5);
                model.addAttribute("subContent", "mu_management/addService5");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC5);
                break;
            
            
           
            
          
 
         
        }
        return new ModelAndView("layout", FragmentController.checkMUMenu(muDao.findById(id), model));
    }

    
    
       
       
    /**Chama a tela de atualizaão de estrutura*/
    @GetMapping("/preUpdate/{svcId}/{serviceType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView preUpdate(@ModelAttribute("svcId") Long svcId, @ModelAttribute("serviceType") ServiceCatalogEnum serviceType, ModelMap model) {            
          
        MilitaryUnit mu = new MilitaryUnit();
        switch (serviceType){
            case SVC1:
                Service svc1 = dao.getSvc1ById(svcId);
                mu = svc1.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("svc1", svc1);
                model.addAttribute("subContent", "mu_management/addService1");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC1);
                break;
            case SVC2:
                SVC2_WaterSupplySewageCollection svc2 = dao.getSvc2ById(svcId);//Construtor já seta o tipo 
                mu = svc2.getMu_owner();                
                model.addAttribute("svc2", svc2);
                model.addAttribute("subContent", "mu_management/addService2");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC2);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\nACHOU SVC2"+svc2.toString());
                break;
            case SVC3:
                SVC3_GarbageCollection svc3 = dao.getSvc3ById(svcId);//Construtor já seta o tipo 
                mu = svc3.getMu_owner();
                model.addAttribute("svc3", svc3);
                model.addAttribute("subContent", "mu_management/addService3");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC3);
                break;
            case SVC4:
                SVC4_EnergyNet svc4 = dao.getSvc4ById(svcId);//Construtor já seta o tipo 
                mu = svc4.getMu_owner();
                model.addAttribute("svc4", svc4);
                model.addAttribute("subContent", "mu_management/addService4");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC4);
                break;
            case SVC5:
                SVC5_Insurance svc5 = dao.getSvc5ById(svcId);//Construtor já seta o tipo "Outros"
                mu = svc5.getMu_owner();
                model.addAttribute("svc5", svc5);
                model.addAttribute("subContent", "mu_management/addService5");
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC5);
                break;
            
                     
            
        
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nVOLTANDO...............");
        model.addAttribute("militaryUnit",mu);
        return new ModelAndView("layout", FragmentController.checkMUMenu(mu, model));
        
    }
  
    
    
       
    /**Exclui um item de material a volta para a tela de listagem de materiais*/
    @GetMapping("/deleteService/{svcId}/{serviceType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView deleteService(@ModelAttribute("svcId") Long svcId, @ModelAttribute("serviceType") ServiceCatalogEnum serviceType) {            
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        Long muId = new Long(0);
        
                System.out.println("\n\n\n\n\n\n\n\n\n CHAMADA DELETE");
        switch (serviceType){
            case SVC1:
                //recupera a MU
                muId = dao.getSvc1ById(svcId).getMu_owner().getId();
                dao.deleteSvc1(svcId);
                modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC1);
                break;
            case SVC2:
                System.out.println("\n\n\n\n\n\n\n\n\n DELETE SVC2");
                muId = dao.getSvc2ById(svcId).getMu_owner().getId();
                System.out.println("\n\n\n\n\n\n\n\n\n ATUALIZOU UNIDADE MILITAR: "+dao.getSvc2ById(svcId).getMu_owner().getMU_name());
                dao.deleteSvc2(svcId);
                modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC2);
                System.out.println("\n\n\n\n\n\n\n\n\n DELETADO SVC2!");
                
                break;
            case SVC3:
                muId = dao.getSvc3ById(svcId).getMu_owner().getId();
                dao.deleteSvc3(svcId);
                modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC3);
                break;
            case SVC4:
                muId = dao.getSvc4ById(svcId).getMu_owner().getId();
                dao.deleteSvc4(svcId);
                modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC4);
                break;
            case SVC5:
                muId = dao.getSvc5ById(svcId).getMu_owner().getId();
                dao.deleteSvc5(svcId);
                modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC5);
                break;
                     
            
            default:
                muId = new Long(0);
        
        }
        modelAndView.addObject("id", muId);
        return modelAndView;        
    }
    
    
     /**Chama a tela de cadastro para um novo material de acordo com sua classe */
    @GetMapping("/moreInfo/{id}/{serviceType}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView moreInfo(@ModelAttribute("id") Long id, @ModelAttribute("serviceType") ServiceCatalogEnum serviceType, ModelMap model) {            
        MilitaryUnit mu = new MilitaryUnit();
        switch (serviceType){
            case SVC1:                
                Service svc1 = dao.getSvc1ById(id);
                mu = svc1.getMu_owner();
                model.addAttribute("serviceItem", svc1);
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC1);
            break;
            case SVC2:                
                SVC2_WaterSupplySewageCollection svc2 = dao.getSvc2ById(id);
                mu = svc2.getMu_owner();
                model.addAttribute("serviceItem", svc2);
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC2);
            break;
            case SVC3:                
                SVC3_GarbageCollection svc3 = dao.getSvc3ById(id);
                mu = svc3.getMu_owner();
                model.addAttribute("serviceItem", svc3);
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC3);
            break;
            case SVC4:                
                SVC4_EnergyNet svc4 = dao.getSvc4ById(id);
                mu = svc4.getMu_owner();
                model.addAttribute("serviceItem", svc4);
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC4);
            break;
            case SVC5:                
                SVC5_Insurance svc5 = dao.getSvc5ById(id);
                mu = svc5.getMu_owner();
                model.addAttribute("serviceItem", svc5);
                model.addAttribute("serviceType", ServiceCatalogEnum.SVC5);
            break;
            
        
        }
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("subContent", "mu_management/serviceInfo");
        //Atualiza o model com a parte de menu e retorna 
        return new ModelAndView("layout", FragmentController.checkMUMenu(mu, model));
    } 
    
    //MÉTODOS ESPECÍFICOS POR TIPO DE ESTRUTURA 
    /**Salva um serviço genérico "Outros serviços"*/    
    @RequestMapping(value = "/saveService1", method = RequestMethod.POST)
    public ModelAndView saveService1(@Valid @ModelAttribute("service") Service service, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(service.getMu_owner().getId());
        service.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC1);
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        service.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        service.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSvc(service);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    /**Salva um serviço de água/esgoto*/    
    @RequestMapping(value = "/saveService2", method = RequestMethod.POST)
    public ModelAndView saveService2(@Valid @ModelAttribute("svc2") SVC2_WaterSupplySewageCollection svc2, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(svc2.getMu_owner().getId());
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\nTIPO DE SISTEMA"+svc2.getSewage_serviceType().getDescription());
        svc2.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC2);
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        svc2.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc2.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSvc2(svc2);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    /**Salva um serviço de coleta de lixo*/    
    @RequestMapping(value = "/saveService3", method = RequestMethod.POST)
    public ModelAndView saveService3(@Valid @ModelAttribute("svc3") SVC3_GarbageCollection svc3, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(svc3.getMu_owner().getId());
        svc3.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC3);
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        svc3.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc3.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSvc3(svc3);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    /**Salva um serviço de Rede elétrica*/    
    @RequestMapping(value = "/saveService4", method = RequestMethod.POST)
    public ModelAndView saveService4(@Valid @ModelAttribute("svc4") SVC4_EnergyNet svc4, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(svc4.getMu_owner().getId());
        svc4.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC4);
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        svc4.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc4.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSvc4(svc4);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    /**Salva um convênio*/    
    @RequestMapping(value = "/saveService5", method = RequestMethod.POST)
    public ModelAndView saveService5(@Valid @ModelAttribute("svc5") SVC5_Insurance svc5, BindingResult result, RedirectAttributes attr) {
        MilitaryUnit mu = muDao.findById(svc5.getMu_owner().getId());
        svc5.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC5);
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        svc5.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc5.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSvc5(svc5);
        //System.out.println("ENTROU, lpg: "+lpg.toString());
        return modelAndView;
    }
    
    
    
    /**Efetiva a atualização de um serviço genérico "Outros serviços"*/
    @PostMapping("/updateService1")
    public ModelAndView updateService1(@Valid @ModelAttribute("svc1") Service svc1, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",svc1.getMu_owner().getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC1);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc1.setMil_modifier(currentUser.getUser().getMilitary());
        
        svc1.setLast_update(LocalDate.now());
        dao.updateSvc1(svc1);
        attr.addFlashAttribute("message", "Infomação de serviço alterada com sucesso.");
        return modelAndView;
    }
    /**Efetiva a atualização de um serviço de água/esgoto"*/
    @PostMapping("/updateService2")
    public ModelAndView updateService2(@Valid @ModelAttribute("svc2") SVC2_WaterSupplySewageCollection svc2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",svc2.getMu_owner().getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC2);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc2.setMil_modifier(currentUser.getUser().getMilitary());
        
        svc2.setLast_update(LocalDate.now());
        dao.updateSvc2(svc2);
        attr.addFlashAttribute("message", "Infomação de serviço de água/esgoto alterada com sucesso.");
        return modelAndView;
    }
    /**Efetiva a atualização de um serviço de coleta de lixo"*/
    @PostMapping("/updateService3")
    public ModelAndView updateService3(@Valid @ModelAttribute("svc3") SVC3_GarbageCollection svc3, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",svc3.getMu_owner().getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC3);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc3.setMil_modifier(currentUser.getUser().getMilitary());
        
        svc3.setLast_update(LocalDate.now());
        dao.updateSvc3(svc3);
        attr.addFlashAttribute("message", "Infomação de serviço alterada com sucesso.");
        return modelAndView;
    }
    /**Efetiva a atualização de um serviço de Rede elétrica"*/
    @PostMapping("/updateService4")
    public ModelAndView updateService4(@Valid @ModelAttribute("svc4") SVC4_EnergyNet svc4, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",svc4.getMu_owner().getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC4);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc4.setMil_modifier(currentUser.getUser().getMilitary());
        
        svc4.setLast_update(LocalDate.now());
        dao.updateSvc4(svc4);
        attr.addFlashAttribute("message", "Infomação de serviço alterada com sucesso.");
        return modelAndView;
    }
    /**Efetiva a atualização de um convênio"*/
    @PostMapping("/updateService5")
    public ModelAndView updateService5(@Valid @ModelAttribute("svc5") SVC5_Insurance svc5, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/serviceControl/listServices/{id}/{serviceType}");
        modelAndView.addObject("id",svc5.getMu_owner().getId());
        modelAndView.addObject("serviceType", ServiceCatalogEnum.SVC5);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        svc5.setMil_modifier(currentUser.getUser().getMilitary());
        
        svc5.setLast_update(LocalDate.now());
        dao.updateSvc5(svc5);
        attr.addFlashAttribute("message", "Infomação de serviço alterada com sucesso.");
        return modelAndView;
    }
}
