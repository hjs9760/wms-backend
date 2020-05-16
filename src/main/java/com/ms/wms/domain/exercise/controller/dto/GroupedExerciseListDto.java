package com.ms.wms.domain.exercise.controller.dto;

import com.ms.wms.domain.exercise.domain.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by momentjin@gmail.com on 2020-05-17
 * Github : http://github.com/momentjin
 */

@Getter
public class GroupedExerciseListDto {

    List<GroupdExerciseItemDto> exerciseList = new ArrayList<>();

    public GroupedExerciseListDto(List<FindExerciseDetailDto> exerciseDetailList) {

        Map<Category, List<FindExerciseDetailDto>> map = exerciseDetailList
                        .stream()
                        .collect(Collectors.groupingBy(FindExerciseDetailDto::getCategory));

        for (Category category : map.keySet()) {
            this.exerciseList.add(new GroupdExerciseItemDto(category, map.get(category)));
        }
    }

    @Getter
    static class GroupdExerciseItemDto {

        private Category category;
        private List<FindExerciseDetailDto> exerciseDetailList;

        GroupdExerciseItemDto(Category category, List<FindExerciseDetailDto> exerciseDetailList) {
            this.category = category;
            this.exerciseDetailList = exerciseDetailList;
        }
    }
}
