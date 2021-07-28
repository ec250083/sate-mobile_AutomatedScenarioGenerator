package com.sate.common.defintions.asg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tender {
    @JsonProperty("Index")
    public int index;
    @JsonProperty("ActionName")
    public String actionName;
    @JsonProperty("Tender")
    public String tender;

}
