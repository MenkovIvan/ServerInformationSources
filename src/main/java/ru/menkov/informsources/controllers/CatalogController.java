package ru.menkov.informsources.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.menkov.informsources.services.CatalogService;

@RestController
@Slf4j
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @RequestMapping("/catalog/add")
    public String addCatalog(@RequestBody String inputJson){
        log.info("Request to add Catalog, body = { " + inputJson + "}");
        return catalogService.addCatalog(inputJson);
    }

    @RequestMapping("/catalog/delete")
    public String deleteCatalog(@RequestBody String inputJson){
        log.info("Request to delete Catalog, body = { " + inputJson + "}");
        return catalogService.deleteCatalog(inputJson);
    }

    @RequestMapping("/catalog/update")
    public String updateCatalog(@RequestBody String inputJson){
        log.info("Request to update description of Catalog, body = { " + inputJson + "}");
        return catalogService.updateCatalog(inputJson);
    }

    @RequestMapping("/catalog/all")
    public String getAllCatalog(@RequestBody String inputJson){
        log.info("Request to get all Catalogs, body");
        return catalogService.getAllCatalog();
    }

    @RequestMapping("/catalog/search")
    public String searchCatalog(@RequestBody String inputJson){
        log.info("Request to search Catalog by name, body = { " + inputJson + "}");
        return catalogService.searchCatalog(inputJson);
    }
}
