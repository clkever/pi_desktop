/**
 * Validação dos formulários
 * @author Cleverson Martins
 09/03/2022
 */

function validar() {
	//variáveis usadas para capturar o texto do formulário
	let nome = frmNome.nome.value;
	let fone = frmNome.fone.value;
	let email = frmNome.email.value;
	if (nome === "") {
		alert("Preencha o campo nome");
		frmNome.nome.focus();
	} else if (fone === "") {
		alert("Preencha o fone");
		frmNome.fone.focus();
	} else {
		//submeter o formulário
		document.forms["frmNome"].submit(); //passo 3 do slide 21
	}
}

function confirmar(id) {
	let resposta = confirm("Confirma a exclusão deste cliente?")
	if (resposta == true) {
		window.location.href = "delete?id=" + id;
	}
}