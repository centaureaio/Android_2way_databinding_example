package com.example.twowaydatabindingexample;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.twowaydatabindingexample.databinding.CustomSwitcherBinding;

@InverseBindingMethods({
        @InverseBindingMethod(type = CustomSwitcher.class, attribute = "vm")
})

public class CustomSwitcher extends LinearLayout {

    public interface OnValueChanged{
        void onValChanged(CustomSwitcher view, boolean val);
    }

    private final CustomSwitcherBinding binding;
    private boolean vm;
    private OnValueChanged onValChanged;

    public CustomSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.binding = CustomSwitcherBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setOnValChanged(OnValueChanged listener){
        this.onValChanged = listener;
    }

    public boolean getVm(){
        return vm;
    }

    public void setVm(boolean vmVal){
        this.vm = vmVal;
        this.binding.setItem(vm);
    }

    @BindingAdapter("vmAttrChanged")
    public static void setVmListener(CustomSwitcher view, final InverseBindingListener vmChange){
        if(vmChange == null){
            view.setOnValChanged(null);
        }else{
            view.setOnValChanged(new OnValueChanged() {
                @Override
                public void onValChanged(CustomSwitcher view, boolean val) {
                    vmChange.onChange();
                }
            });
        }
    }
}
