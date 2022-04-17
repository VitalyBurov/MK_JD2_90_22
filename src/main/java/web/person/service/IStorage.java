package web.person.service;

import web.person.core.dto.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IStorage {
    void create(HttpServletRequest req, HttpServletResponse resp, Person person);
    Person get(HttpServletRequest req);
}
