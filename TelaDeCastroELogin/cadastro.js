const formulario = document.querySelector("form");
const inome = document.querySelector(".nome");
const iemail = document.querySelector(".email");
const isenha = document.querySelector(".senha");
const irepetirSenha = document.querySelector(".repetir-senha");

const formularioLogin = document.querySelector(".container-login .formlogin");
const iemailLogin = document.querySelector(".container-login .emaillogin");
const isenhaLogin = document.querySelector(".container-login .senhalogin");

formulario.addEventListener('submit', e =>{
    handleSubmit(e);
});

formularioLogin.addEventListener('submit', e => {
  handleSubmitLogin(e);
});

function handleSubmit(e){
    e.preventDefault();
    const camposValidos = camposSaoValidos(formulario);
    const senhasValidas = senhasSaoValidasCadastro();
    const nomesValidos = nomesSaoValidosCadastro();
    const emailsValidos = emailsSaoValidosCadastro();
    if(camposValidos && senhasValidas && nomesValidos && emailsValidos) {
      limpar(formulario);
      alert('Formulário enviado.');
    }
}

function handleSubmitLogin(e) {
  e.preventDefault();
  const camposValidos = camposSaoValidos(formularioLogin);
  const emailValido = emailsSaoValidosLogin(iemailLogin);
  const senhaValida = senhasSaoValidasLogin(isenhaLogin);
  if (camposValidos && emailValido && senhaValida) {
      limpar(formularioLogin);
      alert('Login realizado com sucesso.');
  }
}

function cadastrar(){
  fetch("http://localhost:8080/users",
      {
          headers: {
              'Accept':'application/json',
              'Content-Type':'application/json'
          },
          method: "POST",
          body:JSON.stringify({
              username:inome.value,
              email:iemail.value,
              senha:isenha.value,
          })
      }).then(function(res){console.log(res)})
      .catch(function (res) { console.log(res) })
};

function login() {
  
}

function senhasSaoValidasCadastro() {
    let valid = true;

    if(isenha.value.length < 8) {
      valid = false; 
      criaErro(isenha, 'A senha precisa ter 8 caracteres no mínimo');
    }

    if(irepetirSenha.value.length < 8) {
      valid = false; 
      criaErro(irepetirSenha, 'A senha precisa ter 8 caracteres no mínimo');
    }

    if(isenha.value != irepetirSenha.value){
      valid = false; 
      criaErro(isenha, 'As senhas são diferentes');
      criaErro(irepetirSenha, 'As senhas são diferentes');
    }
    return valid;
}

function nomesSaoValidosCadastro() {
    let valid = true;

    if(inome.value.length < 3) {
      valid = false; 
      criaErro(inome, 'O nome precisa ter pelo menos 3 letras');
    }

    return valid;
}

function emailsSaoValidosCadastro() {
  let valid = true;

  if (!iemail.value.includes('@') || !iemail.value.includes('.')) {
    valid = false; 
    return this.criaErro(iemail, 'Email inválido');
  }

  return valid;
}

function emailsSaoValidosLogin(emailInput) {
  let valid = true;

  if (!emailInput.value.includes('@') || !emailInput.value.includes('.')) {
    valid = false; 
    return this.criaErro(iemailLogin, 'Email inválido');
  }

  return valid;
}

function senhasSaoValidasLogin(senhaInput) {
  const senha = senhaInput.value.trim();
  if (senha.length < 8) {
      criaErro(senhaInput, 'A senha precisa ter pelo menos 8 caracteres');
      return false;
  }
  return true;
}

function camposSaoValidos(form) {
    let valid = true;

    for(let errorText of form.querySelectorAll('.error-text')) {
      errorText.remove();
    }

    for(let campo of form.querySelectorAll('.validar')) {
      const label = campo.previousElementSibling.innerText;

      if(!campo.value) {
        criaErro(campo, `Campo "${label}" não pode estar em branco.`);
        valid = false;
      }
    }

    return valid;
}

function criaErro(campo, msg) {
    const div = document.createElement('div');
    div.innerHTML = msg;
    div.classList.add('error-text');
    campo.insertAdjacentElement('afterend', div);
}

function limpar(form) {
    for (let campo of form.querySelectorAll('.validar')) {
        campo.value = "";
    }
}
