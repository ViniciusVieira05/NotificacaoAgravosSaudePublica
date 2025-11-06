package entidades.dados;

import enums.doenca.ClassificacaoOperacionalHansieniase;
import enums.outros.ClassificacaoFinal;
import enums.outros.FormaClínica;
import enums.paciente.Autocdone;
import enums.paciente.GrauDeIncapacidadeFisica;

import java.time.LocalDate;

public class Coclusão {

    private ClassificacaoFinal classificacaoFinal;
    private Autocdone autocdone;
    private String UFInfecção;
    private String MunicipioInfecção;
    private String PaísInfecção;
    private LocalDate dataEncerramento;
    private String nomeExaminador;
    private String conselhoProfissional;
    private FormaClínica formaClínica;
    private ClassificacaoOperacionalHansieniase classificacaoOperacionalHansieniase;
    private GrauDeIncapacidadeFisica grauDeIncapacidadeFisica;

    public Coclusão() {
    }

    public Coclusão(ClassificacaoFinal classificacaoFinal, Autocdone autocdone, String UFInfecção, String municipioInfecção, String paísInfecção, LocalDate dataEncerramento, String nomeExaminador, String conselhoProfissional, FormaClínica formaClínica, ClassificacaoOperacionalHansieniase classificacaoOperacionalHansieniase, GrauDeIncapacidadeFisica grauDeIncapacidadeFisica) {
        this.classificacaoFinal = classificacaoFinal;
        this.autocdone = autocdone;
        this.UFInfecção = UFInfecção;
        this.MunicipioInfecção = municipioInfecção;
        this.PaísInfecção = paísInfecção;
        this.dataEncerramento = dataEncerramento;
        this.nomeExaminador = nomeExaminador;
        this.conselhoProfissional = conselhoProfissional;
        this.formaClínica = formaClínica;
        this.classificacaoOperacionalHansieniase = classificacaoOperacionalHansieniase;
        this.grauDeIncapacidadeFisica = grauDeIncapacidadeFisica;
    }


    public ClassificacaoFinal getClassificacaoFinal() {
        return classificacaoFinal;
    }

    public void setClassificacaoFinal(ClassificacaoFinal classificacaoFinal) {
        this.classificacaoFinal = classificacaoFinal;
    }

    public Autocdone getAutocdone() {
        return autocdone;
    }

    public void setAutocdone(Autocdone autocdone) {
        this.autocdone = autocdone;
    }

    public String getUFInfecção() {
        return UFInfecção;
    }

    public void setUFInfecção(String UFInfecção) {
        this.UFInfecção = UFInfecção;
    }

    public String getMunicipioInfecção() {
        return MunicipioInfecção;
    }

    public void setMunicipioInfecção(String municipioInfecção) {
        this.MunicipioInfecção = municipioInfecção;
    }

    public String getPaísInfecção() {
        return PaísInfecção;
    }

    public void setPaísInfecção(String paísInfecção) {
        this.PaísInfecção = paísInfecção;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDate dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getNomeExaminador() {
        return nomeExaminador;
    }

    public void setNomeExaminador(String nomeExaminador) {
        this.nomeExaminador = nomeExaminador;
    }

    public String getConselhoProfissional() {
        return conselhoProfissional;
    }

    public void setConselhoProfissional(String conselhoProfissional) {
        this.conselhoProfissional = conselhoProfissional;
    }

    public FormaClínica getFormaClínica() {
        return formaClínica;
    }

    public void setFormaClínica(FormaClínica formaClínica) {
        this.formaClínica = formaClínica;
    }

    public ClassificacaoOperacionalHansieniase getClassificacaoOperacionalHansieniase() {
        return classificacaoOperacionalHansieniase;
    }

    public void setClassificacaoOperacionalHansieniase(ClassificacaoOperacionalHansieniase classificacaoOperacionalHansieniase) {
        this.classificacaoOperacionalHansieniase = classificacaoOperacionalHansieniase;
    }

    public GrauDeIncapacidadeFisica getGrauDeIncapacidadeFisica() {
        return grauDeIncapacidadeFisica;
    }

    public void setGrauDeIncapacidadeFisica(GrauDeIncapacidadeFisica grauDeIncapacidadeFisica) {
        this.grauDeIncapacidadeFisica = grauDeIncapacidadeFisica;
    }


}
