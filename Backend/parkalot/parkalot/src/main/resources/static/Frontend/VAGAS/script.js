const apiUrl = 'http://localhost:8080';

function getUserIdFromLocalStorage() {
    const userId = localStorage.getItem('userId');
    return userId;
}

function checkUserAuth() {
    const userId = getUserIdFromLocalStorage();
    if (!userId) {
        return false;
    }
    return true;
}

function listarCarros() {
    const cpf = localStorage.getItem('cpf');
    if (!cpf) {
        return;
    }

    fetch(`${apiUrl}/listaVeiculos?cpf=${cpf}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na requisição para listar veículos.');
            }
            return response.json();
        })
        .then(carros => {
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
            if (data.status === "success") {
                alert("Login bem-sucedido!");
                localStorage.setItem('userId', data.userId);
                localStorage.setItem('cpf', cpf);

                window.location.replace(data.redirectUrl);
            } else {
                alert(data.message || "Login inválido.");
            }
        })
}

document.addEventListener('DOMContentLoaded', () => {
    const isAuthenticated = checkUserAuth();

    if (isAuthenticated) {
        listarCarros();
        listarVagas();
        listarReservas();
    }
});

function criarCarro() {
    const modelo = document.getElementById('modeloInput').value;
    const marca = document.getElementById('marcaInput').value;
    const placa = document.getElementById('placaInput').value;
    const ano = document.getElementById('anoInput').value;
    const cor = document.getElementById('corInput').value;

    if (!modelo || !marca || !placa || !ano || !cor) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const userId = localStorage.getItem('userId');
    if (!userId) {
        alert('Usuário não autenticado. Faça login para continuar.');
        return;
    }

    const carro = {
        model: modelo,
        brand: marca,
        plate: placa,
        year: ano,
        color: cor,
        client_id: userId
    };

    fetch('http://localhost:8080/criarVeiculo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(carro)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao criar o carro');
            }
            return response.json();
        })
        .then(data => {
            alert('Carro criado com sucesso!');
            document.getElementById('formAdicionarCarro').reset();
        })
}

function listarVagas() {
    const url = `${apiUrl}/disponiveis`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao carregar as vagas');
            }

            if (response.status === 204) {
                return [];
            }

            return response.json();
        })
        .then(vagas => {
            const vagaSelect = document.getElementById('vagaSelect');
            vagaSelect.innerHTML = '<option value="">Selecione uma vaga disponível</option>';

            if (vagas.length === 0) {
                vagaSelect.innerHTML = '<option value="">Não há vagas disponíveis no momento</option>';
                return;
            }

            vagas.forEach(vaga => {
                const option = document.createElement('option');
                option.value = vaga.id;
                option.textContent = `${vaga.local}`;
                vagaSelect.appendChild(option);
            });
        })
}

function reservarVaga() {
    const spotId = document.getElementById('vagaSelect').value;
    const carroSelect = document.getElementById('carroSelect');
    const selectedOption = carroSelect.options[carroSelect.selectedIndex];

    const userId = localStorage.getItem('userId');

    if (!spotId || !selectedOption || !userId) {
        alert('Por favor, selecione uma vaga, um carro e verifique o ID do cliente!');
        return;
    }

    const carInfo = selectedOption.textContent;
    const plate = carInfo.split('-')[1].trim();

    const url = new URL(`${apiUrl}/reservarVaga`);
    url.searchParams.append('spotId', spotId);
    url.searchParams.append('plate', plate);
    url.searchParams.append('clientId', userId);

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
            listarVagas();
            listarReservas();
        })
}

function listarReservas() {
    const userId = localStorage.getItem('userId');

    if (!userId) {
        return;
    }

    fetch(`${apiUrl}/listaReservasCliente?userId=${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao carregar reservas');
            }
            return response.json();
        })
        .then(reservasData => {
            const reservasContainer = document.getElementById('reservasContainer');
            reservasContainer.innerHTML = '';

            if (reservasData.length === 0) {
                reservasContainer.innerHTML = '<p>Você ainda não tem reservas.</p>';
                return;
            }

            reservasData.forEach(reserva => {
                const reservaCard = document.createElement('div');
                reservaCard.classList.add('reserva-card');

                const reservaInfo = document.createElement('div');
                reservaInfo.classList.add('reserva-info');

                const carroInfo = document.createElement('h3');
                carroInfo.textContent = `Carro: ${reserva.veiculo.model} - ${reserva.veiculo.plate}`;
                reservaInfo.appendChild(carroInfo);

                const vagaInfo = document.createElement('p');
                vagaInfo.innerHTML = `Vaga: <strong>${reserva.spot.local}</strong>`;
                reservaInfo.appendChild(vagaInfo);

                const horaInfo = document.createElement('p');
                horaInfo.innerHTML = `Horário: <strong>${reserva.spot.time} minutos</strong>`;
                reservaInfo.appendChild(horaInfo);

                reservaCard.appendChild(reservaInfo);

                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Excluir Reserva';
                deleteButton.classList.add('delete-button');
                deleteButton.onclick = () => excluirReserva(reserva.id);
                reservaCard.appendChild(deleteButton);

                reservasContainer.appendChild(reservaCard);
            });
        })
}

function excluirReserva(reservaId) {
    const confirmDelete = confirm('Tem certeza que deseja excluir essa reserva?');
    if (!confirmDelete) return;

    fetch(`${apiUrl}/excluirReserva?booking_id=${reservaId}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao excluir a reserva');
            }
            alert('Reserva excluída com sucesso');
            listarReservas();
            listarVagas();
        })
}
