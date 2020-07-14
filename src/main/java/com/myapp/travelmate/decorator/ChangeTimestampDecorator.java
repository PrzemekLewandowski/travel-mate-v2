package com.myapp.travelmate.decorator;

import com.myapp.travelmate.model.AbstractDocument;

import java.time.LocalDateTime;

class ChangeTimestampDecorator implements TimestampSetter {
    private AbstractDocument abstractDocument;

    public ChangeTimestampDecorator(AbstractDocument abstractDocument) {
        this.abstractDocument = abstractDocument;
    }

    @Override
    public void setTimestamp() {
        abstractDocument.setEditedAt(LocalDateTime.now());
    }
}
