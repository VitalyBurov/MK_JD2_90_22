package web.person.service;

import web.person.core.dto.Person;
import web.person.service.IStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionStorage implements IStorage {

    public static final String SESSION_ATTRIBUTE_NAME = "person";

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp, Person person) {
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_ATTRIBUTE_NAME, person);
    }

    @Override
    public Person get(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return  (Person) session.getAttribute(SESSION_ATTRIBUTE_NAME);
    }
}
