package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class MusicMsgBean {
    public String album;
    public String artist;
    public long duration;
    public int playState;
    public long playTime;
    public String title;

    public MusicMsgBean(String str, String str2, String str3, int i) {
        this.artist = str;
        this.album = str2;
        this.title = str3;
        this.duration = i;
    }

    public CharSequence getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public CharSequence getAlbum() {
        return this.album;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public int getPlayState() {
        return this.playState;
    }

    public void setPlayState(int i) {
        this.playState = i;
    }

    public long getPlayTime() {
        return this.playTime;
    }

    public void setPlayTime(long j) {
        this.playTime = j;
    }

    public String toString() {
        return "MusicMsgBean{artist='" + this.artist + "', album='" + this.album + "', title='" + this.title + "', duration=" + this.duration + ", playState=" + this.playState + ", playTime=" + this.playTime + '}';
    }
}