package br.com.bb.t99.utils;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.json.Json;
import javax.json.JsonObject;

import br.com.bb.t99.models.MensagemErro;

public final class Result<T> {
    private final T result;
    private final MensagemErro error;

    /**
     * Creates a result containing the given value
     *
     * @param result the value of the result
     * @param <T>    the type of the result
     *
     * @return a {@code Result} containing the value
     * @throws NullPointerException if the value is null
     */
    public static <T> Result<T> of(T result) {
        return new Result<>(Objects.requireNonNull(result, "Result cannot be null"));
    }

    /**
     * Creates a result containing an error
     *
     * @param error the error message
     * @param <T>     the type that the value would have had
     *
     * @return a {@code Result} containing an error
     * @throws NullPointerException if the message is null
     */
    public static <T> Result<T> error(MensagemErro error) {
        Objects.requireNonNull(error, "Error cannot be null");
        Objects.requireNonNull(error.getMensagemErro(), "Error cannot have a null message null");
        return new Result<>(error);
    }

    /**
     * Creates a result containing an error
     *
     * @param code    the error code
     * @param message the error message
     * @param <T>     the type that the value would have had
     *
     * @return a {@code Result} containing an error
     * @throws NullPointerException if the message is null
     */
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, Objects.requireNonNull(message, "Message cannot be null"));
    }

    public Result(T result) {
        this.result = result;
        this.error = null;
    }

    private Result(MensagemErro error) {
        this.result = null;
        this.error = error;
    }

    private Result(int errorCode, String errorMessage) {
        this.result = null;
        this.error = new MensagemErro(errorCode, errorMessage);
    }

    /**
     * Returns whether this result was successful or not
     *
     * @return {@code true} if the result is successful, {@code false} otherwise
     */
    public boolean isSuccess() {
        return result != null;
    }

    /**
     * Returns the non-null value of the result if it's present, otherwise throws
     *
     * @return the value of the result
     * @throws NoSuchElementException if the result is an error
     */
    public T getResult() {
        throwIfError();
        return result;
    }

    /**
     * Returns the error code if this result is an error, otherwise throws
     *
     * @return the code of the error
     * @throws NoSuchElementException if the result is successful
     */
    public int getErrorCode() {
        throwIfSuccess();
        return error.getCodigoErro();
    }

    /**
     * Returns the non-null error message if this result is an error, otherwise
     * throws
     *
     * @return the message describing the error
     * @throws NoSuchElementException if the result is successful
     */
    public String getErrorMessage() {
        throwIfSuccess();
        return error.getMensagemErro();
    }

    /**
     * Returns a JSON object containing the code and the message if this result is
     * an error, otherwise throws
     *
     * @return the JSON containing both {@code errorCode} and {@code errorMessage}
     * @throws NoSuchElementException if the result is successful
     */
    public JsonObject getErrorJson() {
        throwIfSuccess();
        return Json.createObjectBuilder().add("errorCode", error.getCodigoErro())
                .add("errorMessage", error.getMensagemErro()).build();
    }

    /**
     * Returns an object containing the code and the message if this result is an
     * error, otherwise throws
     *
     * @return the object containing both {@code errorCode} and {@code errorMessage}
     * @throws NoSuchElementException if the result is successful
     */
    public MensagemErro getErrorObject() {
        throwIfSuccess();
        return error;
    }

    private void throwIfError() {
        if (!isSuccess()) {
            throw new NoSuchElementException("Result was not successful");
        }
    }

    private void throwIfSuccess() {
        if (isSuccess()) {
            throw new NoSuchElementException("Result was successful");
        }
    }

    /**
     * If the result is successful, performs the given action with the result,
     * otherwise does nothing.
     *
     * @param action the action to be performed, if result is successful
     *
     * @return this object
     * @throws NullPointerException if the given action is {@code null}
     */
    public Result<T> ifSuccess(Consumer<? super T> action) {
        Objects.requireNonNull(action, "Action cannot be null");
        if (isSuccess()) {
            action.accept(result);
        }
        return this;
    }

    /**
     * If the result is not successful, performs the given action with the error,
     * otherwise does nothing.
     *
     * @param action the error action to be performed, if the result is not
     *               successful
     *
     * @throws NullPointerException if either of the given actions are {@code null}
     */
    public Result<T> ifError(Consumer<? super MensagemErro> action) {
        Objects.requireNonNull(action, "Action cannot be null");
        if (isSuccess()) {
            action.accept(error);
        }
        return this;
    }

    /**
     * If the result is successful, returns the value created using the result,
     * otherwise returns the value created using the error
     *
     * @param creator      the action to be performed, if result is successful
     * @param errorCreator the error action to be performed, if the result is not
     *                     successful
     *
     * @throws NullPointerException if either of the given actions are {@code null}
     */
    public <R> R returnIfSuccessOrError(Function<? super T, ? extends R> creator,
                                        Function<? super MensagemErro, ? extends R> errorCreator) {
        Objects.requireNonNull(creator, "Action cannot be null");
        Objects.requireNonNull(errorCreator, "Error action cannot be null");
        if (isSuccess()) {
            return creator.apply(result);
        } else {
            return errorCreator.apply(error);
        }
    }
}