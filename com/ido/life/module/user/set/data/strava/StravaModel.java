package com.ido.life.module.user.set.data.strava;

import android.text.TextUtils;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.alexa.manager.AlexaEndpointIdDefine;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.UploadUtil;
import com.ido.common.net.http.HttpManager;
import com.ido.common.net.http.IHttpCallback;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.IOUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.constants.Constants;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.home.detail.DetailActivity;
import com.ido.life.module.user.set.data.StravaData;
import com.ido.life.module.user.set.data.StravaPreference;
import com.ido.life.util.DateUtil;
import com.ido.life.util.SPUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes3.dex */
public class StravaModel {
    private static final String CANCEL_AUTHORIZATION = "https://www.strava.com/oauth/deauthorize";
    private static final String CLIENT_ID = "51563";
    private static final String CLIENT_SECRET = "12ef6e4b03f23ca3767466c515224b065837adf5";
    private static final String REDIRECT_URI = "http://life-content.veryfitplus.com/page/stravaH5.html";
    private static final String STRAVA_GET_REFRESH_TOKEN = "https://www.strava.com/oauth/token";
    private static final String STRAVA_REFRESH_TOKEN = "https://www.strava.com/api/v3/oauth/token";
    private static String STRAVA_UPLOAD_FILE_DATAS = "https://www.strava.com/api/v3/uploads";
    private static final String TAG = "StravaModel";
    static final String URL_PRIVACY = "https://www.strava.com/legal/privacy";
    public static final String URL_STRAVA = "https://www.strava.com/oauth/authorize?client_id=51563&response_type=code&redirect_uri=http://life-content.veryfitplus.com/page/stravaH5.html&approval_prompt=auto&scope=activity:write,read";
    private StravaPreference mStravaPreference;
    private LinkedList<Long> mUploadList = null;
    private boolean misDataUploading = false;

    /* JADX WARN: Removed duplicated region for block: B:20:0x002a A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002d A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0030 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getSportType(int r1) {
        /*
            r0 = 11
            if (r1 == r0) goto L42
            r0 = 16
            if (r1 == r0) goto L3f
            r0 = 18
            if (r1 == r0) goto L3c
            r0 = 31
            if (r1 == r0) goto L39
            r0 = 27
            if (r1 == r0) goto L36
            r0 = 28
            if (r1 == r0) goto L33
            switch(r1) {
                case 1: goto L30;
                case 2: goto L2d;
                case 3: goto L2a;
                case 4: goto L27;
                case 5: goto L24;
                case 6: goto L21;
                default: goto L1b;
            }
        L1b:
            switch(r1) {
                case 48: goto L2d;
                case 49: goto L2d;
                case 50: goto L2a;
                case 51: goto L2a;
                case 52: goto L30;
                case 53: goto L30;
                default: goto L1e;
            }
        L1e:
            java.lang.String r1 = ""
            goto L44
        L21:
            java.lang.String r1 = "RockClimbing"
            goto L44
        L24:
            java.lang.String r1 = "Swim"
            goto L44
        L27:
            java.lang.String r1 = "Hike"
            goto L44
        L2a:
            java.lang.String r1 = "Ride"
            goto L44
        L2d:
            java.lang.String r1 = "Run"
            goto L44
        L30:
            java.lang.String r1 = "Walk"
            goto L44
        L33:
            java.lang.String r1 = "IceSkate"
            goto L44
        L36:
            java.lang.String r1 = "Snowshoe"
            goto L44
        L39:
            java.lang.String r1 = "Rowing"
            goto L44
        L3c:
            java.lang.String r1 = "Yoga"
            goto L44
        L3f:
            java.lang.String r1 = "WeightTraining"
            goto L44
        L42:
            java.lang.String r1 = "Elliptical"
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.user.set.data.strava.StravaModel.getSportType(int):java.lang.String");
    }

    public void uploadDatas() {
        if (TextUtils.isEmpty((String) SPUtils.get(Constants.STRAVA_ACCESS_TOKEN, ""))) {
            return;
        }
        new Thread(new Runnable() { // from class: com.ido.life.module.user.set.data.strava.StravaModel.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                StravaModel.this.updateDataThread();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDataThread() throws Throwable {
        printAndSaveLog("开始准备上传Strava数据");
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            printAndSaveLog("准备上传Strava数据，但是此时没有网络，取消上传");
            return;
        }
        String str = (String) SPUtils.get(Constants.STRAVA_ACCESS_TOKEN, "");
        if (TextUtils.isEmpty(str)) {
            printAndSaveLog("上传Strava数据，但是token为空");
            return;
        }
        if (Math.abs(System.currentTimeMillis() - ((Long) SPUtils.get(Constants.STRAVA_LAST_UPLOAD_TIME, 0L)).longValue()) < 300000) {
            printAndSaveLog("Strava数据上传间隔小于5分钟，停止上传");
            return;
        }
        this.mUploadList = LocalHealthDataManager.getInstance().getAllNotUploadStravaActivityDataTimeStamp();
        LinkedList<Long> linkedList = this.mUploadList;
        if (linkedList == null || linkedList.size() == 0) {
            printAndSaveLog("没有需要上传的Strava数据,停止数据上传");
        } else {
            if (this.misDataUploading) {
                return;
            }
            this.misDataUploading = true;
            if (this.mUploadList.size() > 0) {
                startNextTask(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNextTask(String str) throws Throwable {
        LinkedList<Long> linkedList = this.mUploadList;
        if (linkedList != null && linkedList.size() > 0) {
            List<SportHealth> allNotUploadStravaActivityData = LocalHealthDataManager.getInstance().getAllNotUploadStravaActivityData(this.mUploadList.pollFirst().longValue());
            if (allNotUploadStravaActivityData == null || allNotUploadStravaActivityData.size() <= 0 || TextUtils.isEmpty(allNotUploadStravaActivityData.get(0).getStartTime()) || TextUtils.isEmpty(allNotUploadStravaActivityData.get(0).getDateTime())) {
                return;
            }
            uploadStravaDataGpx(allNotUploadStravaActivityData.get(0), str);
            return;
        }
        this.misDataUploading = false;
        printAndSaveLog("Strave数据上传成功");
    }

    private void uploadStravaDataGpx(final SportHealth sportHealth, final String str) throws Throwable {
        String str2;
        boolean zCreateGPXFileNew;
        String str3;
        HashMap map;
        if (sportHealth == null || TextUtils.isEmpty(sportHealth.getStartTime()) || TextUtils.isEmpty(sportHealth.getDateTime())) {
            startNextTask(str);
            return;
        }
        printAndSaveLog("Strave开始上传的运动数据是sportHealth=" + sportHealth.toString());
        SportGpsData sportGpsData = LocalHealthDataManager.getInstance().getSportGpsData(sportHealth.getUserId().longValue(), TimeUtil.convTimeYmdhmsToLong(sportHealth.getStartTime()));
        if (sportGpsData == null) {
            map = new HashMap();
            map.put(AppMeasurementSdk.ConditionalUserProperty.NAME, getSportType(sportHealth.getType()));
            map.put(DetailActivity.DATA_TYPE, "tcx");
            map.put("trainer", "Other");
            map.put("access_token", str);
            long jConvTimeYmdhmsToLong = TimeUtil.convTimeYmdhmsToLong(sportHealth.getStartTime());
            str3 = STRAVA_UPLOAD_FILE_DATAS + "?data_type=tcx";
            str2 = LogPathImpl.getInstance().getStravaLogPath() + jConvTimeYmdhmsToLong + ".tcx";
            zCreateGPXFileNew = createTCXFileNew(jConvTimeYmdhmsToLong, str2, sportHealth);
        } else {
            HashMap map2 = new HashMap();
            map2.put(AppMeasurementSdk.ConditionalUserProperty.NAME, "Other");
            map2.put(DetailActivity.DATA_TYPE, "gpx");
            map2.put("trainer", "Other");
            map2.put("access_token", str);
            str2 = LogPathImpl.getInstance().getStravaLogPath() + TimeUtil.convTimeYmdhmsToLong(sportHealth.getStartTime()) + ".gpx";
            zCreateGPXFileNew = createGPXFileNew(sportGpsData, sportHealth, str2);
            str3 = STRAVA_UPLOAD_FILE_DATAS;
            map = map2;
        }
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str2) && zCreateGPXFileNew) {
            UploadUtil.getInstance().upload(str3, str2, map, new UploadUtil.OnCommCallback<String>() { // from class: com.ido.life.module.user.set.data.strava.StravaModel.2
                @Override // com.ido.common.net.UploadUtil.OnCommCallback
                public void onSuccess(String str4) throws Throwable {
                    sportHealth.setUploadedStrava(true);
                    try {
                        sportHealth.update();
                        StravaModel.printAndSaveLog("Strava数据上传成功 startTime=" + sportHealth.getStartTime());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    StravaModel.this.startNextTask(str);
                }

                @Override // com.ido.common.net.UploadUtil.OnCommCallback
                public void onFailed(String str4) throws Throwable {
                    StravaModel.printAndSaveLog("Strava数据上传失败 startTime=" + sportHealth.getStartTime());
                    StravaModel.this.startNextTask(str);
                }
            });
        } else {
            startNextTask(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v2, types: [org.w3c.dom.Element, org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r12v6, types: [org.w3c.dom.Element, org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r32v2 */
    /* JADX WARN: Type inference failed for: r32v3 */
    /* JADX WARN: Type inference failed for: r32v4 */
    /* JADX WARN: Type inference failed for: r32v5 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23, types: [org.w3c.dom.Element] */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35 */
    /* JADX WARN: Type inference failed for: r8v39, types: [org.w3c.dom.Element] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r8v41 */
    /* JADX WARN: Type inference failed for: r8v42 */
    /* JADX WARN: Type inference failed for: r8v43 */
    /* JADX WARN: Type inference failed for: r8v44 */
    /* JADX WARN: Type inference failed for: r8v45 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public static boolean createGPXFileNew(SportGpsData sportGpsData, SportHealth sportHealth, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        File file;
        File file2;
        Document document;
        List listAnalysisJsonArrayToList;
        int i;
        List list;
        String str2;
        ?? r8;
        ?? CreateElement;
        ?? r82;
        Element elementCreateElement;
        Element elementCreateElement2;
        Element elementCreateElement3;
        Element element;
        Element element2;
        Element element3;
        Document document2;
        String str3;
        String str4;
        List list2;
        int i2;
        int i3;
        String str5;
        String str6;
        String str7;
        ?? r83;
        String[] strArr;
        if (sportGpsData == null || sportHealth == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(sportHealth.getStartTime()) || TextUtils.isEmpty(sportHealth.getDateTime())) {
            return false;
        }
        try {
            try {
                file = new File(str);
                if (file.exists()) {
                    try {
                        file.delete();
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream2 = null;
                        IOUtil.close(fileOutputStream2);
                        throw th;
                    }
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
            }
            if (file.createNewFile()) {
                Document documentNewDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                Element elementCreateElement4 = documentNewDocument.createElement("gpx");
                Element elementCreateElement5 = documentNewDocument.createElement("trk");
                Element elementCreateElement6 = documentNewDocument.createElement("trkseg");
                elementCreateElement4.setAttribute("version", "1.1");
                documentNewDocument.appendChild(elementCreateElement4);
                elementCreateElement4.appendChild(elementCreateElement5);
                elementCreateElement5.appendChild(elementCreateElement6);
                String hr_data_vlaue_json = sportHealth.getHr_data_vlaue_json();
                int[] iArr = (int[]) GsonUtil.fromJson(hr_data_vlaue_json, int[].class);
                String items = sportGpsData.getGpsData().getItems();
                try {
                    try {
                        if (TextUtils.isEmpty(items) || (listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(items, String[][].class)) == null || listAnalysisJsonArrayToList.size() <= 0) {
                            file2 = file;
                        } else {
                            int size = listAnalysisJsonArrayToList.size();
                            int length = iArr.length;
                            CommonLogUtil.printAndSave(TAG, "createGPXFileNew: " + hr_data_vlaue_json.length() + AppInfo.DELIM + listAnalysisJsonArrayToList.size() + AppInfo.DELIM + Math.max(length, size));
                            String str8 = "gpxtpx:TrackPointExtension";
                            String str9 = "extensions";
                            String str10 = "time";
                            String str11 = "trkpt";
                            file2 = file;
                            Element element4 = elementCreateElement6;
                            String str12 = "lat";
                            if (size < length) {
                                document = documentNewDocument;
                                String str13 = "gpxtpx:TrackPointExtension";
                                String str14 = "lat";
                                List list3 = listAnalysisJsonArrayToList;
                                String str15 = "extensions";
                                String str16 = "time";
                                String str17 = "trkpt";
                                ?? r84 = element4;
                                int i4 = size;
                                int i5 = 0;
                                while (i5 < i4) {
                                    List list4 = list3;
                                    try {
                                        String[] strArr2 = (String[]) list4.get(i5);
                                        if (strArr2 == null || strArr2.length != 3) {
                                            i = i4;
                                            list = list4;
                                            str2 = str14;
                                        } else {
                                            long j = Long.parseLong(strArr2[2]);
                                            String str18 = str17;
                                            Document document3 = document;
                                            try {
                                                CreateElement = document3.createElement(str18);
                                                i = i4;
                                                str17 = str18;
                                                String str19 = str16;
                                                try {
                                                    elementCreateElement = document3.createElement(str19);
                                                    str16 = str19;
                                                    ?? r32 = r84;
                                                    String str20 = str15;
                                                    try {
                                                        elementCreateElement2 = document3.createElement(str20);
                                                        str15 = str20;
                                                        String str21 = str13;
                                                        try {
                                                            elementCreateElement3 = document3.createElement(str21);
                                                            str13 = str21;
                                                        } catch (Exception e3) {
                                                            e = e3;
                                                            r82 = r32;
                                                            str13 = str21;
                                                        }
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                        r82 = r32;
                                                        str15 = str20;
                                                    }
                                                    try {
                                                        Element elementCreateElement7 = document3.createElement("gpxtpx:hr");
                                                        document = document3;
                                                        try {
                                                            if (i5 >= list4.size()) {
                                                                element = elementCreateElement3;
                                                                try {
                                                                    str2 = str14;
                                                                } catch (Exception e5) {
                                                                    e = e5;
                                                                    str2 = str14;
                                                                }
                                                                try {
                                                                    CreateElement.setAttribute(str2, strArr2[0]);
                                                                    element2 = elementCreateElement7;
                                                                    CreateElement.setAttribute("lon", strArr2[1]);
                                                                    elementCreateElement.setTextContent(getStravaDataTime(getLocalDate(j)));
                                                                    CommonLogUtil.printAndSave(TAG, "createGPXFileNew: lat:" + strArr2[0] + ",long=" + strArr2[1] + ",time=" + strArr2[2]);
                                                                    list = list4;
                                                                } catch (Exception e6) {
                                                                    e = e6;
                                                                    r84 = r32;
                                                                    list = list4;
                                                                    e.printStackTrace();
                                                                    i5++;
                                                                    str14 = str2;
                                                                    i4 = i;
                                                                    list3 = list;
                                                                    r84 = r84;
                                                                }
                                                            } else {
                                                                element = elementCreateElement3;
                                                                str2 = str14;
                                                                element2 = elementCreateElement7;
                                                                String[] strArr3 = (String[]) list4.get(list4.size() - 1);
                                                                StringBuilder sb = new StringBuilder();
                                                                list = list4;
                                                                try {
                                                                    sb.append(strArr3[0]);
                                                                    sb.append("");
                                                                    CreateElement.setAttribute(str2, sb.toString());
                                                                    CreateElement.setAttribute("lon", strArr3[1] + "");
                                                                    elementCreateElement.setTextContent(getStravaDataTime(getLocalDate(j)));
                                                                    CommonLogUtil.printAndSave(TAG, "createGPXFileNew: lat:" + strArr3[0] + ",long=" + strArr3[1] + ",time=" + strArr3[2]);
                                                                } catch (Exception e7) {
                                                                    e = e7;
                                                                    r84 = r32;
                                                                    e.printStackTrace();
                                                                    i5++;
                                                                    str14 = str2;
                                                                    i4 = i;
                                                                    list3 = list;
                                                                    r84 = r84;
                                                                }
                                                            }
                                                            element3 = element2;
                                                            element3.setTextContent(String.valueOf(iArr[i5]));
                                                            r84 = r32;
                                                        } catch (Exception e8) {
                                                            e = e8;
                                                            r8 = r32;
                                                            list = list4;
                                                            r84 = r8;
                                                            str2 = str14;
                                                            e.printStackTrace();
                                                            i5++;
                                                            str14 = str2;
                                                            i4 = i;
                                                            list3 = list;
                                                            r84 = r84;
                                                        }
                                                    } catch (Exception e9) {
                                                        e = e9;
                                                        r82 = r32;
                                                        list = list4;
                                                        document = document3;
                                                        r84 = r82;
                                                        str2 = str14;
                                                        e.printStackTrace();
                                                        i5++;
                                                        str14 = str2;
                                                        i4 = i;
                                                        list3 = list;
                                                        r84 = r84;
                                                    }
                                                } catch (Exception e10) {
                                                    e = e10;
                                                    str16 = str19;
                                                    r82 = r84;
                                                }
                                            } catch (Exception e11) {
                                                e = e11;
                                                i = i4;
                                                list = list4;
                                                document = document3;
                                                str17 = str18;
                                                r84 = r84;
                                            }
                                            try {
                                                r84.appendChild(CreateElement);
                                                CreateElement.appendChild(elementCreateElement);
                                                CreateElement.appendChild(elementCreateElement2);
                                                Element element5 = element;
                                                elementCreateElement2.appendChild(element5);
                                                element5.appendChild(element3);
                                            } catch (Exception e12) {
                                                e = e12;
                                                e.printStackTrace();
                                            }
                                        }
                                    } catch (Exception e13) {
                                        e = e13;
                                        i = i4;
                                        r8 = r84;
                                    }
                                    i5++;
                                    str14 = str2;
                                    i4 = i;
                                    list3 = list;
                                    r84 = r84;
                                }
                                Document document4 = document;
                                document4.setXmlVersion("1.0");
                                Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
                                transformerNewTransformer.setOutputProperty("encoding", "utf-8");
                                DOMSource dOMSource = new DOMSource(document4);
                                fileOutputStream = new FileOutputStream(file2);
                                transformerNewTransformer.transform(dOMSource, new StreamResult(fileOutputStream));
                                fileOutputStream.flush();
                                fileOutputStream.close();
                                IOUtil.close(fileOutputStream);
                                return true;
                            }
                            int i6 = 0;
                            ?? r322 = element4;
                            while (i6 < size) {
                                try {
                                    strArr = (String[]) listAnalysisJsonArrayToList.get(i6);
                                } catch (Exception e14) {
                                    e = e14;
                                    document2 = documentNewDocument;
                                    str3 = str8;
                                    str4 = str12;
                                    list2 = listAnalysisJsonArrayToList;
                                }
                                if (strArr != null) {
                                    list2 = listAnalysisJsonArrayToList;
                                    try {
                                        i2 = size;
                                    } catch (Exception e15) {
                                        e = e15;
                                        document2 = documentNewDocument;
                                        str3 = str8;
                                        str4 = str12;
                                        i2 = size;
                                        i3 = i6;
                                        str5 = str9;
                                        str6 = str10;
                                        str7 = str11;
                                        r83 = r322;
                                        e.printStackTrace();
                                        i6 = i3 + 1;
                                        r322 = r83;
                                        listAnalysisJsonArrayToList = list2;
                                        size = i2;
                                        str11 = str7;
                                        str10 = str6;
                                        str9 = str5;
                                        str8 = str3;
                                        documentNewDocument = document2;
                                        str12 = str4;
                                    }
                                    if (strArr.length != 3) {
                                        document2 = documentNewDocument;
                                        str3 = str8;
                                        str4 = str12;
                                    } else {
                                        try {
                                            long j2 = Long.parseLong(strArr[2]);
                                            int i7 = i6;
                                            try {
                                                ?? CreateElement2 = documentNewDocument.createElement(str11);
                                                str7 = str11;
                                                try {
                                                    Element elementCreateElement8 = documentNewDocument.createElement(str10);
                                                    str6 = str10;
                                                    try {
                                                        Element elementCreateElement9 = documentNewDocument.createElement(str9);
                                                        str5 = str9;
                                                        try {
                                                            Element elementCreateElement10 = documentNewDocument.createElement(str8);
                                                            str3 = str8;
                                                            try {
                                                                Element elementCreateElement11 = documentNewDocument.createElement("gpxtpx:hr");
                                                                document2 = documentNewDocument;
                                                                try {
                                                                    CreateElement2.setAttribute(str12, strArr[0]);
                                                                    str4 = str12;
                                                                    try {
                                                                        CreateElement2.setAttribute("lon", strArr[1]);
                                                                        elementCreateElement8.setTextContent(getStravaDataTime(getLocalDate(j2)));
                                                                        CommonLogUtil.printAndSave(TAG, "createGPXFileNew: lat:" + strArr[0] + ",long=" + strArr[1] + ",time=" + strArr[2]);
                                                                        i3 = i7;
                                                                        if (i3 >= iArr.length) {
                                                                            try {
                                                                                elementCreateElement11.setTextContent(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                                                                            } catch (Exception e16) {
                                                                                e = e16;
                                                                                r83 = r322;
                                                                                e.printStackTrace();
                                                                                i6 = i3 + 1;
                                                                                r322 = r83;
                                                                                listAnalysisJsonArrayToList = list2;
                                                                                size = i2;
                                                                                str11 = str7;
                                                                                str10 = str6;
                                                                                str9 = str5;
                                                                                str8 = str3;
                                                                                documentNewDocument = document2;
                                                                                str12 = str4;
                                                                            }
                                                                        } else {
                                                                            elementCreateElement11.setTextContent(String.valueOf(iArr[i3]));
                                                                        }
                                                                        r83 = r322;
                                                                    } catch (Exception e17) {
                                                                        e = e17;
                                                                        r83 = r322;
                                                                        i3 = i7;
                                                                    }
                                                                    try {
                                                                        r83.appendChild(CreateElement2);
                                                                        CreateElement2.appendChild(elementCreateElement8);
                                                                        CreateElement2.appendChild(elementCreateElement9);
                                                                        elementCreateElement9.appendChild(elementCreateElement10);
                                                                        elementCreateElement10.appendChild(elementCreateElement11);
                                                                    } catch (Exception e18) {
                                                                        e = e18;
                                                                        e.printStackTrace();
                                                                    }
                                                                } catch (Exception e19) {
                                                                    e = e19;
                                                                    str4 = str12;
                                                                    i3 = i7;
                                                                    r83 = r322;
                                                                    e.printStackTrace();
                                                                    i6 = i3 + 1;
                                                                    r322 = r83;
                                                                    listAnalysisJsonArrayToList = list2;
                                                                    size = i2;
                                                                    str11 = str7;
                                                                    str10 = str6;
                                                                    str9 = str5;
                                                                    str8 = str3;
                                                                    documentNewDocument = document2;
                                                                    str12 = str4;
                                                                }
                                                            } catch (Exception e20) {
                                                                e = e20;
                                                                document2 = documentNewDocument;
                                                            }
                                                        } catch (Exception e21) {
                                                            e = e21;
                                                            document2 = documentNewDocument;
                                                            str3 = str8;
                                                        }
                                                    } catch (Exception e22) {
                                                        e = e22;
                                                        document2 = documentNewDocument;
                                                        str3 = str8;
                                                        str4 = str12;
                                                        str5 = str9;
                                                    }
                                                } catch (Exception e23) {
                                                    e = e23;
                                                    document2 = documentNewDocument;
                                                    str3 = str8;
                                                    str4 = str12;
                                                    str5 = str9;
                                                    str6 = str10;
                                                }
                                            } catch (Exception e24) {
                                                e = e24;
                                                document2 = documentNewDocument;
                                                str3 = str8;
                                                str4 = str12;
                                                str5 = str9;
                                                str6 = str10;
                                                str7 = str11;
                                            }
                                        } catch (Exception e25) {
                                            e = e25;
                                            document2 = documentNewDocument;
                                            str3 = str8;
                                            str4 = str12;
                                            i3 = i6;
                                            str5 = str9;
                                            str6 = str10;
                                            str7 = str11;
                                            r83 = r322;
                                            e.printStackTrace();
                                            i6 = i3 + 1;
                                            r322 = r83;
                                            listAnalysisJsonArrayToList = list2;
                                            size = i2;
                                            str11 = str7;
                                            str10 = str6;
                                            str9 = str5;
                                            str8 = str3;
                                            documentNewDocument = document2;
                                            str12 = str4;
                                        }
                                        i6 = i3 + 1;
                                        r322 = r83;
                                        listAnalysisJsonArrayToList = list2;
                                        size = i2;
                                        str11 = str7;
                                        str10 = str6;
                                        str9 = str5;
                                        str8 = str3;
                                        documentNewDocument = document2;
                                        str12 = str4;
                                    }
                                } else {
                                    document2 = documentNewDocument;
                                    str3 = str8;
                                    str4 = str12;
                                    list2 = listAnalysisJsonArrayToList;
                                    i2 = size;
                                }
                                i3 = i6;
                                str5 = str9;
                                str6 = str10;
                                str7 = str11;
                                r83 = r322;
                                i6 = i3 + 1;
                                r322 = r83;
                                listAnalysisJsonArrayToList = list2;
                                size = i2;
                                str11 = str7;
                                str10 = str6;
                                str9 = str5;
                                str8 = str3;
                                documentNewDocument = document2;
                                str12 = str4;
                            }
                        }
                        transformerNewTransformer.transform(dOMSource, new StreamResult(fileOutputStream));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        IOUtil.close(fileOutputStream);
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream;
                        IOUtil.close(fileOutputStream2);
                        throw th;
                    }
                } catch (Exception e26) {
                    e = e26;
                    e.printStackTrace();
                    IOUtil.close(fileOutputStream);
                    return false;
                }
                document = documentNewDocument;
                Document document42 = document;
                document42.setXmlVersion("1.0");
                Transformer transformerNewTransformer2 = TransformerFactory.newInstance().newTransformer();
                transformerNewTransformer2.setOutputProperty("encoding", "utf-8");
                DOMSource dOMSource2 = new DOMSource(document42);
                fileOutputStream = new FileOutputStream(file2);
            } else {
                fileOutputStream = null;
                IOUtil.close(fileOutputStream);
                return false;
            }
            e.printStackTrace();
            IOUtil.close(fileOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            fileOutputStream2 = fileOutputStream;
            IOUtil.close(fileOutputStream2);
            throw th;
        }
    }

    public static void getStravaRefteshToken(String str, IHttpCallback<String> iHttpCallback) {
        HashMap map = new HashMap();
        map.put("client_secret", CLIENT_SECRET);
        map.put(AccountManagerConstants.CLIENT_ID_LABEL, CLIENT_ID);
        map.put(AuthorizationResponseParser.CODE, str);
        map.put("grant_type", "authorization_code");
        HttpManager.getInstance().getRequestString(STRAVA_GET_REFRESH_TOKEN, map, iHttpCallback, false);
    }

    public static void cancelStravaAuth(Map<String, Object> map, IHttpCallback<String> iHttpCallback) {
        HttpManager.getInstance().getRequestString(CANCEL_AUTHORIZATION, map, iHttpCallback, false);
    }

    public void savaStravaData() {
        this.mStravaPreference = StravaPreference.getInstance();
        StravaData stravaData = new StravaData();
        stravaData.setClientId("28542");
        stravaData.setClientSecret("7767ba9c587fb4f8467879834675ce01f17aa6c3");
        stravaData.setGrantType("refresh_token");
        stravaData.setRefreshToken("1c0266ae1debf64bb2a6a7d0d798846d3d5f8458");
        this.mStravaPreference.saveStravaDataUpload(stravaData);
    }

    public void refereshToken() {
        this.mStravaPreference = StravaPreference.getInstance();
        StravaData stravaDataUpload = this.mStravaPreference.getStravaDataUpload();
        HashMap map = new HashMap();
        map.put(AccountManagerConstants.CLIENT_ID_LABEL, stravaDataUpload.getClientId());
        map.put("client_secret", stravaDataUpload.getClientSecret());
        map.put("grant_type", stravaDataUpload.getGrantType());
        map.put("refresh_token", stravaDataUpload.getRefreshToken());
        HttpManager.getInstance().getRequestString(STRAVA_REFRESH_TOKEN, map, new IHttpCallback<String>() { // from class: com.ido.life.module.user.set.data.strava.StravaModel.3
            @Override // com.ido.common.net.http.IHttpCallback
            public void onSuccess(String str) {
                CommonLogUtil.printAndSave(StravaModel.TAG, "onSuccess: + refresh " + str);
            }

            @Override // com.ido.common.net.http.IHttpCallback
            public void onFaild(String str) {
                CommonLogUtil.printAndSave(StravaModel.TAG, "onFaild: " + str);
            }
        }, false);
    }

    private static String getLocalDate(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS zzz");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);
    }

    private static String getStravaDataTime(String str) {
        if (!str.contains(" ")) {
            return str;
        }
        return str.split(" ")[0] + "Z";
    }

    public static boolean createTCXFileNew(long j, String str, SportHealth sportHealth) {
        int i;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(str) && sportHealth != null && !TextUtils.isEmpty(sportHealth.getStartTime())) {
            File file = new File(str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            if (file.createNewFile()) {
                Document documentNewDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                Element elementCreateElement = documentNewDocument.createElement("TrainingCenterDatabase");
                documentNewDocument.appendChild(elementCreateElement);
                Element elementCreateElement2 = documentNewDocument.createElement("Activities");
                elementCreateElement.appendChild(elementCreateElement2);
                Element elementCreateElement3 = documentNewDocument.createElement("Activity");
                elementCreateElement3.setAttribute(AlexaEndpointIdDefine.SPORT, getSportType(sportHealth.getType()));
                elementCreateElement2.appendChild(elementCreateElement3);
                Element elementCreateElement4 = documentNewDocument.createElement("Id");
                elementCreateElement4.setTextContent(j + "");
                Element elementCreateElement5 = documentNewDocument.createElement("Lap");
                String stravaDataTime = getStravaDataTime(getLocalDate(j));
                CommonLogUtil.printAndSave(TAG, "createTCXFileNew: " + stravaDataTime);
                elementCreateElement5.setAttribute("StartTime", stravaDataTime);
                elementCreateElement3.appendChild(elementCreateElement4);
                elementCreateElement3.appendChild(elementCreateElement5);
                Element elementCreateElement6 = documentNewDocument.createElement("TotalTimeSeconds");
                elementCreateElement6.setTextContent(String.valueOf(sportHealth.getTotalSeconds()));
                elementCreateElement5.appendChild(elementCreateElement6);
                Element elementCreateElement7 = documentNewDocument.createElement("DistanceMeters");
                elementCreateElement7.setTextContent(String.valueOf(sportHealth.getDistance()));
                elementCreateElement5.appendChild(elementCreateElement7);
                Element elementCreateElement8 = documentNewDocument.createElement("Calories");
                elementCreateElement8.setTextContent(String.valueOf(sportHealth.getNumCalories()));
                elementCreateElement5.appendChild(elementCreateElement8);
                Element elementCreateElement9 = documentNewDocument.createElement("AverageHeartRateBpm");
                elementCreateElement5.appendChild(elementCreateElement9);
                Element elementCreateElement10 = documentNewDocument.createElement(MAPCookie.KEY_VALUE);
                elementCreateElement10.setTextContent(String.valueOf(sportHealth.getAvgHrValue()));
                elementCreateElement9.appendChild(elementCreateElement10);
                Element elementCreateElement11 = documentNewDocument.createElement("MaximumHeartRateBpm");
                elementCreateElement5.appendChild(elementCreateElement11);
                Element elementCreateElement12 = documentNewDocument.createElement(MAPCookie.KEY_VALUE);
                elementCreateElement12.setTextContent(String.valueOf(sportHealth.getMaxHrValue()));
                elementCreateElement11.appendChild(elementCreateElement12);
                Element elementCreateElement13 = documentNewDocument.createElement("Intensity");
                elementCreateElement13.setTextContent("Active");
                elementCreateElement5.appendChild(elementCreateElement13);
                Element elementCreateElement14 = documentNewDocument.createElement("TriggerMethod");
                elementCreateElement14.setTextContent("Manual");
                elementCreateElement5.appendChild(elementCreateElement14);
                Element elementCreateElement15 = documentNewDocument.createElement("Track");
                elementCreateElement5.appendChild(elementCreateElement15);
                String[] strArrSplit = sportHealth.getHr_data_vlaue_json().replace("[", "").replace("]", "").replace(",255", "").split(AppInfo.DELIM);
                if (strArrSplit != null && strArrSplit.length != 0) {
                    int distance = sportHealth.getDistance() / strArrSplit.length;
                    for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                        try {
                            String strConvTimeDetail = TimeUtil.convTimeDetail(DateUtil.getLongFromDateStr(sportHealth.getDateTime()) + ((long) (i2 * 5000)));
                            if (!TextUtils.isEmpty(strConvTimeDetail)) {
                                String[] strArrSplit2 = strConvTimeDetail.split(" ");
                                String str2 = strArrSplit2[0] + "T" + strArrSplit2[1] + ".000Z";
                                if (i2 != 0) {
                                    i = i2 % 2 == 0 ? distance + 1 : distance - 1;
                                } else {
                                    i = distance;
                                }
                                Element elementCreateElement16 = documentNewDocument.createElement("Trackpoint");
                                elementCreateElement15.appendChild(elementCreateElement16);
                                Element elementCreateElement17 = documentNewDocument.createElement("Time");
                                elementCreateElement17.setTextContent(str2);
                                elementCreateElement16.appendChild(elementCreateElement17);
                                Element elementCreateElement18 = documentNewDocument.createElement("DistanceMeters");
                                elementCreateElement18.setTextContent(i + "");
                                elementCreateElement16.appendChild(elementCreateElement18);
                                Element elementCreateElement19 = documentNewDocument.createElement("HeartRateBpm");
                                elementCreateElement16.appendChild(elementCreateElement19);
                                Element elementCreateElement20 = documentNewDocument.createElement(MAPCookie.KEY_VALUE);
                                elementCreateElement20.setTextContent(strArrSplit[i2]);
                                elementCreateElement19.appendChild(elementCreateElement20);
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    documentNewDocument.setXmlVersion("1.0");
                    Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
                    transformerNewTransformer.setOutputProperty("encoding", "utf-8");
                    DOMSource dOMSource = new DOMSource(documentNewDocument);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    transformerNewTransformer.transform(dOMSource, new StreamResult(fileOutputStream));
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printAndSaveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.d(TAG, str);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getStravaLogPath(), TAG, str);
    }
}