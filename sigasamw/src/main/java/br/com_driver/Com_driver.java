/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com_driver;

import br.dao.DaoDriver;
import br.modelo.Driver;
import br.modelo.Sensor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Diovane
 */
public class Com_driver {

    public static boolean verificaDiretorio(MultipartFile[] arquivo) {
        boolean retorno = false;
        System.out.println("Verificando existencia do diretório...");
        File URL_JAR = new File("C:/Users/Diovane/Documents/NetBeansProjects/sigasamw/src/main/java/run_drivers");
        URL_JAR = new File(URL_JAR + "/" + arquivo[0].getOriginalFilename());
        if (!URL_JAR.exists()) {
            System.out.println("OK");
            retorno = true;
        } else {
            System.out.println("DRIVER EXISTENTE!");
        }
        return retorno;
    }

    /**
     * *****************************************************************************
     * @param arquivo
     * @return
     */
    public static File gravaJar(MultipartFile[] arquivo) {
        File URL_JAR;

        //apresenta nome do arquivo enviado
        System.out.println("Arquivo: " + arquivo[0].getOriginalFilename());

        //acessa ou cria diretório DRIVER se nao existir
        URL_JAR = new File("C:/Users/Diovane/Documents/NetBeansProjects/sigasamw/src/main/java/run_drivers");
        if (!URL_JAR.exists()) {
            URL_JAR.mkdir();
            System.out.println("Deretório RUN_DRIVER criado");
        } else {
            System.out.println("Diretório RUN_DRIVER já existente");
        }

        try {
            //grava jar no diretório temp
            System.out.println("Gravando .JAR ...");
            URL_JAR = new File(URL_JAR + "/" + arquivo[0].getOriginalFilename());
            byte[] arquivo_bytes = arquivo[0].getBytes();

            try (OutputStream fout = new FileOutputStream(URL_JAR)) {
                fout.write(arquivo_bytes);
            }
            System.out.println("Arquivo < " + arquivo[0].getOriginalFilename() + " > gravado");
            System.out.println("caminho do .JAR : < " + URL_JAR.getAbsolutePath() + " >");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return URL_JAR;
    }

    /**
     * *****************************************************************************
     * @param caminho
     * @throws java.io.IOException
     */
    public static void adicionarAoClasspath(File caminho) throws IOException {
        System.out.println("Adicionando o JAR ao Classpath...\nCamimho : " + caminho + " >\ncaminho.toURI().toURL() : " + caminho.toURI().toURL());
        adicionarAoClasspath(caminho.toURI().toURL());
    }

    public static void adicionarAoClasspath(URL url) throws IOException {
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();

        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(sysloader, url);
            System.out.println("JAR adicionado ao Classpath OK");

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException t) {
            throw new IOException("Error, could not add URL to system classloader", t);
        }
    }

    /**
     * *****************************************************************************
     * @param jarName
     */
    //Método que lista os .class dentro do jar criado
    public static void getClasseNamesInPackage(File jarName) {

        ArrayList listaDeClasses = new ArrayList();
        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
            JarEntry jarEntry;
            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                listaDeClasses.add(jarEntry.getName().replaceAll("/", "\\."));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("\nListando as classes presentes do novo .jar");
        for (int i = 0; i < listaDeClasses.size(); i++) {
            System.out.println(listaDeClasses.get(i));
        }
        System.out.println("Classes Listadas...\n");

    }

    /**
     * *****************
     * @param CaminhoJAR
     * @param nomeJAR
     * @return
     */
    public static Driver capturaDriver(File CaminhoJAR, String nomeJAR) {
        Driver armazena = new Driver();
        try {
            armazena.setURIDriver(CaminhoJAR);
            System.out.println("URIDRIVERNOVO -> " + armazena.getURIDriver());
            java.net.URL location = new URL("jar:file:////" + CaminhoJAR + "!/");
            System.out.println("Location: " + location.getFile());
            URL[] allLocations = new URL[1];
            allLocations[0] = location;
            ClassLoader loader = URLClassLoader.newInstance(allLocations);

            //pegar nome do pacote e da classe
            armazena.setNomeJarDriver(nomeJAR);
            System.out.println("Nome do JAR : < " + armazena.getNomeJarDriver() + " >");
            String array[] = new String[2];
            array = nomeJAR.split(".jar");
            armazena.setPacoteDriver(array[0].toLowerCase());
            armazena.setClasseDriver(array[0]);
            System.out.println("Pacote : " + armazena.getPacoteDriver() + "\nClasse : " + armazena.getClasseDriver());

            try {
                System.out.println("Vai carregar a classe '" + armazena.getPacoteDriver() + "." + armazena.getClasseDriver() + "'");
                Class userList = loader.loadClass(armazena.getPacoteDriver() + "." + armazena.getClasseDriver());
                System.out.println("Class userList = loader.loadClass(" + armazena.getPacoteDriver() + "." + armazena.getClasseDriver() + "); CAREGADA , nome: " + userList.getName());

                System.out.println("LISTANDO METODOS DA CLASSE " + userList.getName() + "...\n");
                Method m[] = userList.getDeclaredMethods();
                for (Method m1 : m) {
                    System.out.println(m1.toString());
                }
                System.out.println("\nMETODOS DA CLASSE " + armazena.getPacoteDriver() + "." + armazena.getClasseDriver() + " LISTADAS\nEXECUTANDO METODOS...");

                Object Instance = userList.newInstance();

                Method Method = userList.getMethod("getNome");
                armazena.setNomeDriver((String) Method.invoke(Instance));
                Method = userList.getMethod("getDescricao");
                armazena.setDescricaoDriver((String) Method.invoke(Instance));
                Method = userList.getMethod("getFabricante");
                armazena.setFabricanteDriver((String) Method.invoke(Instance));
                Method = userList.getMethod("getVersao");
                armazena.setPackDriver((String) Method.invoke(Instance));

                /*
                Method = userList.getMethod("getDado", String.class);
                String dados = (String) Method.invoke(Instance, "192.0.0.1");

                Method = userList.getMethod("setAtuar", int.class, String.class);
                Method.invoke(Instance, 1, "");

                Method = userList.getMethod("getSensoresReconhecidos");
                List<String> sensores = (ArrayList<String>) Method.invoke(Instance);
                 */
                System.out.println(" .... RETORNO DOS METODOS DO NOVO DRIVER: ...");

                //vai pro banco + caminho do jar pra posterior carregamento
                System.out.println("Nome Driver: " + armazena.getNomeDriver() + "\n");
                System.out.println("Descrição: " + armazena.getDescricaoDriver() + "\n");
                System.out.println("Fabricante: " + armazena.getFabricanteDriver() + "\n");
                System.out.println("Versão: " + armazena.getPackDriver() + "\n");

                System.out.println("URI Driver: " + armazena.getURIDriver());
                System.out.println("Nome do JAR: " + armazena.getNomeJarDriver());
                System.out.println("Nome do pacote: " + armazena.getPacoteDriver());
                System.out.println("Nome da Classe: " + armazena.getClasseDriver());

            } catch (Exception e) {
                armazena = null;
                e.printStackTrace();
            }

        } catch (Exception e) {
            armazena = null;
            e.printStackTrace();
        }
        return armazena;
    }

    public static int buscaAtualizaSensores(int id) throws MalformedURLException {
        int retorno = 0;

        //busca os dados para invocar o metodo do driver referente aos sensores
        Driver dados = DaoDriver.buscaDadosAtualizarSensores(id);

        System.out.println("URI DRIVER -> " + dados.getURIDriver());
        java.net.URL location = new URL("jar:file:////" + dados.getURIDriver() + "!/");
        System.out.println("Location: " + location.getFile());
        URL[] allLocations = new URL[1];
        allLocations[0] = location;
        ClassLoader loader = URLClassLoader.newInstance(allLocations);

        System.out.println("Pacote : " + dados.getPacoteDriver() + "\nClasse : " + dados.getClasseDriver());

        try {
            System.out.println("Vai carregar a classe '" + dados.getPacoteDriver() + "." + dados.getClasseDriver() + "'");
            Class userList = loader.loadClass(dados.getPacoteDriver() + "." + dados.getClasseDriver());
            System.out.println("Class userList = loader.loadClass(" + dados.getPacoteDriver() + "." + dados.getClasseDriver() + "); CAREGADA , nome: " + userList.getName());

            Object Instance = userList.newInstance();

            Method Method = userList.getMethod("getSensoresReconhecidos");
            List<String> sensores = (ArrayList<String>) Method.invoke(Instance);

            ArrayList<Sensor> sensors = new ArrayList<Sensor>();
            for (int i = 0; i < sensores.size(); i++) {
                retorno++;
                Sensor s = new Sensor();
                String array[] = new String[2];
                array = sensores.get(i).split(";");
                s.setID(id);
                s.setIdSensor(array[0]);
                s.setIpSensor(array[1]);
                sensors.add(s);
            }

            try {
                //caso nao tenha sensores deve apagar todos q tem no banco
                if (retorno == 0) {
                    System.out.println("Nenhum sensor encontrado");
                    Sensor s = new Sensor();
                    s.setID(id);//manda só o id do driver
                    s.setIdSensor(null);
                    s.setIpSensor(null);
                    sensors.add(s);
                }
                DaoDriver.AtualizarSensores(sensors);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static Sensor invocarSensor(String idSensor) throws MalformedURLException {

        //buscar dados de id apartir do id do sensor
        Sensor s = DaoDriver.buscaDadosSensor(idSensor);

        //busca os dados para invocar o metodo do driver referente aos sensores
        Driver dados = DaoDriver.buscaDadosAtualizarSensores(s.getID());

        System.out.println("URI DRIVER -> " + dados.getURIDriver());
        java.net.URL location = new URL("jar:file:////" + dados.getURIDriver() + "!/");
        System.out.println("Location: " + location.getFile());
        URL[] allLocations = new URL[1];
        allLocations[0] = location;
        ClassLoader loader = URLClassLoader.newInstance(allLocations);

        System.out.println("Pacote : " + dados.getPacoteDriver() + "\nClasse : " + dados.getClasseDriver());

        try {
            System.out.println("Vai carregar a classe '" + dados.getPacoteDriver() + "." + dados.getClasseDriver() + "'");
            Class userList = loader.loadClass(dados.getPacoteDriver() + "." + dados.getClasseDriver());
            System.out.println("Class userList = loader.loadClass(" + dados.getPacoteDriver() + "." + dados.getClasseDriver() + "); CAREGADA , nome: " + userList.getName());

            Object Instance = userList.newInstance();

            Method Method = userList.getMethod("getDado", String.class);
            s.setRetornoDados((String) Method.invoke(Instance, s.getIpSensor()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean deletaDriver(int id) throws InterruptedException {
        boolean retorno = false;

        Driver dados = DaoDriver.buscaDadosAtualizarSensores(id);

        System.out.println("verificando diretório do driver: "+dados.getURIDriver());
        if (dados.getURIDriver().isFile()) {
            System.out.println("Deletando diretorio jar...");
            dados.getURIDriver().delete();
        }
        Thread.sleep(1000);//aguarda tempo de exclusão do arquivo
        if (dados.getURIDriver().isFile()) {
            System.out.println("IIIIIIIIII  Jar não deletado!! IIIIIIIIIII");
            return retorno;//caso nao foi deletado retorna informando usuario
        } else {
            System.out.println("Arquivo jar deletado, apagando dados do banco...");
            DaoDriver.deletaDriver(id);//apaga os sensores do driver e os dados do driver no banco
            retorno = true;
        }
        return retorno;
    }
}
