package com.sate.common.defintions.asg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    @JsonProperty("Index")
    public int index;
    @JsonProperty("ActionName")
    public String actionName;
    @JsonProperty("Customer_Id")
    public String customer_Id;
    @JsonProperty("GlobalParam")
    public String globalParam;

}
