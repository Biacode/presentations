package presentation.akka.client;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import presentation.akka.api.model.GreetingModel;
import presentation.akka.api.model.request.GreetingRequest;
import presentation.akka.api.model.response.GreetingResponse;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 9/1/15
 * Time: 10:23 PM
 */
public class AkkaSystemClient extends UntypedActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), AkkaSystemClient.class);

    private static final String SERVER_ACTOR_PATH = "akka.tcp://server-system@127.0.0.1:2020/user/server";

    public static void main(final String[] args) {
        final Config config = ConfigFactory.load().getConfig("client");
        final ActorSystem system = ActorSystem.create("client-system", config);
        system.actorOf(Props.create(AkkaSystemClient.class), "client");
    }

    /* Callbacks */
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof GreetingResponse) {
            final String responseMessage = ((GreetingResponse) message).getModel().getMessage();
            logger.info("The - {} says - {}", getSender().path(), responseMessage);
        } else {
            logger.warning("Can not handle message - {}", message);
            unhandled(message);
        }
    }

    @Override
    public void preStart() throws Exception {
        logger.info("Successfully created client actor with path - {}", getSelf().path());
        final ActorSelection serverActor = context().system().actorSelection(SERVER_ACTOR_PATH);
        // Prepare message
        final GreetingRequest request = new GreetingRequest(new GreetingModel("Hey babe! which time?"));
        serverActor.tell(request, getSelf());
    }
}
