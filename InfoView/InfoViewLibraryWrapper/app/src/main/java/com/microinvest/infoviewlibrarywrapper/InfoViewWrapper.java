package com.microinvest.infoviewlibrarywrapper;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.marcoscg.infoview.InfoView;

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
@BA.DependsOn(values = {"InfoViewLibrary.androidx.aar"})
@BA.Events(values = {"TryAgainClicked"})
@BA.ShortName("InfoView")
public class InfoViewWrapper extends ViewWrapper<InfoView> implements Common.DesignerCustomView {
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new InfoView(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
        if(ba.subExists(eventName + "_tryagainclicked")) {
            getObject().setOnTryAgainClickListener(new InfoView.OnTryAgainClickListener() {
                @Override
                public void onTryAgainClick() {
                    ba.raiseEventFromUI(getObject(), eventName + "_tryagainclicked");
                }
            });
        }
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, InfoView.class, props, designer);
        }
        InfoView iv = (InfoView) ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                iv.setBackgroundDrawable(d);
            }
        }
        return iv;
    }

    @Override
    @BA.Hide
    public void _initialize(BA ba, Object o, String EventName) {
        final InfoView iv = new InfoView(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(iv);
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

    public void setTitle(String title) {
        getObject().setTitle(title);
    }

    public void setTitleTextColor(int titleTextColor) {
        getObject().setTitleTextColor(titleTextColor);
    }

    public void setMessage(String message) {
        getObject().setMessage(message);
    }

    public void setMessageTextColor(int textColor) {
        getObject().setMessageTextColor(textColor);
    }

    public void setIconDrawable(Object drawable) {
        getObject().setIconDrawable((Drawable) drawable);
    }

    public void setIconDrawableColor(int color) {
        getObject().setIconDrawableColor(color);
    }

    public void setButtonText(String text) {
        getObject().setButtonText(text);
    }

    public void setButtonTextColor(int color) {
        getObject().setButtonTextColor(color);
    }

    public void setProgress(boolean enabled) {
        getObject().setProgress(enabled);
    }
}
