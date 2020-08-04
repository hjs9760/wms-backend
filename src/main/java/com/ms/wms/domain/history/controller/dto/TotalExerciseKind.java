package com.ms.wms.domain.history.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class TotalExerciseKind {

    private List<String> exerciseNameList = new ArrayList<>();
    private int totalExerciseKinds;
    HashMap<String, Integer> categoryMap = new HashMap<>();


    public void createTotalExerciseKind(String name, Category category, Integer count) {

        if( !exerciseNameList.contains(name)) {
            exerciseNameList.add(name);
            totalExerciseKinds ++;
        }


        if(categoryMap.containsKey(category.getName()) == false) {
            categoryMap.put(category.getName(), count);
        } else {
            categoryMap.put(category.getName(), categoryMap.get(category.getName()) + count);
        }

    }

}
