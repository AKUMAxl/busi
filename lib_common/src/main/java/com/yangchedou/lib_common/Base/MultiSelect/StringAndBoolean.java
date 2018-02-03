package com.yangchedou.lib_common.Base.MultiSelect;

/**
 * Created by ADMIN on 2017/12/5.
 */

public class StringAndBoolean {

    private String name;

    private Boolean checked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "StringAndBoolean{" +
                "name='" + name + '\'' +
                ", checked=" + checked +
                '}';
    }
}
