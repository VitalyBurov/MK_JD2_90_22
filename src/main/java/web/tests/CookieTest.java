package web.tests;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "Cookie", urlPatterns = "/cookie")
public class CookieTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String firstNameValue = getValue ("firstName",req);
        saveCookies(resp, "firstName", firstNameValue);
        String lastNameValue = getValue("lastName",req);
        saveCookies(resp,"lastName",lastNameValue);

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<p>Hello, " + firstNameValue + " " + lastNameValue + "!</p>");


    }

    public static String getValue(String key,HttpServletRequest req){
        String value = req.getParameter(key);

        if(value == null){
            Cookie[] cookies = req.getCookies();
            if(cookies != null){
                value = Arrays.stream(cookies)
                        .filter(c->key.equalsIgnoreCase(c.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);

            }
            /*     if(cookies !=null) {
                for(Cookie c: cookies) {
                    if(value.equals(c.getName())) {
                        cookie = c;
                        break;
                    }
                }*/
        }
        if (value == null){
            throw new IllegalArgumentException("No parameters received!!");
        }
        return value;
    }

    public static void saveCookies(HttpServletResponse resp, String key,String value){
        Cookie myCookies = new Cookie(key, URLEncoder.encode(value, StandardCharsets.UTF_8));
        myCookies.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        resp.addCookie(myCookies);
    }


}
