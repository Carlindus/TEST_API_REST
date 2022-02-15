package org.symbolit.rest.common;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.symbolit.dao.IRestEntity;

/**
 * Classe utilitaire du service REST
 */
public class RestUtil {

    /**
     * Transforme une liste d'entité du service REST en Json
     *
     * @param objectList Liste d'objets
     * @return Json de la liste d'objet
     * @throws IOException
     */
    public static String writeObjectListToJsonArray(final List<? extends IRestEntity> objectList) throws IOException {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
        mapper.writeValue(out, objectList);

        final byte[] data = out.toByteArray();
        return new String(data);
    }
}
