package com.kamil.servicExpert.db.visitor;

import java.math.BigDecimal;

import com.kamil.servicExpert.db.model.UsedElement;

public class RepairVisitorImpl implements RepairVisitor{

	@Override
    public BigDecimal visit(UsedElement usedElement) {
        return usedElement.getPriceOfElement();
    }
}
