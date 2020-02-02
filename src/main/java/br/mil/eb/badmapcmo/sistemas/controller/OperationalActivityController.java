/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MilitaryDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.OperationalActivityDao;
import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.FilterList;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.OperationalActivityStatusEnum;
import br.mil.eb.badmapcmo.sistemas.domain.operations.OperationForceMap;
import br.mil.eb.badmapcmo.sistemas.domain.operations.OperationalActivity;
import java.time.LocalDateTime;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller de operações sobre atividades operacionais em PEFs.
 * @see OperationalActivity.java
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("operationalActivityControl")
public class OperationalActivityController {
      
    @Autowired
    private OperationalActivityDao dao;
    
    @Autowired
    private MilitaryUnitDao muDao;
    
    @Autowired
    private MilitaryDao milDao;
        
    @ModelAttribute("oaStatus")
    public OperationalActivityStatusEnum[] operationalActivityStatusEnum(){
            return OperationalActivityStatusEnum.values();
    }
    
    /**Lista atividades operacionais para um pelotão*/
    @GetMapping("/listOperationalActivities/{id}")
    public ModelAndView listOperationalActivities(@PathVariable("id") Long id, ModelMap model) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n Entrou LIST\n\n\n\n\n");
        FilterList filterList = new FilterList();
        filterList.setOption(8);
        MilitaryUnit mu = muDao.findById(id);
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("filterList", filterList);
        model.addAttribute("activities", dao.getByMUId(id));
        model.addAttribute("subMenu", "mu_management/subMenuPEL");       
        model.addAttribute("subContent", "mu_management/listMuOpActivities");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);
    } 
    
    /**Lista atividades operacionais para um pelotão obedecendo um determinado filtro*/
    @PostMapping("/filterOperationalActivities/{id}")
    public ModelAndView filterOperationalActivities(@PathVariable("id") Long id, @ModelAttribute("filterList") FilterList filterList, ModelMap model) {
        MilitaryUnit mu = muDao.findById(id);
        System.out.println("\n\n\n\n\n\n\n\n\n\n Filter OPTION: "+filterList.getOption());
        switch (filterList.getOption()){
            case 1://Ordem alfabética
                model.addAttribute("activities", dao.getAlphabeticalForMUId(id));
                break;
            case 2://Mais recentes primeiro
                model.addAttribute("activities", dao.getNewerFirstForMUId(id));
                break;
            case 3://Mais antigos primeiro
                model.addAttribute("activities", dao.getOlderFirstyForMUId(id));
                break;
            case 4://Em planejamento
                model.addAttribute("activities", dao.getPlanForMUId(id));
                break;
            case 5://Em curso
                model.addAttribute("activities", dao.getDoingForMUId(id));
                break;
            case 6://Concluídas
                model.addAttribute("activities", dao.getDoneForMUId(id));
                break;
            default:
                model.addAttribute("activities", dao.getByMUId(id));


        }
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("filterList", filterList);
        
        model.addAttribute("subMenu", "mu_management/subMenuPEL");       
        model.addAttribute("subContent", "mu_management/listMuOpActivities");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);
    }    
    //Detalhar informações de operação
    
    
    //Criar operação
    /**Chama a tela de cadastro para uma nova atividade operacional*/
    @GetMapping("/registerOperationalActivity/{id}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView registerOperationalActivity(@ModelAttribute("id") Long id, ModelMap model) {
        MilitaryUnit mu = muDao.findById(id);
        OperationalActivity operationalActivity = new OperationalActivity();
        operationalActivity.setMilitary_unit(mu);
        operationalActivity.setSet_force(Boolean.FALSE);        
        model.addAttribute("operationalActivity", operationalActivity);
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("subMenu", "mu_management/subMenuPEL"); 
        model.addAttribute("subContent", "mu_management/addOpActivity");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);           
    }
    
    /**Salva uma atividade operacional e redireciona para edição de efetivo ou listagem de operações conforme escolha do usuário.*/    
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("operationalActivity") OperationalActivity operationalActivity, BindingResult result, RedirectAttributes attr, ModelMap model) {
        //seta redirecionamento inicial para a listagem de operações
        ModelAndView modelAndView = new ModelAndView("redirect:/operationalActivityControl/listOperationalActivities/{id}");//pega o id da unidade
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        } 
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n Entrou3\n\n\n\n\n");
        //recupera a unidade militar do banco 
        MilitaryUnit mu = muDao.findById(operationalActivity.getMilitary_unit().getId());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n UNIDADE MILITAR:"+mu.getMU_name());
        operationalActivity.setMilitary_unit(mu);
        operationalActivity.setLast_update(LocalDateTime.now());               
        operationalActivity.setStatusByDate();               
        modelAndView.addObject("id",mu.getId());
        //seta o responsável pela alteração/inclusão
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        operationalActivity.setLast_author(currentUser.getUser().getMilitary());
        //persiste a operação no banco
        dao.save(operationalActivity);    
        //verifica se foi marcada a opeção de editar pelotão escalado para a operação
        if(operationalActivity.getSet_force()==Boolean.TRUE){//redireciona para edição de efetivo    
            attr.addFlashAttribute("message", "Atividade operacional cadastrada com sucesso. Adicione os militares do efetivo. ");
            modelAndView = new ModelAndView("redirect:/operationalActivityControl/editForce/{id}");
            modelAndView.addObject("id",operationalActivity.getId());    
            System.out.println("\n\n\n\n\n\nIndo para edição de efetivo");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n ID esperado:"+operationalActivity.getId());
        }else{
            attr.addFlashAttribute("message", "Atividade operacional cadastrada com sucesso.");
        }
        return modelAndView;
    }
    //Editar efetivo da operação
    /**Chama tela com form para atualização*/
    @GetMapping("/editForce/{id}")//TODO atualizar o nome do método no html
    public ModelAndView editForce(@PathVariable("id") Long id, ModelMap model) { 
        OperationalActivity operationalActivity = dao.getById(id);
        operationalActivity.setSet_force(Boolean.FALSE);
        model.addAttribute("operationalActivity", operationalActivity);
        model.addAttribute("militaryUnit",operationalActivity.getMilitary_unit());
        model.addAttribute("subMenu", "mu_management/subMenuPEL"); 
        model.addAttribute("subContent", "mu_management/editOpForce");
        model.addAttribute("content", "mu_management/subLayout");
        List<Military> militaryList = dao.getAllMilitaryOnForceMap(operationalActivity.getId());
        model.addAttribute("militaryList",militaryList);
        Military military = new Military();
        model.addAttribute("military",military);
        return new ModelAndView("layout", model);  
    }
    /**Chama tela com form para atualização*/
    @PostMapping("/addMilitary/{id}")//TODO atualizar o nome do método no html
    public ModelAndView addMilitary(@PathVariable("id") Long id, @Valid @ModelAttribute("military") Military military, ModelMap model, RedirectAttributes attr) { 
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n ADICIONANDO MILITAR");
        OperationalActivity operationalActivity = dao.getById(id);
        //Recupera o objeto military completo do banco buscando por identidade militar
        Military military_repo = milDao.getByIdt(military.getMilitary_identitiy());
        //Idt não encontrada no banco, volta para a edição de pelotão
        if(military_repo==null){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n Idt não encontrada no banco, volta para a edição de pelotão");
            attr.addFlashAttribute("message", "Identidade militar informada não consta no sistema.");
            ModelAndView modelAndView = new ModelAndView("redirect:/operationalActivityControl/editForce/{id}");
            modelAndView.addObject("id",operationalActivity.getId());
            return modelAndView;
        }
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n MILITAR EXISTE NO BANCO:"+military_repo.getName());
        //Caso exista, verifica se o militar pertence ao pelotão
        if(military_repo.getMilitaryUnit().getId().equals(operationalActivity.getMilitary_unit().getId())){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n o militar pertence a mesma unidade da operação, verifica se já não foi adicionado antes");
            //Se o militar pertence a mesma unidade da operação, verifica se já não foi adicionado antes
            if(dao.verifyMilitaryOnForceMap(military.getId(), operationalActivity.getId())){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n MILITAR JÁ ADICIONADO");
                attr.addFlashAttribute("message", "Militar já consta adicionado para a operação.");
                
            }else{//Caso não tenha sido adicionado antes, inclui na lista de militares
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n  MILITAR não tenha sido adicionado antes, inclui na lista de militares");
                attr.addFlashAttribute("message", "Militar adicionado para a operação.");
                OperationForceMap operationForceMap = new OperationForceMap(military_repo,operationalActivity);
                System.out.println("\n\n\n\n\n\n\n\n SALVANDO FORCEMAP");
                dao.saveInForceMap(operationForceMap);    
            }   
        }else{
            attr.addFlashAttribute("message", "Militar não pertence ao pelotão da operação.");
                
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n MILITAR de pelotão: "+military_repo.getMilitaryUnit().getMU_name()+"  comparado com pelotão:"+operationalActivity.getMilitary_unit().getMU_name());
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/operationalActivityControl/editForce/{id}");
        modelAndView.addObject("id",operationalActivity.getId());
        return modelAndView;  
    }
    /**Realiza a remoção de um militar do efetivo escalado para uma atividade operacional específica*/
    @GetMapping("/removeMilitary/{milId}/{oaId}")//TODO atualizar o nome do método no html
    public ModelAndView removeMilitary(@PathVariable("milId") Long milId, @PathVariable("oaId") Long oaId, ModelMap model, RedirectAttributes attr) { 
        OperationalActivity operationalActivity = dao.getById(oaId);
        operationalActivity.setSet_force(Boolean.FALSE);
             
        if(dao.delete(oaId,milId)){
            attr.addFlashAttribute("message", "Militar removido do efetivo escalado para a atividade operacional.");
        }else{
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente. Caso o erro persista, abra um chamado.");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/operationalActivityControl/editForce/{id}");
        modelAndView.addObject("id",operationalActivity.getId());
        return modelAndView;
    }
    
    
    
    
    //Editar operação
    /**Chama tela com form para atualização*/
    @GetMapping("/preUpdate/{id}")//TODO atualizar o nome do método no html
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {        
        OperationalActivity operationalActivity = dao.getById(id);
        operationalActivity.setSet_force(Boolean.FALSE);
        model.addAttribute("operationalActivity", operationalActivity);
        model.addAttribute("militaryUnit",operationalActivity.getMilitary_unit());
        model.addAttribute("subMenu", "mu_management/subMenuPEL"); 
        model.addAttribute("subContent", "mu_management/addOpActivity");
        model.addAttribute("content", "mu_management/subLayout");
        
        return new ModelAndView("layout", model);  
    }
    
    
    
    /**Efetiva a atualização*/
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("operationalActivity") OperationalActivity operationalActivity, BindingResult result, RedirectAttributes attr, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/operationalActivityControl/listOperationalActivities/{id}");
        modelAndView.addObject("id",operationalActivity.getMilitary_unit().getId());
        //Verifica coerencia entre datas conhecidas
//        if(operationalActivity.getEstimated_start_date()!=null&&operationalActivity.getEstimated_finish_date()!=null){
//            if(operationalActivity.getEstimated_start_date().compareTo(operationalActivity.getEstimated_finish_date())>=0){
//                modelAndView = new ModelAndView("redirect:/operationalActivityControl/preUpdate/{id}");
//                modelAndView.addObject("id",operationalActivity.getId());
//                return modelAndView; 
//            }
//        }
//        if(operationalActivity.getReal_start_date()!=null&&operationalActivity.getReal_finish_date()!=null){
//            if(operationalActivity.getReal_start_date().compareTo(operationalActivity.getReal_finish_date())>=0){
//                modelAndView = new ModelAndView("redirect:/operationalActivityControl/preUpdate/{id}");
//                modelAndView.addObject("id",operationalActivity.getId());
//                return modelAndView;
//            }
//        }
        
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return modelAndView;
        }  
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        operationalActivity.setLast_author(currentUser.getUser().getMilitary());
        
        operationalActivity.setLast_update(LocalDateTime.now());
        operationalActivity.setStatusByDate();
        
        dao.update(operationalActivity);
        
        if(operationalActivity.getSet_force()==Boolean.TRUE){//redireciona para edição de efetivo        
            System.out.println("\n\n\n\n\n\nIndo para edição de efetivo");
            modelAndView = new ModelAndView("redirect:/operationalActivityControl/editForce/{id}");
            modelAndView.addObject("id",operationalActivity.getId());
            attr.addFlashAttribute("message", "Atividade operacional alterada com sucesso. Edite os militares do efetivo.");
        }else{
            attr.addFlashAttribute("message", "Atividade operacional alterada com sucesso.");
        }
        return modelAndView;
    }
    
    //Excluir operação
    /**Deleta uma atividade operacional*/
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        Long muId = dao.getById(id).getMilitary_unit().getId();
       
        dao.deleteOperationalActivity(id);
        attr.addFlashAttribute("message", "Atividade operacional excluída com sucesso.");
        
        ModelAndView modelAndView = new ModelAndView("redirect:/operationalActivityControl/listOperationalActivities/{id}");
        modelAndView.addObject("id",muId);
        
       
        return modelAndView;
    }
    //Info de cadastro
    /**Mostra resumo de informações cadastradas*/
    @GetMapping("/moreInfo/{id}")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView moreInfo(@ModelAttribute("id") Long id, ModelMap model) {            
        MilitaryUnit mu = dao.getById(id).getMilitary_unit();        
        model.addAttribute("operationalActivity", dao.getById(id));
        model.addAttribute("militaryUnit", mu);
        model.addAttribute("subContent", "mu_management/opActivityInfo");
        model.addAttribute("subMenu", "mu_management/subMenuPEL"); 
        model.addAttribute("content", "mu_management/subLayout");
        //Atualiza o model com a parte de menu e retorna 
        return new ModelAndView("layout", model);
    } 
    
    
}
