/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.dao;

import br.conectaBD.Conexao;
import br.modelo.Driver;
import br.modelo.Sensor;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Diovane
 */
public class DaoDriver {

    public static boolean armazenaDriver(Driver info) {
        boolean retorno = false;
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            System.out.println("Iserindo dados do novo driver no banco...");
            c = Conexao.getConexao();
            String sql = "";
            sql = "insert into drivers"
                    + "(nomeDriver,descricao,fabricante,versao,uri,nomeJar,nomePacote,nomeClasse)"
                    + "values"
                    + "(?,?,?,?,?,?,?,?);";
            stmt = c.prepareStatement(sql);
            stmt.setString(1, info.getNomeDriver());
            stmt.setString(2, info.getDescricaoDriver());
            stmt.setString(3, info.getFabricanteDriver());
            stmt.setString(4, info.getPackDriver());
            stmt.setString(5, info.getURIDriver().getAbsolutePath());
            stmt.setString(6, info.getNomeJarDriver());
            stmt.setString(7, info.getPacoteDriver());
            stmt.setString(8, info.getClasseDriver());

            stmt.execute();
            stmt.close();
            System.out.println("Dados adicionados");
            c.close();
            retorno = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO em Fazendo cadastro único");
        }

        return retorno;
    }

    public static Driver buscaDadosAtualizarSensores(int id) {
        Driver d = new Driver();

        try {
            System.out.println("Buscando dados de drivers no banco...");
            Connection c = Conexao.getConexao();//Faz conexão com banco
            PreparedStatement stmt = c.prepareStatement("select nomedriver,descricao,iddriver,uri,nomepacote,nomeclasse from drivers where iddriver = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                d.setNomeDriver(rs.getString("nomedriver"));
                d.setDescricaoDriver(rs.getString("descricao"));
                d.setID(rs.getInt("iddriver"));
                d.setURIDriver(new File(rs.getString("uri")));
                d.setPacoteDriver(rs.getString("nomepacote"));
                d.setClasseDriver(rs.getString("nomeclasse"));
            }
            System.out.println("tudo OK ...");

        } catch (Exception e) {
            System.out.println("CATCH em public Object listaDrivers() ...");
            e.printStackTrace();
        }

        return d;
    }

    public static void AtualizarSensores(ArrayList<Sensor> sensors) {
        System.out.println("Atualizando lista de sensores no banco de dados...");

        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = Conexao.getConexao();
            //limpando lista para atualizar
            System.out.println("Limpando sensores para atualizar...");
            String sql = "delete from sensores where iddriver=?;";
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, sensors.get(0).getID());
            stmt.execute();
            System.out.println("Limpeza concluída!");

            int i = 0;
            if (sensors.get(i).getIdSensor() != null && sensors.get(i).getIpSensor() != null) {
                while (i < sensors.size()) {
                    System.out.println("Inserindo sensor ID: " + sensors.get(i).getIdSensor());
                    sql = "insert into sensores values (?,?,?);";
                    stmt = c.prepareStatement(sql);
                    stmt.setInt(1, sensors.get(i).getID());
                    stmt.setString(2, sensors.get(i).getIpSensor());
                    stmt.setString(3, sensors.get(i).getIdSensor());
                    stmt.execute();
                    i++;
                }
            }
            stmt.close();
            System.out.println("sensores atualizados");
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO em atualizar sensores");
        }
    }

    public static Sensor buscaDadosSensor(String idSensor) {
        Sensor s = new Sensor();
        try {
            System.out.println("Buscando dados do sensor " + idSensor + " no banco...");
            Connection c = Conexao.getConexao();//Faz conexão com banco
            PreparedStatement stmt = c.prepareStatement("select sensores.iddriver,ip,idsensor from sensores,drivers where sensores.iddriver = drivers.iddriver and sensores.idsensor=?;");
            stmt.setString(1, idSensor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                s.setID(rs.getInt("iddriver"));
                s.setIpSensor(rs.getString("ip"));
                s.setIdSensor(rs.getString("idsensor"));
            }
            System.out.println("tudo OK ...");

        } catch (Exception e) {
            System.out.println("CATCH em buscaDadosSensor(String idSensor) ...");
            e.printStackTrace();
        }
        return s;
    }

    public static void deletaDriver(int id) {
        System.out.println("Apagando dados do banco do driver ID " + id);
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            c = Conexao.getConexao();
            //limpando lista de sensores do driver
            System.out.println("Limpando sensores do driver...");
            String sql = "delete from sensores where iddriver=?;";
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Sensores Apagados!\nLimpando dados do driver...");
            sql = "delete from drivers where iddriver=?;";
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Dados do driver apagados!");

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO em Desistalar apagar dados do driver do banco");
        }
    }

    public static Object buscaDriversSensoresIdentificados() {
        ArrayList<Driver> drivers = new ArrayList<Driver>();

        try {
            System.out.println("Buscando dados de drivers que possuem sensores ativos...");
            Connection c = Conexao.getConexao();//Faz conexão com banco
            PreparedStatement stmt = c.prepareStatement("select distinct count(drivers.iddriver) as sensores ,drivers.iddriver as iddriver, nomedriver from sensores , drivers where drivers.iddriver = sensores.iddriver group by drivers.iddriver;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Driver d = new Driver();
                d.setSensoresReconhecidos(rs.getInt("sensores"));
                d.setID(rs.getInt("iddriver"));
                d.setNomeDriver(rs.getString("nomedriver"));
                drivers.add(d);
            }
            System.out.println("tudo OK ");

        } catch (Exception e) {
            System.out.println("CATCH em public Object buscaDriversSensoresIdentificados() ...");
            e.printStackTrace();
        }
        return drivers;
    }

    public Object listaDrivers() {
        ArrayList<Driver> drivers = new ArrayList<Driver>();

        try {
            System.out.println("Buscando dados de drivers no banco...");
            Connection c = Conexao.getConexao();//Faz conexão com banco
            PreparedStatement stmt = c.prepareStatement("select iddriver,nomedriver,descricao,fabricante,versao from drivers;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Driver d = new Driver();
                d.setID(rs.getInt("iddriver"));
                d.setNomeDriver(rs.getString("nomedriver"));
                d.setDescricaoDriver(rs.getString("descricao"));
                d.setFabricanteDriver(rs.getString("fabricante"));
                d.setPackDriver(rs.getString("versao"));
                drivers.add(d);
            }
            System.out.println("tudo OK ... retornando a lista com drivers cadastrados no banco");

        } catch (Exception e) {
            System.out.println("CATCH em public Object listaDrivers() ...");
            e.printStackTrace();
        }
        return drivers;
    }

    public Object listaSensores(int id) {
        ArrayList<Sensor> sensor = new ArrayList<Sensor>();

        try {
            System.out.println("Buscando dados de sensores no banco do driver ID: " + id);
            Connection c = Conexao.getConexao();//Faz conexão com banco
            PreparedStatement stmt = c.prepareStatement("select iddriver,ip,idsensor from sensores where iddriver=?;");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sensor s = new Sensor();
                s.setID(rs.getInt("iddriver"));
                s.setIpSensor(rs.getString("ip"));
                s.setIdSensor(rs.getString("idsensor"));
                sensor.add(s);
            }
            System.out.println("tudo OK ... retornando a lista com sensores cadastrados no banco");

        } catch (Exception e) {
            System.out.println("CATCH em listaSensores(int id) ...");
            e.printStackTrace();
        }
        return sensor;
    }

}
