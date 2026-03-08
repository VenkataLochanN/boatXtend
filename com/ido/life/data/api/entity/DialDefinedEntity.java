package com.ido.life.data.api.entity;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedEntity {
    private Result result;

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        private Bkground bkground;
        private List<String> colorenableditem;
        private List<Colors> colors;
        private DialDate dialDate;
        private List<DialStyle> dialStyle;
        private Dialpreview dialpreview;
        private HourPoint hourPoint;
        private String isColorEnabled;
        private MinutePoint minutePoint;
        private SecondPoint secondPoint;
        private SelectItem selectItem;

        public String getIsColorEnabled() {
            return this.isColorEnabled;
        }

        public void setIsColorEnabled(String str) {
            this.isColorEnabled = str;
        }

        public HourPoint getHourPoint() {
            return this.hourPoint;
        }

        public void setHourPoint(HourPoint hourPoint) {
            this.hourPoint = hourPoint;
        }

        public SecondPoint getSecondPoint() {
            return this.secondPoint;
        }

        public void setSecondPoint(SecondPoint secondPoint) {
            this.secondPoint = secondPoint;
        }

        public MinutePoint getMinutePoint() {
            return this.minutePoint;
        }

        public void setMinutePoint(MinutePoint minutePoint) {
            this.minutePoint = minutePoint;
        }

        public Bkground getBkground() {
            return this.bkground;
        }

        public void setBkground(Bkground bkground) {
            this.bkground = bkground;
        }

        public Dialpreview getDialpreview() {
            return this.dialpreview;
        }

        public void setDialpreview(Dialpreview dialpreview) {
            this.dialpreview = dialpreview;
        }

        public DialDate getDialDate() {
            return this.dialDate;
        }

        public void setDialDate(DialDate dialDate) {
            this.dialDate = dialDate;
        }

        public SelectItem getSelectItem() {
            return this.selectItem;
        }

        public void setSelectItem(SelectItem selectItem) {
            this.selectItem = selectItem;
        }

        public List<DialStyle> getDialStyle() {
            return this.dialStyle;
        }

        public void setDialStyle(List<DialStyle> list) {
            this.dialStyle = list;
        }

        public List<Colors> getColors() {
            return this.colors;
        }

        public void setColors(List<Colors> list) {
            this.colors = list;
        }

        public List<String> getColorenableditem() {
            return this.colorenableditem;
        }

        public void setColorenableditem(List<String> list) {
            this.colorenableditem = list;
        }

        public static class HourPoint {
            private String img;

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }
        }

        public static class SecondPoint {
            private String img;

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }
        }

        public static class MinutePoint {
            private String img;

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }
        }

        public static class Bkground {
            private String img;

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }
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

        public static class DialDate {
            private String img;

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }
        }

        public static class SelectItem {
            private int colors;
            private int dial_num;
            private int secondPlate;

            public int getColors() {
                return this.colors;
            }

            public void setColors(int i) {
                this.colors = i;
            }

            public int getDial_num() {
                return this.dial_num;
            }

            public void setDial_num(int i) {
                this.dial_num = i;
            }

            public int getSecondPlate() {
                return this.secondPlate;
            }

            public void setSecondPlate(int i) {
                this.secondPlate = i;
            }
        }

        public static class DialStyle {
            private String description;
            private String img;

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }

            public String getDescription() {
                return this.description;
            }

            public void setDescription(String str) {
                this.description = str;
            }
        }

        public static class Colors {
            private String color;
            private Extend extend;

            public String getColor() {
                return this.color;
            }

            public void setColor(String str) {
                this.color = str;
            }

            public Extend getExtend() {
                return this.extend;
            }

            public void setExtend(Extend extend) {
                this.extend = extend;
            }

            public static class Extend {
                private String bkground;
                private String dialDate;
                private String secondPlate;

                public String getBkground() {
                    return this.bkground;
                }

                public void setBkground(String str) {
                    this.bkground = str;
                }

                public String getSecondPlate() {
                    return this.secondPlate;
                }

                public void setSecondPlate(String str) {
                    this.secondPlate = str;
                }

                public String getDialDate() {
                    return this.dialDate;
                }

                public void setDialDate(String str) {
                    this.dialDate = str;
                }
            }
        }
    }
}