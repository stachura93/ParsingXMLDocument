package pl.stachura.projekty.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

@WebListener
public class AplicationAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent arg0) throws IOException {
		System.out.println("AppAsyncListener onComplete");
	}

	@Override
	public void onError(AsyncEvent arg0) throws IOException {
		System.out.println("AppAsyncListener onError");
	}

	@Override
	public void onStartAsync(AsyncEvent arg0) throws IOException {
		System.out.println("AppAsyncListener onStartAsync");

	}

	@Override
	public void onTimeout(AsyncEvent arg0) throws IOException {
		System.out.println("AppAsyncListener onTimeout");
		ServletResponse response = arg0.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.write("TimeOut Error in Processing");
	}
}
