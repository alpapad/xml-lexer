package com.aktarma.xml.tokenizer.tokens;


public interface ElementTagPart extends TokenPart{

    String getKey();
    
    void setParent(ElementPart parent);
    
    ElementPart getParent();
    
}
