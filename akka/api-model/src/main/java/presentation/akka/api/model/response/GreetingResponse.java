package presentation.akka.api.model.response;

import presentation.akka.api.model.GreetingModel;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 9/1/15
 * Time: 10:47 PM
 */
public class GreetingResponse implements Serializable {
    private static final long serialVersionUID = -8254148591912186175L;

    /* Properties */
    private final GreetingModel model;

    /* Constructors */
    public GreetingResponse(final GreetingModel model) {
        this.model = model;
    }

    /* Properties getters */
    public GreetingModel getModel() {
        return model;
    }
}
