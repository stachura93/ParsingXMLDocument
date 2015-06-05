package pl.stachura.projekty.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AplicationContectListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
		arg0.getServletContext().setAttribute("executorPool", executorPool);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
        ThreadPoolExecutor executorPool = (ThreadPoolExecutor) arg0.getServletContext().getAttribute("executorPool");
        executorPool.shutdown();

	}

}
