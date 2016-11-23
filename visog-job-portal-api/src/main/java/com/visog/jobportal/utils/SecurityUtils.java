package com.visog.jobportal.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SecurityUtils {

	private static final Logger logger = Logger.getLogger(SecurityUtils.class);

	public static String encode(String key, String data) {
		return Jwts.builder()
				  .setSubject(data)
				  .signWith(SignatureAlgorithm.HS256, key)
				  .compact();
		
	}

	public static String decode(String key, String data) {
		try {

		   Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(data);

			return jws.getBody().getSubject();

		} catch (SignatureException e) {

			logger.error("Error in decode JWT", e);
		    return null;
		}
		
	}

	public static JSONObject decodeToJSON(String key, String data) {
//		String jwtKey = "3Gi2fO8IYDSzm8_XApeblINMygU";
//		String jwtPayload = "eyJhbGciOiJIUzI1NiJ9.eyJhY3Rpdml0eSI6ImxvZ2luIn0.cVq2Oxk0VZocLkDjJFjicrWgzo-1gOPoee-nbe1OOpo";
		
		try {
		    final JWTVerifier verifier = new JWTVerifier(key);
		    final Map<String,Object> claims = verifier.verify(data);
		    
		    return new JSONObject(claims);
		    
		} catch (JWTVerifyException | InvalidKeyException | NoSuchAlgorithmException | 
				IllegalStateException | java.security.SignatureException | IOException e) {
			logger.error("decodeToJSON Error key : " + key + ";; data : " + data, e);
			return null;
		}
	}

	public static void main1(String[] args) {
		String key = "3Gi2fO8IYDSzm8_XApeblINMygU";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("roleId", 2);
		JSONArray accessLinks = new JSONArray();
		accessLinks.put("myServices");
		accessLinks.put("changePassword");
		jsonObject.put("accessLinks", accessLinks);
		
		JSONArray roleArr = new JSONArray();
		roleArr.put(jsonObject);
		
		String cipherText = encodeJSON(key, jsonObject);
		System.out.println("cipherText :"+cipherText);
		JSONObject decodedText = decodeToJSON(key, cipherText);
		System.out.println("decodedText : "+decodedText);

		System.out.println(decodeToJSON("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzY4In0.aHwqlJsazQdRhADeMSJu3EFRhIcnoAPHJK-L6LKe5vY", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJzdGF0dXNcIjpcInN1Y2Nlc3NcIixcInN0YXR1c0NvZGVcIjoyMDAsXCJtZXNzYWdlXCI6XCJVc2VyIGxvZ2dlZCBpbiBzdWNjZXNzZnVsbHlcIixcImRhdGFcIjp7XCJ1c2VySWRcIjo2LFwidXNlck5hbWVcIjpcInNudW5lQHZzb2Z0Y29ycC5jb21cIixcImZpcnN0TmFtZVwiOlwiU3Jpbml2YXNcIixcImxhc3ROYW1lXCI6XCJOdW5lXCIsXCJtb2JpbGVOb1wiOlwiMTIzNDU2Nzg5MFwiLFwiZW1haWxJZFwiOlwic251bmVAdnNvZnRjb3JwLmNvbVwiLFwibGFzdExvZ2luRGF0ZVwiOlwiMTAvMDcvMjAxNiAxOTozNFwiLFwiaXNGaXJzdExvZ2luXCI6ZmFsc2UsXCJpc1B3ZENoYW5nZVJlcXVpcmVkXCI6ZmFsc2UsXCJpc01GQVJlcVwiOnRydWUsXCJkZWZhdWx0UGFnZVwiOntcImlkXCI6MSxcIm5hbWVcIjpcImRhc2hib2FyZFwiLFwiZGlzcGxheU5hbWVcIjpcIkRhc2ggQm9hcmRcIixcImRlc2NyaXB0aW9uXCI6XCJEYXNoIEJvYXJkXCJ9LFwicGFzc3dvcmRFeHBpcmVkSW5EYXlzXCI6ODgsXCJsb2dpblNlc3Npb25JZFwiOlwiZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKemRXSWlPaUl4TXpZNUluMC55UnAzVFY5aTBvSGJ2VjR3WE8zYW9OdWJ2akRGcEZWOWVRT2dTRGtYN09nXCIsXCJzZXNzaW9uRXhwaXJ5VGltZVwiOjM2MDAsXCJzZXNzaW9uRXhwaXJ5V2FybmluZ1wiOjMwLFwiaXNQYXNzd29yZEV4cGlyZXNcIjpmYWxzZSxcImlzU2VjdXJpdHlRdWVzdGlvbnNSZXNldFwiOmZhbHNlLFwiYWNjZXNzTGlua3NcIjpbe1wiaWRcIjoyLFwibmFtZVwiOlwibXlTZXJ2aWNlc1wiLFwiZGlzcGxheU5hbWVcIjpcIk15IFNlcnZpY2VzXCIsXCJkZXNjcmlwdGlvblwiOlwiTXkgU2VydmljZXNcIixcIm90cFwiOmZhbHNlLFwib3RwTWVkaWFcIjpcIkVNQUlMXCJ9LHtcImlkXCI6MyxcIm5hbWVcIjpcImNoYW5nZVNlY3VyaXR5UXVlc3Rpb25cIixcImRpc3BsYXlOYW1lXCI6XCJjaGFuZ2UgU2VjdXJpdHkgUXVlc3Rpb25cIixcImRlc2NyaXB0aW9uXCI6XCJjaGFuZ2VTZWN1cml0eVF1ZXN0aW9udHJ1ZVwiLFwib3RwXCI6ZmFsc2UsXCJvdHBNZWRpYVwiOlwiRU1BSUxcIn0se1wiaWRcIjo0LFwibmFtZVwiOlwiY2hhbmdlUGFzc3dvcmRcIixcImRpc3BsYXlOYW1lXCI6XCJDaGFuZ2UgUGFzc3dvcmRcIixcImRlc2NyaXB0aW9uXCI6XCJDaGFuZ2UgUGFzc3dvcmRcIixcIm90cFwiOmZhbHNlLFwib3RwTWVkaWFcIjpcIkVNQUlMXCJ9LHtcImlkXCI6NSxcIm5hbWVcIjpcInZpZXdQZXJzb25hbEluZm9ybWF0aW9uXCIsXCJkaXNwbGF5TmFtZVwiOlwiVmlldyBQZXJzb25hbCBJbmZvcm1hdGlvblwiLFwiZGVzY3JpcHRpb25cIjpcIlZpZXcgUGVyc29uYWwgSW5mb3JtYXRpb25cIixcIm90cFwiOmZhbHNlLFwib3RwTWVkaWFcIjpcIkVNQUlMXCJ9XSxcInJvbGVcIjoyLFwiaW5zdGl0dXRpb25JZFwiOjMsXCJpbnN0aXR1dGlvbk5hbWVcIjpcIkNVIEhBV0FJSSBGRURFUkFMIENSRURJVCBVTklPTlwiLFwicnROdW1iZXJcIjpcIjMyMTM3ODcwOVwifX0ifQ.HVVOVOOJQEWZgyEcPlnV9Qj2DNM1IjX7XLHS7FY1sYM"));
		
	}
	
	public static void main(String[] args) {
		String key = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzcxIn0.adWnuuSgY61QQQ6tgjfWn7zTbg03x6xf_QlUwxLJ3Ek";
		
		String decodedStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjp7Imxhc3ROYW1lIjoiTnVuZSIsImRlZmF1bHRQYWdlIjp7ImRpc3BsYXlOYW1lIjoiRGFzaCBCb2FyZCIsIm5hbWUiOiJkYXNoYm9hcmQiLCJkZXNjcmlwdGlvbiI6IkRhc2ggQm9hcmQiLCJpZCI6MS4wfSwic2Vzc2lvbkV4cGlyeVdhcm5pbmciOjMwLjAsInJvbGUiOjIuMCwiaXNGaXJzdExvZ2luIjpmYWxzZSwiaW5zdGl0dXRpb25JZCI6My4wLCJpc01GQVJlcSI6dHJ1ZSwicGFzc3dvcmRFeHBpcmVkSW5EYXlzIjo4OC4wLCJlbWFpbElkIjoic251bmVAdnNvZnRjb3JwLmNvbSIsImxvZ2luU2Vzc2lvbklkIjoiZXlKaGJHY2lPaUpJVXpJMU5pSjkuZXlKemRXSWlPaUl4TXpjeUluMC5RaUx3cVd6T3ZmaDZqc1Z2aXVkbUZEWjlidTJlT0NyR05vQmVkSXEycGpzIiwibW9iaWxlTm8iOiIxMjM0NTY3ODkwIiwibGFzdExvZ2luRGF0ZSI6IjEwLzA3LzIwMTYgMTk6NDUiLCJ1c2VyTmFtZSI6InNudW5lQHZzb2Z0Y29ycC5jb20iLCJ1c2VySWQiOjYuMCwiZmlyc3ROYW1lIjoiU3Jpbml2YXMiLCJpc1NlY3VyaXR5UXVlc3Rpb25zUmVzZXQiOmZhbHNlLCJhY2Nlc3NMaW5rcyI6W3siZGlzcGxheU5hbWUiOiJNeSBTZXJ2aWNlcyIsIm5hbWUiOiJteVNlcnZpY2VzIiwiZGVzY3JpcHRpb24iOiJNeSBTZXJ2aWNlcyIsIm90cE1lZGlhIjoiRU1BSUwiLCJvdHAiOmZhbHNlLCJpZCI6Mi4wfSx7ImRpc3BsYXlOYW1lIjoiY2hhbmdlIFNlY3VyaXR5IFF1ZXN0aW9uIiwibmFtZSI6ImNoYW5nZVNlY3VyaXR5UXVlc3Rpb24iLCJkZXNjcmlwdGlvbiI6ImNoYW5nZVNlY3VyaXR5UXVlc3Rpb250cnVlIiwib3RwTWVkaWEiOiJFTUFJTCIsIm90cCI6ZmFsc2UsImlkIjozLjB9LHsiZGlzcGxheU5hbWUiOiJDaGFuZ2UgUGFzc3dvcmQiLCJuYW1lIjoiY2hhbmdlUGFzc3dvcmQiLCJkZXNjcmlwdGlvbiI6IkNoYW5nZSBQYXNzd29yZCIsIm90cE1lZGlhIjoiRU1BSUwiLCJvdHAiOmZhbHNlLCJpZCI6NC4wfSx7ImRpc3BsYXlOYW1lIjoiVmlldyBQZXJzb25hbCBJbmZvcm1hdGlvbiIsIm5hbWUiOiJ2aWV3UGVyc29uYWxJbmZvcm1hdGlvbiIsImRlc2NyaXB0aW9uIjoiVmlldyBQZXJzb25hbCBJbmZvcm1hdGlvbiIsIm90cE1lZGlhIjoiRU1BSUwiLCJvdHAiOmZhbHNlLCJpZCI6NS4wfV0sImlzUHdkQ2hhbmdlUmVxdWlyZWQiOmZhbHNlLCJpc1Bhc3N3b3JkRXhwaXJlcyI6ZmFsc2UsInNlc3Npb25FeHBpcnlUaW1lIjozNjAwLjAsImluc3RpdHV0aW9uTmFtZSI6IkNVIEhBV0FJSSBGRURFUkFMIENSRURJVCBVTklPTiIsInJ0TnVtYmVyIjoiMzIxMzc4NzA5In0sIm1lc3NhZ2UiOiJVc2VyIGxvZ2dlZCBpbiBzdWNjZXNzZnVsbHkiLCJzdGF0dXMiOiJzdWNjZXNzIiwic3RhdHVzQ29kZSI6MjAwLjB9.QqLOMD87JgJDwys4jCma9pi8B8Kj1GmsEVYHrLwyVMc";

		System.out.println(decodeToJSON(key, decodedStr));
		
	}
	
	
	public static String encodeJSON(String key, JSONObject jsonObject) {
		final JWTSigner signer = new JWTSigner(key);
		
		Map<String, Object> map = new Gson().fromJson(jsonObject.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());

		return signer.sign(map);	
	}
}
