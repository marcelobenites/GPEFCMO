/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.CommandLogDao;
import br.mil.eb.badmapcmo.sistemas.dao.CommanderDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.ZoneDao;
import br.mil.eb.badmapcmo.sistemas.domain.CommandLog;
import br.mil.eb.badmapcmo.sistemas.domain.Commander;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.CommandCategoryEnum;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Registro de comando 
 * @author tenbenites
 */
@Controller
@RequestMapping("commandControl")
public class CommandController {
    
    @Autowired
    private CommanderDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;

    @Autowired
    private MilitaryDao milDao;
    
    @Autowired
    private ZoneDao zoneDao;
    
    @Autowired
    private CommandLogDao cmdLogDao;
    
    @ModelAttribute("role")
    public CommandCategoryEnum[] commandCategoryEnum(){
            return CommandCategoryEnum.values();
    }
    
    
    
    
    /**Chama tela de edição de informações de uma unidade*/
    @GetMapping("/listCommanders/{id}")
    public ModelAndView listCommanders(@PathVariable("id") Long id, ModelMap model) {
        MilitaryUnit militaryUnit = muDao.findById(id);
        
        model.addAttribute("militaryUnit", militaryUnit);
        System.out.println("\n\n\n\n\n\n Unidade militar"+militaryUnit.getMU_name());
        model.addAttribute("commanders", cmdLogDao.getCmdByMU(militaryUnit.getId()));
        model.addAttribute("subcommanders", cmdLogDao.getSCmdByMU(militaryUnit.getId()));  
        model.addAttribute("subContent", "mu_management/listCommanders");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", FragmentController.checkMUMenu(militaryUnit, model));
            
    } 
    
    
    /**Chama a tela de cadastro para uma nova informação*/
    @GetMapping("/registerCmd/{id}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView registerCmd(@ModelAttribute("id") Long id, ModelMap model, RedirectAttributes attr) {
        MilitaryUnit militaryUnit = muDao.findById(id);  
        //commander.getMilitary().setMilitaryUnit(militaryUnit);
        
        //System.out.println("\n\n\n\n\n\n INÍCIO REGISTRO DE NOVO COMANDANTE, UNIDADE MILITAR:"+commander.getMilitary().getMilitaryUnit().getMU_name());
        Commander commander = new Commander();
        Military military = new Military();
        
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("cmdStatus", "new");
        model.addAttribute("commander", commander);
        model.addAttribute("military", military);
        model.addAttribute("militaryUnit", militaryUnit);        
        model.addAttribute("subContent", "mu_management/addCmd");
        model.addAttribute("content", "mu_management/subLayout");
        model.addAttribute("zones", zoneDao.getAll());
        return new ModelAndView("layout", FragmentController.checkMUMenu(militaryUnit, model));           
    }
    
    /**Chama a tela de cadastro para uma nova informação*/
    @PostMapping("/fullRegisterCmd/{id}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView fullRegisterCmd(@ModelAttribute("id") Long id, BindingResult result, @ModelAttribute("military") Military military, ModelMap model, RedirectAttributes attr) {
        ModelAndView modelAndView = new ModelAndView("redirect:/commandControl/listCommanders/{id}");
        MilitaryUnit militaryUnit = muDao.findById(id);  
        Commander commander = new Commander();
        military = milDao.getByIdt(military.getMilitary_identitiy());
        
        if (result.hasErrors()){
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente. Se o erro persistir abra um chamado.");
            
        }else if(military!=null){
            attr.addFlashAttribute("message", "Dados recuperados. Qualquer alteração será salva também no cadastro de militar!");
            model.addAttribute("military", military); 
            commander.setMilitary(military);
        }else if(military==null){
            attr.addFlashAttribute("message", "Cadastro de militar não localizado. Verifique se o novo comandante não é militar de uma unidade superior.");         
            System.out.println("\n\n\n\n\n\n NÃO RECUPEROU CADASTRO PRÉVIO DE MILITAR");
            modelAndView.addObject("id",id);
            return modelAndView;
        } 
        if(military.getMilitaryUnit().getId()!=id){
            attr.addFlashAttribute("message", "ATENÇÃO! Unidade Militar atual do cadastro deste militar diferente da unidade na qual o cadastro de comando está em andamento.");
                       
        }
        
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("cmdStatus", "recovered");
        model.addAttribute("zones", zoneDao.getAll());
        model.addAttribute("militaryUnit", militaryUnit);
        model.addAttribute("commander", commander);       
        model.addAttribute("subContent", "mu_management/addCmd");
        model.addAttribute("content", "mu_management/subLayout");
        attr.addFlashAttribute("message", "Usuário alterado com sucesso.");
        //System.out.println("\n\n\n\n\n\n CHAMANDO TELA DE NOVO REGISTRO DE COMANDANTE: msg - "+attr.toString());
        System.out.println("\n\n\n\n\n\n CHAMANDO TELA DE NOVO REGISTRO DE COMANDANTE: "+commander.toString());
        return new ModelAndView("layout", FragmentController.checkMUMenu(militaryUnit, model));           
    }
    
    /**Salva um registro de Cmd/SCmd*/    
    @PostMapping("/saveCmd/{mid}")
    public ModelAndView saveCmd(@Valid @ModelAttribute("commander") Commander commander, @ModelAttribute("mid") Long mid, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //TODO: puxa dados restantes do cadastro prévio de militar, pois o form está incompleto para este cadastro
//        Military mil = milDao.getByIdt(commander.getMilitary().getMilitary_identitiy());
//        if(mil!=null){
//            commander.setMilitary(milDao.getByIdt(commander.getMilitary().getMilitary_identitiy()));
//        }
        ModelAndView modelAndView = new ModelAndView("redirect:/commandControl/listCommanders/{id}");
        modelAndView.addObject("id",mid);
        Military holder = milDao.getByIdt(commander.getHolder().getMilitary_identitiy());
        if(holder==null){
            attr.addFlashAttribute("message", "Identidade do padrinho não existe! Tente novamente.");
            return modelAndView;
        }
        commander.setHolder(holder);
        
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }
        System.out.println("\n\n\n\n ADICIONOU COMANDO AO LOG DA UM!");
        //Criação/atualização de cadastro de militar associado como comandante no registro de comando
        //Tenta recuperar o cadastro pré-existente no banco
        Military military = milDao.getByIdt(commander.getMilitary().getMilitary_identitiy());
        //Cadastro não existe: Comandante ainda não cadastrado como militar, salva um novo militar
        System.out.println("\n\n\n\n\n\n VERIFICANDO CADASTRO");
        if(military==null){     
            System.out.println("\n\n\n\n\n\n\n\n\n\n\nCadastro não existe: Comandante ainda não cadastrado como militar, salva um novo militar");
       
            milDao.save(commander.getMilitary());
        }else if(!military.equals(commander.getMilitary())){//Comandante já possui cadastro de militar, mas ele foi alterado no caastramento de comando
            System.out.println("\n\n\n\n\n\n\n\n\n\n\nComandante já possui cadastro de militar, mas ele foi alterado");
        
            //milDao.update(commander.getMilitary());        
        }else{
            System.out.println("\n\n\n\n\n\n\n Comandante já possui cadastro de militar e NADA foi alterado");
                
        }
        dao.save(commander); 
        CommandLog cl = new CommandLog();
        cl.setMU_cmd(commander);
        cl.setMilitaryUnit(muDao.findById(mid));
        cmdLogDao.save(cl);
        return modelAndView;
    }
    
    
     /**Chama a tela de atualização para um comando cadastrado*/
    @GetMapping("/preUpdate/{id}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView preUpdate(@Valid @ModelAttribute("id") Long id, BindingResult result, ModelMap model, RedirectAttributes attr) {
        //Recupera registro de comando pelo id
        Commander commander = dao.getById(id); 
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nENTROU PREUPDATE commander:"+commander.getMilitary().toString());
        if (commander==null){//se não achou registro 
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente. Se o erro persistir abra um chamado.");
            System.out.println("\n\n\n\n\n\n\n REGISTRO DE COMANDO NÃO ENCONTRADO!!!");
            return new ModelAndView("redirect:/commandControl/listCommanders/{id}");
        }else {
            attr.addFlashAttribute("message", "Dados recuperados. Qualquer alteração será salva também no cadastro de militar!");
        }
        
        model.addAttribute("units", muDao.getAll());       
        model.addAttribute("cmdStatus", "updating");
        model.addAttribute("zones", zoneDao.getAll());
        model.addAttribute("militaryUnit", commander.getMilitary().getMilitaryUnit());
        model.addAttribute("commander", commander);       
        model.addAttribute("subContent", "mu_management/addCmd");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", FragmentController.checkMUMenu(commander.getMilitary().getMilitaryUnit(), model));           
    }
    
    
    /**Salva um registro de Cmd/SCmd*/    
    @PostMapping("/updateCmd/{mid}")
    public ModelAndView updateCmd(@Valid @ModelAttribute("commander") Commander commander, @ModelAttribute("mid") Long mid, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/commandControl/listCommanders/{id}");
        if(commander.getHolder().getId()==null){
            System.out.println("\n\n\n\n\n\n\nNão achou o militar");
        }
        modelAndView.addObject("id",mid); 
        Commander cDB = dao.getById(commander.getId());
        commander.getMilitary().getAddress().setZone(zoneDao.getById(commander.getMilitary().getAddress().getZone().getId()));
     
        commander.getMilitary().setMilitaryUnit(muDao.findById(commander.getMilitary().getMilitaryUnit().getId()));
         
        commander.setHolder(milDao.getByIdt(commander.getHolder().getMilitary_identitiy()));
         
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        }   
//        System.out.println("\n\n\n\n\n\n\n\n\n\nAtualizando comando - militar:"+commander.getMilitary().toString());
//        System.out.println("\n\n\n\n\n\n\n\n\n\nAtualizando comando - holder:"+commander.getHolder().toString());
        attr.addFlashAttribute("message", "Cadastro de comando atualizado com sucesso.");
        dao.update(commander);
        return modelAndView;
    }
    
    
    
    /**Deleta um comando cadastrado*/
    @GetMapping("/delete/{id}/{muId}")
    public ModelAndView delete(@PathVariable("id") Long id, @ModelAttribute("muId") Long muId, RedirectAttributes attr) {
        ModelAndView modelAndView = new ModelAndView("redirect:/commandControl/listCommanders/{muId}");        
        //Remove o log do comando
        Boolean control = cmdLogDao.removeCommandLogForMilitary(id);
        if(!control){
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente. Se o erro persistir abra um chamado.");
            return modelAndView;
        }
        //Deleta o comando
        dao.delete(id);
        attr.addFlashAttribute("message", "Registro de comando excluído com sucesso.");
        modelAndView.addObject("muId",muId);
        return modelAndView;
    }
     
}
