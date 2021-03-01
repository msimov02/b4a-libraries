package net.microinvest.dayviewwrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.DynamicBuilder;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;
import pl.digitalzombielab.dayview.DayView;

@BA.ActivityObject
@BA.Version(0.01F)
@BA.DependsOn(values = {"DayViewLibrary.aar","kotlin-stdlib-1.4.20.jar"})
@BA.Events(values = {"Click"})
@BA.ShortName("DayView")
public class DayViewWrapper extends ViewWrapper<DayView> implements Common.DesignerCustomView {
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new DayView(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
        if(ba.subExists(eventName + "_Click")) {
            getObject().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ba.raiseEventFromUI(getObject(), eventName + "_click");
                }
            });
        }
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, DayView.class, props, designer);
        }
        DayView dv = (DayView)ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                dv.setBackgroundDrawable(d);
            }
        }
        return dv;
    }

    @BA.Hide
    @Override
    public void _initialize(BA ba, Object o, String EventName) {
        final DayView dv = new DayView(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(dv);
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

    public void setBarColor(int color) {
        getObject().setBarColor(color);
    }

    public void setBorderColor(int color) {
        getObject().setBorderColor(color);
    }

    public void setCardBackgroundColor(int color) {
        getObject().setCardBackgroundColor(color);
    }
    public void setTextColor(int color) {
        getObject().setTextColor(color);
    }
    public void setDate(int year, int month, int day ) throws ParseException {
        getObject().setDate(new Date(year, month - 1, day));
    }
}
