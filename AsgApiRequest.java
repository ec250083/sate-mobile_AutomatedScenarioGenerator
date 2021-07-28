package com.sate.common.json.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sate.common.defintions.asg.Customer;
import com.sate.common.defintions.asg.Tender;
import com.sate.common.defintions.asg.Unit;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AsgApiRequest {

    private Long id;

    @JsonProperty("scenarioName")
    private String scenarioName;

    @JsonProperty("project")
    private String project;

    @JsonProperty("creationDate")
    private String creationDate;

    @JsonProperty("ListOfCustomers")
    private List<Customer> listOfCustomer;

    @JsonProperty("ListOfItems")
    private List<Unit> listOfItems;

    @JsonProperty("ListOfTenders")
    private List<Tender> listOfTenders;

    @JsonProperty("ListOfPromotion")
    private List<Tender> listOfPromotions;


    //@JsonProperty("date")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //@JsonDeserialize()
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    //private LocalDateTime date;

}
