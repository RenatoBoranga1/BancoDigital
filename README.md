# BancoDigital

Aplicação Java de console criada para praticar orientação a objetos por meio de contas, operações e extratos bancários simulados.

## Contexto

O projeto registra uma etapa anterior da minha evolução em Java. Ele não é uma API, não possui persistência e não representa um sistema bancário pronto para produção.

This project represents an earlier stage of my Java development journey. A new production-oriented banking backend using Java and Spring Boot is planned as a separate project.

## Funcionalidades implementadas

- cadastro de clientes em memória
- autenticação simples por nome e senha
- criação de conta corrente e conta poupança
- consulta de saldo
- depósitos e saques
- transferência da conta corrente para a poupança
- solicitação e simulação de empréstimo com juros simples de 5%
- histórico e impressão de extratos

## Fundamentos de Java demonstrados

- abstração com a classe `Conta`
- herança em `ContaCorrente` e `ContaPoupanca`
- polimorfismo pelo contrato `IConta`
- encapsulamento de cliente, saldo e histórico
- composição entre cliente, contas e transações
- coleções com `List`
- menu interativo com `Scanner`

## Estrutura

```text
src/
├── SistemaBanco.java    # entrada da aplicação e menus
├── Cliente.java         # dados e autenticação em memória
├── IConta.java          # contrato das operações bancárias
├── Conta.java           # comportamento compartilhado
├── ContaCorrente.java   # extrato da conta corrente
├── ContaPoupanca.java   # extrato da poupança
├── Transacao.java       # registro do histórico
└── Banco.java           # agregado simples de contas
```

## Como executar

### Pré-requisito

- JDK 17 ou superior

Na raiz do projeto:

```bash
javac -d out src/*.java
java -cp out SistemaBanco
```

O diretório `out/` é gerado localmente e deve permanecer fora do Git.

## Testes

O projeto ainda não possui suíte de testes automatizados nem ferramenta de build. A validação atual é feita pela compilação e pelo fluxo interativo no console.

## Limitações conhecidas

- dados existem somente durante a execução
- senhas permanecem em texto puro na memória do processo
- valores monetários usam `double`, inadequado para um backend financeiro real
- não há controle transacional, concorrência, idempotência ou auditoria persistente
- empréstimos usam uma regra didática simplificada

Essas limitações são mantidas visíveis para não apresentar o projeto acadêmico como uma solução bancária de produção. O backend bancário futuro deverá nascer em outro repositório, com regras e arquitetura próprias.

## Autor

Renato Boranga
