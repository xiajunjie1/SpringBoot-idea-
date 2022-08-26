package com.maker.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FirstListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("【WebServletListener】监听器运行:"+sce.getServletContext().getServerInfo());
        System.out.println("【WebServletListener】监听器运行:"+sce.getServletContext().getRealPath("/"));
        System.out.println("【WebServletListener】监听器运行:"+sce.getServletContext().getVirtualServerName());


    }


}
