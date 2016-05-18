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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fission.SampleJavascriptSignIN.Google2Api;
import com.fissionlabs.dto.GoogleUserDTO;
import com.fissionlabs.dto.LoginResponseDTO;
import com.fissionlabs.dto.ResponseDTO;
import com.fissionlabs.model.TokenResponse;
import com.fissionlabs.model.User;
import com.fissionlabs.repository.UserRepository;
import com.fissionlabs.security.TokenHandler;

@Controller
public class SocialLoginController {
	@Autowired
	UserRepository userRepository;
	String apiKey = "92208181413-5f19jn5j14k2tgirsq5p30qf42fpo3kq.apps.googleusercontent.com";

	String secretKey = "jMN_QJDnbzwQY-LeVA9TvUei";

	String callbackUrl = "http://localhost:3000/googlesignin";

	private static final String SCOPE = "https://mail.google.com/ https://www.googleapis.com/auth/userinfo.email";

	private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo?alt=json";

	private static final Token EMPTY_TOKEN = null;

	private final TokenHandler tokenHandler = new TokenHandler(
			DatatypeConverter.parseBase64Binary("secret"));

	@RequestMapping(value = "/api/googlesignin", method = RequestMethod.POST)
	@ResponseBody
	public TokenResponse getCode(
			@RequestBody LoginResponseDTO response) throws Exception {
		String code = response.getCode();

		OAuthService service = new ServiceBuilder().provider(Google2Api.class)
				.apiKey(apiKey).apiSecret(secretKey).callback(callbackUrl)
				.scope(SCOPE).build();

		Token accessToken = service.getAccessToken(EMPTY_TOKEN, new Verifier(
				code));

		OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response1 = request.send();
		System.out.println("response 1 is"+response1.getBody());
		GoogleUserDTO userInfo = new ObjectMapper().readValue(
				response1.getBody(), GoogleUserDTO.class);
		String name = userInfo.getName();
		String username = userInfo.getEmail();

		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			user = new User();
			user.setName(name);
			user.setUsername(username);
			user.setAccountExpired(false);
			user.setCreatedAt(new Date());
			user.setAccountEnabled(true);
			user.setAccountLocked(false);
			user.setPicture(userInfo.getPicture());
			// System.out.println("entered");
			System.out.println("Code is" + code);
			//System.out.println("user is" + user.toString());
			user = userRepository.save(user);
			// System.out.println(user);

		}

		String jwt = tokenHandler.createTokenForUser(user);
		// TokenDTO tokenDTO = new TokenDTO(jwt);
		/*
		 * System.out.println(tokenDTO); System.out.println(jwt);
		 */
		//System.out.println(jwt);
		/*return ResponseEntity
				.ok()
				.header("Authorization", jwt)
				.body(new ResponseDTO.ResponseDTOBuilder<>(true, null,
						"login successful").build());*/
		TokenResponse res=new TokenResponse();
		res.setToken(jwt);
		return res;

	}
}
