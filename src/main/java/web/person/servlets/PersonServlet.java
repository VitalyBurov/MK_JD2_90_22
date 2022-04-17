package web.person.servlets;

import web.person.service.FactoryStorage;
import web.person.service.IFactoryStorage;
import web.person.service.IStorage;
import web.person.core.StorageType;
import web.person.core.dto.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PersonServlet", urlPatterns = "/person")
public class PersonServlet extends HttpServlet {


    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String AGE = "age";
    public static final String STORAGE_HEADER_NAME = "Storage";

    private final IFactoryStorage factoryStorage = new FactoryStorage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String lastNameValue = req.getParameter(LAST_NAME);
        String firstNameValue = req.getParameter(FIRST_NAME);
        int ageValue = Integer.parseInt(req.getParameter(AGE));

        Person person = new Person(lastNameValue, firstNameValue, ageValue);

        String storageType = req.getHeader(STORAGE_HEADER_NAME);

        try {
            StorageType choiceStorageType = StorageType.valueOfIgnoreCase(storageType);

            IStorage storageByType = factoryStorage.getStorageByType(choiceStorageType);

            storageByType.create(req, resp, person);

        } catch (IllegalArgumentException e) {
            resp.sendError(400, e.getMessage());
        } catch (IllegalStateException e) {
            resp.sendError(500, e.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String storageType = req.getHeader(STORAGE_HEADER_NAME);

        try {
            StorageType choiceStorageType = StorageType.valueOfIgnoreCase(storageType);

            IStorage storageByType = factoryStorage.getStorageByType(choiceStorageType);

            Person person = storageByType.get(req);

            if (choiceStorageType.equals(StorageType.SESSION)) {
            } else if (choiceStorageType.equals(StorageType.COOKIES)) {

            } else {
                throw new IllegalStateException("No realisation!");
            }
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("<p> lastName: " + person.getLastNAme() + "</p></br>");
            printWriter.write("<p> firstName: " + person.getFirstName() + "</p></br>");
            printWriter.write("<p> age: " + person.getAge() + "</p></br>");
        } catch (IllegalArgumentException e) {
            resp.sendError(400, e.getMessage());
        } catch (IllegalStateException e) {
            resp.sendError(500, e.getMessage());
        }
    }


}
