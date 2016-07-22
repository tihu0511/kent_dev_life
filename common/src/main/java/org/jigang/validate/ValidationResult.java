package org.jigang.validate;

import java.util.Map;

/**
 * 校验结果
 *
 * Created by wujigang on 16/7/11.
 */
public class ValidationResult {
    /** 是否校验通过 **/
    private boolean passed;

    /** 校验结果信息 **/
    private Map<String, String> errorMsg;

    public ValidationResult() {
        this.passed = true;
    }

    public ValidationResult(boolean passed, Map<String, String> errorMsg) {
        this.passed = passed;
        this.errorMsg = errorMsg;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Map<String, String> errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 获取校验的错误信息
     * @return
     */
    public String getValidateErrorMsg() {
        if (null == errorMsg || errorMsg.size() <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : errorMsg.entrySet()) {
            sb.append("'").append(entry.getKey()).append("':").append(entry.getValue()).append(";");
        }

        return sb.length() > 0 ? sb.substring(0, sb.length() - 1).toString() : null;
    }
}
