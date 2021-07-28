package com.sate.common.defintions.asg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Unit {

    @JsonProperty("Index")
    public int index;
    @JsonProperty("ActionName")
    public String actionName;
    @JsonProperty("Sale_Method")
    public String sale_Method;
    @JsonProperty("Item_Number")
    public String item_Number;
    @JsonProperty("Qty")
    public String qty;
    @JsonProperty("Weight")
    public String weight;
    @JsonProperty("ItemDesc")
    public String itemDesc;
    @JsonProperty("ItemQty")
    public String itemQty;
    @JsonProperty("ItemPrice")
    public String itemPrice;
    @JsonProperty("ItemCounter")
    public String itemCounter;
    @JsonProperty("ItemStatus")
    public String itemStatus;

}
