package com.g50.view.builders;

import com.g50.view.ViewProperty;

import java.util.BitSet;

public class DefaultWallViewerBuilder extends WallViewerBuilder{
    public DefaultWallViewerBuilder() {
        super();

        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x0}), new ViewProperty(' '));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x1}), new ViewProperty("#0000FF",(char)(131)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x2}), new ViewProperty("#0000FF",(char)(129)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x3}), new ViewProperty("#0000FF",(char)(134)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x4}), new ViewProperty("#0000FF",(char)(130)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x5}), new ViewProperty("#0000FF",(char)(141)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x6}), new ViewProperty("#0000FF",(char)(133)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x7}), new ViewProperty("#0000FF",(char)(136)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x8}), new ViewProperty("#0000FF",(char)(128)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0x9}), new ViewProperty("#0000FF",(char)(135)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0xA}), new ViewProperty("#0000FF",(char)(142)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0xB}), new ViewProperty("#0000FF",(char)(139)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0xC}), new ViewProperty("#0000FF",(char)(132)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0xD}), new ViewProperty("#0000FF",(char)(138)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0xE}), new ViewProperty("#0000FF",(char)(137)));
        this.properties.put(BitSet.valueOf(new byte[]{(byte) 0xF}), new ViewProperty("#0000FF",(char)(140)));

    }
}
