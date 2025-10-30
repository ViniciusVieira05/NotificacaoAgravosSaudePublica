package entidades.dados;

import enums.paciente.Escolaridade;
import enums.paciente.Gestante;
import enums.paciente.Raca;
import enums.paciente.Sexo;

import java.time.LocalDate;

public class DadosIndividuais {

        private String nome;
        private LocalDate dataNascimento;
        private int idade;
        private Sexo sexo;
        private Gestante gestante;
        private Raca racaCor;
        private Escolaridade escolaridade;
        private String cartaoSUS;
        private String nomeMae;

        public DadosIndividuais(){

        }
        public DadosIndividuais(String nome, LocalDate dataNascimento, int idade, Sexo sexo, Gestante gestante, Raca racaCor, Escolaridade escolaridade, String cartaoSUS, String nomeMae) {
            this.nome = nome;
            this.dataNascimento = dataNascimento;
            this.idade = idade;
            this.sexo = sexo;
            this.gestante = gestante;
            this.racaCor = racaCor;
            this.escolaridade = escolaridade;
            this.cartaoSUS = cartaoSUS;
            this.nomeMae = nomeMae;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Gestante getGestante() {
        return gestante;
    }

    public void setGestante(Gestante gestante) {
        this.gestante = gestante;
    }

    public Raca getRacaCor() {
        return racaCor;
    }

    public void setRacaCor(Raca racaCor) {
        this.racaCor = racaCor;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getCartaoSUS() {
        return cartaoSUS;
    }

    public void setCartaoSUS(String cartaoSUS) {
        this.cartaoSUS = cartaoSUS;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
}
