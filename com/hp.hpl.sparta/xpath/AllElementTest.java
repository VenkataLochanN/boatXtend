package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes2.dex */
public class AllElementTest extends NodeTest {
    static final AllElementTest INSTANCE = new AllElementTest();

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return false;
    }

    public String toString() {
        return "*";
    }

    private AllElementTest() {
    }

    @Override // com.hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}