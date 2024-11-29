package sponsor.servlet;

import sponsor.dal.*;
import sponsor.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userread")
public class UserRead extends HttpServlet {

    protected UserDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = UserDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        String userIdStr = req.getParameter("userid");
        try {
            int userId = Integer.parseInt(userIdStr);
            Users user = usersDao.getUserByUserId(userId);
            req.setAttribute("user", user);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            messages.put("success", "User not found.");
        }
        req.getRequestDispatcher("/UserRead.jsp").forward(req, resp);
    }
}
