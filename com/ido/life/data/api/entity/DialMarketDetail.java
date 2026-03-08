package com.ido.life.data.api.entity;

import com.ido.common.net.BaseEntity;
import com.ido.life.data.api.entity.DialMarket;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarketDetail extends BaseEntity implements Serializable {
    private DialInfoDetail result;

    public DialInfoDetail getResult() {
        return this.result;
    }

    public void setResult(DialInfoDetail dialInfoDetail) {
        this.result = dialInfoDetail;
    }

    public static class DialInfoDetail extends BaseEntity implements Serializable {
        private int currentPage;
        private List<DialMarket.DialType.Dial> items;
        private int numRows;
        private int pageSize;
        private int totalPages;

        public int getCurrentPage() {
            return this.currentPage;
        }

        public void setCurrentPage(int i) {
            this.currentPage = i;
        }

        public int getNumRows() {
            return this.numRows;
        }

        public void setNumRows(int i) {
            this.numRows = i;
        }

        public int getTotalPages() {
            return this.totalPages;
        }

        public void setTotalPages(int i) {
            this.totalPages = i;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(int i) {
            this.pageSize = i;
        }

        public List<DialMarket.DialType.Dial> getItems() {
            return this.items;
        }

        public void setItems(List<DialMarket.DialType.Dial> list) {
            this.items = list;
        }

        public String toString() {
            return "DialInfoDetail{currentPage=" + this.currentPage + ", numRows=" + this.numRows + ", totalPages=" + this.totalPages + ", pageSize=" + this.pageSize + ", items=" + this.items + '}';
        }
    }
}