package br.com.bb.t99.exceptions;

import br.com.bb.dev.erros.exceptions.BBException;
import br.com.bb.dev.erros.filter.FilterUtils;
import org.hibernate.JDBCException;

public class ErroSqlException extends BBException {
    private static final String MENSAGEM_ORIGEM = "%s - linha: %s.";

    public ErroSqlException(Exception e) {
        super("999", "Ocorreu um erro de sistema.", e.getCause());
        int code = -1;
        String sqlQuery = "";
        String motivo;
        if (e.getCause() instanceof JDBCException) {
            JDBCException jdbcException = (JDBCException) e.getCause();
            if (jdbcException.getSQLException() != null) {
                code = jdbcException.getSQLException().getErrorCode();
            }
            if (jdbcException.getSQL() != null && jdbcException.getSQL().length() > 0) {
                sqlQuery = jdbcException.getSQL();
            }
            if (jdbcException.getCause() != null) {
                motivo = jdbcException.getCause().getMessage();
            } else {
                motivo = jdbcException.getMessage();
            }
        } else {
            motivo = e.getMessage();
        }
        this.put(FilterUtils.VAR_MOTIVO, motivo);
        this.put(FilterUtils.VAR_ORIGEM, getSourceFromStackTraceSqlTrace());
        this.put("SQL_CODE", String.valueOf(code));
        this.put("QUERY_SQL", sqlQuery);
    }

    private String getSourceFromStackTraceSqlTrace() {
        int index = 0;
        if (this.getStackTrace().length > 1) {
            index = 1;
        }
        StackTraceElement stackTrace = this.getStackTrace()[index];
        return String.format(MENSAGEM_ORIGEM, stackTrace.getClassName(), stackTrace.getLineNumber());
    }

    public final String put(String key, String value) {
        return super.put(key, value);
    }
}