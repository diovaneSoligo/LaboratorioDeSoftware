<%-- 
    Document   : index
    Created on : 29/04/2017, 15:15:06
    Author     : Diovane
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="modulosIndex/headIndex.jsp"%>
    </head>
    <body>
        
        <!--barra superior-->
        <%@ include file="modulosIndex/barraSuperior.jsp" %>
        
        <%@include file="modulosIndex/alerts.jsp" %>
        
        <%@include file="modulosIndex/conteudo-e-login.jsp" %>
        
        <%@ include file="modulosIndex/rodapeIndex.jsp"%>
        
        <%@ include file="modulosIndex/scripts.jsp"%>
    </body>
</html>
