package com.certificate.learning.digitalCertificate;

 

import com.certificate.learning.digitalCertificate.bean.Certificates;
import com.certificate.learning.digitalCertificate.bean.RenewForm;
import com.certificate.learning.digitalCertificate.bean.UserForm;
import com.certificate.learning.digitalCertificate.repository.CertificatesRepository;
import com.certificate.learning.digitalCertificate.service.CertificateService;
import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

 

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

 

import static org.junit.jupiter.api.Assertions.assertEquals;

 

@SpringBootTest
class DigitalCertificateApplicationTests {

 

	@Autowired
	private CertificateService certificateService;

 

	@Autowired
	private CertificatesRepository certificatesRepository;

 

	@Test
	void testvalidate() throws Exception {
		String validity=certificateService.validateCertificate("ca27");
		assertEquals(validity,"valid");
	}

 


	@Test
	void testUserCerts() throws Exception {
		String certList = certificateService.usercerts("CaAuthority");
		String [] certsArray = certList.split("\n");
		assertEquals(certsArray.length,1);
	}

	@Test
	void testRenewal(){
		RenewForm renewForm = new RenewForm();
		renewForm.setAlias("ca2709");
		renewForm.setRenewYears(2);
		String renewal = certificateService.renewCertificate(renewForm);
		
	}

 

	@Test
	void testGenerateSS() throws Exception {
		UserForm userForm = new UserForm();
		userForm.setAlias("sstest3");
		userForm.setCn("ssCert");
		userForm.setCountry("India");
		userForm.setEmail("abc@gmail.com");
		userForm.setName("James");
		userForm.setOrganization("Telstra");
		userForm.setState("Ts");
		userForm.setLocality("Hyd");
		String Cert = certificateService.generateSelfSignedCertificate(userForm);
		Certificates cert = certificatesRepository.getcertest("sstest3");
		String id = cert.getId();

 

		KeyFactory keyFact = KeyFactory.getInstance("RSA");
		PrivateKey pk = keyFact.generatePrivate(new PKCS8EncodedKeySpec(java.util.Base64.getDecoder().decode(cert.getPrivatekey().getBytes("UTF-8"))));
		String dec= EncryptionDecryptionAES.decrypt(cert.getCertificatetest(),pk);
		X509Certificate certificate = EncryptionDecryptionAES.convertToX509Cert(dec);
		String CertRepo = new String(Base64.encode(certificate.getEncoded()));
		certificatesRepository.deleteById(id);
		assertEquals(Cert,CertRepo);

 

 

	}

 

	@Test
	void testGenerateCA() throws Exception {
		UserForm userForm = new UserForm();
		userForm.setAlias("caTest");
		userForm.setCn("caCert");
		userForm.setCountry("India");
		userForm.setEmail("abc@gmail.com");
		userForm.setName("James");
		userForm.setOrganization("Telstra");
		userForm.setState("Ts");
		userForm.setLocality("Hyd");
		String Cert = certificateService.generateCaSignedCertificate(userForm);
		Certificates cert = certificatesRepository.getcertest("caTest");
		String id = cert.getId();

 

		KeyFactory keyFact = KeyFactory.getInstance("RSA");
		PrivateKey pk = keyFact.generatePrivate(new PKCS8EncodedKeySpec(java.util.Base64.getDecoder().decode(cert.getPrivatekey().getBytes("UTF-8"))));
		String dec= EncryptionDecryptionAES.decrypt(cert.getCertificatetest(),pk);
		X509Certificate certificate = EncryptionDecryptionAES.convertToX509Cert(dec);
		String CertRepo = new String(Base64.encode(certificate.getEncoded()));
		certificatesRepository.deleteById(id);
		assertEquals(Cert,CertRepo);

 

 

	}

 

	@Test
	void testGenerateSigned() throws Exception {
		UserForm userForm = new UserForm();
		userForm.setAlias("signedTest");
		userForm.setCn("signedCert");
		userForm.setCountry("India");
		userForm.setEmail("abc@gmail.com");
		userForm.setName("James");
		userForm.setOrganization("Telstra");
		userForm.setState("Ts");
		userForm.setLocality("Hyd");
		String Cert = certificateService.generateSignedCertificate(userForm);
		Certificates cert = certificatesRepository.getcertest("signedTest");
		String id = cert.getId();

 

		KeyFactory keyFact = KeyFactory.getInstance("RSA");
		PrivateKey pk = keyFact.generatePrivate(new PKCS8EncodedKeySpec(java.util.Base64.getDecoder().decode(cert.getPrivatekey().getBytes("UTF-8"))));
		String dec= EncryptionDecryptionAES.decrypt(cert.getCertificatetest(),pk);
		X509Certificate certificate = EncryptionDecryptionAES.convertToX509Cert(dec);
		String CertRepo = new String(Base64.encode(certificate.getEncoded()));
		certificatesRepository.deleteById(id);
		assertEquals(Cert,CertRepo);

 

 

	}


	@Test
	void testGenerateUnsigned() throws Exception {
		UserForm userForm = new UserForm();
		userForm.setAlias("sstest3");
		userForm.setCn("ssCert");
		userForm.setCountry("India");
		userForm.setEmail("abc@gmail.com");
		userForm.setName("James");
		userForm.setOrganization("Telstra");
		userForm.setState("Ts");
		userForm.setLocality("Hyd");
		String Cert = certificateService.generateSelfSignedCertificate(userForm);
		Certificates cert = certificatesRepository.getcertest("sstest3");
		String id = cert.getId();

 

		KeyFactory keyFact = KeyFactory.getInstance("RSA");
		PrivateKey pk = keyFact.generatePrivate(new PKCS8EncodedKeySpec(java.util.Base64.getDecoder().decode(cert.getPrivatekey().getBytes("UTF-8"))));
		String dec= EncryptionDecryptionAES.decrypt(cert.getCertificatetest(),pk);
		X509Certificate certificate = EncryptionDecryptionAES.convertToX509Cert(dec);
		String CertRepo = new String(Base64.encode(certificate.getEncoded()));
		certificatesRepository.deleteById(id);
		assertEquals(Cert,CertRepo);

 

 

	}
}

