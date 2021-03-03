package cz.deepvision.dpvsn.actionGraphql;

public interface ActionCallback<T> {
    void recievedCallBack(T result, SubscriptionType type);

    void connectedCallBack();

    void disconnectedCallBack();
}
