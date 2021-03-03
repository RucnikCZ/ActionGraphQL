# ActionGraphQL


How to add?

Step 1.

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}Copy
Step 2. 
Add the dependency

	dependencies {
	        implementation 'com.github.LubosCzech:ActionGraphQL:Tag'
	}


Usage:

Add implements ActionCallback<ReturnType>  to your Activity

    String token = "security token";
    String wssUrl = "websocket url - actionCable";
    JsonObject variables = new JsonObject();
    //Example - array of primitives
    JsonArray jArray = new JsonArray();
    JsonPrimitive element = new JsonPrimitive("1");
    jArray.add(element);

    variables.add("branches", jArray);


    ActionGraphQL actionGraphQL = new ActionGraphQL(token,wssUrl,variables,this,UpdatedOrderCountCategorySubscription.class);
    actionGraphQL.subscribe();
