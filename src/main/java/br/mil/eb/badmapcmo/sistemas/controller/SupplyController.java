/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.StructureDao;
import br.mil.eb.badmapcmo.sistemas.dao.SupplyDao;
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
 * Controla operações sobre itens de suprimento, independente de classe, 
 * operações de CRUD são feitas individualmente pois, dependendo das regras de 
 * negócio, podem ser necessários tratamentos específicos de dados dentro dos 
 * métodos de cotrole.
 * @author tenbenites
 */
@Controller
@RequestMapping("supplyControl")
public class SupplyController {
    
    @Autowired
    private SupplyDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;
    
    @Autowired
    private StructureDao stcDao;
    
    @ModelAttribute("supplyClasses")
    public SupplyClassEnum[] supplyClassEnum(){
            return SupplyClassEnum.values();
    }
    @ModelAttribute("CIXstatus")
    public CIXStatusEnum[] CIXStatusEnum(){
            return CIXStatusEnum.values();
    }
    @ModelAttribute("CIXfuel")
    public CIXFuelEnum[] CIXFuelEnum(){
            return CIXFuelEnum.values();
    }
    @ModelAttribute("CIXtype")
    public CIXTypeEnum[] CIXTypeEnum(){
            return CIXTypeEnum.values();
    }
    
    /**Lista itens de suprimento de acordo com a classe de suprimento fornecida na chamada.*/
    @RequestMapping(value = "/listSupplyItems/{id}/{supplyClass}", method=RequestMethod.GET)
    public ModelAndView listSupplyItems(@PathVariable("id") Long id, @PathVariable("supplyClass") SupplyClassEnum supplyClass) {                     
        ModelMap model = new ModelMap();
        model.addAttribute("militaryUnit", muDao.findById(id));
        switch (supplyClass){
            case CLASSI:
                model.addAttribute("supplyItems", dao.getSCIByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSI);
                break;
            case CLASSII:
                model.addAttribute("supplyItems", dao.getSCIIByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSII);
                break;
            case CLASSIII1:
                model.addAttribute("supplyItems", dao.getSCIII1ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII1);
                break;
            case CLASSIII2:
                model.addAttribute("supplyItems", dao.getSCIII2ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII2);
                break;
            case CLASSIII3:
                model.addAttribute("supplyItems", dao.getSCIII3ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII3);
                break;
            case CLASSIV:
                model.addAttribute("supplyItems", dao.getSCIByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIV);
                break;
            case CLASSV1:
                model.addAttribute("supplyItems", dao.getSCV1ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV1);
                break;
            case CLASSV2:
                model.addAttribute("supplyItems", dao.getSCV2ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV2);
                break;
            case CLASSVI:
                model.addAttribute("supplyItems", dao.getSCVIByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI);
                break;
            case CLASSVI1:
                model.addAttribute("supplyItems", dao.getSCVI1ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI1);
                break;
            case CLASSVI2:
                model.addAttribute("supplyItems", dao.getSCVI2ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI2);
                break;
            case CLASSVIII:
                model.addAttribute("supplyItems", dao.getSCIByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVIII);
                break;
            case CLASSIX:
                model.addAttribute("supplyItems", dao.getSCIByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX);
                break;
            case CLASSIX1:
                model.addAttribute("supplyItems", dao.getSCIX1ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX1);
                break;
            case CLASSIX2:
                model.addAttribute("supplyItems", dao.getSCIX2ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX2);
                break;
            case CLASSIX3:
                model.addAttribute("supplyItems", dao.getSCIX3ByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX3);
                break;
            case CLASSX:
                model.addAttribute("supplyItems", dao.getSCIByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSX);
                break;
            default:
                model.addAttribute("supplyItems", dao.getAllByMUId(id));
                model.addAttribute("supplyClass", SupplyClassEnum.SCLASS);
        }
        
	//System.out.println("Usuários recuperados::"+model.toString());
	model.addAttribute("subContent", "mu_management/listSupply");        
        return new ModelAndView("layout", FragmentController.checkMUMenu(muDao.findById(id), model));
    }
    
    
    
    /**Chama a tela de cadastro para um novo material de acordo com sua classe */
    @GetMapping("/registerSupply/{id}/{supplyClass}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView registerSupply(@ModelAttribute("id") Long id, @ModelAttribute("supplyClass") SupplyClassEnum supplyClass, ModelMap model) {            
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n RECEBEU CLASSE:  "+supplyClass.toString());        
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit", mu);
        switch (supplyClass){
            case CLASSI:
                System.out.println("INCLUSÃO DE SUPRIMENTO CLASSE I");
                SupplyItemCI supplyItemCI = new SupplyItemCI();
                supplyItemCI.setMu_owner(mu);
                model.addAttribute("supplyItemCI", supplyItemCI);
                model.addAttribute("subContent", "mu_management/addSupplyCI");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSI);
                break;
            case CLASSII:
                System.out.println("INCLUSÃO DE SUPRIMENTO CLASSE II");
                SupplyItemCII supplyItemCII = new SupplyItemCII();
                supplyItemCII.setMu_owner(mu);
                model.addAttribute("supplyItemCII", supplyItemCII);
                model.addAttribute("subContent", "mu_management/addSupplyCII");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSII);
                break;
            case CLASSIII1:
                System.out.println("INCLUSÃO DE SUPRIMENTO CLASSE III1");
                SupplyItemCIII1_LPG lpg = new SupplyItemCIII1_LPG();
                lpg.setMu_owner(mu);
                model.addAttribute("lpg", lpg);
                model.addAttribute("subContent", "mu_management/addSupplyCIII1");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII1);
                break;
            case CLASSIII2:
                SupplyItemCIII2_Gasoline supplyItemCIII2 = new SupplyItemCIII2_Gasoline();
                supplyItemCIII2.setMu_owner(mu);
                supplyItemCIII2.setName("Gasolina");
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentStorages", stcDao.getST2ByMUId(mu.getId()));
                model.addAttribute("supplyItemCIII2", supplyItemCIII2);
                model.addAttribute("subContent", "mu_management/addSupplyCIII2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII2);
                break;
            case CLASSIII3:
                SupplyItemCIII3_Diesel SupplyItemCIII3 = new SupplyItemCIII3_Diesel();
                SupplyItemCIII3.setMu_owner(mu);
                SupplyItemCIII3.setName("Diesel");
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentStorages", stcDao.getST2ByMUId(mu.getId()));
                model.addAttribute("supplyItemCIII3", SupplyItemCIII3);
                model.addAttribute("subContent", "mu_management/addSupplyCIII3");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII3);
                break;
            case CLASSIV:
                SupplyItemCIV supplyItemCIV = new SupplyItemCIV();
                supplyItemCIV.setMu_owner(mu);
                model.addAttribute("supplyItemCIV", supplyItemCIV);
                model.addAttribute("subContent", "mu_management/addSupplyCIV");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIV);
                break;
            case CLASSV1:
                SupplyItemCV1_munition supplyItemCV1_munition = new SupplyItemCV1_munition();
                supplyItemCV1_munition.setMu_owner(mu);
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentArmories", stcDao.getST3ByMUId(mu.getId()));
                model.addAttribute("supplyItemCV1", supplyItemCV1_munition);
                model.addAttribute("subContent", "mu_management/addSupplyCV1");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV1);
                break;
            case CLASSV2:
                SupplyItemCV2_weaponry supplyItemCV2_weaponry = new SupplyItemCV2_weaponry();
                supplyItemCV2_weaponry.setMu_owner(mu);
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentArmories", stcDao.getST3ByMUId(mu.getId()));                
                model.addAttribute("supplyItemCV2", supplyItemCV2_weaponry);
                model.addAttribute("subContent", "mu_management/addSupplyCV2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV2);
                break;
            case CLASSVI:
                SupplyItemCVI supplyItemCVI = new SupplyItemCVI();
                supplyItemCVI.setMu_owner(mu);
                model.addAttribute("supplyItemCVI", supplyItemCVI);
                model.addAttribute("subContent", "mu_management/addSupplyCVI");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI);
                break;
            case CLASSVI1:
                SupplyItemCVI1_generator supplyItemCVI1 = new SupplyItemCVI1_generator();
                supplyItemCVI1.setMu_owner(mu);
                model.addAttribute("supplyItemCVI1", supplyItemCVI1);
                model.addAttribute("subContent", "mu_management/addSupplyCVI1");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI1);
                break;
            case CLASSVI2:
                SupplyItemCVI2_GPS supplyItemCVI2 = new SupplyItemCVI2_GPS();
                supplyItemCVI2.setMu_owner(mu);
                model.addAttribute("supplyItemCVI2", supplyItemCVI2);
                model.addAttribute("subContent", "mu_management/addSupplyCVI2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI2);
                break;
             case CLASSVII:
                SupplyItemCVII supplyItemCVII = new SupplyItemCVII();
                supplyItemCVII.setMu_owner(mu);
                model.addAttribute("supplyItemCVI", supplyItemCVII);
                model.addAttribute("subContent", "mu_management/addSupplyCVII");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVII);
                break;    
            case CLASSVIII:
                SupplyItemCVIII supplyItemCVIII = new SupplyItemCVIII();
                supplyItemCVIII.setMu_owner(mu);
                model.addAttribute("supplyItemCVIII", supplyItemCVIII);
                model.addAttribute("subContent", "mu_management/addSupplyCVIII");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVIII);
                break;
            case CLASSIX1:
                System.out.println("INCLUSÃO DE SUPRIMENTO CLASSE IX1");
                Vehicle v = new Vehicle();
                v.setMu_owner(mu);
                model.addAttribute("vehicle", v);
	        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nPré salvamento vehicle:"+v.toString());
                model.addAttribute("subContent", "mu_management/addVehicle");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX1);
                break;
            case CLASSIX2:
                System.out.println("INCLUSÃO DE SUPRIMENTO CLASSE IX2");
                Watercraft wc = new Watercraft();
                wc.setMu_owner(mu);
                model.addAttribute("watercraft", wc);
	        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nPré salvamento Watercraft:"+wc.toString());
                model.addAttribute("subContent", "mu_management/addSupplyCIX2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX2);
                break;
            case CLASSIX3:
                OutboardEngine oe = new OutboardEngine();
                oe.setMu_owner(mu);
                model.addAttribute("outboardengine", oe);
                model.addAttribute("subContent", "mu_management/addSupplyCIX3");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX3);
                break;
            case CLASSX:
                SupplyItemCX supplyItemCX = new SupplyItemCX();
                supplyItemCX.setMu_owner(mu);
                model.addAttribute("supplyItemCX", supplyItemCX);
                model.addAttribute("subContent", "mu_management/addSupplyCX");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSX);
                break;
        }
        return new ModelAndView("layout", FragmentController.checkMUMenu(muDao.findById(id), model));
    }
   
    
    /**Chama a tela de cadastro para um novo material*/
    @GetMapping("/preUpdate/{supId}/{supplyClass}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView preUpdate(@ModelAttribute("supId") Long supId, @ModelAttribute("supplyClass") SupplyClassEnum supplyClass, ModelMap model) {            
        System.out.println("ENTROU NO PREUPDATE");      
        MilitaryUnit mu;
        switch (supplyClass){
            case CLASSI:
                System.out.println("ATUALIZAÇÃO DE SUPRIMENTO CLASSE I");
                SupplyItemCI supplyItemCI = dao.getSCIById(supId);
                mu = supplyItemCI.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCI", supplyItemCI);
                model.addAttribute("subContent", "mu_management/addSupplyCI");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSI);
                break;
            case CLASSII:
                System.out.println("ATUALIZAÇÃO DE SUPRIMENTO CLASSE II");
                SupplyItemCII supplyItemCII = dao.getSCIIById(supId);
                mu = supplyItemCII.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCII", supplyItemCII);
                model.addAttribute("subContent", "mu_management/addSupplyCII");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSII);
                break;
            case CLASSIII1:
                System.out.println("ATUALIZAÇÃO DE SUPRIMENTO CLASSE III.1");
                SupplyItemCIII1_LPG lpg = dao.getSCIII1ById(supId);
                mu = lpg.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("lpg", lpg);
                model.addAttribute("subContent", "mu_management/addSupplyCIII1");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII1);
                break;
            case CLASSIII2:
                System.out.println("ATUALIZAÇÃO DE SUPRIMENTO CLASSE III.2");
                SupplyItemCIII2_Gasoline supplyItemCIII2 = dao.getSCIII2ById(supId);
                mu = supplyItemCIII2.getMu_owner();
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentStorages", stcDao.getST2ByMUId(mu.getId()));
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCIII2", supplyItemCIII2);
                model.addAttribute("subContent", "mu_management/addSupplyCIII2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII2);
                break;
            case CLASSIII3:
                SupplyItemCIII3_Diesel SupplyItemCIII3 = dao.getSCIII3ById(supId);
                mu = SupplyItemCIII3.getMu_owner();
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentStorages", stcDao.getST2ByMUId(mu.getId()));
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCIII3", SupplyItemCIII3);
                model.addAttribute("subContent", "mu_management/addSupplyCIII3");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII3);
                break;
            case CLASSIV:
                SupplyItemCIV supplyItemCIV = dao.getSCIVById(supId);
                mu = supplyItemCIV.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCIV", supplyItemCIV);
                model.addAttribute("subContent", "mu_management/addSupplyCIV");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIV);
                break;
            case CLASSV1:
                SupplyItemCV1_munition supplyItemCV1 = dao.getSCV1ById(supId);
                mu = supplyItemCV1.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentArmories", stcDao.getST3ByMUId(mu.getId()));
                model.addAttribute("supplyItemCV1", supplyItemCV1);
                model.addAttribute("subContent", "mu_management/addSupplyCV1");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV1);
                break;
            case CLASSV2:
                SupplyItemCV2_weaponry supplyItemCV2 = dao.getSCV2ById(supId);
                mu = supplyItemCV2.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                //levanda as reservas disponíveis para a unidade militar 
                model.addAttribute("currentArmories", stcDao.getST3ByMUId(mu.getId()));
                model.addAttribute("supplyItemCV2", supplyItemCV2);
                model.addAttribute("subContent", "mu_management/addSupplyCV2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV2);
                break;
            case CLASSVI:
                SupplyItemCVI supplyItemCVI = dao.getSCVIById(supId);
                mu = supplyItemCVI.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCVI", supplyItemCVI);
                model.addAttribute("subContent", "mu_management/addSupplyCVI");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI);
                break;
            case CLASSVI1:
                SupplyItemCVI1_generator supplyItemCVI1 = dao.getSCVI1ById(supId);
                mu = supplyItemCVI1.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCVI1", supplyItemCVI1);
                model.addAttribute("subContent", "mu_management/addSupplyCVI1");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI1);
                break;
            case CLASSVI2:
                SupplyItemCVI2_GPS supplyItemCVI2 = dao.getSCVI2ById(supId);
                mu = supplyItemCVI2.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCVI2", supplyItemCVI2);
                model.addAttribute("subContent", "mu_management/addSupplyCVI2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI2);
                break;
//            case CLASSVII:
//                SupplyItemCVI supplyItemCVI = dao.getSCVIById(supId);
//                mu = supplyItemCVI.getMu_owner();
//                model.addAttribute("militaryUnit", mu);
//                model.addAttribute("supplyItemCVI", supplyItemCVI);
//                model.addAttribute("subContent", "mu_management/addSupplyCVI");
//                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI);
//                break;
            case CLASSVIII:
                SupplyItemCVIII supplyItemCVIII = dao.getSCVIIIById(supId);
                mu = supplyItemCVIII.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCVIII", supplyItemCVIII);
                model.addAttribute("subContent", "mu_management/addSupplyCVIII");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVIII);
                break;
            case CLASSIX1:
                Vehicle v = dao.getSCIX1ById(supId);
                mu = v.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("vehicle", v);
                model.addAttribute("subContent", "mu_management/addSupplyCIX1");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX1);
                break;
            case CLASSIX2:
                Watercraft wc = dao.getSCIX2ById(supId);
                mu = wc.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("watercraft", wc);
                model.addAttribute("subContent", "mu_management/addSupplyCIX2");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX2);
                break;
            case CLASSIX3:
                OutboardEngine oe = dao.getSCIX3ById(supId);
                mu = oe.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("outboardengine", oe);
                model.addAttribute("subContent", "mu_management/addSupplyCIX3");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX3);
                break;
            case CLASSX:
                SupplyItemCX supplyItemCX = dao.getSCXById(supId);
                mu = supplyItemCX.getMu_owner();
                model.addAttribute("militaryUnit", mu);
                model.addAttribute("supplyItemCX", supplyItemCX);
                model.addAttribute("subContent", "mu_management/addSupplyCX");
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSX);
                break;
            default:
                mu = new MilitaryUnit();
        
        }
        
        return new ModelAndView("layout", FragmentController.checkMUMenu(mu, model));
        
    }
    
    /**Exclui um item de material a volta para a tela de listagem de materiais*/
    @GetMapping("/delete/{id}/{supplyClass}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView delete(@ModelAttribute("id") Long id, @ModelAttribute("supplyClass") SupplyClassEnum supplyClass) {            
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        System.out.println("ENTROU NO DELETE");      
        Long muId = new Long(1);
        switch (supplyClass){
            case CLASSI:
                System.out.println("REMOÇÃO SUPRIMENTO CLASSE I");
                muId = dao.getSCIById(id).getMu_owner().getId();
                dao.deleteSCI(id);
                System.out.println("\n\n\n\n\n\n\n\n MUID"+muId);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSI);
                break;
            case CLASSII:
                System.out.println("REMOÇÃO SUPRIMENTO CLASSE II");
                muId = dao.getSCIIById(id).getMu_owner().getId();
                dao.deleteSCII(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSII);
                break;
            case CLASSIII1:
                muId = dao.getSCIII1ById(id).getMu_owner().getId();
                dao.deleteSCIII1(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII1);
                break;
            case CLASSIII2:
                muId = dao.getSCIII2ById(id).getMu_owner().getId();
                dao.deleteSCIII2(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII2);
                break;
            case CLASSIII3:
                muId = dao.getSCIII1ById(id).getMu_owner().getId();
                dao.deleteSCIII1(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII1);
                break;
            case CLASSIV:
                muId = dao.getSCIVById(id).getMu_owner().getId();
                dao.deleteSCIV(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIV);
                break;
            case CLASSV1: 
                muId = dao.getSCV1ById(id).getMu_owner().getId();
                dao.deleteSCV1(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSV1);
                break;
             case CLASSV2:
                muId = dao.getSCV2ById(id).getMu_owner().getId();
                dao.deleteSCV2(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSV2);
                break;
             case CLASSVI:
                muId = dao.getSCVIById(id).getMu_owner().getId();
                dao.deleteSCVI(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI);
                break;
             case CLASSVI1:
                muId = dao.getSCVI1ById(id).getMu_owner().getId();
                dao.deleteSCVI1(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI1);
                break;
             case CLASSVI2:
                muId = dao.getSCVI2ById(id).getMu_owner().getId();
                dao.deleteSCVI2(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI2);
                break;
//             case CLASSVII:
//                muId = dao.getSCIVById(id).getMu_owner().getId();
//                dao.deleteSCIV(id);
//                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIV);
//                break;
             case CLASSVIII:
                muId = dao.getSCVIIIById(id).getMu_owner().getId();
                dao.deleteSCVIII(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVIII);
                break;
            case CLASSIX1:
                muId = dao.getSCIX1ById(id).getMu_owner().getId();
                dao.deleteSCIX1(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX1);
                break;
            case CLASSIX2:
                muId = dao.getSCIX2ById(id).getMu_owner().getId();
                dao.deleteSCIX2(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIX2);
                break;
            case CLASSX:
                muId = dao.getSCXById(id).getMu_owner().getId();
                dao.deleteSCX(id);
                modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSX);
                break;
        
        }
        
        modelAndView.addObject("id",muId);
        return modelAndView;
        
    }
  
    
    
    /**Chama a tela de cadastro para um novo material de acordo com sua classe */
    @GetMapping("/moreInfo/{id}/{supplyClass}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView moreInfo(@ModelAttribute("id") Long id, @ModelAttribute("supplyClass") SupplyClassEnum supplyClass, ModelMap model) {            
        MilitaryUnit mu = new MilitaryUnit();
        switch (supplyClass){
            case CLASSI:                
                SupplyItemCI supplyItemCI = dao.getSCIById(id);
                mu = supplyItemCI.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCI);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSI);
            break;
            case CLASSII:                
                SupplyItemCII supplyItemCII = dao.getSCIIById(id);
                mu = supplyItemCII.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCII);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSII);
            break;
            case CLASSIII1:                
                SupplyItemCIII1_LPG lpg = dao.getSCIII1ById(id);
                mu = lpg.getMu_owner();
                model.addAttribute("supplyItem", lpg);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII1);
            break;
            case CLASSIII2:                
                SupplyItemCIII2_Gasoline supplyItemCIII2 = dao.getSCIII2ById(id);
                mu = supplyItemCIII2.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCIII2);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII2);
            break;
            case CLASSIII3:                
                SupplyItemCIII3_Diesel supplyItemCIII3 = dao.getSCIII3ById(id);
                mu = supplyItemCIII3.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCIII3);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIII3);
            break;
            case CLASSIV:                
                SupplyItemCIV supplyItemCIV = dao.getSCIVById(id);
                mu = supplyItemCIV.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCIV);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIV);
            break;
            case CLASSV1://MUNIÇÃO                
                SupplyItemCV1_munition supplyItemCV1 = dao.getSCV1ById(id);
                mu = supplyItemCV1.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCV1);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV1);
            break;
            case CLASSV2://ARMAMENTO                   
                SupplyItemCV2_weaponry supplyItemCV2 = dao.getSCV2ById(id);
                mu = supplyItemCV2.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCV2);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSV2);               
            break;
            
            
            case CLASSVI:                
                SupplyItemCVI supplyItemCVI = dao.getSCVIById(id);
                mu = supplyItemCVI.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCVI);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI);
            break;
            case CLASSVI1:                
                SupplyItemCVI supplyItemCVI1 = dao.getSCVI1ById(id);
                mu = supplyItemCVI1.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCVI1);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI1);
            break;
            case CLASSVI2:                
                SupplyItemCVI supplyItemCVI2 = dao.getSCVI2ById(id);
                mu = supplyItemCVI2.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCVI2);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVI2);
            break;
            
            case CLASSVII:                
                SupplyItemCVII supplyItemCVII = dao.getSCVIIById(id);
                mu = supplyItemCVII.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCVII);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVII);
            break;
            
            case CLASSVIII:                
                SupplyItemCVIII supplyItemCVIII = dao.getSCVIIIById(id);
                mu = supplyItemCVIII.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCVIII);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSVIII);
            break;
            case CLASSIX1:                
                Vehicle v = dao.getSCIX1ById(id);
                mu = v.getMu_owner();
                model.addAttribute("supplyItem", v);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX1);
            break;
            case CLASSIX2:                
                Watercraft wc = dao.getSCIX2ById(id);
                mu = wc.getMu_owner();
                model.addAttribute("supplyItem", wc);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX2);
            break;
            case CLASSIX3:                
                OutboardEngine oe = dao.getSCIX3ById(id);
                mu = oe.getMu_owner();
                model.addAttribute("supplyItem", oe);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSIX3);
                
            break;
            case CLASSX:                
                SupplyItemCX supplyItemCX = dao.getSCXById(id);
                mu = supplyItemCX.getMu_owner();
                model.addAttribute("supplyItem", supplyItemCX);
                model.addAttribute("supplyClass", SupplyClassEnum.CLASSX);
                
            break;
        
        }
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("subContent", "mu_management/supplyInfo");
        //Atualiza o model com a parte de menu e retorna 
        return new ModelAndView("layout", FragmentController.checkMUMenu(mu, model));
    }    
  
   //TODO melhorar a passagem de parametro enviando o enum direto para o controller e retirando esse método
//    private SupplyClassEnum testClass(String testClass){
//        System.out.println("\n\n\n\n\n\nTESTANDO SUPPLY: "+testClass);
//        if(testClass.contains("Classe X")){
//            return SupplyClassEnum.CLASSX;}
//        if(testClass.contains("Classe IX.3")){
//            return SupplyClassEnum.CLASSIX3;}
//        if(testClass.contains("Classe IX.2")){
//            return SupplyClassEnum.CLASSIX2;}
//        if(testClass.contains("Classe IX.1")){
//            return SupplyClassEnum.CLASSIX1;}
//        if(testClass.contains("Classe IX")){
//            return SupplyClassEnum.CLASSIX;}
//        if(testClass.contains("Classe VIII")){
//            return SupplyClassEnum.CLASSVIII;}
//        if(testClass.contains("Classe VII")){
//            return SupplyClassEnum.CLASSVII;}
//        if(testClass.contains("Classe VI")){
//            return SupplyClassEnum.CLASSVI;}
//        if(testClass.contains("Classe V")){
//            return SupplyClassEnum.CLASSV;}
//        if(testClass.contains("Classe IV")){
//            return SupplyClassEnum.CLASSIV;}
//        if(testClass.contains("Classe III.3")){
//            return SupplyClassEnum.CLASSIII3;}
//        if(testClass.contains("Classe III.2")){
//            return SupplyClassEnum.CLASSIII2;}
//        if(testClass.contains("Classe III.1")){
//            return SupplyClassEnum.CLASSIII1;}
//        if(testClass.contains("Classe III")){
//            return SupplyClassEnum.CLASSIII;}
//        if(testClass.contains("Classe II")){
//            return SupplyClassEnum.CLASSII;}        
//        if(testClass.contains("Classe I")){
//            return SupplyClassEnum.CLASSI;}
//        System.out.println("Saiu sem classe definida");
//        return SupplyClassEnum.SCLASS;
//    }
    
    //  MÉTODOS ESPECÍFICOS POR SUPRIMENTO
    
     /**Salva um item genérico de infomação*/    
    @RequestMapping(value = "/saveSupplyCI", method = RequestMethod.POST)
    public ModelAndView saveSupplyCI(@Valid @ModelAttribute("supplyItemCI") SupplyItemCI supplyItemCI, BindingResult result, RedirectAttributes attr, ModelMap model) {
        MilitaryUnit mu = muDao.findById(supplyItemCI.getMu_owner().getId());
        supplyItemCI.setMu_owner(mu);
        System.out.println("\n\n\n\n\n\n\n\n Military Unit:"+supplyItemCI.getMu_owner());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSI);
//        modelAndView.addObject("id",supplyItemCI.getMu_owner().getId());
//        modelAndView.addObject("supClass",supplyItemCI.getSupply_class().getDescription());
//        //System.out.println("\n\n\n\n\nId da unidade rel:"+muInfo.getRelated_mu().getId());
//        System.out.println("\n\n\nEntrou no salvamenteo para: "+supplyItemCI.toString());
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
//        //MilitaryUnit mu = daoMU.findById(muInfo.getRelated_mu().getId());
//        //model.addAttribute("militaryUnit", supplyItemCI.getMu_owner());
//       //muInfo.setRelated_mu(mu);
        supplyItemCI.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCI.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCI(supplyItemCI);
        System.out.println("ENTROU, SUPPLYITEM: "+supplyItemCI.toString());
        return modelAndView;
    }
   
    
    /**Efetiva a atualização*/
    @PostMapping("/updateSupplyCI")
    public ModelAndView updateSupplyCI(@Valid @ModelAttribute("supplyItemCI") SupplyItemCI supplyItemCI, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCI.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSI);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCI.setMil_modifier(currentUser.getUser().getMilitary());
        
        supplyItemCI.setLast_update(LocalDate.now());
        dao.updateSCI(supplyItemCI);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
     
    /**Salva um item genérico de classe II*/    
    @RequestMapping(value = "/saveSupplyCII", method = RequestMethod.POST)
    public ModelAndView saveSupplyCII(@Valid @ModelAttribute("supplyItemCII") SupplyItemCII supplyItemC2, BindingResult result, RedirectAttributes attr, ModelMap model) {
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
       
    
    /**Efetiva a atualização item genérico de classe II*/
    @PostMapping("/updateSupplyCII")
    public ModelAndView updateSupplyCII(@Valid @ModelAttribute("supplyItemCII") SupplyItemCII supplyItemC2, BindingResult result, RedirectAttributes attr, ModelMap model) {
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
    
        
    /**Salva um item de gás de cozinha*/    
    @RequestMapping(value = "/saveSupplyItemCIII1", method = RequestMethod.POST)
    public ModelAndView saveSupplyItemCIII1(@Valid @ModelAttribute("lpg") SupplyItemCIII1_LPG lpg, BindingResult result, RedirectAttributes attr, ModelMap model) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENTROU SALVAMENTO"+lpg+" \n\n\n\n\n\n\n");
        MilitaryUnit mu = muDao.findById(lpg.getMu_owner().getId());
        lpg.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII1);
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
    
    /**Efetiva a atualização de um item de gás de cozinha*/
    @PostMapping("/updateSupplyItemCIII1")
    public ModelAndView updateSupplyItemCIII1(@Valid @ModelAttribute("lpg") SupplyItemCIII1_LPG lpg, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update viatura: "+lpg.toString());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",lpg.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII1);
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
    
        
    /**Salva um item de gasolina*/    
    @RequestMapping(value = "/saveSupplyItemCIII2", method = RequestMethod.POST)
    public ModelAndView saveSupplyItemCIII2(@Valid @ModelAttribute("supplyItemCIII2") SupplyItemCIII2_Gasoline supplyItemCIII2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        MilitaryUnit mu = muDao.findById(supplyItemCIII2.getMu_owner().getId());
        supplyItemCIII2.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII2);
        //seta a sala de armas que armazena a munição 
        StructureT2 storage = stcDao.getST2ById(supplyItemCIII2.getStorage().getId());
        supplyItemCIII2.setStorage(storage);
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemCIII2.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCIII2.setMil_modifier(currentUser.getUser().getMilitary());
            
        dao.saveSCIII2(supplyItemCIII2);
        return modelAndView;
    }
    
    /**Efetiva a atualização de um item de gasolina*/
    @PostMapping("/updateSupplyItemCIII2")
    public ModelAndView updateSupplyItemCIII2(@Valid @ModelAttribute("supplyItemCIII2") SupplyItemCIII2_Gasoline supplyItemCIII2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCIII2.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII2);
        //se necessário, atualiza o local que armazena a gasolina
        if(dao.getSCIII2ById(supplyItemCIII2.getId()).getStorage().getId()!=supplyItemCIII2.getStorage().getId()){
            StructureT2 storage = stcDao.getST2ById(supplyItemCIII2.getStorage().getId());
            supplyItemCIII2.setStorage(storage);
        }
        
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCIII2.setMil_modifier(currentUser.getUser().getMilitary());
        
        supplyItemCIII2.setLast_update(LocalDate.now());
        dao.updateSCIII2(supplyItemCIII2);
        attr.addFlashAttribute("message", "Cadastro de estoque de gasolina alterado com sucesso.");
        return modelAndView;
    }
    
     /**Salva um item de diesel*/    
    @RequestMapping(value = "/saveSupplyItemCIII3", method = RequestMethod.POST)
    public ModelAndView saveSupplyItemCIII3(@Valid @ModelAttribute("supplyItemCIII3") SupplyItemCIII3_Diesel supplyItemCIII3, BindingResult result, RedirectAttributes attr, ModelMap model) {
        MilitaryUnit mu = muDao.findById(supplyItemCIII3.getMu_owner().getId());
        supplyItemCIII3.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII3);
        //seta a sala de armas que armazena a munição 
        StructureT2 storage = stcDao.getST2ById(supplyItemCIII3.getStorage().getId());
        supplyItemCIII3.setStorage(storage);
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n ERROS ENCONTRADOS"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemCIII3.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCIII3.setMil_modifier(currentUser.getUser().getMilitary());
            
        dao.saveSCIII3(supplyItemCIII3);
        return modelAndView;
    }
    
    /**Efetiva a atualização de um item de diesel*/
    @PostMapping("/updateSupplyItemCIII3")
    public ModelAndView updateSupplyItemCIII3(@Valid @ModelAttribute("supplyItemCIII3") SupplyItemCIII3_Diesel supplyItemCIII3, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCIII3.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIII3);
        //se necessário, atualiza o local que armazena a gasolina
        if(dao.getSCIII2ById(supplyItemCIII3.getId()).getStorage().getId()!=supplyItemCIII3.getStorage().getId()){
            StructureT2 storage = stcDao.getST2ById(supplyItemCIII3.getStorage().getId());
            supplyItemCIII3.setStorage(storage);
        }
        
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCIII3.setMil_modifier(currentUser.getUser().getMilitary());
        
        supplyItemCIII3.setLast_update(LocalDate.now());
        dao.updateSCIII3(supplyItemCIII3);
        attr.addFlashAttribute("message", "Cadastro de estoque de diesel alterado com sucesso.");
        return modelAndView;
    }
    
      /**Salva um item genérico de infomação*/    
    @RequestMapping(value = "/saveSupplyCIV", method = RequestMethod.POST)
    public ModelAndView saveSupplyCIV(@Valid @ModelAttribute("supplyItemCIV") SupplyItemCIV supplyItemCIV, BindingResult result, RedirectAttributes attr, ModelMap model) {
        MilitaryUnit mu = muDao.findById(supplyItemCIV.getMu_owner().getId());
        supplyItemCIV.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSIV);
//        modelAndView.addObject("id",supplyItemCI.getMu_owner().getId());
//        modelAndView.addObject("supClass",supplyItemCI.getSupply_class().getDescription());
//        //System.out.println("\n\n\n\n\nId da unidade rel:"+muInfo.getRelated_mu().getId());
//        System.out.println("\n\n\nEntrou no salvamenteo para: "+supplyItemCI.toString());
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
//        //MilitaryUnit mu = daoMU.findById(muInfo.getRelated_mu().getId());
//        //model.addAttribute("militaryUnit", supplyItemCI.getMu_owner());
//       //muInfo.setRelated_mu(mu);
        supplyItemCIV.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCIV.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCIV(supplyItemCIV);
        System.out.println("ENTROU, SUPPLYITEM: "+supplyItemCIV.toString());
        return modelAndView;
    }
   
    //CLASSE IV
    /**Efetiva a atualização para item de suprimento Classe IV*/
    @PostMapping("/updateSupplyCIV")
    public ModelAndView updateSupplyCIV(@Valid @ModelAttribute("supplyItemCIV") SupplyItemCIV supplyItemCIV, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCIV.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSI);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCIV.setMil_modifier(currentUser.getUser().getMilitary());
        
        supplyItemCIV.setLast_update(LocalDate.now());
        dao.updateSCIV(supplyItemCIV);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
       
    //CLASSE V.1
     /**Salva um item Classe V.1 - Munição*/    
    @RequestMapping(value = "/saveSupplyCV1", method = RequestMethod.POST)
    public ModelAndView saveSupplyCV1(@Valid @ModelAttribute("supplyItemCV1") SupplyItemCV1_munition supplyItemCV1, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //seta a unidade militar para a munição
        MilitaryUnit mu = muDao.findById(supplyItemCV1.getMu_owner().getId());
        supplyItemCV1.setMu_owner(mu);
        //seta a sala de armas que armazena a munição 
        StructureT3 armory = stcDao.getST3ById(supplyItemCV1.getArmory().getId());
        supplyItemCV1.setArmory(armory);
        
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSV1);

        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemCV1.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCV1.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCV1(supplyItemCV1);
        System.out.println("ENTROU, SUPPLYITEM: "+supplyItemCV1.toString());
        return modelAndView;
    }
    
     /**Efetiva a atualização para item de suprimento Classe V.1*/
    @PostMapping("/updateSupplyCV1")
    public ModelAndView updateSupplyCV1(@Valid @ModelAttribute("supplyItemCV1") SupplyItemCV1_munition supplyItemCV1, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCV1.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSV1);
        
        //se necessário, atualiza a sala de armas que armazena o armamento
        if(dao.getSCV1ById(supplyItemCV1.getId()).getArmory().getId()!=supplyItemCV1.getArmory().getId()){
            StructureT3 armory = stcDao.getST3ById(supplyItemCV1.getArmory().getId());
            supplyItemCV1.setArmory(armory);
        }
        
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCV1.setMil_modifier(currentUser.getUser().getMilitary());
        
        supplyItemCV1.setLast_update(LocalDate.now());
        dao.updateSCV1(supplyItemCV1);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    //CLASSE V.2 - Armamento
    /**Salva um item Classe V.2 - Armamento*/    
    @RequestMapping(value = "/saveSupplyCV2", method = RequestMethod.POST)
    public ModelAndView saveSupplyCV2(@Valid @ModelAttribute("supplyItemCV2") SupplyItemCV2_weaponry supplyItemCV2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //seta a unidade militar para a munição
        MilitaryUnit mu = muDao.findById(supplyItemCV2.getMu_owner().getId());
        supplyItemCV2.setMu_owner(mu);
        //seta a sala de armas que armazena o armamento 
        StructureT3 armory = stcDao.getST3ById(supplyItemCV2.getArmory().getId());
        supplyItemCV2.setArmory(armory);
        
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSV2);

        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemCV2.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCV2.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCV2(supplyItemCV2);
        return modelAndView;
    }
    
     /**Efetiva a atualização para item de suprimento Classe V.2 - Armamento*/
    @PostMapping("/updateSupplyCV2")
    public ModelAndView updateSupplyCV2(@Valid @ModelAttribute("supplyItemCV2") SupplyItemCV2_weaponry supplyItemCV2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCV2.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSV2);
        
        //se necessário, atualiza a sala de armas que armazena o armamento
        if(dao.getSCV2ById(supplyItemCV2.getId()).getArmory().getId()!=supplyItemCV2.getArmory().getId()){
            StructureT3 armory = stcDao.getST3ById(supplyItemCV2.getArmory().getId());
            supplyItemCV2.setArmory(armory);
        }
        
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCV2.setMil_modifier(currentUser.getUser().getMilitary());
        
        supplyItemCV2.setLast_update(LocalDate.now());
        dao.updateSCV2(supplyItemCV2);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    //CLASSE VI
        
    //CLASSE VI.1
     /**Salva um item Classe VI.1 - Grupo de Geradores*/    
    @RequestMapping(value = "/saveSupplyCVI1", method = RequestMethod.POST)
    public ModelAndView saveSupplyCVI1(@Valid @ModelAttribute("supplyItemCVI1") SupplyItemCVI1_generator supplyItemCVI1, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //seta a unidade militar para a munição
        MilitaryUnit mu = muDao.findById(supplyItemCVI1.getMu_owner().getId());
        supplyItemCVI1.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI1);

        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemCVI1.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCVI1.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCVI1(supplyItemCVI1);
        return modelAndView;
    }
    
     /**Efetiva a atualização para item de suprimento Classe VI.1 - Grupo de Geradores*/
    @PostMapping("/updateSupplyCVI1")
    public ModelAndView updateSupplyCVI1(@Valid @ModelAttribute("supplyItemCVI1") SupplyItemCVI1_generator supplyItemCVI1, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCVI1.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI1);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCVI1.setMil_modifier(currentUser.getUser().getMilitary());
        supplyItemCVI1.setLast_update(LocalDate.now());
        dao.updateSCVI1(supplyItemCVI1);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    //CLASSE VI
     /**Salva um item genérico Classe VI - Material de Engenharia e de Cartografia*/    
    @RequestMapping(value = "/saveSupplyCVI", method = RequestMethod.POST)
    public ModelAndView saveSupplyCVI(@Valid @ModelAttribute("supplyItemCVI") SupplyItemCVI supplyItemCVI, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //seta a unidade militar para a munição
        MilitaryUnit mu = muDao.findById(supplyItemCVI.getMu_owner().getId());
        supplyItemCVI.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI);

        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemCVI.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCVI.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCVI(supplyItemCVI);
        return modelAndView;
    }
    
     /**Efetiva a atualização para item de suprimento genérico Classe VI - Material de Engenharia e de Cartografia*/
    @PostMapping("/updateSupplyCVI")
    public ModelAndView updateSupplyCVI2(@Valid @ModelAttribute("supplyItemCVI") SupplyItemCVI supplyItemCVI, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCVI.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCVI.setMil_modifier(currentUser.getUser().getMilitary());
        supplyItemCVI.setLast_update(LocalDate.now());
        dao.updateSCVI(supplyItemCVI);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
        
    //CLASSE VI.2
     /**Salva um item Classe VI.2 - GPS*/    
    @RequestMapping(value = "/saveSupplyCVI2", method = RequestMethod.POST)
    public ModelAndView saveSupplyCVI2(@Valid @ModelAttribute("supplyItemCVI2") SupplyItemCVI2_GPS supplyItemCVI2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //seta a unidade militar para a munição
        MilitaryUnit mu = muDao.findById(supplyItemCVI2.getMu_owner().getId());
        supplyItemCVI2.setMu_owner(mu);
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",mu.getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI2);

        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        supplyItemCVI2.setLast_update(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCVI2.setMil_modifier(currentUser.getUser().getMilitary());
        dao.saveSCVI2(supplyItemCVI2);
        return modelAndView;
    }
    
     /**Efetiva a atualização para item de suprimento Classe VI.2 - GPS*/
    @PostMapping("/updateSupplyCVI2")
    public ModelAndView updateSupplyCVI2(@Valid @ModelAttribute("supplyItemCVI2") SupplyItemCVI2_GPS supplyItemCVI2, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/supplyControl/listSupplyItems/{id}/{supplyClass}");
        modelAndView.addObject("id",supplyItemCVI2.getMu_owner().getId());
        modelAndView.addObject("supplyClass", SupplyClassEnum.CLASSVI2);
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        supplyItemCVI2.setMil_modifier(currentUser.getUser().getMilitary());
        supplyItemCVI2.setLast_update(LocalDate.now());
        dao.updateSCVI2(supplyItemCVI2);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    
    
    //CLASSE IX.1 - VIATURA
    /**Salva um item CLASSE IX.1 - Viatura*/    
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
