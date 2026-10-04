# late-fee-interest

Aplicação que calcula os juros de um valor vencido, a partir da data de vencimento e da data de hoje.

A multa é de **2,5% ao dia** sobre o valor original, contando só os dias em atraso.

## Como o cálculo funciona

```
diasAtraso = hoje - dataVencimento (se for negativo, considera 0)
juros = valor x 2,5% x diasAtraso
valorAtualizado = valor + juros
```

Exemplo: R$ 1.000,00 com 2 dias de atraso gera R$ 50,00 de juros e valor atualizado de R$ 1.050,00.

## Regras

- Se o vencimento for hoje ou uma data futura, não há juros.
- Os juros são simples: 2,5% do valor original por dia de atraso.
- O valor é obrigatório e deve ser maior que zero.
- A data de vencimento é obrigatória, no formato `AAAA-MM-DD`.
- A "data de hoje" é a data atual no horário de Brasília.

## Pré-requisitos

- Java 21 instalado.
- Não precisa instalar Maven: o projeto já vem com tudo que precisa para rodar.

## Como executar

Abra o terminal na pasta do projeto e rode:

```bash
.\mvnw.cmd spring-boot:run
```

A aplicação sobe em `http://localhost:8083`.

Caso já tenha o java 21 instalado no seu computador, execute esse comando para trocar a versão e executar a aplicação

```bash
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path
java -version
.\mvnw.cmd clean spring-boot:run
```

## Como rodar os testes

```bash
.\mvnw.cmd test
```

## Como testar (Postman ou Insomnia)

Selecione o corpo como **JSON** (raw).

### Calcular juros

**POST** `http://localhost:8083/api/juros`

Corpo:

```json
{
  "valor": 1000.00,
  "dataVencimento": "2026-10-01"
}
```

Resposta, supondo que hoje seja `2026-10-03` (2 dias de atraso):

```json
{
  "valorOriginal": 1000.00,
  "dataVencimento": "2026-10-01",
  "dataCalculo": "2026-10-03",
  "diasAtraso": 2,
  "percentualDiario": 0.025,
  "juros": 50.00,
  "valorAtualizado": 1050.00
}
```

Sem atraso (vencimento hoje ou no futuro):

```json
{
  "valorOriginal": 1000.00,
  "dataVencimento": "2026-12-31",
  "dataCalculo": "2026-10-03",
  "diasAtraso": 0,
  "percentualDiario": 0.025,
  "juros": 0.00,
  "valorAtualizado": 1000.00
}
```

Se o valor estiver faltando ou for zero/negativo, ou se a data estiver faltando ou em formato errado, a resposta é 400 Bad Request.
