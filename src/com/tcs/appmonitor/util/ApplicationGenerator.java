package com.tcs.appmonitor.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationGenerator implements ServletContextListener {
	
	private ScheduledExecutorService  scheduler ;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		scheduler.shutdownNow();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable command = new ApplicationSchedular(event.getServletContext());
        
        long initialDelay = 1;
        TimeUnit unit = TimeUnit.MINUTES;
        // period the period between successive executions              
        scheduler.scheduleAtFixedRate(command, initialDelay, Constants.SCHEDULAR_INTERVAL, unit);
	}

}
