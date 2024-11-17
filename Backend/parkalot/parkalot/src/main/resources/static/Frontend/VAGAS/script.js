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
        .then(response => response.json())
        .then(data => {
            console.log('Dados recebidos do servidor:', data);

            if (data.status === "success") {
                alert("Login bem-sucedido!");
                console.log("Redirecionando para:", data.redirectUrl);

                // Armazenando corretamente o userId no localStorage
                localStorage.setItem('userId', data.userId);  // Certifique-se de que 'userId' é o nome correto da chave
                localStorage.setItem('cpf', cpf);  // Salvando o CPF no localStorage

                // Redireciona para a URL fornecida
                window.location.replace(data.redirectUrl);
            } else {
                alert(data.message || "Login inválido.");
            }
        })
}

// Evento de carregamento da página
document.addEventListener('DOMContentLoaded', () => {
    const isAuthenticated = checkUserAuth(); // Verifica se o usuário está autenticado com base no localStorage

    if (isAuthenticated) {
        console.log('Usuário autenticado, carregando dados da página...');
        listarCarros();  // Carrega os carros do usuário logado
        listarVagas();   // Carrega as vagas do usuário ou todas as vagas
        listarReservas(); // Carrega as reservas do usuário logado
    } else {
        console.log('Usuário não autenticado, solicitando login...');
        // Exibe o formulário de login ou redireciona o usuário para a página de login
    }
});


// Função para criar um novo carro
function criarCarro() {
    const modelo = document.getElementById('modeloInput').value;
    const marca = document.getElementById('marcaInput').value;
    const placa = document.getElementById('placaInput').value;
    const ano = document.getElementById('anoInput').value;
    const cor = document.getElementById('corInput').value;

    // Verificando se todos os campos obrigatórios foram preenchidos
    if (!modelo || !marca || !placa || !ano || !cor) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    // Obtendo o userId do localStorage
    const userId = localStorage.getItem('userId');
    if (!userId) {
        alert('Usuário não autenticado. Faça login para continuar.');
        return;
    }

    // Verifique se o userId não está undefined
    console.log('userId recuperado do localStorage:', userId);

    const carro = {
        model: modelo,
        brand: marca,
        plate: placa,
        year: ano,
        color: cor,
        client_id: userId  // Certifique-se de que userId está sendo incluído corretamente
    };

    console.log('Carro a ser criado:', carro);

    // Enviar dados do carro para o backend
    fetch('http://localhost:8080/criarVeiculo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(carro)
    })
        .then(response => {
            console.log('Resposta da requisição:', response);  // Verifique o status e os dados da resposta
            if (!response.ok) {
                throw new Error('Erro ao criar o carro');
            }
            return response.json();
        })
        .then(data => {
            console.log('Carro criado com sucesso:', data);
            alert('Carro criado com sucesso!');
            // Limpar campos do formulário após sucesso
            document.getElementById('formAdicionarCarro').reset();
        })
}

function listarVagas() {
    console.log('Buscando vagas disponíveis...');

    // Atualizando a URL para chamar o endpoint de vagas disponíveis
    const url = `${apiUrl}/disponiveis`; // Endpoint que retorna apenas as vagas disponíveis

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao carregar as vagas');
            }

            // Verificar se o corpo da resposta está vazio
            if (response.status === 204) {
                console.log('Nenhuma vaga disponível');
                return []; // Retorna um array vazio
            }

            // Se a resposta for válida, tenta converter para JSON
            return response.json();
        })
        .then(vagas => {
            const vagaSelect = document.getElementById('vagaSelect');
            vagaSelect.innerHTML = '<option value="">Selecione uma vaga disponível</option>';

            if (vagas.length === 0) {
                console.log('Nenhuma vaga disponível');
                vagaSelect.innerHTML = '<option value="">Não há vagas disponíveis no momento</option>';
                return; // Não mostramos erro, apenas uma mensagem
            }

            vagas.forEach(vaga => {
                const option = document.createElement('option');
                option.value = vaga.id;
                option.textContent = `${vaga.local}`;  // Exibe a localização da vaga
                vagaSelect.appendChild(option);
            });
        })
}

function reservarVaga() {
    const spotId = document.getElementById('vagaSelect').value;
    const carroSelect = document.getElementById('carroSelect');
    const selectedOption = carroSelect.options[carroSelect.selectedIndex];

    const userId = localStorage.getItem('userId');  // Recupere o clientId armazenado no localStorage

    // Verificação para garantir que todos os campos foram preenchidos
    if (!spotId || !selectedOption || !userId) {
        alert('Por favor, selecione uma vaga, um carro e verifique o ID do cliente!');
        return;
    }

    const carInfo = selectedOption.textContent;
    const plate = carInfo.split('-')[1].trim();  // Extrai a placa do carro

    // Verifica se o userId está disponível
    if (!userId) {
        alert('Erro: ID do cliente não encontrado');
        return;
    }

    // Construir a URL com todos os parâmetros necessários
    const url = new URL(`${apiUrl}/reservarVaga`);
    url.searchParams.append('spotId', spotId);
    url.searchParams.append('plate', plate);
    url.searchParams.append('clientId', userId);  // Passando o clientId correto

    console.log("URL gerada para reserva: ", url.toString());  // Verifique no console se a URL está correta

    // Enviar a requisição para o backend
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(`Erro ${response.status}: ${text || 'Erro ao realizar a reserva'}`);
                });
            }
            return response.json();
        })
        .then(data => {
            alert('Reserva realizada com sucesso!');
            listarVagas(); // Atualiza a lista de vagas disponíveis
            listarReservas(); // Atualiza as reservas do usuário
        })
}

function listarReservas() {
    const userId = localStorage.getItem('userId');  // Recupera o ID do usuário do localStorage

    if (!userId) {
        console.log('Usuário não autenticado!');
        return;
    }

    console.log('Buscando reservas para o usuário:', userId);

    fetch(`${apiUrl}/listaReservasCliente?userId=${userId}`)  // Certifique-se de que o nome do parâmetro esteja correto
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao carregar reservas');
            }
            return response.json();
        })
        .then(reservasData => {
            console.log('Reservas recebidas:', reservasData);

            const reservasContainer = document.getElementById('reservasContainer');
            reservasContainer.innerHTML = '';  // Limpa o conteúdo anterior

            if (reservasData.length === 0) {
                reservasContainer.innerHTML = '<p>Você ainda não tem reservas.</p>';
                return;
            }

            reservasData.forEach(reserva => {
                const reservaCard = document.createElement('div');
                reservaCard.classList.add('reserva-card');  // Adiciona a classe do card

                // Criando a seção de informações da reserva
                const reservaInfo = document.createElement('div');
                reservaInfo.classList.add('reserva-info');

                const carroInfo = document.createElement('h3');
                carroInfo.textContent = `Carro: ${reserva.veiculo.model} - ${reserva.veiculo.plate}`;
                reservaInfo.appendChild(carroInfo);

                const vagaInfo = document.createElement('p');
                vagaInfo.innerHTML = `Vaga: <strong>${reserva.spot.local}</strong>`;
                reservaInfo.appendChild(vagaInfo);

                reservaCard.appendChild(reservaInfo);

                // Criando o botão de exclusão
                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Excluir Reserva';
                deleteButton.classList.add('delete-button');
                deleteButton.onclick = () => excluirReserva(reserva.id);  // Chama a função para excluir a reserva
                reservaCard.appendChild(deleteButton);

                // Adiciona o card de reserva ao container de reservas
                reservasContainer.appendChild(reservaCard);
            });
        })
}

function excluirReserva(reservaId) {
    const confirmDelete = confirm('Tem certeza que deseja excluir essa reserva?');
    if (!confirmDelete) return;

    // Envia o parâmetro 'booking_id' como query na URL
    fetch(`${apiUrl}/excluirReserva?booking_id=${reservaId}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao excluir a reserva');
            }
            alert('Reserva excluída com sucesso');
            listarReservas(); // Atualiza a lista de reservas
            listarVagas();
        })
}



