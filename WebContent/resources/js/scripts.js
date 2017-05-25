//Função para ferificar se os campos obrigatórios foram preenchidos
function verificar(xhr, status, args, dlg, tbl) {
	if (args.validationFailed) {
		PF(dlg).jq.effect("shake", {
			times : 5
		}, 100);
	} else {
		PF(dlg).hide();
		PF(tbl).clearFilters();
	}
}

//Função para validar o login de acesso
function login(usuario, senha){
	var usuario = document.getElementById('usuario').value;
	var senha = document.getElementById('senha').value;
	var permissao = 0;
	
	if (usuario == "suelber" && password == 123){
		alert("Seja bem vindo ao sistema!");
	} 
	else {
		alert('Foi triste, você não tem acesso.');
		return false;
	}
}