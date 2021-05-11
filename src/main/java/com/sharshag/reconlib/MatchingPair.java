package com.sharshag.reconlib;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchingPair {

    private UUID matchingId;
    private List<BaseTransaction> lhsItems = new ArrayList<>();
    private List<BaseTransaction> rhsItems = new ArrayList<>();

    public MatchingPair(BaseTransaction lhsItem, BaseTransaction rhsItem) {

        matchingId = UUID.randomUUID();
        this.lhsItems.add(lhsItem);
        this.rhsItems.add(rhsItem);
    }

    public MatchingPair(List<BaseTransaction> lhsItems, List<BaseTransaction> rhsItems) {
        matchingId = UUID.randomUUID();
        this.lhsItems.addAll(lhsItems);
        this.rhsItems.addAll(rhsItems);
    }

    public List<BaseTransaction> getLhsItems() {
        return lhsItems;
    }


    public List<BaseTransaction> getRhsItems() {
        return rhsItems;
    }

}
