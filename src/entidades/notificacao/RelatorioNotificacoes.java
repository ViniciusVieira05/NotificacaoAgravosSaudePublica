package entidades.notificacao;

import entidades.dados.DadosIndividuais;
import enums.doenca.Doencas;
import enums.paciente.Escolaridade;
import enums.paciente.Raca;
import enums.paciente.Sexo;

import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


public class RelatorioNotificacoes {

    // Total de notificações por agravo (Doencas)
    public static Map<Doencas, Long> totalPorAgravo() {
        synchronized (Notificacao.todasAsNotificacoes) {
            return Notificacao.todasAsNotificacoes.stream()
                    .filter(n -> n.getDadosGerais() != null && n.getDadosGerais().getDoenca() != null)
                    .collect(Collectors.groupingBy(n -> n.getDadosGerais().getDoenca(), LinkedHashMap::new, Collectors.counting()));
        }
    }

    // Total de notificações por bairro
    public static Map<String, Long> totalPorBairro() {
        synchronized (Notificacao.todasAsNotificacoes) {
            return Notificacao.todasAsNotificacoes.stream()
                    .filter(n -> n.getDadosResidenciais() != null && n.getDadosResidenciais().getBairro() != null && !n.getDadosResidenciais().getBairro().trim().isEmpty())
                    .collect(Collectors.groupingBy(n -> n.getDadosResidenciais().getBairro().trim(), LinkedHashMap::new, Collectors.counting()));
        }
    }

    // Total de notificações por mês/ano (YearMonth -> "YYYY-MM")
    public static Map<YearMonth, Long> totalPorMesAno() {
        synchronized (Notificacao.todasAsNotificacoes) {
            return Notificacao.todasAsNotificacoes.stream()
                    .map(n -> n.getDadosGerais() != null ? n.getDadosGerais().getDataNotificacao() : null)
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(d -> YearMonth.from(d), LinkedHashMap::new, Collectors.counting()));
        }
    }

    // Total de notificações por faixa etária (0-18, 19-64, 65+)
    public static Map<String, Long> totalPorFaixaEtaria() {
        synchronized (Notificacao.todasAsNotificacoes) {
            return Notificacao.todasAsNotificacoes.stream()
                    .map(n -> {
                        DadosIndividuais di = n.getDadosIndividuais();
                        int idade = (di != null) ? di.getIdade() : -1;
                        if (idade >= 0 && idade <= 18) return "0-18";
                        if (idade >= 19 && idade <= 64) return "19-64";
                        if (idade >= 65) return "65+";
                        return "Não informado";
                    })
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        }
    }

    // Total de notificações por sexo
    public static Map<String, Long> totalPorSexo() {
        synchronized (Notificacao.todasAsNotificacoes) {
            return Notificacao.todasAsNotificacoes.stream()
                    .map(n -> {
                        DadosIndividuais di = n.getDadosIndividuais();
                        Sexo s = (di != null) ? di.getSexo() : null;
                        return s != null ? s.name() : "Não informado";
                    })
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        }
    }

    // Total de notificações por raça/cor
    public static Map<String, Long> totalPorRaca() {
        synchronized (Notificacao.todasAsNotificacoes) {
            return Notificacao.todasAsNotificacoes.stream()
                    .map(n -> {
                        DadosIndividuais di = n.getDadosIndividuais();
                        Raca r = (di != null) ? di.getRacaCor() : null;
                        return r != null ? r.name() : "Não informado";
                    })
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        }
    }

    // Total de notificações por escolaridade
    public static Map<String, Long> totalPorEscolaridade() {
        synchronized (Notificacao.todasAsNotificacoes) {
            return Notificacao.todasAsNotificacoes.stream()
                    .map(n -> {
                        DadosIndividuais di = n.getDadosIndividuais();
                        Escolaridade e = (di != null) ? di.getEscolaridade() : null;
                        return e != null ? e.name() : "Não informado";
                    })
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        }
    }

    // Método de impressão resumida de todos os relatórios
    public static void imprimirRelatoriosResumo() {
        System.out.println("\n=== RELATÓRIO RESUMO DAS NOTIFICAÇÕES ===\n");

        System.out.println("Total por agravo:");
        totalPorAgravo().forEach((k, v) -> System.out.println("- " + k + ": " + v));

        System.out.println("\nTotal por bairro:");
        totalPorBairro().forEach((k, v) -> System.out.println("- " + k + ": " + v));

        System.out.println("\nTotal por mês/ano:");
        totalPorMesAno().forEach((k, v) -> System.out.println("- " + k + ": " + v));

        System.out.println("\nTotal por faixa etária:");
        totalPorFaixaEtaria().forEach((k, v) -> System.out.println("- " + k + ": " + v));

        System.out.println("\nTotal por sexo:");
        totalPorSexo().forEach((k, v) -> System.out.println("- " + k + ": " + v));

        System.out.println("\nTotal por raça/cor:");
        totalPorRaca().forEach((k, v) -> System.out.println("- " + k + ": " + v));

        System.out.println("\nTotal por escolaridade:");
        totalPorEscolaridade().forEach((k, v) -> System.out.println("- " + k + ": " + v));

        System.out.println("\nRelatório completo gerado.");
    }

    // Método utilitário que retorna os notificações filtradas (por agravo opcional)
    public static List<Notificacao> filtrarPorAgravo(Doencas agravo) {
        synchronized (Notificacao.todasAsNotificacoes) {
            if (agravo == null) return List.copyOf(Notificacao.todasAsNotificacoes);
            return Notificacao.todasAsNotificacoes.stream()
                    .filter(n -> n.getDadosGerais() != null && n.getDadosGerais().getDoenca() == agravo)
                    .collect(Collectors.toList());
        }
    }
}

