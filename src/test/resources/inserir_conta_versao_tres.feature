#language: pt
@selenium
Funcionalidade: Cadastro de contas
  Como um usuario
  Gostaria de cadastrar contas
  Para que eu possa distribuir meu dinheiro de uma forma mais organizada

  Contexto:
    Dado que estou acessando a aplicacao
    Quando inform o usuario "leanstorm@gmail.com"
    E a senha "qsc258"
    E seleciono entrar
    Entao visualizo a pagina inicial
    Quando seleciono Contas
    E seleciono Adicionar

    Esquema do Cenario: Deve validar regras cadastro contas
      Quando informo a conta <conta>
      E seleciono Salvar
      Entao recebo a mensagem <mensagem>

      Exemplos:
        | conta            | mensagem                             |
        | "Conta de Teste" | "Conta adicionada com sucesso!"      |
        | ""               | "Informe o nome da conta"            |
        | "Conta de Teste" | "Já existe uma conta com esse nome!" |
