package com.fty1.tools.free.data.kernel;

import java.util.concurrent.Executor;

public interface SQLExecutor {

    RowDataResult execute(RowDataSource dataSource, SQL sql);

}
