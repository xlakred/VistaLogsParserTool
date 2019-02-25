package com.vistalogsparsertool;

import java.io.BufferedWriter;

public class BeanDTO {

    String vista_interface_metrics;
    String imSearchText;
    String imfiles;
    String imfilesSearchType;
    String imExportExcel;

    String vista_servlet_metrics;
    String smSearchText;
    String smfiles;
    String smfilesSearchType;
    String smExportExcel;

    BufferedWriter servletMetricBW;

    public String getVista_interface_metrics() {
        return vista_interface_metrics;
    }

    public void setVista_interface_metrics(String vista_interface_metrics) {
        this.vista_interface_metrics = vista_interface_metrics;
    }

    public String getImSearchText() {
        return imSearchText;
    }

    public void setImSearchText(String imSearchText) {
        this.imSearchText = imSearchText;
    }

    public String getImfiles() {
        return imfiles;
    }

    public void setImfiles(String imfiles) {
        this.imfiles = imfiles;
    }

    public String getImfilesSearchType() {
        return imfilesSearchType;
    }

    public void setImfilesSearchType(String imfilesSearchType) {
        this.imfilesSearchType = imfilesSearchType;
    }

    public String getImExportExcel() {
        return imExportExcel;
    }

    public void setImExportExcel(String imExportExcel) {
        this.imExportExcel = imExportExcel;
    }

    public String getVista_servlet_metrics() {
        return vista_servlet_metrics;
    }

    public void setVista_servlet_metrics(String vista_servlet_metrics) {
        this.vista_servlet_metrics = vista_servlet_metrics;
    }

    public String getSmSearchText() {
        return smSearchText;
    }

    public void setSmSearchText(String smSearchText) {
        this.smSearchText = smSearchText;
    }

    public String getSmfiles() {
        return smfiles;
    }

    public void setSmfiles(String smfiles) {
        this.smfiles = smfiles;
    }

    public String getSmfilesSearchType() {
        return smfilesSearchType;
    }

    public void setSmfilesSearchType(String smfilesSearchType) {
        this.smfilesSearchType = smfilesSearchType;
    }

    public String getSmExportExcel() {
        return smExportExcel;
    }

    public void setSmExportExcel(String smExportExcel) {
        this.smExportExcel = smExportExcel;
    }

    public BufferedWriter getServletMetricBW() {
        return servletMetricBW;
    }

    public void setServletMetricBW(BufferedWriter servletMetricBW) {
        this.servletMetricBW = servletMetricBW;
    }

}
