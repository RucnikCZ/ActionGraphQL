package cz.deepvision.dpvsn.actionGraphql;

public interface ActionCallback<T> {
    public void recievedCallBack(T result);

    public void disconnectedCallBack();

    public void failedCallBack(Exception e);
}
