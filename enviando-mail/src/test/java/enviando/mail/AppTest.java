package enviando.mail;

import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

	@org.junit.Test
	public void testeEmail() {
		
		// Observar configurações do smtp do email para testar sem problemas de bloqueio
		Properties properties= new Properties();
		properties.put("mail.smtp.auth", "true"); // Autorização
		properties.put("mail.smtp.starttls", "true"); // Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Servidor Gmail
		properties.put("mail.smtp.port", "465"); // Porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465"); //Especificação da porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Classe socket de conexão ao SMTP")
	}
}
