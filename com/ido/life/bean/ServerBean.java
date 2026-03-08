package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.module.user.country.CountryChooseActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServerBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\u0018\u00002\u00020\u0001:\u000b\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\rB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u000e"}, d2 = {"Lcom/ido/life/bean/ServerBean;", "", "()V", "BindAuthMesasgeList", "GrantAcceptInvitedRequest", "HomeCardRequest", "InvitedMessage", "LifeCycleUploadBean", "MemberInfo", "MensConfigReponse", "MessageCountInfo", "MessageRequest", "RecommendArticleList", "UpdateMessageRequest", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ServerBean {

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u0000 \u00042\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\t"}, d2 = {"Lcom/ido/life/bean/ServerBean$MemberInfo;", "", "()V", "Account", "Companion", "InvitedInfo", "MemberProfile", "ServerEntity", "UserStatus", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class MemberInfo {
        public static final String TYPE_ADMIN = "ADMIN";
        public static final String TYPE_READ = "READ";
        public static final String TYPE_ACCOUNT = "USERNAME";
        public static final String TYPE_EMAIL = "EMAIL";

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\rHÆ\u0003JW\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006'"}, d2 = {"Lcom/ido/life/bean/ServerBean$MemberInfo$ServerEntity;", "", "adminToken", "", "readToken", "userinfo", "Lcom/ido/life/bean/ServerBean$MemberInfo$MemberProfile;", "userStatus", "Lcom/ido/life/bean/ServerBean$MemberInfo$UserStatus;", "accounts", "", "Lcom/ido/life/bean/ServerBean$MemberInfo$Account;", "shareGrantRequest", "Lcom/ido/life/bean/ServerBean$MemberInfo$InvitedInfo;", "(Ljava/lang/String;Ljava/lang/String;Lcom/ido/life/bean/ServerBean$MemberInfo$MemberProfile;Lcom/ido/life/bean/ServerBean$MemberInfo$UserStatus;Ljava/util/List;Lcom/ido/life/bean/ServerBean$MemberInfo$InvitedInfo;)V", "getAccounts", "()Ljava/util/List;", "getAdminToken", "()Ljava/lang/String;", "getReadToken", "getShareGrantRequest", "()Lcom/ido/life/bean/ServerBean$MemberInfo$InvitedInfo;", "getUserStatus", "()Lcom/ido/life/bean/ServerBean$MemberInfo$UserStatus;", "getUserinfo", "()Lcom/ido/life/bean/ServerBean$MemberInfo$MemberProfile;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class ServerEntity {
            private final List<Account> accounts;
            private final String adminToken;
            private final String readToken;
            private final InvitedInfo shareGrantRequest;
            private final UserStatus userStatus;
            private final MemberProfile userinfo;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ ServerEntity copy$default(ServerEntity serverEntity, String str, String str2, MemberProfile memberProfile, UserStatus userStatus, List list, InvitedInfo invitedInfo, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = serverEntity.adminToken;
                }
                if ((i & 2) != 0) {
                    str2 = serverEntity.readToken;
                }
                String str3 = str2;
                if ((i & 4) != 0) {
                    memberProfile = serverEntity.userinfo;
                }
                MemberProfile memberProfile2 = memberProfile;
                if ((i & 8) != 0) {
                    userStatus = serverEntity.userStatus;
                }
                UserStatus userStatus2 = userStatus;
                if ((i & 16) != 0) {
                    list = serverEntity.accounts;
                }
                List list2 = list;
                if ((i & 32) != 0) {
                    invitedInfo = serverEntity.shareGrantRequest;
                }
                return serverEntity.copy(str, str3, memberProfile2, userStatus2, list2, invitedInfo);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAdminToken() {
                return this.adminToken;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getReadToken() {
                return this.readToken;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final MemberProfile getUserinfo() {
                return this.userinfo;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final UserStatus getUserStatus() {
                return this.userStatus;
            }

            public final List<Account> component5() {
                return this.accounts;
            }

            /* JADX INFO: renamed from: component6, reason: from getter */
            public final InvitedInfo getShareGrantRequest() {
                return this.shareGrantRequest;
            }

            public final ServerEntity copy(String adminToken, String readToken, MemberProfile userinfo, UserStatus userStatus, List<Account> accounts, InvitedInfo shareGrantRequest) {
                return new ServerEntity(adminToken, readToken, userinfo, userStatus, accounts, shareGrantRequest);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ServerEntity)) {
                    return false;
                }
                ServerEntity serverEntity = (ServerEntity) other;
                return Intrinsics.areEqual(this.adminToken, serverEntity.adminToken) && Intrinsics.areEqual(this.readToken, serverEntity.readToken) && Intrinsics.areEqual(this.userinfo, serverEntity.userinfo) && Intrinsics.areEqual(this.userStatus, serverEntity.userStatus) && Intrinsics.areEqual(this.accounts, serverEntity.accounts) && Intrinsics.areEqual(this.shareGrantRequest, serverEntity.shareGrantRequest);
            }

            public int hashCode() {
                String str = this.adminToken;
                int iHashCode = (str != null ? str.hashCode() : 0) * 31;
                String str2 = this.readToken;
                int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
                MemberProfile memberProfile = this.userinfo;
                int iHashCode3 = (iHashCode2 + (memberProfile != null ? memberProfile.hashCode() : 0)) * 31;
                UserStatus userStatus = this.userStatus;
                int iHashCode4 = (iHashCode3 + (userStatus != null ? userStatus.hashCode() : 0)) * 31;
                List<Account> list = this.accounts;
                int iHashCode5 = (iHashCode4 + (list != null ? list.hashCode() : 0)) * 31;
                InvitedInfo invitedInfo = this.shareGrantRequest;
                return iHashCode5 + (invitedInfo != null ? invitedInfo.hashCode() : 0);
            }

            public String toString() {
                return "ServerEntity(adminToken=" + this.adminToken + ", readToken=" + this.readToken + ", userinfo=" + this.userinfo + ", userStatus=" + this.userStatus + ", accounts=" + this.accounts + ", shareGrantRequest=" + this.shareGrantRequest + ")";
            }

            public ServerEntity(String str, String str2, MemberProfile memberProfile, UserStatus userStatus, List<Account> list, InvitedInfo invitedInfo) {
                this.adminToken = str;
                this.readToken = str2;
                this.userinfo = memberProfile;
                this.userStatus = userStatus;
                this.accounts = list;
                this.shareGrantRequest = invitedInfo;
            }

            public final String getAdminToken() {
                return this.adminToken;
            }

            public final String getReadToken() {
                return this.readToken;
            }

            public final MemberProfile getUserinfo() {
                return this.userinfo;
            }

            public final UserStatus getUserStatus() {
                return this.userStatus;
            }

            public final List<Account> getAccounts() {
                return this.accounts;
            }

            public final InvitedInfo getShareGrantRequest() {
                return this.shareGrantRequest;
            }
        }

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b3\b\u0086\b\u0018\u00002\u00020\u0001B\u0099\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0017J\u0010\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010)J\t\u00103\u001a\u00020\tHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0015HÆ\u0003¢\u0006\u0002\u0010,J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010=\u001a\u00020\tHÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010?\u001a\u00020\fHÆ\u0003J\t\u0010@\u001a\u00020\tHÆ\u0003J\t\u0010A\u001a\u00020\fHÆ\u0003JÂ\u0001\u0010B\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010CJ\u0013\u0010D\u001a\u00020\u00152\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010F\u001a\u00020\tHÖ\u0001J\t\u0010G\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0019\"\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\n\n\u0002\u0010-\u001a\u0004\b+\u0010,R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u000e\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b0\u0010&R\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b1\u0010$¨\u0006H"}, d2 = {"Lcom/ido/life/bean/ServerBean$MemberInfo$MemberProfile;", "", "id", "", "displayName", "", "avatarUrl", "email", "gender", "", "birthday", "height", "", "heightUnit", "weight", "weightUnit", CountryChooseActivity.COUNTRY, "city", "areaCode", "timestamp", "owner", "", "account", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;FIFILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Boolean;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getAreaCode", "setAreaCode", "(Ljava/lang/String;)V", "getAvatarUrl", "getBirthday", "getCity", "getCountry", "getDisplayName", "getEmail", "getGender", "()I", "getHeight", "()F", "getHeightUnit", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOwner", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getTimestamp", "()J", "getWeight", "getWeightUnit", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;FIFILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Boolean;Ljava/lang/String;)Lcom/ido/life/bean/ServerBean$MemberInfo$MemberProfile;", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class MemberProfile {
            private final String account;
            private String areaCode;
            private final String avatarUrl;
            private final String birthday;
            private final String city;
            private final String country;
            private final String displayName;
            private final String email;
            private final int gender;
            private final float height;
            private final int heightUnit;
            private final Long id;
            private final Boolean owner;
            private final long timestamp;
            private final float weight;
            private final int weightUnit;

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Long getId() {
                return this.id;
            }

            /* JADX INFO: renamed from: component10, reason: from getter */
            public final int getWeightUnit() {
                return this.weightUnit;
            }

            /* JADX INFO: renamed from: component11, reason: from getter */
            public final String getCountry() {
                return this.country;
            }

            /* JADX INFO: renamed from: component12, reason: from getter */
            public final String getCity() {
                return this.city;
            }

            /* JADX INFO: renamed from: component13, reason: from getter */
            public final String getAreaCode() {
                return this.areaCode;
            }

            /* JADX INFO: renamed from: component14, reason: from getter */
            public final long getTimestamp() {
                return this.timestamp;
            }

            /* JADX INFO: renamed from: component15, reason: from getter */
            public final Boolean getOwner() {
                return this.owner;
            }

            /* JADX INFO: renamed from: component16, reason: from getter */
            public final String getAccount() {
                return this.account;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getDisplayName() {
                return this.displayName;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getAvatarUrl() {
                return this.avatarUrl;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final int getGender() {
                return this.gender;
            }

            /* JADX INFO: renamed from: component6, reason: from getter */
            public final String getBirthday() {
                return this.birthday;
            }

            /* JADX INFO: renamed from: component7, reason: from getter */
            public final float getHeight() {
                return this.height;
            }

            /* JADX INFO: renamed from: component8, reason: from getter */
            public final int getHeightUnit() {
                return this.heightUnit;
            }

            /* JADX INFO: renamed from: component9, reason: from getter */
            public final float getWeight() {
                return this.weight;
            }

            public final MemberProfile copy(Long id, String displayName, String avatarUrl, String email, int gender, String birthday, float height, int heightUnit, float weight, int weightUnit, String country, String city, String areaCode, long timestamp, Boolean owner, String account) {
                return new MemberProfile(id, displayName, avatarUrl, email, gender, birthday, height, heightUnit, weight, weightUnit, country, city, areaCode, timestamp, owner, account);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof MemberProfile)) {
                    return false;
                }
                MemberProfile memberProfile = (MemberProfile) other;
                return Intrinsics.areEqual(this.id, memberProfile.id) && Intrinsics.areEqual(this.displayName, memberProfile.displayName) && Intrinsics.areEqual(this.avatarUrl, memberProfile.avatarUrl) && Intrinsics.areEqual(this.email, memberProfile.email) && this.gender == memberProfile.gender && Intrinsics.areEqual(this.birthday, memberProfile.birthday) && Float.compare(this.height, memberProfile.height) == 0 && this.heightUnit == memberProfile.heightUnit && Float.compare(this.weight, memberProfile.weight) == 0 && this.weightUnit == memberProfile.weightUnit && Intrinsics.areEqual(this.country, memberProfile.country) && Intrinsics.areEqual(this.city, memberProfile.city) && Intrinsics.areEqual(this.areaCode, memberProfile.areaCode) && this.timestamp == memberProfile.timestamp && Intrinsics.areEqual(this.owner, memberProfile.owner) && Intrinsics.areEqual(this.account, memberProfile.account);
            }

            public int hashCode() {
                Long l = this.id;
                int iHashCode = (l != null ? l.hashCode() : 0) * 31;
                String str = this.displayName;
                int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
                String str2 = this.avatarUrl;
                int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
                String str3 = this.email;
                int iHashCode4 = (((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.valueOf(this.gender).hashCode()) * 31;
                String str4 = this.birthday;
                int iHashCode5 = (((((((((iHashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31) + Float.valueOf(this.height).hashCode()) * 31) + Integer.valueOf(this.heightUnit).hashCode()) * 31) + Float.valueOf(this.weight).hashCode()) * 31) + Integer.valueOf(this.weightUnit).hashCode()) * 31;
                String str5 = this.country;
                int iHashCode6 = (iHashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
                String str6 = this.city;
                int iHashCode7 = (iHashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
                String str7 = this.areaCode;
                int iHashCode8 = (((iHashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31) + Long.valueOf(this.timestamp).hashCode()) * 31;
                Boolean bool = this.owner;
                int iHashCode9 = (iHashCode8 + (bool != null ? bool.hashCode() : 0)) * 31;
                String str8 = this.account;
                return iHashCode9 + (str8 != null ? str8.hashCode() : 0);
            }

            public String toString() {
                return "MemberProfile(id=" + this.id + ", displayName=" + this.displayName + ", avatarUrl=" + this.avatarUrl + ", email=" + this.email + ", gender=" + this.gender + ", birthday=" + this.birthday + ", height=" + this.height + ", heightUnit=" + this.heightUnit + ", weight=" + this.weight + ", weightUnit=" + this.weightUnit + ", country=" + this.country + ", city=" + this.city + ", areaCode=" + this.areaCode + ", timestamp=" + this.timestamp + ", owner=" + this.owner + ", account=" + this.account + ")";
            }

            public MemberProfile(Long l, String str, String str2, String str3, int i, String str4, float f2, int i2, float f3, int i3, String str5, String str6, String str7, long j, Boolean bool, String str8) {
                this.id = l;
                this.displayName = str;
                this.avatarUrl = str2;
                this.email = str3;
                this.gender = i;
                this.birthday = str4;
                this.height = f2;
                this.heightUnit = i2;
                this.weight = f3;
                this.weightUnit = i3;
                this.country = str5;
                this.city = str6;
                this.areaCode = str7;
                this.timestamp = j;
                this.owner = bool;
                this.account = str8;
            }

            public final Long getId() {
                return this.id;
            }

            public final String getDisplayName() {
                return this.displayName;
            }

            public final String getAvatarUrl() {
                return this.avatarUrl;
            }

            public final String getEmail() {
                return this.email;
            }

            public final int getGender() {
                return this.gender;
            }

            public final String getBirthday() {
                return this.birthday;
            }

            public final float getHeight() {
                return this.height;
            }

            public final int getHeightUnit() {
                return this.heightUnit;
            }

            public final float getWeight() {
                return this.weight;
            }

            public final int getWeightUnit() {
                return this.weightUnit;
            }

            public final String getCountry() {
                return this.country;
            }

            public final String getCity() {
                return this.city;
            }

            public final String getAreaCode() {
                return this.areaCode;
            }

            public final void setAreaCode(String str) {
                this.areaCode = str;
            }

            public final long getTimestamp() {
                return this.timestamp;
            }

            public final Boolean getOwner() {
                return this.owner;
            }

            public final String getAccount() {
                return this.account;
            }
        }

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\bHÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/ido/life/bean/ServerBean$MemberInfo$UserStatus;", "", "emailVerifyType", "", "enabled", "", "validEmail", "email", "", "(IZZLjava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getEmailVerifyType", "()I", "getEnabled", "()Z", "getValidEmail", "component1", "component2", "component3", "component4", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class UserStatus {
            private final String email;
            private final int emailVerifyType;
            private final boolean enabled;
            private final boolean validEmail;

            public static /* synthetic */ UserStatus copy$default(UserStatus userStatus, int i, boolean z, boolean z2, String str, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = userStatus.emailVerifyType;
                }
                if ((i2 & 2) != 0) {
                    z = userStatus.enabled;
                }
                if ((i2 & 4) != 0) {
                    z2 = userStatus.validEmail;
                }
                if ((i2 & 8) != 0) {
                    str = userStatus.email;
                }
                return userStatus.copy(i, z, z2, str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getEmailVerifyType() {
                return this.emailVerifyType;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final boolean getEnabled() {
                return this.enabled;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final boolean getValidEmail() {
                return this.validEmail;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            public final UserStatus copy(int emailVerifyType, boolean enabled, boolean validEmail, String email) {
                return new UserStatus(emailVerifyType, enabled, validEmail, email);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof UserStatus)) {
                    return false;
                }
                UserStatus userStatus = (UserStatus) other;
                return this.emailVerifyType == userStatus.emailVerifyType && this.enabled == userStatus.enabled && this.validEmail == userStatus.validEmail && Intrinsics.areEqual(this.email, userStatus.email);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [int] */
            /* JADX WARN: Type inference failed for: r1v10 */
            /* JADX WARN: Type inference failed for: r1v11 */
            /* JADX WARN: Type inference failed for: r1v3, types: [int] */
            /* JADX WARN: Type inference failed for: r1v8 */
            /* JADX WARN: Type inference failed for: r1v9 */
            public int hashCode() {
                int iHashCode = Integer.valueOf(this.emailVerifyType).hashCode() * 31;
                boolean z = this.enabled;
                ?? r1 = z;
                if (z) {
                    r1 = 1;
                }
                int i = (iHashCode + r1) * 31;
                boolean z2 = this.validEmail;
                ?? r12 = z2;
                if (z2) {
                    r12 = 1;
                }
                int i2 = (i + r12) * 31;
                String str = this.email;
                return i2 + (str != null ? str.hashCode() : 0);
            }

            public String toString() {
                return "UserStatus(emailVerifyType=" + this.emailVerifyType + ", enabled=" + this.enabled + ", validEmail=" + this.validEmail + ", email=" + this.email + ")";
            }

            public UserStatus(int i, boolean z, boolean z2, String str) {
                this.emailVerifyType = i;
                this.enabled = z;
                this.validEmail = z2;
                this.email = str;
            }

            public final int getEmailVerifyType() {
                return this.emailVerifyType;
            }

            public final boolean getEnabled() {
                return this.enabled;
            }

            public final boolean getValidEmail() {
                return this.validEmail;
            }

            public final String getEmail() {
                return this.email;
            }
        }

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003JJ\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/ido/life/bean/ServerBean$MemberInfo$Account;", "", "id", "", "username", "", "creationTime", "lastLoginTime", "accountType", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccountType", "()Ljava/lang/String;", "getCreationTime", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLastLoginTime", "getUsername", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ido/life/bean/ServerBean$MemberInfo$Account;", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class Account {
            private final String accountType;
            private final String creationTime;
            private final Long id;
            private final String lastLoginTime;
            private final String username;

            public static /* synthetic */ Account copy$default(Account account, Long l, String str, String str2, String str3, String str4, int i, Object obj) {
                if ((i & 1) != 0) {
                    l = account.id;
                }
                if ((i & 2) != 0) {
                    str = account.username;
                }
                String str5 = str;
                if ((i & 4) != 0) {
                    str2 = account.creationTime;
                }
                String str6 = str2;
                if ((i & 8) != 0) {
                    str3 = account.lastLoginTime;
                }
                String str7 = str3;
                if ((i & 16) != 0) {
                    str4 = account.accountType;
                }
                return account.copy(l, str5, str6, str7, str4);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Long getId() {
                return this.id;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getUsername() {
                return this.username;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getCreationTime() {
                return this.creationTime;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getLastLoginTime() {
                return this.lastLoginTime;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final String getAccountType() {
                return this.accountType;
            }

            public final Account copy(Long id, String username, String creationTime, String lastLoginTime, String accountType) {
                return new Account(id, username, creationTime, lastLoginTime, accountType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Account)) {
                    return false;
                }
                Account account = (Account) other;
                return Intrinsics.areEqual(this.id, account.id) && Intrinsics.areEqual(this.username, account.username) && Intrinsics.areEqual(this.creationTime, account.creationTime) && Intrinsics.areEqual(this.lastLoginTime, account.lastLoginTime) && Intrinsics.areEqual(this.accountType, account.accountType);
            }

            public int hashCode() {
                Long l = this.id;
                int iHashCode = (l != null ? l.hashCode() : 0) * 31;
                String str = this.username;
                int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
                String str2 = this.creationTime;
                int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
                String str3 = this.lastLoginTime;
                int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
                String str4 = this.accountType;
                return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
            }

            public String toString() {
                return "Account(id=" + this.id + ", username=" + this.username + ", creationTime=" + this.creationTime + ", lastLoginTime=" + this.lastLoginTime + ", accountType=" + this.accountType + ")";
            }

            public Account(Long l, String str, String str2, String str3, String str4) {
                this.id = l;
                this.username = str;
                this.creationTime = str2;
                this.lastLoginTime = str3;
                this.accountType = str4;
            }

            public final Long getId() {
                return this.id;
            }

            public final String getUsername() {
                return this.username;
            }

            public final String getCreationTime() {
                return this.creationTime;
            }

            public final String getLastLoginTime() {
                return this.lastLoginTime;
            }

            public final String getAccountType() {
                return this.accountType;
            }
        }

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b,\b\u0086\b\u0018\u00002\u00020\u0001B\u0093\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013\u0012\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\u0002\u0010\u0016J\u0010\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010#J\t\u0010-\u001a\u00020\u000fHÆ\u0003J\t\u0010.\u001a\u00020\u000fHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u00100\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013HÆ\u0003J\u0011\u00101\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u00109\u001a\u00020\rHÆ\u0003J¸\u0001\u0010:\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013HÆ\u0001¢\u0006\u0002\u0010;J\u0013\u0010<\u001a\u00020\u000f2\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010>\u001a\u00020\rHÖ\u0001J\t\u0010?\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0019\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006@"}, d2 = {"Lcom/ido/life/bean/ServerBean$MemberInfo$InvitedInfo;", "", "id", "", "fromUserId", "fromUserName", "", "fromUserImage", "toUserId", "toUserName", "toUserImage", "acceptedTime", "type", "", "accepted", "", "readed", "createdTime", "toAccountList", "", "Lcom/ido/life/bean/ServerBean$MemberInfo$Account;", "fromAccounts", "(Ljava/lang/Long;JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IZZLjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getAccepted", "()Z", "getAcceptedTime", "()Ljava/lang/String;", "getCreatedTime", "getFromAccounts", "()Ljava/util/List;", "getFromUserId", "()J", "getFromUserImage", "getFromUserName", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getReaded", "getToAccountList", "getToUserId", "getToUserImage", "getToUserName", "getType", "()I", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IZZLjava/lang/String;Ljava/util/List;Ljava/util/List;)Lcom/ido/life/bean/ServerBean$MemberInfo$InvitedInfo;", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class InvitedInfo {
            private final boolean accepted;
            private final String acceptedTime;
            private final String createdTime;
            private final List<Account> fromAccounts;
            private final long fromUserId;
            private final String fromUserImage;
            private final String fromUserName;
            private final Long id;
            private final boolean readed;
            private final List<Account> toAccountList;
            private final long toUserId;
            private final String toUserImage;
            private final String toUserName;
            private final int type;

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Long getId() {
                return this.id;
            }

            /* JADX INFO: renamed from: component10, reason: from getter */
            public final boolean getAccepted() {
                return this.accepted;
            }

            /* JADX INFO: renamed from: component11, reason: from getter */
            public final boolean getReaded() {
                return this.readed;
            }

            /* JADX INFO: renamed from: component12, reason: from getter */
            public final String getCreatedTime() {
                return this.createdTime;
            }

            public final List<Account> component13() {
                return this.toAccountList;
            }

            public final List<Account> component14() {
                return this.fromAccounts;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final long getFromUserId() {
                return this.fromUserId;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getFromUserName() {
                return this.fromUserName;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getFromUserImage() {
                return this.fromUserImage;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final long getToUserId() {
                return this.toUserId;
            }

            /* JADX INFO: renamed from: component6, reason: from getter */
            public final String getToUserName() {
                return this.toUserName;
            }

            /* JADX INFO: renamed from: component7, reason: from getter */
            public final String getToUserImage() {
                return this.toUserImage;
            }

            /* JADX INFO: renamed from: component8, reason: from getter */
            public final String getAcceptedTime() {
                return this.acceptedTime;
            }

            /* JADX INFO: renamed from: component9, reason: from getter */
            public final int getType() {
                return this.type;
            }

            public final InvitedInfo copy(Long id, long fromUserId, String fromUserName, String fromUserImage, long toUserId, String toUserName, String toUserImage, String acceptedTime, int type, boolean accepted, boolean readed, String createdTime, List<Account> toAccountList, List<Account> fromAccounts) {
                return new InvitedInfo(id, fromUserId, fromUserName, fromUserImage, toUserId, toUserName, toUserImage, acceptedTime, type, accepted, readed, createdTime, toAccountList, fromAccounts);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof InvitedInfo)) {
                    return false;
                }
                InvitedInfo invitedInfo = (InvitedInfo) other;
                return Intrinsics.areEqual(this.id, invitedInfo.id) && this.fromUserId == invitedInfo.fromUserId && Intrinsics.areEqual(this.fromUserName, invitedInfo.fromUserName) && Intrinsics.areEqual(this.fromUserImage, invitedInfo.fromUserImage) && this.toUserId == invitedInfo.toUserId && Intrinsics.areEqual(this.toUserName, invitedInfo.toUserName) && Intrinsics.areEqual(this.toUserImage, invitedInfo.toUserImage) && Intrinsics.areEqual(this.acceptedTime, invitedInfo.acceptedTime) && this.type == invitedInfo.type && this.accepted == invitedInfo.accepted && this.readed == invitedInfo.readed && Intrinsics.areEqual(this.createdTime, invitedInfo.createdTime) && Intrinsics.areEqual(this.toAccountList, invitedInfo.toAccountList) && Intrinsics.areEqual(this.fromAccounts, invitedInfo.fromAccounts);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v22, types: [int] */
            /* JADX WARN: Type inference failed for: r2v24, types: [int] */
            /* JADX WARN: Type inference failed for: r2v34 */
            /* JADX WARN: Type inference failed for: r2v35 */
            /* JADX WARN: Type inference failed for: r2v41 */
            /* JADX WARN: Type inference failed for: r2v42 */
            public int hashCode() {
                Long l = this.id;
                int iHashCode = (((l != null ? l.hashCode() : 0) * 31) + Long.valueOf(this.fromUserId).hashCode()) * 31;
                String str = this.fromUserName;
                int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
                String str2 = this.fromUserImage;
                int iHashCode3 = (((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Long.valueOf(this.toUserId).hashCode()) * 31;
                String str3 = this.toUserName;
                int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
                String str4 = this.toUserImage;
                int iHashCode5 = (iHashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
                String str5 = this.acceptedTime;
                int iHashCode6 = (((iHashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31) + Integer.valueOf(this.type).hashCode()) * 31;
                boolean z = this.accepted;
                ?? r2 = z;
                if (z) {
                    r2 = 1;
                }
                int i = (iHashCode6 + r2) * 31;
                boolean z2 = this.readed;
                ?? r22 = z2;
                if (z2) {
                    r22 = 1;
                }
                int i2 = (i + r22) * 31;
                String str6 = this.createdTime;
                int iHashCode7 = (i2 + (str6 != null ? str6.hashCode() : 0)) * 31;
                List<Account> list = this.toAccountList;
                int iHashCode8 = (iHashCode7 + (list != null ? list.hashCode() : 0)) * 31;
                List<Account> list2 = this.fromAccounts;
                return iHashCode8 + (list2 != null ? list2.hashCode() : 0);
            }

            public String toString() {
                return "InvitedInfo(id=" + this.id + ", fromUserId=" + this.fromUserId + ", fromUserName=" + this.fromUserName + ", fromUserImage=" + this.fromUserImage + ", toUserId=" + this.toUserId + ", toUserName=" + this.toUserName + ", toUserImage=" + this.toUserImage + ", acceptedTime=" + this.acceptedTime + ", type=" + this.type + ", accepted=" + this.accepted + ", readed=" + this.readed + ", createdTime=" + this.createdTime + ", toAccountList=" + this.toAccountList + ", fromAccounts=" + this.fromAccounts + ")";
            }

            public InvitedInfo(Long l, long j, String str, String str2, long j2, String str3, String str4, String str5, int i, boolean z, boolean z2, String str6, List<Account> list, List<Account> list2) {
                this.id = l;
                this.fromUserId = j;
                this.fromUserName = str;
                this.fromUserImage = str2;
                this.toUserId = j2;
                this.toUserName = str3;
                this.toUserImage = str4;
                this.acceptedTime = str5;
                this.type = i;
                this.accepted = z;
                this.readed = z2;
                this.createdTime = str6;
                this.toAccountList = list;
                this.fromAccounts = list2;
            }

            public final Long getId() {
                return this.id;
            }

            public final long getFromUserId() {
                return this.fromUserId;
            }

            public final String getFromUserName() {
                return this.fromUserName;
            }

            public final String getFromUserImage() {
                return this.fromUserImage;
            }

            public final long getToUserId() {
                return this.toUserId;
            }

            public final String getToUserName() {
                return this.toUserName;
            }

            public final String getToUserImage() {
                return this.toUserImage;
            }

            public final String getAcceptedTime() {
                return this.acceptedTime;
            }

            public final int getType() {
                return this.type;
            }

            public final boolean getAccepted() {
                return this.accepted;
            }

            public final boolean getReaded() {
                return this.readed;
            }

            public final String getCreatedTime() {
                return this.createdTime;
            }

            public final List<Account> getToAccountList() {
                return this.toAccountList;
            }

            public final List<Account> getFromAccounts() {
                return this.fromAccounts;
            }
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/ido/life/bean/ServerBean$InvitedMessage;", "", "()V", "ServerEntity", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class InvitedMessage {

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003JA\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/ido/life/bean/ServerBean$InvitedMessage$ServerEntity;", "", "items", "", "Lcom/ido/life/bean/ServerBean$MemberInfo$InvitedInfo;", "numRows", "", "totalPages", "pageSize", "currentPage", "(Ljava/util/List;IIII)V", "getCurrentPage", "()I", "getItems", "()Ljava/util/List;", "getNumRows", "getPageSize", "getTotalPages", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class ServerEntity {
            private final int currentPage;
            private final List<MemberInfo.InvitedInfo> items;
            private final int numRows;
            private final int pageSize;
            private final int totalPages;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ ServerEntity copy$default(ServerEntity serverEntity, List list, int i, int i2, int i3, int i4, int i5, Object obj) {
                if ((i5 & 1) != 0) {
                    list = serverEntity.items;
                }
                if ((i5 & 2) != 0) {
                    i = serverEntity.numRows;
                }
                int i6 = i;
                if ((i5 & 4) != 0) {
                    i2 = serverEntity.totalPages;
                }
                int i7 = i2;
                if ((i5 & 8) != 0) {
                    i3 = serverEntity.pageSize;
                }
                int i8 = i3;
                if ((i5 & 16) != 0) {
                    i4 = serverEntity.currentPage;
                }
                return serverEntity.copy(list, i6, i7, i8, i4);
            }

            public final List<MemberInfo.InvitedInfo> component1() {
                return this.items;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getNumRows() {
                return this.numRows;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final int getTotalPages() {
                return this.totalPages;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final int getPageSize() {
                return this.pageSize;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final int getCurrentPage() {
                return this.currentPage;
            }

            public final ServerEntity copy(List<MemberInfo.InvitedInfo> items, int numRows, int totalPages, int pageSize, int currentPage) {
                Intrinsics.checkParameterIsNotNull(items, "items");
                return new ServerEntity(items, numRows, totalPages, pageSize, currentPage);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ServerEntity)) {
                    return false;
                }
                ServerEntity serverEntity = (ServerEntity) other;
                return Intrinsics.areEqual(this.items, serverEntity.items) && this.numRows == serverEntity.numRows && this.totalPages == serverEntity.totalPages && this.pageSize == serverEntity.pageSize && this.currentPage == serverEntity.currentPage;
            }

            public int hashCode() {
                List<MemberInfo.InvitedInfo> list = this.items;
                return ((((((((list != null ? list.hashCode() : 0) * 31) + Integer.valueOf(this.numRows).hashCode()) * 31) + Integer.valueOf(this.totalPages).hashCode()) * 31) + Integer.valueOf(this.pageSize).hashCode()) * 31) + Integer.valueOf(this.currentPage).hashCode();
            }

            public String toString() {
                return "ServerEntity(items=" + this.items + ", numRows=" + this.numRows + ", totalPages=" + this.totalPages + ", pageSize=" + this.pageSize + ", currentPage=" + this.currentPage + ")";
            }

            public ServerEntity(List<MemberInfo.InvitedInfo> items, int i, int i2, int i3, int i4) {
                Intrinsics.checkParameterIsNotNull(items, "items");
                this.items = items;
                this.numRows = i;
                this.totalPages = i2;
                this.pageSize = i3;
                this.currentPage = i4;
            }

            public final List<MemberInfo.InvitedInfo> getItems() {
                return this.items;
            }

            public final int getNumRows() {
                return this.numRows;
            }

            public final int getTotalPages() {
                return this.totalPages;
            }

            public final int getPageSize() {
                return this.pageSize;
            }

            public final int getCurrentPage() {
                return this.currentPage;
            }
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/ido/life/bean/ServerBean$MessageCountInfo;", "", "()V", "MessageItem", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class MessageCountInfo {

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/ido/life/bean/ServerBean$MessageCountInfo$MessageItem;", "", "type", "", "count", "", "(Ljava/lang/String;I)V", "getCount", "()I", "getType", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class MessageItem {
            private final int count;
            private final String type;

            public static /* synthetic */ MessageItem copy$default(MessageItem messageItem, String str, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    str = messageItem.type;
                }
                if ((i2 & 2) != 0) {
                    i = messageItem.count;
                }
                return messageItem.copy(str, i);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getType() {
                return this.type;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getCount() {
                return this.count;
            }

            public final MessageItem copy(String type, int count) {
                return new MessageItem(type, count);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof MessageItem)) {
                    return false;
                }
                MessageItem messageItem = (MessageItem) other;
                return Intrinsics.areEqual(this.type, messageItem.type) && this.count == messageItem.count;
            }

            public int hashCode() {
                String str = this.type;
                return ((str != null ? str.hashCode() : 0) * 31) + Integer.valueOf(this.count).hashCode();
            }

            public String toString() {
                return "MessageItem(type=" + this.type + ", count=" + this.count + ")";
            }

            public MessageItem(String str, int i) {
                this.type = str;
                this.count = i;
            }

            public final int getCount() {
                return this.count;
            }

            public final String getType() {
                return this.type;
            }
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\u0003HÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/ido/life/bean/ServerBean$MessageRequest;", "", "type", "", "(I)V", "getType", "()I", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class MessageRequest {
        private final int type;

        public static /* synthetic */ MessageRequest copy$default(MessageRequest messageRequest, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = messageRequest.type;
            }
            return messageRequest.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getType() {
            return this.type;
        }

        public final MessageRequest copy(int type) {
            return new MessageRequest(type);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof MessageRequest) && this.type == ((MessageRequest) other).type;
            }
            return true;
        }

        public int hashCode() {
            return Integer.valueOf(this.type).hashCode();
        }

        public String toString() {
            return "MessageRequest(type=" + this.type + ")";
        }

        public MessageRequest(int i) {
            this.type = i;
        }

        public final int getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/ido/life/bean/ServerBean$BindAuthMesasgeList;", "", "()V", "ServerEntity", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class BindAuthMesasgeList {

        /* JADX INFO: compiled from: ServerBean.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b(\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\u0002\u0010\u0015J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\t\u0010*\u001a\u00020\u000fHÆ\u0003J\t\u0010+\u001a\u00020\u000fHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010/\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u00102\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u00105\u001a\u00020\rHÆ\u0003Jª\u0001\u00106\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013HÆ\u0001¢\u0006\u0002\u00107J\u0013\u00108\u001a\u00020\u000f2\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\rHÖ\u0001J\t\u0010;\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b \u0010\u001cR\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b$\u0010\u001cR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006<"}, d2 = {"Lcom/ido/life/bean/ServerBean$BindAuthMesasgeList$ServerEntity;", "", "id", "", "fromUserId", "fromUserName", "", "fromUserImage", "toUserId", "toUserName", "toUserImage", "acceptedTime", "type", "", "accepted", "", "readed", "createdTime", "toAccounts", "", "Lcom/ido/life/bean/ServerBean$MemberInfo$Account;", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZZLjava/lang/String;Ljava/util/List;)V", "getAccepted", "()Z", "getAcceptedTime", "()Ljava/lang/String;", "getCreatedTime", "getFromUserId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFromUserImage", "getFromUserName", "getId", "getReaded", "getToAccounts", "()Ljava/util/List;", "getToUserId", "getToUserImage", "getToUserName", "getType", "()I", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZZLjava/lang/String;Ljava/util/List;)Lcom/ido/life/bean/ServerBean$BindAuthMesasgeList$ServerEntity;", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final /* data */ class ServerEntity {
            private final boolean accepted;
            private final String acceptedTime;
            private final String createdTime;
            private final Long fromUserId;
            private final String fromUserImage;
            private final String fromUserName;
            private final Long id;
            private final boolean readed;
            private final List<MemberInfo.Account> toAccounts;
            private final Long toUserId;
            private final String toUserImage;
            private final String toUserName;
            private final int type;

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Long getId() {
                return this.id;
            }

            /* JADX INFO: renamed from: component10, reason: from getter */
            public final boolean getAccepted() {
                return this.accepted;
            }

            /* JADX INFO: renamed from: component11, reason: from getter */
            public final boolean getReaded() {
                return this.readed;
            }

            /* JADX INFO: renamed from: component12, reason: from getter */
            public final String getCreatedTime() {
                return this.createdTime;
            }

            public final List<MemberInfo.Account> component13() {
                return this.toAccounts;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final Long getFromUserId() {
                return this.fromUserId;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getFromUserName() {
                return this.fromUserName;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getFromUserImage() {
                return this.fromUserImage;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final Long getToUserId() {
                return this.toUserId;
            }

            /* JADX INFO: renamed from: component6, reason: from getter */
            public final String getToUserName() {
                return this.toUserName;
            }

            /* JADX INFO: renamed from: component7, reason: from getter */
            public final String getToUserImage() {
                return this.toUserImage;
            }

            /* JADX INFO: renamed from: component8, reason: from getter */
            public final String getAcceptedTime() {
                return this.acceptedTime;
            }

            /* JADX INFO: renamed from: component9, reason: from getter */
            public final int getType() {
                return this.type;
            }

            public final ServerEntity copy(Long id, Long fromUserId, String fromUserName, String fromUserImage, Long toUserId, String toUserName, String toUserImage, String acceptedTime, int type, boolean accepted, boolean readed, String createdTime, List<MemberInfo.Account> toAccounts) {
                return new ServerEntity(id, fromUserId, fromUserName, fromUserImage, toUserId, toUserName, toUserImage, acceptedTime, type, accepted, readed, createdTime, toAccounts);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ServerEntity)) {
                    return false;
                }
                ServerEntity serverEntity = (ServerEntity) other;
                return Intrinsics.areEqual(this.id, serverEntity.id) && Intrinsics.areEqual(this.fromUserId, serverEntity.fromUserId) && Intrinsics.areEqual(this.fromUserName, serverEntity.fromUserName) && Intrinsics.areEqual(this.fromUserImage, serverEntity.fromUserImage) && Intrinsics.areEqual(this.toUserId, serverEntity.toUserId) && Intrinsics.areEqual(this.toUserName, serverEntity.toUserName) && Intrinsics.areEqual(this.toUserImage, serverEntity.toUserImage) && Intrinsics.areEqual(this.acceptedTime, serverEntity.acceptedTime) && this.type == serverEntity.type && this.accepted == serverEntity.accepted && this.readed == serverEntity.readed && Intrinsics.areEqual(this.createdTime, serverEntity.createdTime) && Intrinsics.areEqual(this.toAccounts, serverEntity.toAccounts);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v24, types: [int] */
            /* JADX WARN: Type inference failed for: r2v26, types: [int] */
            /* JADX WARN: Type inference failed for: r2v32 */
            /* JADX WARN: Type inference failed for: r2v33 */
            /* JADX WARN: Type inference failed for: r2v41 */
            /* JADX WARN: Type inference failed for: r2v42 */
            public int hashCode() {
                Long l = this.id;
                int iHashCode = (l != null ? l.hashCode() : 0) * 31;
                Long l2 = this.fromUserId;
                int iHashCode2 = (iHashCode + (l2 != null ? l2.hashCode() : 0)) * 31;
                String str = this.fromUserName;
                int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
                String str2 = this.fromUserImage;
                int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
                Long l3 = this.toUserId;
                int iHashCode5 = (iHashCode4 + (l3 != null ? l3.hashCode() : 0)) * 31;
                String str3 = this.toUserName;
                int iHashCode6 = (iHashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31;
                String str4 = this.toUserImage;
                int iHashCode7 = (iHashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
                String str5 = this.acceptedTime;
                int iHashCode8 = (((iHashCode7 + (str5 != null ? str5.hashCode() : 0)) * 31) + Integer.valueOf(this.type).hashCode()) * 31;
                boolean z = this.accepted;
                ?? r2 = z;
                if (z) {
                    r2 = 1;
                }
                int i = (iHashCode8 + r2) * 31;
                boolean z2 = this.readed;
                ?? r22 = z2;
                if (z2) {
                    r22 = 1;
                }
                int i2 = (i + r22) * 31;
                String str6 = this.createdTime;
                int iHashCode9 = (i2 + (str6 != null ? str6.hashCode() : 0)) * 31;
                List<MemberInfo.Account> list = this.toAccounts;
                return iHashCode9 + (list != null ? list.hashCode() : 0);
            }

            public String toString() {
                return "ServerEntity(id=" + this.id + ", fromUserId=" + this.fromUserId + ", fromUserName=" + this.fromUserName + ", fromUserImage=" + this.fromUserImage + ", toUserId=" + this.toUserId + ", toUserName=" + this.toUserName + ", toUserImage=" + this.toUserImage + ", acceptedTime=" + this.acceptedTime + ", type=" + this.type + ", accepted=" + this.accepted + ", readed=" + this.readed + ", createdTime=" + this.createdTime + ", toAccounts=" + this.toAccounts + ")";
            }

            public ServerEntity(Long l, Long l2, String str, String str2, Long l3, String str3, String str4, String str5, int i, boolean z, boolean z2, String str6, List<MemberInfo.Account> list) {
                this.id = l;
                this.fromUserId = l2;
                this.fromUserName = str;
                this.fromUserImage = str2;
                this.toUserId = l3;
                this.toUserName = str3;
                this.toUserImage = str4;
                this.acceptedTime = str5;
                this.type = i;
                this.accepted = z;
                this.readed = z2;
                this.createdTime = str6;
                this.toAccounts = list;
            }

            public final Long getId() {
                return this.id;
            }

            public final Long getFromUserId() {
                return this.fromUserId;
            }

            public final String getFromUserName() {
                return this.fromUserName;
            }

            public final String getFromUserImage() {
                return this.fromUserImage;
            }

            public final Long getToUserId() {
                return this.toUserId;
            }

            public final String getToUserName() {
                return this.toUserName;
            }

            public final String getToUserImage() {
                return this.toUserImage;
            }

            public final String getAcceptedTime() {
                return this.acceptedTime;
            }

            public final int getType() {
                return this.type;
            }

            public final boolean getAccepted() {
                return this.accepted;
            }

            public final boolean getReaded() {
                return this.readed;
            }

            public final String getCreatedTime() {
                return this.createdTime;
            }

            public final List<MemberInfo.Account> getToAccounts() {
                return this.toAccounts;
            }
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/ido/life/bean/ServerBean$UpdateMessageRequest;", "", "grantRequestId", "", "messageResourceType", "", "(JI)V", "getGrantRequestId", "()J", "setGrantRequestId", "(J)V", "getMessageResourceType", "()I", "setMessageResourceType", "(I)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class UpdateMessageRequest {
        private long grantRequestId;
        private int messageResourceType;

        public static /* synthetic */ UpdateMessageRequest copy$default(UpdateMessageRequest updateMessageRequest, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = updateMessageRequest.grantRequestId;
            }
            if ((i2 & 2) != 0) {
                i = updateMessageRequest.messageResourceType;
            }
            return updateMessageRequest.copy(j, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getGrantRequestId() {
            return this.grantRequestId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMessageResourceType() {
            return this.messageResourceType;
        }

        public final UpdateMessageRequest copy(long grantRequestId, int messageResourceType) {
            return new UpdateMessageRequest(grantRequestId, messageResourceType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpdateMessageRequest)) {
                return false;
            }
            UpdateMessageRequest updateMessageRequest = (UpdateMessageRequest) other;
            return this.grantRequestId == updateMessageRequest.grantRequestId && this.messageResourceType == updateMessageRequest.messageResourceType;
        }

        public int hashCode() {
            return (Long.valueOf(this.grantRequestId).hashCode() * 31) + Integer.valueOf(this.messageResourceType).hashCode();
        }

        public String toString() {
            return "UpdateMessageRequest(grantRequestId=" + this.grantRequestId + ", messageResourceType=" + this.messageResourceType + ")";
        }

        public UpdateMessageRequest(long j, int i) {
            this.grantRequestId = j;
            this.messageResourceType = i;
        }

        public final long getGrantRequestId() {
            return this.grantRequestId;
        }

        public final int getMessageResourceType() {
            return this.messageResourceType;
        }

        public final void setGrantRequestId(long j) {
            this.grantRequestId = j;
        }

        public final void setMessageResourceType(int i) {
            this.messageResourceType = i;
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/ido/life/bean/ServerBean$GrantAcceptInvitedRequest;", "", "grantRequestId", "", "(J)V", "getGrantRequestId", "()J", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class GrantAcceptInvitedRequest {
        private final long grantRequestId;

        public static /* synthetic */ GrantAcceptInvitedRequest copy$default(GrantAcceptInvitedRequest grantAcceptInvitedRequest, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = grantAcceptInvitedRequest.grantRequestId;
            }
            return grantAcceptInvitedRequest.copy(j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getGrantRequestId() {
            return this.grantRequestId;
        }

        public final GrantAcceptInvitedRequest copy(long grantRequestId) {
            return new GrantAcceptInvitedRequest(grantRequestId);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof GrantAcceptInvitedRequest) && this.grantRequestId == ((GrantAcceptInvitedRequest) other).grantRequestId;
            }
            return true;
        }

        public int hashCode() {
            return Long.valueOf(this.grantRequestId).hashCode();
        }

        public String toString() {
            return "GrantAcceptInvitedRequest(grantRequestId=" + this.grantRequestId + ")";
        }

        public GrantAcceptInvitedRequest(long j) {
            this.grantRequestId = j;
        }

        public final long getGrantRequestId() {
            return this.grantRequestId;
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J5\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/ido/life/bean/ServerBean$HomeCardRequest;", "", "visiableCards", "", "hiddenCards", "version", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getHiddenCards", "()Ljava/lang/String;", "getTimestamp", "()J", "getVersion", "getVisiableCards", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class HomeCardRequest {
        private final String hiddenCards;
        private final long timestamp;
        private final String version;
        private final String visiableCards;

        public static /* synthetic */ HomeCardRequest copy$default(HomeCardRequest homeCardRequest, String str, String str2, String str3, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = homeCardRequest.visiableCards;
            }
            if ((i & 2) != 0) {
                str2 = homeCardRequest.hiddenCards;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = homeCardRequest.version;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                j = homeCardRequest.timestamp;
            }
            return homeCardRequest.copy(str, str4, str5, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getVisiableCards() {
            return this.visiableCards;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getHiddenCards() {
            return this.hiddenCards;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVersion() {
            return this.version;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        public final HomeCardRequest copy(String visiableCards, String hiddenCards, String version, long timestamp) {
            Intrinsics.checkParameterIsNotNull(version, "version");
            return new HomeCardRequest(visiableCards, hiddenCards, version, timestamp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HomeCardRequest)) {
                return false;
            }
            HomeCardRequest homeCardRequest = (HomeCardRequest) other;
            return Intrinsics.areEqual(this.visiableCards, homeCardRequest.visiableCards) && Intrinsics.areEqual(this.hiddenCards, homeCardRequest.hiddenCards) && Intrinsics.areEqual(this.version, homeCardRequest.version) && this.timestamp == homeCardRequest.timestamp;
        }

        public int hashCode() {
            String str = this.visiableCards;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.hiddenCards;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.version;
            return ((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + Long.valueOf(this.timestamp).hashCode();
        }

        public String toString() {
            return "HomeCardRequest(visiableCards=" + this.visiableCards + ", hiddenCards=" + this.hiddenCards + ", version=" + this.version + ", timestamp=" + this.timestamp + ")";
        }

        public HomeCardRequest(String str, String str2, String version, long j) {
            Intrinsics.checkParameterIsNotNull(version, "version");
            this.visiableCards = str;
            this.hiddenCards = str2;
            this.version = version;
            this.timestamp = j;
        }

        public final String getVisiableCards() {
            return this.visiableCards;
        }

        public final String getHiddenCards() {
            return this.hiddenCards;
        }

        public final String getVersion() {
            return this.version;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jb\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\r¨\u0006%"}, d2 = {"Lcom/ido/life/bean/ServerBean$RecommendArticleList;", "", "id", "", CommonDialog.EXTRA_TITLE, "", "subTitle", "contentType", "linkUrl", "mainImageUrl", "createTime", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContentType", "()Ljava/lang/String;", "getCreateTime", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLinkUrl", "getMainImageUrl", "getSubTitle", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ido/life/bean/ServerBean$RecommendArticleList;", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class RecommendArticleList {
        private final String contentType;
        private final String createTime;
        private final Long id;
        private final String linkUrl;
        private final String mainImageUrl;
        private final String subTitle;
        private final String title;

        public static /* synthetic */ RecommendArticleList copy$default(RecommendArticleList recommendArticleList, Long l, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                l = recommendArticleList.id;
            }
            if ((i & 2) != 0) {
                str = recommendArticleList.title;
            }
            String str7 = str;
            if ((i & 4) != 0) {
                str2 = recommendArticleList.subTitle;
            }
            String str8 = str2;
            if ((i & 8) != 0) {
                str3 = recommendArticleList.contentType;
            }
            String str9 = str3;
            if ((i & 16) != 0) {
                str4 = recommendArticleList.linkUrl;
            }
            String str10 = str4;
            if ((i & 32) != 0) {
                str5 = recommendArticleList.mainImageUrl;
            }
            String str11 = str5;
            if ((i & 64) != 0) {
                str6 = recommendArticleList.createTime;
            }
            return recommendArticleList.copy(l, str7, str8, str9, str10, str11, str6);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSubTitle() {
            return this.subTitle;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getContentType() {
            return this.contentType;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getLinkUrl() {
            return this.linkUrl;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getMainImageUrl() {
            return this.mainImageUrl;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCreateTime() {
            return this.createTime;
        }

        public final RecommendArticleList copy(Long id, String title, String subTitle, String contentType, String linkUrl, String mainImageUrl, String createTime) {
            return new RecommendArticleList(id, title, subTitle, contentType, linkUrl, mainImageUrl, createTime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommendArticleList)) {
                return false;
            }
            RecommendArticleList recommendArticleList = (RecommendArticleList) other;
            return Intrinsics.areEqual(this.id, recommendArticleList.id) && Intrinsics.areEqual(this.title, recommendArticleList.title) && Intrinsics.areEqual(this.subTitle, recommendArticleList.subTitle) && Intrinsics.areEqual(this.contentType, recommendArticleList.contentType) && Intrinsics.areEqual(this.linkUrl, recommendArticleList.linkUrl) && Intrinsics.areEqual(this.mainImageUrl, recommendArticleList.mainImageUrl) && Intrinsics.areEqual(this.createTime, recommendArticleList.createTime);
        }

        public int hashCode() {
            Long l = this.id;
            int iHashCode = (l != null ? l.hashCode() : 0) * 31;
            String str = this.title;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.subTitle;
            int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.contentType;
            int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.linkUrl;
            int iHashCode5 = (iHashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.mainImageUrl;
            int iHashCode6 = (iHashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.createTime;
            return iHashCode6 + (str6 != null ? str6.hashCode() : 0);
        }

        public String toString() {
            return "RecommendArticleList(id=" + this.id + ", title=" + this.title + ", subTitle=" + this.subTitle + ", contentType=" + this.contentType + ", linkUrl=" + this.linkUrl + ", mainImageUrl=" + this.mainImageUrl + ", createTime=" + this.createTime + ")";
        }

        public RecommendArticleList(Long l, String str, String str2, String str3, String str4, String str5, String str6) {
            this.id = l;
            this.title = str;
            this.subTitle = str2;
            this.contentType = str3;
            this.linkUrl = str4;
            this.mainImageUrl = str5;
            this.createTime = str6;
        }

        public final String getContentType() {
            return this.contentType;
        }

        public final Long getId() {
            return this.id;
        }

        public final String getSubTitle() {
            return this.subTitle;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getCreateTime() {
            return this.createTime;
        }

        public final String getLinkUrl() {
            return this.linkUrl;
        }

        public final String getMainImageUrl() {
            return this.mainImageUrl;
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/ido/life/bean/ServerBean$LifeCycleUploadBean;", "", "datas", "", "Lcom/ido/life/database/model/LifeCycleItemBean;", "(Ljava/util/List;)V", "getDatas", "()Ljava/util/List;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class LifeCycleUploadBean {
        private final List<LifeCycleItemBean> datas;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LifeCycleUploadBean copy$default(LifeCycleUploadBean lifeCycleUploadBean, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = lifeCycleUploadBean.datas;
            }
            return lifeCycleUploadBean.copy(list);
        }

        public final List<LifeCycleItemBean> component1() {
            return this.datas;
        }

        public final LifeCycleUploadBean copy(List<? extends LifeCycleItemBean> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            return new LifeCycleUploadBean(datas);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof LifeCycleUploadBean) && Intrinsics.areEqual(this.datas, ((LifeCycleUploadBean) other).datas);
            }
            return true;
        }

        public int hashCode() {
            List<LifeCycleItemBean> list = this.datas;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "LifeCycleUploadBean(datas=" + this.datas + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public LifeCycleUploadBean(List<? extends LifeCycleItemBean> datas) {
            Intrinsics.checkParameterIsNotNull(datas, "datas");
            this.datas = datas;
        }

        public final List<LifeCycleItemBean> getDatas() {
            return this.datas;
        }
    }

    /* JADX INFO: compiled from: ServerBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/ido/life/bean/ServerBean$MensConfigReponse;", "", "MENSCYCLE", "", "MENSLENGTH", "(II)V", "getMENSCYCLE", "()I", "getMENSLENGTH", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class MensConfigReponse {
        private final int MENSCYCLE;
        private final int MENSLENGTH;

        public static /* synthetic */ MensConfigReponse copy$default(MensConfigReponse mensConfigReponse, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = mensConfigReponse.MENSCYCLE;
            }
            if ((i3 & 2) != 0) {
                i2 = mensConfigReponse.MENSLENGTH;
            }
            return mensConfigReponse.copy(i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMENSCYCLE() {
            return this.MENSCYCLE;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMENSLENGTH() {
            return this.MENSLENGTH;
        }

        public final MensConfigReponse copy(int MENSCYCLE, int MENSLENGTH) {
            return new MensConfigReponse(MENSCYCLE, MENSLENGTH);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MensConfigReponse)) {
                return false;
            }
            MensConfigReponse mensConfigReponse = (MensConfigReponse) other;
            return this.MENSCYCLE == mensConfigReponse.MENSCYCLE && this.MENSLENGTH == mensConfigReponse.MENSLENGTH;
        }

        public int hashCode() {
            return (Integer.valueOf(this.MENSCYCLE).hashCode() * 31) + Integer.valueOf(this.MENSLENGTH).hashCode();
        }

        public String toString() {
            return "MensConfigReponse(MENSCYCLE=" + this.MENSCYCLE + ", MENSLENGTH=" + this.MENSLENGTH + ")";
        }

        public MensConfigReponse(int i, int i2) {
            this.MENSCYCLE = i;
            this.MENSLENGTH = i2;
        }

        public final int getMENSCYCLE() {
            return this.MENSCYCLE;
        }

        public final int getMENSLENGTH() {
            return this.MENSLENGTH;
        }
    }
}