package br.com.bb.t99.models;

public class MensagemErro {
    private final int codigoErro;
    private final String mensagemErro;
    public static final MensagemErro PET_NOT_EXIST = new MensagemErro(119, "Pet inexistente");
    public static final MensagemErro AGENDA_NOT_EXIST = new MensagemErro(120, "Agenda inexistente");
    public static final MensagemErro AGENDA_NAO_DISPONIVEL = new MensagemErro(121, "Agenda Indisponivel nesse horário");

    public MensagemErro(int codigo, String mensagem) {
        this.codigoErro = codigo;
        this.mensagemErro = mensagem;
    }

    public int getCodigoErro() {
        return codigoErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}