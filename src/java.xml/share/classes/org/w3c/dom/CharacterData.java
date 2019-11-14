package org.w3c.dom;

import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface CharacterData extends Node {

    @SideEffectFree
    public String getData() throws DOMException;

    public void setData(String data) throws DOMException;

    @Pure
    public int getLength();

    @SideEffectFree
    public String substringData(int offset, int count) throws DOMException;

    public void appendData(String arg) throws DOMException;

    public void insertData(int offset, String arg) throws DOMException;

    public void deleteData(int offset, int count) throws DOMException;

    public void replaceData(int offset, int count, String arg) throws DOMException;
}
