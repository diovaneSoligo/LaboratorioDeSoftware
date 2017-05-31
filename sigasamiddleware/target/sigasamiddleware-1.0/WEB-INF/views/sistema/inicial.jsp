<%-- 
    Document   : inicial
    Created on : 19/05/2017, 21:43:32
    Author     : Diovane
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="modulosPrincipais/head.jsp"%>
    </head>
    <body>
        
        <%@include file="modulosPrincipais/nome_pagina.jsp" %>

        <%@include file="modulosPrincipais/munu_lateral.jsp" %>   

        <%@include file="moduloPaginaIncial/conteudoIncial.jsp" %>
        
        <%@include file="modulosPrincipais/ola_usuario.jsp" %>

        <%@include file="modulosPrincipais/fecha_conteudo-Scripts.jsp" %>
    </body>
</html>
