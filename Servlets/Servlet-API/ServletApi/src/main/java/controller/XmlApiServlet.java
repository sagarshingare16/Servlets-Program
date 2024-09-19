package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.ParseXmlRequest;
import service.RegisterUser;

import java.io.IOException;

import org.w3c.dom.Document;

@WebServlet("/api/xml")
public class XmlApiServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Document xmlDoc = ParseXmlRequest.parseXmlRequest(req);
		User user = new User();

        if (xmlDoc != null) {
            String userId = xmlDoc.getElementsByTagName("userId").item(0).getTextContent();
            String userName = xmlDoc.getElementsByTagName("userName").item(0).getTextContent();
            String userEmail = xmlDoc.getElementsByTagName("userEmail").item(0).getTextContent();
            String userPassword = xmlDoc.getElementsByTagName("userEmail").item(0).getTextContent();
            
            user.setUserId(userId);
            user.setUserName(userName);
            user.setUserEmail(userEmail);
            user.setUserPassword(userPassword);

            boolean insertSuccess = RegisterUser.registerUser(user);

            resp.setContentType("application/xml");
            resp.setCharacterEncoding("UTF-8");

            if (insertSuccess) {
            	resp.setStatus(HttpServletResponse.SC_OK);
                String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                                     "<response>" +
                                     "<message>Data inserted successfully</message>" +
                                     "</response>";
                resp.getWriter().write(xmlResponse);
            } else {
            	resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                                     "<response>" +
                                     "<message>Data insertion failed</message>" +
                                     "</response>";
                resp.getWriter().write(xmlResponse);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String xmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                                 "<response>" +
                                 "<message>Invalid XML data</message>" +
                                 "</response>";
            resp.getWriter().write(xmlResponse);
        }
	
	}

}
