package tgs.com.junglecookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;


/**
 * Created by metro on 2/1/2017.
 */
public class TextSliderViews extends BaseSliderView {
    public TextSliderViews(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_test2,null);
        ImageView target = (ImageView)v.findViewById(R.id.daimajia_slider_image);
     //   TextView description = (TextView)v.findViewById(R.id.description);
      // description.setText(getDescription());
        bindEventAndShow(v, target);
        return v;
    }
}
