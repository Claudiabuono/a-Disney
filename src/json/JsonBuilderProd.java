package json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import coreModels.beans.ProductBean;

public class JsonBuilderProd implements com.google.gson.JsonSerializer<coreModels.beans.ProductBean>{

	@Override
	public JsonElement serialize(ProductBean o, Type arg1, JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		JsonObject obj = new JsonObject();
		
		int ctgy= o.getCategory();
		String c="";
			if("0".equals(ctgy+c)){ c="Articoli per la casa";}
			if("1".equals(ctgy+c)){ c="Abbigliamento e Accessori";}
			if("2".equals(ctgy+c)){ c="Articoli per le feste";}
			if("3".equals(ctgy+c)){ c="Giochi";}
		
		obj.addProperty("id", o.getCode());
		obj.addProperty("name", o.getName());
		obj.addProperty("img", o.getPhoto());
		obj.addProperty("isinDicount", o.isinDiscount());
		obj.addProperty("price", o.getPriceSenzaSconto().toString());
		obj.addProperty("priceDisc", o.getPricewithIva().toString());
		obj.addProperty("discount", o.getDiscount());
		obj.addProperty("qty", o.getQty());
		obj.addProperty("iva", o.getIva());
		obj.addProperty("character", o.getCharacter());
		obj.addProperty("category", c);
		
		return obj;
	}

}
