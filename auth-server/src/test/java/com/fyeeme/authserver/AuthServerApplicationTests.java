package com.fyeeme.authserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//@SpringBootTest
class AuthServerApplicationTests {

	@Test
	void contextLoads() {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		//generate
		// https://www.valentinog.com/blog/challenge/
		// https://www.appsdeveloperblog.com/pkce-code-verifier-and-code-challenge-in-java/
		//QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8
		byte[] digest = md.digest("qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI".getBytes(StandardCharsets.UTF_8));

		String encodedVerifier = Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
		System.out.println(encodedVerifier);
	}

}
