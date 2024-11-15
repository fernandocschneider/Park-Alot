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
            return response.json();
        })
        .then(data => {
            console.log('Dados recebidos do servidor:', data);

            if (data.status === "success") {
                alert("Login bem-sucedido!");
                console.log("Redirecionando para:", data.redirectUrl);
                window.location.replace(data.redirectUrl);
            } else {
                alert(data.message || "Login inválido.");
            }
        })
        .catch(error => {
            console.error('Erro no login:', error);
            alert('Erro ao tentar fazer login.');
        });
}
