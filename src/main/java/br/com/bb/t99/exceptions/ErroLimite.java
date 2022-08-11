package br.com.bb.t99.exceptions;

import br.com.bb.dev.erros.exceptions.BBException;

import br.com.bb.dev.erros.filter.FilterUtils;

public class ErroLimite extends BBException{

    public ErroLimite(Exception e) {
        super("998", "Erro em solicitação de limite.", e.getCause());
        this.put(FilterUtils.VAR_MOTIVO,  e.getMessage());
        StackTraceElement stackTrace = this.getStackTrace()[this.getStackTrace().length>1?1:0];
        this.put(FilterUtils.VAR_ORIGEM,
                String.format("%s:%s", stackTrace.getClassName(), stackTrace.getLineNumber()));
    }

    public final String put(String key, String value) {
        return super.put(key, value);
    }
}
