package com.vistalogsparsertool;

public class BeanClass {

    String BodId;

    public String getBodId() {
        return BodId;
    }

    public void setBodId(String bodId) {
        BodId = bodId;
    }

    String node;
    String date;
    String hr;
    String minute;
    String sec;
    String millisec;
    String userId;
    String ipAddr;
    String brand;
    String market;
    String resTime;
    String reqURL;
    String method;
    String interceId;
    String resTime1;
    String resTime2;

    public String getResTime1() {
        return resTime1;
    }

    public void setResTime1(String resTime1) {
        this.resTime1 = resTime1;
    }

    public String getResTime2() {
        return resTime2;
    }

    public void setResTime2(String resTime2) {
        this.resTime2 = resTime2;
    }

    String resTime3;
    String resTime4;
    String resTime5;

    public String getResTime3() {
        return resTime3;
    }

    public void setResTime3(String resTime3) {
        this.resTime3 = resTime3;
    }

    public String getResTime4() {
        return resTime4;
    }

    public void setResTime4(String resTime4) {
        this.resTime4 = resTime4;
    }

    public String getResTime5() {
        return resTime5;
    }

    public void setResTime5(String resTime5) {
        this.resTime5 = resTime5;
    }

    public BeanClass() {
    }
    public BeanClass(String node, String date, String hr, String minute, String sec, String millisec, String userId, String ipAddr, String brand, String market,
        String resTime, String reqURL, String method, String interceId, String resTime3, String resTime4, String resTime5) {
        super();
        this.node = node;
        this.date = date;
        this.hr = hr;
        this.minute = minute;
        this.sec = sec;
        this.millisec = millisec;
        this.userId = userId;
        this.ipAddr = ipAddr;
        this.brand = brand;
        this.market = market;
        this.resTime = resTime;
        this.reqURL = reqURL;
        this.method = method;
        this.interceId = interceId;
        this.resTime3 = resTime3;
        this.resTime4 = resTime4;
        this.resTime5 = resTime5;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getMillisec() {
        return millisec;
    }

    public void setMillisec(String millisec) {
        this.millisec = millisec;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getResTime() {
        return resTime;
    }

    public void setResTime(String resTime) {
        this.resTime = resTime;
    }

    public String getReqURL() {
        return reqURL;
    }

    public void setReqURL(String reqURL) {
        this.reqURL = reqURL;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getInterceId() {
        return interceId;
    }

    public void setInterceId(String interceId) {
        this.interceId = interceId;
    }
}
