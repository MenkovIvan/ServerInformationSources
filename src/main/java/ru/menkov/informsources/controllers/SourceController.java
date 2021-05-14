package ru.menkov.informsources.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.menkov.informsources.services.SourceService;

@RestController
@Slf4j
public class SourceController {
    private SourceService sourceService;

    @Autowired
    public SourceController(SourceService sourceService){
        this.sourceService = sourceService;
    }

    @RequestMapping("/source/add")
    public String addSource(@RequestBody String inputJson){
        log.info("Request to add Source, body = { " + inputJson + "}");
        return sourceService.addSource(inputJson);
    }

    @RequestMapping("/source/delete")
    public String deleteSource(@RequestBody String inputJson){
        log.info("Request to delete Source, body = {} ", inputJson);
        return sourceService.deleteSource(inputJson);
    }

    @RequestMapping("/source/update/descroption")
    public String updateSourceDescription(@RequestBody String inputJson){
        log.info("Request to update description of Source, body = {} ", inputJson);
        return sourceService.updateSourceDescription(inputJson);
    }

    @RequestMapping("/source/update/value")
    public String updateSourceValue(@RequestBody String inputJson){
        log.info("Request to update value of Source, body = {} ", inputJson);
        return sourceService.updateSourceValue(inputJson);
    }

    @RequestMapping("/source/all")
    public String getAllSource(){
        log.info("Request to get all Source, body");
        return sourceService.getAllSource();
    }

    @RequestMapping("/source/search")
    public String searchSource(@RequestBody String inputJson){
        log.info("Request to search Source by name, body = {} ", inputJson);
        return sourceService.searchSource(inputJson);
    }

    @RequestMapping("/source/all/catalog")
    public String getAllSourcesFromCatalog(@RequestBody String inputJson){
        log.info("Request to get all Sources from Catalog, body = {} ", inputJson);
        return sourceService.getAllSourcesFromCatalog(inputJson);
    }

    @RequestMapping("/source/all/user")
    public String getAllSourcesUser(@RequestBody String inputJson){
        log.info("Request to get all Sources created by User, body = {} ", inputJson);
        return sourceService.getAllSourcesFromUser(inputJson);
    }
}
