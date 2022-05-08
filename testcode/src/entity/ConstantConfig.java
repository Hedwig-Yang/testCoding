package entity;

import java.util.HashMap;
import java.util.Map;

public class ConstantConfig {
    public static String PNG_PATH = "/data/pdf_report/png/";
    public static String PNG_SUFFIX = ".png";

    public static String PDF_NO_MASK_PATH = "/data/pdf_report/pdf_no_mask/";
    public static String PDF_NO_MASK_SUFFIX = "_no_mask" + ".pdf";


    public static String PDF_MASK_PATH = "/data/pdf_report/pdf_mask/";
    public static String PDF_MASK_SUFFIX = ".pdf";

    public static String FILE_SERVER = "/data/fileServer/";

    static String LOGO_PATH = "C:\\Users\\62667\\IdeaProjects\\pdf_report\\src\\main\\resources\\logo\\";
    public static String ORDER_INFO = LOGO_PATH + "多端@3x(1).png";
    public static String COVER_INFO = LOGO_PATH + "全屋@3x.png";
    public static String DOWNLOAD_INFO = LOGO_PATH + "下载@3x.png";
    public static String CHECK_INFO = LOGO_PATH + "定位@3x.png";
    public static String APPLICATION_INFO = LOGO_PATH + "定位@3x(1).png";

    public static String NETWORK_BEFORE = LOGO_PATH + "编组@3x.png";
    public static String NETWORK_AFTER = LOGO_PATH + "编组@3x(1).png";


    public static String CHECK_POINT_1 = LOGO_PATH + "1@3x.png";
    public static String CHECK_POINT_2 = LOGO_PATH + "2@3x.png";
    public static String CHECK_POINT_3 = LOGO_PATH + "3@3x.png";
    public static String CHECK_POINT_4 = LOGO_PATH + "4@3x.png";
    public static String RIGHT = LOGO_PATH + "✅@3x.png";
    public static String GOOD = LOGO_PATH + "优@3x.png";

    //    应用logo
    public static String WANG_ZHE_RONG_YAO = LOGO_PATH + "王者荣耀.png";
    public static String TAO_BAO = LOGO_PATH + "淘宝@2x.png";
    public static String AI_QI_YI = LOGO_PATH + "爱奇艺@2x.png";
    public static String HE_PNIG_JING_YING = LOGO_PATH + "和平精英.png";
    public static String TENCENT_VEDIO = LOGO_PATH + "腾讯视频@2x.png";
    public static String JING_DONG = LOGO_PATH + "京东@2x.png";
    public static String YOU_KU = LOGO_PATH + "优酷@2x.png";

    public static Map<String, String> APP_LOG_MAP = new HashMap<String, String>() {{
        put("王者荣耀", WANG_ZHE_RONG_YAO);
        put("淘宝", TAO_BAO);
        put("爱奇艺", AI_QI_YI);
        put("和平精英", AI_QI_YI);
        put("腾讯视频", TENCENT_VEDIO);
        put("京东", JING_DONG);
        put("优酷", YOU_KU);
    }};









}
