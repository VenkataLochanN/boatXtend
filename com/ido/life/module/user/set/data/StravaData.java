package com.ido.life.module.user.set.data;

/* JADX INFO: loaded from: classes3.dex */
public class StravaData {
    private String access_token;
    private AthleteBean athlete;
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String refreshToken;

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String str) {
        this.clientSecret = str;
    }

    public String getGrantType() {
        return this.grantType;
    }

    public void setGrantType(String str) {
        this.grantType = str;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String str) {
        this.access_token = str;
    }

    public AthleteBean getAthlete() {
        return this.athlete;
    }

    public void setAthlete(AthleteBean athleteBean) {
        this.athlete = athleteBean;
    }

    public static class AthleteBean {
        private int badge_type_id;
        private String city;
        private String country;
        private String created_at;
        private String firstname;
        private String follower;
        private String friend;
        private int id;
        private String lastname;
        private boolean premium;
        private String profile;
        private String profile_medium;
        private int resource_state;
        private String sex;
        private String state;
        private boolean summit;
        private String update_at;
        private String username;

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public int getResource_state() {
            return this.resource_state;
        }

        public void setResource_state(int i) {
            this.resource_state = i;
        }

        public String getFirstname() {
            return this.firstname;
        }

        public void setFirstname(String str) {
            this.firstname = str;
        }

        public String getLastname() {
            return this.lastname;
        }

        public void setLastname(String str) {
            this.lastname = str;
        }

        public String getCity() {
            return this.city;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getCountry() {
            return this.country;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public String getSex() {
            return this.sex;
        }

        public void setSex(String str) {
            this.sex = str;
        }

        public boolean isPremium() {
            return this.premium;
        }

        public void setPremium(boolean z) {
            this.premium = z;
        }

        public boolean isSummit() {
            return this.summit;
        }

        public void setSummit(boolean z) {
            this.summit = z;
        }

        public String getCreated_at() {
            return this.created_at;
        }

        public void setCreated_at(String str) {
            this.created_at = str;
        }

        public String getUpdate_at() {
            return this.update_at;
        }

        public void setUpdate_at(String str) {
            this.update_at = str;
        }

        public int getBadge_type_id() {
            return this.badge_type_id;
        }

        public void setBadge_type_id(int i) {
            this.badge_type_id = i;
        }

        public String getProfile_medium() {
            return this.profile_medium;
        }

        public void setProfile_medium(String str) {
            this.profile_medium = str;
        }

        public String getProfile() {
            return this.profile;
        }

        public void setProfile(String str) {
            this.profile = str;
        }

        public String getFriend() {
            return this.friend;
        }

        public void setFriend(String str) {
            this.friend = str;
        }

        public String getFollower() {
            return this.follower;
        }

        public void setFollower(String str) {
            this.follower = str;
        }
    }
}