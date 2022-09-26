package com.fty1.tools.free.data.kernel;

import com.fty1.tools.free.data.kernel.SQL;

public class NavicatSQL implements SQL {

    private String text;


    public NavicatSQL(String text) {
        this.text = text;
    }

    @Override
    public String finalSQLText() {
        return text;
    }
}
