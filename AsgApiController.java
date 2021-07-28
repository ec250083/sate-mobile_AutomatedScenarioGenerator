package com.sate.controllers;

import com.sate.common.json.request.AsgApiRequest;
import com.sate.common.json.request.CycleApiRequest;
import com.sate.common.json.response.SateResponse;
import com.sate.managers.ApiManager;
import com.sate.managers.AsgApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"/api"})
@Configuration
public class AsgApiController {

    @Autowired
    AsgApiManager asgManager;

        @PostMapping("/asgAPI")
        public SateResponse createScenario(@RequestBody AsgApiRequest request)  {
            SateResponse response ;

            response = asgManager.createAsgScenario(request);

            return response;
        }
}
