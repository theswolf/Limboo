package core.september.android.limboo.constants;

import core.september.android.limboo.activities.ActionBarDrawerActivity;
import core.september.android.limboo.ifaces.CRUDable;
import core.september.android.limboo.models.AppUser;

/**
 * Created by christian on 19/02/14.
 */
public class Const {

    public final static String APPLICATION_ID = "7289";
    public final static String AUTHORIZATION_KEY = "HSxgbucxBk-8qvg";
    public final static String AUTHORIZATION_SECRET = "8xvwxEayHXG-X-p";

    public final static String END_OF_LINE = System.getProperty("line.separator");

    public final static Class LANDING_ACTIVITY = ActionBarDrawerActivity.class;

    private final static String MODEL_PACKAGE = "core.september.android.limboo.models";


    //private static List<Class<? extends CRUDable>> models = null;
    public static Class<? extends CRUDable>[] _models = new Class[]{AppUser.class};


}