package cz.deepvision.dpvsn.actionGraphql;

import com.google.gson.JsonObject;

public interface ActionCallback {
    public void recievedCallBack(JsonObject result);
}
