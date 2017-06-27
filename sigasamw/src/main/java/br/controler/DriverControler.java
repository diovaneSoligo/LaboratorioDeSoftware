/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controler;

import br.dao.DaoDriver;
import br.dao.DaoSistema;
import br.com_driver.Com_driver;
import br.modelo.Driver;
import br.modelo.Sensor;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DriverControler {

    @RequestMapping(value = "/up_driver", method = RequestMethod.POST)
    public ModelAndView up_driver(HttpSession session, @RequestParam("arquivo") MultipartFile[] arquivo) throws IOException {

        /* verifica se usuario logado */
        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        ModelAndView mv = new ModelAndView("sistema/adm_driver");

        if (!arquivo[0].isEmpty()) {//verifica se possui arquivo

            try {
                //ferivicar se o mesmo já nao existe no diretório
                if (Com_driver.verificaDiretorio(arquivo)) {

                    //Grava o jar no diretório
                    File CaminhoJAR = Com_driver.gravaJar(arquivo);

                    //Acrescenta o novo jar no Classpath do sistema
                    //Com_driver.adicionarAoClasspath(CaminhoJAR);
                    //lista no log as classes presentes no novo .jar
                    Com_driver.getClasseNamesInPackage(CaminhoJAR);

                    //carrega jar e pega dados
                    Driver info
                            = Com_driver.capturaDriver(CaminhoJAR, arquivo[0].getOriginalFilename());

                    //armazenar dados do driver no banco
                    if (DaoDriver.armazenaDriver(info)) {
                        System.out.println("classes inseridas com sucesso");
                        mv.addObject("notificacaoOK", "DRIVER < " + arquivo[0].getOriginalFilename() + " > INSTALADO COM SUCESSO");
                    } else {
                        System.out.println("ERRO");
                        mv.addObject("notificacaoERRO", "Ocorreu um erro ao instalar < " + arquivo[0].getOriginalFilename() + " >");
                    }
                } else {
                    System.out.println("ERRO diretório existente");
                    mv.addObject("notificacaoERRO", "O driver < " + arquivo[0].getOriginalFilename() + " > já consta no sistema!");
                }

            } catch (Exception e) {
                System.out.println("You failed to upload " + arquivo[0].getOriginalFilename() + " => " + e.getMessage());
                mv.addObject("notificacaoERRO", "FALHA NA INSTALAÇÃO DO DRIVER < " + arquivo[0].getOriginalFilename() + " > , ERRO < " + e.getMessage() + " >");
            }

        } else {
            System.out.println("You failed to upload " + arquivo[0].getOriginalFilename() + " because the file was empty.");
            mv.addObject("notificacaoERRO", "FALHA NA INSTALAÇÃO DO DRIVER, O MESMO NÃO FOI SELECIONADO");
        }

        System.out.println("Voltando para página...");
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers");
        mv.addObject("drivers_modulos", new DaoDriver().listaDrivers());
        return mv;
    }

    @RequestMapping(value = "/deletar_driver", method = RequestMethod.POST)
    public ModelAndView deletarDriver(HttpSession session, Driver id) throws IOException, InterruptedException {
        System.out.println("Chamou deletar_driver ID: " + id.getID());
        /* verifica se usuario logado */
        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        ModelAndView mv = new ModelAndView("sistema/adm_driver");

        if (Com_driver.deletaDriver(id.getID())) {
            mv.addObject("notificacaoOK", "DRIVER DESINSTALADO COM SUCESSO!");
        } else {
            mv.addObject("notificacaoERRO", "FALHA NA DESINSTALAÇÃO DO DRIVER, O MESMO ESTÁ EM USO PELO SISTEMA SIGASA!");
        }

        System.out.println("Voltando para página...");
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers");
        mv.addObject("drivers_modulos", new DaoDriver().listaDrivers());
        return mv;
    }

    @RequestMapping(value = "/atualizar_sensores_driver", method = RequestMethod.POST)
    public ModelAndView atualizar_sensores_driver(HttpSession session, Driver id) throws IOException {
        System.out.println("Chamou atualizar_sensores_driver ID: " + id.getID());
        /* verifica se usuario logado */
        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        ModelAndView mv = new ModelAndView("sistema/visualizar_driver");

        //loader class . invocar metodo get sensores e armazenar os mesmos no banco . retornar quantos encontrados
        int total = Com_driver.buscaAtualizaSensores(id.getID());//id do driver

        if (total != 0) {
            mv.addObject("notificacaoOK", "<h4>Atualização concluída, < " + total + " > sensore(s) localizados</h4>");
        } else {
            mv.addObject("notificacaoERRO", "Nenhum Sensor encontrado :(");
        }

        System.out.println("Voltando para página...");
        mv.addObject("dados_driver", DaoDriver.buscaDadosAtualizarSensores(id.getID()));
        mv.addObject("lista_sensores", new DaoDriver().listaSensores(id.getID()));
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers / Ver Drivers");
        
        return mv;
    }

    @RequestMapping(value = "/invokeSensor", method = RequestMethod.POST)
    public ModelAndView invokeSensor(HttpSession session, Sensor id) throws IOException {
        System.out.println("Chamou invokeSensor ID do sensor: " + id.getIdSensor());
        /* verifica se usuario logado */
        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        ModelAndView mv = new ModelAndView("sistema/visualizar_driver");

        //invocar, retornar o resultado do invoke, e id do driver do driver para buscar os dados pra página
        Sensor s = Com_driver.invocarSensor(id.getIdSensor());

        System.out.println("Voltando para página...");
        mv.addObject("notificacaoOK", "<h4 style=\"color: blue;\"> Retorno do sensor < " + s.getIdSensor() + " > IP: < " + s.getIpSensor() + " ></h4><h3 style=\"color: #000;\">" + s.getRetornoDados() + " <span class=\"mdi mdi-keyboard-return\" style=\"color: blue;\"></span></h3>");
        mv.addObject("dados_driver", DaoDriver.buscaDadosAtualizarSensores(s.getID()));
        mv.addObject("lista_sensores", new DaoDriver().listaSensores(s.getID()));
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers / Ver Drivers");
        mv.addObject("drivers_modulos", new DaoDriver().listaDrivers());
        return mv;
    }

    @RequestMapping(value = "/ver_sensores_driver", method = RequestMethod.POST)
    public ModelAndView ver_sensores_driver(HttpSession session, Driver id) throws IOException {
        System.out.println("Chamou ver_sensores_driver ID: " + id.getID());
        /* verifica se usuario logado */
        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }
        ModelAndView mv = new ModelAndView("sistema/visualizar_driver");

        System.out.println("Voltando para página...");
        mv.addObject("dados_driver", DaoDriver.buscaDadosAtualizarSensores(id.getID()));
        mv.addObject("lista_sensores", new DaoDriver().listaSensores(id.getID()));
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers / Ver Drivers");
        return mv;
    }

}
