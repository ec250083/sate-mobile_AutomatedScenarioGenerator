package com.sate.common.defintions.asg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Promotion {

    @JsonProperty("ActionId")
    public int actionId;
    @JsonProperty("UnitId")
    public int unitId;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("SeqNum")
    public String seqNum;
    @JsonProperty("ItemDesc")
    public String itemDesc;
    @JsonProperty("ItemQty")
    public String itemQty;
    @JsonProperty("ItemPrice")
    public Object itemPrice;
    @JsonProperty("ItemCounter")
    public String itemCounter;
    @JsonProperty("ItemStatus")
    public String itemStatus;
}
