package org.symbolit.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import org.symbolit.dao.DevExcusesMessage;
import org.symbolit.dao.FakeDataBaseQueries;
import org.symbolit.rest.Exception.RestException;
import org.symbolit.rest.common.RestUtil;


@Path("/excuses")
public class DeveloperExcuses {

    /**
     * Return the complete list of developper's excuses in JSON
     *
     * @return the complete list of developper's excuses in JSON
     */
    @Path("/list")
    @GET
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public Response getExcusesList(
                    @Context UriInfo pInfo,
                    @Context HttpHeaders pHttpHeaders) throws RestException {

        Response rResponse;

        try {
            // Ouverture Session hibernate

            // Appel à la base de données (ici fichier json dans les ressources) pour récupérer la liste
            FakeDataBaseQueries db = new FakeDataBaseQueries();
            final List<DevExcusesMessage> devExcusesList = db.getExcusesList();

            // Convertit la liste d'objets en objet JSON
            final String rDevExcusesListJson = RestUtil.writeObjectListToJsonArray(devExcusesList);

            // Tentative de prise en compte infructueuses des problèmes de sécurité (CORS origin des navigateurs)
            final ResponseBuilder responseBuilder = Response.ok().entity(rDevExcusesListJson);
            responseBuilder.header("Access-Control-Allow-Origin", "*");
            responseBuilder.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            responseBuilder.header("Access-Control-Allow-Credentials", "false");

            rResponse = responseBuilder.build();


        } catch (Exception ex) {
            // LOGGER l'erreur dans les logs du service REST
            // Ajouter un service de gestion des erreurs
            return Response.status(404).entity("Data not found").build();

        } finally {
            // Fermeture de Session Hibernate
        }

        return rResponse;
    }

    /**
     * Return the complete list of developper's excuses in JSON
     *
     * @return the complete list of developper's excuses in JSON
     */
    @Path("/add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addExcuse(String excuseEntityToAdd,
                    @Context UriInfo pInfo,
                    @Context HttpHeaders pHttpHeaders) throws RestException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> newExcuseEntityMap = mapper.readValue(excuseEntityToAdd, Map.class);
            // Utilisation d'un Mapper car problème de sérialisation (pas réussi à gérer le Provider pour cette librairie)
            DevExcusesMessage newExcuseEntity = createDevExcusesMessageFromMap(newExcuseEntityMap);

            FakeDataBaseQueries fakeDB = new FakeDataBaseQueries();
            fakeDB.addToExcusesListDataBase(newExcuseEntity);

        } catch (Exception ex) {
            // LOGGER l'erreur dans les logs du service REST
            // Ajouter un service de gestion des erreurs
            return Response.status(400).entity("Invalid data").build();
        }

        return Response.ok().entity(excuseEntityToAdd).build();
    }

    private DevExcusesMessage createDevExcusesMessageFromMap(Map<String, Object> excuseEntityMap) throws Exception {
        DevExcusesMessage newExcuseMessage = new DevExcusesMessage();
        try {
            newExcuseMessage.setHttpcode((Integer) excuseEntityMap.get("http_code"));
            newExcuseMessage.setTag((String) excuseEntityMap.getOrDefault("tag", null));
            newExcuseMessage.setMessage((String) excuseEntityMap.getOrDefault("message", null));
        } catch (Exception ex) {
            // LOGGER Erreur lors de la sérialisation
            throw ex;
        }

        return newExcuseMessage;
    }
}
