package net.microinvest.floatingactionbuttonwrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;

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
@BA.DependsOn(values = {"FloatingActionButtonLibrary.aar"})
@BA.Events(values = {"Click"})
@BA.ShortName("FloatingActionButton")
public class FloatingActionButtonWrapper extends ViewWrapper<FloatingActionButton> implements Common.DesignerCustomView{
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new FloatingActionButton(ba.context));
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
            prev = ViewWrapper.buildNativeView((Context)tag, FloatingActionButton.class, props, designer);
        }
        FloatingActionButton fab = (FloatingActionButton)ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                fab.setBackgroundDrawable(d);
            }
        }
        return fab;
    }

    @BA.Hide
    @Override
    public void _initialize(BA ba, Object o, String EventName) {
        final FloatingActionButton fab = new FloatingActionButton(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(fab);
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

    public void setActionButtonColors(int colorNormal, int colorPressed, int colorDisabled) {
        getObject().setColorNormal(colorNormal);
        getObject().setColorPressed(colorPressed);
        getObject().setColorDisabled(colorDisabled);
    }

    public void setIconImageResource(Object drawable) {
        getObject().setImageDrawable((Drawable) drawable);
    }

    public FloatingActionButton asClass() {
        return getObject();
    }

}
