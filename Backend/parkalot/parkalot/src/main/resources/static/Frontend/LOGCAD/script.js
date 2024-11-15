function verificarCPF(client_cpf) {
    return $.ajax({
        method: "GET",
        url: "verificarCPF",
        data: { cpf: client_cpf }
    });
}

function verificarEmail(client_mail) {
    return $.ajax({
        method: "GET",
        url: "verificarEmail",
        data: { email: client_mail }
    });
}

function salvarUsuario() {

    var client_name = $("#nome").val();
    var client_cpf = $("#cpf").val();
    var client_mail = $("#email").val();
    var client_phone = $("#telefone").val();
    var client_sex = $("#sexo").val();
    var client_age = $("#dataDeNascimento").val();

    //Função para calcular a idade do cliente

    function calcularIdade() {
        const hoje = new Date();
        const inputidade = new Date(client_age);

        let anos = hoje.getFullYear() - inputidade.getFullYear();
        const diferencaMeses = hoje.getMonth() - inputidade.getMonth();
        if (diferencaMeses < 0) {
            anos--;
        }

        return anos;
    }

    //Avisos para caso o cliente não informe algum dado

    if (client_name == "") {
        alert("Informe o nome");
        return;
    }

    if (client_cpf == "") {
        alert("Informe o CPF");
        return;
    }

    if (client_mail == "") {
        alert("Informe o email");
        return;
    }

    if (client_phone == "") {
        alert("Informe o telefone");
        return;
    }

    if (client_age == "") {
        alert("Informe a idade");
        return;
    }

    const idade = calcularIdade(client_age);

    if (idade < 0 || idade > 120) {
        alert("Idade inválida");
        return;
    }

    verificarCPF(client_cpf)
        .then(function (exists) {
            if (exists) {
                alert("CPF já cadastrado");
                return false;
            } else {
                verificarEmail(client_mail)
                    .then(function (exists) {
                        if (exists) {
                            alert("E-mail já cadastrado");
                            return false;
                        } else {
                            return $$.ajax({
                                method: "POST",
                                url: "salvar",
                                data: JSON.stringify({
                                    nome: client_name,
                                    cpf: client_cpf,
                                    email: client_mail,
                                    telefone: client_phone,
                                    dataDeNascimento: client_age,
                                    sexo: client_sex
                                }),
                                contentType: "application/json; charset=utf-8",
                                success: function (response) {
                                    $("#id").val(response.id);
                                    alert("Cadastro realizado com sucesso");
                                },
                                error: function (xhr, status, error) {
                                    console.error("Erro ao realizar o cadastro:", status, error, xhr.responseText);
                                    alert("Erro ao realizar o cadastro: " + xhr.responseText + "\nStatus: " + status + "\nErro: " + error);
                                }
                            });
                        }
                    })
            }
        })
}
