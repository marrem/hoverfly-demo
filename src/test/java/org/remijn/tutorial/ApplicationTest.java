package org.remijn.tutorial;

import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

public class ApplicationTest {

	@ClassRule
	public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureMode("simulation.json");

	@Test
	public void testAppWithUrl() throws IOException {
		Application app = new Application();
		app.getUrlWithNetUrl("http://www.google.com");
	}

	@Test
	public void testAppWithHttpComponents() throws IOException {
		Application app = new Application();
		app.getUrlWithHttpComponents("http://www.google.com");
	}


}
