package br.conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static void main(String args[]) {
        Conexao.getConexao();
        System.out.println("conexao aberta!");
    }

    public static Connection getConexao() {
        Connection c = null;
        System.out.println("Conecta BD");
        try {
            Class.forName("org.postgresql.Driver");		//SE NECESSARIO ALTERAR O CAMINHO DO BANCO
            String url = "jdbc:postgresql://localhost:5432/sigasa";
            String user = "postgres"; 			// ALTERAR USUARIO SE NECESSARIO
            String password = "1234"; 			// ALTERAR A SENHA
            c = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("ERRO EM CATCH NO CONECTAR COM O BANCO");
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ERRO EM CATCH NO CONECTAR COM O BANCO");
        }

        return c;
    }

}
