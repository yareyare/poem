package com.ivy.model.po;

public class PoemDetailWithBLOBs extends com.com.ivy.model.po.PoemDetail {
    private String content;

    private String reference;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }
}