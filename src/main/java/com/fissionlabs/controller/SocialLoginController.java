package com.fissionlabs.controller;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fission.SampleJavascriptSignIN.Google2Api;
import com.fissionlabs.dto.GoogleUserDTO;
import com.fissionlabs.dto.TokenDTO;
import com.fissionlabs.model.User;
import com.fissionlabs.repository.UserRepository;
import com.fissionlabs.security.TokenHandler;

@Controller
public class SocialLoginController {
	@Autowired
	UserRepository userRepository;
	String apiKey = "92208181413-5f19jn5j14k2tgirsq5p30qf42fpo3kq.apps.googleusercontent.com";

	String secretKey = "jMN_QJDnbzwQY-LeVA9TvUei";

	// String callbackUrl =
	// "http://localhost:8080/SampleJavascriptSignIN/googlesignin";
	String callbackUrl = "http://localhost:8080/googlesignin";

	private static final String SCOPE = "https://mail.google.com/ https://www.googleapis.com/auth/userinfo.email";

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo?alt=json";

	private static final Token EMPTY_TOKEN = null;

	private final TokenHandler tokenHandler = new TokenHandler(
			DatatypeConverter.parseBase64Binary("secret"));

	@RequestMapping(value = "/googlesignin", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<TokenDTO> getCode(
			@RequestParam(value = "code") String code) throws Exception {

	//	System.out.println("entered");
		OAuthService service = new ServiceBuilder().provider(Google2Api.class)
				.apiKey(apiKey).apiSecret(secretKey).callback(callbackUrl)
				.scope(SCOPE).build();

		Token accessToken = service.getAccessToken(EMPTY_TOKEN, new Verifier(
				code));

		OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
		GoogleUserDTO userInfo = new ObjectMapper().readValue(
				response.getBody(), GoogleUserDTO.class);
		String name = userInfo.getName();
		String username = userInfo.getEmail();
		/*
		 * System.out.println("Name: " + userInfo.getName());
		 * System.out.println("email: " + userInfo.getEmail());
		 */
		/*
		 * JSONParser parser = new JSONParser(); String s =
		 * "{\n \"id\": \"115685014809507364444\",\n \"email\": \"karthik.velishala54@gmail.com\","
		 * +
		 * "\n \"verified_email\": true,\n \"name\": \"karthik velishala\",\n \"given_name\": \"karthik\",\n \"family_name\": \"velishala\",\n \"link\": \"https://plus.google.com/115685014809507364444\",\n \"picture\": \"https://lh5.googleusercontent.com/"
		 * +
		 * "-dGP2-B4XAdg/AAAAAAAAAAI/AAAAAAAAAHU/RE-mSChxPMw/photo.jpg\",\n \"gender\": \"male\"\n}\n"
		 * ; try { JSONObject obj=(JSONObject) parser.parse(s);
		 * System.out.println(obj.get("email"));
		 * System.out.println(obj.get("name")); String email=(String)
		 * obj.get("email"); String name=(String)obj.get("name");
		 * 
		 * User user= userRepository.findByEmail(email); if(user==null) { User
		 * user1=new User(name, email); userRepository.save(user1);
		 * 
		 * } else return "old user";
		 * 
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// String email=(String) jsonObject.get("email");
		// System.out.println(json.get("email"));
		// System.out.println("email");

		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			user = new User();
			user.setName(name);
			user.setUsername(username);
			user.setAccountExpired(false);
			user.setCreatedAt(new Date());
			user.setAccountEnabled(true);
			user.setAccountLocked(false);
			//System.out.println("entered");
			System.out.println("Code is"+code);
			System.out.println("user is"+user.toString());
			user = userRepository.save(user);
			// System.out.println(user);

		}

		String jwt = tokenHandler.createTokenForUser(user);
		TokenDTO tokenDTO = new TokenDTO(jwt);
		/*
		 * System.out.println(tokenDTO); System.out.println(jwt);
		 */

		return ResponseEntity.ok().header("Authorization", jwt).body(tokenDTO);

	}
}
