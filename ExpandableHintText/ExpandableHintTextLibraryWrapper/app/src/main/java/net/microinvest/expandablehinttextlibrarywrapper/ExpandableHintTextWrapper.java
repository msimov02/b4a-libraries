package net.microinvest.expandablehinttextlibrarywrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;

import com.tomlonghurst.expandablehinttext.ExpandableHintText;

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
@BA.DependsOn(values = {"ExpandableHintTextLibrary.aar", "kotlin-stdlib-1.4.20.jar"})
@BA.Events(values = {"TabSelected (IsLeftTab as Boolean, TabTextValue as String)"})
@BA.ShortName("ExpandableHintText")
public class ExpandableHintTextWrapper extends ViewWrapper<ExpandableHintText> implements Common.DesignerCustomView{
    @Override
    public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
        if (!keepOldObject)
            setObject(new ExpandableHintText(ba.context));
        super.innerInitialize(ba, eventName, keepOldObject);
    }
    @BA.Hide
    public static View build(Object prev, HashMap<String, Object> props, boolean designer, Object tag) throws Exception {
        if (prev == null) {
            prev = ViewWrapper.buildNativeView((Context)tag, ExpandableHintText.class, props, designer);
        }
        ExpandableHintText eht = (ExpandableHintText) ViewWrapper.build(prev, props, designer);
        HashMap<String, Object> drawProps = (HashMap<String, Object>) props.get("drawable");
        if(drawProps != null) {
            Drawable d = DynamicBuilder.build(prev, drawProps, designer, null);
            if(d != null) {
                eht.setBackgroundDrawable(d);
            }
        }
        return eht;
    }

    @Override
    @BA.Hide
    public void _initialize(BA ba, Object o, String EventName) {
        final ExpandableHintText eht = new ExpandableHintText(ba.context);
        final String eventName = EventName.toLowerCase(BA.cul);
        setObject(eht);
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

    public void setText(String text) {
        getObject().setText(text);
    }

    public void setAnimationDurationMs(int animationDurationMs) {
        getObject().setAnimationDurationMs(animationDurationMs);
    }
    public void setEnabled(boolean enabled) {
        getObject().setEnabled(enabled);
    }

    public void setFloatingLabelColor(int floatingLabelColor) {
        getObject().setFloatingLabelColor(floatingLabelColor);
    }

    public void setHintText(String hintText) {
        getObject().setHintText(hintText);
    }

    public void setInputType(int inputType) {
        getObject().setInputType(inputType);
    }

    public void setMaxLines(int maxLines) {
        getObject().setMaxLines(maxLines);
    }

    public void setReadOnly(boolean readOnly) {
        getObject().setReadOnly(readOnly);
    }

    public void setTextBoxColor(int textBoxColor) {
        getObject().setTextBoxColor(textBoxColor);
    }

    public void setTextColor(int textColor) {
        getObject().setTextColor(textColor);
    }

    public void setTextSize(float textSize) {
        getObject().setTextSize(textSize);
    }

}
