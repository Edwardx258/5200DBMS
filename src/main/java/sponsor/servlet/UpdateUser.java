package sponsor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sponsor.dal.UsersDAO;
import sponsor.model.User;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");

        try {
            UsersDAO usersDAO = new UsersDAO();
            int parsedUserId = Integer.parseInt(userId);


            User existingUser = usersDAO.getUserById(parsedUserId);
            if (existingUser != null) {

                existingUser.setUsername(userName);
                existingUser.setEmail(userEmail);
                existingUser.setPasswordHash(userPassword);


                usersDAO.updateUser(existingUser);


                request.setAttribute("message", "User updated successfully.");
            } else {

                request.setAttribute("error", "User not found.");
            }
        } catch (NumberFormatException e) {

            e.printStackTrace();
            request.setAttribute("error", "Invalid User ID format.");
        } catch (Exception e) {

            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
        }


        request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
    }
}

