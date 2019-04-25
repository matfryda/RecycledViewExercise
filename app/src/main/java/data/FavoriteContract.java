package data;

import android.provider.BaseColumns;

public class FavoriteContract {
    public static final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_EMPLOYEE_ID = "employeeid";
        public static final String COLUMN_EMPLOYEE_NAME = "employeename";
    }
}
