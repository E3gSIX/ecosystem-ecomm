package br.com.fiap.postech.adjt.checkout.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Method;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import feign.Client;
import feign.Logger;

class FeignConfigTest {

	@InjectMocks
	private FeignConfig feignConfig;

	@Mock
	private SSLSocketFactory sslSocketFactory;

	@Mock
	private X509TrustManager trustManager;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFeignClientBeanCreation() {
		Client feignClient = feignConfig.feignClient();
		assertNotNull(feignClient, "Feign client should not be null");
	}

	@Test
	void testFeignLoggerLevelBeanCreation() {
		Logger.Level loggerLevel = feignConfig.feignLoggerLevel();
		assertNotNull(loggerLevel, "Feign logger level should not be null");
	}

	@Test
	void testSSLSocketFactoryCreation() {
		SSLSocketFactory sslSocketFactory = FeignConfig.SSLSocketClient.getSSLSocketFactory();
		assertNotNull(sslSocketFactory, "SSLSocketFactory should not be null");
	}

	@Test
	void testHostnameVerifierCreation() {
		assertNotNull(FeignConfig.SSLSocketClient.getHostnameVerifier(), "HostnameVerifier should not be null");
	}

	@Test
	void testTrustManagersCreation() throws Exception {
		Method method = FeignConfig.SSLSocketClient.class.getDeclaredMethod("getTrustManagers");
		method.setAccessible(true);
		TrustManager[] trustManagers = (TrustManager[]) method.invoke(null);
		assertNotNull(trustManagers, "TrustManagers should not be null");
		assertNotNull(trustManagers[0], "First TrustManager should not be null");
	}
}