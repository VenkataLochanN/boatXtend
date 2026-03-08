package com.hp.hpl.sparta.xpath;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BooleanExpr {
    public abstract void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException;
}