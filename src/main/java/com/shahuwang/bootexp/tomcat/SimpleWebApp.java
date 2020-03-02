package com.shahuwang.bootexp.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class SimpleWebApp {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir("temp");
        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);
        String servletName = "AddSevlet";
        String urlPattern = "/add";
        AddServlet addServlet = new AddServlet();
        tomcat.addServlet(contextPath, servletName, addServlet);
        context.addServletMappingDecoded(urlPattern, servletName);
        tomcat.start();
        tomcat.getServer().await();
    }
}
