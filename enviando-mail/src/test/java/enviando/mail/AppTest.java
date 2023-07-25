package enviando.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	private String userName = "inserir gmail aqui";
	private String senha = "inserir senha do gmail aqui";

	@org.junit.Test
	public void testeEmail() {
		try {
		
		// Observar configurações do smtp do email para testar sem problemas de bloqueio
		Properties properties= new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*"); // novo ajuste para validação, o asterico é para autorizar tudo
		properties.put("mail.smtp.auth", "true"); // Autorização
		properties.put("mail.smtp.starttls", "true"); // Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Servidor Gmail
		properties.put("mail.smtp.port", "465"); // Porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465"); //Especificação da porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Classe socket de conexão ao SMTP")
	
		@SuppressWarnings("unused")
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		Address[] toUser = InternetAddress.parse("informar@emailcliente.com");
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, "JavaMailJDEV")); // Quem está enviando, incluído nome da empresa ou pessoa
		message.setRecipients(Message.RecipientType.TO, toUser); // Email destino
		message.setSubject("Chegou email de teste Java"); // Assunto do email
		message.setText("Olá programador, funcionou o teste de envio do email");
		
		Transport.send(message);
		
		/*
		 * Caso o email não esteja enviando colocar
		 * um tempo de espera para encerrar o junit
		 * esse procedimento é usado somente nesse cenário de testes
		 * para isso descomentar a linha abaixo
		 * */
		
		//Thread.sleep(5000);
		
		} catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
}
