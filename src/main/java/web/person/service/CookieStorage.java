package web.person.service;

import web.person.core.dto.Person;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CookieStorage implements IStorage {

    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String AGE = "age";

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp, Person person) {
        saveCookie(resp, LAST_NAME, person.getLastNAme());
        saveCookie(resp, FIRST_NAME, person.getFirstName());
        saveCookie(resp, AGE, String.valueOf(person.getAge()));
    }
    private void saveCookie(HttpServletResponse resp, String key, String value) {
        Cookie cookie = new Cookie(key, URLEncoder.encode(value, StandardCharsets.UTF_8));
        cookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        resp.addCookie(cookie);
    }

    @Override
    public Person get(HttpServletRequest req) {
        String lastNameValue = getCookieValue(req, LAST_NAME);
        String firstNameValue = getCookieValue(req, FIRST_NAME);
        String ageValue = getCookieValue(req, AGE);

        return new Person(lastNameValue, firstNameValue, Integer.parseInt(ageValue));

    }
    private static String getCookieValue(HttpServletRequest req, String key) {
        String value = req.getParameter(key);

        if (value == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                value = Arrays.stream(cookies)
                        .filter(c -> key.equalsIgnoreCase(c.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        }
        if (value == null) {
            throw new IllegalArgumentException("No parameters received!!");
        }
        return value;
    }
}
