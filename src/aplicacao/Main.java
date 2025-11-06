package aplicacao;

import entidades.dados.*;
import entidades.notificacao.Notificacao;
import entidades.notificacao.RelatorioNotificacoes;
import enums.doenca.Doencas;
import enums.paciente.Gestante;
import enums.paciente.Escolaridade;
import enums.paciente.Raca;
import enums.paciente.Sexo;
import enums.paciente.Zona;

import java.io.IOException;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE NOTIFICAÇÕES DE AGRAVOS ===");
            System.out.println("1 - Registrar nova notificação");
            System.out.println("2 - Consultar notificações por agravo");
            System.out.println("3 - Relatórios resumo");
            System.out.println("4 - Listar todas as notificações");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String linha = sc.nextLine().trim();
            try {
                opcao = Integer.parseInt(linha.isEmpty() ? "-1" : linha);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    registrarNotificacaoInterativa(sc);
                    break;
                case 2:
                    Doencas doenca = escolherDoenca(sc);
                    if (doenca != null) {
                        Notificacao.consultarNotificacoes(sc, doenca);
                    }
                    break;
                case 3:
                    RelatorioNotificacoes.imprimirRelatoriosResumo();
                    break;
                case 4:
                    listarTodasNotificacoes();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close();
    }

    // Classe interna concreta para permitir instanciar Notificacao
    public static class SimpleNotificacao extends Notificacao {
        @Override
        public void registrarNotificacao(Scanner sc) {
            // não utilizada; o registro é feito estaticamente via Notificacao.registrar
        }
    }

    // ---------------------------- Fluxos de interação ----------------------------
    private static void registrarNotificacaoInterativa(Scanner sc) {
        System.out.println("\n--- REGISTRAR NOVA NOTIFICAÇÃO ---");

        Doencas doenca = escolherDoenca(sc);
        if (doenca == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        DadosGerais dg = lerDadosGerais(sc, doenca);
        DadosIndividuais di = lerDadosIndividuais(sc);
        DadosResidencias dr = lerDadosResidencias(sc);

        // campos opcionais (leitura simplificada)
        DadosEpidemiologicos de = null;
        System.out.print("Deseja preencher dados epidemiológicos? (s/N): ");
        String resp = sc.nextLine().trim();
        if (resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("sim")) {
            de = lerDadosEpidemiologicos(sc);
        }

        DadosTratamento dt = null;
        System.out.print("Deseja preencher dados de tratamento? (s/N): ");
        resp = sc.nextLine().trim();
        if (resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("sim")) {
            dt = lerDadosTratamento(sc);
        }

        SimpleNotificacao n = new SimpleNotificacao();
        n.setDadosGerais(dg);
        n.setDadosIndividuais(di);
        n.setDadosResidenciais(dr);
        n.setDadosEpidemiologicos(de);
        n.setDadosTratamento(dt);

        try {
            Notificacao.registrar(n);
            System.out.println("Notificação registrada com sucesso. Código: " + n.getCodigo());
        } catch (IllegalArgumentException iae) {
            System.out.println("Erro ao registrar notificação: " + iae.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao registrar notificacao: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarTodasNotificacoes() {
        List<Notificacao> lista = Notificacao.todasAsNotificacoes;
        if (lista.isEmpty()) {
            System.out.println("Nenhuma notificação cadastrada.");
            return;
        }
        System.out.println("\n--- LISTA DE NOTIFICAÇÕES ---");
        for (Notificacao n : lista) {
            System.out.println("Cód: " + n.getCodigo()
                    + " | Doença: " + (n.getDadosGerais() != null ? n.getDadosGerais().getDoenca() : "N/A")
                    + " | Paciente: " + (n.getDadosIndividuais() != null ? n.getDadosIndividuais().getNome() : "N/A")
                    + " | Bairro: " + (n.getDadosResidenciais() != null ? n.getDadosResidenciais().getBairro() : "N/A")
                    + " | Data Notificação: " + (n.getDadosGerais() != null ? n.getDadosGerais().getDataNotificacao() : "N/A")
            );
        }
    }

    // ---------------------------- Leitura de grupos de dados ----------------------------
    private static DadosGerais lerDadosGerais(Scanner sc, Doencas doenca) {
        System.out.println("\nPreenchendo Dados Gerais (obrigatórios):");
        LocalDate dataNotificacao = null;
        while (dataNotificacao == null) {
            System.out.print("Data de notificação (AAAA-MM-DD): ");
            String s = sc.nextLine().trim();
            try {
                dataNotificacao = LocalDate.parse(s);
                if (dataNotificacao.isAfter(LocalDate.now())) {
                    System.out.println("Data de notificação não pode ser futura.");
                    dataNotificacao = null;
                }
            } catch (DateTimeException e) {
                System.out.println("Formato inválido. Use AAAA-MM-DD.");
            }
        }

        System.out.print("UF (sigla): ");
        String uf = lerStringObrigatoria(sc);

        System.out.print("Município de notificação: ");
        String muni = lerStringObrigatoria(sc);

        System.out.print("UBS (unidade básica): ");
        String ubs = lerString(sc);

        System.out.print("Data do diagnóstico (opcional, AAAA-MM-DD ou Enter): ");
        String dataDiagStr = sc.nextLine().trim();
        LocalDate dataDiag = null;
        if (!dataDiagStr.isEmpty()) {
            try {
                dataDiag = LocalDate.parse(dataDiagStr);
            } catch (DateTimeException e) {
                System.out.println("Data diagnóstico inválida; será deixada em branco.");
            }
        }

        return new DadosGerais(doenca, dataNotificacao, uf, muni, ubs, dataDiag);
    }

    private static DadosIndividuais lerDadosIndividuais(Scanner sc) {
        System.out.println("\nPreenchendo Dados Individuais (obrigatórios):");
        System.out.print("Nome do paciente: ");
        String nome = lerStringObrigatoria(sc);

        System.out.print("Data de nascimento (AAAA-MM-DD ou Enter se desconhecida): ");
        String nascStr = sc.nextLine().trim();
        LocalDate dtNasc = null;
        if (!nascStr.isEmpty()) {
            try {
                dtNasc = LocalDate.parse(nascStr);
            } catch (DateTimeException e) {
                System.out.println("Data nascimento inválida; será deixada em branco.");
                dtNasc = null;
            }
        }

        int idade = -1;
        while (idade < 0) {
            System.out.print("Idade (em anos): ");
            String s = sc.nextLine().trim();
            try {
                idade = Integer.parseInt(s);
                if (idade < 0) System.out.println("Idade deve ser >= 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Informe um número inteiro para idade.");
                idade = -1;
            }
        }

        System.out.println("Sexo:");
        Sexo sexo = escolherEnum(sc, Sexo.values());

        System.out.println("Gestante (apenas se aplicável):");
        Gestante gestante = escolherEnumOpcional(sc, Gestante.values());

        System.out.println("Raça/Cor:");
        Raca raca = escolherEnumOpcional(sc, Raca.values());

        System.out.println("Escolaridade:");
        Escolaridade escolaridade = escolherEnumOpcional(sc, Escolaridade.values());

        System.out.print("Cartão SUS (opcional): ");
        String cartao = lerString(sc);

        System.out.print("Nome da mãe (opcional): ");
        String nomeMae = lerString(sc);

        DadosIndividuais di = new DadosIndividuais();
        di.setNome(nome);
        di.setDataNascimento(dtNasc);
        di.setIdade(idade);
        di.setSexo(sexo);
        di.setGestante(gestante);
        di.setRacaCor(raca);
        di.setEscolaridade(escolaridade);
        di.setCartaoSUS(cartao);
        di.setNomeMae(nomeMae);
        return di;
    }

    private static DadosResidencias lerDadosResidencias(Scanner sc) {
        System.out.println("\nPreenchendo Dados Residenciais (obrigatórios):");
        System.out.print("UF (sigla): ");
        String uf = lerStringObrigatoria(sc);

        System.out.print("Município: ");
        String municipio = lerStringObrigatoria(sc);

        System.out.print("Distrito (opcional): ");
        String distrito = lerString(sc);

        System.out.print("Bairro: ");
        String bairro = lerStringObrigatoria(sc);

        System.out.print("Logradouro (opcional): ");
        String logradouro = lerString(sc);

        System.out.print("Número (opcional): ");
        String numero = lerString(sc);

        System.out.print("CEP (opcional): ");
        String cep = lerString(sc);

        System.out.print("Telefone (opcional): ");
        String telefone = lerString(sc);

        System.out.println("Zona:");
        Zona zona = escolherEnumOpcional(sc, Zona.values());

        DadosResidencias dr = new DadosResidencias();
        dr.setUf(uf);
        dr.setMunicipio(municipio);
        dr.setDistrito(distrito);
        dr.setBairro(bairro);
        dr.setLogradouro(logradouro);
        dr.setNumero(numero);
        dr.setCep(cep);
        dr.setTelefone(telefone);
        dr.setZona(zona);
        return dr;
    }

    private static DadosEpidemiologicos lerDadosEpidemiologicos(Scanner sc) {
        System.out.println("\nPreenchendo Dados Epidemiológicos (simplificado):");
        DadosEpidemiologicos de = new DadosEpidemiologicos();
        System.out.print("Data investigação (AAAA-MM-DD ou Enter): ");
        String s = sc.nextLine().trim();
        if (!s.isEmpty()) {
            try { de.setDataInvestigação(LocalDate.parse(s)); } catch (DateTimeException e) { System.out.println("Data inválida."); }
        }
        System.out.print("Ocupação (opcional): "); de.setOcupação(lerString(sc));
        return de;
    }

    private static DadosTratamento lerDadosTratamento(Scanner sc) {
        System.out.println("\nPreenchendo Dados de Tratamento (simplificado):");
        DadosTratamento dt = new DadosTratamento();
        System.out.print("Data início do tratamento (AAAA-MM-DD ou Enter): ");
        String s = sc.nextLine().trim();
        if (!s.isEmpty()) {
            try { dt.setDataInicioDoTratamento(LocalDate.parse(s)); } catch (DateTimeException e) { System.out.println("Data inválida."); }
        }
        return dt;
    }

    // ---------------------------- Utilitários ----------------------------
    private static String lerStringObrigatoria(Scanner sc) {
        String s;
        do {
            s = sc.nextLine().trim();
            if (s.isEmpty()) System.out.print("Campo obrigatório. Tente novamente: ");
        } while (s.isEmpty());
        return s;
    }

    private static String lerString(Scanner sc) {
        return sc.nextLine().trim();
    }

    private static Doencas escolherDoenca(Scanner sc) {
        System.out.println("Selecione o agravo/doença:");
        Doencas[] valores = Doencas.values();
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + " - " + valores[i]);
        }
        System.out.print("Escolha (número) ou 0 para cancelar: ");
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int opc = Integer.parseInt(s);
                if (opc == 0) return null;
                if (opc >= 1 && opc <= valores.length) return valores[opc - 1];
            } catch (NumberFormatException ignored) {}
            System.out.print("Opção inválida. Informe o número da doença ou 0 para cancelar: ");
        }
    }

    private static <T extends Enum<T>> T escolherEnum(Scanner sc, T[] valores) {
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + " - " + valores[i]);
        }
        System.out.print("Escolha (número): ");
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int opc = Integer.parseInt(s);
                if (opc >= 1 && opc <= valores.length) return valores[opc - 1];
            } catch (NumberFormatException ignored) {}
            System.out.print("Opção inválida. Escolha novamente: ");
        }
    }

    private static <T extends Enum<T>> T escolherEnumOpcional(Scanner sc, T[] valores) {
        System.out.println("0 - Não informar");
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + " - " + valores[i]);
        }
        System.out.print("Escolha (número): ");
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int opc = Integer.parseInt(s);
                if (opc == 0) return null;
                if (opc >= 1 && opc <= valores.length) return valores[opc - 1];
            } catch (NumberFormatException ignored) {}
            System.out.print("Opção inválida. Escolha novamente: ");
        }
    }
}