# 🎬 Golden Raspberry Awards API

API RESTful para leitura e análise dos vencedores da categoria **Pior Filme** do Golden Raspberry Awards, conforme desafio técnico proposto para o processo de seleção da Outsera.

[![Java](https://img.shields.io/badge/Java-21-blue?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![Build](https://img.shields.io/badge/Build-Maven-blue?logo=apache-maven)](https://maven.apache.org/)
[![Test Coverage](https://img.shields.io/badge/Testes-Integração-lightgrey)]()
[![Status](https://img.shields.io/badge/Status-Completo-success)]()

---

## 📦 Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3.5.3**
- **Maven Wrapper**
- **Banco de dados H2 (em memória)**
- **Leitura de arquivo CSV na inicialização**
- **Testes de Integração com MockMvc**

---

## 🚀 Como executar o projeto

### ✅ Pré-requisitos:

- Java 21+
- Git (opcional)
- Terminal Linux/Mac ou WSL (Windows Subsystem for Linux)

### ▶️ Executando:

```bash
# Clone o repositório
HTTPS
git clone https://github.com/leonardocr/golden-awards-challenge.git

SSH
git clone git@github.com:leonardocr/golden-awards-challenge.git

cd golden-awards-challenge

# Rode a aplicação
./mvnw spring-boot:run
```

> A aplicação estará disponível em:
> [http://localhost:8080](http://localhost:8080)

---

## 📥 Endpoint principal

### `GET /awards/intervals`

Retorna os **produtores** com:
- 🏆 **Menor intervalo** entre duas vitórias consecutivas (`min`)
- 🕰️ **Maior intervalo** entre duas vitórias consecutivas (`max`)

### ✅ Exemplo de resposta:

```json
{
  "min": [
    {
      "producer": "Joel Silver",
      "interval": 1,
      "previousWin": 1990,
      "followingWin": 1991
    }
  ],
  "max": [
    {
      "producer": "Matthew Vaughn",
      "interval": 13,
      "previousWin": 2002,
      "followingWin": 2015
    }
  ]
}
```

### 🧪 Testar com `curl`:

```bash
curl http://localhost:8080/awards/intervals
```

---

## 🧪 Rodando os testes de integração

Para validar o comportamento da API com base nos dados do CSV:

```bash
./mvnw test
```

Ou para rodar apenas os testes de integração (exemplo):

```bash
./mvnw -Dtest=AwardIntegrationTest test
```

---

## 🗂️ Organização dos pacotes

| Camada               | Pacote        |
|----------------------|---------------|
| Controller           | `controller`  |
| Service              | `services`    |
| Repository           | `repository`  |
| DTOs                 | `dto`         |
| Entidade             | `entity`      |
| Inicialização (CSV)  | `init`        |
| Testes de Integração | `integration` |

---

## 📂 CSV e banco

- O arquivo `movielist.csv` está em: `src/main/resources/movielist.csv`
- Os dados são carregados automaticamente no banco H2 em memória na inicialização da aplicação (`StartupRunner`)

---

## ✅ Requisitos atendidos

- [x] Leitura de dados a partir de CSV
- [x] Banco de dados H2 com inserção automática dos dados
- [x] API RESTful conforme o nível 2 da maturidade de Richardson
- [x] Retorno correto de produtores com maior e menor intervalo entre prêmios
- [x] Testes de integração cobrindo a resposta da API

---

## 💡 Observações finais

O projeto foi construído com foco em legibilidade, separação de responsabilidades (seguindo princípios **SOLID**) e facilidade de manutenção.  
A estrutura está preparada para evoluir com novos endpoints ou repositórios de dados.
