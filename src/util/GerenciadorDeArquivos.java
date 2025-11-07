package util;

import entidades.dados.*;
import entidades.notificacao.Notificacao;
import enums.doenca.Doencas;
import enums.paciente.*;
import enums.outros.*;
import enums.doenca.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class GerenciadorDeArquivos {

    private static final String DIR = "src/database/";
    private static final String NOTIFICACOES_FILE = DIR + "notificacoes.txt";

    static {
        try {
            Files.createDirectories(Paths.get(DIR));
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório do banco de dados: " + e.getMessage());
        }
    }

    public static void salvarNotificacao(Notificacao notificacao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOTIFICACOES_FILE, true))) {
            writer.write(formatarParaSalvar(notificacao));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar notificação: " + e.getMessage());
        }
    }

    public static void carregarNotificacoes() {
        Notificacao.todasAsNotificacoes.clear();

        File f = new File(NOTIFICACOES_FILE);
        if (!f.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(NOTIFICACOES_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                Map<String, String> dados = new HashMap<>();
                for (String par : linha.split(";")) {
                    String[] chaveValor = par.split("=", 2);
                    if (chaveValor.length == 2) {
                        dados.put(chaveValor[0], chaveValor[1]);
                    } else if (chaveValor.length == 1) {
                        dados.put(chaveValor[0], "");
                    }
                }
                Notificacao n = parseNotificacao(dados);
                if (n != null) Notificacao.todasAsNotificacoes.add(n);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar notificações: " + e.getMessage());
        }

        int maxCodigo = Notificacao.todasAsNotificacoes.stream()
                .mapToInt(Notificacao::getCodigo)
                .max()
                .orElse(0);
        Notificacao.setContadorCodigo(maxCodigo + 1);
    }

    private static Notificacao parseNotificacao(Map<String, String> dados) {
        Notificacao n = new Notificacao() {
            @Override
            public void registrarNotificacao(Scanner sc) {
                // não usado para carregamento
            }
        };
        preencherDadosComuns(n, dados);
        return n;
    }

    private static String formatarParaSalvar(Notificacao n) {
        StringBuilder sb = new StringBuilder();
        append(sb, "codigo", n.getCodigo());
        if (n.getDadosGerais() != null) {
            append(sb, "doenca", n.getDadosGerais().getDoenca());
            append(sb, "dataNotificacao", n.getDadosGerais().getDataNotificacao());
            append(sb, "uf", n.getDadosGerais().getUf());
            append(sb, "municipioDeNotificacao", n.getDadosGerais().getMunicipioDeNotificacao());
            append(sb, "ubs", n.getDadosGerais().getUbs());
            append(sb, "dataDiagnostico", n.getDadosGerais().getDataDiagnostico());
        }
        if (n.getDadosIndividuais() != null) {
            append(sb, "nome", n.getDadosIndividuais().getNome());
            append(sb, "dataNascimento", n.getDadosIndividuais().getDataNascimento());
            append(sb, "idade", n.getDadosIndividuais().getIdade());
            append(sb, "sexo", n.getDadosIndividuais().getSexo());
            append(sb, "gestante", n.getDadosIndividuais().getGestante());
            append(sb, "racaCor", n.getDadosIndividuais().getRacaCor());
            append(sb, "escolaridade", n.getDadosIndividuais().getEscolaridade());
            append(sb, "cartaoSUS", n.getDadosIndividuais().getCartaoSUS());
            append(sb, "nomeMae", n.getDadosIndividuais().getNomeMae());
        }
        if (n.getDadosResidenciais() != null) {
            append(sb, "residenciaUf", n.getDadosResidenciais().getUf());
            append(sb, "residenciaMunicipio", n.getDadosResidenciais().getMunicipio());
            append(sb, "distrito", n.getDadosResidenciais().getDistrito());
            append(sb, "bairro", n.getDadosResidenciais().getBairro());
            append(sb, "logradouro", n.getDadosResidenciais().getLogradouro());
            append(sb, "numero", n.getDadosResidenciais().getNumero());
            append(sb, "cep", n.getDadosResidenciais().getCep());
            append(sb, "telefone", n.getDadosResidenciais().getTelefone());
            append(sb, "zona", n.getDadosResidenciais().getZona());
        }
        if (n.getDadosEpidemiologicos() != null) {
            append(sb, "dataInvestigacao", n.getDadosEpidemiologicos().getDataInvestigação());
            append(sb, "ocupacao", n.getDadosEpidemiologicos().getOcupação());
            append(sb, "tipoLamina", n.getDadosEpidemiologicos().getTipoLamina());
            append(sb, "sintomas", n.getDadosEpidemiologicos().getSintomas());
            append(sb, "dataExame", n.getDadosEpidemiologicos().getDataExame());
            append(sb, "resultadoExame", n.getDadosEpidemiologicos().getResultadoExame());
            append(sb, "parasitasMetroCubico", n.getDadosEpidemiologicos().getParasitasMetroCubico());
            append(sb, "tipoEntrada", n.getDadosEpidemiologicos().getTipoEntrada());
            append(sb, "formaTuberculose", n.getDadosEpidemiologicos().getFormaTuberculose());
            append(sb, "localExtrapulmonar", n.getDadosEpidemiologicos().getLocalExtrapulmonar());
            append(sb, "radiografiaDoTorax", n.getDadosEpidemiologicos().getRadiografiaDoTorax());
            append(sb, "resultadoHiv", n.getDadosEpidemiologicos().getHiv());
        }
        if (n.getDadosTratamento() != null) {
            append(sb, "dataInicioTratamento", n.getDadosTratamento().getDataInicioDoTratamento());
            append(sb, "esquemaTerapeuticoInicial", n.getDadosTratamento().getEsquemaTerapeuticoInicial());
            append(sb, "esquemadeTratamentoUtilizado", n.getDadosTratamento().getEsquemadeTratamentoUtilizado());
        }
        if (n.getConclusaoEncerramento() != null) {
            append(sb, "classificacaoFinal", n.getConclusaoEncerramento().getClassificacaoFinal());
            append(sb, "autocdone", n.getConclusaoEncerramento().getAutocdone());
            append(sb, "UFInfecção", n.getConclusaoEncerramento().getUFInfecção());
            append(sb, "MunicipioInfecção", n.getConclusaoEncerramento().getMunicipioInfecção());
            append(sb, "PaísInfecção", n.getConclusaoEncerramento().getPaísInfecção());
            append(sb, "dataEncerramento", n.getConclusaoEncerramento().getDataEncerramento());
            append(sb, "formaClinica", n.getConclusaoEncerramento().getFormaClínica());
            append(sb, "classificacaoOperacionalHansieniase", n.getConclusaoEncerramento().getClassificacaoOperacionalHansieniase());
            append(sb, "grauIncapacidadeFisica", n.getConclusaoEncerramento().getGrauDeIncapacidadeFisica());
        }
        return sb.toString();
    }

    private static void append(StringBuilder sb, String key, Object value) {
        sb.append(key).append("=").append(value == null ? "" : value.toString()).append(";");
    }

    private static <E extends Enum<E>> E parseEnum(Class<E> enumClass, String value) {
        if (value == null || value.isEmpty() || value.equals("null")) return null;
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static void preencherDadosComuns(Notificacao n, Map<String, String> dados) {
        // Código
        try {
            if (dados.containsKey("codigo") && dados.get("codigo") != null && !dados.get("codigo").isEmpty()) {
                try {
                    n.setCodigo(Integer.parseInt(dados.get("codigo")));
                } catch (NumberFormatException ex) {
                    // ignore
                }
            }
        } catch (Exception ignored) {}

        // DadosGerais
        if (n.getDadosGerais() == null) n.setDadosGerais(new DadosGerais());
        applyIfPresent(dados, "doenca", v -> n.getDadosGerais().setDoenca(parseEnum(Doencas.class, v)));
        applyIfPresent(dados, "dataNotificacao", v -> {
            try { n.getDadosGerais().setDataNotificacao(LocalDate.parse(v)); } catch (DateTimeParseException ex) {}
        });
        applyIfPresent(dados, "uf", v -> n.getDadosGerais().setUf(v));
        applyIfPresent(dados, "municipioDeNotificacao", v -> n.getDadosGerais().setMunicipioDeNotificacao(v));
        applyIfPresent(dados, "ubs", v -> n.getDadosGerais().setUbs(v));
        applyIfPresent(dados, "dataDiagnostico", v -> {
            try { n.getDadosGerais().setDataDiagnostico(LocalDate.parse(v)); } catch (DateTimeParseException ex) {}
        });

        // DadosIndividuais
        if (n.getDadosIndividuais() == null) n.setDadosIndividuais(new DadosIndividuais());
        applyIfPresent(dados, "nome", v -> n.getDadosIndividuais().setNome(v));
        applyIfPresent(dados, "dataNascimento", v -> {
            try { n.getDadosIndividuais().setDataNascimento(LocalDate.parse(v)); } catch (DateTimeParseException ex) {}
        });
        applyIfPresent(dados, "idade", v -> {
            try { n.getDadosIndividuais().setIdade(Integer.parseInt(v)); } catch (NumberFormatException ex) {}
        });
        applyIfPresent(dados, "sexo", v -> n.getDadosIndividuais().setSexo(parseEnum(Sexo.class, v)));
        applyIfPresent(dados, "gestante", v -> n.getDadosIndividuais().setGestante(parseEnum(Gestante.class, v)));
        applyIfPresent(dados, "racaCor", v -> n.getDadosIndividuais().setRacaCor(parseEnum(Raca.class, v)));
        applyIfPresent(dados, "escolaridade", v -> n.getDadosIndividuais().setEscolaridade(parseEnum(Escolaridade.class, v)));
        applyIfPresent(dados, "cartaoSUS", v -> n.getDadosIndividuais().setCartaoSUS(v));
        applyIfPresent(dados, "nomeMae", v -> n.getDadosIndividuais().setNomeMae(v));

        // DadosResidenciais
        if (n.getDadosResidenciais() == null) n.setDadosResidenciais(new DadosResidencias());
        applyIfPresent(dados, "residenciaUf", v -> n.getDadosResidenciais().setUf(v));
        applyIfPresent(dados, "residenciaMunicipio", v -> n.getDadosResidenciais().setMunicipio(v));
        applyIfPresent(dados, "distrito", v -> n.getDadosResidenciais().setDistrito(v));
        applyIfPresent(dados, "bairro", v -> n.getDadosResidenciais().setBairro(v));
        applyIfPresent(dados, "logradouro", v -> n.getDadosResidenciais().setLogradouro(v));
        applyIfPresent(dados, "numero", v -> n.getDadosResidenciais().setNumero(v));
        applyIfPresent(dados, "cep", v -> n.getDadosResidenciais().setCep(v));
        applyIfPresent(dados, "telefone", v -> n.getDadosResidenciais().setTelefone(v));
        applyIfPresent(dados, "zona", v -> n.getDadosResidenciais().setZona(parseEnum(Zona.class, v)));

        // DadosEpidemiologicos
        if (n.getDadosEpidemiologicos() == null) n.setDadosEpidemiologicos(new DadosEpidemiologicos());
        applyIfPresent(dados, "dataInvestigacao", v -> {
            try { n.getDadosEpidemiologicos().setDataInvestigação(LocalDate.parse(v)); } catch (DateTimeParseException ex) {}
        });
        applyIfPresent(dados, "ocupacao", v -> n.getDadosEpidemiologicos().setOcupação(v));
        applyIfPresent(dados, "tipoLamina", v -> n.getDadosEpidemiologicos().setTipoLamina(parseEnum(TipoDeLamina.class, v)));
        applyIfPresent(dados, "sintomas", v -> n.getDadosEpidemiologicos().setSintomas(parseEnum(Sintomas.class, v)));
        applyIfPresent(dados, "dataExame", v -> {
            try { n.getDadosEpidemiologicos().setDataExame(LocalDate.parse(v)); } catch (DateTimeParseException ex) {}
        });
        applyIfPresent(dados, "resultadoExame", v -> n.getDadosEpidemiologicos().setResultadoExame(parseEnum(ResultadoExame.class, v)));
        applyIfPresent(dados, "parasitasMetroCubico", v -> {
            try { n.getDadosEpidemiologicos().setParasitasMetroCubico(Float.parseFloat(v)); } catch (NumberFormatException ex) {}
        });
        applyIfPresent(dados, "tipoEntrada", v -> n.getDadosEpidemiologicos().setTipoEntrada(parseEnum(TipoEntrada.class, v)));
        applyIfPresent(dados, "formaTuberculose", v -> n.getDadosEpidemiologicos().setFormaTuberculose(parseEnum(FormaTuberculose.class, v)));
        applyIfPresent(dados, "localExtrapulmonar", v -> n.getDadosEpidemiologicos().setLocalExtrapulmonar(parseEnum(LocalExtrapulmonar.class, v)));
        applyIfPresent(dados, "radiografiaDoTorax", v -> n.getDadosEpidemiologicos().setRadiografiaDoTorax(parseEnum(RadiografiaDoTorax.class, v)));
        applyIfPresent(dados, "resultadoHiv", v -> n.getDadosEpidemiologicos().setHiv(parseEnum(HIV.class, v)));

        // DadosTratamento
        if (n.getDadosTratamento() == null) n.setDadosTratamento(new DadosTratamento());
        applyIfPresent(dados, "dataInicioTratamento", v -> {
            try { n.getDadosTratamento().setDataInicioDoTratamento(LocalDate.parse(v)); } catch (DateTimeParseException ex) {}
        });
        applyIfPresent(dados, "esquemaTerapeuticoInicial", v -> n.getDadosTratamento().setEsquemaTerapeuticoInicial(parseEnum(EsquemaTerapeuticoInicial.class, v)));
        applyIfPresent(dados, "esquemadeTratamentoUtilizado", v -> n.getDadosTratamento().setEsquemadeTratamentoUtilizado(parseEnum(EsquemadeTratamentoUtilizado.class, v)));

        // Conclusao (Coclusão)
        if (n.getConclusaoEncerramento() == null) n.setConclusaoEncerramento(new Coclusão());
        applyIfPresent(dados, "classificacaoFinal", v -> n.getConclusaoEncerramento().setClassificacaoFinal(parseEnum(ClassificacaoFinal.class, v)));
        applyIfPresent(dados, "autocdone", v -> n.getConclusaoEncerramento().setAutocdone(parseEnum(Autocdone.class, v)));
        applyIfPresent(dados, "UFInfecção", v -> n.getConclusaoEncerramento().setUFInfecção(v));
        applyIfPresent(dados, "MunicipioInfecção", v -> n.getConclusaoEncerramento().setMunicipioInfecção(v));
        applyIfPresent(dados, "PaísInfecção", v -> n.getConclusaoEncerramento().setPaísInfecção(v));
        applyIfPresent(dados, "dataEncerramento", v -> {
            try { n.getConclusaoEncerramento().setDataEncerramento(LocalDate.parse(v)); } catch (DateTimeParseException ex) {}
        });
        applyIfPresent(dados, "formaClinica", v -> n.getConclusaoEncerramento().setFormaClínica(parseEnum(FormaClínica.class, v)));
        applyIfPresent(dados, "classificacaoOperacionalHansieniase", v -> n.getConclusaoEncerramento().setClassificacaoOperacionalHansieniase(parseEnum(ClassificacaoOperacionalHansieniase.class, v)));
        applyIfPresent(dados, "grauIncapacidadeFisica", v -> n.getConclusaoEncerramento().setGrauDeIncapacidadeFisica(parseEnum(GrauDeIncapacidadeFisica.class, v)));
    }

    private static void applyIfPresent(Map<String, String> map, String key, Consumer<String> consumer) {
        if (map.containsKey(key) && map.get(key) != null && !map.get(key).isEmpty() && !map.get(key).equals("null")) {
            consumer.accept(map.get(key));
        }
    }
}
