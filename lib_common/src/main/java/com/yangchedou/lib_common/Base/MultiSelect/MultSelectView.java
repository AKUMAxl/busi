package com.yangchedou.lib_common.Base.MultiSelect;

import java.util.List;

/**
 * Created by ADMIN on 2017/12/5.
 */

public interface MultSelectView {

    void AllSelect(boolean targetState);

    void notifyRv(List<StringAndBoolean> list);

}
