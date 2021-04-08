package net.microinvest.tileprogressviewwrapper;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.iammert.tileprogressview.TiledProgressView;

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
@BA.DependsOn(values = {"TileProgressViewLibrary.aar", "kotlin-stdlib-1.4.20.jar", "androidx-core.jar"})
@BA.ShortName("TileProgressView")
public class TileProgressViewWrapper extends ViewWrapper<TiledProgressView> implements Common.DesignerCustomView{

    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new TiledProgressView(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
    }

    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, TiledProgressView.class, props, designer);
        }
        TiledProgressView tpv = (TiledProgressView)ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                tpv.setBackgroundDrawable(d);
            }
        }
        return tpv;
    }

    @Override
    @BA.Hide
    public void _initialize(BA ba, Object o, String EventName) {
        final TiledProgressView tpv = new TiledProgressView(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(tpv);
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

    public void setProgress(float progress) {
        getObject().setProgress(progress);
    }

    public void setTileColor(int color) {
        getObject().setColor(color);
    }

    public void setLoadingColor(int loadingColor) {
        getObject().setLoadingColor(loadingColor);
    }
}
