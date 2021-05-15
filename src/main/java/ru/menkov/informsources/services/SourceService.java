package ru.menkov.informsources.services;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.custom.User;
import ru.menkov.informsources.model.product.Catalog;
import ru.menkov.informsources.model.product.Source;
import ru.menkov.informsources.repositories.CatalogRepository;
import ru.menkov.informsources.repositories.SourceRepository;
import ru.menkov.informsources.repositories.UserRepository;

import java.util.ArrayList;

@Service
@Slf4j
public class SourceService extends ru.menkov.informsources.services.Service {

    private SourceRepository sourceRepository;
    private CatalogRepository catalogRepository;
    private UserRepository userRepository;

    private Gson gson = new Gson();

    @Autowired
    public SourceService(SourceRepository sourceRepository, CatalogRepository catalogRepository, UserRepository userRepository){
        this.sourceRepository = sourceRepository;
        this.catalogRepository = catalogRepository;
        this.userRepository = userRepository;
    }

    public String addSource(String inputJson){
        Source sourceFromClient = gson.fromJson(inputJson, Source.class);

        String message = "Result of add = {";
        Integer status;

        if (sourceRepository.existsSourceByName(sourceFromClient.getName())){

            log.info("exist in db");
            message += "incorrect, Source with input name is exist";
            status = RequestStatus.ERROR.getStatus();

        } else{

            sourceRepository.save(sourceFromClient);
            log.info("add to db");
            message += "correct, add Source";
            status = RequestStatus.OK.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String deleteSource(String inputJson){

        Source sourceFromClient = gson.fromJson(inputJson, Source.class);

        String message = "Result of delete = {";
        Integer status;

        if (sourceRepository.existsSourceByName(sourceFromClient.getName())){

            sourceRepository.deleteSourceByName(sourceFromClient.getName());

            log.info("exist in db, delete him");
            message += "correct, Source with input name is exist, delete him";
            status = RequestStatus.OK.getStatus();

        } else{

            log.info("not exist in db");
            message += "incorrect, Source with input name not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String updateSourceDescription(String inputJson){
        Source sourceFromClient = gson.fromJson(inputJson, Source.class);

        String message = "Result of update = {";
        Integer status;

        if (sourceRepository.existsSourceByName(sourceFromClient.getName())){

            sourceRepository.setSourceDescriptionByName(sourceFromClient.getDescription(), sourceFromClient.getName());

            log.info("exist in db, update description");
            message += "correct, Source with input name is exist, update description";
            status = RequestStatus.OK.getStatus();

        } else{

            log.info("not exist in db");
            message += "incorrect, Source with input name not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String updateSourceValue(String inputJson){
        Source sourceFromClient = gson.fromJson(inputJson, Source.class);

        String message = "Result of update = {";
        Integer status;

        if (sourceRepository.existsSourceByName(sourceFromClient.getName())){

            sourceRepository.setSourceValueByName(sourceFromClient.getValue(), sourceFromClient.getName());

            log.info("exist in db, update value");
            message += "correct, Source with input name is exist, update value";
            status = RequestStatus.OK.getStatus();

        } else{

            log.info("not exist in db");
            message += "incorrect, Source with input name not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String getAllSource(){
        Iterable<Source> SourcesList = sourceRepository.findAll();
        String outPutJson = gson.toJson(SourcesList);
        log.info("findAll Sources = {}", outPutJson);
        return outPutJson;
    }

    public String searchSource(String inputJson){
        Source sourceFromClient = gson.fromJson(inputJson, Source.class);
        Source sourceFromDb = sourceRepository.findSourceByName(sourceFromClient.getName());

        String message = "Result of search = {";
        Integer status;

        if (sourceFromDb != null){

            log.info("exist in db, get to client");
            message += "correct, Source with input name is exist, get result search";
            status = RequestStatus.OK.getStatus();

        } else{

            log.info("not exist in db");
            message += "incorrect, Source with input name not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonStringWithSource(sourceFromDb,message,status);
    }

    public String getAllSourcesFromCatalog(String inputJson){
        Catalog catalogFromClient = gson.fromJson(inputJson, Catalog.class);
        Catalog catalogFromDb = catalogRepository.findCatalogByName(catalogFromClient.getName());

        String message = "Result of search = {";
        Integer status;

        if (catalogFromDb != null){
            Iterable<Source> sourceCatalog = sourceRepository.findAllByCatalog_id(catalogFromClient.getId());

            return gson.toJson(sourceCatalog);
        } else {
            log.info("catalog with inputName is not exist in db");
            message += "incorrect, Catalog with input name is not exist";
            status = RequestStatus.ERROR.getStatus();
            return getJsonString(message,status);
        }
    }

    public String getAllSourcesFromUser(String inputJson){
        User userFromClient = gson.fromJson(inputJson, User.class);
        User userFromDb = userRepository.findUserByName(userFromClient.getName());

        String message = "Result of search = {";
        Integer status;

        if (userFromDb != null){
            ArrayList<Source> sourceUser = sourceRepository.findAllByUser_id(userFromDb.getId());

            return gson.toJson(sourceUser);
        } else {
            log.info("user with inputName is not exist in db");
            message += "incorrect, User with input name is not exist";
            status = RequestStatus.ERROR.getStatus();
            return getJsonString(message,status);
        }
    }

    private String getJsonStringWithSource(Source source, String message, Integer status){
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);

        jsonObject.add("Source",gson.toJsonTree(source));
        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);

        return jsonToClient;
    }
}
