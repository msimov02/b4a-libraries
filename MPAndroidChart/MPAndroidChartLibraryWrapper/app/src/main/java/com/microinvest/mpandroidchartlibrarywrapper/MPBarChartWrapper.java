package com.microinvest.mpandroidchartlibrarywrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

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
@BA.ShortName("MPBarChart")
public class MPBarChartWrapper extends ViewWrapper<BarChart> implements Common.DesignerCustomView {

    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new BarChart(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);

        getObject().setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(final Entry selectedEntry, Highlight highlight) {
                for(IBarDataSet iBarDataSet : getObject().getBarData().getDataSets()) {
                    iBarDataSet.setValueFormatter(new IValueFormatter() {
                        @Override
                        public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {

                            if(selectedEntry.getY() == entry.getY()) {
                                return String.valueOf(new LargeValueFormatter().getFormattedValue(v, entry, i, viewPortHandler));
                            }
                            return "";
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected() {
                for(IBarDataSet iBarDataSet : getObject().getBarData().getDataSets()) {
                    iBarDataSet.setValueFormatter(new LargeValueFormatter());
                }
            }
        });
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, BarChart.class, props, designer);
        }
        BarChart bc = (BarChart)ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                bc.setBackgroundDrawable(d);
            }
        }
        return bc;
    }

    @BA.Hide
    @Override
    public void _initialize(BA ba, Object o, String EventName) {
        final BarChart bc = new BarChart(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(bc);
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

    public void SetYValues(final List<String> YValues, String ChartTitle, int ValueTextColor, int BarColor, int ValueTextSize) {

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < YValues.size(); i++) {
            float x = i;
            float y = Float.parseFloat(YValues.get(i));
            values.add(new BarEntry(x, y));
        }

        BarDataSet set = new BarDataSet(values, ChartTitle);
        set.setColor(BarColor);
        set.setValueTextColor(ValueTextColor);
        set.setValueTextSize(ValueTextSize);
        set.setValueFormatter(new LargeValueFormatter());

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        BarData data = new BarData(dataSets);
        getObject().setData(data);
    }

    public void setPinchZoom(boolean pinchZoom) {
        getObject().setPinchZoom(pinchZoom);
    }

    public void setDoubleTapToZoom(boolean doubleTapToZoom) {
        getObject().setDoubleTapToZoomEnabled(false);
    }

    public void setDrawValuesAboveBar(boolean drawValuesAboveBar) {
        getObject().setDrawValueAboveBar(drawValuesAboveBar);
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

    public void setVisibleXRangeMaximum(int visibleXRangeMaximum) {
        getObject().setVisibleXRangeMaximum(visibleXRangeMaximum);
    }

    public void setTextColor(int textColor) {
        getObject().getAxisLeft().setTextColor(textColor);
        getObject().getAxisRight().setTextColor(textColor);
        getObject().getXAxis().setTextColor(textColor);
        getObject().getLegend().setTextColor(textColor);
    }

}
