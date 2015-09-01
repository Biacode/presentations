package presentation.akka.api.model.request;

import presentation.akka.api.model.GreetingModel;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 9/1/15
 * Time: 10:47 PM
 */
public class GreetingRequest implements Serializable {
    private static final long serialVersionUID = -308320103298640678L;

    /* Properties */
    private final GreetingModel model;

    /* Constructors */
    public GreetingRequest(final GreetingModel model) {
        this.model = model;
    }

    /* Properties getters */
    public GreetingModel getModel() {
        return model;
    }
}
