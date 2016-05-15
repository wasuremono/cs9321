<%-- 
    Document   : UserUpdate
    Created on : May 15, 2016, 2:06:33 PM
    Author     : williamding
--%>
<%@page import="models.User"%>
<%@page import="controllers.servlet.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update form </title>
    </head>
     <body>
             <form name= "updateform" action="UserUpdate" method="post">
            <table border = "1" align = "center" style="border-collapse:collapse;">
            <tr>
            <td colspan="2">consumer update</td>
            </tr>
            <tr>
               <td>username：</td>
               <td><input type="text" name="userName" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                <td>password：</td>
                <td><input type="text" name="password" value='<%=user.getUserName() %>'/></td>
            
            <tr>
                     <td>first Name：</td>
                     <td><input type="text" name="firstName" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                     <td>last Name：</td>
                     <td><input type="text" name="lastName" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                     <td>nickName：</td>
                     <td><input type="text" name="nickName" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                     <td>address：</td>
                     <td><input type="text" name="address" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                     <td>email：</td>
                     <td><input type="text" name="Email" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                     <td>card Number：</td>
                     <td><input type="text" name="cardNumber" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                     <td>card Name：</td>
                     <td><input type="text" name="cardName" value='<%=user.getUserName() %>'/></td>
            </tr>
            <tr>
                     <td>card Expire Date：</td>
                     <td><input type="text" name="cardExpireDate" value='<%=user.getUserName() %>'/></td>
            </tr>
             <tr>
                     <td>CVC：</td>
                     <td><input type="text" name="CVC" value='<%=user.getUserName() %>'/></td>
            </tr>
            </table>
            </form>
   </body>
</html>
