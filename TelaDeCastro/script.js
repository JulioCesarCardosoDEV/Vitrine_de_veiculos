const formulario = document.querySelector("form");
const inome = document.querySelector(".nome");
const iemail = document.querySelector(".email");
const isenha = document.querySelector(".senha");



function limpar(){
    inome.value = "";
    iemail.value = "";
    isenha.value = ""
}

formulario.addEventListener('submit', e =>{
    handleSubmit(e);
    limpar();
});

function handleSubmit(e){
    e.preventDefault();
    const camposValidos = this.camposSaoValidos();
    const senhasValidas = this.senhasSaoValidas();

    if(camposValidos && senhasValidas) {
      alert('Formulário enviado.');
      this.formulario.submit();
    }
}

function senhasSaoValidas() {
    let valid = true;

    if(isenha.length < 6 || isenha.length > 12) {
      valid = false;
      this.criaErro(senha, 'Senha precisa estar entre 6 e 12 caracteres.');
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

      if(campo.classList.contains('cpf')) {
        if(!this.validaCPF(campo)) valid = false;
      }

      if(campo.classList.contains('usuario')) {
        if(!this.validaUsuario(campo)) valid = false;
      }

    }

    return valid;
  }

  function validaUsuario(campo) {
    const usuario = campo.value;
    let valid = true;

    if(usuario.length < 3 || usuario.length > 12) {
      this.criaErro(campo, 'Usuário precisa ter entre 3 e 12 caracteres.');
      valid = false;
    }

    if(!usuario.match(/^[a-zA-Z0-9]+$/g)) {
      this.criaErro(campo, 'Nome de usuário precisar conter apenas letras e/ou números.');
      valid = false;
    }

    return valid;
  }

  function criaErro(campo, msg) {
    const div = document.createElement('div');
    div.innerHTML = msg;
    div.classList.add('error-text');
    campo.insertAdjacentElement('afterend', div);
  }


