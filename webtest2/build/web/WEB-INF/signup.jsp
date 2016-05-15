<%-- 
    Document   : signup
    Created on : May 11, 2016, 11:47:43 PM
    Author     : williamding
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sign_up_form</title>
    </head>
    <body>
        
 1      <form action="UserRegister" method="post">
            <table border = "1" align = "center" style="border-collapse:collapse;">
            <tr>
            <td colspan="2">consumer register</td>
            </tr>
            <tr>
               <td>username：</td>
               <td><input type="text" name="userName" /></td>
            </tr>
            <tr>
                <td>password：</td>
                     <td><input type="text" name="password" /></td>
            </tr>
            <tr>
                     <td>first Name：</td>
                     <td><input type="text" name="firstName" /></td>
            </tr>
            <tr>
                     <td>last Name：</td>
                     <td><input type="text" name="lastName" /></td>
            </tr>
            <tr>
                     <td>nickName：</td>
                     <td><input type="text" name="nickName" /></td>
            </tr>
            <tr>
                     <td>address：</td>
                     <td><input type="text" name="address" /></td>
            </tr>
            <tr>
                     <td>email：</td>
                     <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                     <td>card Number：</td>
                     <td><input type="text" name="cardNumber" /></td>
            </tr>
            <tr>
                     <td>card Name：</td>
                     <td><input type="text" name="cardName" /></td>
            </tr>
            <tr>
                     <td>card Expire Date：</td>
                     <td><input type="text" name="cardExpireDate" /></td>
            </tr>
             <tr>
                     <td>CVC：</td>
                     <td><input type="text" name="cvc" /></td>
            </tr>
            <tr>
45                     <td class="tdstyle" colspan="2">
46                         <input type="submit" value="submit" />
47                     </td>
48          </tr>
        </form>
    </body>
</html>
