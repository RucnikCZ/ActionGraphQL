package cz.deepvision.dpvsn.actionGraphql;

import android.util.Log;

import com.google.gson.JsonObject;
import com.hosopy.actioncable.ActionCable;
import com.hosopy.actioncable.Channel;
import com.hosopy.actioncable.Consumer;
import com.hosopy.actioncable.Subscription;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActionGraphQL {
    private static String TAG = "ActionGraphQL";
    private String graphqlQuery, token, wssUrl, channelId;
    private JsonObject variables;
    private ActionCallback actionCallback;
    private Subscription subscription;
    private Consumer consumer;

    public ActionGraphQL(String graphqlQuery, String token, String wssUrl, JsonObject variables, ActionCallback callback) {
        this.graphqlQuery = graphqlQuery;
        this.token = token;
        this.wssUrl = wssUrl;
        this.variables = variables;
        this.actionCallback = callback;
    }

    public void subscribe() {
        URI uri = null;
        try {
            uri = new URI(this.wssUrl + token);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        Consumer.Options options = new Consumer.Options();

        Map<String, String> headers = new HashMap();
        headers.put("Origin", "https://pos.speedlo.cloud");
        options.headers = headers;
        options.reconnection = true;

        this.consumer = ActionCable.createConsumer(uri, options);

        this.channelId = UUID.randomUUID().toString();
        Channel graphqlChannel = new Channel("GraphqlChannel");
        graphqlChannel.addParam("channelId", channelId);
        this.subscription = consumer.getSubscriptions().create(graphqlChannel);

        subscription
                .onConnected(() -> {
                    Log.e(TAG, "Connected");

                    JsonObject graphqlQuery = new JsonObject();
                    graphqlQuery.addProperty("query", this.graphqlQuery);
                    graphqlQuery.add("variables", this.variables);

                    subscription.perform("execute", graphqlQuery);
                })
                .onReceived(jsonElement -> {
                    Log.e(TAG, "Received " + jsonElement);
                    if (jsonElement.getAsJsonObject().get("result") != null) {
                        actionCallback.recievedCallBack(jsonElement.getAsJsonObject().get("result").getAsJsonObject());
                    }
                    if (!jsonElement.getAsJsonObject().get("more").getAsBoolean()) {
                        consumer.unsubscribeAndDisconnect();
                    }
                })
                .onDisconnected(() -> Log.e(TAG, "Disconnected"))
                .onFailed(e -> Log.e(TAG, "Failed " + e))
                .onRejected(() -> Log.e(TAG, "Rejected"));

        consumer.connect();
    }

    public void unsubscribeAndDisconnect() {
        consumer.unsubscribeAndDisconnect();
    }
}
