import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";
    private static final String TAG = StreamUtil.class.getSimpleName();
   

    public static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        int read = 0;
		byte[] bytes = new byte[1024];
		
		try {
			FileOutputStream out = new FileOutputStream(tempFile);
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		}
		
		catch(Throwable t) {
				Log.e(TAG,t.getMessage(),t);
			}
		
       
        return tempFile;
    }

}
