package dao;

import com.google.gson.*;
import entity.*;
import java.lang.reflect.Type;

/**
 * Cette classe permet à Gson de gérer le polymorphisme.
 * Elle aide à transformer le JSON en l'objet Java spécifique (Sirop, Comprime, etc.)
 */
public class MedicamentAdapter implements JsonDeserializer<Medicament>, JsonSerializer<Medicament> {

    @Override
    public JsonElement serialize(Medicament src, Type typeOfSrc, JsonSerializationContext context) {
        // La sérialisation standard fonctionne bien, on délègue
        return context.serialize(src, src.getClass());
    }

    @Override
    public Medicament deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        // On récupère le champ qui nous permet de distinguer les classes (discriminant)
        JsonElement typeElement = jsonObject.get("formePharmaceutique");

        if (typeElement == null) {
            throw new JsonParseException("Champ 'formePharmaceutique' manquant pour le polymorphisme");
        }

        String type = typeElement.getAsString();

        // Selon le type, on renvoie la bonne instance de classe fille
        switch (type) {
            case "Comprimé":
                return context.deserialize(json, Comprime.class);
            case "Sirop":
                return context.deserialize(json, Sirop.class);
            case "Injection":
                return context.deserialize(json, Injection.class);
            case "Pommade":
                return context.deserialize(json, Pommade.class);
            default:
                throw new JsonParseException("Type de médicament inconnu : " + type);
        }
    }
}