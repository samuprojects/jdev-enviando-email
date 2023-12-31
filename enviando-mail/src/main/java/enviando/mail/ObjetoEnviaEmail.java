package enviando.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String userName = "inserir gmail aqui";
	private String senha = "inserir senha do gmail aqui";
	
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	

	public ObjetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}



	public void enviarEmail(boolean envioHtml) throws Exception {

		// Observar configurações do smtp do email para testar sem problemas de bloqueio
		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*"); // novo ajuste para validação, o asterico é para autorizar tudo
		properties.put("mail.smtp.auth", "true"); // Autorização
		properties.put("mail.smtp.starttls", "true"); // Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Servidor Gmail
		properties.put("mail.smtp.port", "465"); // Porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465"); // Especificação da porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Classe socket de conexão
																							// ao SMTP")

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente)); // Quem está enviando, incluído nome da empresa
																		// ou pessoa
		message.setRecipients(Message.RecipientType.TO, toUser); // Email destino
		message.setSubject(assuntoEmail); // Assunto do email
		
		if (envioHtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");
		} else {
		message.setText(textoEmail);
		}
		
		Transport.send(message);

	}
	
	public void enviarEmailAnexo(boolean envioHtml) throws Exception {

		// Observar configurações do smtp do email para testar sem problemas de bloqueio
		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*"); // novo ajuste para validação, o asterico é para autorizar tudo
		properties.put("mail.smtp.auth", "true"); // Autorização
		properties.put("mail.smtp.starttls", "true"); // Autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Servidor Gmail
		properties.put("mail.smtp.port", "465"); // Porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465"); // Especificação da porta a ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Classe socket de conexão
																							// ao SMTP")

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente)); // Quem está enviando, incluído nome da empresa
																		// ou pessoa
		message.setRecipients(Message.RecipientType.TO, toUser); // Email destino
		message.setSubject(assuntoEmail); // Assunto do email
		
		// Parte 1 do email - texto e descrição
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		if (envioHtml) {
			corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
		} else {
		corpoEmail.setText(textoEmail);
		}
		
		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorDePDF()); // exemplo certificado
		arquivos.add(simuladorDePDF()); // nota fiscal
		arquivos.add(simuladorDePDF()); // doc texto
		arquivos.add(simuladorDePDF()); // imagem
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		
		int index = 0;
		for (FileInputStream fileInputStream : arquivos) {
			
			//Parte 2 do email - os anexos do email		
			MimeBodyPart anexoEmail = new MimeBodyPart();
			
			// Onde foi passado o simuladorPDF deve ser trocado pelo arquivo gravado no banco de dados por exemplo
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
			anexoEmail.setFileName("anexoemail"+index+".pdf");
					
			multipart.addBodyPart(anexoEmail);
			
			index++;
			
		}
		

		
		message.setContent(multipart);
		
		Transport.send(message);

	}
	
	
	// Esse método simula  o PDF ou qualquer arquivo que possa ser enviado por anexo no email
	// pode ser do banco, base64, byte[], Stream de Arquivos, de qualquer lugar.
	// Retorna uma PDF com o texto do paragrafo de exemplo
	@SuppressWarnings("unused")
	private FileInputStream simuladorDePDF() throws Exception{
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto pe do PDF"));
		document.close();
		
		return new FileInputStream(file);		
	}

}
