package com.microinvest.mpandroidchartlibrarywrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


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
@BA.DependsOn(values={"MPAndroidChartLibrary.aar"})
@BA.ShortName("MPLineChart")
public class MPLineChartWrapper extends ViewWrapper<LineChart> implements Common.DesignerCustomView {
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new LineChart(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, LineChart.class, props, designer);
        }
        LineChart lc = (LineChart) ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                lc.setBackgroundDrawable(d);
            }
        }
        return lc;
    }

    @BA.Hide
    @Override
    public void _initialize(BA ba, Object o, String EventName) {
        final LineChart lc = new LineChart(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(lc);
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

    public void SetXValues(final List<String> XValues) {
        getObject().getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if(XValues.get((int) v).length() > 10) {
                    return XValues.get((int) v).substring(0, 7) + "...";
                }
                return XValues.get((int) v);
            }
        });
        getObject().getXAxis().setGranularityEnabled(true);
        getObject().getXAxis().setLabelCount(XValues.size());
    }

    public void SetYValues(final List<String> YValues, String title, int textColor, int lineColor, int textSize) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < YValues.size(); i++) {
            float x = i;
            float y = Float.parseFloat(YValues.get(i));
            values.add(new Entry(x, y));
        }

        LineDataSet set = new LineDataSet(values, title);
        set.setCircleColor(lineColor);
        set.setColor(lineColor);
        set.setValueTextColor(textColor);
        set.setValueTextSize(textSize / 2);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        setTextColor(textColor);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        LineData data = new LineData(dataSets);
        getObject().setData(data);
    }

    public void setPinchZoom(boolean pinchZoom) {
        getObject().setPinchZoom(pinchZoom);
    }

    public void setDoubleTapToZoom(boolean doubleTapToZoom) {
        getObject().setDoubleTapToZoomEnabled(false);
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
    public void setLegendDirection(String legendDirection) {
        getObject().getLegend().setDirection(Legend.LegendDirection.valueOf(legendDirection));
    }

    public void setLegendOrientation(String legendOrientation) {
        getObject().getLegend().setOrientation(Legend.LegendOrientation.valueOf(legendOrientation));
    }

    public void setXAxisPosition(String position) {
        getObject().getXAxis().setPosition(XAxis.XAxisPosition.valueOf(position));
    }
    public void setXAxisLabelRotationAngle(float angle) {
        getObject().getXAxis().setLabelRotationAngle(angle);
    }
    public void setXAxisDrawAxisLine(boolean drawAxisLine) {
        getObject().getXAxis().setDrawAxisLine(drawAxisLine);
    }
    public void setXAxisDrawAxisGrid(boolean drawAxisGrid) {
        getObject().getXAxis().setDrawGridLines(drawAxisGrid);
    }
    public void setLegendForm(String legendForm) {
        getObject().getLegend().setForm(Legend.LegendForm.valueOf(legendForm));
    }

    public void setYAxisSpaceBottom(float spaceBottom) {
        getObject().getAxisLeft().setSpaceBottom(spaceBottom);
        getObject().getAxisRight().setSpaceBottom(spaceBottom);
    }

    public void setYAxisDrawAxisLine(boolean drawAxisLine) {
        getObject().getAxisLeft().setDrawAxisLine(drawAxisLine);
        getObject().getAxisRight().setDrawAxisLine(drawAxisLine);
    }
    public void setYAxisDrawAxisGrid(boolean drawAxisGrid) {
        getObject().getAxisLeft().setDrawGridLines(drawAxisGrid);
        getObject().getAxisRight().setDrawGridLines(drawAxisGrid);
    }
    public void setBackgroundColor(int backgroundColor) {
        getObject().setBackgroundColor(backgroundColor);
    }

    private void setTextColor(int textColor) {
        getObject().getAxisLeft().setTextColor(textColor);
        getObject().getAxisRight().setTextColor(textColor);
        getObject().getXAxis().setTextColor(textColor);
        getObject().getLegend().setTextColor(textColor);
    }
}
