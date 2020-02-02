/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.AddressDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.RegionMapDao;
import br.mil.eb.badmapcmo.sistemas.dao.ZoneDao;
import br.mil.eb.badmapcmo.sistemas.domain.Address;
import br.mil.eb.badmapcmo.sistemas.domain.MURegionMap;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.User;
import br.mil.eb.badmapcmo.sistemas.domain.Zone;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.UserClassEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.StatesEnum;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**Classe de controle de cadastro de regiões do mapa 
 * @since beta 1.0
 * @see br.mil.eb.badmapcmo.sistemas.domain.Zone
 * @author tenbenites
 */

@Controller
@RequestMapping("zoneControl")
public class ZoneController {
    @Autowired
    private ZoneDao dao;
    
    @Autowired
    private RegionMapDao rmapDao;
    
    @Autowired
    private AddressDao adsDao;
        
    @Autowired
    private MilitaryUnitDao muDao;
    
    @ModelAttribute("zStates")
    public StatesEnum[] statesEnum(){
            return StatesEnum.values();
    }
    
    
    /**Lista todas as regiões*/
    @RequestMapping(value = "/listAllZones", method = RequestMethod.GET)
    public ModelAndView listAllZones(@ModelAttribute("zones") Zone zone, ModelMap model) {
		//System.out.println("SOLICITANDO LISTAGEM de Regiões!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");                      
                model.addAttribute("zones", dao.getAll());
		//System.out.println("Usuários recuperados::"+model.toString());
		model.addAttribute("content", "listZones");
                return new ModelAndView("layout", model);
    }
    
    
    /**Lista as informações cadastradas para uma região no gerenciamento de Unidade*/
    @GetMapping(value = "/showSingleZone/{id}")
    public ModelAndView showSingleZone(@PathVariable("id") Long id, ModelMap model) { 
        Zone zone = dao.getById(id);
       // MilitaryUnit mu = zone.getFrontier_militaryUnit();
        model.addAttribute("zone", zone);
       // model.addAttribute("militaryUnit", mu);
	model.addAttribute("content", "listZoneInfo");
        //Verifica se é um pelotão ou OM
        //QUESTION_FOR_CLIENT: haverá controle de regiões abrangidas pelas outras unidades superiores?
//        if (MilitaryUnitTypeEnum.PEF.equals(mu.getMU_type()) || MilitaryUnitTypeEnum.PELD.equals(mu.getMU_type())) {
//            model.addAttribute("subMenu", "mu_management/subMenuPEL");
//        } else {
//            model.addAttribute("subMenu", "mu_management/subMenuMU");
//        }        
        model.addAttribute("subContent", "mu_management/listZoneInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);
    }
    
    
    
    
    
    /**Chama a tela de cadastro para uma nova região*/
    @RequestMapping(value = "/registerZone", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("zone") Zone zone, @ModelAttribute("units") MilitaryUnit units, ModelMap model) {
        System.out.println("chamando tela de adição cidade município!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("zone", zone);
        model.addAttribute("content", "addZone");
        return new ModelAndView("layout", model);           
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("zone") Zone zone, BindingResult result, RedirectAttributes attr) {
            System.out.println("\n\n\n\n\n\n\n\n ZONA: "+zone.toString());
            if (result.hasErrors()) {
                    return "redirect:/zoneControl/registerZone";
            }
            if(!dao.findZone(zone.getCounty_name()).isEmpty()){
                attr.addFlashAttribute("message", "Cidade/Município já cadastrada(o).");
                return "redirect:/zoneControl/register";
            }
            
    //        zone.setFrontier_militaryUnit(muDao.getByName(zone.getFrontier_militaryUnit().getMU_name()).get(0));
            dao.save(zone);
            attr.addFlashAttribute("message", "Cidade/Município salva(o) com sucesso.");
            return "redirect:/zoneControl/listAllZones";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        if(isZoneInUse(dao.getById(id))!=null){
            attr.addFlashAttribute("message", isZoneInUse(dao.getById(id)));        
        }else{
        dao.delete(id);
        attr.addFlashAttribute("message", "Cidade/Município excluída(o) com sucesso.");}
        return "redirect:/zoneControl/listAllZones";
    }
    //QUESTION_FOR_CLIENT: um determinado município pode estar em dois pefs ao mesmo tempo?
    /**Verifica se a zona está em uso por outras entidades para prevenir conflitos de chave estrangeira*/
    private String isZoneInUse(Zone z){
        int i;
        List<MURegionMap> listRmap= rmapDao.getAll();
        for(i=0;i<listRmap.size();i++){
            if(listRmap.get(i).getZone().equals(z)){
                return "Não foi possível excluir. Remova antes o município de regiões nas unidades.";
            }
        }
        List<Address> listAds= adsDao.getAll();
        for(i=0;i<listAds.size();i++){
            if(listAds.get(i).getZone().equals(z)){
                return "Não foi possível excluir. Remova antes endereços cadastrados no município.";
            }
        }
        return null;
    }
    /**
     * Chama tela com form para atualização de informações especiais (somente
     * SUPERADMIN) do município.
     */
    @GetMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("units", muDao.getAll());
        model.addAttribute("zone", dao.getById(id));
        model.addAttribute("content", "addZone");
        return new ModelAndView("layout", model);             
    }
    
    //Chama tela com form para atualização
    @GetMapping("/preUpdateZonePelInfo/{mu_id}/{zone_id}")
    public ModelAndView preUpdateZonePelInfo(@PathVariable("mu_id") Long mu_id, @PathVariable("zone_id") Long zone_id, ModelMap model) {
        model.addAttribute("militaryUnit", muDao.findById(mu_id));
        model.addAttribute("zone", dao.getById(zone_id));
        model.addAttribute("subMenu", "mu_management/subMenuMU");        
        model.addAttribute("subContent", "mu_management/editZoneGeneralInfo");
        model.addAttribute("content", "mu_management/subLayout");
        return new ModelAndView("layout", model);   
        
            
            
    }
    //Efetiva a atualização
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("zone") Zone zone, BindingResult result, RedirectAttributes attr) {
            if (result.hasErrors()) {
                    return new ModelAndView("/addUser");
            }
            dao.update(zone);
            attr.addFlashAttribute("message", "Cidade/Município alterado com sucesso.");
            
            return new ModelAndView("redirect:/zoneControl/listAllZones");
    }
    
    
     
    
}
