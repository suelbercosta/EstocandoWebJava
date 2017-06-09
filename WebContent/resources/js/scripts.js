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

function verificar2(xhr, status, args, dlg) {
	if (args.validationFailed) {
		PF(dlg).jq.effect("shake", {
			times : 5
		}, 100);
	} else {
		PF(dlg).hide();
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

//Expect input as d/m/y
function validatData(s) {
  var bits = s.split('/');
  var d = new Date(bits[2], bits[1] - 1, bits[0]);
  return d && (d.getMonth() + 1) == bits[1];
}

['0/10/2017','29/2/2016'].forEach(function(s) {
  console.log(s + ' : ' + isValidDate(s))
})

function data() {
	var data = new Date();
	var d = data.getDate();
	var m = data.getMonth() + 1;
	var a = data.getFullYear();
	document.getElementById('dt').value = (d+'/0'+m+'/'+a);
}

function relRequisicao() {
	//if (document.getElementById('txtRelFilRequisicao').Value = 'Data') {
		document.getElementById('txtRelDataRequis').disabled = "False";
	//}
}

