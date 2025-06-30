package com.translation.helper.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MergeOptions {
    String oldFilePath;
    String newFilePath;
    String mergedOutputFilePath;
    boolean doSort;
    boolean doMetaData;
}