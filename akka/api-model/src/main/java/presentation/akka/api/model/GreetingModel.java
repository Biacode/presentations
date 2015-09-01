package presentation.akka.api.model;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 9/1/15
 * Time: 10:54 PM
 */
public class GreetingModel implements Serializable {
    private static final long serialVersionUID = -448710591060174051L;

    /* Properties */
    private final String message;

    /* Constructors */
    public GreetingModel(final String message) {
        this.message = message;
    }

    // TODO: Implement Equals, HashCode and ToString

    /* Properties getters */
    public String getMessage() {
        return message;
    }
}
