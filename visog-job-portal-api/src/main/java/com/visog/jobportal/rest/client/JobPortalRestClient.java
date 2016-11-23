package com.visog.jobportal.rest.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.apache.log4j.Logger;

import com.visog.jobportal.exceptions.JobPortalException;

public class JobPortalRestClient {

	private static final Logger logger = Logger
			.getLogger(JobPortalRestClient.class);

	private static Random random;

	static {
		random = new Random();
	}

	public static synchronized Long getRandomNumber() {
		return random.nextLong();
	}

	/**
	 * Send PUT type of request using HTTPURLConnection
	 * 
	 * @param urlStr
	 * @param contentType
	 * @param jsonStr
	 * @return
	 */
	public static String sendPutRequest(String urlStr, String contentType,
			String jsonStr) {
		return sendRequest(urlStr, contentType, jsonStr, "PUT");
	}

	/**
	 * Send POST type of request using HTTPURLConnection
	 * 
	 * @param urlStr
	 * @param contentType
	 * @param jsonStr
	 * @return
	 */
	public static String sendPostRequest(String urlStr, String contentType,
			String jsonStr) {
		return sendRequest(urlStr, contentType, jsonStr, "POST");
	}

	/**
	 * Send different type of request using HTTPURLConnection
	 * 
	 * @param urlStr
	 * @param contentType
	 * @param jsonStr
	 * @param methodType
	 * @return
	 */
	public static String sendRequest(String urlStr, String contentType,
			String jsonStr, String methodType) {

		logger.info("rest req method type : " + methodType + "; url : " + urlStr);
		logger.info("rest req body : " + jsonStr);

		try {

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(methodType);
			conn.setRequestProperty("Content-Type", contentType);

			String input = jsonStr;

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			StringBuffer sb = new StringBuffer();
			logger.info("rest buffer response  : ");
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			output = sb.toString();
			logger.info("methodType>>>"+methodType+"; urlStr >>> "+urlStr + " : rest response  : " + output);

			conn.disconnect();

			return output;

		} catch (Exception e) {

			throw new JobPortalException(e);

		}

	}

	/**
	 * Send GET type of request using HTTPURLConnection
	 * 
	 * @param urlStr
	 * @return
	 * @throws Exception
	 */
	public static String sendGetRequest(String urlStr) {
		return sendRequest(urlStr, "GET");
	}

	public static String sendDeleteRequest(String urlStr) {
		return sendRequest(urlStr, "DELETE");
	}

	public static String sendRequest(String urlStr, String methodType) {
		try {
			logger.info("rest request method type : " + methodType + "; urlStr : " + urlStr);
			
			URL obj = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod(methodType);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			logger.info(urlStr + " : rest response : " + response);
			
			return response.toString();
		} catch (Exception e) {
			throw new JobPortalException(e);
		}
	}


	public static void main(String[] args) {
	}

}
