package net.microinvest.datatablewrapper;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.DynamicBuilder;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.keywords.constants.Colors;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;
import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.enums.Direction;
import ir.androidexception.datatable.enums.Gravity;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

@BA.ActivityObject
@BA.Version(0.01F)
@BA.DependsOn(values = {"DataTableLibrary.aar", "DesignSupport.aar"})
@BA.ShortName("DataTable")
public class DataTableWrapper extends ViewWrapper<DataTable> implements Common.DesignerCustomView {
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new DataTable(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, DataTable.class, props, designer);
        }
        DataTable dt = (DataTable) ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                dt.setBackgroundDrawable(d);
            }
        }
        return dt;
    }

    @Override
    @BA.Hide
    public void _initialize(BA ba, Object o, String EventName) {
        final DataTable dt = new DataTable(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(dt);
        innerInitialize(ba, eventName, true);
    }

    @BA.Hide
    public void AddToParent(ViewGroup Parent, @BA.Pixel int left, @BA.Pixel int top, @BA.Pixel int width, @BA.Pixel int height) {
        Parent.addView(getObject(), new BALayout.LayoutParams(left, top, width, height));
    }

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        ViewGroup vg = (ViewGroup) base.getObject().getParent();
        AddToParent(vg, base.getLeft(), base.getTop(), base.getWidth(), base.getHeight());
        base.RemoveView();
    }

    public void setHeaders(ArrayList<String> headers) {

        if(headers.size() == 0) {
            return;
        }

        ArrayList<Integer> weights = new ArrayList<>();

        for(String header : headers) {
            weights.add(1);
        }

        DataTableHeader header = new DataTableHeader(headers, weights);

        getObject().setHeader(header);
    }

    public void setRows(ArrayList<ArrayList<String>> rows) {

        if(rows.size() == 0) {
            return;
        }

        ArrayList<DataTableRow> rows1 = new ArrayList<>();

        for (ArrayList<String> row : rows) {
            DataTableRow dataTableRow = new DataTableRow(row);
            rows1.add(dataTableRow);
        }

        getObject().setRows(rows1);
    }


    public void setTestHeaders(int columnCount) {

        ArrayList<String> columns = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();

        for(int i = 0; i < columnCount; i++) {
            columns.add("Column #" + i);
            weights.add(1);
        }

        DataTableHeader header = new DataTableHeader(columns, weights);
        getObject().setHeader(header);
    }

    public void setTestRows(int rowCount, int columnCount) {
        ArrayList<DataTableRow> rows = new ArrayList<>();
        for(int i = 0; i < rowCount; i++){
            ArrayList<String> rowColumns = new ArrayList<>();
            for(int j = 0; j < columnCount; j++) {
                rowColumns.add("Row #" + i + " " + j);
            }
            DataTableRow row = new DataTableRow(rowColumns);
            rows.add(row);
        }
        getObject().setRows(rows);
    }

    public void Inflate() {
        getObject().inflate(ba.context);
    }

    public void setHeaderGravity(String headerGravity) {
        getObject().setHeaderGravity(Gravity.valueOf(headerGravity));
    }

    public void setRowGravity(String rowGravity) {
        getObject().setRowGravity(Gravity.valueOf(rowGravity));
    }

    public void setHeaderVerticalPadding(float headerVerticalPadding) {
        getObject().setHeaderVerticalPadding(headerVerticalPadding);
    }

    public void setRowVerticalPadding(float rowVerticalPadding) {
        getObject().setRowVerticalPadding(rowVerticalPadding);
    }

    public void setHeaderHorizontalPadding(float headerHorizontalPadding) {
        getObject().setHeaderHorizontalPadding(headerHorizontalPadding);
    }

    public void setRowHorizontalPadding(float rowHorizontalPadding) {
        getObject().setRowHorizontalPadding(rowHorizontalPadding);
    }

    public void setHeaderTextSize(float headerTextSize) {
        getObject().setHeaderTextSize(headerTextSize);
    }

    public void setRowTextSize(float rowTextSize) {
        getObject().setRowTextSize(rowTextSize);
    }

    public void setHeaderTextColor(int headerTextColor) {
        getObject().setHeaderTextColor(headerTextColor);
    }

    public void setRowTextColor(int rowTextColor) {
        getObject().setRowTextColor(rowTextColor);
    }

    public void setHeaderBackgroundColor(int headerBackgroundColor) {
        getObject().setHeaderBackgroundColor(headerBackgroundColor);
    }
    public void setRowBackgroundColor(int rowBackgroundColor) {
        getObject().setRowBackgroundColor(rowBackgroundColor);
    }

    public void setPersianNumber(boolean persianNumber) {
        getObject().setPersianNumber(persianNumber);
    }

    public void setCornerRadius(float cornerRadius) {
        getObject().setCornerRadius(cornerRadius);
    }

    public void setDirection(String direction) {
        getObject().setDirection(Direction.valueOf(direction));
    }

    public void setShadow(float shadow) {
        getObject().setShadow(shadow);
    }

}
