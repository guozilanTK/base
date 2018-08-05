package tk.guozilan.base.controller.propertyeditors;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.beans.PropertyEditorSupport;
import java.util.Date;


public class CustomDateEditor extends PropertyEditorSupport {

    private final boolean allowEmpty;

    private final int exactDateLength;

    public CustomDateEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public CustomDateEditor(boolean allowEmpty, int exactDateLength) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StrUtil.isNotBlank(text)) {
            setValue(null);
        } else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
            throw new IllegalArgumentException(
                    "Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
        } else {
            try {
                setValue(DateUtil.parse(text));
            } catch (Exception ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? DateUtil.format(value, DatePattern.NORM_DATE_PATTERN) : "");
    }

}