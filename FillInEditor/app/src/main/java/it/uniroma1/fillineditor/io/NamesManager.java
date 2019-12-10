package it.uniroma1.fillineditor.io;

public class NamesManager {
    private static final String FOLDER_NAME = "FillInEditor";
    private static final String DOC_FOLDER_NAME = "compilableDocs";
    private final static String CONFIG_FILE = "configuration.json";
    private static final String SEPARATOR = "_";

//    private static final NamesManager ourInstance = new NamesManager();
//    public static NamesManager getInstance() {
//        return ourInstance;
//    }
//
//    private NamesManager() {}
//
//    public static File getUserConfigFileName() {
//        return new File(NamesManager.baseDirectory(), CONFIG_FILE);
//    }
//
//
//    public static String getJsonName(ItemData data) {
//        return getFileName(data, "json");
//    }
//
//    public static String getScreenshotName(ItemData data) {
//        return getFileName(data, "png");
//    }
//
//    public static String getFileName(ItemData data, String extesion) {
//        return String.format(Locale.getDefault(), "%s%s%s%s%s%s%s.%s",
//                DataProvider.getInstance().getItems_provider().getNormalized(data.item_index), SEPARATOR,
//                data.session_data.name, SEPARATOR,
//                data.session_data.surname, SEPARATOR,
//                data.item_index, extesion);
//    }
//
//    public static File baseDirectory() {
//
//        File path = Environment.getExternalStorageDirectory();
//        File baseDirectory = new File(path, FOLDER_NAME);
//
//        // Make sure the Pictures directory exists.
//        baseDirectory.mkdirs();
//        return baseDirectory;
//    }
//
//    public static String normalize(String s){
//        return  s.replaceAll("\\s+", ".").toLowerCase();
//    }
//
//    public static File sessionDirectory(SessionData data, int item_index, String timestamp)
//    {
//        File base = baseDirectory();
//
//        String firstFolder = DataProvider.getInstance().getItems_provider().getNormalized(item_index);
//        String secondFolder = normalize(data.name) + SEPARATOR +
//                normalize(data.surname) + SEPARATOR + timestamp;
//        File sessionDirectory = new File(new File(base, firstFolder), secondFolder);
//        sessionDirectory.mkdirs();
//
//        return sessionDirectory;
//    }
//
//    public static synchronized void  scanFile(Activity activity, File path) {
//        activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(path)));
//    }
//
//    public static String getDate() {
//        Date now = new Date();
//        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
//        return now.toString();
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    public static String getShortDate() {
//        return  new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
//    }
}

