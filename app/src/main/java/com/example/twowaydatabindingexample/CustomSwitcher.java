package com.example.twowaydatabindingexample;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.Observable;
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
        this.binding.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.item) {
                    setVm(binding.getItem());
                }
            }
        });
    }

    public void setOnValChanged(OnValueChanged listener){
        this.onValChanged = listener;
    }

    public boolean getVm(){
        return vm;
    }

    public void setVm(boolean vmVal){
        if (vmVal != this.vm) {
            this.vm = vmVal;
            this.binding.setItem(vm);
            if (this.onValChanged != null) {
                this.onValChanged.onValChanged(this, vmVal);
            }
        }
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
