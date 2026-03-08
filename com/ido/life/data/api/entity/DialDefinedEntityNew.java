package com.ido.life.data.api.entity;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedEntityNew {
    private List<Colors> colors;
    private Integer dailType;
    private List<DialStyle> dialStyle;
    private Dialpreview dialpreview;
    private OutFile outFile;
    private List<String> previewmodul;
    private SelectItem selectItem;
    private List<String> stylemodul;
    private Integer version;

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer num) {
        this.version = num;
    }

    public Integer getDailType() {
        return this.dailType;
    }

    public void setDailType(Integer num) {
        this.dailType = num;
    }

    public Dialpreview getDialpreview() {
        return this.dialpreview;
    }

    public void setDialpreview(Dialpreview dialpreview) {
        this.dialpreview = dialpreview;
    }

    public OutFile getOutFile() {
        return this.outFile;
    }

    public void setOutFile(OutFile outFile) {
        this.outFile = outFile;
    }

    public SelectItem getSelectItem() {
        return this.selectItem;
    }

    public void setSelectItem(SelectItem selectItem) {
        this.selectItem = selectItem;
    }

    public List<String> getStylemodul() {
        return this.stylemodul;
    }

    public void setStylemodul(List<String> list) {
        this.stylemodul = list;
    }

    public List<DialStyle> getDialStyle() {
        return this.dialStyle;
    }

    public void setDialStyle(List<DialStyle> list) {
        this.dialStyle = list;
    }

    public List<String> getPreviewmodul() {
        return this.previewmodul;
    }

    public void setPreviewmodul(List<String> list) {
        this.previewmodul = list;
    }

    public List<Colors> getColors() {
        return this.colors;
    }

    public void setColors(List<Colors> list) {
        this.colors = list;
    }

    public static class Dialpreview {
        private String img;

        public String getImg() {
            return this.img;
        }

        public void setImg(String str) {
            this.img = str;
        }
    }

    public static class OutFile {
        private String deviceColor;
        private List<String> imagecolor;
        private Imagegroup imagegroup;
        private Imagegroupsize imagegroupsize;
        private Boolean isCompress;
        private List<List<String>> movefileForm;
        private List<String> movefileTo;

        public Imagegroupsize getImagegroupsize() {
            return this.imagegroupsize;
        }

        public void setImagegroupsize(Imagegroupsize imagegroupsize) {
            this.imagegroupsize = imagegroupsize;
        }

        public Boolean isIsCompress() {
            return this.isCompress;
        }

        public void setIsCompress(Boolean bool) {
            this.isCompress = bool;
        }

        public String getDeviceColor() {
            return this.deviceColor;
        }

        public void setDeviceColor(String str) {
            this.deviceColor = str;
        }

        public Imagegroup getImagegroup() {
            return this.imagegroup;
        }

        public void setImagegroup(Imagegroup imagegroup) {
            this.imagegroup = imagegroup;
        }

        public List<String> getImagecolor() {
            return this.imagecolor;
        }

        public void setImagecolor(List<String> list) {
            this.imagecolor = list;
        }

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

        public static class Imagegroupsize {
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

        public static class Imagegroup {
            private String name;
            private List<String> source;

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public List<String> getSource() {
                return this.source;
            }

            public void setSource(List<String> list) {
                this.source = list;
            }
        }
    }

    public static class SelectItem {
        private Integer colors;
        private Integer dialStyle;

        public Integer getColors() {
            return this.colors;
        }

        public void setColors(Integer num) {
            this.colors = num;
        }

        public Integer getDialStyle() {
            return this.dialStyle;
        }

        public void setDialStyle(Integer num) {
            this.dialStyle = num;
        }
    }

    public static class DialStyle {
        private String description;
        private List<String> styleimg;

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public List<String> getStyleimg() {
            return this.styleimg;
        }

        public void setStyleimg(List<String> list) {
            this.styleimg = list;
        }
    }

    public static class Colors {
        private String color;
        private List<String> previewmodul;
        private List<String> style;
        private List<String> stylemodul;

        public String getColor() {
            return this.color;
        }

        public void setColor(String str) {
            this.color = str;
        }

        public List<String> getStyle() {
            return this.style;
        }

        public void setStyle(List<String> list) {
            this.style = list;
        }

        public List<String> getStylemodul() {
            return this.stylemodul;
        }

        public void setStylemodul(List<String> list) {
            this.stylemodul = list;
        }

        public List<String> getPreviewmodul() {
            return this.previewmodul;
        }

        public void setPreviewmodul(List<String> list) {
            this.previewmodul = list;
        }
    }
}