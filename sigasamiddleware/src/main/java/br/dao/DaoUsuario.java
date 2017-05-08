/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import br.conectaBD.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.DadosUsuario;

/**
 *
 * @author Diovane
 */
public class DaoUsuario {

    public static boolean logar(DadosUsuario dadosDeAcesso) {
        Connection c = null;
        PreparedStatement stmt = null;
        boolean retorno = false;

        try {
            c = Conexao.getConexao();
            String sql = "";
            sql = "select *from usuario where usuario = ? and senha = ?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, dadosDeAcesso.getUsuario());
            stmt.setString(2, dadosDeAcesso.getSenha());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dadosDeAcesso.setNome("nome");
                dadosDeAcesso.setSobrenome("sobrenome");
                dadosDeAcesso.setEmail("email");
                dadosDeAcesso.setRg("rg");
                dadosDeAcesso.setCpf("cpf");
                dadosDeAcesso.setNascimento("nascimento");
                dadosDeAcesso.setUsuario("usuario");
                dadosDeAcesso.setSenha("senha");

                retorno = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

}
