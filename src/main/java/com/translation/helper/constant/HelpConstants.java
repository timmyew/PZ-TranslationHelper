package com.translation.helper.constant;

import com.translation.helper.enums.OptionEnum;
import com.translation.helper.model.dto.VersionModel;

public class HelpConstants {
    public static final String MERGE_EXAMPLE = String.format("merge %s %s %s=\"file-path\" %s=\"file-path\" %s\"file-path\"",
            OptionEnum.SORT, OptionEnum.META_DATA, OptionEnum.OLD_FILE, OptionEnum.NEW_FILE, OptionEnum.MERGED_OUTPUT_FILE);
    public static final String MERGE_DESCRIPTION = String.format("""
            Merge-Program:
                Description:
                This program merged two files and use the old-file as file of truth.
                That mean the old attributes stay themself and it check just for new additions to the old file from the new file.
                \s
                Options:
                    REQUIRED: %s: The path of the current old file.
                    REQUIRED: %s: The path of the new file.
                    REQUIRED: %s: The path of the merged output file.
                \s
                    OPTIONAL: %s: Activate the alphabetical sorting algorithm that used on the merged file.
                    OPTIONAL: %s: Generate metadata and write them to the terminal.
                \s
                Example:
                %s
           \s""", OptionEnum.OLD_FILE, OptionEnum.NEW_FILE, OptionEnum.MERGED_OUTPUT_FILE, OptionEnum.SORT, OptionEnum.META_DATA, MERGE_EXAMPLE);

    public static final String HELP_DESCRIPTION = String.format("""
            ###### TranslationHelper version %s.%s.%s ######
            """, VersionModel.major, VersionModel.minor, VersionModel.patch);
}
