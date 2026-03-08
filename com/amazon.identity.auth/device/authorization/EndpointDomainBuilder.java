package com.amazon.identity.auth.device.authorization;

import android.content.Context;
import com.amazon.identity.auth.device.StoredPreferences;
import com.amazon.identity.auth.device.api.authorization.Region;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.utils.DefaultLibraryInfo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class EndpointDomainBuilder {
    private static final String HTTPS = "https://";
    private static final boolean REGULAR = false;
    private static final boolean SANDBOX = true;
    private static Map<String, String> allDomainsMap = new HashMap();
    private static Map<String, Region> domainToRegionMap = new HashMap();
    private boolean isSandbox = false;
    private String pandaEndpointFromAPIKey;
    private Region region;
    private Service service;
    private Stage stage;

    static {
        addEndpoint(Service.AUTHORIZATION, Stage.DEVO, false, Region.NA, "https://na-account.integ.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.DEVO, false, Region.EU, "https://eu-account.integ.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.DEVO, false, Region.FE, "https://apac-account.integ.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.PRE_PROD, false, Region.NA, "https://na.account.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.PRE_PROD, false, Region.EU, "https://eu.account.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.PRE_PROD, false, Region.FE, "https://apac.account.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.PROD, false, Region.NA, "https://na.account.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.PROD, false, Region.EU, "https://eu.account.amazon.com");
        addEndpoint(Service.AUTHORIZATION, Stage.PROD, false, Region.FE, "https://apac.account.amazon.com");
        addEndpoint(Service.PANDA, Stage.DEVO, true, Region.NA, "https://api-sandbox.integ.amazon.com");
        addEndpoint(Service.PANDA, Stage.DEVO, true, Region.EU, "https://api-sandbox.integ.amazon.co.uk");
        addEndpoint(Service.PANDA, Stage.DEVO, true, Region.FE, "https://api-sandbox-jp.integ.amazon.com");
        addEndpoint(Service.PANDA, Stage.DEVO, false, Region.NA, "https://api.integ.amazon.com");
        addEndpoint(Service.PANDA, Stage.DEVO, false, Region.EU, "https://api.integ.amazon.co.uk");
        addEndpoint(Service.PANDA, Stage.DEVO, false, Region.FE, "https://api.integ.amazon.co.jp");
        addEndpoint(Service.PANDA, Stage.PRE_PROD, true, Region.NA, "https://api.sandbox.amazon.com");
        addEndpoint(Service.PANDA, Stage.PRE_PROD, true, Region.EU, "https://api.sandbox.amazon.co.uk");
        addEndpoint(Service.PANDA, Stage.PRE_PROD, true, Region.FE, "https://api-sandbox.amazon.co.jp");
        addEndpoint(Service.PANDA, Stage.PRE_PROD, false, Region.NA, "https://api-preprod.amazon.com");
        addEndpoint(Service.PANDA, Stage.PRE_PROD, false, Region.EU, "https://api-preprod.amazon.co.uk");
        addEndpoint(Service.PANDA, Stage.PRE_PROD, false, Region.FE, "https://api-preprod.amazon.co.jp");
        addEndpoint(Service.PANDA, Stage.PROD, true, Region.NA, "https://api.sandbox.amazon.com");
        addEndpoint(Service.PANDA, Stage.PROD, true, Region.EU, "https://api.sandbox.amazon.co.uk");
        addEndpoint(Service.PANDA, Stage.PROD, true, Region.FE, "https://api-sandbox.amazon.co.jp");
        addEndpoint(Service.PANDA, Stage.PROD, false, Region.NA, "https://api.amazon.com");
        addEndpoint(Service.PANDA, Stage.PROD, false, Region.EU, "https://api.amazon.co.uk");
        addEndpoint(Service.PANDA, Stage.PROD, false, Region.FE, "https://api.amazon.co.jp");
    }

    public EndpointDomainBuilder(Context context, AppInfo appInfo) {
        this.stage = Stage.PROD;
        this.region = Region.NA;
        this.region = StoredPreferences.getRegion(context);
        this.stage = DefaultLibraryInfo.getOverrideLibraryStage();
        if (appInfo != null) {
            this.pandaEndpointFromAPIKey = appInfo.getExchangeHost();
        }
    }

    public EndpointDomainBuilder forService(Service service) {
        this.service = service;
        return this;
    }

    public EndpointDomainBuilder forStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public EndpointDomainBuilder forSandbox(boolean z) {
        this.isSandbox = z;
        return this;
    }

    public EndpointDomainBuilder forRegion(Region region) {
        this.region = region;
        return this;
    }

    public String getDomain() throws MalformedURLException {
        if (Region.AUTO == this.region) {
            this.region = getRegionForAPIKey();
        }
        return allDomainsMap.get(getKey(this.service, this.stage, this.isSandbox, this.region));
    }

    public Region getRegionForAPIKey() {
        Region region = Region.NA;
        try {
            return this.pandaEndpointFromAPIKey != null ? domainToRegionMap.get(getDomainFor(this.pandaEndpointFromAPIKey)) : region;
        } catch (MalformedURLException unused) {
            return region;
        }
    }

    private static void addEndpoint(Service service, Stage stage, boolean z, Region region, String str) {
        allDomainsMap.put(getKey(service, stage, z, region), str);
        if (Region.AUTO == region || Service.PANDA != service) {
            return;
        }
        domainToRegionMap.put(str, region);
    }

    private String getDomainFor(String str) throws MalformedURLException {
        return HTTPS + new URL(str).getHost();
    }

    private static String getKey(Service service, Stage stage, boolean z, Region region) {
        return String.format("%s.%s.%s.%s", service.toString(), stage.toString(), Boolean.valueOf(z), region.toString());
    }
}