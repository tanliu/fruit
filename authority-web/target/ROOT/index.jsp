<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
     <title>Character encoding test page</title>
   </head>
   <body>
     <h1>authorityOK</h1>
     <p>Data posted to this form was:
     <%
       out.print(request.getParameter("mydata"));
     %>

     </p>
     <form method="POST" action="index.jsp">
       <input type="text" name="mydata">
       <input type="submit" value="Submit" />
       <input type="reset" value="Reset" />
     </form>
   </body>
</html>
