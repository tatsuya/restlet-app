package com.tatsuyaoiw.restlet.cli;

import com.tatsuyaoiw.restlet.App;
import org.restlet.Component;

public class Main {

	public static void main(String[] args) throws Exception {
		Component component = App.createApp();
		component.start();
	}

}
