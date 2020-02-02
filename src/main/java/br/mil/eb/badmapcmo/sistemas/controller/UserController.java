package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MUDependenciesDao;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.mil.eb.badmapcmo.sistemas.dao.MilitaryDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.UserDao;
import br.mil.eb.badmapcmo.sistemas.dao.ZoneDao;
import br.mil.eb.badmapcmo.sistemas.domain.*;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.UserClassEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.PostGradEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SexoEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.StatesEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("userControl")
public class UserController {

    @Autowired
    private UserDao dao;

    @Autowired
    private MilitaryDao milDao;

    @Autowired
    private MilitaryUnitDao muDao;

    @Autowired
    private MUDependenciesDao mudepDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ZoneDao zoneDao;
    
    @ModelAttribute("classesU")
    public UserClassEnum[] classeUsuarioEnum(){
            return UserClassEnum.values();
    }

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

    /**Chamada para pagina de perfil pessoal*/
    @GetMapping("/showPersonalProfile")
    public ModelAndView showPersonalProfile() {
        CurrentUser currentUser = (CurrentUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();                        
        User user = dao.getByUsername(currentUser.getUsername());
        ModelMap model = new ModelMap();
        model.addAttribute("user", user);
        model.addAttribute("content", "personalProfile");
        System.out.println("Personal profile:"+user.toString());
        return new ModelAndView("layout", model);       
    }	
    /**Chamada para pagina de detalher do perfil de um usuário*/
    @GetMapping(value="/showSingleProfile/{id}")    
    public ModelAndView showSingleProfile(@PathVariable("id") Long id, ModelMap model) {
        User user = dao.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("content", "personalProfile");
        System.out.println("Profile to be showed:"+user.toString());
        return new ModelAndView("layout", model);       
    }
    
    /**Lista todos os usuários*/
    @RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
    public ModelAndView listAllUsers(ModelMap model) {
        //TODO acertar listagem de usuários por OM                      
        System.out.println("1 receber a lista de unidades subordinadas a unidade do usuario logado");
        
        Long mu_id = dao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMilitary().getId();
        System.out.println("1.1 encontra o id da unidade do usuario logado:"+ mu_id);
        /**Passa como parâmetro o id da unidade do usuário logado e recupera as unidades subordinadas a unidade do mesmo*/
        Long auxId = dao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMilitary().getMilitaryUnit().getId();
        List<MilitaryUnit> sub_l = mudepDao.getSubordinatedById(auxId);
        //Adiciona sua própria unidade
        System.out.println("adicionando a propria unidade:"+muDao.findById(auxId).toString());
        sub_l.add(muDao.findById(auxId));
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n LISTA DE UNIDADES RECUPERADAS::["+sub_l.toString()+"]");
                   
        //2 para cada unidade subordinada, recuperar todos os usuários desta 
        //cria uma lista de usuários para ser incrementada e retornada
        List<User> usr_l = new ArrayList<User>();
        for (int i = 0; i < sub_l.size(); i++) {            
            System.out.println("\n\n ADICIONANDO USARIOS DA UNIDADE::["+sub_l.get(i).getMU_name()+"]");
            usr_l.addAll(dao.getByMU(sub_l.get(i).getId()));
            //System.out.println("\n\n\n\n\nUnidade: "+sub_l.get(i).toString()+"\n Usuários:"+dao.getByMU(sub_l.get(i).getId()).toString());
	}
        //unidade e jogar numa lista incremental
        System.out.println("\n\n\n\n\n\n\nLista final de subordinados: ["+usr_l.toString()+"]");
        model.addAttribute("users", usr_l);
        model.addAttribute("uid", mu_id);
        model.addAttribute("units", muDao.getAll());
        //System.out.println("Usuários recuperados::"+model.toString());
        model.addAttribute("content", "listUsers");
        return new ModelAndView("layout", model);
    }
        
    /**Lista todos os usuários por classe*/
    @GetMapping("/listUsrByCl")//TODO organizar todas as filtragens para respeitarem as dependencias entre unidades
    public ModelAndView listUsrByCl(@RequestParam(value = "classe") UserClassEnum classe, ModelMap model) {
        if (classe == null) {
            return new ModelAndView("redirect:/userControl/listAllUsers");
        }
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("users", dao.getByClasse(classe));
        model.addAttribute("content", "listUsers");
        return new ModelAndView("layout", model);
    }
	
    /**Chama a tela de cadastro para um novo usuário*/
    @RequestMapping(value = "/register", method = RequestMethod.GET)//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView register(@ModelAttribute("user") User user, @ModelAttribute("military") Military military, @ModelAttribute("units") MilitaryUnit units, ModelMap model) {
        //System.out.println("chamando tela de cadastro!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("zones", zoneDao.getAll());
        model.addAttribute("user", user);
        model.addAttribute("military", military);
        model.addAttribute("content", "addUser");
        model.addAttribute("formStatus", "new");
        return new ModelAndView("layout", model);           
    }
    
    /**Recupera informações de cadastro de militar para o form de cadastro para um novo usuário*/
    @RequestMapping(value = "/fullRegisterUser")//TODO adicionar filtragem por classe de usuario para redirecionamento
    public ModelAndView fullRegisterUser(@ModelAttribute("military") Military military, BindingResult result, ModelMap model, RedirectAttributes attr) {
        //System.out.println("chamando tela de cadastro!!!!!!!!!!!!!!!!!!!");
        military = milDao.getByIdt(military.getMilitary_identitiy());
        User user = new User();
        if (result.hasErrors()){
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente. Se o erro persistir abra um chamado.");
            
        }else if(military!=null){
            attr.addFlashAttribute("message", "Dados recuperados. Qualquer alteração nos dados de militar será salva também no cadastro de militar!");
            model.addAttribute("military", military); 
            user.setMilitary(military);
        }else if(military==null){
            attr.addFlashAttribute("message", "Cadastro de militar não localizado. Verifique se o novo comandante não é militar de uma unidade superior.");         
        } 
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("zones", zoneDao.getAll());
        model.addAttribute("user", user);
        model.addAttribute("content", "addUser");
        model.addAttribute("formStatus", "recovered");
        return new ModelAndView("layout", model);           
    }

    /**Salva um novo usuário para o sistema*/
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attr) {
        System.out.println("\n\n\n\n\n\n\n\n chegou usuario com nome de guerra: "+user.getMilitary().getWar_name());
        Integer military_idt = user.getMilitary().getMilitary_identitiy();
        if (result.hasErrors()) {
                System.out.println("\n\n\n\n\n\n\n\n  Primeiro IF");
                return "redirect:/userControl/register";
        }
        //Verifica se o nome de usuário já está cadastrado
        if(dao.getByUsername(user.getUsername())!=null){
                System.out.println("\n\n\n\n\n\n\n\n  Segundo IF");
            attr.addFlashAttribute("message", "Nome de usuário já cadastrado.");
            return "redirect:/userControl/register";
        //Verifica se o usuário de idt:idt já está cadastrado    
        }else if(milDao.getByIdt(military_idt)!=null){//existe cadastro de militar para idt informada
            if(dao.getByMilitaryId(milDao.getByIdt(military_idt).getId())!=null){//existe usuário associado ao cadastro de militar    
                System.out.println("\n\n\n\n\n\n\n\n  Terceiro ELSE IF");
                attr.addFlashAttribute("message", "Cadastro não efeturado. Militar inserido já cadastrado como usuário.");
                return "redirect:/userControl/register";
            }
        }
        System.out.println("\n\n\n\n\n\n\n\n  Saiu do condicional para verificar identidade");
        /*Senha inicial é identidade do militar*/        
//        user.getMilitary().getAddress().setZone(ZoneController.checkZone(user.getMilitary().getAddress().getZone());
        System.out.println("\n\n\n\n\n\n\n\n  Identidade do militar antes:"+user.getPassword());
        user.setPassword(String.valueOf(user.getMilitary().getMilitary_identitiy()));
        System.out.println("\n\n\n\n\n\n\n\n Identidade do militar depois:"+user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("\n\n\n\n\n\n\n\n  Identidade do militar depois:"+user.getPassword());
//        Zone z = zoneDao.checkZone(user.getMilitary().getAddress().getZone());
//        if(!z.equals(user.getMilitary().getAddress().getZone())){
//            user.getMilitary().getAddress().setZone(z);
//        }       
        dao.save(user);
        attr.addFlashAttribute("message", "Usuário salvo com sucesso.");
        return "redirect:/userControl/listAllUsers";
    }
    
    /**Reseta senha de usuário*/
    @PostMapping("/resetPassword/{id}")
    public String resetPassword(@PathVariable("id") Long id, RedirectAttributes attr) {
        User user = dao.getId(id);//recupera usuário no banco
        user.setPassword(passwordEncoder.encode(String.valueOf(user.getMilitary().getMilitary_identitiy())));//seta senha como identidade
        dao.update(user);//atualiza user no banco
        attr.addFlashAttribute("message", "Senha de usuário resetada com sucesso.");
        return "redirect:/userControl/listAllUsers";
    }
    
	
    /**Chama tela com form para atualização*/
    @GetMapping("/preUpdate/{id}")//TODO atualizar o nome do método no html
    public ModelAndView preUpdate(@PathVariable("id") Long id, @ModelAttribute("units") MilitaryUnit units, @ModelAttribute("military") Military military, ModelMap model) {
        User user = dao.getId(id);
        //System.out.println("Chamando edição para: "+user.toString());
        model.addAttribute("formStatus", "updating");
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("zones", zoneDao.getAll());
        model.addAttribute("user", user);
        military = user.getMilitary();
        model.addAttribute("military", military);
        model.addAttribute("content", "addUser");
        System.out.println("\n\n\n\n\n\n\n\n CADASTRO RECUPERADO, CHAMANDO UPDATE");
        return new ModelAndView("layout", model); 
    }
    /**Efetiva a atualização*/
    @PostMapping("/update")//TODO atualizar os métodos e o html para update de usuário
    public ModelAndView update(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attr) {
        //System.out.println("Unidade militar esperada: "+user.getMilitary().getMilitaryUnit().toString()+"\n\n\n\n\n\n\n");
        if (result.hasErrors()) {
            attr.addFlashAttribute("message", "Um erro ocorreu, tente novamente.");
            return new ModelAndView("redirect:/userControl/listAllUsers");
        }
        //criptografa senha
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDB = dao.getById(user.getId());
       

        //atualiza zona
        user.getMilitary().getAddress().setZone(zoneDao.getById(user.getMilitary().getAddress().getZone().getId()));
        //atualiza a UM
        user.getMilitary().setMilitaryUnit(muDao.findById(user.getMilitary().getMilitaryUnit().getId()));
        
         
         
         
         
//        System.out.println("\n\n\n\n\n\n\n\nENDEREÇOFORM:"+user.getMilitary().getAddress().toString());
//        System.out.println("\n\nENDEREÇODB:"+userDB.getMilitary().getAddress().toString());
//        
//       
//        System.out.println("\n\n\n\n\n\n\n\nOM FORM:"+user.getMilitary().getMilitaryUnit().toString());
//        System.out.println("\n\nOM DB:"+userDB.getMilitary().getMilitaryUnit().toString());
        
        
        
        
        //atualiza zona
        if(user.getMilitary().getAddress().equals(milDao.getById(user.getMilitary().getId()).getAddress())){
            System.out.println("\n\n\n\n\n\n\n\nENDEREÇO IGUAL");
        }
        //user.getMilitary().getAddress().setZone(zoneDao.findById(user.getMilitary().getAddress().getZone().getId()));
        //atualiza cadastro de militar
        //user.setMilitary(milDao.findById(user.getMilitary().getId()));
        
        System.out.println("\n\n\n\n\n\nUsuario a ser atualizado: "+user.toString()+"\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\nUsuario recuperado do banco: "+dao.getById(user.getId()).toString()+"\n\n\n\n\n\n\n");
        if(user.equals(dao.getById(user.getId()))){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\nSÃO IGUAIS");
            return new ModelAndView("redirect:/userControl/listAllUsers");
        }else{
            System.out.println("\n\n\n\n\n\n\n\n Necessita update");
        }
        dao.update(user);
        attr.addFlashAttribute("message", "Usuário alterado com sucesso.");//TODO consertar a disposição destas msgs no html
        return new ModelAndView("redirect:/userControl/listAllUsers");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
            dao.excluir(id);
            attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
            return "redirect:/userControl/listAllUsers";
    }

    //TODO trocar nome da função para verperfilPessoal
    @GetMapping(value="/verPerfil/{login}")
    public ModelAndView verPerfil(@PathVariable(value="login") String login, ModelMap model) {
        System.out.println("ENTROU!  - "+login);
        User usuario = dao.getByUsername(login);
        if(usuario==null) {
            System.out.println("lista VAZIA");
            return new ModelAndView("/home/index");
        }
        if(UserClassEnum.ADMIN.equals(usuario.getClasse())){
            System.out.println("Acessando perfil usuario read-only");
            //return new ModelAndView("/usuarioAdmin/adminPerfil", "usuario", usuario); 
            model.addAttribute("content", "perfil");
            return new ModelAndView("layout", model);
        }
        if(UserClassEnum.READ_WRITE.equals(usuario.getClasse())){
            System.out.println("Acessando perfil usuario read-only");
            return new ModelAndView("/usuarioRW/RWPerfil", "usuario", usuario);        		
        }
        if(UserClassEnum.READ_ONLY.equals(usuario.getClasse())){
            System.out.println("Acessando perfil usuario read-only");
            return new ModelAndView("/usuarioRO/ROPerfil", "usuario", usuario); 
        }

        System.out.println("Algo ocorreu não foi possível acessar perfil:");
        return new ModelAndView("/usuarioRO/ROPerfil");
    }
/*@GetMapping("/nome")
public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome) {

    if (nome == null) {
        return new ModelAndView("redirect:/usuario/todos");
    }
    return new ModelAndView("/user/list", "usuarios", dao.getByNome(nome));
}	

/*
@ModelAttribute("sexos")
    public TipoSexo[] tipoSexo() {
            return TipoSexo.values();
    }

@GetMapping("/sexo")
public ModelAndView listarPorSexo(@RequestParam(value = "tipoSexo") TipoSexo sexo) {

        if (sexo == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }
        return new ModelAndView("/user/list", "usuarios", dao.getBySexo(sexo));
    }*/
}
