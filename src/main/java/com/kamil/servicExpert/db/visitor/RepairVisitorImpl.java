package com.kamil.servicExpert.db.visitor;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.UsedElement;

@Component
public class RepairVisitorImpl implements RepairVisitor{

	@Override
    public BigDecimal visit(UsedElement usedElement) {
        return usedElement.getPriceOfElement();
    }
}
