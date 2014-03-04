package core.september.android.limboo.models;

import com.niusounds.sqlite.Persistence;
import com.niusounds.sqlite.PrimaryKey;
import com.quickblox.internal.core.helper.FileHelper;
import com.quickblox.module.custom.model.QBCustomObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import core.september.android.limboo.ifaces.CRUDable;

/**
 * Created by christian on 04/03/14.
 */



public class Board implements CRUDable {

    private final static String QBClass = "Board";

    @Persistence
    @PrimaryKey(autoIncrement = true)
    private long _id;

    @Persistence
    private byte[] bitmap;

    @Persistence
    private long createdAt;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Board() {
        super();
    }

    public void upload(Context context) {
        //int fileId2 = R.raw.sample_file2;
        InputStream is2 = new ByteArrayInputStream(getBitmap());



        File file2 = FileHelper.getFileInputStream(is2, "sample_file2.txt", "qb_snippets12");

        QBCustomObject qbCustomObject = new
        QBCustomObjectsFiles.uploadFile(file2, qbCustomObject, "avatar", new QBCallbackImpl() {
            @Override
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    Log.i(TAG, ">>> file updated successfully");
                } else {
                    Log.e("Errors",result.getErrors().toString());
                }
            }
        });
    }
}
