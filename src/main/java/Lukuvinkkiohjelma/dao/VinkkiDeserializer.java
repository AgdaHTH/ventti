package Lukuvinkkiohjelma.dao;

import Lukuvinkkiohjelma.domain.Vinkki;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.HashMap;
import java.util.Map;

public class VinkkiDeserializer implements JsonDeserializer<Vinkki> {
    private String luokanTyyppikentta;
    private Gson gson;
    private Map<String, Class<? extends Vinkki>> luokkaTyypit;
 
    public VinkkiDeserializer(String tyyppikentta) {
        this.luokanTyyppikentta = tyyppikentta;
        this.gson = new Gson();
        this.luokkaTyypit = new HashMap<>();
    }
 
    public void lisaaLuokka(String tyypinNimi, Class<? extends Vinkki> luokanTyyppi) {
        luokkaTyypit.put(tyypinNimi, luokanTyyppi);
    }

    @Override
    public Vinkki deserialize(JsonElement json, java.lang.reflect.Type type, 
            JsonDeserializationContext contex) throws JsonParseException {
        JsonObject vinkkiObject = json.getAsJsonObject();
        JsonElement typeElement = vinkkiObject.get(luokanTyyppikentta);
 
        Class<? extends Vinkki> vinkkiType = luokkaTyypit.get(typeElement.getAsString());
        return gson.fromJson(vinkkiObject, vinkkiType);
    }
}