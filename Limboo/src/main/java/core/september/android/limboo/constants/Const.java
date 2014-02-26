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

    public final static Class LANDING_ACTIVITY = ActionBarDrawerActivity.class;

    private final static String MODEL_PACKAGE = "core.september.android.limboo.models";
    //private static List<Class<? extends CRUDable>> models = null;
    private static Class<? extends CRUDable>[] _models = null;

    public static Class[] getModelClasses() {
        if (_models == null) {
            _models = new Class[]{AppUser.class};
           /* try {
                models = new ArrayList<Class<? extends CRUDable>>();
                ImmutableList<ClassPath.ClassInfo> classes = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(MODEL_PACKAGE).asList();
                for(ClassPath.ClassInfo clz : classes){
                    models.add((Class<? extends CRUDable>) clz.load());
                }
                _models = models.toArray(new Class[models.size()]);

            } catch (IOException e) {
                android.util.Log.e(Const.class.getSimpleName(),e.getMessage(),e);
            }*/
        }

        return _models;

    }

}