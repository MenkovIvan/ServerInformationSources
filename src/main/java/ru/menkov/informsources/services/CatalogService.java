package ru.menkov.informsources.services;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.product.Catalog;
import ru.menkov.informsources.repositories.CatalogRepository;

@Service
@Slf4j
public class CatalogService extends ru.menkov.informsources.services.Service {

    private Gson gson = new Gson();

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository){
        this.catalogRepository = catalogRepository;
    }

    public String addCatalog(String inputJson){
        Catalog catalogFromClient = gson.fromJson(inputJson, Catalog.class);

        String message = "Result of add = {";
        Integer status;

        if (catalogRepository.existsCatalogByName(catalogFromClient.getName())){

            log.info("exist in db");
            message += "incorrect, catalog with input name is exist";
            status = RequestStatus.ERROR.getStatus();

        } else{

            catalogRepository.save(catalogFromClient);
            log.info("add to db");
            message += "correct, add catalog";
            status = RequestStatus.OK.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String deleteCatalog(String inputJson){

        Catalog catalogFromClient = gson.fromJson(inputJson, Catalog.class);

        String message = "Result of delete = {";
        Integer status;

        if (catalogRepository.existsCatalogByName(catalogFromClient.getName())){

            catalogRepository.delete(catalogFromClient);

            log.info("exist in db, delete him");
            message += "correct, catalog with input name is exist, delete him";
            status = RequestStatus.OK.getStatus();

        } else{

            log.info("not exist in db");
            message += "incorrect, catalog with input name not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String updateCatalog(String inputJson){
        Catalog catalogFromClient = gson.fromJson(inputJson, Catalog.class);

        String message = "Result of update = {";
        Integer status;

        if (catalogRepository.existsCatalogByName(catalogFromClient.getName())){

            catalogRepository.setCatalogDescriptionByName(catalogFromClient.getDescription(), catalogFromClient.getName());

            log.info("exist in db, update description");
            message += "correct, catalog with input name is exist, update description";
            status = RequestStatus.OK.getStatus();

        } else{

            log.info("not exist in db");
            message += "incorrect, catalog with input name not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String getAllCatalog(){
        Iterable<Catalog> catalogsList = catalogRepository.findAll();
        String outPutJson = gson.toJson(catalogsList);
        log.info("findAll catalogs = {}", outPutJson);
        return outPutJson;
    }

    public String searchCatalog(String inputJson){
        Catalog catalogFromClient = gson.fromJson(inputJson, Catalog.class);
        Catalog catalogFromDb = catalogRepository.findCatalogByName(catalogFromClient.getName());

        String message = "Result of search = {";
        Integer status;

        if (catalogFromDb != null){

            log.info("exist in db, get to client");
            message += "correct, catalog with input name is exist, get result search";
            status = RequestStatus.OK.getStatus();

        } else{

            log.info("not exist in db");
            message += "incorrect, catalog with input name not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonStringWithCatalog(catalogFromDb,message,status);
    }

    private String getJsonStringWithCatalog(Catalog catalog, String message, Integer status){
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);

        jsonObject.add("catalog",gson.toJsonTree(catalog));
        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);

        return jsonToClient;
    }
}
