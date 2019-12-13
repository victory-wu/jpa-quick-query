package com.victor.wu.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SearchSymbolType {
    EQ(false),
    NEQ(false),
    LK(false),
    NLK(false),
    IN(true),
    NIN(true),
    BT(true),
    LT(false),
    GT(false);
    private boolean array;
}
