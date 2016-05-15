

<%@page import="models.User"%>
<%@page import="controllers.servlet.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Preview form</title>
    </head>
    <body>
             <form action="UserRegister" method="post">
            <table border = "1" align = "center" style="border-collapse:collapse;">
            <tr>
            <td colspan="2">consumer register</td>
            </tr>
            <tr>
               <td>username：</td>
               <td><%=request.getParameter("User")%></td>
            </tr>
            
                <td>password：</td>
                <td><%=request.getParameter("password") %></td>
            
            <tr>
                     <td>first Name：</td>
                     <td><%=request.getParameter("firstName")%></td>
            </tr>
            <tr>
                     <td>last Name：</td>
                     <td><%=request.getParameter("lastName")%></td>
            </tr>
            <tr>
                     <td>nickName：</td>
                     <td><%=request.getParameter("nickName")%></td>
            </tr>
            <tr>
                     <td>address：</td>
                     <td><%=request.getParameter("address")%></td>
            </tr>
            <tr>
                     <td>email：</td>
                     <td><%=request.getParameter("Email")%></td>
            </tr>
            <tr>
                     <td>card Number：</td>
                     <td><%=request.getParameter("CardNumber")%></td>
            </tr>
            <tr>
                     <td>card Name：</td>
                     <td><%=request.getParameter("CardName")%></td>
            </tr>
            <tr>
                     <td>card Expire Date：</td>
                     <td><%=request.getParameter("cardExpireDate")%></td>
            </tr>
             <tr>
                     <td>CVC：</td>
                     <td><%=request.getParameter("CVC")%></td>
            </tr>
            </table>
            </form>
   </body>
</html>
