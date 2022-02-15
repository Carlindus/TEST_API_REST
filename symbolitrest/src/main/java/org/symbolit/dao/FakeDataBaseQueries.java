package org.symbolit.dao;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe qui traite les données statiques comme des appels à la DB pour le TEST
 */
public class FakeDataBaseQueries {

    private final static String DATA_FILE_NAME = "test_data.json";

    /**
     * Retourne la liste des excuses stockée dans les ressources  à défaut de DB
     *
     * @return la liste des excuses stockée dans les ressources
     */
    public List<DevExcusesMessage> getExcusesList() {
        List<DevExcusesMessage> lines = new ArrayList<>();

        try {
            // Récupération du fichier
            File jsonFile = getFileFromResource(DATA_FILE_NAME);
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonFile.toURI())));

            // Transformation en liste d'objet pour faire croire qu'on récupère une DAO ^^
            ObjectMapper mapper = new ObjectMapper();
            mapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
            lines = mapper.readValue(jsonString, new TypeReference<List<DevExcusesMessage>>() {
            });

        } catch (IOException | URISyntaxException ex) {
            // LOGGER une erreur de la BDD
        }

        return lines;

    }

    /**
     * Ajoute une excuse à la liste stockée dans les ressources à défaut de DB
     *
     * @return la liste des excuses stockée dans les ressources
     */
    public boolean addToExcusesListDataBase(DevExcusesMessage newExcuse) throws Exception {
        boolean rFlag = false;

        List<DevExcusesMessage> excusesList = getExcusesList();
        if (newExcuse != null) {
            Optional<DevExcusesMessage> existingExcuseWithSameCode =
                            excusesList.stream().filter(excuse -> excuse.getHttpcode().compareTo(newExcuse.getHttpcode()) == 0).findFirst();
            if (!existingExcuseWithSameCode.isPresent()) {
                excusesList.add(newExcuse);
            } else {
                throw new Exception("HibernateException : existingObject");
            }
        }

        try {
            writeExcuseListToFile(excusesList);
            rFlag = true;

        } catch (Exception ex) {
            // LOGGER une  erreur de la BDD
            throw ex;
        }

        return rFlag;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public void writeExcuseListToFile(List<DevExcusesMessage> excusesList) throws Exception {
        // Transforme la liste d'objets en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(excusesList);

        FileOutputStream outputStream = new FileOutputStream(getFileFromResource(DATA_FILE_NAME));
        byte[] strToBytes = jsonString.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }

}
