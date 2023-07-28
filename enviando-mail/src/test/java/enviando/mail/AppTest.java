package enviando.mail;

/**
 * Unit test for simple App.
 */
public class AppTest {


	@org.junit.Test
	public void testeEmail() throws Exception {
		
		ObjetoEnviaEmail enviaEmail = 
				new ObjetoEnviaEmail("informar@emailcliente.com", 
						"JDEV TestMail", 
						"Testando email com Java", 
						"Email teste aplicação de envio de Email não responda este e-mail");
		
		enviaEmail.enviarEmail();

		
		/*
		 * Caso o email não esteja enviando colocar
		 * um tempo de espera para encerrar o junit
		 * esse procedimento é usado somente nesse cenário de testes
		 * para isso descomentar a linha abaixo
		 * */
		
		//Thread.sleep(5000);
		
		
	}
	
}
