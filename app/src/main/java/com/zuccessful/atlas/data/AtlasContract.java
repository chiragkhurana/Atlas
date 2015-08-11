package com.zuccessful.atlas.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class AtlasContract {

    public static final String CONTENT_AUTHORITY = "com.zuccessful.atlas";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class CountriesEntry implements BaseColumns{
        public static final String TABLE_NAME = "countries";
        public static final String COLUMN_COUNTRY_NAME = "country_name";
        public static final String COLUMN_CAPITAL = "capital";
        public static final String COLUMN_CALLING_CODE = "calling_code";
    }
}
