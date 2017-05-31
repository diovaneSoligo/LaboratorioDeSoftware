/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import br.conectaBD.Conexao;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import br.modelo.DadosUsuario;
import br.modelo.RecuperaEmail;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Diovane
 */
public class DaoUsuario {

    public static boolean logar(DadosUsuario dadosDeAcesso) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Connection c = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        //gerar hash da senha
        System.out.println("Gerando HASH da senha...");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashSenhaSHA256 = md.digest(dadosDeAcesso.getSenha().getBytes());
        //passar hash pra string
        byte[] hashSenha = Base64.encodeBase64(hashSenhaSHA256);
        String valor = new String(hashSenha, "ISO-8859-1");
        dadosDeAcesso.setSenha(valor);
        System.out.println("HASH da senha gerado");
        try {
            c = Conexao.getConexao();
            String sql = "";
            sql = "select *from usuario where usuario = ? and senha = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dadosDeAcesso.getUsuario());
            stmt.setString(2, dadosDeAcesso.getSenha());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dadosDeAcesso.setNome(rs.getString("nome"));
                dadosDeAcesso.setSobrenome(rs.getString("sobrenome"));
                dadosDeAcesso.setEmail(rs.getString("email"));
                dadosDeAcesso.setRg(rs.getString("rg"));
                dadosDeAcesso.setCpf(rs.getString("cpf"));
                dadosDeAcesso.setNascimento(rs.getString("nascimento"));
                dadosDeAcesso.setUsuario(rs.getString("usuario"));
                dadosDeAcesso.setSenha(rs.getString("senha"));

                retorno = true;
            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static boolean cadastra(DadosUsuario dadosDeAcesso) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean retorno = false;
        Connection c = null;
        PreparedStatement stmt = null;

        //gerar hash da senha
        System.out.println("Gerando HASH da senha...");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashSenhaSHA256 = md.digest(dadosDeAcesso.getSenha().getBytes());
        //passar hash pra string
        byte[] hashSenha = Base64.encodeBase64(hashSenhaSHA256);
        String valor = new String(hashSenha, "ISO-8859-1");
        dadosDeAcesso.setSenha(valor);
        System.out.println("HASH da senha gerado: " + dadosDeAcesso.getSenha());
        try {
            System.out.println("Fazendo cadastro único...");
            c = Conexao.getConexao();
            String sql = "";
            sql = "update usuario set nome = ?"
                    + " , sobrenome = ?"
                    + " , email = ?"
                    + " , rg = ?"
                    + " , cpf = ?"
                    + " , nascimento = ?"
                    + " , usuario = ?"
                    + " , senha = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dadosDeAcesso.getNome());
            stmt.setString(2, dadosDeAcesso.getSobrenome());
            stmt.setString(3, dadosDeAcesso.getEmail());
            stmt.setString(4, dadosDeAcesso.getRg());
            stmt.setString(5, dadosDeAcesso.getCpf());
            stmt.setString(6, dadosDeAcesso.getNascimento());
            stmt.setString(7, dadosDeAcesso.getUsuario());
            stmt.setString(8, dadosDeAcesso.getSenha());

            stmt.execute();
            stmt.close();
            System.out.println("Cadastro Realizado");
            c.close();
            retorno = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO em Fazendo cadastro único");
        }

        return retorno;
    }

    public static String recuperaAcesso() {
        Connection c = null;
        PreparedStatement stmt = null;
        String retorno = null;

        try {
            c = Conexao.getConexao();
            String sql = "";
            sql = "select email from usuario";
            stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                retorno = rs.getString("email");
            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static boolean enviaEmail(RecuperaEmail recuperacao) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean retorno = false;

        Random random = new Random();
        int numeroDeVerificacao = (random.nextInt(1000) + 1000) * 3;//numro de verificação randomico
        recuperacao.setVerificacaoINT(numeroDeVerificacao);//numero de verificação setado
        System.out.println("Numero randomico gerado: " + recuperacao.getVerificacaoINT());

        System.out.println("Gerando HASH do numero randomico...");
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hashverificacaoSHA256 = md.digest(Integer.toString(recuperacao.getVerificacaoINT()).getBytes());
        //passar hash pra string
        byte[] hashVerificacao = Base64.encodeBase64(hashverificacaoSHA256);
        String valor = new String(hashVerificacao, "ISO-8859-1");
        recuperacao.setVerificacaoString(valor);
        System.out.println("HASH da verificação gerada: " + recuperacao.getVerificacaoString());

        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication()
                         {
                             return new PasswordAuthentication("suporte.f207@gmail.com", "suporteF207");
                         }
                    });
        /**
         * Ativa Debug para sessão
         */
       session.setDebug(true);
        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("suporte.f207@gmail.com")); //Remetente
              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(recuperacao.getEmail());  
              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject("SIGASA - Solicitação de recuperação de acesso");//Assunto
              String texto = " Olá "
              		+ "\n\n Sou o robo do seu sistema SIGASA e estou aqui para lhe ajudar a recuperar seu acesso ao sistema, vamos lá?"
              		+ "\n\n Caso você não tenha solicitado a recuperação de acesso, verifique se alguem não esta tentando acessar seu sistema!\n"
                        + " Caso tenha solicitado por engano ignore este e-mail"
              		+ "\n\n Bem, Vamos la!"
              		+ "\n\nPrimeiro tenha em mãos seu CPF\nGuarde esse número :< "+recuperacao.getVerificacaoINT()+" > Ele será seu código de verificação, e valerá por um tempo limite!"
              		+ "\nApós solicitar uma página foi aberta\n"
              		+ "Nessa página insira:\n"
                        + "- O código de verificação \n"
                        + "- E seu CPF\n\n"
              		+ "Voce terá após ter solicitado a recuperação, um tempo limite para recadastrar seus dados de acesso na página que abriu até que o código de verificação expire!"
                        + "\n\n\nCaso tenha dúvidas entre em contato com nossa central\n\nATT.: Suporte SIGASA";
              message.setText(texto);
              System.out.println("....Vai enviar email para\n"+recuperacao.getEmail()+"\ntexto\n"+texto);
            /**
             * Método para enviar a mensagem criada
             */
            // Transport.send(message);    //metodo para mandar o email
            Transport.send(message);
            System.out.println("email enviado!!!");
            retorno = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO catch em enviar email, nao conseguiur se conectar com o gmail");
            retorno = false;
        }

        //envia email com a pagina de acesso
        //junto ao email envia o numero randomico (hash)
        /*seta as variaveis 
        private String verificacaoString;//armazena o hash do numero randomico gerado*/
        return retorno;
    }

    public static boolean buscarCPF(RecuperaEmail dadosrecupera) {
        Connection c = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            String sql = "select cpf from usuario where cpf = ?;";

            c = Conexao.getConexao();
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dadosrecupera.getCpf());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                retorno = true;
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar o CPF");
        }

        return retorno;

    }

    public static boolean redefinirAcesso(DadosUsuario dadosDeAcesso) {
        boolean retorno = false;
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = Conexao.getConexao();

            //gerar hash da senha
            System.out.println("Gerando HASH da senha...");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashSenhaSHA256 = md.digest(dadosDeAcesso.getSenha().getBytes());
            //passar hash pra string
            byte[] hashSenha = Base64.encodeBase64(hashSenhaSHA256);
            String valor = new String(hashSenha, "ISO-8859-1");
            dadosDeAcesso.setSenha(valor);
            System.out.println("HASH da senha gerado: " + dadosDeAcesso.getSenha());

            String sql = "UPDATE usuario SET usuario = ? , senha = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dadosDeAcesso.getUsuario());
            stmt.setString(2, dadosDeAcesso.getSenha());

            stmt.execute();
            stmt.close();
            System.out.println("Redefinição Realizada");
            c.close();
            retorno = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }
}
