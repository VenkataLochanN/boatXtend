package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes2.dex */
public interface NodeTestVisitor {
    void visit(AllElementTest allElementTest);

    void visit(AttrTest attrTest);

    void visit(ElementTest elementTest);

    void visit(ParentNodeTest parentNodeTest) throws XPathException;

    void visit(TextTest textTest);

    void visit(ThisNodeTest thisNodeTest);
}