import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Future {

    public static void main(String[] args) {
        Mono<String> a = Mono.just("a");
        a.subscribe(value -> System.out.println(value));

        for (int i=0;i<20;i++)
            System.out.println("Hello");

        //CompletableFuture.completedFuture(value )
    }
}
