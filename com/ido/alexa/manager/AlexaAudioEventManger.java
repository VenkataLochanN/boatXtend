package com.ido.alexa.manager;

import android.text.TextUtils;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.bean.EndpointIDBean;
import com.ido.alexa.callbacks.AsyncCallback;
import com.ido.alexa.callbacks.ChunkPostCallback;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.ApiResponse;
import com.ido.alexa.data.AvsItem;
import com.ido.alexa.data.AvsSetGatewayItem;
import com.ido.alexa.data.Event;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.net.ApiParser;
import com.ido.alexa.requestbody.PipeBody;
import com.ido.alexa.smarthome.CreateEndpoint;
import com.ido.alexa.util.ClientUtil;
import com.ido.life.util.DateUtil;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import no.nordicsemi.android.dfu.DfuBaseService;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaAudioEventManger implements ChunkPostCallback {
    private static final int ONE_MIN = 60000;
    private static final String TAG = "AlexaAudioEventManger";
    private static AlexaAudioEventManger instance;
    private boolean isEndOfRecording;
    public long lastSyncStateTime;
    private Call mCurrentCall;
    private String mEventUrl;
    private Thread mThread;
    private String mToken;
    private PipeBody mPipeBody = new PipeBody();
    private Queue<byte[]> chunkData = new LinkedBlockingQueue();
    private int writeSize = 0;
    private int pcmSize = 0;

    static /* synthetic */ int access$008(AlexaAudioEventManger alexaAudioEventManger) {
        int i = alexaAudioEventManger.writeSize;
        alexaAudioEventManger.writeSize = i + 1;
        return i;
    }

    private AlexaAudioEventManger() {
    }

    public static AlexaAudioEventManger getInstance() {
        if (instance == null) {
            instance = new AlexaAudioEventManger();
        }
        return instance;
    }

    void setEventUrl(String str) {
        this.mEventUrl = str;
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    @Override // com.ido.alexa.callbacks.ChunkPostCallback
    public void addVoiceData(byte[] bArr) {
        if (this.isEndOfRecording) {
            return;
        }
        this.chunkData.add(bArr);
        this.pcmSize++;
    }

    @Override // com.ido.alexa.callbacks.ChunkPostCallback
    public void endRecording(int i) {
        this.isEndOfRecording = true;
        if (i == 2 || i == 8) {
            AlexaLogUtil.printAndSave("收到 11 03 =" + this.chunkData.size() + "  pcmSize=" + this.pcmSize + " writeSize=" + this.writeSize);
        }
        this.chunkData.clear();
    }

    public void cancelCall() {
        Call call = this.mCurrentCall;
        if (call == null || !call.isExecuted()) {
            return;
        }
        this.mCurrentCall.cancel();
    }

    public ChunkPostCallback chunkSendRecordRequest(String str, final AsyncCallback<ApiResponse, Throwable> asyncCallback) {
        cancelCall();
        this.isEndOfRecording = false;
        this.chunkData.clear();
        this.pcmSize = 0;
        this.mPipeBody = null;
        this.mPipeBody = new PipeBody();
        sendRecordRequest(str, this.mPipeBody, asyncCallback);
        Thread thread = this.mThread;
        if (thread != null && thread.isAlive()) {
            this.mThread.interrupt();
            AlexaLogUtil.d("Alexa", "mThread interrupt");
        }
        this.mThread = new Thread(new Runnable() { // from class: com.ido.alexa.manager.AlexaAudioEventManger.1
            @Override // java.lang.Runnable
            public void run() {
                byte[] bArr;
                try {
                    AlexaAudioEventManger.this.writeSize = 0;
                    while (true) {
                        if (AlexaAudioEventManger.this.isEndOfRecording && AlexaAudioEventManger.this.chunkData.size() == 0) {
                            break;
                        }
                        if (AlexaAudioEventManger.this.chunkData.size() > 0 && (bArr = (byte[]) AlexaAudioEventManger.this.chunkData.poll()) != null) {
                            AlexaAudioEventManger.this.mPipeBody.sink().write(bArr);
                            AlexaAudioEventManger.this.mPipeBody.sink().flush();
                            AlexaAudioEventManger.access$008(AlexaAudioEventManger.this);
                        }
                    }
                    AlexaLogUtil.d("Alexa", "mPipeBody.sink().close()");
                    AlexaAudioEventManger.this.mPipeBody.sink().close();
                    AlexaAudioEventManger.this.chunkData.clear();
                    if (asyncCallback != null) {
                        asyncCallback.startParse();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    AlexaLogUtil.printAndSave("writeVoiceStream exception= " + e2.getMessage());
                    AsyncCallback asyncCallback2 = asyncCallback;
                    if (asyncCallback2 != null) {
                        asyncCallback2.failure(e2);
                    }
                }
            }
        });
        this.mThread.start();
        return this;
    }

    public void sendRecordRequest(String str, byte[] bArr, AsyncCallback<ApiResponse, Throwable> asyncCallback) {
        sendRecordRequest(str, bArr, null, asyncCallback);
    }

    public void sendRecordRequest(String str, PipeBody pipeBody, AsyncCallback<ApiResponse, Throwable> asyncCallback) {
        sendRecordRequest(str, null, pipeBody, asyncCallback);
    }

    private void sendRecordRequest(final String str, final byte[] bArr, final PipeBody pipeBody, final AsyncCallback<ApiResponse, Throwable> asyncCallback) {
        if (!TextUtils.isEmpty(this.mToken)) {
            new Thread(new Runnable() { // from class: com.ido.alexa.manager.AlexaAudioEventManger.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Request.Builder builder = new Request.Builder();
                        builder.url(AlexaAudioEventManger.this.mEventUrl);
                        builder.addHeader("Authorization", "Bearer " + AlexaAudioEventManger.this.mToken);
                        String speechRecognizerEvent = Event.getSpeechRecognizerEvent(str);
                        if (asyncCallback != null) {
                            asyncCallback.start(Event.mSpeechRecognizerEventRequestId);
                        }
                        MultipartBody.Builder builderAddFormDataPart = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("metadata", "metadata", RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), speechRecognizerEvent));
                        if (pipeBody != null) {
                            builderAddFormDataPart.addFormDataPart("audio", "speech", pipeBody);
                        } else {
                            builderAddFormDataPart.addFormDataPart("audio", "speech", new RequestBody() { // from class: com.ido.alexa.manager.AlexaAudioEventManger.2.1
                                @Override // okhttp3.RequestBody
                                public MediaType contentType() {
                                    return MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM);
                                }

                                @Override // okhttp3.RequestBody
                                public void writeTo(BufferedSink bufferedSink) throws IOException {
                                    bufferedSink.write(bArr);
                                }
                            });
                        }
                        builder.post(builderAddFormDataPart.build());
                        AlexaAudioEventManger.this.mCurrentCall = ClientUtil.getTLS12OkHttpClient().newCall(builder.build());
                        try {
                            Response responseExecute = AlexaAudioEventManger.this.mCurrentCall.execute();
                            if (responseExecute.code() == 204) {
                                AlexaLogUtil.printAndSave("Received a 204 response code from Amazon, is this expected?");
                            } else if (responseExecute.code() == 403) {
                                AlexaLogUtil.printAndSave("Received a 403 response code from Amazon, need authorize");
                            }
                            ResponseBody responseBodyBody = responseExecute.body();
                            if (responseBodyBody == null) {
                                if (asyncCallback != null) {
                                    asyncCallback.failure(new Exception("ResponseBody is null"));
                                    return;
                                }
                                return;
                            }
                            List<AvsItem> list = (responseExecute.code() == 204 || responseExecute.code() == 403) ? null : ApiParser.parse(responseBodyBody.byteStream(), AlexaAudioEventManger.this.getBoundary(responseExecute));
                            responseBodyBody.close();
                            if (asyncCallback != null) {
                                if (list != null) {
                                    ApiResponse apiResponse = new ApiResponse();
                                    apiResponse.setAvsItems(list);
                                    apiResponse.setResponseCode(responseExecute.code());
                                    if (responseExecute.code() == 204) {
                                        apiResponse.setMessage("Received a 204 response code from Amazon");
                                    }
                                    asyncCallback.success(apiResponse);
                                    return;
                                }
                                if (responseExecute.code() == 204) {
                                    asyncCallback.failure(new Exception("content is empty"));
                                } else if (responseExecute.code() == 403) {
                                    asyncCallback.authorize();
                                } else {
                                    asyncCallback.success(new ApiResponse());
                                }
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            if (asyncCallback != null) {
                                asyncCallback.failure(e2);
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        AsyncCallback asyncCallback2 = asyncCallback;
                        if (asyncCallback2 != null) {
                            asyncCallback2.failure(e3);
                        }
                    }
                }
            }).start();
        } else if (asyncCallback != null) {
            asyncCallback.failure(new AvsException("no login"));
        }
    }

    protected String getBoundary(Response response) {
        String str = response.headers().get("content-type");
        if (str != null) {
            Matcher matcher = Pattern.compile("boundary=(.*?);").matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return "";
    }

    public void sendEvent(final String str, final AsyncCallback<String, AvsException> asyncCallback) {
        if (TextUtils.isEmpty(this.mToken)) {
            if (asyncCallback != null) {
                asyncCallback.failure(new AvsException("no login"));
            }
            AlexaLogUtil.printAndSave(str + "---Alexa no login");
            return;
        }
        new Thread(new Runnable() { // from class: com.ido.alexa.manager.AlexaAudioEventManger.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Response responseExecute = ClientUtil.getTLS12OkHttpClient().newCall(AlexaAudioEventManger.this.getRequest(str)).execute();
                    AlexaLogUtil.printAndSave(str + " response.code()==" + responseExecute.code());
                    if (responseExecute.code() == 204) {
                        if (asyncCallback != null) {
                            asyncCallback.success(null);
                            return;
                        }
                        return;
                    }
                    ResponseBody responseBodyBody = responseExecute.body();
                    if (responseBodyBody != null) {
                        String strString = responseBodyBody.string();
                        AlexaLogUtil.printAndSave("body==" + strString);
                        if (asyncCallback != null) {
                            if (responseExecute.code() >= 200 && responseExecute.code() < 204) {
                                asyncCallback.success(strString);
                            } else {
                                asyncCallback.failure(new AvsException(strString));
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    AsyncCallback asyncCallback2 = asyncCallback;
                    if (asyncCallback2 != null) {
                        asyncCallback2.failure(new AvsException(e2.getMessage()));
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Request getRequest(String str) {
        Request.Builder builder = new Request.Builder();
        builder.url(this.mEventUrl);
        builder.addHeader("Authorization", "Bearer " + this.mToken);
        builder.post(new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("metadata", "metadata", RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), str)).build());
        return builder.build();
    }

    public void syncGateWay() {
        if (TextUtils.isEmpty(this.mToken)) {
            return;
        }
        new Thread(new Runnable() { // from class: com.ido.alexa.manager.AlexaAudioEventManger.4
            @Override // java.lang.Runnable
            public void run() {
                ResponseBody responseBodyBody;
                String gatewayEvent = Event.getGatewayEvent();
                try {
                    Response responseExecute = ClientUtil.getTLS12OkHttpClient().newCall(AlexaAudioEventManger.this.getRequest(gatewayEvent)).execute();
                    AlexaLogUtil.printAndSave(gatewayEvent + " response.code()==" + responseExecute.code());
                    if (responseExecute.code() == 204 || (responseBodyBody = responseExecute.body()) == null) {
                        return;
                    }
                    List<AvsItem> list = responseExecute.code() == 403 ? null : ApiParser.parse(responseBodyBody.byteStream(), AlexaAudioEventManger.this.getBoundary(responseExecute));
                    AlexaLogUtil.printAndSave(gatewayEvent + " body==" + responseBodyBody.string());
                    String gateway = "";
                    if (list != null) {
                        Iterator<AvsItem> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            AvsItem next = it.next();
                            if (next instanceof AvsSetGatewayItem) {
                                gateway = ((AvsSetGatewayItem) next).getGateway();
                                break;
                            }
                        }
                    }
                    if (TextUtils.isEmpty(gateway)) {
                        return;
                    }
                    AlexaManager.getInstance().setEndPoint(gateway);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
    }

    public void deleteEndpoint(final ImplAsyncCallback<String, AvsException> implAsyncCallback) {
        String endpointId = SpManager.getEndpointId();
        AlexaLogUtil.e("Alexa", "deleteEndpoint:" + endpointId);
        if (TextUtils.isEmpty(endpointId)) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("[{\"endpointId\":\"");
        sb.append(endpointId);
        sb.append("\"}");
        sb.append(",{\"endpointId\":\"");
        sb.append(endpointId);
        sb.append("-");
        sb.append(AlexaEndpointIdDefine.SPORT);
        sb.append("\"}");
        for (EndpointIDBean endpointIDBean : AlexaEndpointIdDefine.getEndpoints()) {
            sb.append(",{\"endpointId\":\"");
            sb.append(endpointId);
            sb.append("-");
            sb.append(endpointIDBean.getEndpointId());
            sb.append("\"}");
        }
        sb.append("]");
        sendEvent(Event.getDeleteReportEvent(this.mToken, sb.toString()), new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.alexa.manager.AlexaAudioEventManger.5
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str) {
                super.success(str);
                AlexaLogUtil.printAndSave(" deleteReport  success==" + str + " ,endpointIds=" + sb.toString());
                ImplAsyncCallback implAsyncCallback2 = implAsyncCallback;
                if (implAsyncCallback2 != null) {
                    implAsyncCallback2.success(null);
                    implAsyncCallback.complete();
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(AvsException avsException) {
                super.failure(avsException);
                AlexaLogUtil.printAndSave(" deleteReport  failure==" + avsException.getMessage());
                ImplAsyncCallback implAsyncCallback2 = implAsyncCallback;
                if (implAsyncCallback2 != null) {
                    implAsyncCallback2.failure(avsException);
                    implAsyncCallback.complete();
                }
            }
        });
    }

    public void discovery(final ImplAsyncCallback<String, AvsException> implAsyncCallback) {
        sendEvent(CreateEndpoint.getDiscoveryEvent(), new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.alexa.manager.AlexaAudioEventManger.6
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str) {
                super.success(str);
                SpManager.setEndpointId(CreateEndpoint.getCurrentEndpointId());
                AlexaLogUtil.printAndSave(" discovery  success==" + str);
                ImplAsyncCallback implAsyncCallback2 = implAsyncCallback;
                if (implAsyncCallback2 != null) {
                    implAsyncCallback2.success(null);
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(AvsException avsException) {
                super.failure(avsException);
                AlexaLogUtil.printAndSave(" discovery  failure==" + avsException.getMessage());
                ImplAsyncCallback implAsyncCallback2 = implAsyncCallback;
                if (implAsyncCallback2 != null) {
                    implAsyncCallback2.failure(avsException);
                }
            }
        });
    }

    public void sendSyncStateEvent(ImplAsyncCallback<String, AvsException> implAsyncCallback) {
        if (Math.abs(this.lastSyncStateTime - System.currentTimeMillis()) > DateUtil.MINUTE) {
            this.lastSyncStateTime = System.currentTimeMillis();
            sendEvent(Event.getNotifyStateEvent(false), implAsyncCallback);
        } else {
            AlexaLogUtil.printAndSave("1分钟内的同步状态请求不发送," + this.lastSyncStateTime);
        }
    }
}