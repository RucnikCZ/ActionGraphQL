package cz.deepvision.dpvsn.actionGraphql;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest implements ActionCallback<DummySub.Data> {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    public void test() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyNCwiYXV0aGVudGljYXRpb25fdG9rZW4iOm51bGwsImlhdCI6MjU3NTA4NDc3LCJqdGkiOiI0ODQ2ZWFhOTdiMWJhNzU5Y2E3ZmRlMWUyOWQ0YzcyMSJ9.lfHboTMrsYMdUggQJHUp_yBtQhguukotmXatQ8T7Abk";
        String wssUrl = "ws://be.dev.admin.c2e.deep-vision.cloud/cable?token=" + token;

        JsonObject variables = new JsonObject();

        JsonArray jArray = new JsonArray();
        JsonPrimitive element = new JsonPrimitive("1");
        jArray.add(element);

        JsonArray jArrayCat = new JsonArray();
        JsonPrimitive element2 = new JsonPrimitive("PREPARING");
        jArrayCat.add(element2);

        variables.add("branches", jArray);
        variables.add("categories", jArrayCat);
        ActionGraphQL actionGraphQL = new ActionGraphQL(token, wssUrl, variables, (ActionCallback<DummySub.Data>) result -> {

        }, null);
        actionGraphQL.subscribe();
    }

    @Override
    public void recievedCallBack(DummySub.Data result) {

    }

}