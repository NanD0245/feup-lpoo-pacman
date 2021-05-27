package g50.states;

public class State<T> {
    private final T model;

    public State(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }
}
