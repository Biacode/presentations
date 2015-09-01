package presentation.akka.server;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 9/1/15
 * Time: 10:14 PM
 */
public class AkkaSystemServer extends UntypedActor {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), AkkaSystemServer.class);

    public static void main(String[] args) {
        final Config config = ConfigFactory.load().getConfig("server");
        final ActorSystem system = ActorSystem.create("server-system", config);
        system.actorOf(Props.create(AkkaSystemServer.class), "server");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof GreetingRequest) {
            final String requestMessage = ((GreetingRequest) message).getModel().getMessage();
            logger.info("The - {} says - {}", getSender().path(), requestMessage);
            // Prepare response message
            final String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            final GreetingResponse response = new GreetingResponse(new GreetingModel(time));
            getSender().tell(response, getSelf());
        } else {
            logger.warning("Can not handle message - {}", message);
            unhandled(message);
        }
    }

    @Override
    public void preStart() {
        logger.info("Successfully created server actor with path - {}", getSelf().path());
    }
}
