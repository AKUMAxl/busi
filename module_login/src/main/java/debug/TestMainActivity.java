package debug;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yangchedou.lib_common.Base.BaseActivity;

/**
 * Created by ADMIN on 2017/11/9.
 */
@Route(path = "/module_main/main")
public class TestMainActivity extends BaseActivity {
    @Override
    public void loadView() {
        setContentView(com.yangchedou.lib_common.R.layout.activity_error);
    }

    @Override
    public void initData() {

    }

    @Override
    public void firstRequestNet() {

    }

    @Override
    public void fresh() {

    }
}
