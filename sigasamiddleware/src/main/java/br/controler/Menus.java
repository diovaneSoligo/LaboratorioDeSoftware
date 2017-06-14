/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controler;

import br.dao.DaoSistema;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Diovane
 */
@Controller
public class Menus {

    /**/
 /*  MENU INICIO  */

    /**
     *
     * @param session
     * @return 
     */

    @RequestMapping("home")
    public ModelAndView PáginaInicialDoSistema(HttpSession session) {
        System.out.println("...chamou a página HOME.");

        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        System.out.println("...ABRINDO página");

        ModelAndView mv = new ModelAndView("sistema/inicial");
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi mdi-home\"></span> inicío");

        return mv;
    }

    /**/
 /*  MENU ADM DRIVER  */

    /**
     *
     * @param session
     * @return 
     */

    @RequestMapping("administrar_driver")
    public ModelAndView PaginaAdmDriver(HttpSession session) {
        System.out.println("...chamou a página ADMINISTRAR DRIVE.");

        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        System.out.println("...ABRINDO página");

        ModelAndView mv = new ModelAndView("sistema/adm_driver");
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers");
        return mv;
    }

    /**/
 /*  MENU VISUALIZAR DRIVER  */

    /**
     *
     * @param session
     * @return 
     */

    @RequestMapping("visualizar_driver")
    public ModelAndView PaginaIniciaVisualizaDriver(HttpSession session) {
        System.out.println("...chamou a página Visualizar DRIVE.");

        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        System.out.println("...ABRINDO página");
        ModelAndView mv = new ModelAndView("sistema/visualizar_driver");
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-eye\"></span> Visualizar Drivers");
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        return mv;
    }

    /**/
 /* MENU MINHA CONTA  */

    /**
     *
     * @param session
     * @return 
     */

    @RequestMapping("minha_conta")
    public ModelAndView PaginaMinhaConta(HttpSession session) {
        System.out.println("...chamou a página Minha Conta.");

        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }

        System.out.println("...ABRINDO página");
        ModelAndView mv = new ModelAndView("sistema/minha_conta");
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi mdi-account-settings-variant\"></span> Minha Conta");

        return mv;
    }
}
