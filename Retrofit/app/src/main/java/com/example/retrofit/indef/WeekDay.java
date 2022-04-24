package com.example.retrofit.indef;

/**
 * 1、每一个枚举值都是一个对象,增加额外的内存消耗 相比于静态常量, Enum会花费两倍以上的内存
 * 2、较多的使用 Enum 会增加 DEX 文件的大小
 */
public enum WeekDay {
    SUNDAY, MONDAY
}
