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
        .then(response => response.json())
        .then(data => {
            console.log('Dados recebidos do servidor:', data); // Verifique o conteúdo da resposta

            // Certifique-se de que o 'userId' está sendo retornado corretamente
            if (data.userId) {
                console.log('userId recebido:', data.userId); // Verificando se o userId está presente

                // Armazena o userId no localStorage
                localStorage.setItem('userId', data.userId);
                localStorage.setItem('cpf', cpf);

                // Redireciona o usuário para a página correta
                window.location.replace(data.redirectUrl);
            } else {
                alert(data.message || "Login inválido. userId não recebido.");
            }
        })
        .catch(error => {
            console.error('Erro no login:', error);
            alert('Erro ao tentar fazer login.');
        });
}
