package com.agilethought.atsceapi.service.security;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.agilethought.atsceapi.exception.BadRequestException;

public class RsaPasswordEncoderTest {
    @InjectMocks
    private RsaPasswordEncoder rsaPasswordEncoder;
    private String originalPassword;
    
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		String PUBLIC_KEY = System.getenv("PUBLIC_KEY");
		String PRIVATE_KEY = System.getenv("PRIVATE_KEY");
		rsaPasswordEncoder = new RsaPasswordEncoder(PUBLIC_KEY, PRIVATE_KEY);
		originalPassword = "AgileThought4";
	}

	@Test
	public void testEncryptAndDecrypt() {		
		String passwordEncrypted = rsaPasswordEncoder.encode(originalPassword);
		String passwordDecrypted = rsaPasswordEncoder.decode(passwordEncrypted);
		assertEquals(originalPassword, passwordDecrypted);
	}  
	
	@Test
	public void testDecryptAPlainText() {
		assertThrows(IllegalArgumentException.class, () -> rsaPasswordEncoder.decode(originalPassword));
	}
	
	@Test
	public void testMatches() {
		String dbPassword = rsaPasswordEncoder.encode(originalPassword);
		boolean bothPasswordsMatch = rsaPasswordEncoder.matches(originalPassword, dbPassword);
		assertEquals(true, bothPasswordsMatch);
	}
}