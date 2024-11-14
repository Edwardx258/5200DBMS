package sponsor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
 private static final long serialVersionUID = 1L;

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String userId = request.getParameter("userId");

     try {
         UsersDAO usersDAO = new UsersDAO();
         boolean isDeleted = usersDAO.deleteUser(Integer.parseInt(userId));

         if (isDeleted) {
             request.setAttribute("message", "User deleted successfully.");
         } else {
             request.setAttribute("error", "Failed to delete user.");
         }
         request.getRequestDispatcher("deleteUser.jsp").forward(request, response);
     } catch (Exception e) {
         e.printStackTrace();
         request.setAttribute("error", "An error occurred: " + e.getMessage());
         request.getRequestDispatcher("deleteUser.jsp").forward(request, response);
     }
 }
}
