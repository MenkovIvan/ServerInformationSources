package ru.menkov.informsources.controllers.commerce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.menkov.informsources.services.commerce.FinanceService;

@RestController
@Slf4j
public class FinanceController {

    private FinanceService financeService;

    @Autowired
    public FinanceController(FinanceService financeService){
        this.financeService = financeService;
    }

    @RequestMapping("/finance/add")
    public String addFinance(@RequestBody String inputJson){
        log.info("Request to add Finance, body = {} ", inputJson);
        return financeService.addFinance(inputJson);
    }

    @RequestMapping("/finance/info/id")
    public String getInfoIDFinance(@RequestBody String inputJson){
        log.info("Request to get info about Finance by id, body = {} ", inputJson);
        return financeService.getInfoIDFinance(inputJson);
    }

    @RequestMapping("/finance/info/username")
    public String getInfoUserNameFinance(@RequestBody String inputJson){
        log.info("Request to get info about Finance by user name, body = {} ", inputJson);
        return financeService.getInfoUserNameFinance(inputJson);
    }

    @RequestMapping("/finance/update/agreement")
    public String updateAgreementFinance(@RequestBody String inputJson){
        log.info("Request to update agreement in Finance, body = {} ", inputJson);
        return financeService.updateAgreementFinance(inputJson);
    }

    @RequestMapping("/finance/update/card")
    public String updateCardFinance(@RequestBody String inputJson){
        log.info("Request to update card in Finance, body = {} ", inputJson);
        return financeService.updateCardFinance(inputJson);
    }


}
