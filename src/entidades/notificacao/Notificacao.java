package entidades.notificacao;

import entidades.dados.*;
import enums.doenca.Doencas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class Notificacao {
    public static List<Notificacao> todasAsNotificacoes = new ArrayList<>();

    protected static Integer contadorCodigo = 1;
    protected Integer codigo;
    protected DadosGerais dadosGerais;
    protected DadosIndividuais dadosIndividuais;
    protected DadosResidencias dadosResidenciais;
    protected DadosEpidemiologicos dadosEpidemiologicos;
    protected DadosTratamento dadosTratamento;
    protected entidades.dados.Coclusão conclusaoEncerramento; // usando a classe existente (com acento)


    public Notificacao() {
        this.dadosGerais = new DadosGerais();
        this.dadosIndividuais = new DadosIndividuais();
        this.dadosResidenciais = new DadosResidencias();
        this.dadosEpidemiologicos = new DadosEpidemiologicos();
        this.dadosTratamento = new DadosTratamento();
        this.conclusaoEncerramento = new Coclusão();
    }

    public abstract void registrarNotificacao(Scanner sc);


    public static Notificacao registrar(Notificacao n) {
        Objects.requireNonNull(n, "Notificacao não pode ser nula");

        // validações de campos principais
        if (n.getDadosGerais() == null)
            throw new IllegalArgumentException("DadosGerais obrigatórios");
        if (n.getDadosIndividuais() == null)
            throw new IllegalArgumentException("DadosIndividuais obrigatórios");
        if (n.getDadosResidenciais() == null)
            throw new IllegalArgumentException("DadosResidenciais obrigatórios");

        if (n.getDadosGerais().getDoenca() == null)
            throw new IllegalArgumentException("Tipo de agravo (doenca) obrigatório");
        if (n.getDadosGerais().getDataNotificacao() == null)
            throw new IllegalArgumentException("Data de notificacao obrigatória");
        if (n.getDadosGerais().getDataNotificacao().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data de notificacao nao pode ser futura");

        String nome = n.getDadosIndividuais().getNome();
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do paciente obrigatório");

        String bairro = n.getDadosResidenciais().getBairro();
        if (bairro == null || bairro.trim().isEmpty())
            throw new IllegalArgumentException("Bairro obrigatório");

        // atribui código e armazena
        n.setCodigo(contadorCodigo++);
        todasAsNotificacoes.add(n);
        return n;
    }

    public static void consultarNotificacoes(Scanner sc, Doencas tipoAgravo) {
        List<Notificacao> notificacoesDoTipo = todasAsNotificacoes.stream()
                .filter(n -> n.getDadosGerais().getDoenca() == tipoAgravo)
                .collect(Collectors.toList());

        if (notificacoesDoTipo.isEmpty()) {
            System.out.println("Não há notificações de " + tipoAgravo + " registradas.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n====== CONSULTAR NOTIFICAÇÕES DE " + tipoAgravo + " ======");
            System.out.println("1 - Consultar pelo nome do paciente");
            System.out.println("2 - Consultar por bairro");
            System.out.println("3 - Consultar por período");
            System.out.println("4 - Consultar todos os casos de " + tipoAgravo);
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: {
                    String nome;
                    while (true) {
                        System.out.print("Digite o nome do paciente: ");
                        nome = sc.nextLine();
                        if (nome.isEmpty()) {
                            System.out.println("O campo Nome do paciente é obrigatório, tente novamente!");
                        } else {
                            break;
                        }
                    }

                    System.out.println("\nResultados da consulta pelo nome: " + nome);
                    int encontrados = 0;
                    for (Notificacao n : notificacoesDoTipo) {
                        if (n.getDadosIndividuais().getNome().equalsIgnoreCase(nome)) {
                            System.out.println("-> Cód: " + n.getCodigo()
                                    + " | Paciente: " + n.getDadosIndividuais().getNome()
                                    + " | Bairro: " + n.getDadosResidenciais().getBairro()
                                    + " | Data Notificação: " + n.getDadosGerais().getDataNotificacao());
                            encontrados++;
                        }
                    }
                    if (encontrados == 0) {
                        System.out.println("Nenhuma notificação encontrada para o paciente: " + nome);
                    } else {
                        System.out.println("Total de notificações encontradas: " + encontrados);
                    }
                    break;
                }
                case 2: {
                    String bairro;
                    while (true) {
                        System.out.print("Digite o bairro: ");
                        bairro = sc.nextLine();

                        if (bairro.isEmpty()) {
                            System.out.println("O campo Bairro é obrigatório, tente novamente!");
                        } else {
                            break;
                        }
                    }

                    System.out.println("\nResultados da consulta pelo bairro: " + bairro);
                    int encontrados = 0;
                    for (Notificacao n : notificacoesDoTipo) {
                        if (n.getDadosResidenciais().getBairro().equalsIgnoreCase(bairro)) {
                            System.out.println("-> Cód: " + n.getCodigo()
                                    + " | Paciente: " + n.getDadosIndividuais().getNome()
                                    + " | Bairro: " + n.getDadosResidenciais().getBairro()
                                    + " | Data Notificação: " + n.getDadosGerais().getDataNotificacao());
                            encontrados++;
                        }
                    }
                    if (encontrados == 0) {
                        System.out.println("Nenhuma notificação encontrada para o bairro: " + bairro);
                    } else {
                        System.out.println("Total de notificações encontradas: " + encontrados);
                    }
                    break;
                }
                case 3: {
                    LocalDate inicio;
                    while (true) {
                        try {
                            System.out.print("Digite a data inicial (AAAA-MM-DD): ");
                            inicio = LocalDate.parse(sc.nextLine());
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido. Use AAAA-MM-DD. Tente novamente!");
                        }
                    }
                    LocalDate fim;
                    while (true) {
                        try {
                            System.out.print("Digite a data final (AAAA-MM-DD): ");
                            fim = LocalDate.parse(sc.nextLine());
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido. Use AAAA-MM-DD. Tente novamente!");
                        }
                    }

                    System.out.println("\nResultados da consulta por período: " + inicio + " a " + fim);
                    int encontrados = 0;
                    for (Notificacao n : notificacoesDoTipo) {
                        LocalDate data = n.getDadosGerais().getDataNotificacao();
                        if (!data.isBefore(inicio) && !data.isAfter(fim)) {
                            System.out.println("-> Cód: " + n.getCodigo()
                                    + " | Paciente: " + n.getDadosIndividuais().getNome()
                                    + " | Bairro: " + n.getDadosResidenciais().getBairro()
                                    + " | Data Notificação: " + data);
                            encontrados++;
                        }
                    }
                    if (encontrados == 0) {
                        System.out.println("Nenhuma notificação encontrada no período especificado.");
                    } else {
                        System.out.println("Total de notificações encontradas: " + encontrados);
                    }
                    break;
                }
                case 4: {
                    System.out.println("\nResultados da consulta por agravo: " + tipoAgravo);
                    int encontrados = 0;
                    for (Notificacao n : notificacoesDoTipo) {
                        System.out.println("-> Cód: " + n.getCodigo()
                                + " | Paciente: " + n.getDadosIndividuais().getNome()
                                + " | Bairro: " + n.getDadosResidenciais().getBairro()
                                + " | Data Notificação: " + n.getDadosGerais().getDataNotificacao());
                        encontrados++;
                    }
                    System.out.println("Total de notificações encontradas: " + encontrados);
                    break;
                }
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void gerarRelatorio(Doencas tipoAgravo) {
        List<Notificacao> notificacoesDoTipo = todasAsNotificacoes.stream()
                .filter(n -> n.getDadosGerais().getDoenca() == tipoAgravo)
                .collect(Collectors.toList());

        System.out.println("\n=== RELATÓRIO ESTATÍSTICO DE " + tipoAgravo + " ===");

        if (notificacoesDoTipo.isEmpty()) {
            System.out.println("Não há notificações de " + tipoAgravo + " para gerar relatório.");
            return;
        }

        int totalAgravo;
        int totalSexoM = 0;
        int totalSexoF = 0;
        int criancaAdolescente = 0;
        int adulto = 0;
        int idoso = 0;
        int totalBranca = 0;
        int totalPreta = 0;
        int totalParda = 0;
        int totalAmarela = 0;
        int totalIndigena = 0;
        int totalNaoInformadoRaca = 0;
        int totalA = 0;
        int totalFI = 0;
        int totalFC = 0;
        int totalMI = 0;
        int totalMC = 0;
        int totalSI = 0;
        int totalSC = 0;
        int totalPG = 0;
        int totalM = 0;
        int totalD = 0;
        int totalNI = 0;

        System.out.println("\nTotal de notificações por agravo:");
        totalAgravo = notificacoesDoTipo.size();
        System.out.println(tipoAgravo + ": " + totalAgravo);

        System.out.println("\nTotal de notificações por bairro:");
        for (Notificacao n : notificacoesDoTipo) {
            System.out.println("-> " + n.getDadosResidenciais().getBairro());
        }

        System.out.println("\nTotal de notificações por faixa etária: ");
        for (Notificacao n : notificacoesDoTipo) {
            if (n.getDadosIndividuais().getIdade() >= 0 && n.getDadosIndividuais().getIdade() <= 18) {
                criancaAdolescente++;
            } else if (n.getDadosIndividuais().getIdade() > 18 && n.getDadosIndividuais().getIdade() <= 64) {
                adulto++;
            } else {
                idoso++;
            }
        }

        System.out.println("Total de crianças e adolescentes: " + criancaAdolescente);
        System.out.println("Total de Adultos: " + adulto);
        System.out.println("Total de idosos: " + idoso);

        System.out.println("\nTotal de notificações por sexo:");
        for (Notificacao n : notificacoesDoTipo) {
            if (n.getDadosIndividuais().getSexo() != null) {
                switch (n.getDadosIndividuais().getSexo()) {
                    case M:
                        totalSexoM++;
                        break;
                    case F:
                        totalSexoF++;
                        break;
                }
            }
        }
        System.out.println("Masculino: " + totalSexoM);
        System.out.println("Feminino: " + totalSexoF);

        System.out.println("\nTotal de notificações por raça/cor:");
        for (Notificacao n : notificacoesDoTipo) {
            if (n.getDadosIndividuais().getRacaCor() != null) {
                switch (n.getDadosIndividuais().getRacaCor()) {
                    case Branca -> totalBranca++;
                    case Preta -> totalPreta++;
                    case Parda -> totalParda++;
                    case Amarela -> totalAmarela++;
                    case Indígena -> totalIndigena++;
                    case Ignorado -> totalNaoInformadoRaca++;
                }
            }
        }
        System.out.println("Branca: " + totalBranca);
        System.out.println("Preta: " + totalPreta);
        System.out.println("Parda: " + totalParda);
        System.out.println("Amarela: " + totalAmarela);
        System.out.println("Indígena: " + totalIndigena);
        System.out.println("Não informado: " + totalNaoInformadoRaca);

        System.out.println("\nTotal de notificações por escolaridade:");
        for (Notificacao n : notificacoesDoTipo) {
            if (n.getDadosIndividuais().getEscolaridade() != null) {
                switch (n.getDadosIndividuais().getEscolaridade()) {
                    case Ensino_Fundamental_Incompleto -> totalFI++;
                    case Ensino_Fundamental_Completo -> totalFC++;
                    case Ensino_Medio_Incompleto -> totalMI++;
                    case Ensino_Medio_Completo -> totalMC++;
                    case Educação_Superior_Incompleta -> totalSI++;
                    case Educação_Superior_Completa -> totalSC++;
                }
            }
        }
        System.out.println("Analfabeto: " + totalA);
        System.out.println("Fundamental incompleto: " + totalFI);
        System.out.println("Fundamental completo: " + totalFC);
        System.out.println("Médio incompleto: " + totalMI);
        System.out.println("Médio completo: " + totalMC);
        System.out.println("Superior incompleto: " + totalSI);
        System.out.println("Superior completo: " + totalSC);
        System.out.println("Pós graduação: " + totalPG);
        System.out.println("Mestrado: " + totalM);
        System.out.println("Doutorado: " + totalD);
        System.out.println("Não informado: " + totalNI);

        System.out.println("\nRelatório gerado!");
    }

    public static void setContadorCodigo(Integer contador) {
        contadorCodigo = contador;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public DadosGerais getDadosGerais() {
        return dadosGerais;
    }

    public void setDadosGerais(DadosGerais dadosGerais) {
        this.dadosGerais = dadosGerais;
    }

    public DadosIndividuais getDadosIndividuais() {
        return dadosIndividuais;
    }

    public void setDadosIndividuais(DadosIndividuais dadosIndividuais) {
        this.dadosIndividuais = dadosIndividuais;
    }

    public DadosResidencias getDadosResidenciais() {
        return dadosResidenciais;
    }

    public void setDadosResidenciais(DadosResidencias dadosResidenciais) {
        this.dadosResidenciais = dadosResidenciais;
    }

    public DadosEpidemiologicos getDadosEpidemiologicos() {
        return dadosEpidemiologicos;
    }

    public void setDadosEpidemiologicos(DadosEpidemiologicos dadosEpidemiologicos) {
        this.dadosEpidemiologicos = dadosEpidemiologicos;
    }

    public DadosTratamento getDadosTratamento() {
        return dadosTratamento;
    }

    public void setDadosTratamento(DadosTratamento dadosTratamento) {
        this.dadosTratamento = dadosTratamento;
    }

    public entidades.dados.Coclusão getConclusaoEncerramento() {
        return conclusaoEncerramento;
    }

    public void setConclusaoEncerramento(entidades.dados.Coclusão conclusaoEncerramento) {
        this.conclusaoEncerramento = conclusaoEncerramento;
    }
}