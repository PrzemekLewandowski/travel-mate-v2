package com.myapp.travelmate.decorator;

import com.myapp.travelmate.model.AbstractDocument;

import java.time.LocalDateTime;

public class CreateTimestampDecorator implements TimestampSetter{
    private AbstractDocument abstractDocument;

    public CreateTimestampDecorator(AbstractDocument abstractDocument) {
        this.abstractDocument = abstractDocument;
    }

    @Override
    public void setTimestamp() {
        abstractDocument.setCreatedAt(LocalDateTime.now());
    }
}
