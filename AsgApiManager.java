package com.sate.managers;

import com.sate.common.defintions.asg.Customer;
import com.sate.common.defintions.asg.Tender;
import com.sate.common.defintions.asg.Unit;
import com.sate.common.gup.enums.ResponseStatus;
import com.sate.common.json.request.AsgApiRequest;
import com.sate.common.json.response.SateResponse;
import com.sate.persistence.access.ScenarioAccess;
import com.sate.persistence.entity.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AsgApiManager {

    private ScenarioAccess scenarioAccess;

    public SateResponse createAsgScenario(AsgApiRequest request){
        SateResponse response = new SateResponse();

        String scenarioName = request.getScenarioName();
        String creationDate = request.getCreationDate();
        String project = request.getProject();
        String scenarioTable = "Tbl_" + project + "_" + scenarioName;
        LocalDateTime timestamp= LocalDateTime.parse(creationDate);

        Scenario scenario = Scenario.builder().
                scenarioName(scenarioName).
                scenarioDescription("ASG Scenario").
                scenarioStatus("Design").
                abortOnError("Yes").
                scenarioType("Regression").
                recordMovie("Yes").
                scenarioPath("ASG").
                app("POS").
                highlight(true).
                performance(false).build();

        if (scenarioAccess.checkIfTableExists(scenarioTable)){
            return response;
        }

        scenarioAccess.createScenarioTable(scenarioTable);
        List<Unit> actions = request.getListOfItems();
        List<Customer> customers = request.getListOfCustomer();
        List<Tender> tender = request.getListOfTenders();

        return response;
    }


}
