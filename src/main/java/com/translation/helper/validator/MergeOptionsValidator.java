package com.translation.helper.validator;

import com.translation.helper.constant.ExceptionConstants;
import com.translation.helper.exception.MergeOptionsException;
import com.translation.helper.model.dto.MergeOptions;

public class MergeOptionsValidator extends AbstractValidator<MergeOptions> {
    private static MergeOptionsValidator instance;
    private MergeOptionsValidator(){};

    public static MergeOptionsValidator getInstance(){
        if (instance == null){
            instance = new MergeOptionsValidator();
        }
        return instance;
    }

    @Override
    public void validate(MergeOptions data) {
        if (data.getNewFilePath().isBlank() || data.getOldFilePath().isBlank()) {
            throw new MergeOptionsException(data.getNewFilePath().isBlank() ?
                    ExceptionConstants.MERGE_OPTIONS_OLD_FILE_INVALID_PATH :
                    ExceptionConstants.MERGE_OPTIONS_NEW_FILE_INVALID_PATH);
        }
    }
}
