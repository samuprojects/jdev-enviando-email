package enviando.mail;

/**
 * Unit test for simple App.
 */
public class AppTest {


	@org.junit.Test
	public void testeEmail() throws Exception {
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá, <br/><br/>");
		stringBuilderTextoEmail.append("Você está recebendo o email de teste. <br/><br/>");
		stringBuilderTextoEmail.append("Para acessar página teste clique no botão abaixo. <br/><br/>");
		
		stringBuilderTextoEmail.append("<b>Login:</b> teste@1234.com<br/>");
		stringBuilderTextoEmail.append("<b>Senha:</b> 12345678teste<br/><br/>");
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href= \"http://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7; padding: 14px 25px; text-align:center; text-decoration: none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border: 3px solid green; background-color:#99DA39;\">Acessar Portal do Aluno</a><br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\">Ass.: Samuel do JDev Treinamento</span>");
		
		ObjetoEnviaEmail enviaEmail = 
				new ObjetoEnviaEmail("informar@emailcliente.com", 
						"JDEV TestMail", 
						"Testando email com Java", 
						stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmail(true);

		
		/*
		 * Caso o email não esteja enviando colocar
		 * um tempo de espera para encerrar o junit
		 * esse procedimento é usado somente nesse cenário de testes
		 * para isso descomentar a linha abaixo
		 * */
		
		//Thread.sleep(5000);
		
		
	}
	
}
