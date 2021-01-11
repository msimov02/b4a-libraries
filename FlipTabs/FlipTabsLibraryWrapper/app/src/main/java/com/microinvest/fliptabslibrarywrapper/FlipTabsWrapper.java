package com.microinvest.fliptabslibrarywrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.jem.fliptabs.FlipTab;

import java.util.HashMap;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.DynamicBuilder;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;

@BA.ActivityObject
@BA.Version(0.01F)
@BA.DependsOn(values = {"FlipTabsLibrary.aar", "kotlin-stdlib-1.4.20.jar"})
@BA.Events(values = {"TabSelected (IsLeftTab as Boolean, TabTextValue as String)"})
@BA.ShortName("FlipTabs")
public class FlipTabsWrapper extends ViewWrapper<FlipTab> implements Common.DesignerCustomView{
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new FlipTab(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
        if(ba.subExists(eventName + "_tabselected")) {
            getObject().setTabSelectedListener(new FlipTab.TabSelectedListener() {
                @Override
                public void onTabSelected(boolean b, String s) {
                    ba.raiseEventFromUI(getObject(), eventName + "_tabselected", b, s);
                }

                @Override
                public void onTabReselected(boolean b, String s) {

                }
            });
        }
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, FlipTab.class, props, designer);
        }
        FlipTab ft = (FlipTab)ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                ft.setBackgroundDrawable(d);
            }
        }
        return ft;
    }

    @Override
    @BA.Hide
    public void _initialize(BA ba, Object o, String EventName) {
        final FlipTab ft = new FlipTab(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(ft);
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

    public void setLeftTabText(String text) {
        getObject().setLeftTabText(text);
    }

    public void setRightTabText(String text) {
        getObject().setRightTabText(text);
    }

    public void setFlipAnimationDuration(int duration) {
        getObject().setFlipAnimationDuration(duration);
    }

    public void setOverallColor(int color) {
        getObject().setOverallColor(color);
    }

    public void setHighlightColor(int color) {
        getObject().setHighlightColor(color);
    }

    public void setTextColor(int color) {
        getObject().setTextColor(color);
    }

    public void setWobbleReturnAnimationDuration(int duration) {
        getObject().setWobbleReturnAnimationDuration(duration);
    }

    public void setWobbleAngle(float angle) {
        getObject().setWobbleAngle(angle);
    }

    public void setBorderWidth(int width) {
        getObject().setBorderWidth(width);
    }

    public void selectLeftTab(boolean withAnimation, boolean raiseEvent) {
        getObject().selectLeftTab(withAnimation, raiseEvent);
    }

    public void selectRightTab(boolean withAnimation, boolean raiseEvent) {
        getObject().selectRightTab(withAnimation, raiseEvent);
    }



}
