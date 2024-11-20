function addNewCard() {
    // Criar um novo card
    const container = document.getElementById('reservasContainer');
    const newCard = document.createElement('div');
    newCard.classList.add('reserva-card');
    
    // Adicionar conteúdo ao novo card
    newCard.innerHTML = `
        <div class="reserva-info">
            <h3>Nome do Carro</h3>
            <p><strong>Modelo:</strong> Modelo do Carro</p>
            <p><strong>Marca:</strong> Marca do Carro</p>
            <p><strong>Placa:</strong> Placa do Carro</p>
            <p><strong>Cor:</strong> Cor do Carro</p>
            <p><strong>Ano:</strong> Ano de Fabricação</p>
        </div>
        <button class="delete-button" onclick="deleteCard(this)">Excluir</button>
    `;

    // Adicionar o novo card ao contêiner
    container.appendChild(newCard);
}

function deleteCard(button) {
    button.parentElement.remove();
}
// Função para mostrar o formulário de adicionar carro
function mostrarFormularioAdicionarCarro() {
    const formContainer = document.getElementById('formAdicionarCarroContainer');
    formContainer.style.display = 'block'; // Torna o formulário visível
}

// Função para criar um novo carro
function criarCarro() {
    const modelo = document.getElementById('modeloInput').value;
    const marca = document.getElementById('marcaInput').value;
    const placa = document.getElementById('placaInput').value;
    const cor = document.getElementById('corInput').value;
    const ano = document.getElementById('anoInput').value;

    // Aqui você pode adicionar a lógica para criar o carro, como salvar os dados ou adicioná-los em um banco de dados
    console.log(`Carro adicionado: ${modelo}, ${marca}, ${placa}, ${cor}, ${ano}`);

    // Após adicionar o carro, escondemos o formulário novamente
    document.getElementById('formAdicionarCarroContainer').style.display = 'none';

    // Aqui você poderia atualizar a lista de carros no select ou realizar outra ação
    const carroSelect = document.getElementById('carroSelect');
    const newOption = document.createElement('option');
    newOption.value = placa;
    newOption.textContent = `${modelo} - ${marca}`;
    carroSelect.appendChild(newOption);
}

// Função para reservar uma vaga
function reservarVaga() {
    const carroSelect = document.getElementById('carroSelect');
    const vagaSelect = document.getElementById('vagaSelect');
    
    const carro = carroSelect.value;
    const vaga = vagaSelect.value;
    
    if (!carro || !vaga) {
        alert('Por favor, selecione um carro e uma vaga!');
        return;
    }

    console.log(`Carro ${carro} foi relacionado à vaga ${vaga}`);

    // Simular exibição de reserva
    const reservasContainer = document.getElementById('reservasContainer');
    const reservaCard = document.createElement('div');
    reservaCard.classList.add('reserva-card');
    reservaCard.innerHTML = `
        <div class="reserva-info">
            <h3>Carro: ${carro}</h3>
            <p><strong>Vaga:</strong> ${vaga}</p>
        </div>
    `;
    reservasContainer.appendChild(reservaCard);

    // Limpar os selects após reserva
    carroSelect.value = '';
    vagaSelect.value = '';
}
// Função para mostrar ou esconder o card de "Adicionar Novo Carro"
function toggleAddCarCard() {
    const card = document.getElementById('cardAdicionarNovoCarro');
    card.style.display = card.style.display === 'block' ? 'none' : 'block';
}

// Função para criar um novo carro (ajuste conforme a lógica do seu sistema)
function criarCarro() {
    // Lógica para adicionar um novo carro
    alert("Carro adicionado com sucesso!");
    toggleAddCarCard();  // Fecha o card após o envio
}
