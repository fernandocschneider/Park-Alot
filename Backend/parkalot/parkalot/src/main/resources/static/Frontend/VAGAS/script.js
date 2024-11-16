const apiUrl = 'http://localhost:8080';


// Função para obter o ID do usuário a partir do localStorage
function getUserIdFromLocalStorage() {
    const userId = localStorage.getItem('userId');
    console.log('ID do usuário do localStorage:', userId);  // Verificando o userId
    return userId;
}

// Função para verificar se o usuário está autenticado com base no localStorage
function checkUserAuth() {
    const userId = getUserIdFromLocalStorage();
    if (!userId) {
        console.log('Usuário não autenticado!');
        return false; // Se não houver userId no localStorage, o usuário não está autenticado
    }
    console.log('Usuário autenticado, ID encontrado no localStorage:', userId);
    return true; // Se houver userId no localStorage, o usuário está autenticado
}

// Função para listar carros do usuário logado filtrados pelo CPF
function listarCarros() {
    const cpf = localStorage.getItem('cpf');  // Recupera o CPF do localStorage
    if (!cpf) {
        console.log('CPF do usuário não encontrado no localStorage!');
        return;
    }

    console.log('Buscando carros para o CPF:', cpf);  // Verificando o CPF

    fetch(`${apiUrl}/listaVeiculos?cpf=${cpf}`)  // Passando o CPF na requisição
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na requisição para listar veículos.');
            }
            return response.json();
        })
        .then(carros => {
            console.log('Carros recebidos:', carros);  // Verifique a resposta da API
            const carroSelect = document.getElementById('carroSelect');
            carroSelect.innerHTML = '<option value="">Selecione um carro</option>';
            carros.forEach(carro => {
                const option = document.createElement('option');
                option.value = carro.id;
                option.textContent = `${carro.model} - ${carro.plate}`;
                carroSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar carros:', error);
            alert('Erro ao carregar a lista de carros. Por favor, tente novamente.');
        });
}

// Função para listar vagas filtradas pelo CPF
function listarVagas() {
    console.log('Buscando vagas...');  // Verificando a chamada da função

    const cpf = localStorage.getItem('cpf');  // Recupera o CPF do localStorage

    fetch(`${apiUrl}/listaVagas?cpf=${cpf}`)  // Passando o CPF na requisição
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na requisição para listar vagas.');
            }
            return response.json();
        })
        .then(vagas => {
            console.log('Vagas recebidas:', vagas);  // Verifique a resposta da API
            const vagaSelect = document.getElementById('vagaSelect');
            vagaSelect.innerHTML = '<option value="">Selecione uma vaga</option>';
            vagas.forEach(vaga => {
                const option = document.createElement('option');
                option.value = vaga.id;
                option.textContent = `${vaga.local}`;
                vagaSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar vagas:', error);
            alert('Erro ao carregar a lista de vagas. Por favor, tente novamente.');
        });
}


// Função para listar as vagas que o usuário já ocupou
function atualizarVagasRelacionadas() {
    const cpf = localStorage.getItem('cpf');  // Recupera o CPF do localStorage
    if (!cpf) {
        console.log('CPF do usuário não encontrado no localStorage!');
        return;
    }

    fetch(`${apiUrl}/listaVagasOcupadas?cpf=${cpf}`)  // Passando o CPF na requisição
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na requisição para listar vagas ocupadas.');
            }
            return response.json();
        })
        .then(relacionamentos => {
            console.log('Vagas relacionadas recebidas:', relacionamentos);  // Verifique a resposta da API
            const container = document.getElementById('vagasRelacionadas');
            container.innerHTML = '<h2>Vagas Relacionadas</h2>';

            relacionamentos.forEach(rel => {
                const novoCard = document.createElement('div');
                novoCard.classList.add('card');
                novoCard.innerHTML = `
                    <div class="card-title">${rel.veiculo.model} - ${rel.veiculo.plate}</div>
                    <p><strong>Vaga:</strong> ${rel.spot.localizacao}</p>
                `;
                container.appendChild(novoCard);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar vagas relacionadas:', error);
        });
}

// Função para logar o usuário
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
                // Salva as informações do usuário no localStorage após o login bem-sucedido
                localStorage.setItem('userId', data.userId);  // Salva o userId no localStorage
                localStorage.setItem('cpf', cpf);  // Salva o CPF no localStorage
                window.location.replace(data.redirectUrl);  // Redireciona para a URL fornecida
            } else {
                alert(data.message || "Login inválido.");
            }
        })
        .catch(error => {
            console.error('Erro no login:', error);
            alert('Erro ao tentar fazer login.');
        });
}

// Evento de carregamento da página
document.addEventListener('DOMContentLoaded', () => {
    const isAuthenticated = checkUserAuth(); // Verifica se o usuário está autenticado com base no localStorage

    if (isAuthenticated) {
        console.log('Usuário autenticado, carregando dados da página...');
        listarCarros();  // Carrega os carros do usuário logado
        listarVagas();   // Carrega as vagas
        atualizarVagasRelacionadas();  // Carrega as vagas relacionadas ao usuário
    } else {
        console.log('Usuário não autenticado, solicitando login...');
        // Exibe o formulário de login ou redireciona o usuário para a página de login
    }
});
