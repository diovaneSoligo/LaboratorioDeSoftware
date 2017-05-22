/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controler;

import br.dao.DaoUsuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DadosUsuario;
import modelo.RecuperaEmail;
import org.apache.commons.codec.binary.Base64;
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
    public String recuperarAcesso(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("Entrou em recuperar conta de acesso");

        String email = DaoUsuario.recuperaAcesso();//busca e-mail
        System.out.println("E-mail: " + email);
        if (email == null) {
            return "forward:retornaERROEmail";
        }
        RecuperaEmail recuperacao = new RecuperaEmail();
        recuperacao.setEmail(email);

        if (DaoUsuario.enviaEmail(recuperacao)) {
            Cookie codigoV = new Cookie("codigoV", recuperacao.getVerificacaoString());//armazena o código de verificação em um cookie
            codigoV.setMaxAge(300);//seta pra valer por 5 minutos
            response.addCookie(codigoV);//adiciona o cookie ao cliente

            System.out.println("Valor do cookie: " + codigoV.getValue());

            return "forward:retornaOKEmail";
        }

        return "forward:retornaERROEmail";

        //verificar se existe e-mail
        //mandar e-mail
        //se email enviado, cria cookie 
        //a parti do email acessa um link q abre só se ouver o cookie ainda ativo
        //colocar aqui código para buscar e-mail e mandar 
    }

    @RequestMapping("retornaERROEmail")
    public ModelAndView ERRO() {
        System.out.println("...chamou o retornaERRO recupera acesso poremail....");

        ModelAndView mv = new ModelAndView("../../index");
        mv.addObject("notificacaoERRO", "Algo de errado ocorreu :( ");

        return mv;
    }

    @RequestMapping("retornaOKEmail")
    public ModelAndView OK() {
        System.out.println("...chamou o retornaOK recupera email....");
        //ABRIR PAGINA PEDINDO O CÓDIGO DE VERIFICAÇÃO ENVIADO POR EMAIL E O CPF
        ModelAndView mv = new ModelAndView("recuperacaopasso1");//criar página
        mv.addObject("notificacaoOK", "Verifique seu e-mail, copie o código de verificação e insira no campo de código de verificação!");

        return mv;
    }

    /**
     * **********************************************************************
     */
    //recuperaAcesso
    @RequestMapping("recuperarConta")//verifica código de verificação e cpf
    public String recuperarAcessoCodigo(RecuperaEmail dadosrecupera, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String nome = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("codigoV")) {
                nome = cookie.getValue();
                System.out.println("Valor do cookie recuperado: " + nome);

                System.out.println("Código digitado: " + dadosrecupera.getVerificacaoString());
                System.out.println("CPF digitado: " + dadosrecupera.getCpf());
                if (dadosrecupera.getVerificacaoString() == null) {
                    return "forward:retornaERROrecupera";
                }
                //gerar hash da senha
                System.out.println("Gerando HASH do código digitado...");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashSenhaSHA256 = md.digest(dadosrecupera.getVerificacaoString().getBytes());
                //passar hash pra string
                byte[] hashSenha = Base64.encodeBase64(hashSenhaSHA256);
                String valor = new String(hashSenha, "ISO-8859-1");
                dadosrecupera.setVerificacaoString(valor);
                System.out.println("HASH do código digitado gerado: " + dadosrecupera.getVerificacaoString() + "\nComparando...");

                if (dadosrecupera.getVerificacaoString().equals(nome)) {
                    System.out.println("Código de verificação válido\nVerificando CPF...");
                    //verificar CPF para poder abrir a pagina de redefinir acesso
                    if (DaoUsuario.buscarCPF(dadosrecupera)) {
                        System.out.println("CPF válido");
                        cad = true;
                        return "forward:retornaOKrecupera";
                    }
                }
            }
        }
        return "forward:retornaERROrecupera";
    }

    @RequestMapping("retornaERROrecupera")//ageitar
    public ModelAndView ERROrecupera() {
        System.out.println("...chamou o retornaERRO recupera acesso poremail....");

        ModelAndView mv = new ModelAndView("../../index");
        mv.addObject("notificacaoERRO", " Código Expirado!.");

        return mv;
    }

    @RequestMapping("retornaOKrecupera")//ageitar
    public ModelAndView OKrecupera(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("...chamou o retornaOK recupera email....");
        //verificar se cad é true e se o cookie ainda existe
        if (cad) {//verifica se passou pelas outras verificações
            cad = false;
            System.out.println("Até aqui tudo ok, verificando cookie...");
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("codigoV")) {
                    System.out.println("Cookie exixtente\nValor do cookie recuperado: " + cookie.getName() + "Abrindo página para redefinição");

                    ModelAndView mv = new ModelAndView("redefinicaoDeAcesso");//abre pagina pra redefinir acesso
                    mv.addObject("notificacaoOK", "CPF e Código de verificação válidos, redefina o usuário de acesso e a senha");
                    return mv;
                }
            }
        }
        System.out.println("Ou não passou pelos outros passou ou o cookie expirou.");
        //
        ModelAndView mv = new ModelAndView("../../index");//criar página
        mv.addObject("notificacaoERRO", "Erro :( ");

        return mv;
    }

    /**
     * **********************************************************************
     */
    //acessa conta LOGIN
    @RequestMapping("paginaprincipal")
    public String efetuarLogin(DadosUsuario dadosDeAcesso, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("Entrou em efetuar login --> paginaprincipal");
        System.out.println("dados de acesso recebidos.\n"
                + "Usuario: " + dadosDeAcesso.getUsuario()
                + "\nSenha: " + dadosDeAcesso.getSenha());

        if (dadosDeAcesso.getUsuario() == null) {
            System.out.println("Campos USUARIO E SENHA nulos...");
            return "forward:retornaERRoLogin";
        }

        String user = dadosDeAcesso.getUsuario();

        if (DaoUsuario.logar(dadosDeAcesso)) {

            System.out.println("usuario existente, verificando se é primeiro acesso...");
            if (user.equals("admin")) {
                System.out.println("SIM. Primeiro acesso, direcionando para pagina de cadastro...");
                cad = true;
                return "forward:pr1m31r0c@d@str0";
            } else {
                System.out.println("NÃO");
                System.out.println("INICIANDO SESSÃO E DIRECIONANDO PARA PAGINA PRINCIPAL DO SISTEMA...");
                session.setAttribute("usuarioLogado", DaoUsuario.logar(dadosDeAcesso));
                return "forward:home";
            }
        }

        System.out.println("OPS!!! usuário inválido");
        return "forward:retornaERRoLogin";//retorna pra pagina de login sem mensagem
    }

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

        return mv;
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
    public String cadastro(DadosUsuario dadosDeAcesso) throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
        String user = dadosDeAcesso.getUsuario();

        if (user.equals("admin")) {
            return "forward:cadastroERRO";
        }
        if (DaoUsuario.cadastra(dadosDeAcesso)) {
            return "forward:cadastroOK";
        }

        return "forward:cadastroERRO";
    }

    @RequestMapping("cadastroOK")
    public ModelAndView cadastroOK() {
        System.out.println("...chamou cadastroOK");
        ModelAndView mv;
        mv = new ModelAndView("../../index");
        mv.addObject("notificacaoOK", "Cadastro Efetuado com sucesso! Acesse o sistema com seu usuário e senha cadastrados.");
        return mv;
    }

    @RequestMapping("cadastroERRO")
    public ModelAndView cadastroERRO() {//erro somente se o javascript não funcionar
        System.out.println("...chamou cadastroERRO");
        ModelAndView mv;
        mv = new ModelAndView("../../index");
        mv.addObject("notificacaoERRO", " Você utilizou como usuário uma palavra reservada do sistema!");
        return mv;
    }

    /**
     * **********************************************************************
     */
    //Redefinir acesso
    @RequestMapping("rEdeFinic@o_de-C0nta")
    public String redefinirAcesso(DadosUsuario dadosDeAcesso, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("Entrou em efetuar redefiir acesso");

        if (dadosDeAcesso.getUsuario() == null) {
            System.out.println("Campos USUARIO E SENHA nulos...");
            return "forward:retornaERRoRedefinir";
        }

        for (Cookie cookie : request.getCookies()) {//verifica se o código ainda está valido, no tempo
            if (cookie.getName().equals("codigoV")) {
                System.out.println("Cookie ainda exixtente\nValor do cookie recuperado: " + cookie.getName());
                System.out.println("dados de acesso redefinição recebidos.\n"
                        + "Usuario: " + dadosDeAcesso.getUsuario()
                        + "\nSenha: " + dadosDeAcesso.getSenha());

                if (DaoUsuario.redefinirAcesso(dadosDeAcesso)) {
                    System.out.println("Dados de acesso redefinodos com sucesso!");
                    cad = true;
                    return "forward:redefinicaoOK";
                }
            }
        }

        System.out.println("OPS!!! Ocorreu algum erro em redefinir acesso");
        return "forward:retornaERRoLogin";//retorna pra pagina de login sem mensagem
    }

    @RequestMapping("retornaERRoRedefinir")
    public ModelAndView erroRedefinicao() {
        System.out.println("...chamou o retornaERRO redefinir.");

        ModelAndView mv = new ModelAndView("../../index");
        mv.addObject("notificacaoERRO", " Erro :( !");

        return mv;
    }

    @RequestMapping("redefinicaoOK")
    public ModelAndView redefinicaoOk() {
        System.out.println("...chamou cadastro: ");
        ModelAndView mv;
        if (cad) {
            cad = false;
            mv = new ModelAndView("../../index");
            mv.addObject("notificacaoOK", " Usuário e Senha redefinidos com sucesso :)");
            return mv;
        }
        mv = new ModelAndView("../../index");
        return mv;
    }

}
