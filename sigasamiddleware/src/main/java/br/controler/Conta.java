/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controler;

import br.dao.DaoUsuario;
import javax.servlet.http.HttpSession;
import modelo.DadosUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Diovane
 */
@Controller
public class Conta {

    boolean cad = false;

    /**
     * *************************************************************
     */
    //recupera dados de conta
    @RequestMapping("recuperarContaDeAcesso")
    public String recuperarAcesso() {
        System.out.println("Entrou em recuperar conta de acesso");

        //colocar aqui código para buscar e-mail e mandar 
        if (true) {
            return "forward:retornaOKEmail";
        } else {
            return "forward:retornaERROEmail";
        }
    }

    @RequestMapping("retornaERROEmail")
    public ModelAndView ERRO() {
        System.out.println("...chamou o retornaERRO recupera email....");

        ModelAndView mv = new ModelAndView("../../index");
        mv.addObject("notificacaoERRO", "Algo de errado ocorreu, verifique a conexão com a internet, ou se você já realizou o cadastro ao sistema.");

        return mv;
    }

    @RequestMapping("retornaOKEmail")
    public ModelAndView OK() {
        System.out.println("...chamou o retornaOK recupera email....");

        ModelAndView mv = new ModelAndView("../../index");
        mv.addObject("notificacaoOK", "Um e-mail foi enviado para sua caixa de entrada, verifique seu e-mail daqui alguns instantes (verifique também sua caixa de span)!");

        return mv;
    }

    /**
     * **********************************************************************
     */
    //acessa conta
    @RequestMapping("paginaprincipal")
    public String efetuarLogin(DadosUsuario dadosDeAcesso, HttpSession session) {
        System.out.println("Entrou em efetuar login --> paginaprincipal");
        System.out.println("dados de acesso recebidos.\n"
                + "Usuario: " + dadosDeAcesso.getUsuario()
                + "\nSenha: " + dadosDeAcesso.getSenha());

        String user = dadosDeAcesso.getUsuario();

        if (DaoUsuario.logar(dadosDeAcesso)) {

            System.out.println("usuario existente, verificando se é primeiro acesso...");
            if (user.equals("admin")) {
                System.out.println("Primeiro acesso, direcionando para pagina de cadastro...");
                cad = true;
                return "forward:pr1m31r0c@d@str0";
            } else {
                //inicia sesão e vai para pagina inicial
            }
        }
        System.out.println("OPS!!! usuário inválido");
        return "forward:retornaERRoLogin";//retorna pra pagina de login sem mensagem
    }

    @RequestMapping("retornaERRoLogin")
    public ModelAndView ErroLogin() {
        System.out.println("...chamou o retornaERRO login.");

        ModelAndView mv = new ModelAndView("../../index");
        mv.addObject("notificacaoERRO", " Usuário ou senha inválido!");

        return mv;
    }

    @RequestMapping("pr1m31r0c@d@str0")
    public ModelAndView cadastro() {
        System.out.println("...chamou cadastro: " + cad);
        ModelAndView mv;
        if (cad) {
            System.out.println("direcionando pagina para cadastro");
            cad = false;

            mv = new ModelAndView("primeiroCadastro");
            return mv;
        }
        mv = new ModelAndView("../../index");
        return mv;
    }
    /**
     * **********************************************************************
     */
    //cadastra
    @RequestMapping("cadastro_unico")
    public String cadastro(DadosUsuario dadosDeAcesso) {
        System.out.println("Entrou em efetuar cadastro--> cadastro_unico");
        System.out.println("dados de cadastro recebidos.\n"
                + "Nome: " + dadosDeAcesso.getNome()
                + "\nSobrenome: " + dadosDeAcesso.getSobrenome()
                + "\nE-mail: " + dadosDeAcesso.getEmail()
                + "\nRG: " + dadosDeAcesso.getRg()
                + "\nCPF: " + dadosDeAcesso.getCpf()
                + "\nData nascimento: " + dadosDeAcesso.getNascimento()
                + "\nUsuário: " + dadosDeAcesso.getUsuario()
                + "\nSenha: " + dadosDeAcesso.getSenha());
        
        //escrever codigo para inserir no banco os dados, setar os que ja vem padrão

        return "forward:cadastroOK";
    }
    @RequestMapping("cadastroOK")
    public ModelAndView cadastroOK() {
        System.out.println("...chamou cadastroOK");
        ModelAndView mv;
        mv = new ModelAndView("../../index");
        mv.addObject("notificacaoOK", "Cadastro Efetuado com sucesso!");
        return mv;
    }
}
