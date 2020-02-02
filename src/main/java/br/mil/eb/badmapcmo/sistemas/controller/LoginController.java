package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MUDependenciesDao;
import br.mil.eb.badmapcmo.sistemas.dao.OperationalActivityDao;
import br.mil.eb.badmapcmo.sistemas.dao.UserDao;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.User;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.FilterMap;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.DependencyTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.UserClassEnum;
import br.mil.eb.badmapcmo.sistemas.domain.operations.OperationalActivity;
import br.mil.eb.badmapcmo.sistemas.service.UserService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private OperationalActivityDao oaDao;
    @Autowired
    private UserDao usrDao;
    @Autowired
    private MUDependenciesDao mudDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().contains("ANONYMOUS") || SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().contains("anonymous")) {
            System.out.println("Foi de / para login..............");
            return new ModelAndView("login");
        }

//          System.out.println("há usuário no contexto "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//          System.out.println("há autorizações no contexto "+SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        //System.out.println("há usuário no contexto "+SecurityContextHolder.getContext().getAuthentication().getName().toString());
        return new ModelAndView("redirect:/home");
        //ModelMap model = new ModelMap();
        //model.addAttribute("conteudomenu", "/admin/adminMenu");
        //                        model.addAttribute("conteudo", "generalStart");
        //                      System.out.println("RETORNANDO LAYOUT");
        //                    return new ModelAndView("layout", model);
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) boolean error,
            @RequestParam(value = "logout", required = false) boolean logout, ModelMap model) {

        if (error) {
            System.out.println("ERRO NA CHAMADA /login");
            model.addAttribute("error", "Invalid username or password!");
            return new ModelAndView("login", model);
        }

        if (logout) {

            System.out.println("SOLICITOU DESLOGAR EM /login");
            model.addAttribute("logout", "Logged out! You've been logged out successfully.");
            return new ModelAndView("login");
        }

        System.out.println("LOGOU COMO:" + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() + "CHAMANDO /home");
        return new ModelAndView("redirect:/home");
    }

    @GetMapping(value = "/home")
    public ModelAndView callHome() {
        System.out.println("INÍCIO CALLHOME");
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
        model.addAttribute("content", "generalStart");
        System.out.println("RETORNANDO LAYOUT");
        return new ModelAndView("layout", model);

        //TODO apagar :: return new ModelAndView("/usuarioAdmin/adminHome", "usuario", u);        		
        //        	}
        //return new ModelAndView("login");
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {

        return new ModelAndView("accessDenied");
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loginPage() {

        return "login";
    }

    /*private UserService userService;
    
    
    
   /* @GetMapping("/")
    public ModelAndView home() { /*System.out.println("ABRINDO USERADMIN");
        
    
        System.out.println("Entrou de novo com "+
                SecurityContextHolder.getContext().getAuthentication().toString());
        //ModelAndView modelAndView = new ModelAndView();
        if(SecurityContextHolder.getContext().getAuthentication().equals(null)){
            System.out.println("segunda passada!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            
            User user = userService.findUserByUsername(auth.getName());
            System.out.println("usuario tem classe "+user.getClasse().toString());
            ModelMap model = new ModelMap();
            model.addAttribute("conteudomenu", "admin/adminMenu");
            model.addAttribute("conteudo", "generalStart");
            model.addAttribute(user);
            return new ModelAndView("layout", model);
        }/*
        //    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
       //     User user = userService.findUserByUsername(auth.getName());
        //modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        //modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        //if(UserClassEnum.ADMINISTRADOR.equals(user.getClasse())){
        
        
        //modelAndView.setViewName("admin/home");
      //  model.addAttribute("conteudomenu", "admin/adminMenu");
       // model.addAttribute("conteudo", "generalStart");
       // return new ModelAndView("layout", model);
       // }}
       System.out.println("PRIMEIRAAA passada!!!!!!!!!!!!!!!!!!!!!!!!!!");
       return new ModelAndView("layout");
    }

     */
    
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
}
