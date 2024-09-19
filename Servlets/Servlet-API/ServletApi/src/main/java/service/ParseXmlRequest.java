package service;

import java.io.BufferedReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import jakarta.servlet.http.HttpServletRequest;

public class ParseXmlRequest {
	public static Document parseXmlRequest(HttpServletRequest req) {
        try {
            BufferedReader reader = req.getReader();
            StringBuilder xmlData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlData.append(line);
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new java.io.ByteArrayInputStream(xmlData.toString().getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
