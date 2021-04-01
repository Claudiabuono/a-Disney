package json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class JsonBuilderCart implements com.google.gson.JsonSerializer<coreModels.beans.Cart> {

	@Override
	public JsonElement serialize(coreModels.beans.Cart cart, Type arg1, JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		JsonObject obj = new JsonObject();
		
		obj.addProperty("totIva", cart.getTotalIva().toString());
		obj.addProperty("noIva", cart.getTotalWithoutIva().toString());
		obj.addProperty("tot", cart.getTotal().toString());
		obj.addProperty("size", cart.size());
		obj.add("list", new GsonBuilder().registerTypeAdapter(coreModels.beans.Order.class, new json.JsonBuilderOrder()).create().toJsonTree(cart.getOrders()));
		
		return obj;
	}

}
