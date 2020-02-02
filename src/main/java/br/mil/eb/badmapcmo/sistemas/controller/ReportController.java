/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.dao.MilitaryDao;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;


/**
 * Controller de geração de relatórios com JasperReport
 * @author tenbenites
 */


@Controller
@RequestMapping("reportControl")
public class ReportController {
    @Autowired
    private MilitaryDao dao;
    @Autowired
    private ApplicationContext appContext;

    /**Gera relatório sobre militares cadastrados no sistema*/
    @RequestMapping(path = "/firstReport", method = RequestMethod.GET)
    public ModelAndView firstReport() {

//        JasperReportsPdfView view = new JasperReportsPdfView();
//        view.setUrl("classpath:report2.jrxml");
//        view.setApplicationContext(appContext);
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("datasource", dao.getAll());
//
//        return new ModelAndView(view, params);
return null;
      
    }
    
}
