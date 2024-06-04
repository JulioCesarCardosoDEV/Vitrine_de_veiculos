const formulario = document.querySelector("form");
const inome = document.querySelector(".nome");
const iemail = document.querySelector(".email");
const isenha = document.querySelector(".senha");
const irepetirSenha = document.querySelector(".repetir-senha")

formulario.addEventListener('submit', e =>{
    handleSubmit(e);
});

function handleSubmit(e){
    e.preventDefault();
    const camposValidos = this.camposSaoValidos();
    const senhasValidas = this.senhasSaoValidas();
    const nomesValidos = this.nomesSaoValidos();
    const emailsValidos = this.emailsSaoValidos();

    if(camposValidos && senhasValidas && nomesValidos && emailsValidos) {
      cadastrar();
      limpar();
      alert('Formulário enviado.');
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

function senhasSaoValidas() {
    let valid = true;

    if(isenha.value.length < 8) {
      valid = false; 
      this.criaErro(isenha, 'A senha precisa ter 8 caracteres no mínimo');
    }

    if(irepetirSenha.value.length < 8) {
      valid = false; 
      this.criaErro(irepetirSenha, 'A senha precisa ter 8 caracteres no mínimo');
    }

    if(isenha.value != irepetirSenha.value){
      valid = false; 
      this.criaErro(isenha, 'As senhas são diferentes');
      this.criaErro(irepetirSenha, 'As senhas são diferentes');
    }

    return valid;
  }

function nomesSaoValidos() {
    let valid = true;

    if(inome.value.length < 3) {
      valid = false; 
      return this.criaErro(inome, 'O nome precisa ter pelo menos 3 letras');
    }

    return valid;
}

function emailsSaoValidos() {
  let valid = true;

  if (!iemail.value.includes('@') || !iemail.value.includes('.')) {
    valid = false; 
    return this.criaErro(iemail, 'Email inválido');
  }

  return valid;
}

function camposSaoValidos() {
    let valid = true;

    for(let errorText of formulario.querySelectorAll('.error-text')) {
      errorText.remove();
    }

    for(let campo of formulario.querySelectorAll('.validar')) {
      const label = campo.previousElementSibling.innerText;

      if(!campo.value) {
        this.criaErro(campo, `Campo "${label}" não pode estar em branco.`);
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

  function limpar(){
    inome.value = "";
    iemail.value = "";
    isenha.value = "",
    irepetirSenha.value = ""
}


