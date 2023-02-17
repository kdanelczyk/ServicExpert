package com.kamil.servicExpert.db.visitor;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.UsedElement;

@Component
public interface RepairVisitor {

	BigDecimal visit(UsedElement usedElement);
}
