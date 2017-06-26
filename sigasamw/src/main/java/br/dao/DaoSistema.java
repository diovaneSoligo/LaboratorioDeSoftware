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
import br.modelo.DadosUsuario;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Diovane
 */
public class DaoSistema {

    public static boolean verificaSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Connection c = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        //gerar hash da senha
        System.out.println("Gerando HASH da senha...");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashSenhaSHA256 = md.digest(senha.getBytes());
        //passar hash pra string
        byte[] hashSenha = Base64.encodeBase64(hashSenhaSHA256);
        String senhaHASH = new String(hashSenha, "ISO-8859-1");
        System.out.println("HASH da senha gerado");

        try {
            c = Conexao.getConexao();
            String sql = "";
            sql = "select *from usuario where senha = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, senhaHASH);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                retorno = true;
            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("senha OK?: " + retorno);
        return retorno;
    }

    public static boolean alteraUsuarioEmailSenha(DadosUsuario dados) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean retorno = false;
        Connection c = null;
        PreparedStatement stmt = null;

        //gerar hash da senha
        System.out.println("Gerando HASH da senha...");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashSenhaSHA256 = md.digest(dados.getSenha_nova().getBytes());
        //passar hash pra string
        byte[] hashSenha = Base64.encodeBase64(hashSenhaSHA256);
        String valor = new String(hashSenha, "ISO-8859-1");
        dados.setSenha_nova(valor);
        System.out.println("HASH da senha gerado: " + dados.getSenha_nova());
        try {
            System.out.println("Alterando email, usuario e senha...");
            c = Conexao.getConexao();
            String sql = "";
            sql = "update usuario set usuario = ?"
                    + " , email = ?"
                    + " , senha = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dados.getUsuario());
            stmt.setString(2, dados.getEmail());
            stmt.setString(3, dados.getSenha_nova());

            stmt.execute();
            stmt.close();
            System.out.println("Dados de acesso usuario email e senha alterados");
            c.close();
            retorno = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO em alterar dados de email, usuario e senha");
        }

        return retorno;
    }

    public static boolean alteraUsuarioEmail(DadosUsuario dados) {
        boolean retorno = false;
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            System.out.println("Alterando email e usuario");
            c = Conexao.getConexao();
            String sql = "";
            sql = "update usuario set usuario = ?"
                    + " , email = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dados.getUsuario());
            stmt.setString(2, dados.getEmail());

            stmt.execute();
            stmt.close();
            System.out.println("Dados de acesso email e usuario alterados");
            c.close();
            retorno = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO em alterar dados de email e usuario");
        }

        return retorno;
    }

    public Object dadosUsuario() {//busca dados do usuario
        DadosUsuario dados = new DadosUsuario();

        try {
            System.out.println("Buscando dados do usuario...");

            Connection c = null;
            c = Conexao.getConexao();

            String sql = null;
            sql = "select nome,sobrenome,email,rg,cpf,nascimento,usuario from usuario;";

            PreparedStatement stmt = null;
            stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dados.setNome(rs.getString("nome"));
                dados.setSobrenome(rs.getString("sobrenome"));
                dados.setEmail(rs.getString("email"));
                dados.setRg(rs.getString("rg"));
                dados.setCpf(rs.getString("cpf"));
                dados.setNascimento(rs.getString("nascimento"));
                dados.setUsuario(rs.getString("usuario"));
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println("ERRO ao buscar dados do usuário!!!");
            e.printStackTrace();
        }
        System.out.println("Dados do usuário OK");
        return dados;
    }

}
