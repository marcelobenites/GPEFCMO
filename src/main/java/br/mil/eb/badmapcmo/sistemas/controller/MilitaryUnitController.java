/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MUDependenciesDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryDao;
import br.mil.eb.badmapcmo.sistemas.dao.MilitaryUnitDao;
import br.mil.eb.badmapcmo.sistemas.dao.RegionMapDao;
import br.mil.eb.badmapcmo.sistemas.dao.UserDao;
import br.mil.eb.badmapcmo.sistemas.dao.ZoneDao;
import br.mil.eb.badmapcmo.sistemas.domain.MUDependencies;
import br.mil.eb.badmapcmo.sistemas.domain.MURegionMap;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.MUDeps;
import br.mil.eb.badmapcmo.sistemas.domain.Zone;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.auxiliary.Region;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.DependencyTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.StatesEnum;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe de controle de CRUD de unidades militares.
 *
 * @see MilitaryUnit.java
 * @author tenbenites
 */
@Controller
@RequestMapping("militaryUnitControl")
public class MilitaryUnitController {

    @Autowired
    private MilitaryUnitDao dao;

    @Autowired
    private MilitaryDao milDao;

    @Autowired
    private UserDao usrDao;

    @Autowired
    private ZoneDao zoneDao;

    @Autowired
    private MUDependenciesDao mudDao;

    @Autowired
    private RegionMapDao daoRMap;

    @ModelAttribute("zStates")
    public StatesEnum[] statesEnum() {
        return StatesEnum.values();
    }

    @ModelAttribute("muTypes")
    public MilitaryUnitTypeEnum[] militaryUnitType() {
        return MilitaryUnitTypeEnum.values();
    }

    //enum do tipo de dependencia que pode ocorrer entre duas unidades    
    @ModelAttribute("muDep")
    public DependencyTypeEnum[] dependencyTypeEnum() {
        return DependencyTypeEnum.values();
    }

    /**
     * Lista todas as unidades militares por ordem em que foram cadastradas
     */
    @RequestMapping(value = "/listMUs", method = RequestMethod.GET)
    public ModelAndView listUMs() {
        ModelMap model = new ModelMap();
        Long auxId = usrDao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMilitary().getMilitaryUnit().getId();
        //Recebe todas as unidades subordinadas à unidade do usuário
        List<MilitaryUnit> units = mudDao.getSubordinatedById(auxId);
        //Adiciona a unidade do usuário
        units.add(dao.findById(auxId));
        model.addAttribute("units", units);
        model.addAttribute("content", "/listMUs");
        return new ModelAndView("layout", model);
    }

    /**
     * Lista OUTRAS unidades militares por ordem em que foram cadastradas
     */
    @RequestMapping(value = "/listOUs", method = RequestMethod.GET)
    public ModelAndView listOUs() {
        ModelMap model = new ModelMap();
        Long auxId = usrDao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMilitary().getMilitaryUnit().getId();
        //Recebe todas as unidades subordinadas à unidade do usuário
        List<MilitaryUnit> units = mudDao.getSubordinatedById(auxId);
        //Adiciona a unidade do usuário
        units.add(dao.findById(auxId));
        //Filtra somente outras unidades
        List<MilitaryUnit> units_aux = new ArrayList<MilitaryUnit>();
        for (int i = 0; i < units.size(); i++) {
            if (!(units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PEF) || units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PELD) || units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.ESQD))) {
                units_aux.add(units.get(i));
            }
        }
        model.addAttribute("units", units_aux);
        model.addAttribute("content", "/listMUs");
        return new ModelAndView("layout", model);
    }

    /**
     * Lista todos os PEFs e EQDR
     */
    @RequestMapping(value = "/listPEFs", method = RequestMethod.GET)
    public ModelAndView listPEFs() {
        ModelMap model = new ModelMap();
        Long auxId = usrDao.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMilitary().getMilitaryUnit().getId();
        //Recebe todas as unidades subordinadas à unidade do usuário
        List<MilitaryUnit> units = mudDao.getSubordinatedById(auxId);
        //Adiciona a unidade do usuário
        units.add(dao.findById(auxId));
        //Filtra somente pefs e esqd
        List<MilitaryUnit> units_aux = new ArrayList<MilitaryUnit>();
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PEF) || units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.PELD) || units.get(i).getMU_type().equals(MilitaryUnitTypeEnum.ESQD)) {
                units_aux.add(units.get(i));
            }
        }
        model.addAttribute("units", units_aux);
        model.addAttribute("content", "/listPEFs");
        return new ModelAndView("layout", model);
    }

    /**
     * Lista unidades militares segundo seu tipo de classificação
     */
    @RequestMapping(value = "/listMUsByType")
    public ModelAndView listUMsByType(@RequestParam(value = "MU_type") MilitaryUnitTypeEnum MU_type, ModelMap model) {
        model.addAttribute("units", dao.getByType(MU_type));
        model.addAttribute("content", "listMUs");
        return new ModelAndView("layout", model);
    }

    /**
     * Lista unidades militares como resultado de uma busca por nome
     */
    @RequestMapping(value = "/findByMUName")
    public ModelAndView findMUByName(@RequestParam(value = "MU_name") String MU_name, ModelMap model) {
        model.addAttribute("units", dao.getByName(MU_name));
        model.addAttribute("content", "listMUs");
        return new ModelAndView("layout", model);
    }

    @RequestMapping(value = "/saveMUlist/{muId}", method = RequestMethod.POST)
    public String saveMUlist(@Valid @ModelAttribute("militaryUnit") MilitaryUnit militaryUnit, @PathVariable Integer muId, ModelMap model) {
        MilitaryUnit mu = new MilitaryUnit();
        //su.setMilitar(mDao.getByIdentidadeMilitar(m.getIdentidade_militar()));
        mu.setMU_name(militaryUnit.getMU_name());
        dao.save(mu);
        return "redirect:/militaryUnitControl/listMUs";
    }

    
    /**
     * Chama a tela de cadastro de nova unidade militar
     */
    @RequestMapping(value = "/addMilitaryUnit", method = RequestMethod.GET)//TODO: trocar nome para preSaveMilitaryUnit
    public ModelAndView addMilitaryUnit(@ModelAttribute("militaryUnit") MilitaryUnit militaryUnit,@ModelAttribute("units") MilitaryUnit units,
            @ModelAttribute("supUnits") MilitaryUnit supUnits, ModelMap model) {        
        System.out.println("chamando tela de cadastro de MU!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("units", dao.getAll());
        model.addAttribute("zones", zoneDao.getAll());
        int max_size = dao.getAll().size();//número total de unidades já existentes
        //Listas de superiores e dependentes
        model.addAttribute("supUnits", new ArrayList<>(max_size));
        model.addAttribute("deps", new ArrayList<>(max_size));
        militaryUnit.setRegions(new ArrayList<>(zoneDao.getAll().size()));//seta um vetor transiente para cadastro de região
        militaryUnit.setDeps(new ArrayList<>(max_size));//seta um vetor transiente para cadastro de dependências 
        model.addAttribute("regions", new ArrayList<>(zoneDao.getAll().size()));//TODO: rever
        model.addAttribute("militaryUnit", militaryUnit);
        model.addAttribute("content", "addMilitaryUnit");
        return new ModelAndView("layout", model);
    }

    /**
     * Salva uma nova unidade militar
     */
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("militaryUnit") MilitaryUnit militaryUnit, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            System.out.println("ERRO no salvamento!!!!!!!!!!!XXXXXXXXXXXX!!!!!!!!!!!!!!!" + result.toString());
            attr.addFlashAttribute("message", "Algo ocorreu, tente novamente.");
            return "redirect:/militaryUnitControl/listMUs";
        }
        MilitaryUnit mu = militaryUnit;
        mu.getAddress().setZone(zoneDao.getById(mu.getAddress().getZone().getId()));
        mu.setMU_name(militaryUnit.getMU_name());
        //Verifica se unidade já está cadastrada
        if (!dao.getByName(mu.getMU_name()).isEmpty()) {
            System.out.println("UNIDADE JÁ CADASTRADA");
            attr.addFlashAttribute("message", "Unidade já cadastrada no sistema.");
            return "redirect:/militaryUnitControl/listMUs";
        }
        //Salva military unit
        dao.save(mu);
        System.out.println("\n\n\n\n Salvo unidade" + mu.toString());
        System.out.println("\n\n\n\n REGIÃO DA UNIDADE\n" + mu.getRegions().toString());

        //Alimenta a tabela de região para a unidade militar - IR PARA O UPDATE DE EDIÇÃO
//                MURegionMap regionMap = new MURegionMap();
//                for(int i=0;i<militaryUnit.getRegion().size();i++){
//                    regionMap.setMilitaryUnit(militaryUnit);
//                    regionMap.setZone(zoneDao.findById(militaryUnit.getRegion().get(i)));
//                    System.out.println("\n\n\n\nEnviando região para salvamento:"+regionMap.toString());
//                    daoRMap.saveMURegion(regionMap);                    
//                }
        Zone zo;
        //Atualiza a região definida para a unidade
        for (int i = 0; i < militaryUnit.getRegions().size(); i++) {//para cada zona recuperada
            zo = zoneDao.getById(militaryUnit.getRegions().get(i).getZone_id());//pega a zona

            MURegionMap muR = new MURegionMap();
            if (militaryUnit.getRegions().get(i).getIncluded()) {
                System.out.println("ZONA marcada foi recuperada:" + zo.toString());
                muR.setZone(zo);
                muR.setMilitaryUnit(militaryUnit);//seta a unidade para persistência no banco
                daoRMap.saveMURegion(muR);
            }
        }
        //Atualiza árvore de dependências
        MUDependencies mud;        
        MilitaryUnit mut;//auxiliar para as outras unidades que se relacionam com a unidade sendo persistida
        String relation;//auxiliar para registro do tipo de relação de subordinação
        for (int i = 0; i < militaryUnit.getDeps().size(); i++) {//para todos os itens da lista recebida do form
            mut = dao.findById(militaryUnit.getDeps().get(i).getMu_id());//recebe a unidade na posição i da lista
            //System.out.println("MUT foi recuperado, unidade:"+mut.getMU_name());
            mud = new MUDependencies();
            relation = militaryUnit.getDeps().get(i).getMu_dep();
            //Guarda o tipo de link da árvore na dependencia
            if (relation.contains("direta")) {
                mud.setDep_type(Boolean.TRUE);
            } else if (relation.contains("indireta")) {
                mud.setDep_type(Boolean.TRUE);
            }
            //Registra a dependencia no banco
            if (relation.contains("Subordinada")) {
                mud.setSubordinatedUnit(mut);
                mud.setSuperiorUnit(mu);
                mudDao.save(mud);
            } else if (relation.contains("Superior")) {
                mud.setSubordinatedUnit(mu);
                mud.setSuperiorUnit(mut);
                mudDao.save(mud);
            }
            
        }

        attr.addFlashAttribute("message", "Unidade salva com sucesso.");
        return "redirect:/militaryUnitControl/listMUs";
    }

    /**
     * Chama tela com form para atualização
     */
    @GetMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
        //System.out.println("\n\n\n\n\n\n\n\n\n\n ABRINDO EDITOR");
        MilitaryUnit militaryUnit = dao.findById(id);
        //passa uma lista de todas as unidades
        List<MilitaryUnit> units = dao.getAll();//recupera todas as unidades
        units.remove(militaryUnit);//remove a própria unidade da lista
        model.addAttribute("units", units);//acrescenta a lista ao model
        List<Zone> zones = zoneDao.getAll();
        model.addAttribute("zones", zones);//acrescenta a lista de municipios ao model

        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        //System.out.println("remonta a lista transiente dos municípios que formam a região para uma MilitaryUnit");
        List<MURegionMap> storedRegion = daoRMap.getRegionByMUId(id);

        Region rg;

        militaryUnit.setRegions(new ArrayList<Region>(zones.size()));//seta uma list vazia para todas as zonas para militaryUnit.regions.zone_id
        for (int i = 0; i < zones.size(); i++) {//converte em uma lista de regiões
//            militaryUnit.getRegions().get(i).setZone_id(zones.get(i).getId());//recebe um-a-um todos os ids de todas as zonas

            rg = new Region();
            rg.setZone_id(zones.get(i).getId());
            rg.setIncluded(Boolean.FALSE);
            militaryUnit.getRegions().add(i, rg);//seta zona como não integrante por default

            for (int j = 0; j < storedRegion.size(); j++) {//verifica se consta registro da zona como integrante da região da unidade militar no banco
                System.out.println("\n\n\nComparando storedRegion zone id[" + storedRegion.get(j).getZone().getId() + "]  com militaryUnit regions zone id [" + militaryUnit.getRegions().get(i).getZone_id() + "]");
                if (storedRegion.get(j).getZone().getId() == militaryUnit.getRegions().get(i).getZone_id()) {
                    militaryUnit.getRegions().get(i).setIncluded(Boolean.TRUE);
                    System.out.println("Entrou vai setar true para região de id:" + militaryUnit.getRegions().get(i).getZone_id());
                }
            }

        }
        System.out.println("Lista de zonas:" + militaryUnit.getRegions().toString());

        //TODO_next:alterar a remontagem da lista considerando o tipo de ligação
        //remonta a lista transiente de depenencias existente na classe MilitaryUnit
        List<MUDependencies> storedDeps = mudDao.getAllById(id); //recuperar todas as dependencias relacionadas à unidade a ser editada
        System.out.println("\n\n\n\n\n\n\n LISTA ORIGINAL RECUPERADA DO BANCO:");
        MUDeps mudAux;
        List<MUDeps> deps = new ArrayList<MUDeps>(units.size());//todas as unidades existentes irão aparecer, menos a própria unidade
        for (int i = 0; i < storedDeps.size(); i++) {
            System.out.println("Índice [" + i + "] Subordinada:" + storedDeps.get(i).getSubordinatedUnit().getMU_name() + "Superior:" + storedDeps.get(i).getSuperiorUnit().getMU_name());
        }
        for (int i = 0; i < units.size(); i++) {//corre a lista de todas as unidades e estabelece a relação na lista para cada unidade
            System.out.println("\n\n\n\n\n Analisando unidade - " + units.get(i).getMU_name());
            mudAux = new MUDeps();
            mudAux.setMu_id(units.get(i).getId());
            //Corre a lista de dependencias storedDeps para encontrar a relação entre as unidades
            for (int j = 0; j < storedDeps.size(); j++) {
                //Caso 1: a unidade em edição é subordinada, relação de subordinação    
                if (storedDeps.get(j).getSubordinatedUnit().getId() == mudAux.getMu_id()) {
                    System.out.print(" - Unidade superior");
                    mudAux.setMu_dep(DependencyTypeEnum.SUP.getDescription());
                    // mudAux.setMu_id(storedDeps.get(j).getSuperiorUnit().getId());
                } else if (storedDeps.get(j).getSuperiorUnit().getId() == mudAux.getMu_id()) {//Caso 2: relação de superioridade
                    System.out.print("- Unidade subordinada");
                    mudAux.setMu_dep(DependencyTypeEnum.SUB.getDescription());
                    //mudAux.setMu_id(storedDeps.get(j).getSubordinatedUnit().getId());
                }
            }
            if (mudAux.getMu_dep() == null) {//Caso 3: a unidade não tem relação 
                System.out.print("- Unidade sem relação");
                mudAux.setMu_dep(DependencyTypeEnum.NR.getDescription());
            }
            deps.add(i, mudAux);
            System.out.println("Deps adicionado, dependencia:" + deps.get(i).getMu_dep() + " Id:" + deps.get(i).getMu_id());
        }

        militaryUnit.setDeps(deps);
        model.addAttribute("deps", deps);
        model.addAttribute("regions", militaryUnit.getRegions());
        model.addAttribute("militaryUnit", militaryUnit);
        model.addAttribute("content", "addMilitaryUnit");
        return new ModelAndView("layout", model);
    }

    /**
     * Efetiva uma atualização de unidade militar
     */
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("militaryUnit") MilitaryUnit militaryUnit, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            System.out.println("\n\n\n\n\n\n\n\n\n ERROR:" + result.toString());
            return new ModelAndView("/addUser");
        }
        //System.out.println("remonta a lista transiente dos municípios que formam a região para uma MilitaryUnit");
        List<MURegionMap> storedRegion = daoRMap.getRegionByMUId(militaryUnit.getId());
        Zone zo;

        MURegionMap rgM;
        //Verifica a situação de cada zona e atualiza a região definida para a unidade
        for (int i = 0; i < militaryUnit.getRegions().size(); i++) {//para cada zona recuperada
            zo = zoneDao.getById(militaryUnit.getRegions().get(i).getZone_id());//pega a zona
            rgM = new MURegionMap();
            rgM.setZone(zo);
            rgM.setMilitaryUnit(militaryUnit);
            //Compara a situação de cada zona no banco com a nova situação após atualização
            //Para cada zona marcada como included.true, inclui, se necessário, registro de região em RegionMap 
            if (militaryUnit.getRegions().get(i).getIncluded()) {
                //se não há registro
                if (daoRMap.getRegionByMUandZoneId(zo.getId(), militaryUnit.getId()) == null) {
                    daoRMap.saveMURegion(rgM);//salva registro da zona como região da UM
                }
            } else {//Para cada zona marcada como included.false, remove registros de região casos existam para a Unidade Militar em atualização
                //se há registro
                if (daoRMap.getRegionByMUandZoneId(zo.getId(), militaryUnit.getId()) != null) {
                    daoRMap.deleteZoneRegistry(zo.getId(), militaryUnit.getId());//remove o registro da zona como região da UM
                }
            }

        }

        MUDependencies mud = new MUDependencies();
//      MilitaryUnit mut;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        //System.out.println("Verifica a situação de cada dependência e atualiza a tabela de dependencias entre unidades");
        //recuperar todas as dependencias existentes no banco para a unidade (MUDependencies)
        //List<MUDependencies> deps = mudDao.getAllById(militaryUnit.getId());
        DependencyTypeEnum dpe;
        for (int i = 0; i < militaryUnit.getDeps().size(); i++) {//para cada unidade relacionada a lista transiente
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(">>>>Analisando dependencia cadastrada no banco com a unidade: " + dao.findById(militaryUnit.getDeps().get(i).getMu_id()).getMU_name());
            //recebe dependencia do banco
            dpe = mudDao.getDependencyType(militaryUnit.getId(), militaryUnit.getDeps().get(i).getMu_id());
            System.out.println("\n\n");
            System.out.println(">>>>Dependencia no banco é: " + dpe.getDescription());

            //se dependencia do banco e do form são diferentes
            if (!dpe.getDescription().equals(militaryUnit.getDeps().get(i).getMu_dep())) {
                System.out.println("\n\n");
                System.out.println(">>>>>>>>>>>>>>Dependencias são diferentes");
                //se dependencia do banco é NR
                if (dpe.equals(DependencyTypeEnum.NR)) {
                    System.out.println("\n\n");
                    System.out.println(">>>>>>>>>>>>>>Dependencia no banco é NR");
                    if (militaryUnit.getDeps().get(i).getMu_dep().equals(DependencyTypeEnum.SUP.getDescription())) {//se dependencia do form é SUP
                        System.out.println("\n\n");
                        System.out.println(">>>>>>>>>>>>>>Dependencia no form é SUP");
                        mud.setSuperiorUnit(militaryUnit);//seta unidade superior a unidade em edição
                        mud.setSubordinatedUnit(dao.findById(militaryUnit.getDeps().get(i).getMu_id()));//seta unidade inferior a unidade relacionada

                    } else if (militaryUnit.getDeps().get(i).getMu_dep().equals(DependencyTypeEnum.SUB.getDescription())) {//se dependencia do form é SUP
                        System.out.println("\n\n");
                        System.out.println(">>>>>>>>>>>>>>Dependencia no form é SUB - ");
                        mud.setSuperiorUnit(dao.findById(militaryUnit.getDeps().get(i).getMu_id()));//seta unidade superior a unidade em edição
                        mud.setSubordinatedUnit(militaryUnit);//seta unidade inferior a unidade relacionada
                    }
                    //como as duas alterações são save new, neste caso o save fica no final do if - NR
                    mudDao.save(mud);
                    System.out.println("\n\n");
                    System.out.println(">>>>>>>>>>>>>>Persistida nova dependencia");

                } else if (dpe.equals(DependencyTypeEnum.SUP)) {
                    System.out.println("\n\n");
                    System.out.println(">>>>>>>>>>>>>>Dependencia no banco é SUP");
                    if (militaryUnit.getDeps().get(i).getMu_dep().equals(DependencyTypeEnum.NR.getDescription())) {//se dependencia do form é SUP
                        System.out.println("\n\n");
                        System.out.println(">>>>>>>>>>>>>>Dependencia no form é NR");
                        mudDao.deleteMUDep(militaryUnit.getId(), militaryUnit.getDeps().get(i).getMu_id());
                        //delete dependencia do banco
                    } else if (militaryUnit.getDeps().get(i).getMu_dep().equals(DependencyTypeEnum.SUB.getDescription())) {//se dependencia do form é SUP
                        System.out.println("\n\n");
                        System.out.println(">>>>>>>>>>>>>>Dependencia no form é SUB - ");
                        //update dependencia do banco para SUB
                        mud = mudDao.getDependency(militaryUnit.getId(), militaryUnit.getDeps().get(i).getMu_id());//recupera a dependencia do banco
                        //switch
                        mud.switchMUs();
                        mudDao.update(mud);
                    }
                } else if (dpe.equals(DependencyTypeEnum.SUB)) {
                    System.out.println("\n\n");
                    System.out.println(">>>>>>>>>>>>>>Dependencia no banco é SUB");
                    if (militaryUnit.getDeps().get(i).getMu_dep().equals(DependencyTypeEnum.NR.getDescription())) {//se dependencia do form é SUP
                        System.out.println("\n\n");
                        System.out.println(">>>>>>>>>>>>>>Dependencia no form é NR");
                        mudDao.deleteMUDep(militaryUnit.getDeps().get(i).getMu_id(), militaryUnit.getId());
                        //delete dependencia do banco
                    } else if (militaryUnit.getDeps().get(i).getMu_dep().equals(DependencyTypeEnum.SUP.getDescription())) {//se dependencia do form é SUP
                        System.out.println("\n\n");
                        System.out.println(">>>>>>>>>>>>>>Dependencia no form é SUP - ");
                        //update dependencia do banco para SUB
                        mud = mudDao.getDependency(militaryUnit.getDeps().get(i).getMu_id(), militaryUnit.getId());//recupera a dependencia do banco
                        //switch
                        mud.switchMUs();
                        mudDao.update(mud);
                    }

                }

                //senão - se dependencia do banco é SUP ou SUB
                //se são diferentes
            } else {

                System.out.println("\n\n");
                System.out.println(">>>>>>>>>>>>>>SEM ALTERAÇÃO");
            }

        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Fim do laço");

        dao.update(militaryUnit);
        attr.addFlashAttribute("message", "Unidade alterada com sucesso.");
        return new ModelAndView("redirect:/militaryUnitControl/listMUs");
    }

    /**
     * Exclui uma unidade militar pelo seu id
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        //System.out.println("Solicitou exclusão de unidade militar");
        //TODO: Implementar todas as anulações de todas as ocorrências de unidade militar como chave estrangeira
//        - remover todas as dependencias registradas para outras unidades
//        - remover todos os registros de região cadastrados para a unidade

        //Anula as referencias por chave estrangeira para cadastro de militares
        List<Military> list = milDao.getAllForMU(id);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setMilitaryUnit(null);
            milDao.update(list.get(i));
        }
        mudDao.deleteMU(id);
        dao.delete(id);
        attr.addFlashAttribute("message", "Unidade excluída com sucesso.");
        return "redirect:/militaryUnitControl/listMUs";
    }

}
