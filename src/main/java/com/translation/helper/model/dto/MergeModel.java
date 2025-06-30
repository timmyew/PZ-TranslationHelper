package com.translation.helper.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MergeModel {
    List<String> oldFile;
    List<String> newFile;
    List<String> mergedFile;
    List<String> mergeFileMissingAttributes;
    List<String> newFileMissingAttributes;
    List<String> newAttributesList;
}
