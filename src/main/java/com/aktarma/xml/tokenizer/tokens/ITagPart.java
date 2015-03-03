package com.aktarma.xml.tokenizer.tokens;


public interface ITagPart extends IToken{

    String getKey();
    
    void setParent(IElement parent);
    
    IElement getElement();
    
}
