package json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import coreModels.beans.Order;
import coreModels.beans.ProductBean;

public class JsonBuilderOrder implements com.google.gson.JsonSerializer<coreModels.beans.Order>
{

	public JsonElement serialize(Order order, Type arg1, JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		
		obj.addProperty("totOrdine", order.getTotal().toString());
		obj.add("order", new GsonBuilder().registerTypeAdapter(ProductBean.class, new json.JsonBuilderProd()).create().toJsonTree(order.getProduct()));
		
		return obj;
	}

}
