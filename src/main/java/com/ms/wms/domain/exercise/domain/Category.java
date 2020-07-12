package com.ms.wms.domain.exercise.domain;

import lombok.Getter;

@Getter
public enum Category {

    SHOULDER("어깨")
    , BACK("등")
    , CHEST("가슴")
    , LOWERBODY("하체")
    , Bicep("이두")
    , Tricep("삼두");

    private String name;

    Category(String name) {
        this.name = name;
    }
}
