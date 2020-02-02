/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MUDependenciesDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.OperationalActivityDao;
import br.mil.eb.badmapcmo.sistemas.dao.UserDao;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.FilterMap;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.DependencyTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.OperationalActivityStatusEnum;
import br.mil.eb.badmapcmo.sistemas.domain.operations.OperationalActivity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe de controle de interações com o mapa.
 * @author tenbenites
 */
@Controller
@RequestMapping("mapControl")
public class MapController {
    @Autowired
    private OperationalActivityDao oaDao;
    
    @Autowired
    private MUDependenciesDao mudDao;
    
    @Autowired
    private UserDao usrDao;
    
    @Autowired
    MilitaryUnitDao dao;
    /**Abre o mapa inicial mostrando todas as operações e unidades sem filtro*/
    @RequestMapping(value = "/mapping", method = RequestMethod.GET)
    public ModelAndView mapping() {
        ModelMap model = new ModelMap();
        //Recebe do banco todas as operações de todas as unidades 
        List<OperationalActivity> listAux = oaDao.getAllMappedOA();
        List<OperationalActivity> listOA = new ArrayList<OperationalActivity>();
        //Para cada operação da lista
        //id da unidade do usuário
        Long id1 = usrDao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMilitary().getMilitaryUnit().getId();
        listOA = selectOAs(id1);//Carrega todas as operações de todos os pel/esq subordinados 
        //Pega todoas as unidades subordinadas que são tipos de pelotão/esquadrão e devolve para ser utilizado no form de filtro do mapa
        List<MilitaryUnit> units = mudDao.getSubordinatedById(id1);
        for(int i=0;i<units.size();i++){
            if (!(units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.ESQD)||
                    (units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PEF))||
                    (units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PELD)))){
                System.out.println("Removeu -- "+ units.get(i).getMU_name());
                units.remove(i);
                i--;
            
            }
        }
        model.addAttribute("filterMap", new FilterMap(true,true,true,true,true));
        model.addAttribute("units", units);
        model.addAttribute("operations", listOA);
        model.addAttribute("content", "/map");
        return new ModelAndView("layout", model);
    }
    /**Seleciona todas as operações para uma unidade de id:muId*/
    private List<OperationalActivity> selectOAs(Long muId){
        List<OperationalActivity> listAux = oaDao.getAllMappedOA();//carrega todas as operações registradas
       //List<OperationalActivity> listOA = new ArrayList<OperationalActivity>();
        
        for(int i=0;i<listAux.size();i++){//para cada operação
            Long id2 = listAux.get(i).getMilitary_unit().getId();//id da unidade dona da operação
            
            //Se unidades são diferentes, entra em um condicional de comparação
            if(!muId.equals(id2)){
                //Se a unidade da operação é superior ou indiferente à unidade do usuário, remove da lista
                DependencyTypeEnum depAux = mudDao.getDependencyType(muId,id2);
                if((depAux.equals(DependencyTypeEnum.SUB)||depAux.equals(DependencyTypeEnum.NR))){
                    listAux.remove(i);
                    i--;
                }
            }
        }
//        if(!showP){//em planejamento
//                Predicate<OperationalActivity> operationalActivityPredicate = o-> o.getStatus()==OperationalActivityStatusEnum.PLAN ;
//                listOA.removeIf(operationalActivityPredicate);
//        }
//        if(!showDG){
//            Predicate<OperationalActivity> operationalActivityPredicate = o-> o.getStatus()==OperationalActivityStatusEnum.DOING ;
//            listOA.removeIf(operationalActivityPredicate);
//        }
//        if(!showDE){
//            Predicate<OperationalActivity> operationalActivityPredicate = o-> o.getStatus()==OperationalActivityStatusEnum.DONE ;
//            listOA.removeIf(operationalActivityPredicate);
//        }
        return listAux;
    }
    /**Método privado auxiliar que, dada uma seleção de status a serem mostrados, remove operações com status não selecionados*/
    private List<OperationalActivity> filterOAbyStatus(List<OperationalActivity> list, Boolean showPlan, Boolean showDoing, Boolean showDone){
        if(!showPlan){
            for(int i=0;i<list.size();i++){//para cada atividade operacional
                if (list.get(i).getStatus().equals(OperationalActivityStatusEnum.PLAN)){
                        list.remove(i);
                        i--;
                }
            }
        }
        if(!showDoing){
            for(int i=0;i<list.size();i++){//para cada atividade operacional
                if (list.get(i).getStatus().equals(OperationalActivityStatusEnum.DOING)){
                        list.remove(i);
                        i--;
                }
            }
        }
        if(!showDone){
            for(int i=0;i<list.size();i++){//para cada atividade operacional
                if (list.get(i).getStatus().equals(OperationalActivityStatusEnum.DONE)){
                        list.remove(i);
                        i--;
                }
            }
        }
              
        return list;   
    
    }
    /**Filtra uma lista de operações por período*/
    private List<OperationalActivity> filterOAbyPeriod(List<OperationalActivity> list, LocalDate start, LocalDate finish){
        //se data de início não é nula
        if(finish!=null){
            //para cada operação da lista
            for(int i=0;i<list.size();i++){
                //remove a operação caso a operação esteja fora do período selecionado
                if(list.get(i).getReal_start_date()==null||list.get(i).getReal_start_date().isBefore(start)||list.get(i).getReal_start_date().isAfter(finish)||list.get(i).getReal_finish_date().isBefore(start)||list.get(i).getReal_finish_date().isAfter(finish)){
                    list.remove(i);
                    i--;                  
                }
            }
        }else{
         //para cada operação da lista
            for(int i=0;i<list.size();i++){
                //remove a operação caso a operação esteja fora do período selecionado
                if(list.get(i).getReal_start_date()==null||list.get(i).getReal_start_date().isBefore(start)||list.get(i).getReal_start_date().isAfter(finish)){
                    list.remove(i);
                    i--;                  
                }
            }
        } 
        return list;
    }
    
    
    
    /**Atualiza o mapa com opções de filtragem e navegação definidas pelo usuário*/
    @RequestMapping(value = "/filterMap")
    public ModelAndView filterMap(@ModelAttribute("filterMap") FilterMap filterMap, BindingResult result, RedirectAttributes attr, ModelMap model) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ENTROU FILTERMAP - "+filterMap.toString());
        ModelAndView modelAndView = new ModelAndView("redirect:/mapControl/mapping");//pega o id da unidade
        
        if (result.hasErrors()) {
            System.out.println("ERRO na busca!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!"+result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return modelAndView;
        } 
        
        MilitaryUnit mu = dao.findById(usrDao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMilitary().getMilitaryUnit().getId());//pega a unidade militar do usuário
        List<OperationalActivity> listOA = new ArrayList<OperationalActivity>();//Lista das atividades operacionais
        List<MilitaryUnit> listMU = new ArrayList<MilitaryUnit>();//Lista de unidades militares a serem mostradas no mapa
        //Verifica se houve solicitação de filtragem por unidade
        if(filterMap.getMilitaryUnit().getId()!=-1){//foi solicitada filtragem se militaryUnit é diferente de null
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n FILTRO EXCLUSIVO para:"+dao.findById(filterMap.getMilitaryUnit().getId()).getMU_name());
            //filterMap.setMilitaryUnit(mu);
            //Se foi escolhido um pel/esq para mostrar operações no mapa
            listMU.add(filterMap.getMilitaryUnit());//única adição, não mostra outras unidades
            listOA = oaDao.getByMUId(filterMap.getMilitaryUnit().getId());//Carrega todas as operações do pel/esq no mapa
            
        }else{//Se não foi selecionado um pel/esq, carrega operações de todos os pel/esq subordinados
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n FILTRO NÃO EXCLUSIVO, opção - "+filterMap.getMilitaryUnit().getId()+" -  SUBORDINADAS DE:"+mu.getMU_name());
            listMU = mudDao.getSubordinatedById(mu.getId());//pega a lista de todas as subordinadas
            listMU.add(mu);
            listOA = selectOAs(mu.getId());//Carrega todas as operações de todos os pel/esq subordinados  
        }
        //filtra por status
        listOA = filterOAbyStatus(listOA,filterMap.getShowPlannedOA(),filterMap.getShowDoingOA(),filterMap.getShowDoneOA());//filtra por status
        //filtra por período
        if(filterMap.getStart_date()!=null){            
            listOA = filterOAbyPeriod(listOA, filterMap.getStart_date(),filterMap.getFinish_date());        
        }


        //Pega todoas as unidades subordinadas que são tipos de pelotão/esquadrão e devolve para ser utilizado no form de filtro do mapa
        List<MilitaryUnit> units = mudDao.getSubordinatedById(mu.getId());
        for(int i=0;i<units.size();i++){
            if (!(units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.ESQD)||
                    (units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PEF))||
                    (units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PELD)))){
                System.out.println("Removeu -- "+ units.get(i).getMU_name());
                units.remove(i);
                i--;
            
            }
        }
        
        //Recupera a unidade pesquisada no mapa
        model.addAttribute("operations", listOA);
        model.addAttribute("filterMap", filterMap);
        model.addAttribute("units", units);
        model.addAttribute("content", "/map");
        return new ModelAndView("layout", model);
    }
    
    /**Lista unidades militares como resultado de uma busca por nome*/
    @RequestMapping(value = "/goToMUInfo/{MU_initials}")
    public ModelAndView goToMUInfo(@PathVariable("MU_initials") String MU_initials) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENTROU, AVALIANDO MU_initials:"+MU_initials);
        ModelAndView modelAndView = new ModelAndView("redirect:/pefEsqdPelControl/navigateMU/{id}");
        //Recupera a unidade pesquisada no mapa
        MilitaryUnit mu = dao.findByInitials(MU_initials);
        if(mu==null){
            System.out.println("\n\n\n\n Unidade não encontrada, verifique configuração do mapa");
            return new ModelAndView("redirect:/mapControl/mapping");
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENCONTROU:"+mu.getMU_name());
        //Examina a subordinação da unidade do usuário em relação à unidade pesquisada
        modelAndView.addObject("id", mu.getId());
        
       //model.addAttribute("content", "listMUs");
        return modelAndView;
    }
     /**Vai para a tela de informações de uma operação localizada no mapa*/
    @RequestMapping(value = "/goToOAInfo/{id}")
    public ModelAndView goToOAInfo(@PathVariable("id") Long id) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENTROU, BUSCANDO OPERAÇÃO de id:"+id);
        ModelAndView modelAndView = new ModelAndView("redirect:/operationalActivityControl/moreInfo/{id}");
        //Recupera a unidade pesquisada no mapa
        OperationalActivity oa = oaDao.getById(id);
        if(oa==null){
            System.out.println("\n\n\n\n Operação não encontrada, verifique configuração do mapa");
            return new ModelAndView("redirect:/mapControl/mapping");
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n ENCONTROU:"+oa.getName());
        modelAndView.addObject("id", oa.getId());
        return modelAndView;
    }
    
}
