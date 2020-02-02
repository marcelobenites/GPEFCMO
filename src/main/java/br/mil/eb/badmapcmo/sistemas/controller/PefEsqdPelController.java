/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MUAdditionalFractionsDao;
import br.mil.eb.badmapcmo.sistemas.dao.MUAdditionalStaffDao;
import br.mil.eb.badmapcmo.sistemas.dao.MUDependenciesDao;
import br.mil.eb.badmapcmo.sistemas.dao.MUInfoDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.RegionMapDao;
import br.mil.eb.badmapcmo.sistemas.dao.ZoneDao;
import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import br.mil.eb.badmapcmo.sistemas.domain.MUDependencies;
import br.mil.eb.badmapcmo.sistemas.domain.MUInfo;
import br.mil.eb.badmapcmo.sistemas.domain.MURegionMap;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.PELDST.MUAdditionalStaff;
import br.mil.eb.badmapcmo.sistemas.domain.Zone;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.Region;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.DependencyTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.InfoTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe de controle de operações envolvendo exclusivamente PEF/DST.
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("pefEsqdPelControl")
public class PefEsqdPelController {
    //Última lista de dependencias recuperada do banco   
    private List<MUDependencies> lastDepTree;
    
    @Autowired
    private MilitaryUnitDao muDao;
    
    @Autowired
    private MUInfoDao dao;
    
    @Autowired
    private RegionMapDao daoRMap;
    
    @Autowired
    private MUDependenciesDao daoMUD;
      
    @Autowired
    private MUAdditionalStaffDao asDao;
    
    @Autowired
    private MUAdditionalFractionsDao afDao;
    
    @Autowired
    private ZoneDao zoneDao;
      
    @ModelAttribute("infoTypes")
    public InfoTypeEnum[] infoTypeEnum(){
            return InfoTypeEnum.values();
    }
    
    @ModelAttribute("muTypes")
	public MilitaryUnitTypeEnum[] militaryUnitType(){
		return MilitaryUnitTypeEnum.values();
    }
    

             
    /**
     * Chama o ambiente de gerenciamento de informações específicas de pelotões 
     * e destacamentos - A página inicial é de apresentação de informações gerais
     * da unidade.
     */
    @GetMapping("/navigateMU/{id}")
    public ModelAndView navigateMU(@PathVariable("id") Long id, ModelMap model) {
        //System.out.println("ABRINDO NAVEGADOR de Unidades, buscando unidade id: "+id);
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit", mu);
        //Gera a cadeia de subordinação para a unidade     
        this.lastDepTree = daoMUD.getAll();//atualiza a lista se dependencias para o último id consultado
        model.addAttribute("subordinationChain", this.getSubordinationChainById(mu));
        
        model.addAttribute("regionZones", daoRMap.getZonesByMUId(id));
        model.addAttribute("muAdditionalFractions", afDao.getAllByMUId(id));
        if(afDao.getAllByMUId(id)==null)
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n VERIFICANDO FRAÇÕES: NULL");
       // model.addAttribute("information", dao.getByMUId(id));
       // model.addAttribute("subordinatedGroup", daoMUD.getSubordinatedById(id));
       // model.addAttribute("superiorGroup", daoMUD.getSuperiorsById(id));
       
        //abre a página de gerenciamento restrita a PEFs e Pelotões
        if(MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type())||MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())){
            model.addAttribute("subMenu", "mu_management/subMenuPEL");
            model.addAttribute("subContent", "mu_management/managePELs");
        }
        //Separa os menus por tipo de unidade militar
        else{        
            model.addAttribute("subMenu", "mu_management/subMenuMU");
            model.addAttribute("subContent", "mu_management/muInfo");
        }
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);
    }  
    
    
    /**
     * Chama tela de edição de informações de uma unidade
     */
    @GetMapping("/listMUInfo/{id}")
    public ModelAndView listMUInfo(@PathVariable("id") Long id, ModelMap model) {
            System.out.println("ABRINDO edição de informações de Unidades, buscando unidade id: "+id);
            MilitaryUnit mu = muDao.findById(id);
            model.addAttribute("militaryUnit", mu);
            model.addAttribute("information", dao.getByMUId(id));
            //Verifica se é um pelotão ou OM
             if(MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type())||MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())){
            model.addAttribute("subMenu", "mu_management/subMenuPEL");       
        }else{        
            model.addAttribute("subMenu", "mu_management/subMenuMU");
        }
            
            model.addAttribute("subContent", "mu_management/listMuInfo");
            model.addAttribute("content", "mu_management/subLayout");
            return new ModelAndView("layout", model);
            
    }    
	
        
    /**Chama a tela de cadastro para uma nova informação*/
    @GetMapping("/registerMuInfo/{id}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView registerMuInfo(@ModelAttribute("id") Long id, ModelMap model) {
        System.out.println("Unidade militar de id: "+id);       
        //System.out.println("Informação de id: "+muInfo.getId());       
        MilitaryUnit mu = muDao.findById(id);
        MUInfo muInfo = new MUInfo();
        muInfo.setRelated_mu(mu);
        model.addAttribute("muInfo", muInfo);
        model.addAttribute("militaryUnit", mu);
         if(MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type())||MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())){
            model.addAttribute("subMenu", "mu_management/subMenuPEL");       
        }else{        
            model.addAttribute("subMenu", "mu_management/subMenuMU");
        }
        
        model.addAttribute("subContent", "mu_management/addMuInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);           
    }
        
    /**Salva um item genérico de infomação*/    
    @PostMapping("/saveInfo")
    public ModelAndView saveInfo(@Valid @ModelAttribute("muInfo") MUInfo muInfo, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/listMUInfo/{id}");
        modelAndView.addObject("id",muInfo.getRelated_mu().getId());
        //System.out.println("\n\n\n\n\nId da unidade rel:"+muInfo.getRelated_mu().getId());
        //System.out.println("\n\n\nEntrou no salvamenteo para: "+muInfo.toString());
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
        MilitaryUnit mu = muDao.findById(muInfo.getRelated_mu().getId());
        model.addAttribute("militaryUnit", mu);
        muInfo.setRelated_mu(mu);
        
        muInfo.setCreationDate(LocalDate.now());
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        muInfo.setMilitary_author(currentUser.getUser().getMilitary());
        dao.save(muInfo);
        return modelAndView;
    }
        
    /**Retorna a busca de informações por título*/
    @GetMapping("/findMUInfoByTitle/{id}")//TODO organizar todas as filtragens para respeitarem as dependencias entre unidades
    public ModelAndView findMUInfoByTitle(@RequestParam(value = "title") String title, @ModelAttribute("id") Long id, ModelMap model) {
        model.addAttribute("information", dao.getByTitle(title,id));
        System.out.println("\n\n\n\nLista final:"+dao.getByTitle(title,id));
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("subContent", "mu_management/listMuInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);
    }    
    
    /**Chama tela com form para atualização de informações gerenciais que devem 
     * ser preenchidas pelos comandantes de cada PEF/Esqd/Pel C Mec Dst.     * 
     */
    @GetMapping("/preUpdatePELAdmInfo/{id}")
    public ModelAndView preUpdatePELAdmInfo(@PathVariable("id") Long id, ModelMap model) {        
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit",mu);
        model.addAttribute("zones", zoneDao.getAll());
        model.addAttribute("subContent", "mu_management/addPELAdmInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", FragmentController.checkMUMenu(mu, model));  
    }
    
    /**Chama tela com form para atualização*/
    @GetMapping("/preUpdateMUInfo/{id}")//TODO atualizar o nome do método no html
    public ModelAndView preUpdateMUInfo(@PathVariable("id") Long id, ModelMap model) {        
        //System.out.println("\n\n\n\n\n\n Info de id:"+id+"  é: \n"+dao.findById(id).toString());
        MUInfo muInfo = dao.getById(id);
        model.addAttribute("muInfo", muInfo);
        model.addAttribute("militaryUnit",muInfo.getRelated_mu());
        
        model.addAttribute("subContent", "mu_management/addMuInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", FragmentController.checkMUMenu(muInfo.getRelated_mu(), model));  
    }
    
    /**Efetiva a atualização*/
    @PostMapping("/updateInfo")
    public ModelAndView updateInfo(@Valid @ModelAttribute("muInfo") MUInfo muInfo, BindingResult result, RedirectAttributes attr, ModelMap model) {
        System.out.println("\n\n\n\n\nEntrou no update, id da unidade: "+muInfo.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/listMUInfo/{id}");
        modelAndView.addObject("id",muInfo.getRelated_mu().getId());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        muInfo.setMilitary_author(currentUser.getUser().getMilitary());
        
        muInfo.setCreationDate(LocalDate.now());
        dao.update(muInfo);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    //INFORMAÇÕES DE ZONA///////////////////////////////////////////////////////
    /**Chama tela de informações sobre os municípios na area do PEL/DST*/
    @GetMapping("/listPelZonesInfo/{id}")
    public ModelAndView listPelZonesInfo(@PathVariable("id") Long id, ModelMap model) {
        System.out.println("ABRINDO edição de informações de Unidades, buscando unidade id: " + id);
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("zones", mu.getZones());
        //Verifica se é um pelotão ou OM
        if (MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type()) || MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())) {
            model.addAttribute("subMenu", "mu_management/subMenuPEL");
        } else {
            model.addAttribute("subMenu", "mu_management/subMenuMU");
        }
        model.addAttribute("subContent", "mu_management/listPelZones");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);
            
    }



    //TRATAMENTO DE EFETIVO/////////////////////////////////////////////////////
    
    /**Chama tela de informações do efetivo de uma unidade (PEL/DST)*/
    @GetMapping("/listStaffInfo/{id}")
    public ModelAndView listStaffInfo(@PathVariable("id") Long id, ModelMap model) {
            System.out.println("ABRINDO edição de informações de Unidades, buscando unidade id: "+id);
            MilitaryUnit mu = muDao.findById(id);
            model.addAttribute("militaryUnit", mu);
            //Verifica se é um pelotão ou OM
             if(MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type())||MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())){
            model.addAttribute("subMenu", "mu_management/subMenuPEL");       
        }else{        
            model.addAttribute("subMenu", "mu_management/subMenuMU");
        }
            model.addAttribute("muAdditionalStaffs", asDao.getByMUId(mu.getId()));
            model.addAttribute("subContent", "mu_management/listMUStaff");
            model.addAttribute("content", "mu_management/subLayout");
            return new ModelAndView("layout", model);
            
    }
    
    //Efetivo geral
     /**Chama tela com form para atualização de efetivo geral de Pel/Dst*/
    @GetMapping("/preUpdateMUGenStaff/{id}")//TODO atualizar o nome do método no html
    public ModelAndView preUpdateMUGenStaff(@PathVariable("id") Long id, ModelMap model) {        
        //System.out.println("\n\n\n\n\n\n Inf
        //Recupera do banco
        model.addAttribute("militaryUnit",muDao.findById(id));        
        model.addAttribute("subContent", "mu_management/editMUGeneralStaff");
        model.addAttribute("subMenu", "mu_management/subMenuPEL");  
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);  
    }
    
    /**Efetiva a atualização*/
    @PostMapping("/updateStaff")
    public ModelAndView updateStaff(@Valid @ModelAttribute("militaryUnit") MilitaryUnit militaryUnit, BindingResult result, RedirectAttributes attr) {
        
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/listStaffInfo/{id}");
        modelAndView.addObject("id",militaryUnit.getId());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        MilitaryUnit militaryUnitAux = muDao.findById(militaryUnit.getId());
        militaryUnitAux.setStaff(militaryUnit.getStaff());
        
        //Registra quem fez a última alteração e quando ela foi feita
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        militaryUnitAux.setLast_author(currentUser.getUser().getMilitary());
        militaryUnitAux.setLast_update(LocalDate.now());
        
        //Atualiza o efetivo da unidade
        muDao.update(militaryUnitAux);
        attr.addFlashAttribute("message", "Infomação de efetivo alterada com sucesso.");
        return modelAndView;
    }
    
    //Efetivos adicionais de outros órgaos

    
     /**Chama tela com form para atualização de efetivo geral de Pel/Dst*/
    @GetMapping("/preAddMUAdtStaff/{id}")//TODO atualizar o nome do método no html
    public ModelAndView preAddMUAdtStaff(@PathVariable("id") Long id, ModelMap model) {        
        //System.out.println("\n\n\n\n\n\n Inf
        //Recupera do banco
        MilitaryUnit militaryUnit = muDao.findById(id);
        model.addAttribute("militaryUnit",militaryUnit);
        //Instancia um novo objeto de efetivo adicional
        MUAdditionalStaff muAdditionalStaff = new MUAdditionalStaff();
        System.out.println("\n\n\n\n\n\n Criou novo efetivo");
        muAdditionalStaff.setRelated_mu(militaryUnit);
        model.addAttribute("muAdditionalStaff",muAdditionalStaff);        
        model.addAttribute("subContent", "mu_management/addMuAdditionalStaff");
        model.addAttribute("subMenu", "mu_management/subMenuPEL");  
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);  
    }
    
    
    /**Salva um novo efetivo adicional de Pel/Dst*/
    @PostMapping("/saveMUAdditonalStaff")//TODO atualizar o nome do método no html
    public ModelAndView saveMUAdditonalStaff(@Valid @ModelAttribute("mUAdditionalStaff") MUAdditionalStaff mUAdditionalStaff, BindingResult result, RedirectAttributes attr, ModelMap model) {        
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/listStaffInfo/{id}");
        modelAndView.addObject("id",mUAdditionalStaff.getRelated_mu().getId());
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        } 
        
        MilitaryUnit militaryUnit = muDao.findById(mUAdditionalStaff.getRelated_mu().getId());
        model.addAttribute("militaryUnit", militaryUnit);
        mUAdditionalStaff.setRelated_mu(militaryUnit);
        
        
        System.out.println("INDO PARA SALVAMENTO NO BANCO!!!!!!!!!!!!!!"+mUAdditionalStaff.toString());
        asDao.save(mUAdditionalStaff);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSAIU DO SALVAMENTO NO BANCO!!!!!!!!!!!!!!");
        return modelAndView; 
    }


    /**Chama tela com form para atualização de efetivo adicional*/
    @GetMapping("/preUpdateMUAdditonalStaff/{id}")//TODO atualizar o nome do método no html
    public ModelAndView preUpdateMUAdditonalStaff(@PathVariable("id") Long id, ModelMap model) {        
        //System.out.println("\n\n\n\n\n\n Info de id:"+id+"  é: \n"+dao.findById(id).toString());
        MUAdditionalStaff mUAdditionalStaff = asDao.getById(id);
        model.addAttribute("mUAdditonalStaff", mUAdditionalStaff);
        model.addAttribute("militaryUnit",mUAdditionalStaff.getRelated_mu());
        
        model.addAttribute("subContent", "mu_management/addMuInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", FragmentController.checkMUMenu(mUAdditionalStaff.getRelated_mu(), model));  
    }
    
    
    
    /**Efetiva uma atualização em efetivo adicional preexistente*/
    @PostMapping("/updateMUAdditonalStaff")
    public ModelAndView updateMUAdditonalStaff(@Valid @ModelAttribute("mUAdditionalStaff") MUAdditionalStaff mUAdditionalStaff, BindingResult result, RedirectAttributes attr, ModelMap model) {
        System.out.println("\n\n\n\n\nEntrou no update de efetivo adicional, id da unidade: "+mUAdditionalStaff.getRelated_mu().getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/listStaffInfo/{id}");
        modelAndView.addObject("id",mUAdditionalStaff.getRelated_mu().getId());
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
       
        asDao.update(mUAdditionalStaff);
        attr.addFlashAttribute("message", "Infomação alterada com sucesso.");
        return modelAndView;
    }
    
    
    /**Deleta um item de efetivo adicional*/
    @GetMapping("/removeMUAdditionalStaff/{id}")
    public ModelAndView removeMUAdditionalStaff(@PathVariable("id") Long id, RedirectAttributes attr) {
        Long muId = asDao.getById(id).getRelated_mu().getId();
        dao.delete(id);
        attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
        
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/listStaffInfo/{id}");
        modelAndView.addObject("id",muId);
        
       
        return modelAndView;
    }
    
    
    /**Deleta um item genérico de informação*/
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        Long muId = dao.getById(id).getRelated_mu().getId();
        dao.delete(id);
        attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
        
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/listMUInfo/{id}");
        modelAndView.addObject("id",muId);
        
       
        return modelAndView;
    }

    /**Chama tela com form para atualização de informações principais de unidades*/
    @GetMapping("/preUpdateMainInfo/{id}")
    public ModelAndView preUpdateMainInfo(@PathVariable("id") Long id, ModelMap model) {
        //Verifica se é PEL ou UM
        MilitaryUnit militaryUnit = muDao.findById(id);
        model.addAttribute("militaryUnit", militaryUnit);
        List<Zone> zones = zoneDao.getAll();
        model.addAttribute("zones", zones); 
        List<MURegionMap> storedRegion = daoRMap.getRegionByMUId(id);
        Region rg;
        
       militaryUnit.setRegions(new ArrayList<Region>(zones.size()));//seta uma list vazia para todas as zonas para militaryUnit.regions.zone_id
        for(int i=0;i<zones.size();i++){//converte em uma lista de regiões
//            militaryUnit.getRegions().get(i).setZone_id(zones.get(i).getId());//recebe um-a-um todos os ids de todas as zonas
            
            rg = new Region();
            rg.setZone_id(zones.get(i).getId());
            rg.setIncluded(Boolean.FALSE);
            militaryUnit.getRegions().add(i, rg);//seta zona como não integrante por default
        
            for(int j=0;j<storedRegion.size();j++){//verifica se consta registro da zona como integrante da região da unidade militar no banco
                System.out.println("\n\n\nComparando storedRegion zone id["+storedRegion.get(j).getZone().getId()+"]  com militaryUnit regions zone id ["+militaryUnit.getRegions().get(i).getZone_id()+"]");
                if(storedRegion.get(j).getZone().getId()==militaryUnit.getRegions().get(i).getZone_id()){
                    militaryUnit.getRegions().get(i).setIncluded(Boolean.TRUE);
                    System.out.println("Entrou vai setar true para região de id:"+militaryUnit.getRegions().get(i).getZone_id());
                }
            }
            
        }
        
        model.addAttribute("subContent", "mu_management/editMUInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", FragmentController.checkMUMenu(militaryUnit, model));  
    
    }
    
    
    /**Efetiva uma atualização de unidade militar*/
    @PostMapping("/updateMainInfo")
    public ModelAndView updateMainInfo(@Valid @ModelAttribute("militaryUnit") MilitaryUnit militaryUnit, BindingResult result, RedirectAttributes attr) {
        ModelAndView modelAndView;
        if (result.hasErrors() && militaryUnit.getMU_type()!=null) {
            System.out.println("\n\n\n\n\n\n\n\n\n ERROR:"+result.toString());
            modelAndView = new ModelAndView ("redirect:pefEsqdPelControl/preUpdateMainInfo/{id}");
            modelAndView.addObject("militaryUnit",militaryUnit.getId());
            return  modelAndView;
        }
        MilitaryUnit mu = muDao.findById(militaryUnit.getId());//pega objeto auxiliar com o estado de informações antigo presente no banco
        //altera informações que podem ser editadas no formulário
        mu.setAddress(militaryUnit.getAddress());
        mu.setMU_name(militaryUnit.getMU_name());
        mu.setMU_tel(militaryUnit.getMU_tel());
        mu.setMU_website(militaryUnit.getMU_website());
        mu.setMU_email(militaryUnit.getMU_email());
        mu.setMU_history(militaryUnit.getMU_history());
        muDao.update(mu); //atualiza unidade com objeto alterado
        attr.addFlashAttribute("message", "Unidade alterada com sucesso.");
        modelAndView = new ModelAndView ("redirect:/pefEsqdPelControl/listMUInfo/{id}");
        modelAndView.addObject("id", mu.getId());
        return modelAndView;
    }
    
    /**Efetiva uma atualização de informações gerais de PEF/Esqd/Pel C Mec Dst*/
    @PostMapping("/updatePELGeneralInfo")
    public ModelAndView updatePELGeneralInfo(@Valid @ModelAttribute("militaryUnit") MilitaryUnit militaryUnit, BindingResult result, RedirectAttributes attr) {
        ModelAndView modelAndView;
        if (result.hasErrors() && militaryUnit.getMU_type()!=null) {
            System.out.println("\n\n\n\n\n\n\n\n\n ERROR:"+result.toString());
            modelAndView = new ModelAndView ("redirect:pefEsqdPelControl/preUpdateMainInfo/{id}");
            modelAndView.addObject("militaryUnit",militaryUnit.getId());
            return  modelAndView;
        }
        MilitaryUnit mu = muDao.findById(militaryUnit.getId());//pega objeto auxiliar com o estado de informações antigo presente no banco
        //altera informações que podem ser editadas no formulário
        mu.setMU_history(militaryUnit.getMU_history());
        mu.setCommander(militaryUnit.getCommander());
        mu.setS_commander(militaryUnit.getS_commander());
        mu.setAddress(militaryUnit.getAddress());
        mu.setMU_email(militaryUnit.getMU_email());
        mu.setMU_tel(militaryUnit.getMU_tel());
        mu.setRitex(militaryUnit.getRitex());
        mu.setRadio_freq(militaryUnit.getRadio_freq());
        mu.setOperational_environment(militaryUnit.getOperational_environment());
        mu.setMain_zones(militaryUnit.getMain_zones());
        mu.setForeign_relation(militaryUnit.getForeign_relation());
        mu.setGeneral_etta(militaryUnit.getGeneral_etta());
        muDao.update(mu); //atualiza unidade com objeto alterado
        attr.addFlashAttribute("message", "Unidade alterada com sucesso.");
        modelAndView = new ModelAndView ("redirect:/pefEsqdPelControl/navigateMU/{id}");
        modelAndView.addObject("id", mu.getId());
        return modelAndView;
    }

    /*************************Funções Auxiliares*******************************/
        /**
     * Retorna a cadeia de comando de uma unidade de Id:id.
     */
    private List<MilitaryUnit>  getSubordinationChainById(MilitaryUnit militaryUnit) {
        List<MilitaryUnit> chainList = new ArrayList<>();
        List auxList = this.getSubordinationList(militaryUnit,chainList);
        Collections.reverse(auxList);      
        return auxList;      
    }
    /**
     * Função recursiva que percorre o grafo da hierarquia a partir da folha e 
     * devolve uma lista encadeada do caminho até a raiz / Obs: para o funcionamento
     * correto desta função a lista de dependencias recuperada do banco deve
     * ser recuperada previamente para a folha do caminho.
     */
    private List<MilitaryUnit> getSubordinationList(MilitaryUnit lastLeaf, List<MilitaryUnit> chainList){
        chainList.add(lastLeaf);//adiciona a nova folha
        MilitaryUnit aux = this.nextDirectSuperior(lastLeaf);//busca o próximo superior
        if(aux==null){//verifica se a folha é raiz
            return chainList;//chegou na raiz  
            
        }else{
            return this.getSubordinationList(aux, chainList);//continua recursivamente na árvore
        }        
    }
    /**
     * Busca na árvore de dependencias atual o próximo superior imediado da unidade.
     */
    private MilitaryUnit nextDirectSuperior(MilitaryUnit militaryUnit){
        for(MUDependencies muD : this.lastDepTree){
            //verifica se há superior direta para a unidade militar nas dependências
            if(muD.getSubordinatedUnit().equals(militaryUnit) && muD.getDep_type().booleanValue()){
                return muD.getSuperiorUnit();
            }
        }
        return null;        
    }
}
