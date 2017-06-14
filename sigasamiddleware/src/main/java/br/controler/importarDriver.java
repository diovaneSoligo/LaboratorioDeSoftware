/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controler;

import br.dao.DaoSistema;
import br.modelo.ContextoDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
/**
 *
 * @author Diovane
 */
@Controller
public class importarDriver {

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

            //apresenta nome do arquivo enviado
            System.out.println("Arquivo: " + arquivo[0].getOriginalFilename());

            //acessa pacote ou criar diretório DRIVER se nao existir
            File pacote = new File("C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver");
            if (!pacote.exists()) {
                pacote.mkdir();
                System.out.println("Deretório DRIVER criado");
            } else {
                System.out.println("Diretório DRIVER já existente");
            }

            pacote = new File("C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver\\temp");
            if (!pacote.exists()) {
                pacote.mkdir();
                System.out.println("diretório TEMP criado");
            } else {
                System.out.println("Diretório TEMP já existe\napagando conteudo do diretório...");
                if (pacote.isDirectory()) {
                    File[] sun = pacote.listFiles();
                    for (File toDelete : sun) {
                        toDelete.delete();
                    }
                }
            }
            try {
                //grava zip no diretório temporario
                System.out.println("Gravando .ZIP ...");
                File zip = new File("C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver\\temp\\" + arquivo[0].getOriginalFilename());
                byte[] arquivo_bytes = arquivo[0].getBytes();

                OutputStream fout = new FileOutputStream(zip);
                fout.write(arquivo_bytes);
                fout.close();
                System.out.println("Arquivo < " + arquivo[0].getOriginalFilename() + " > gravado");

                System.out.println("Iniciano descompressao do arquivo < " + arquivo[0].getOriginalFilename() + " >");

                System.out.println("cria  buffer");
                byte[] buffer = new byte[1024];

                //cria input do arquivo.zip
                ZipInputStream zipstream
                        = new ZipInputStream(new FileInputStream(zip.getAbsolutePath()));

                // Pega a proxima entrada do arquivo
                ZipEntry zentry = zipstream.getNextEntry();

                // Enquanto existir entradas no ZIP
                while (zentry != null) {
                    // Pega o nome da entrada
                    String entryName = zentry.getName();

                    System.out.println("zentry.getNmae: arquivo >> " + zentry.getName());
                    // Cria o output do arquivo , Sera extraido onde esta rodando a classe
                    //ageitar aqui
                    FileOutputStream outstream = new FileOutputStream(pacote + "\\" + entryName);
                    int n;
                    // Escreve no arquivo
                    while ((n = zipstream.read(buffer)) > -1) {
                        outstream.write(buffer, 0, n);
                    }

                    // Fecha arquivo
                    outstream.close();

                    // Fecha entrada e tenta pegar a proxima
                    zipstream.closeEntry();
                    zentry = zipstream.getNextEntry();
                }
                // Fecha o zip como um todo
                zipstream.close();

                zip.delete();//deleta o arquivo zip importado deixando só as classes e o XMl

                //ler xml para alterar o pacote de temp para o correto informado
                ContextoDriver info = new ContextoDriver();
/*
                //**************
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //contrutor de documento
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    //fazer o passe do documento CONTEXTO.XML para  doc
                    Document doc = builder.parse("C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver\\temp\\contexto.xml");

                    //recebe o documento do passe e pega o elemento pela tag
                    NodeList contexto_driver = doc.getElementsByTagName("contexto_driver");

                    //pra cada elemento encontrado é feito um nó
                    Node agora = contexto_driver.item(0);

                    //verifica se o nó agora é do tipo elemento, se for do tipo element é pq tem coisa dentro dele
                    if (agora.getNodeType() == Node.ELEMENT_NODE) {
                        //faz a conversão para elemento
                        Element Agora = (Element) agora;//converte o nó agora para um elemento agora

                        String AGORA = Agora.getAttribute("agora");

                        //Pega todos os nós filhos do elemento agora e coloca numa lista
                        NodeList agoraFilhos = Agora.getChildNodes();

                        //pega o tamanho da lista agoraFilhos
                        int taf = agoraFilhos.getLength();

                        for (int j = 0; j < taf; j++) {//percorre os filhos de agora
                            Node noFilho = agoraFilhos.item(j);

                            //verifica se o noFilho é do tipo Element
                            if (noFilho.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementoFilho = (Element) noFilho;

                                switch (elementoFilho.getTagName()) {

                                    case "data_hora":
                                        T.setData_hora(elementoFilho.getTextContent());
                                        break;

                                    case "descricao":
                                        T.setDescricao(elementoFilho.getTextContent());
                                        break;

                                    case "temperatura":
                                        T.setTemperatura(elementoFilho.getTextContent());
                                        break;

                                    case "umidade":
                                        T.setUmidade(elementoFilho.getTextContent());
                                        break;

                                    case "visibilidade":
                                        T.setVisibilidade(elementoFilho.getTextContent());
                                        break;

                                    case "vento_velocidade":
                                        T.setVento_velocidade(elementoFilho.getTextContent());
                                        break;

                                    case "vento_direcao":
                                        T.setVento_direcao(elementoFilho.getTextContent());
                                        break;

                                    case "pressao":
                                        T.setPressao(elementoFilho.getTextContent());
                                        break;

                                    case "pressao_status":
                                        T.setPressao_status(elementoFilho.getTextContent());
                                        break;

                                    case "nascer_do_sol":
                                        T.setNascer_do_sol(elementoFilho.getTextContent());
                                        break;

                                    case "por_do_sol":
                                        T.setPor_do_sol(elementoFilho.getTextContent());
                                        break;

                                    case "imagem":
                                        T.setImagem(elementoFilho.getTextContent());
                                        break;

                                }
                            }
                        }
                    }

                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
                }
                */

                System.out.println("classes inseridas com sucesso");
                mv.addObject("notificacaoOK", "DRIVER < " + arquivo[0].getOriginalFilename() + " > INSTALADO COM SUCESSO");
            } catch (Exception e) {
                System.out.println("You failed to upload " + arquivo[0].getOriginalFilename() + " => " + e.getMessage());
                mv.addObject("notificacaoERRO", "FALHA NA INSTALAÇÃO DO DRIVER < " + arquivo[0].getOriginalFilename() + " > , < " + e.getMessage() + " > ERRO INTERNO");
                //deleta o conteudo do diretório TEMP se der erro na instalação
                if (pacote.isDirectory()) {
                    File[] sun = pacote.listFiles();
                    for (File toDelete : sun) {
                        toDelete.delete();
                    }
                }
                pacote.delete();
            }

        } else {
            System.out.println("You failed to upload " + arquivo[0].getOriginalFilename() + " because the file was empty.");
            mv.addObject("notificacaoERRO", "FALHA NA INSTALAÇÃO DO DRIVER, O MESMO NÃO FOI SELECIONADO");
        }

        System.out.println("Voltando para página...");
        //gravar .zip
        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers");
        return mv;
    }

}
