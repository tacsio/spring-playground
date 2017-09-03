#language: pt
Funcionalidade: Um simples hello world
  Eu quero que meu usuário interaja com o sistema através de APIs REST

  Esquema do Cenário: Receber resposta correta de acordo com quem pergunta
    Dado que <caller> irá interagir
    Quando eu faço a requisição
    Então eu deveria receber um HTTP status <status>
    E a resposta deveria ter a mensagem <message>

    Exemplos: 
      | caller     | status | message         |
      | "Tarcisio" |    200 | "Olá, Tarcisio" |
      | "Mariane"  |    200 | "Olá, Mariane"  |

  Cenário: Um android interage com o sistema
    Dado que quem interage é o "BB8"
    Quando eu faço uma requisição
    Então eu deveria receber a mensagem "wawawawi"
