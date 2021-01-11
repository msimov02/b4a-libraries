package com.microinvest.mpandroidchartlibrarywrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.components.Legend;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.DynamicBuilder;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;

@BA.ActivityObject
@BA.DependsOn(values={"MPAndroidChartLibrary.aar", "MPAndroidChartLibrary.jar"})
@BA.ShortName("MPPieChart")
public class MPPieChartWrapper extends ViewWrapper<PieChart> implements Common.DesignerCustomView {
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new PieChart(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, PieChart.class, props, designer);
        }
        PieChart pc = (PieChart)ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                pc.setBackgroundDrawable(d);
            }
        }
        return pc;
    }

    @BA.Hide
    @Override
    public void _initialize(BA ba, Object o, String EventName) {
        final PieChart pc = new PieChart(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(pc);
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

    public void SetXYValues(final List<String> XValues, final List<String> YValues, String title, int textColor, float textSize) {
        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < YValues.size(); i++) {
            String x = XValues.get(i);
            float y = Float.parseFloat(YValues.get(i));
            values.add(new PieEntry(y, x));
        }

        PieDataSet set = new PieDataSet(values, title);
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        set.setValueTextSize(textSize / 2);
        setTextColor(textColor);
        setTextSize(textSize);

        PieData data = new PieData(set);
        getObject().setData(data);
    }
    public void setLegendEnabled(boolean legendEnabled) {
        getObject().getLegend().setEnabled(legendEnabled);
    }

    public void setDescriptionEnabled(boolean descriptionEnabled) {
        getObject().getDescription().setEnabled(descriptionEnabled);
    }

    public void setLegendHorizontalAlignment(String legendHorizontalAlignment) {
        getObject().getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.valueOf(legendHorizontalAlignment));
    }
    public void setLegendVerticalAlignment(String legendVerticalAlignment) {
        getObject().getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.valueOf(legendVerticalAlignment));
    }

    public void setLegendForm(String legendForm) {
        getObject().getLegend().setForm(Legend.LegendForm.valueOf(legendForm));
    }

    public void setLegendDirection(String legendDirection) {
        getObject().getLegend().setDirection(Legend.LegendDirection.valueOf(legendDirection));
    }

    public void setLegendOrientation(String legendOrientation) {
        getObject().getLegend().setOrientation(Legend.LegendOrientation.valueOf(legendOrientation));
    }

    public void setBackgroundColor(int backgroundColor) {
        getObject().setBackgroundColor(backgroundColor);
    }

    private void setTextColor(int textColor) {
        getObject().setCenterTextColor(textColor);
        getObject().setEntryLabelColor(textColor);
        getObject().getDescription().setTextColor(textColor);
        getObject().getLegend().setTextColor(textColor);
    }

    private void setTextSize(float textSize) {
        getObject().setCenterTextSize(textSize);
    }

    public void setHoleRadius(float holeRadius) {
        getObject().setHoleRadius(holeRadius);
    }
}
