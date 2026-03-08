package com.ido.life.data.api.entity;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedFunctionEntity implements Serializable {
    private AppInfo app;
    private List<BackgroundInfo> backgrounds;
    private int bg_select_position;
    private ClockInfo clock;
    private FunctionInfo funcInfo;
    private Imagegroupsize imagegroupsize;
    private OutFile outFile;
    private List<PaletteColor> palettes;
    private SelectInfo select;
    private List<StyleInfo> styles;
    private int zipName;

    public AppInfo getApp() {
        return this.app;
    }

    public void setApp(AppInfo appInfo) {
        this.app = appInfo;
    }

    public String toString() {
        return "DialDefinedFunctionEntity{backgrounds=" + this.backgrounds + ", select=" + this.select + ", zipName=" + this.zipName + ", clock=" + this.clock + ", bg_select_position=" + this.bg_select_position + ", outFile=" + this.outFile + ", imagegroupsize=" + this.imagegroupsize + ", palettes=" + this.palettes + ", styles=" + this.styles + ", funcInfo=" + this.funcInfo + '}';
    }

    public int getZipName() {
        return this.zipName;
    }

    public void setZipName(int i) {
        this.zipName = i;
    }

    public int getBg_select_position() {
        return this.bg_select_position;
    }

    public void setBg_select_position(int i) {
        this.bg_select_position = i;
    }

    public Imagegroupsize getImagegroupsize() {
        return this.imagegroupsize;
    }

    public void setImagegroupsize(Imagegroupsize imagegroupsize) {
        this.imagegroupsize = imagegroupsize;
    }

    public OutFile getOutFile() {
        return this.outFile;
    }

    public void setOutFile(OutFile outFile) {
        this.outFile = outFile;
    }

    public List<StyleInfo> getStyles() {
        return this.styles;
    }

    public void setStyles(List<StyleInfo> list) {
        this.styles = list;
    }

    public List<PaletteColor> getPalettes() {
        return this.palettes;
    }

    public void setPalettes(List<PaletteColor> list) {
        this.palettes = list;
    }

    public SelectInfo getSelect() {
        return this.select;
    }

    public void setSelect(SelectInfo selectInfo) {
        this.select = selectInfo;
    }

    public List<BackgroundInfo> getBackgrounds() {
        return this.backgrounds;
    }

    public void setBackgrounds(List<BackgroundInfo> list) {
        this.backgrounds = list;
    }

    public ClockInfo getClock() {
        return this.clock;
    }

    public void setClock(ClockInfo clockInfo) {
        this.clock = clockInfo;
    }

    public FunctionInfo getFuncInfo() {
        return this.funcInfo;
    }

    public void setFuncInfo(FunctionInfo functionInfo) {
        this.funcInfo = functionInfo;
    }

    public static class BackgroundInfo implements Serializable {
        private String backgroundColor;
        private int canChangeColor;
        private String image;

        public int getCanChangeColor() {
            return this.canChangeColor;
        }

        public void setCanChangeColor(int i) {
            this.canChangeColor = i;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public String toString() {
            return "BackgroundInfo{canChangeColor=" + this.canChangeColor + ", image='" + this.image + "'}";
        }

        public String getBackgroundColor() {
            return this.backgroundColor;
        }

        public void setBackgroundColor(String str) {
            this.backgroundColor = str;
        }
    }

    public static class ClockInfo implements Serializable {
        private int canChangeColor;
        private List<Integer> cityLocation;
        private String cityName;
        private String getAbbreviation;
        private String image;
        private List<Integer> location;
        private int offSetTime;
        private int type;

        public int getCanChangeColor() {
            return this.canChangeColor;
        }

        public void setCanChangeColor(int i) {
            this.canChangeColor = i;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public List<Integer> getLocation() {
            return this.location;
        }

        public void setLocation(List<Integer> list) {
            this.location = list;
        }

        public List<Integer> getCityLocation() {
            return this.cityLocation;
        }

        public void setCityLocation(List<Integer> list) {
            this.cityLocation = list;
        }

        public String getCityName() {
            return this.cityName;
        }

        public void setCityName(String str) {
            this.cityName = str;
        }

        public int getOffSetTime() {
            return this.offSetTime;
        }

        public void setOffSetTime(int i) {
            this.offSetTime = i;
        }

        public String getGetAbbreviation() {
            return this.getAbbreviation;
        }

        public void setGetAbbreviation(String str) {
            this.getAbbreviation = str;
        }
    }

    public static class SelectInfo implements Serializable {
        private int colorselect;
        private List<String> function;
        private String selectBG;
        private String style;
        private int style_select_position;

        public int getStyle_select_position() {
            return this.style_select_position;
        }

        public void setStyle_select_position(int i) {
            this.style_select_position = i;
        }

        public String getStyle() {
            return this.style;
        }

        public void setStyle(String str) {
            this.style = str;
        }

        public List<String> getFunction() {
            return this.function;
        }

        public void setFunction(List<String> list) {
            this.function = list;
        }

        public String getSelectBG() {
            return this.selectBG;
        }

        public void setSelectBG(String str) {
            this.selectBG = str;
        }

        public int getColorselect() {
            return this.colorselect;
        }

        public void setColorselect(int i) {
            this.colorselect = i;
        }
    }

    public static class StyleInfo implements Serializable {
        private int canChangeColor;
        private List<String> images;
        private String name;

        public int getCanChangeColor() {
            return this.canChangeColor;
        }

        public void setCanChangeColor(int i) {
            this.canChangeColor = i;
        }

        public String getName() {
            return this.name;
        }

        public String toString() {
            return "StyleInfo{name='" + this.name + "', images=" + this.images + ", canChangeColor=" + this.canChangeColor + '}';
        }

        public List<String> getImages() {
            return this.images;
        }

        public void setImages(List<String> list) {
            this.images = list;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public static class FunctionInfo implements Serializable {
        private int canChangeColor;
        private List<DefaultFuns> defaultFuncs;
        private String format;
        private boolean issupportClose;
        private List<FunctionList> list;
        private String name;
        private String version;

        public String getVersion() {
            return this.version;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getFormat() {
            return this.format;
        }

        public void setFormat(String str) {
            this.format = str;
        }

        public int getCanChangeColor() {
            return this.canChangeColor;
        }

        public void setCanChangeColor(int i) {
            this.canChangeColor = i;
        }

        public List<FunctionList> getList() {
            return this.list;
        }

        public void setList(List<FunctionList> list) {
            this.list = list;
        }

        public List<DefaultFuns> getDefaultFuncs() {
            return this.defaultFuncs;
        }

        public void setDefaultFuncs(List<DefaultFuns> list) {
            this.defaultFuncs = list;
        }

        public boolean isIssupportClose() {
            return this.issupportClose;
        }

        public void setIssupportClose(boolean z) {
            this.issupportClose = z;
        }
    }

    public static class DefaultFuns implements Serializable {
        private String contentStr;
        private int cornerRadius;
        private String funcIcon;
        private String funcType;
        private int isClose;
        private List<Integer> location;
        private int modifyContent;

        public String getFuncType() {
            return this.funcType;
        }

        public void setFuncType(String str) {
            this.funcType = str;
        }

        public String getFuncIcon() {
            return this.funcIcon;
        }

        public void setFuncIcon(String str) {
            this.funcIcon = str;
        }

        public int getCornerRadius() {
            return this.cornerRadius;
        }

        public void setCornerRadius(int i) {
            this.cornerRadius = i;
        }

        public int getModifyContent() {
            return this.modifyContent;
        }

        public void setModifyContent(int i) {
            this.modifyContent = i;
        }

        public int getIsClose() {
            return this.isClose;
        }

        public void setIsClose(int i) {
            this.isClose = i;
        }

        public String getContentStr() {
            return this.contentStr;
        }

        public void setContentStr(String str) {
            this.contentStr = str;
        }

        public List<Integer> getLocation() {
            return this.location;
        }

        public void setLocation(List<Integer> list) {
            this.location = list;
        }
    }

    public static class FunctionList implements Serializable {
        private List<Function> items;
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public List<Function> getItems() {
            return this.items;
        }

        public void setItems(List<Function> list) {
            this.items = list;
        }
    }

    public static class Function implements Serializable {
        private String contentStr;
        private String icon;
        private int id;
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getContentStr() {
            return this.contentStr;
        }

        public void setContentStr(String str) {
            this.contentStr = str;
        }
    }

    public static class Imagegroupsize implements Serializable {
        private Integer height;
        private Integer width;

        public Integer getWidth() {
            return this.width;
        }

        public void setWidth(Integer num) {
            this.width = num;
        }

        public Integer getHeight() {
            return this.height;
        }

        public void setHeight(Integer num) {
            this.height = num;
        }
    }

    public static class PaletteColor implements Serializable {
        private String colors;
        private int index;
        private String num;

        public String getNum() {
            return this.num;
        }

        public void setNum(String str) {
            this.num = str;
        }

        public String getColors() {
            return this.colors;
        }

        public void setColors(String str) {
            this.colors = str;
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }
    }

    public static class OutFile implements Serializable {
        private List<List<String>> movefileForm;
        private List<String> movefileTo;

        public List<String> getMovefileTo() {
            return this.movefileTo;
        }

        public void setMovefileTo(List<String> list) {
            this.movefileTo = list;
        }

        public List<List<String>> getMovefileForm() {
            return this.movefileForm;
        }

        public void setMovefileForm(List<List<String>> list) {
            this.movefileForm = list;
        }
    }

    public static class AppInfo implements Serializable {
        private String format;
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getFormat() {
            return this.format;
        }

        public void setFormat(String str) {
            this.format = str;
        }
    }
}