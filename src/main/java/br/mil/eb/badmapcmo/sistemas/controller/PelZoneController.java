/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.RegionMapDao;
import br.mil.eb.badmapcmo.sistemas.dao.ZoneDao;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controla operações sobre os dados de municípios dos PEl.
 * @see PelZone.java
 * @author tenbenites
 */
@Controller
@RequestMapping("pelZoneControl")
public class PelZoneController {
    @Autowired
    private MilitaryUnitDao daoMU;
    @Autowired
    private ZoneDao daoZone;
    @Autowired
    private RegionMapDao daoRMap;
    
    /**Lista todas as unidades militares por ordem em que foram cadastradas*/
    @GetMapping("/listPelZones/{id}")//TODO tentar passar a MU inteira ao invés do id
    public ModelAndView listPelZones(@PathVariable("id") Long id) {
        ModelMap model = new ModelMap();
        MilitaryUnit mu = daoMU.findById(id);
        model.addAttribute("militaryUnit", mu);
        model = FragmentController.checkMUMenu(mu, model);
        model.addAttribute("zones", daoRMap.getZonesByMUId(id));
        model.addAttribute("subContent", "mu_management/listPelZones");
        return new ModelAndView("layout", model);
    } 
    
}
