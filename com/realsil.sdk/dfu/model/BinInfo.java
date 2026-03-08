package com.realsil.sdk.dfu.model;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.image.BaseBinInputStream;
import com.realsil.sdk.dfu.image.SubFileInfo;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class BinInfo {
    public String fileName;
    public long fileSize;
    public int icType;
    public boolean isPackFile;
    public boolean lowVersionExist;
    public List<BaseBinInputStream> mBinInputStreams;
    public String path;
    public int status;
    public int statusCaption;
    public List<SubFileInfo> subFileInfos;
    public List<BaseBinInputStream> supportBinInputStreams;
    public List<SubFileInfo> supportSubFileInfos;
    public int version;

    public int getAppImageVersion(int i) {
        if (this.isPackFile) {
            List<SubFileInfo> list = this.subFileInfos;
            if (list != null && list.size() > 0) {
                for (SubFileInfo subFileInfo : this.subFileInfos) {
                    if (subFileInfo.icType <= 3) {
                        int i2 = subFileInfo.bitNumber;
                        if (i == 0) {
                            if (i2 == 1) {
                                return subFileInfo.version;
                            }
                        } else if (i2 == 2) {
                            return subFileInfo.version;
                        }
                    } else {
                        int i3 = this.icType;
                        if (i3 == 5 || i3 == 9) {
                            int i4 = subFileInfo.bitNumber;
                            if (i == 0) {
                                if (i4 == 5) {
                                    return subFileInfo.version;
                                }
                            } else if (i4 == 21) {
                                return subFileInfo.version;
                            }
                        } else if (i3 == 4 || i3 == 6 || i3 == 7 || i3 == 8) {
                            int i5 = subFileInfo.bitNumber;
                            if (i == 0) {
                                if (i5 == 5) {
                                    return subFileInfo.version;
                                }
                            } else if (i5 == 21) {
                                return subFileInfo.version;
                            }
                        }
                    }
                }
            }
        } else {
            BaseBinInputStream baseBinInputStream = null;
            List<BaseBinInputStream> list2 = this.mBinInputStreams;
            if (list2 != null && list2.size() > 0) {
                baseBinInputStream = this.mBinInputStreams.get(0);
            }
            if (baseBinInputStream != null) {
                int i6 = this.icType;
                if (i6 <= 3) {
                    ZLogger.d("Bee1 single file not support binid");
                } else if (i6 == 5 || i6 == 9) {
                    if (baseBinInputStream.getBinId() == 768) {
                        return baseBinInputStream.getImageVersion();
                    }
                } else if ((i6 == 4 || i6 == 6 || i6 == 7 || i6 == 8) && baseBinInputStream.getBinId() == 768) {
                    return baseBinInputStream.getImageVersion();
                }
            }
        }
        return 0;
    }

    public int getPatchImageVersion(int i) {
        if (this.isPackFile) {
            List<SubFileInfo> list = this.subFileInfos;
            if (list != null && list.size() > 0) {
                for (SubFileInfo subFileInfo : this.subFileInfos) {
                    int i2 = subFileInfo.icType;
                    if (i2 <= 3) {
                        int i3 = subFileInfo.bitNumber;
                        if (i == 0) {
                            if (i3 == 0) {
                                return subFileInfo.version;
                            }
                        } else if (i3 == 16) {
                            return subFileInfo.version;
                        }
                    } else if (i2 == 5 || i2 == 9) {
                        int i4 = subFileInfo.bitNumber;
                        if (i == 0) {
                            if (i4 == 4) {
                                return subFileInfo.version;
                            }
                        } else if (i4 == 20) {
                            return subFileInfo.version;
                        }
                    } else if (i2 == 4 || i2 == 6 || i2 == 7 || i2 == 8) {
                        int i5 = subFileInfo.bitNumber;
                        if (i == 0) {
                            if (i5 == 4) {
                                return subFileInfo.version;
                            }
                        } else if (i5 == 20) {
                            return subFileInfo.version;
                        }
                    }
                }
            }
        } else {
            BaseBinInputStream baseBinInputStream = null;
            List<BaseBinInputStream> list2 = this.mBinInputStreams;
            if (list2 != null && list2.size() > 0) {
                baseBinInputStream = this.mBinInputStreams.get(0);
            }
            if (baseBinInputStream != null) {
                int i6 = this.icType;
                if (i6 <= 3) {
                    ZLogger.d("Bee1 single file not support binid");
                } else if (i6 == 5 || i6 == 9) {
                    if (baseBinInputStream.getBinId() == 512) {
                        return baseBinInputStream.getImageVersion();
                    }
                } else if ((i6 == 4 || i6 == 6 || i6 == 7 || i6 == 8) && baseBinInputStream.getBinId() == 512) {
                    return baseBinInputStream.getImageVersion();
                }
            }
        }
        return 0;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("BinInfo:\n");
        sb.append(String.format("isPackFile=%b\n", Boolean.valueOf(this.isPackFile)));
        sb.append(String.format("path=%s\n", this.path));
        sb.append(String.format("fileName=%s\n", this.fileName));
        sb.append(String.format(Locale.US, "fileSize=%d\n", Long.valueOf(this.fileSize)));
        sb.append(String.format("icType=0x%02x\n", Integer.valueOf(this.icType)));
        if (this.isPackFile) {
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            List<SubFileInfo> list = this.subFileInfos;
            objArr[0] = Integer.valueOf(list != null ? list.size() : 0);
            sb.append(String.format(locale, "subFileInfos.size=%d\n", objArr));
            Locale locale2 = Locale.US;
            Object[] objArr2 = new Object[1];
            List<SubFileInfo> list2 = this.supportSubFileInfos;
            objArr2[0] = Integer.valueOf(list2 != null ? list2.size() : 0);
            str = String.format(locale2, "supportSubFileInfos.size=%d\n", objArr2);
        } else {
            Locale locale3 = Locale.US;
            Object[] objArr3 = new Object[1];
            List<BaseBinInputStream> list3 = this.mBinInputStreams;
            objArr3[0] = Integer.valueOf(list3 != null ? list3.size() : 0);
            sb.append(String.format(locale3, "mBinInputStreams.size=%d\n", objArr3));
            Locale locale4 = Locale.US;
            Object[] objArr4 = new Object[1];
            List<BaseBinInputStream> list4 = this.supportBinInputStreams;
            objArr4[0] = Integer.valueOf(list4 != null ? list4.size() : 0);
            sb.append(String.format(locale4, "supportBinInputStreams.size=%d\n", objArr4));
            str = String.format(Locale.US, "version=%d\n", Integer.valueOf(this.version));
        }
        sb.append(str);
        return sb.toString();
    }
}