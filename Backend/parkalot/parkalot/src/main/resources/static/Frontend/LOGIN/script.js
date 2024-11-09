function logarUsuario() {

    const email = document.getElementById('email').value;
    const cpf = document.getElementById('cpf').value;

    if (!email || !cpf) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    fetch('http://localhost:8080/verificarLogin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ cpf: cpf, email: email })
    })
    .then(response => {
        if (response.status === 200) {
            alert('Login bem-sucedido!');
            // window.location.href = '../../index.html'; // ESPERAR PARA PÁGINA ESTAR PRONTA =D
        } else {
            alert('Login inválido. Verifique CPF e/ou email.');
        }
    })
    .catch(error => {
        console.error('Erro no login:', error);
        alert('Erro ao tentar fazer login.');
    });
}
