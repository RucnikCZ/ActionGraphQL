package cz.deepvision.dpvsn.actionGraphql;

import android.util.Log;

import com.google.gson.Gson;
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

public class ActionGraphQL<T> {
    private static String TAG = "ActionGraphQL";
    private String graphqlQuery, token, wssUrl, channelId;
    private JsonObject variables;
    private ActionCallback<T> actionCallback;
    private Subscription subscription;
    private Consumer consumer;
    private String subscriptionDataClassName;

    private Boolean log = false;

    public ActionGraphQL(String token, String wssUrl, JsonObject variables, ActionCallback<T> callback, Class<com.apollographql.apollo.api.Subscription> operation) {
        this.token = token;
        this.wssUrl = wssUrl;
        this.variables = variables;
        this.actionCallback = callback;
        init(operation);
    }

    private void init(Class<com.apollographql.apollo.api.Subscription> operation) {
        if (!com.apollographql.apollo.api.Subscription.class.isAssignableFrom(operation)) {
            throw new IllegalArgumentException("Not allowed graphql operation");
        }
        Class<com.apollographql.apollo.api.Subscription> subscriptionClass = operation;

        try {
            graphqlQuery = String.valueOf((String) subscriptionClass.getDeclaredField("QUERY_DOCUMENT").get(null));
            subscriptionDataClassName = subscriptionClass.getName() + "$" + "Data";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(Boolean reconnect) {
        URI uri = null;
        try {
            uri = new URI(this.wssUrl + token);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        Consumer.Options options = new Consumer.Options();

        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "https://pos.speedlo.cloud");
        options.headers = headers;
        options.reconnection = reconnect;

        this.consumer = ActionCable.createConsumer(uri, options);

        this.channelId = UUID.randomUUID().toString();
        Channel graphqlChannel = new Channel("GraphqlChannel");
        graphqlChannel.addParam("channelId", channelId);
        this.subscription = consumer.getSubscriptions().create(graphqlChannel);

        subscription
                .onConnected(() -> {
                    if (log) Log.d(TAG, "Connected");

                    JsonObject graphqlQuery = new JsonObject();
                    graphqlQuery.addProperty("query", this.graphqlQuery);
                    graphqlQuery.add("variables", this.variables);

                    subscription.perform("execute", graphqlQuery);
                })
                .onReceived(jsonElement -> {
                    if (log) Log.d(TAG, "Received " + jsonElement);
                    if (jsonElement.getAsJsonObject().get("result") != null) {
                        Gson gson = new Gson();
                        Object result = null;
                        try {
                            result = gson.fromJson(jsonElement.getAsJsonObject().get("result").getAsJsonObject().get("data"), Class.forName(subscriptionDataClassName));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        actionCallback.recievedCallBack((T) result);
                    }
                    if (!jsonElement.getAsJsonObject().get("more").getAsBoolean()) {
                        consumer.unsubscribeAndDisconnect();
                    }
                })
                .onDisconnected(() -> {
                    if (log) Log.d(TAG, "Disconnected");
                    actionCallback.disconnectedCallBack();
                })
                .onFailed(e -> {
                    Log.e(TAG, "Failed " + e);
                    actionCallback.failedCallBack(e);
                })
                .onRejected(() -> {
                    if (log) Log.e(TAG, "Rejected");
                });

            consumer.connect();
    }

    public void unsubscribeAndDisconnect() {
        if(this.consumer!=null){
            this.consumer.unsubscribeAndDisconnect();
        }
        
    }

    public void setLog(Boolean log) {
        this.log = log;
    }
}
