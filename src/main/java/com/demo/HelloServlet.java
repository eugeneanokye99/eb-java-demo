package com.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

    private static final String VERSION = "v1.0";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String env = System.getenv("ENVIRONMENT_NAME");
        if (env == null) env = "local";
        resp.setContentType("text/html");
        resp.getWriter().println(
            "<html><body>" +
            "<h1>App deployed successfully</h1>" +
            "<p>Version: " + VERSION + "</p>" +
            "<p>Environment: " + env + "</p>" +
            "</body></html>"
        );
    }
}
