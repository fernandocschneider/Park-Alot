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

// // Função para listar vagas, com base na autenticação ou não
// function listarVagas() {
//     console.log('Buscando vagas...');  // Verificando a chamada da função

//     const cpf = localStorage.getItem('cpf');  // Recupera o CPF do localStorage
//     let url = `${apiUrl}/listaVagas`; // Endpoint para obter todas as vagas

//     // Se o CPF estiver no localStorage, incluir como filtro
//     if (cpf) {
//         url += `?cpf=${cpf}`;
//     }

//     fetch(url)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Falha na requisição para listar vagas.');
//             }
//             return response.json();
//         })
//         .then(vagas => {
//             console.log('Vagas recebidas:', vagas);  // Verifique a resposta da API
//             const vagaSelect = document.getElementById('vagaSelect');
//             vagaSelect.innerHTML = '<option value="">Selecione uma vaga</option>';
//             vagas.forEach(vaga => {
//                 const option = document.createElement('option');
//                 option.value = vaga.id;
//                 option.textContent = `${vaga.local}`;
//                 vagaSelect.appendChild(option);
//             });
//         })
//         .catch(error => {
//             console.error('Erro ao carregar vagas:', error);
//             alert('Erro ao carregar a lista de vagas. Por favor, tente novamente.');
//         });
// }

// Função para listar todas as reservas feitas
// function listarReservas() {
//     console.log('Buscando todas as reservas...');

//     fetch(`${apiUrl}/listaReservas`)  // Endpoint para listar todas as reservas
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Falha na requisição para listar reservas.');
//             }
//             return response.json();
//         })
//         .then(reservas => {
//             console.log('Reservas recebidas:', reservas);
//             const reservasContainer = document.getElementById('reservasContainer');
//             reservasContainer.innerHTML = '<h2>Reservas</h2>';

//             reservas.forEach(reserva => {
//                 const reservaCard = document.createElement('div');
//                 reservaCard.classList.add('card');
//                 reservaCard.innerHTML = `
//                     <div class="card-title">${reserva.veiculo.model} - ${reserva.veiculo.plate}</div>
//                     <p><strong>Vaga:</strong> ${reserva.spot.localizacao}</p>
//                     <p><strong>Status:</strong> ${reserva.status}</p>
//                 `;
//                 reservasContainer.appendChild(reservaCard);
//             });
//         })
//         .catch(error => {
//             console.error('Erro ao carregar reservas:', error);
//             alert('Erro ao carregar as reservas. Por favor, tente novamente.');
//         });
// }

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
        listarVagas();   // Carrega as vagas do usuário ou todas as vagas
        listarReservas(); // Carrega as reservas do usuário
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
        .catch(error => {
            console.error('Erro ao criar o carro:', error);
            alert('Erro ao tentar criar o carro. Por favor, tente novamente.');
        });
}