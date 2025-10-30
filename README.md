# Atividade Prática - Sistema de Notificação de Agravos de Saúde Pública
## Descrição
Esta atividade tem como objetivo desenvolver um sistema simples para notificar agravos de saúde pública.
O sistema deve permitir o registro e geração de relatórios das notificações abaixo:
- [Hanseniase](https://portalsinan.saude.gov.br/hanseniase)
    - [Ficha de Notificação/Investigação de Hanseníase](http://portalsinan.saude.gov.br/images/documentos/Agravos/Hanseniase/Hanseniase_v5.pdf)
- [Tuberculose](https://portalsinan.saude.gov.br/tuberculose)
    - [Ficha de Notificação/Investigação de Tuberculose](http://portalsinan.saude.gov.br/images/documentos/Agravos/Tuberculose/Tuberculose_v5.pdf)
- [Malária](https://portalsinan.saude.gov.br/malaria)
    - [Ficha de Notificação/Investigação de Malária](http://portalsinan.saude.gov.br/images/documentos/Agravos/Malaria/Malaria_v5.pdf)

Detalhes sobre a entrega da atividade podem ser encontrados no arquivo [ENTREGA](ENTREGA.md).

## Funcionalidades
### Registrar Notificações
O sistema deve permitir o registro de notificações com base na ficha de notificação/investigação disponível nos links acima.
### Consultar Notificações
O sistema deve permitir consultas das notificações registradas com base nos seguintes critérios:
- Consultar pelo nome do paciente.
- Listar todas as notificações de um determinado bairro.
- Listar todas as notificações num determinado período.
- Listar todas as notificações de um determinado agravo.
### Gerar Relatório
O sistema deve gerar relatórios com os seguintes dados:
- Total de notificações por agravo.
- Total de notificações por bairro.
- Total de notificações por mês/ano.
- Total de notificações por faixa etária.
- Total de notificações por sexo.
- Total de notificações por raça/cor.
- Total de notificações por escolaridade.
## Especificações
- A implementação deve ser feita em Java 25.
- O sistema deverá disponibilizar uma interface (menu) de linha de comando (CLI) para interação com o usuário.
- Os dados deverão ser armazenados em arquivos de texto.
- Seguir as boas práticas de programação orientada a objetos.
- Utilizar todos os conceitos aprendidos até então na disciplina.
## Tutoriais
- [Como usar o Git e Github na prática](https://github.com/rafaballerini/GitTutorial)
- [Tutorial de Git e GitHub – controle de versão para iniciantes](https://www.freecodecamp.org/portuguese/news/tutorial-de-git-e-github-controle-de-versao-para-iniciantes/)
- [Como usar Git: tutorial completo para iniciantes](https://www.hostinger.com/br/tutoriais/tutorial-do-git-basics-introducao)
- [Arquivos — Java](https://medium.com/@pedro.vaf/arquivos-java-359156a1bf03)
- [Leitura e escrita de arquivos de texto em Java](https://www.devmedia.com.br/leitura-e-escrita-de-arquivos-de-texto-em-java/25529)
- [Manipulando Arquivo Txt com Java](https://mballem.com/post/manipulando-arquivo
