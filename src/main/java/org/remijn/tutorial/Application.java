package org.remijn.tutorial;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) throws IOException {
		Application application = new Application();

		// Use URL
		for(String line : application.getUrlWithNetUrl("http://www.google.com")) {
			System.out.println(line);
		}

		// Use HttpComponents
		System.out.println(application.getUrlWithHttpComponents("http://www.google.com"));
	}

	public String getUrlWithHttpComponents(String urlString) throws IOException {
		List<String> output = new ArrayList<>();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(urlString);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			return EntityUtils.toString(entity);
		} finally {
			response.close();
		}


	}

	public List<String> getUrlWithNetUrl(String urlString) throws IOException {
		List<String> output = new ArrayList<>();
		final URL url = new URL(urlString);

		final URLConnection urlConnection = url.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			output.add(inputLine);
		in.close();
		return output;
	}

}
